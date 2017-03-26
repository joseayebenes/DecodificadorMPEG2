package models;

/**
 * Created by joseayebenes on 11/03/2017.
 */
public class SequenceExtension {
    public int extension_start_code;
    public byte extension_start_code_identifier;
    public byte profile_and_level_indication;
    public boolean progressive_sequence;
    public byte chroma_format;
    public byte horizontal_size_extension;
    public byte vertical_size_extension;
    public short bit_rate_extension;
    public boolean marker_bit;
    public byte vbv_buffer_size_extension;
    public boolean low_delay;
    public byte frame_rate_extension_n;
    public byte frame_rate_extension_d;

    @Override
    public String toString() {
        String s ="";
        s+="extension_start_code = "+extension_start_code+"\n";
        s+="extension_start_code_identifier = "+extension_start_code_identifier+" : "+start_code_identifier()+"\n";
        s+="profile_and_level_indication = "+profile_and_level_indication+"\n";
        s+="progressive_sequence = "+progressive_sequence+"\n";
        s+="chroma_format = "+chroma_format+" : "+s_chroma_format()+"\n";
        s+="horizontal_size_extension = "+horizontal_size_extension+"\n";
        s+="vertical_size_extension = "+vertical_size_extension+"\n";
        s+="bit_rate_extension = "+bit_rate_extension+"\n";
        s+="marker_bit = "+marker_bit+"\n";
        s+="vbv_buffer_size_extension = "+vbv_buffer_size_extension+"\n";
        s+="low_delay = "+low_delay+"\n";
        s+="frame_rate_extension_n = "+frame_rate_extension_n+"\n";
        s+="frame_rate_extension_d = "+frame_rate_extension_d+"\n";
        return s;
    }

    private String start_code_identifier(){
        String result="";

        switch (extension_start_code_identifier){
            case 1:
                result="Sequence Extension ID";
                break;
            case 2:
                result="Sequence Display Extension ID ";
                break;
            case 3:
                result="Quant Matrix Extension ID";
                break;
            case 4:
                result="Copyright Extension ID ";
                break;
            case 5:
                result="Sequence Scalable Extension ID";
                break;
            case 6:
                result="Reserved";
                break;
            case 7:
                result="Picture Display Extension ID ";
                break;
            case 8:
                result="Picture Coding Extension ID";
                break;
            case 9:
                result="Picture Spatial Scalable Extension ID";
                break;
            case 10:
                result="Picture Temporal Scalable Extension ID";
                break;
            case 11:
                result="Camera Parameters Extension ID";
                break;
            case 12:
                result="ITU-T extension ID";
                break;
        }
        return result;
    }

    private String s_chroma_format(){
        String result="";

        switch (chroma_format){
            case 1:
                result="4:2:0";
                break;
            case 2:
                result="4:2:2";
                break;
            case 3:
                result="4:4:4";
                break;
        }

        return result;
    }

}
