package models;

import java.util.HashMap;

/**
 * Created by joseayebenes on 15/03/2017.
 */
public class TablasVLC {

    public HashMap<String, Integer> macrobloc_address_increment;
    public HashMap<String, MacroblockType> macrobloc_type_I ;
    public HashMap<String, MacroblockType> macrobloc_type_P;
    public HashMap<String, MacroblockType> macrobloc_type_B;
    public HashMap<String, MacroblockType> macrobloc_type_I_ES ;
    public HashMap<String, MacroblockType> macrobloc_type_P_ES ;
    public HashMap<String, MacroblockType> macrobloc_type_B_ES ;
    public HashMap<String, MacroblockType> macrobloc_type_IPB_ES ;
    public HashMap<String, Integer> motion_code;
    public HashMap<String, Integer> dmvector;
    public HashMap<String,Integer> dct_dc_size_luminance;
    public HashMap<String, Integer> dct_dc_size_chrominance;
    public HashMap<String, RunLevel> dct_coeff_zero;
    public HashMap<String, RunLevel> dct_coeff_one;
    public HashMap<String, Integer> coded_block_pattern;


    public TablasVLC(){
        set_macrobloc_address_increment();
        set_macrobloc_type_B();
        set_macrobloc_type_I();
        set_macrobloc_type_P();
        set_macrobloc_type_B_ES();
        set_macrobloc_type_I_ES();
        set_macrobloc_type_P_ES();
        set_macrobloc_type_IPB_ES();
        set_motion_code();
        set_dmvector();
        set_dct_dc_size_luminance();
        set_dct_dc_size_chrominance();
        set_dct_coeff_zero();
        set_coded_block_pattern();
        set_dct_coeff_one();
    }

    private void set_macrobloc_address_increment(){
        macrobloc_address_increment = new HashMap<String, Integer>();
        macrobloc_address_increment.put("1",1);
        macrobloc_address_increment.put("011",2);
        macrobloc_address_increment.put("010",3);
        macrobloc_address_increment.put("0011",4);
        macrobloc_address_increment.put("0010",5);
        macrobloc_address_increment.put("00011",6);
        macrobloc_address_increment.put("00010",7);
        macrobloc_address_increment.put("0000111",8);
        macrobloc_address_increment.put("0000110",9);
        macrobloc_address_increment.put("00001011",10);
        macrobloc_address_increment.put("00001010",11);
        macrobloc_address_increment.put("00001001",12);
        macrobloc_address_increment.put("00001000",13);
        macrobloc_address_increment.put("00000111",14);
        macrobloc_address_increment.put("00000110",15);
        macrobloc_address_increment.put("0000010111",16);
        macrobloc_address_increment.put("0000010110",17);
        macrobloc_address_increment.put("0000010101",18);
        macrobloc_address_increment.put("0000010100",19);
        macrobloc_address_increment.put("0000010011",21);
        macrobloc_address_increment.put("0000010010",21);
        macrobloc_address_increment.put("00000100011",22);
        macrobloc_address_increment.put("00000100010",23);
        macrobloc_address_increment.put("00000100001",24);
        macrobloc_address_increment.put("00000100000",25);
        macrobloc_address_increment.put("00000011111",26);
        macrobloc_address_increment.put("00000011110",27);
        macrobloc_address_increment.put("00000011101",28);
        macrobloc_address_increment.put("00000011100",29);
        macrobloc_address_increment.put("00000011011",30);
        macrobloc_address_increment.put("00000011010",31);
        macrobloc_address_increment.put("00000011010",32);
        macrobloc_address_increment.put("00000011000",33);
        macrobloc_address_increment.put("00000001000",0);
    }

