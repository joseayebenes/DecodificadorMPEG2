import models.*;

import javax.sound.midi.SysexMessage;

public class ProcesarSecuenciaThread implements Runnable {

    private int picture_start_code = 0x100;
    private int user_data_start_code = 0x1b2;
    private int sequence_header_code = 0x1b3;
    private int sequence_error_code = 0x1b4;
    private int extension_start_code = 0x1B5;
    private int sequence_end_code = 0x1b7;
    private int group_start_code = 0x1b8;


    private LectorBits lb;
    Sequence sq;
    TablasVLC tablasVLC;

    int [] dct_dc_pred = new int[3];

    int picActual;
    int gopActual;
    int sliceActual;
    int macroActual;

    public ProcesarSecuenciaThread(byte[] data, Sequence sq) {
        lb = new LectorBits(data);
        this.sq = sq;
        picActual=-1;
        gopActual=-1;
        sliceActual=-1;
        macroActual=-1;

        tablasVLC = new TablasVLC();
        for(int i=0; i<3;i++){
            dct_dc_pred[i]=0;
        }
    }

    @Override
    public void run() {
        sequence_header();
        sequence_extension();
        //extension_and_user_data(0);
        //do{
        if (lb.showNextBits(32) == group_start_code) {
            group_of_pictures_header();
            // extension_and_user_data(1);
        }
        picture_header();
        picture_coding_extension();
        // extension_and_user_data(2);
         picture_data();

        //}while((lb.showNextBits(32)==picture_start_code)||(lb.showNextBits(32)==group_start_code));

        //if(lb.showNextBits(32)!=sequence_end_code){
        //    sequence_header();
        //    sequence_extension();
        //}
    }

    private void picture_data() {
        sliceActual=-1;
        //do{
            slice();
        //}while(0x101<= lb.showNextBits(32) && lb.showNextBits(32)<=0x1AF);
        //lb.next_start_code();
    }

    private void slice() {
        Slice slice = new Slice();
        sq.picts.get(picActual).slices.add(slice);
        sliceActual++;

        slice.slice_start_code = lb.getNextBits(32);

        if(sq.sqh.vertical_size_value>2800){
            slice.slice_vertical_position_extension = (byte) lb.getNextBits(3);
        }
        //if(isSequenceScalable){
            //if(scalable_mode == 'data partitioning'){
         //   slice.priority_breakpoint = (byte) lb.getNextBits(7);
            //}
       // }

        slice.quantiser_scale_code = (byte) lb.getNextBits(5);
        if(lb.showNextBits(1)==1){
            slice.slice_extension_flag = lb.getNextBits(1)==1;
            slice.intra_slice = lb.getNextBits(1)==1;
            slice.slice_picture_id_enable = lb.getNextBits(1)==1;
            slice.slice_picture_id = (byte)lb.getNextBits(60);
            while(lb.showNextBits(1)==1){
                slice.extra_bit_slice=lb.getNextBits(1)==1;
                lb.getNextBits(8);
            }
        }
        slice.extra_bit_slice = lb.getNextBits(1)==1;
        macroActual=-1;
        do{
            macroblock();
        }while(lb.showNextBits(23)!= 0x00);

        lb.next_start_code();


    }

    private void macroblock() {
        Macroblock macro  =new Macroblock();

        while(lb.showNextBits(11)==0x08){lb.getNextBits(11);}


        macro.macroblock_address_increment= (byte) lb.getVLC(tablasVLC.macrobloc_address_increment,11);
        macroblock_modes(macro);

        if(macro.macroblock_type.macroblock_quant){
            macro.quantiser_scale_code= (byte) lb.getNextBits(5);
        }

        if(macro.macroblock_type.macroblock_motion_forward ||
                (macro.macroblock_type.macroblock_intra&&sq.picts.get(picActual).pice.concealment_motion_vector )){
            motion_vectors(0,macro);
        }
        if(macro.macroblock_type.macroblock_motion_backward){
            motion_vectors(1,macro);
        }
        if(macro.macroblock_type.macroblock_intra&&(macro.macroblock_type.macroblock_intra&&sq.picts.get(picActual).pice.concealment_motion_vector )){
            macro.marker_bit=lb.getNextBits(1)==1;
        }
        if(macro.macroblock_type.macroblock_pattern){
            coded_block_pattern(macro);
        }

        for(int i=0; i<12;i++){
            if(macro.macroblock_type.macroblock_intra){
                macro.pattern_code[i]=true;
            }else{
                macro.pattern_code[i]=false;
            }
        }
        if(macro.macroblock_type.macroblock_pattern){
            for(int i=0;i<6;i++){
                if((macro.coded_block_pattern_420 & (1<<(5-i)))==1){
                    macro.pattern_code[i]=true;
                }
            }
            if(chroma_format(sq.sqe.chroma_format)=="4:2:2"){
                for(int i=6;i<8;i++){
                    if((macro.coded_block_pattern_1 & (1<<(7-i)))==1){
                        macro.pattern_code[i]=true;
                    }
                }
            }
            if(chroma_format(sq.sqe.chroma_format)=="4:2:4"){
                for(int i=6;i<812;i++){
                    if((macro.coded_block_pattern_2 & (1<<(11-i)))==1){
                        macro.pattern_code[i]=true;
                    }
                }
            }
        }

        for(int i=0; i<6;i++){
            System.out.println("------");
            block(i,macro);
            System.out.println("......");
        }
        System.out.println("");
        sq.picts.get(picActual).slices.get(sliceActual).macroblocks.add(macro);
        macroActual++;
    }

