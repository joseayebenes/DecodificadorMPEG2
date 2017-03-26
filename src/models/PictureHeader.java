package models;

import java.util.ArrayList;

/**
 * Created by joseayebenes on 12/03/2017.
 */
public class PictureHeader {
    public int picture_start_code;
    public short temporal_reference;
    public byte picture_coding_type;
    public short vbv_delay;
    public boolean full_pel_forward_vector;
    public byte forward_f_code;
    public boolean full_pel_backward_vector;
    public byte backward_f_code;
    public ArrayList<Boolean> extra_bit_picture;
    public ArrayList<Byte> extra_information_picture;

    public PictureHeader(){
        extra_bit_picture=new ArrayList<Boolean>();
        extra_information_picture= new ArrayList<Byte>();
    }

    @Override
    public String toString() {
        String s="";
        s+="picture_start_code= "+picture_start_code+"\n";
        s+="temporal_reference= "+temporal_reference+"\n";
        s+="picture_coding_type= "+picture_coding_type+"\n";
        s+="vbv_delay= "+vbv_delay+"\n";
        s+="full_pel_forward_vector= "+full_pel_forward_vector+"\n";
        s+="forward_f_code= "+forward_f_code+"\n";
        s+="full_pel_backward_vector= "+full_pel_backward_vector+"\n";
        s+="backward_f_code= "+backward_f_code+"\n";
        return s;
    }
}