    private void set_macrobloc_type_I(){
        macrobloc_type_I = new HashMap<String,MacroblockType>();

        macrobloc_type_I.put("1",new MacroblockType(false,false,false,false,true,false,0) );
        macrobloc_type_I.put("01",new MacroblockType(true,false,false,false,true,false,0) );
    }
    private void set_macrobloc_type_P(){
        macrobloc_type_P = new HashMap<String,MacroblockType>();
        macrobloc_type_P.put("1",       new MacroblockType(false,true,false,true,false,false,0) );
        macrobloc_type_P.put("01",      new MacroblockType(false,false,false,true,false,false,0) );
        macrobloc_type_P.put("001",     new MacroblockType(false,true,false,false,false,false,0) );
        macrobloc_type_P.put("00011",   new MacroblockType(false,false,false,false,true,false,0) );
        macrobloc_type_P.put("00010",   new MacroblockType(true,true,false,true,false,false,0) );
        macrobloc_type_P.put("00001",   new MacroblockType(true,false,false,true,false,false,0) );
        macrobloc_type_P.put("000001",  new MacroblockType(true,false,false,false,true,false,0) );

    }
    private void set_macrobloc_type_B(){
        macrobloc_type_B = new HashMap<String,MacroblockType>();
        macrobloc_type_B.put("10",      new MacroblockType(false,true,true,false,false,false,0) );
        macrobloc_type_B.put("11",      new MacroblockType(false,true,true,true,false,false,0) );
        macrobloc_type_B.put("010",     new MacroblockType(false,false,true,false,false,false,0) );
        macrobloc_type_B.put("011",     new MacroblockType(false,false,true,true,false,false,0) );
        macrobloc_type_B.put("0010",    new MacroblockType(false,true,false,false,false,false,0) );
        macrobloc_type_B.put("0011",    new MacroblockType(false,true,false,true,false,false,0) );
        macrobloc_type_B.put("00011",   new MacroblockType(false,false,false,false,true,false,0) );
        macrobloc_type_B.put("00010",   new MacroblockType(true,true,true,true,false,false,0) );
        macrobloc_type_B.put("000011",  new MacroblockType(true,true,false,true,false,false,0) );
        macrobloc_type_B.put("000010",  new MacroblockType(true,false,true,true,false,false,0) );
        macrobloc_type_B.put("000001",  new MacroblockType(true,false,false,false,true,false,0) );

    }

    private void set_macrobloc_type_I_ES(){
        macrobloc_type_I_ES = new HashMap<String,MacroblockType>();

        macrobloc_type_I_ES.put("1",   new MacroblockType(false,false,false,true,false,false,0) );
        macrobloc_type_I_ES.put("01",  new MacroblockType(true,false,false,true,false,false,0) );
        macrobloc_type_I_ES.put("0011",new MacroblockType(false,false,false,false,true,false,0) );
        macrobloc_type_I_ES.put("0010",new MacroblockType(true,false,false,false,true,false,0) );
        macrobloc_type_I_ES.put("0001",new MacroblockType(false,false,false,false,false,false,0) );
    }
    private void set_macrobloc_type_P_ES(){
        macrobloc_type_P_ES = new HashMap<String,MacroblockType>();

        macrobloc_type_P_ES.put("10",      new MacroblockType(false,true,false,true,false,false,0) );
        macrobloc_type_P_ES.put("011",     new MacroblockType(false,true,false,true,false,true,0) );
        macrobloc_type_P_ES.put("0000100", new MacroblockType(false,false,false,true,false,false,0) );
        macrobloc_type_P_ES.put("00111",   new MacroblockType(false,false,false,true,false,true,0) );
        macrobloc_type_P_ES.put("0010",    new MacroblockType(false,true,false,false,false,false,0) );
        macrobloc_type_P_ES.put("0000111", new MacroblockType(false,false,false,false,true,false,0) );
        macrobloc_type_P_ES.put("0011",    new MacroblockType(false,true,false,false,false,true,0) );
        macrobloc_type_P_ES.put("010",     new MacroblockType(true,true,false,true,false,false,0) );
        macrobloc_type_P_ES.put("000100",  new MacroblockType(true,false,false,true,false,false,0) );
        macrobloc_type_P_ES.put("0000110", new MacroblockType(true,false,false,false,true,false,0) );
        macrobloc_type_P_ES.put("11",      new MacroblockType(true,true,false,true,false,true,0) );
        macrobloc_type_P_ES.put("000101",  new MacroblockType(true,false,false,true,false,true,0) );
        macrobloc_type_P_ES.put("000110",  new MacroblockType(false,false,false,false,false,true,0) );
        macrobloc_type_P_ES.put("0000101", new MacroblockType(false,false,false,true,false,false,0) );
        macrobloc_type_P_ES.put("0000010", new MacroblockType(true,false,false,true,false,false,0) );
        macrobloc_type_P_ES.put("0000011", new MacroblockType(false,false,false,false,false,false,0) );
    }
    private void set_macrobloc_type_B_ES(){
        macrobloc_type_B_ES = new HashMap<String,MacroblockType>();

        macrobloc_type_B_ES.put("10",        new MacroblockType(false,true,true,false,false,false,0) );
        macrobloc_type_B_ES.put("11",        new MacroblockType(false,true,false,true,false,false,0) );
        macrobloc_type_B_ES.put("010",       new MacroblockType(false,false,true,false,false,false,0) );
        macrobloc_type_B_ES.put("011",       new MacroblockType(false,false,true,true,false,false,0) );
        macrobloc_type_B_ES.put("0010",      new MacroblockType(false,true,false,false,false,false,0) );
        macrobloc_type_B_ES.put("00110",     new MacroblockType(false,true,false,true,false,false,0) );
        macrobloc_type_B_ES.put("000111",    new MacroblockType(false,false,true,false,false,true,0) );
        macrobloc_type_B_ES.put("000100",    new MacroblockType(false,true,false,false,false,true,0) );
        macrobloc_type_B_ES.put("000101",    new MacroblockType(false,true,false,true,false,true,0) );
        macrobloc_type_B_ES.put("0000110",   new MacroblockType(false,false,false,false,true,false,0) );
        macrobloc_type_B_ES.put("0000111",   new MacroblockType(true,true,true,true,false,false,0) );
        macrobloc_type_B_ES.put("0000100",   new MacroblockType(true,true,false,true,false,false,0) );
        macrobloc_type_B_ES.put("0000101",   new MacroblockType(true,false,true,true,false,false,0) );
        macrobloc_type_B_ES.put("00000100",  new MacroblockType(true,false,false,false,true,false,0) );
        macrobloc_type_B_ES.put("00000101",  new MacroblockType(true,true,false,true,false,true,0) );
        macrobloc_type_B_ES.put("000001100", new MacroblockType(true,false,true,true,false,true,0) );
        macrobloc_type_B_ES.put("000001110", new MacroblockType(false,false,false,false,false,false,0) );
        macrobloc_type_B_ES.put("000001101", new MacroblockType(true,false,false,true,false,false,0) );
        macrobloc_type_B_ES.put("000001111", new MacroblockType(false,false,false,true,false,false,0) );
       }

