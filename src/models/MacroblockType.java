package models;

/**
 * Created by josea on 18/03/2017.
 */
public class MacroblockType {
    public boolean macroblock_quant;
    public boolean macroblock_motion_forward;
    public boolean macroblock_motion_backward;
    public boolean macroblock_pattern;
    public boolean macroblock_intra;
    public boolean spatial_temporal_weight_code_flag;
    public int permitted_spatial_temporal_weight_classes;

    public MacroblockType( boolean macroblock_quant,boolean macroblock_motion_forward,boolean macroblock_motion_backward,
                           boolean macroblock_pattern,boolean macroblock_intra,boolean spatial_temporal_weight_code_flag, int permitted_spatial_temporal_weight_classes){
        this.macroblock_quant = macroblock_quant;
        this.macroblock_motion_forward = macroblock_motion_forward;
        this.macroblock_motion_backward = macroblock_motion_backward;
        this.macroblock_pattern = macroblock_pattern;
        this.macroblock_intra = macroblock_intra;
        this.spatial_temporal_weight_code_flag = spatial_temporal_weight_code_flag;
        this.permitted_spatial_temporal_weight_classes = permitted_spatial_temporal_weight_classes;
    }
    public MacroblockType(){

    }

}