    private void block(int i, Macroblock macro){
        Block block =new Block();
        macro.blocks.add(block);

        int n=0;
        int[] QFS = new int[64];

        boolean eob_not_read=true;

        int dct_dc_size;
        int dct_dc_differential;
        int half_range;
        int dct_diff;

        if(macro.pattern_code[i]){
            if(macro.macroblock_type.macroblock_intra) {
                n = 1;
                if (i < 4) {
                    block.dct_dc_size_luminance = lb.getVLC(tablasVLC.dct_dc_size_luminance, 9);

                    if (block.dct_dc_size_luminance != 0) {
                        block.dct_dc_differential = lb.getNextBits(block.dct_dc_size_luminance);
                    }
                    dct_dc_size = block.dct_dc_size_luminance;
                    dct_dc_differential = block.dct_dc_differential;
                } else {
                    block.dct_dc_size_chrominance = lb.getVLC(tablasVLC.dct_dc_size_chrominance, 10);
                    if (block.dct_dc_size_chrominance != 0) {
                        block.dct_dc_differential = lb.getNextBits(block.dct_dc_size_chrominance);
                    }
                    dct_dc_size = block.dct_dc_size_chrominance;
                    dct_dc_differential = block.dct_dc_differential;
                }

                if(dct_dc_size==0){
                    dct_diff=0;
                }else{
                    half_range=2^(dct_dc_size-1);
                    if(dct_dc_differential>=half_range){
                        dct_diff=dct_dc_differential;
                    }else{
                        dct_diff=(dct_dc_differential+1)-(2*half_range);
                    }
                }
                QFS[0]=dct_dc_pred[cc(i)]+dct_diff;
                dct_dc_pred[cc(i)]=QFS[0];

            }else{
                // FIRST DCT
            }

            while (eob_not_read){
                RunLevel aux = lb.getDCTCoef(tablasVLC.dct_coeff_zero,!macro.macroblock_type.macroblock_intra);
                RunLevel rl = new RunLevel(aux.run,aux.level);
                if(rl.level==0&&rl.run==0){
                    System.out.println("ERROR");
                    return;
                }
                if(rl.run==-1){
                    eob_not_read=false;
                    while (n<64){
                        QFS[n]=0;
                        n=n+1;
                    }
                }else{
                    for(int m=0; m<rl.run&&n<63;m++){
                        QFS[n]=0;
                        n=n+1;
                    }
                    QFS[n]=rl.level;
                    n=n+1;
                }
            }
            System.out.print("");
        }
    }
    int cc(int block_number){
        if(block_number<3){
            return 0;
        }
        if (block_number==4 || block_number==6|| block_number==8|| block_number==10){
            return 1;
        }
        if (block_number==5 || block_number==7|| block_number==9|| block_number==11){
            return 2;
        }
        return 0;

    }
    String chroma_format(byte chroma_format){
        switch (chroma_format){
            case 0x1:
                return "4:2:0";
            case 0x2:
                return "4:2:2";
            case 0x3:
                return "4:4:4";
        }
        return "";
    }
    private void macroblock_modes(Macroblock macro){

        macro.macroblock_type = getMacroBlockType();

        if (macro.macroblock_type.spatial_temporal_weight_code_flag  ){
            macro.spatial_temporal_weight_code = (byte) lb.getNextBits(2);
        }

        if(macro.macroblock_type.macroblock_motion_forward || macro.macroblock_type.macroblock_motion_backward){
            if(sq.picts.get(picActual).pice.picture_structure == 0x03){
                if(sq.picts.get(picActual).pice.frame_pred_frame_dct==false){
                    macro.frame_motion_type = (byte)lb.getNextBits(2);
                }

            }else{
                macro.field_motion_type = (byte)lb.getNextBits(2);
            }
        }

        if((sq.picts.get(picActual).pice.picture_structure == 0x03)&&
                (sq.picts.get(picActual).pice.frame_pred_frame_dct==false)&&
                (macro.macroblock_type.macroblock_intra||macro.macroblock_type.macroblock_pattern)){
            macro.dct_type = lb.getNextBits(1)==1;
        }

    }