    private void set_macrobloc_type_IPB_ES(){
        macrobloc_type_IPB_ES = new HashMap<String,MacroblockType>();
        macrobloc_type_IPB_ES.put("1",new MacroblockType(false,false,false,true,false,false,0) );
        macrobloc_type_IPB_ES.put("01",new MacroblockType(true,false,false,true,false,false,0) );
        macrobloc_type_IPB_ES.put("001",new MacroblockType(false,false,false,false,false,false,0) );
    }

    private void set_motion_code(){
        motion_code = new HashMap<String,Integer>();

        motion_code.put("00000011001", -16);
        motion_code.put("00000011011", -15);
        motion_code.put("00000011101", -14);
        motion_code.put("00000011111", -13);
        motion_code.put("00000100001", -12);
        motion_code.put("00000100011", -11);
        motion_code.put("0000010011", -10);
        motion_code.put("0000010101", -9);
        motion_code.put("0000010111", -8);
        motion_code.put("00000111", -7);
        motion_code.put("00001001", -6);
        motion_code.put("00001011", -5);
        motion_code.put("0000111", -4);
        motion_code.put("00011", -3);
        motion_code.put("0011", -2);
        motion_code.put("011", -1);
        motion_code.put("1", 0);
        motion_code.put("010", 1);
        motion_code.put("0010", 2);
        motion_code.put("00010", 3);
        motion_code.put("0000110", 4);
        motion_code.put("00001010", 5);
        motion_code.put("00001000", 6);
        motion_code.put("00000110", 7);
        motion_code.put("0000010110", 8);
        motion_code.put("0000010100", 9);
        motion_code.put("0000010010", 10);
        motion_code.put("00000100010", 11);
        motion_code.put("00000100000", 12);
        motion_code.put("00000011110", 13);
        motion_code.put("00000011100", 14);
        motion_code.put("00000011010", 15);
        motion_code.put("00000011000", 16);


    }
    private void set_dmvector(){
        dmvector = new HashMap<String,Integer>();
        dmvector.put("11",-1);
        dmvector.put("0",0);
        dmvector.put("10",1);
    }

