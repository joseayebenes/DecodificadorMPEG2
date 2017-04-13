package models;

/**
 * Created by joseayebenes on 12/03/2017.
 */
public class PictureCodingExtension {
    public int extension_start_code;
    public byte extension_start_code_identifier;
    public byte[][] f_code = new byte[2][2];
    public byte intra_dc_precision;
    public byte picture_structure;
    public boolean top_field_first;
    public boolean frame_pred_frame_dct = true;
    public boolean concealment_motion_vector;
    public boolean q_scale_type;
    public boolean intra_vlc_format;
    public boolean alternate_scan;
    public boolean repeat_first_field;
    public boolean chroma_420_type;
    public boolean progressive_frame;
    public boolean composite_display_flag;

    public boolean v_axis;
    public byte field_sequence;
    public boolean sub_carrier;
    public byte burst_amplitude;
    public byte sub_carrier_phase;


    @Override
    public String toString() {
        String s="";
        s+="extension_start_code= "+extension_start_code+"\n";
        s+="extension_start_code_identifier= "+extension_start_code_identifier+"\n";
        s+="f_code= "+"\n";
        s+="[0][0] = "+f_code[0][0]+"\n";
        s+="[0][1] = "+f_code[0][1]+"\n";
        s+="[1][0] = "+f_code[1][0]+"\n";
        s+="[1][1] = "+f_code[1][1]+"\n";
        s+="intra_dc_precision= "+intra_dc_precision+"\n";
        s+="picture_structure= "+picture_structure+"\n";
        s+="top_field_first= "+top_field_first+"\n";
        s+="frame_pred_frame_dct= "+frame_pred_frame_dct+"\n";
        s+="concealment_motion_vector= "+concealment_motion_vector+"\n";
        s+="q_scale_type= "+q_scale_type+"\n";
        s+="intra_vlc_format= "+intra_vlc_format+"\n";
        s+="alternate_scan= "+alternate_scan+"\n";
        s+="repeat_first_field= "+repeat_first_field+"\n";
        s+="chroma_420_type= "+chroma_420_type+"\n";
        s+="progressive_frame= "+progressive_frame+"\n";
        s+="composite_display_flag= "+composite_display_flag+"\n";
        s+="v_axis= "+v_axis+"\n";
        s+="field_sequence= "+field_sequence+"\n";
        s+="sub_carrier= "+sub_carrier+"\n";
        s+="burst_amplitude= "+burst_amplitude+"\n";
        s+="sub_carrier_phase= "+sub_carrier_phase+"\n";

        return s;
    }
}