    private MacroblockType getMacroBlockType() {
        short type =sq.picts.get(picActual).pich.picture_coding_type ;
        MacroblockType macroblockType=null;
        String key;
        switch (type){
            case 0x01:
                key = lb.getVLCKey(tablasVLC.macrobloc_type_I,9);
                return tablasVLC.macrobloc_type_I.get(key);
            case 0x02:
                key = lb.getVLCKey(tablasVLC.macrobloc_type_P,9);
                return tablasVLC.macrobloc_type_I.get(key);
            case 0x03:
                key = lb.getVLCKey(tablasVLC.macrobloc_type_B,9);
                return tablasVLC.macrobloc_type_I.get(key);
        }
        return macroblockType;
    }
    private void coded_block_pattern(Macroblock macro){
        macro.coded_block_pattern_420 = (short) lb.getVLC(tablasVLC.coded_block_pattern,9);

        if(chroma_format(sq.sqe.chroma_format)=="4:2:2"){
            macro.coded_block_pattern_1 = (byte) lb.getNextBits(2);
        }
        if(chroma_format(sq.sqe.chroma_format)=="4:4:4"){
            macro.coded_block_pattern_2 = (byte) lb.getNextBits(6);
        }

        for(int i=0;i<12;i++){
            if(macro.macroblock_type.macroblock_intra){
                macro.pattern_code[i]=true;
            }else{
                macro.pattern_code[i]=false;
            }
        }

    }
    private void motion_vectors( int s , Macroblock macro ){
        if(macro.field_motion_type==0x01 && macro.field_motion_type==0x03){
            if(macro.frame_motion_type!=0x02 && macro.frame_motion_type!=0x03){
                macro.motion_vertical_field_select[0][s] = lb.getNextBits(1)==1;
            }
            motion_vector(0,s);
        }else{
            macro.motion_vertical_field_select[0][s] = lb.getNextBits(1)==1;
            motion_vector(0,s);
            macro.motion_vertical_field_select[1][s] = lb.getNextBits(1)==1;
            motion_vector(1,s);
        }
    }
    private void motion_vector(int r, int s){
        Macroblock macro =sq.picts.get(picActual).slices.get(sliceActual).macroblocks.get(macroActual) ;
        macro.motion_code[r][s][0] = (short) lb.getVLC(tablasVLC.motion_code,11);
        if((sq.picts.get(picActual).pice.f_code[s][0]!=1 && macro.motion_code[r][s][0]!=0)){
            macro.motion_residual[r][s][0] = (byte) lb.getNextBits(sq.picts.get(picActual).pice.f_code[s][0]-1);
        }
        if(macro.field_motion_type == 0x03){
            macro.dmvector[0]= (byte) lb.getVLC(tablasVLC.dmvector, 2);
        }
        macro.motion_code[r][s][1] = (short) lb.getVLC(tablasVLC.motion_code,11);
        if(sq.picts.get(picActual).pice.f_code[s][1]!=1 && macro.motion_code[r][s][1]!=0){
            macro.motion_residual[r][s][1] = (byte) lb.getNextBits(sq.picts.get(picActual).pice.f_code[s][1]-1);
        }
        if(macro.field_motion_type == 0x03){
            macro.dmvector[1]= (byte) lb.getVLC(tablasVLC.dmvector, 2);
        }

    }
    private void picture_coding_extension() {
        PictureCodingExtension pic = new PictureCodingExtension();
        pic.extension_start_code=lb.getNextBits(32);
        pic.extension_start_code_identifier= (byte) lb.getNextBits(4);
        pic.f_code[0][0] = (byte) lb.getNextBits(4);
        pic.f_code[0][1] = (byte) lb.getNextBits(4);
        pic.f_code[1][0] = (byte) lb.getNextBits(4);
        pic.f_code[1][1] = (byte) lb.getNextBits(4);
        pic.intra_dc_precision=(byte) lb.getNextBits(2);
        pic.picture_structure=(byte) lb.getNextBits(2);
        pic.top_field_first=lb.getNextBits(1)==1;
        pic.frame_pred_frame_dct=lb.getNextBits(1)==1;
        pic.concealment_motion_vector=lb.getNextBits(1)==1;
        pic.q_scale_type=lb.getNextBits(1)==1;
        pic.intra_vlc_format=lb.getNextBits(1)==1;
        pic.alternate_scan=lb.getNextBits(1)==1;
        pic.repeat_first_field=lb.getNextBits(1)==1;
        pic.chroma_420_type=lb.getNextBits(1)==1;
        pic.progressive_frame=lb.getNextBits(1)==1;
        pic.composite_display_flag=lb.getNextBits(1)==1;
        if(pic.composite_display_flag){
            pic.v_axis=lb.getNextBits(1)==1;
            pic.field_sequence=(byte) lb.getNextBits(3);
            pic.sub_carrier=lb.getNextBits(1)==1;
            pic.burst_amplitude=(byte) lb.getNextBits(7);
            pic.sub_carrier_phase=(byte) lb.getNextBits(8);
        }
        lb.next_start_code();

        sq.picts.get(picActual).pice = pic;

    }