    private void set_dct_dc_size_luminance(){
        dct_dc_size_luminance = new HashMap<String,Integer>();

        dct_dc_size_luminance.put("100",0);
        dct_dc_size_luminance.put("00",1);
        dct_dc_size_luminance.put("01",2);
        dct_dc_size_luminance.put("101",3);
        dct_dc_size_luminance.put("110",4);
        dct_dc_size_luminance.put("1110",5);
        dct_dc_size_luminance.put("11110",6);
        dct_dc_size_luminance.put("111110",7);
        dct_dc_size_luminance.put("1111110",8);
        dct_dc_size_luminance.put("11111110",9);
        dct_dc_size_luminance.put("111111110",10);
        dct_dc_size_luminance.put("111111111",11);
    }

    private void set_dct_dc_size_chrominance(){
        dct_dc_size_chrominance = new HashMap<String,Integer>();

        dct_dc_size_chrominance.put("00",0);
        dct_dc_size_chrominance.put("01",1);
        dct_dc_size_chrominance.put("10",2);
        dct_dc_size_chrominance.put("110",3);
        dct_dc_size_chrominance.put("1110",4);
        dct_dc_size_chrominance.put("11110",5);
        dct_dc_size_chrominance.put("111110",6);
        dct_dc_size_chrominance.put("1111110",7);
        dct_dc_size_chrominance.put("11111110",8);
        dct_dc_size_chrominance.put("111111110",9);
        dct_dc_size_chrominance.put("1111111110",10);
        dct_dc_size_chrominance.put("1111111111",11);
    }

