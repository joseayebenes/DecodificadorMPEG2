package models;

import java.util.ArrayList;

/**
 * Created by joseayebenes on 12/03/2017.
 */
public class Picture {

    public PictureHeader pich;
    public PictureCodingExtension pice;
    public ArrayList<Slice> slices;

    public Picture(){
        pich =  new PictureHeader();
        pice = new PictureCodingExtension();
        slices = new ArrayList<Slice>();
    }

    @Override
    public String toString() {
        String s="";
        s+="***Picture header***\n";
        s+=pich;
        s+="***Picture extension***\n";
        s+=pice;
        for(Slice slice:slices){
            s+="///////SLICE//////////\n";
            s+=slice;
        }
        return s;
    }
}
