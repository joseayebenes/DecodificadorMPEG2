import models.PictureDisplayExtension;
import models.Sequence;

import java.io.*;
import java.util.ArrayList;

public class Main {

    private static int picture_start_code = 0x100;
    private int user_data_start_code = 0x1b2;
    private static int sequence_header_code = 0x1b3;
    private int sequence_error_code = 0x1b4;
    private int extension_start_code = 0x1B5;
    private int sequence_end_code = 0x1b7;
    private int group_start_code = 0x1b8;
    private static int slice_start_code = 0x101;

    static ArrayList<Sequence> sequences;
    public static void main(String[] args) {
        sequences = new ArrayList<Sequence>();
        procesar("../ch63.mpg");

    }

    public static void procesar(String path){
        ThreadGroup tg1 = new ThreadGroup ("A");
        File file = new File(path);
        byte[] fileData = new byte[(int) file.length()];
        DataInputStream dis = null;
        Thread t;
        try {
            dis = new DataInputStream(new FileInputStream(file));
            dis.readFully(fileData);
            ArrayList<Integer> indices = buscarCabecera(fileData, (byte) sequence_header_code);

            ArrayList<Integer> indicesImagenes =  buscarCabecera(fileData, (byte) picture_start_code);
            ArrayList<Integer> indicesSliceUno =  buscarCabecera(fileData, (byte) 0x101);
            ArrayList<Integer> indicesSliceDos =  buscarCabecera(fileData, (byte) 0x102);
            byte[] data = splitArray(fileData,indicesSliceUno.get(0),indicesSliceDos.get(0));
            for(int i=0;i<data.length;i++){
                System.out.print(String.format("%02X ", data[i]));
            }
            System.out.println("");

            data =splitArray(fileData,indicesSliceDos.get(0),indicesSliceDos.get(0)+200);
            for(int i=0;i<data.length;i++){
                System.out.print(String.format("%02X ", data[i]));

            }
            System.out.println("");

            for (int i=0; i<1;i++) {
                sequences.add(new Sequence());
            }
            int j=0;
            long timeInit = System.currentTimeMillis();
            for (int i=0; i<1;i++) {
                t = new Thread(tg1,new ProcesarSecuenciaThread(splitArray(fileData,indices.get(i),indices.get(i+1)),sequences.get(j)));
                t.run();
                j++;
            }
            while(tg1.activeCount()>0){}

            System.out.println("Ejecutado en "+(System.currentTimeMillis()-timeInit)+" ms");
            System.out.println("Encontrados "+indices.size()+" sequencias");
            System.out.println("Encontrados "+indicesImagenes.size()+" imagenes");
            System.out.println("Tamaño Archivo "+file.length()+" bytes");
            System.out.println("Tamaño sin comprimir "+(indicesImagenes.size()*720*576*3)+" bytes");
            System.out.println("Tasa de Compresión "+(float)(file.length() *100 )/(float)(indicesImagenes.size()*720*576*1.5)+ " %");
            System.out.println("Duración: "+(float)indicesImagenes.size()/25.0+" s");

            for (int i = 0; i <1; i++) {
                System.out.println(sequences.get(i));
            }

            dis.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Integer> buscarCabecera(byte[] data, byte extension){
        ArrayList<Integer> indices = new ArrayList<Integer>();

        for(int i=0; i<data.length; i++){
            if(data[i]==(byte)0 && data[i+1]==(byte)0 && data[i+2]==(byte)1 && data[i+3]==(byte)extension){
                indices.add(i);
            }
        }

        return indices;

    }

    public static byte[] splitArray(byte[] data, int begin, int end){
        byte [] bytes=new byte[end-begin];
        for(int i=0; i<(end-begin);i++){
            bytes[i]=data[begin+i];
        }
        return bytes;
    }



}
