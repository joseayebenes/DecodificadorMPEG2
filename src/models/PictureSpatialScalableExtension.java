package models;

/**
 * Created by josea on 19/03/2017.
 */
public class PictureSpatialScalableExtension {

    public byte extension_start_code_identifier;
    public short lower_layer_temporal_reference;
    public short lower_layer_horizontal_offset;
    public short lower_layer_vertical_offset;
    public byte spatial_temporal_weight_code_table_index;
    public boolean lower_layer_progressive_frame;
    public boolean lower_layer_deinterlaced_field_select;
}
