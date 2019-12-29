import javax.swing.*;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Vector;

public class Compression {

    // vector of arrays to store K vectors
    public static Vector<int[]> theone;
    public  static  int K ;
    static Vector<int[]> v;
    public static int n, m;
    Formatter output1;
    Formatter output2;

    public static int log2(int n){
        if(n <= 0) throw new IllegalArgumentException();
        return 31 - Integer.numberOfLeadingZeros(n);
    }

    Compression(String imagePath){
        theone = new Vector<int[]>();

        Scanner in = new Scanner(System.in);
        //String x;
        ReadWriteImage c = new ReadWriteImage();

        int[][] imagMatrix = c.readImage(imagePath);






        //vector dimensions

        n = Integer.parseInt(JOptionPane.showInputDialog("please enter lenght of vector"));
        m = Integer.parseInt(JOptionPane.showInputDialog("please enter width of vector"));
        K = Integer.parseInt(JOptionPane.showInputDialog("please enter number of vectors"));

        K = log2(K);



        v = new Vector<int[]>();

        for (int i = 0; i < imagMatrix.length; i += n) {
            for (int j = 0; j < imagMatrix[0].length; j += m) {
                int[] arr = new int[n * m];
                int idx2 = 0;
                for (int k = 0, z = i; k < n; ++k, ++z) {
                    for (int l = 0, zz = j; l < m; ++l, ++zz) {
                        if (z >= imagMatrix.length || zz >= imagMatrix[0].length)
                            arr[idx2++] = 0;
                        else
                            arr[idx2++] = imagMatrix[z][zz];
                    }
                }
                v.add(arr);
            }
        }




        int[]tito = new int[n*m];
        for (int i = 0 ; i < tito.length;++i)
            tito[i] = 10000;


        fun(0,tito,false);

        // array of vectors AKA blocks
        int [] Arr = new int[v.size()];

        HashMap<int[],Integer> map =new HashMap<int[],Integer>();
        int curr = 0;

        //give every vector a number
        for (int i = 0 ; i < theone.size(); ++i){

            int [] tt = theone.elementAt(i);
            map.put(tt,curr++);
        }


        int mn = 100000;
        for (int i = 0 ; i < v.size();++i){
            mn =  100000000;
            int code = 0;
            for (int j = 0 ; j < theone.size(); ++j){
                int tempo = DISTANCE(v.elementAt(i),theone.elementAt(j));
                if (tempo < mn){
                    mn = tempo;
                    code = map.get(theone.elementAt(j));
                }
            }
            Arr[i] = code;
        }






        try {
            output1 = new Formatter("codetable");
        }
        catch (Exception e){

        }

        try {
            output2 = new Formatter("compressedfile");
        }
        catch (Exception e){

        }


        curr = 0;
        //printing each vector with its number
        for (int i = 0 ; i < theone.size() ; ++i){
            for (int j = 0 ; j < theone.elementAt(i).length; ++j){
                output1.format("%d ",theone.elementAt(i)[j]);
            }
            output1.format("\n");
            output1.format("%d\n",curr++);
        }
        output1.close();


        K = theone.size();
        int LENGHT = imagMatrix.length;
        while (LENGHT % n !=0)
            LENGHT++;
        int WIDTH = imagMatrix[0].length;
        while (WIDTH % m !=0)
            WIDTH++;

        //printing vector dimensions
        output2.format("%d %d\n",n,m);
        //printing K
        output2.format("%d\n",K);
        //printing Length and Width
        output2.format("%d %d\n",LENGHT,WIDTH);

        //Printing number of vectors
        output2.format("%d\n",Arr.length);

        for (int i = 0 ; i < Arr.length ; ++i){
            output2.format("%d ",Arr[i]);
        }
        output2.close();

    }

    public  static  int DISTANCE(int[]array1,int[]array2){

        int distance = 0;
        for (int i = 0 ; i < array1.length;++i)
            distance += Math.abs(array1[i] - array2[i]);
        return distance;
    }
    public static boolean isNearby (int[]array1, int[]array2, int[]array3){
        int distance1 = 0 , distance2=0;

        for (int i = 0 ; i < array1.length;++i){
            distance1+= Math.abs(array1[i]-array2[i]);
        }
        for (int i = 0 ; i < array1.length;++i){
            distance2+= Math.abs(array1[i]-array3[i]);
        }


        return distance1<=distance2;
    }



    public static void fun (int idx , int[]pastavg, boolean flag){
        int[] temp;
        int [] pastavg2 = new int[n*m];
        int [] avg = new int[n*m];




        if (flag == false)
            for (int i = 0 ; i < pastavg.length;++i)
                pastavg2[i] = pastavg[i] + 1;

        else
            for (int i = 0 ; i < pastavg.length;++i)
                pastavg2[i] = pastavg[i] - 1;


        int xx = 0;

        for (int i = 0 ; i < v.size();++i){
            temp = v.elementAt(i);
            if (isNearby(temp,pastavg,pastavg2)){
                xx++;
                for (int j = 0 ; j < pastavg.length;++j)
                    avg[j] += temp[j];
            }

        }



        for (int i = 0 ; i < pastavg.length;++i){
            try {
                avg[i]/=xx;
            }
            catch (Exception e){

            }
        }


        if (idx == K){
            theone.add(avg);
            return;
        }
        int[]avg2 = new  int[n*m];
        for (int i = 0 ; i < pastavg.length;++i){
            avg2[i] = avg[i] + 1;
        }

        fun(idx+1,avg,false);
        fun(idx+1,avg2,true);

    }






}



