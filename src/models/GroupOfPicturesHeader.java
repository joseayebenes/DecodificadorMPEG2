package models;

/**
 * Created by joseayebenes on 12/03/2017.
 */
public class GroupOfPicturesHeader {
    public int group_start_code;
    public int time_code;
    public boolean closed_gop;
    public boolean broken_link;

    @Override
    public String toString() {
        String s="";

        s+= "group_start_code= "+group_start_code+"\n";
        s+= "time_code= "+group_start_code+"\n";
        s+= "closed_gop= "+group_start_code+"\n";
        s+= "broken_link= "+group_start_code+"\n";
        return s;
    }
}
