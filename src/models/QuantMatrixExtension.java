package models;

/**
 * Created by josea on 18/03/2017.
 */
public class QuantMatrixExtension {
    public byte extension_start_code_identifier;
    public boolean load_intra_quantiser_matrix;
    public byte[] intra_quantiser_matrix = new byte[64];
    public boolean load_non_intra_quantiser_matrix;
    public byte[] non_intra_quantiser_matrix = new byte[64];
    public boolean load_chroma_intra_quantiser_matrix;
    public byte[] chroma_intra_quantiser_matrix = new byte[64];
    public boolean load_chroma_non_intra_quantiser_matrix;
    public byte[] chroma_non_intra_quantiser_matrix = new byte[64];
}
