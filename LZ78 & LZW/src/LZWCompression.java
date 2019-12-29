import javax.swing.*;
import java.io.File;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Scanner;

public  class LZWCompression {
    private String x;
    Formatter output;
    Scanner input;
    LZWCompression(String x){
        this.OpenFile(x);
        this.compress();

    }

    public  void OpenOutputFile(){


        try{
            output = new Formatter("LZWCompressedfile.txt");
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,"Error Ya M3lm","Error",JOptionPane.ERROR_MESSAGE);
            System.exit(0);

        }
    }
    public void OpenFile(String xx){
        OpenOutputFile();


        String path ;
        path = xx;
        try{
            input = new Scanner(new File(path));
            x = input.next();
            //System.out.println(x);
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null,"File Does not exist \n try again","Error",JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
    }
    public void compress(){
        //System.out.println(x);
        HashMap<String,Integer> map =new HashMap<String,Integer>();
        int curr = 128;
        int no = 0;
        String temp = new String();
        for (int i=0;i<x.length();++i){
            temp += x.charAt(i);
            if (!map.containsKey(temp) && temp.length()>1){
                map.put(temp,curr);
                output.format("%s\n",String.valueOf(no));
                curr ++;
                temp = "";
                no = 0;
                i--;
            }
            else if (temp.length() == 1){
                no = (int)(temp.charAt(0));
            }
            else{
                no = map.get(temp);
            }
        }
        if (temp != ""){
            output.format("%s\n",String.valueOf(no));

        }
        JOptionPane.showMessageDialog(null,"Done !!!","3ash",JOptionPane.PLAIN_MESSAGE);
        output.close();
        input.close();

    }

}