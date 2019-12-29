import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Formatter;
import java.util.Scanner;
import java.util.Vector;

public class Compression {
    public int[] arr;
    public int[] compressed;
    public Vector<Integer> v;
    int bits;
    public Formatter xxx;

    ReadWriteImage c = new ReadWriteImage();
    String x;

    Compression(String x, int y) {
        kk(x, y);
    }

    void kk(String x, int bits) {

        int[][] imagMatrix = c.readImage(x);
        arr = new int[imagMatrix.length * imagMatrix[0].length];
        compressed = new int[arr.length];
        int curr = 0;
        for (int i = 0; i < imagMatrix.length; ++i) {
            for (int j = 0; j < imagMatrix[0].length; ++j) {
                arr[curr++] = imagMatrix[i][j];
            }
        }
        v = new Vector<>();


        fun(0, 256, 0);
        v.add(0);
        Collections.sort(v);

        for (int i = 0; i < arr.length; ++i) {
            for (int j = 1; j < v.size(); ++j) {
                if (arr[i] >= v.elementAt(j - 1) && arr[i] < v.elementAt(j)) {
                    compressed[i] = v.elementAt(j);
                    break;
                }
            }
        }
        curr = 0;
        for (int i = 0; i < imagMatrix.length; ++i) {
            for (int j = 0; j < imagMatrix[0].length; ++j) {
                imagMatrix[i][j] = compressed[curr++];
            }
        }

        try {
            Formatter xxx = new Formatter("compressedfile.txt");
        }
        catch (Exception e){
            System.out.println("Error");
        }
        //xxx.format("%s","ahmed");
        //printing();
       // c.writeImage(imagMatrix, x);


    }

   // void printing (){

        //xxx.format("%s","ahmed");
        //for (int i = 0 ; i < compressed.length;i++){
          //  output.format("%s",(int)compressed[i]);
        //}

    }





    public  void  fun (int idx , int num , int flag){

        int c = 0;
        long sum = 0;
        if (flag == 0){
            for (int i = 0 ; i < arr.length;++i){
                if (arr[i] <= num){
                    sum+=arr[i];
                    c++;
                }
            }
        }
        else{
            for (int i = 0 ; i < arr.length;++i){
                if (arr[i] >= num){
                    sum+=arr[i];
                    c++;
                }
            }

        }
        try {
            sum = sum/c;
        }
        catch (Exception e){
            System.out.println("A7a");
        }

        if (idx == bits){
            v.add((int)sum);
            return;
        }
        fun(idx+1,(int)sum,0);
        fun(idx+1,(int)sum+1,1);

    }

}

 class ReadWriteImage {

    public int[][] readImage(String filePath) {

        File f = new File(filePath); //image file path

        int[][] imageMAtrix = null;

        try {
            BufferedImage img = ImageIO.read(f);
            int width = img.getWidth();
            int height = img.getHeight();

            imageMAtrix = new int[height][width];

            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    int p = img.getRGB(x, y);
                    int a = (p >> 24) & 0xff;
                    int r = (p >> 16) & 0xff;
                    int g = (p >> 8) & 0xff;
                    int b = p & 0xff;

                    //because in gray image r=g=b  we will select r

                    imageMAtrix[y][x] = r;


                    //set new RGB value
                    p = (a << 24) | (r << 16) | (g << 8) | b;
                    img.setRGB(x, y, p);
                }
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return imageMAtrix;
    }

    public void writeImage(int[][] imageMatrix, String imageoutPath) {

        int height = imageMatrix.length;
        int width = imageMatrix[0].length;
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {

                int a = 255;
                int pix = imageMatrix[y][x];
                int p = (a << 24) | (pix << 16) | (pix << 8) | pix;

                img.setRGB(x, y, p);

            }
        }

        File f = new File("k.jpg");

        try {
            ImageIO.write(img, "jpg", f);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}