    private void set_dct_coeff_zero() {
        dct_coeff_zero = new HashMap<String, RunLevel>();

        dct_coeff_zero.put("10", new RunLevel(-1,0));
        dct_coeff_zero.put("1", new RunLevel(0,1));
        dct_coeff_zero.put("11", new RunLevel(0,1));
        dct_coeff_zero.put("0100", new RunLevel(0,2));
        dct_coeff_zero.put("0101", new RunLevel(2,1));
        dct_coeff_zero.put("00101", new RunLevel(0,3));
        dct_coeff_zero.put("00111", new RunLevel(3,1));
        dct_coeff_zero.put("00110", new RunLevel(4,1));
        dct_coeff_zero.put("000110", new RunLevel(1,2));
        dct_coeff_zero.put("000111", new RunLevel(5,1));
        dct_coeff_zero.put("000101", new RunLevel(6,1));
        dct_coeff_zero.put("000100", new RunLevel(7,1));
        dct_coeff_zero.put("0000110", new RunLevel(0,4));
        dct_coeff_zero.put("0000100", new RunLevel(2,2));
        dct_coeff_zero.put("0000111", new RunLevel(8,1));
        dct_coeff_zero.put("0000101", new RunLevel(9,1));
        dct_coeff_zero.put("000001", new RunLevel(-2,0));
        dct_coeff_zero.put("00100110", new RunLevel(0,5));
        dct_coeff_zero.put("00100001", new RunLevel(0,6));
        dct_coeff_zero.put("00100101", new RunLevel(1,3));
        dct_coeff_zero.put("00100100", new RunLevel(3,2));
        dct_coeff_zero.put("00100111", new RunLevel(10,1));
        dct_coeff_zero.put("00100011", new RunLevel(11,1));
        dct_coeff_zero.put("00100010", new RunLevel(12,1));
        dct_coeff_zero.put("00100000", new RunLevel(13,1));
        dct_coeff_zero.put("0000001010", new RunLevel(0,7));
        dct_coeff_zero.put("0000001100", new RunLevel(1,4));
        dct_coeff_zero.put("0000001011", new RunLevel(2,3));
        dct_coeff_zero.put("0000001111", new RunLevel(4,2));
        dct_coeff_zero.put("0000001001", new RunLevel(5,2));
        dct_coeff_zero.put("0000001110", new RunLevel(14,1));
        dct_coeff_zero.put("0000001101", new RunLevel(15,1));
        dct_coeff_zero.put("0000001000", new RunLevel(16,1));
        dct_coeff_zero.put("000000011101", new RunLevel(0,8));
        dct_coeff_zero.put("000000011000", new RunLevel(0,9));
        dct_coeff_zero.put("000000010011", new RunLevel(0,10));
        dct_coeff_zero.put("000000010000", new RunLevel(0,11));
        dct_coeff_zero.put("000000011011", new RunLevel(1,5));
        dct_coeff_zero.put("000000010100", new RunLevel(2,4));
        dct_coeff_zero.put("000000011100", new RunLevel(3,3));
        dct_coeff_zero.put("000000010010", new RunLevel(4,3));
        dct_coeff_zero.put("000000011110", new RunLevel(6,2));
        dct_coeff_zero.put("000000010101", new RunLevel(7,2));
        dct_coeff_zero.put("000000010001", new RunLevel(8,2));
        dct_coeff_zero.put("000000011111", new RunLevel(17,1));
        dct_coeff_zero.put("000000011010", new RunLevel(18,1));
        dct_coeff_zero.put("000000011001", new RunLevel(19,1));
        dct_coeff_zero.put("000000010111", new RunLevel(20,1));
        dct_coeff_zero.put("000000010110", new RunLevel(21,1));
        dct_coeff_zero.put("0000000011010", new RunLevel(0,12));
        dct_coeff_zero.put("0000000011001", new RunLevel(0,13));
        dct_coeff_zero.put("0000000011000", new RunLevel(0,14));
        dct_coeff_zero.put("0000000010111", new RunLevel(0,15));
        dct_coeff_zero.put("0000000010110", new RunLevel(1,6));
        dct_coeff_zero.put("0000000010101", new RunLevel(1,7));
        dct_coeff_zero.put("0000000010100", new RunLevel(2,5));
        dct_coeff_zero.put("0000000010011", new RunLevel(3,4));
        dct_coeff_zero.put("0000000010010", new RunLevel(5,3));
        dct_coeff_zero.put("0000000010001", new RunLevel(9,2));
        dct_coeff_zero.put("0000000010000", new RunLevel(10,2));
        dct_coeff_zero.put("0000000011111", new RunLevel(22,1));
        dct_coeff_zero.put("0000000011110", new RunLevel(23,1));
        dct_coeff_zero.put("0000000011101", new RunLevel(24,1));
        dct_coeff_zero.put("0000000011100", new RunLevel(25,1));
        dct_coeff_zero.put("0000000011011", new RunLevel(26,1));
        dct_coeff_zero.put("00000000011111", new RunLevel(0,16));
        dct_coeff_zero.put("00000000011110", new RunLevel(0,17));
        dct_coeff_zero.put("00000000011101", new RunLevel(0,18));
        dct_coeff_zero.put("00000000011100", new RunLevel(0,19));
        dct_coeff_zero.put("00000000011011", new RunLevel(0,20));
        dct_coeff_zero.put("00000000011010", new RunLevel(0,21));
        dct_coeff_zero.put("00000000011001", new RunLevel(0,22));
        dct_coeff_zero.put("00000000011000", new RunLevel(0,23));
        dct_coeff_zero.put("00000000010111", new RunLevel(0,24));
        dct_coeff_zero.put("00000000010110", new RunLevel(0,25));
        dct_coeff_zero.put("00000000010101", new RunLevel(0,26));
        dct_coeff_zero.put("00000000010100", new RunLevel(0,27));
        dct_coeff_zero.put("00000000010011", new RunLevel(0,28));
        dct_coeff_zero.put("00000000010010", new RunLevel(0,29));
        dct_coeff_zero.put("00000000010001", new RunLevel(0,30));
        dct_coeff_zero.put("00000000010000", new RunLevel(0,31));
        dct_coeff_zero.put("000000000011000", new RunLevel(0,32));
        dct_coeff_zero.put("000000000010111", new RunLevel(0,33));
        dct_coeff_zero.put("000000000010110", new RunLevel(0,34));
        dct_coeff_zero.put("000000000010101", new RunLevel(0,35));
        dct_coeff_zero.put("000000000010100", new RunLevel(0,36));
        dct_coeff_zero.put("000000000010011", new RunLevel(0,37));
        dct_coeff_zero.put("000000000010011", new RunLevel(0,38));
        dct_coeff_zero.put("000000000010001", new RunLevel(0,39));
        dct_coeff_zero.put("000000000010000", new RunLevel(0,40));
        dct_coeff_zero.put("000000000011111", new RunLevel(1,8));
        dct_coeff_zero.put("000000000011110", new RunLevel(1,9));
        dct_coeff_zero.put("000000000011101", new RunLevel(1,10));
        dct_coeff_zero.put("000000000011100", new RunLevel(1,11));
        dct_coeff_zero.put("000000000011011", new RunLevel(1,12));
        dct_coeff_zero.put("000000000011010", new RunLevel(1,13));
        dct_coeff_zero.put("000000000011001", new RunLevel(1,14));
        dct_coeff_zero.put("0000000000010011", new RunLevel(1,15));
        dct_coeff_zero.put("0000000000010010", new RunLevel(1,16));
        dct_coeff_zero.put("0000000000010001", new RunLevel(1,17));
        dct_coeff_zero.put("0000000000010000", new RunLevel(1,18));
        dct_coeff_zero.put("0000000000010100", new RunLevel(6,3));
        dct_coeff_zero.put("0000000000011010", new RunLevel(11,2));
        dct_coeff_zero.put("0000000000011001", new RunLevel(12,2));
        dct_coeff_zero.put("0000000000011000", new RunLevel(13,2));
        dct_coeff_zero.put("0000000000010111", new RunLevel(14,2));
        dct_coeff_zero.put("0000000000010110", new RunLevel(15,2));
        dct_coeff_zero.put("0000000000010101", new RunLevel(16,2));
        dct_coeff_zero.put("0000000000011111", new RunLevel(27,1));
        dct_coeff_zero.put("0000000000011110", new RunLevel(28,1));
        dct_coeff_zero.put("0000000000011101", new RunLevel(29,1));
        dct_coeff_zero.put("0000000000011100", new RunLevel(30,1));
        dct_coeff_zero.put("0000000000011011", new RunLevel(31,1));


    }

