package models;

import java.util.ArrayList;

/**
 * Created by joseayebenes on 15/03/2017.
 */
public class Slice {

    public int slice_start_code;
    public byte slice_vertical_position_extension;
    public byte priority_breakpoint;
    public byte quantiser_scale_code;

    public boolean slice_extension_flag;
    public boolean intra_slice;
    public boolean slice_picture_id_enable;
    public byte slice_picture_id;
    public boolean extra_bit_slice;

    public ArrayList<Macroblock> macroblocks;

    public Slice(){
        macroblocks = new ArrayList<Macroblock>();
    }


    @Override
    public String toString() {
        String s="";
        s+="slice_start_code= "+slice_start_code+"\n";
        s+="slice_vertical_position_extension= "+slice_vertical_position_extension+"\n";
        s+="priority_breakpoint= "+priority_breakpoint+"\n";
        s+="quantiser_scale_code= "+quantiser_scale_code+"\n";
        s+="slice_extension_flag= "+slice_extension_flag+"\n";
        s+="intra_slice= "+intra_slice+"\n";
        s+="slice_picture_id_enable= "+slice_picture_id_enable+"\n";
        s+="slice_picture_id= "+slice_picture_id+"\n";
        s+="extra_bit_slice= "+extra_bit_slice+"\n";

        for(Macroblock macro : macroblocks){
            s+="-------MACRO--------";
            s+=macro;
        }

        return s;
    }
}
