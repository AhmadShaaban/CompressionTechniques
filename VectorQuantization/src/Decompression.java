import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Vector;

public class Decompression {
    HashMap<Integer,int[]> map =new HashMap<Integer, int[]>();
    Scanner inn = new Scanner(System.in);
    public  Vector<int[]> v;
    public Scanner input1 ,input2;
    Decompression(String x3){
        int n,m;
        String x = new String();
        //String temppp = inn.nextLine();
        x = JOptionPane.showInputDialog("enter code table path");
        String x2 = new String();
        x2 =  JOptionPane.showInputDialog("enter compressed file");


        //System.out.println(x + " " + x2);
        try{
            input1 = new Scanner(new File(x));
        }
        catch (Exception e){

        }


        try{
            input2 = new Scanner(new File(x2));
        }
        catch (Exception e){

        }

        //vector dimentsion
        n = input2.nextInt();
        m = input2.nextInt();

        v = new Vector<int[]>();
        int K = input2.nextInt();
        //System.out.println(n);
        //System.out.println(m);
        //System.out.println(K);




        int [] temp = new int[n*m];

        int code ;

        for (int i = 0 ; i < K ; ++i){
            for (int j = 0 ; j < n*m; ++j){
                temp[j] = input1.nextInt();
                //System.out.println("j " + temp[j]);
            }
            code = input1.nextInt();


            map.put(code,temp);
            temp = new int[n*m];
        }






        int LENGHT = 0 ; int WIDTH = 0;
        LENGHT = input2.nextInt();
        WIDTH = input2.nextInt();

        int[] Arr ;
        int sz = input2.nextInt();
        Arr = new int[sz];
        for (int i = 0 ; i < sz; ++i){
            Arr[i] = input2.nextInt();
        }

        for (int i = 0 ; i < Arr.length;++i){

            int[] tempo = map.get(Arr[i]);

            v.add(tempo);
        }



        int [][]ImagMatrix = new int[LENGHT][WIDTH];
        int idx1 = 0;
        for (int i = 0; i <LENGHT; i += n) {

            for (int j = 0; j < WIDTH; j += m) {

                int[] arr = v.elementAt(idx1++);
                int idx2 = 0;



                for (int k = 0, Z = i; k < n; ++k, ++Z) {
                    for (int l = 0, zz = j; l < m; ++l, ++zz) {
                        //System.out.println(idx2);
                        //System.out.println("SIZE OF ARRAY " + arr.length);
                        ImagMatrix[Z][zz] = arr[idx2++];
                    }
                }



            }

        }




        ReadWriteImage c = new ReadWriteImage();
        c.writeImage(ImagMatrix,x3);













    }

}