    private void picture_header() {
        Picture pic = new Picture();
        pic.pich.picture_start_code = lb.getNextBits(32);
        pic.pich.temporal_reference=(short)lb.getNextBits(10);
        pic.pich.picture_coding_type=(byte)lb.getNextBits(3);
        pic.pich.vbv_delay=(short)lb.getNextBits(16);
        if( pic.pich.picture_coding_type==2 || pic.pich.picture_coding_type==3){
            pic.pich.full_pel_forward_vector=lb.getNextBits(1)==1;
            pic.pich.forward_f_code=(byte)lb.getNextBits(3);
        }

        if(pic.pich.picture_coding_type==3){
            pic.pich.full_pel_backward_vector=lb.getNextBits(1)==1;
            pic.pich.backward_f_code=(byte)lb.getNextBits(3);
        }

        while(lb.showNextBits(1)==0x1){
            pic.pich.extra_bit_picture.add(lb.getNextBits(1)==1);
            pic.pich.extra_information_picture.add((byte) lb.getNextBits(8));
        }

        pic.pich.extra_bit_picture.add(lb.getNextBits(1)==1);

        lb.next_start_code();
        sq.picts.add(pic);
        picActual++;
    }

    private void group_of_pictures_header() {

        GroupOfPicturesHeader goph = new GroupOfPicturesHeader();
        goph.group_start_code = lb.getNextBits(32);
        goph.time_code = lb.getNextBits(25);
        goph.closed_gop = lb.getNextBits(1) == 1;
        goph.broken_link = lb.getNextBits(1) == 1;
        lb.next_start_code();

        sq.gops.add(goph);
    }

    private void sequence_header() {
        sq.sqh.sequence_header_code = lb.getNextBits(32);
        sq.sqh.horizontal_size_value = (short) lb.getNextBits(12);
        sq.sqh.vertical_size_value = (short) lb.getNextBits(12);
        sq.sqh.aspect_ratio_information = (byte) lb.getNextBits(4);
        sq.sqh.frame_rate_code = (byte) lb.getNextBits(4);
        sq.sqh.bit_rate_value = lb.getNextBits(18);
        sq.sqh.marker_bit = lb.getNextBits(1) == 1;
        sq.sqh.vbv_buffer_size_value = (short) lb.getNextBits(10);
        sq.sqh.constrained_parameters_flag = lb.getNextBits(1) == 1;
        sq.sqh.load_intra_quantiser_matrix = lb.getNextBits(1) == 1;
        if (sq.sqh.load_intra_quantiser_matrix) {
            sq.sqh.intra_quantiser_matrix = new byte[64];
            for (int i = 0; i < 64; i++) {
                sq.sqh.intra_quantiser_matrix[i] = (byte) lb.getNextBits(8);
            }
        }
        sq.sqh.load_non_intra_quantiser_matrix = lb.getNextBits(1) == 1;
        if (sq.sqh.load_non_intra_quantiser_matrix) {
            sq.sqh.non_intra_quantiser_matrix = new byte[64];
            for (int i = 0; i < 64; i++) {
                sq.sqh.non_intra_quantiser_matrix[i] = (byte) lb.getNextBits(8);
            }
        }

        lb.next_start_code();
    }

    private void sequence_extension() {


        sq.sqe.extension_start_code = lb.getNextBits(32);
        sq.sqe.extension_start_code_identifier = (byte) lb.getNextBits(4);
        sq.sqe.profile_and_level_indication = (byte) lb.getNextBits(8);
        sq.sqe.progressive_sequence = lb.getNextBits(1) == 1;
        sq.sqe.chroma_format = (byte) lb.getNextBits(2);
        sq.sqe.horizontal_size_extension = (byte) lb.getNextBits(2);
        sq.sqe.vertical_size_extension = (byte) lb.getNextBits(2);
        sq.sqe.bit_rate_extension = (short) lb.getNextBits(12);
        sq.sqe.marker_bit = lb.getNextBits(1) == 1;
        sq.sqe.vbv_buffer_size_extension = (byte) lb.getNextBits(8);
        sq.sqe.low_delay = lb.getNextBits(1) == 1;
        sq.sqe.frame_rate_extension_n = (byte) lb.getNextBits(2);
        sq.sqe.frame_rate_extension_d = (byte) lb.getNextBits(5);

        lb.next_start_code();
    }

}