    private void set_coded_block_pattern(){
        coded_block_pattern = new HashMap<String,Integer>();

        coded_block_pattern.put("111",60);
        coded_block_pattern.put("1101",4);
        coded_block_pattern.put("1100",8);
        coded_block_pattern.put("1011",16);
        coded_block_pattern.put("1010",32);
        coded_block_pattern.put("10011",12);
        coded_block_pattern.put("10010",48);
        coded_block_pattern.put("10001",20);
        coded_block_pattern.put("10000",40);
        coded_block_pattern.put("01111",28);
        coded_block_pattern.put("01110",44);
        coded_block_pattern.put("01101",52);
        coded_block_pattern.put("01100",56);
        coded_block_pattern.put("01011",1);
        coded_block_pattern.put("01010",61);
        coded_block_pattern.put("01001",2);
        coded_block_pattern.put("01000",62);
        coded_block_pattern.put("001111",24);
        coded_block_pattern.put("001110",36);
        coded_block_pattern.put("001101",3);
        coded_block_pattern.put("001100",63);
        coded_block_pattern.put("0010111",5);
        coded_block_pattern.put("0010110",9);
        coded_block_pattern.put("0010101",17);
        coded_block_pattern.put("0010100",33);
        coded_block_pattern.put("0010011",6);
        coded_block_pattern.put("0010010",10);
        coded_block_pattern.put("0010001",18);
        coded_block_pattern.put("0010000",34);
        coded_block_pattern.put("00011111",7);
        coded_block_pattern.put("00011110",11);
        coded_block_pattern.put("00011101",19);
        coded_block_pattern.put("00011100",35);
        coded_block_pattern.put("00011011",13);
        coded_block_pattern.put("00011010",49);
        coded_block_pattern.put("00011001",21);
        coded_block_pattern.put("00011000",41);
        coded_block_pattern.put("00010111",14);
        coded_block_pattern.put("00010110",50);
        coded_block_pattern.put("00010101",22);
        coded_block_pattern.put("00010100",42);
        coded_block_pattern.put("00010011",15);
        coded_block_pattern.put("00010010",51);
        coded_block_pattern.put("00010001",23);
        coded_block_pattern.put("00010000",43);
        coded_block_pattern.put("00001111",25);
        coded_block_pattern.put("00001110",37);
        coded_block_pattern.put("00001101",26);
        coded_block_pattern.put("00001100",38);
        coded_block_pattern.put("00001011",29);
        coded_block_pattern.put("00001010",45);
        coded_block_pattern.put("00001001",53);
        coded_block_pattern.put("00001000",57);
        coded_block_pattern.put("00000111",30);
        coded_block_pattern.put("00000110",46);
        coded_block_pattern.put("00000101",54);
        coded_block_pattern.put("00000100",58);
        coded_block_pattern.put("000000111",31);
        coded_block_pattern.put("000000110",47);
        coded_block_pattern.put("000000101",55);
        coded_block_pattern.put("000000100",59);
        coded_block_pattern.put("000000011",27);
        coded_block_pattern.put("000000010",39);
        coded_block_pattern.put("000000001",0);

    }

