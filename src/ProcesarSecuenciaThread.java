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

    int picActual;
    int gopActual;
    int sliceActual;

    public ProcesarSecuenciaThread(byte[] data) {
        lb = new LectorBits(data);
        sq = new Sequence();
        picActual=-1;
        gopActual=-1;
        sliceActual=-1;
    }

    public ProcesarSecuenciaThread(byte[] data, Sequence sq) {
        lb = new LectorBits(data);
        this.sq = sq;
        picActual=-1;
        gopActual=-1;
        sliceActual=-1;
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
/*
        do{
            macroblock();
        }while(lb.showNextBits(23)!= 0x00);*/

        lb.next_start_code();

        sq.picts.get(picActual).slices.add(slice);
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