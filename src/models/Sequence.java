package models;

import java.util.ArrayList;

/**
 * Created by joseayebenes on 11/03/2017.
 */
public class Sequence {
    public SequenceHeader sqh;
    public SequenceExtension sqe;
    public ArrayList<GroupOfPicturesHeader> gops;
    public ArrayList<Picture> picts;

    public Sequence(){
        sqh = new SequenceHeader();
        sqe = new SequenceExtension();
        gops = new ArrayList<GroupOfPicturesHeader>();
        picts = new ArrayList<Picture>();
    }

    public String toString(){
        String s = "";
        s +="***Sequence Header***\n"+sqh.toString();
        s +="***Sequence Extension***\n"+sqe.toString();
        s +="***Gops***\n";
        for(GroupOfPicturesHeader gop : gops){
            s+= "--------------------------------\n";
            s+=gop.toString();
        }
        s +="***PICS***\n";
        for(Picture pic : picts){
            s+= "*******************************\n";
            s+=pic.toString();
        }
        return   s;
    }


}
