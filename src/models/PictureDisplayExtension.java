package models;

/**
 * Created by josea on 19/03/2017.
 */
public class PictureDisplayExtension {

    public byte extension_start_code_identifier;
    public int number_of_frame_centre_offset;
    public short[] frame_centre_horizontal_offset = new short[3];
    public boolean[] marker_bit_1 = new boolean[3];
    public short[] frame_centre_vertical_offset = new short[3];
    public boolean[] marker_bit_2 = new boolean[3];

}
