
import models.RunLevel;

import java.util.HashMap;


public  class LectorBits {

    private byte[] data;
    private int offset;

    private int byteActual = 0;

    public LectorBits(byte[] data, int offset){
        this.data=data;
        this.offset=offset;
    }
    public LectorBits(byte[] data){
        this.data=data;
        this.offset=0;
    }

    public int getNextBits(int length){
        int mask = 0x000000FF;
        int byteOffset = offset/8;

        int bitoffset =offset%8;

        int bytefin = (byteOffset+(length+bitoffset)/8);
        if((length+bitoffset)%8 !=0){
            bytefin++;
        }

        int aux=0;
        int result = 0;

        for(int i=byteOffset;i< bytefin;i++){
            aux  = aux<<8 | mask & data[i];
        }
        offset+=length;
        byteActual = aux;
        // Hacer mascara
        int m = 0;
        for(int i=0;i<length;i++){
            m = (m<<1)+1;
        }
        for(int i=0;i<((bytefin-byteOffset)*8-length-bitoffset);i++){
            m = (m<<1);
        }
        result = (aux&m)>>>((bytefin-byteOffset)*8-length-bitoffset);
        /*System.out.println(byteOffset);
        System.out.println(bitoffset);
        System.out.println(bytefin);
        System.out.println(hex(aux));
        System.out.println(hex(m));
        System.out.println(hex(aux&m));*/
        //System.out.println("- "+length+" : "+hex(result));
        return result;
    }

    public static String hex(byte n)
    {
        return String.format("0x%X", n);
    }

    public static String hex(int n) {
        return String.format("0x%8s", Integer.toHexString(n)).replace(' ', '0');
    }


    public void next_start_code(){
        while (!bytealigned()){
        }
        while (showNextBits(24)!= 0x000001){
            getNextBits(8);
        }
    }

    public boolean bytealigned(){
        boolean result = offset%8==0;
        if(!result){
            offset++;
        }
        return result;
    }

    public int showNextBits(int length){
        int mask = 0x000000FF;
        int byteOffset = offset/8;
        int bitoffset =offset%8;

        int bytefin = (byteOffset+(length+bitoffset)/8);
        if((length+bitoffset)%8 !=0){
            bytefin++;
        }

        int aux=0;
        int result = 0;

        for(int i=byteOffset;i< bytefin;i++){
            aux  = aux<<8 | mask & data[i];
        }


        // Hacer mascara
        int m = 0;
        for(int i=0;i<length;i++){
            m = (m<<1)+1;
        }
        for(int i=0;i<((bytefin-byteOffset)*8-length-bitoffset);i++){
            m = (m<<1);
        }
        result = (aux&m)>>>((bytefin-byteOffset)*8-length-bitoffset);
        /*System.out.println(byteOffset);
        System.out.println(bitoffset);
        System.out.println(bytefin);
        System.out.println(hex(aux));
        System.out.println(hex(m));
        System.out.println(hex(aux&m));
        System.out.println(hex(result));*/
        return result;
    }

    public int getVLC(HashMap<String,Integer> coleccion, int max){
        int r=-99;
        String s;
        for(int i=1; i<=max; i++){
            s=showNextBitsAsString(i);
            if(coleccion.containsKey(s)){
                r=coleccion.get(s);
                getNextBits(i);
                break;
            }
        }
        if(r==-99){
            System.out.println("Error en encontrar VLC");
        }

        return r;
    }

    public String getVLCKey(HashMap coleccion, int max){
        int r=-1;
        String s="";
        for(int i=1; i<=max; i++){
            s=showNextBitsAsString(i);
            if(coleccion.containsKey(s)){

                getNextBits(i);
                break;
            }
        }
        if(s.equals("")){
            System.out.println("Error en encontrar VLC key");
        }
        return s;
    }

    public RunLevel getDCTCoef(HashMap<String,RunLevel> coleccion, boolean first) {
        RunLevel r = new RunLevel(0,0);
        String s;
        int j=2;
        if(first){
            j=1;
        }
        for (int i=j; i <= 24; i++) {
            s = showNextBitsAsString(i);
            if (coleccion.containsKey(s)) {
                r = new RunLevel(coleccion.get(s).run,coleccion.get(s).level);
                getNextBits(i);
                break;
            }
        }

        if(r.run>=0){
            if(getNextBits(1)==1){
                r.level =-r.level;
            }
        }

        if(r.run==-2){//SCAPE CODING
            r.run=getNextBits(6);
            r.level=getNextBits(12);
        }

        return  r;
    }


    public String showNextBitsAsString(int length){
        String result = "";

        int nextbits=showNextBits(length);

        for (int i = 0; i < length; i++) {

            if(((nextbits>>i)&0x1 )==1){
                result="1"+result;
            }else{
                result="0"+result;
            }
        }

        return result;
    }

    public void searchNextSequence(){
        offset=32;
        while(showNextBits(32)!=0x000001B3) {
            offset+=32;
        }
    }

}

    
