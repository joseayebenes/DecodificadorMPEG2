package models;

import java.util.ArrayList;

/**
 * Created by josea on 18/03/2017.
 */
public class Block {

    public int dct_dc_size_luminance;
    public int dct_dc_differential;
    public int dct_dc_size_chrominance;

    public ArrayList<RunLevel> dctCoefficents= new ArrayList<RunLevel>();
}
