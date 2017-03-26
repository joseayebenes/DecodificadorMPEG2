package models;

import java.util.ArrayList;

/**
 * Created by joseayebenes on 15/03/2017.
 */
public class Macroblock {

    public byte macroblock_address_increment;
    public byte quantiser_scale_code;
    public boolean marker_bit;
    public ArrayList<Block> blocks =new ArrayList<Block>();

    // Macroblock_modes()
    public MacroblockType macroblock_type;
    public byte spatial_temporal_weight_code;
    public byte frame_motion_type;
    public byte field_motion_type;
    public boolean dct_type;

    // motion_vectors ( s )
    public boolean[][] motion_vertical_field_select = new boolean[2][2];

    //motion_vector ( r, s )
    public short[][][] motion_code = new short[2][2][2];
    public byte[][][] motion_residual = new byte[2][2][2];
    public byte[] dmvector = new byte[2];

    //coded_block_pattern()
    public short coded_block_pattern_420;
    public byte coded_block_pattern_1;
    public byte coded_block_pattern_2;

    public boolean[] pattern_code = new boolean[12];

    public Macroblock(){
        macroblock_type = new MacroblockType();
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
