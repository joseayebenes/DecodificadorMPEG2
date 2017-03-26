package models;

/**
 * Created by joseayebenes on 10/03/2017.
 */
public class SequenceHeader {
    public int sequence_header_code;
    public short horizontal_size_value;
    public short vertical_size_value;
    public byte aspect_ratio_information;
    public byte frame_rate_code;
    public int bit_rate_value;
    public boolean marker_bit;
    public short vbv_buffer_size_value;
    public boolean constrained_parameters_flag;
    public boolean load_intra_quantiser_matrix;
    public byte[] intra_quantiser_matrix;
    public boolean load_non_intra_quantiser_matrix;
    public byte[] non_intra_quantiser_matrix;


    public String toString(){
        String s="";

        s+="sequence_header_code = "+sequence_header_code+"\n";
        s+="horizontal_size_value = "+horizontal_size_value+"\n";
        s+="vertical_size_value = "+vertical_size_value+"\n";
        s+="aspect_ratio_information = "+aspect_ratio_information+" : "+frame_rate_value()+"\n";
        s+="frame_rate_code = "+frame_rate_code+"\n";
        s+="bit_rate_value = "+bit_rate_value+"\n";
        s+="marker_bit = "+marker_bit+"\n";
        s+="vbv_buffer_size_value = "+vbv_buffer_size_value+"\n";
        s+="constrained_parameters_flag = "+constrained_parameters_flag+"\n";
        s+="load_intra_quantiser_matrix = "+load_intra_quantiser_matrix+"\n";
        s+="intra_quantiser_matrix = "+"\n";
        if(load_intra_quantiser_matrix){
            int k=0;
            for(int i=0; i<8;i++){
                for(int j=0; j<8;j++){
                    s+=intra_quantiser_matrix[k]+" ";
                    k++;
                }
                s+="\n";
            }
        }
        s+="load_non_intra_quantiser_matrix = "+load_non_intra_quantiser_matrix+"\n";
        s+="non_intra_quantiser_matrix = "+"\n";
        if(load_non_intra_quantiser_matrix){
            int k=0;
            for(int i=0; i<8;i++){
                for(int j=0; j<8;j++){
                    s+=non_intra_quantiser_matrix[k]+" ";
                    k++;
                }
                s+="\n";
            }
        }


        return s;

    }

    private String frame_rate_value(){
        String result="";

        switch (frame_rate_code){
            case 1:
                result="29.97";
                break;
            case 2:
                result="24";
                break;
            case 3:
                result="25";
                break;
            case 4:
                result="29.97";
                break;
            case 5:
                result="30";
                break;
            case 6:
                result="50";
                break;
            case 7:
                result="59.94";
                break;
            case 8:
                result="60";
                break;
        }

        return result;
    }

}