    private void set_dct_coeff_one() {
        dct_coeff_one = new HashMap<String, RunLevel>();

        dct_coeff_one.put("0110", new RunLevel(-1,0));
        dct_coeff_one.put("10", new RunLevel(0,1));
        dct_coeff_one.put("010", new RunLevel(1,1));
        dct_coeff_one.put("110", new RunLevel(0,2));
        dct_coeff_one.put("00101", new RunLevel(2,1));
        dct_coeff_one.put("0111", new RunLevel(0,3));
        dct_coeff_one.put("00111", new RunLevel(3,1));
        dct_coeff_one.put("000110", new RunLevel(4,1));
        dct_coeff_one.put("00110", new RunLevel(1,2));
        dct_coeff_one.put("000111", new RunLevel(5,1));
        dct_coeff_one.put("0000110", new RunLevel(6,1));
        dct_coeff_one.put("0000100", new RunLevel(7,1));
        dct_coeff_one.put("11100", new RunLevel(0,4));
        dct_coeff_one.put("0000111", new RunLevel(2,2));
        dct_coeff_one.put("0000101", new RunLevel(8,1));
        dct_coeff_one.put("1111000", new RunLevel(9,1));
        dct_coeff_one.put("000001", new RunLevel(-2,0));
        dct_coeff_one.put("11101", new RunLevel(0,5));
        dct_coeff_one.put("000101", new RunLevel(0,6));
        dct_coeff_one.put("1111001", new RunLevel(1,3));
        dct_coeff_one.put("00100110", new RunLevel(3,2));
        dct_coeff_one.put("1111010", new RunLevel(10,1));
        dct_coeff_one.put("00100001", new RunLevel(11,1));
        dct_coeff_one.put("00100101", new RunLevel(12,1));
        dct_coeff_one.put("00100100", new RunLevel(13,1));
        dct_coeff_one.put("000100", new RunLevel(0,7));
        dct_coeff_one.put("00100111", new RunLevel(1,4));
        dct_coeff_one.put("11111100", new RunLevel(2,3));
        dct_coeff_one.put("11111101", new RunLevel(4,2));
        dct_coeff_one.put("000000100", new RunLevel(5,2));
        dct_coeff_one.put("000000101", new RunLevel(14,1));
        dct_coeff_one.put("000000111", new RunLevel(15,1));
        dct_coeff_one.put("0000001101", new RunLevel(16,1));
        dct_coeff_one.put("1111011", new RunLevel(0,8));
        dct_coeff_one.put("1111100", new RunLevel(0,9));
        dct_coeff_one.put("00100011", new RunLevel(0,10));
        dct_coeff_one.put("00100010", new RunLevel(0,11));
        dct_coeff_one.put("00100000", new RunLevel(1,5));
        dct_coeff_one.put("0000001100", new RunLevel(2,4));
        dct_coeff_one.put("000000011100", new RunLevel(3,3));
        dct_coeff_one.put("000000010010", new RunLevel(4,3));
        dct_coeff_one.put("000000011110", new RunLevel(6,2));
        dct_coeff_one.put("000000010101", new RunLevel(7,2));
        dct_coeff_one.put("000000010001", new RunLevel(8,2));
        dct_coeff_one.put("000000011111", new RunLevel(17,1));
        dct_coeff_one.put("000000011010", new RunLevel(18,1));
        dct_coeff_one.put("000000011001", new RunLevel(19,1));
        dct_coeff_one.put("000000011010", new RunLevel(20,1));
        dct_coeff_one.put("000000010110", new RunLevel(21,1));
        dct_coeff_one.put("11111010", new RunLevel(0,12));
        dct_coeff_one.put("11111011", new RunLevel(0,13));
        dct_coeff_one.put("11111110", new RunLevel(0,14));
        dct_coeff_one.put("11111111", new RunLevel(0,15));
        dct_coeff_one.put("0000000010110", new RunLevel(1,6));
        dct_coeff_one.put("0000000010101", new RunLevel(1,7));
        dct_coeff_one.put("0000000010100", new RunLevel(2,5));
        dct_coeff_one.put("0000000010011", new RunLevel(3,4));
        dct_coeff_one.put("0000000010010", new RunLevel(5,3));
        dct_coeff_one.put("0000000010001", new RunLevel(9,2));
        dct_coeff_one.put("0000000010000", new RunLevel(10,2)); //AQui
        dct_coeff_one.put("0000000011111", new RunLevel(22,1));
        dct_coeff_one.put("0000000011110", new RunLevel(23,1));
        dct_coeff_one.put("0000000011101", new RunLevel(24,1));
        dct_coeff_one.put("0000000011100", new RunLevel(25,1));
        dct_coeff_one.put("0000000011011", new RunLevel(26,1));
        dct_coeff_one.put("00000000011111", new RunLevel(0,16)); //a
        dct_coeff_one.put("00000000011110", new RunLevel(0,17));
        dct_coeff_one.put("00000000011101", new RunLevel(0,18));
        dct_coeff_one.put("00000000011100", new RunLevel(0,19));
        dct_coeff_one.put("00000000011011", new RunLevel(0,20));
        dct_coeff_one.put("00000000011010", new RunLevel(0,21));
        dct_coeff_one.put("00000000011001", new RunLevel(0,22));
        dct_coeff_one.put("00000000011000", new RunLevel(0,23));
        dct_coeff_one.put("00000000010111", new RunLevel(0,24));
        dct_coeff_one.put("00000000010110", new RunLevel(0,25));
        dct_coeff_one.put("00000000010101", new RunLevel(0,26));
        dct_coeff_one.put("00000000010100", new RunLevel(0,27));
        dct_coeff_one.put("00000000010011", new RunLevel(0,28));
        dct_coeff_one.put("00000000010010", new RunLevel(0,29));
        dct_coeff_one.put("00000000010001", new RunLevel(0,30));
        dct_coeff_one.put("00000000010000", new RunLevel(0,31));
        dct_coeff_one.put("000000000011000", new RunLevel(0,32));
        dct_coeff_one.put("000000000010111", new RunLevel(0,33));
        dct_coeff_one.put("000000000010110", new RunLevel(0,34));
        dct_coeff_one.put("000000000010101", new RunLevel(0,35));
        dct_coeff_one.put("000000000011100", new RunLevel(0,36));
        dct_coeff_one.put("000000000010011", new RunLevel(0,37));
        dct_coeff_one.put("000000000010010", new RunLevel(0,38));
        dct_coeff_one.put("000000000010110", new RunLevel(0,39)); //AQ
        dct_coeff_one.put("000000000010000", new RunLevel(0,40));
        dct_coeff_one.put("000000000011111", new RunLevel(1,8));
        dct_coeff_one.put("000000000011110", new RunLevel(1,9));
        dct_coeff_one.put("000000000011101", new RunLevel(1,10));
        dct_coeff_one.put("000000000011100", new RunLevel(1,11));
        dct_coeff_one.put("000000000011011", new RunLevel(1,12));
        dct_coeff_one.put("000000000011010", new RunLevel(1,13));
        dct_coeff_one.put("000000000011001", new RunLevel(1,14));
        dct_coeff_one.put("0000000000010011", new RunLevel(1,15));
        dct_coeff_one.put("0000000000010010", new RunLevel(1,16));
        dct_coeff_one.put("0000000000010001", new RunLevel(1,17));
        dct_coeff_one.put("0000000000010000", new RunLevel(1,18));
        dct_coeff_one.put("0000000000010100", new RunLevel(6,3));
        dct_coeff_one.put("0000000000011010", new RunLevel(11,2));
        dct_coeff_one.put("0000000000011001", new RunLevel(12,2));
        dct_coeff_one.put("0000000000011000", new RunLevel(13,2));
        dct_coeff_one.put("0000000000010111", new RunLevel(14,2));
        dct_coeff_one.put("0000000000010110", new RunLevel(15,2));
        dct_coeff_one.put("0000000000010101", new RunLevel(16,2));
        dct_coeff_one.put("0000000000011111", new RunLevel(27,1));
        dct_coeff_one.put("0000000000011110", new RunLevel(28,1));
        dct_coeff_one.put("0000000000011101", new RunLevel(29,1));
        dct_coeff_one.put("0000000000011100", new RunLevel(30,1));
        dct_coeff_one.put("0000000000011011", new RunLevel(31,1));
    }


}
