import javax.swing.*;
import java.io.File;
import java.text.Normalizer;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Scanner;

class Decompress{

    public HashMap<String,String> map =new HashMap<String,String>();
    String path1 = new String();
    String path2 = new String();
    Formatter output;
    Scanner in = new Scanner(System.in);
    Scanner input1;
    Scanner input2;

    Decompress(String a,String b){
        fun(a,b);
    }

    void fun(String a,String b){

        path1 = a;
        path2 = b;

        try {
            input1 = new Scanner(new File(path1));
        }
        catch (Exception e){
            System.out.println("ERROR");
        }

        try {
            input2 = new Scanner(new File(path2));
        }
        catch (Exception e){
            System.out.println("ERROR");
        }


        String x = new String();
        x = input1.nextLine();
        while (input2.hasNext()){
            String xx = new String();
            String y = new String();
            xx = input2.next();
            y = input2.next();
            map.put(y,xx);

        }

        String ans = new String();
        String temp = new String();
        for (int i = 0 ; i < x.length();++i){
            temp+=x.charAt(i);
            if (map.containsKey(temp)){
                ans+= map.get(temp);
                temp="";
            }
        }

        try{
            output = new Formatter("text.txt");
        }
        catch (Exception e){
            System.out.println("ERROR");
        }
        output.format("%s",ans);
        JOptionPane.showMessageDialog(null,"Done !!!","3ash",JOptionPane.PLAIN_MESSAGE);
        output.close();
        input1.close();
        input2.close();

    }



}