import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Scanner;

class Lz78Compression {
    private String x;
    private Formatter output;
    private Scanner input;
    Lz78Compression(String x){
        this.OpenFile(x);
        this.compress();

    }

    public  void OpenOutputFile(){


        try{
            output = new Formatter("LZ78Compressedfile.txt");
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
        try {
            input = new Scanner(new File(path));
            x = input.next();
            //System.out.println(x);
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "File Does not exist \n try again", "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
    }
    public void compress(){
        HashMap<String,Integer> map =new HashMap<String,Integer>();
        int curr = 1 ;
        int no = 0;
        String temp = new String();
        for (int i=0;i<x.length();++i){
            temp += x.charAt(i);
            if (!map.containsKey(temp)){
                map.put(temp,curr);
                output.format("<%s,%s>\n",String.valueOf(no),temp.charAt(temp.length()-1));
                curr ++;
                temp = "";
                no = 0;
            }
            else{
                no = map.get(temp);
            }
        }
        if (temp != ""){
            output.format("<%s,null>\n",String.valueOf(no));

        }
        JOptionPane.showMessageDialog(null,"Done !!!","3ash",JOptionPane.PLAIN_MESSAGE);
        output.close();
        input.close();

    }

}