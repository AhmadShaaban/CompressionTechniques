import javax.swing.*;
import java.io.File;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Scanner;

class Lz78Decompression {
    Formatter output;
    Scanner input ;
    Lz78Decompression(String x){
        this.OpenFile(x);
        this.Decompress();
    }
    public void OpenFile(String xx){
        try{
            output = new Formatter("text.txt");
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null,"Error Ya M3lm","Error",JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }


        String path = xx;
        try{
            input = new Scanner(new File(path));
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null,"File Does not exist \n try again","Error",JOptionPane.ERROR_MESSAGE);
        }


    }

    public void Decompress(){
        HashMap<Integer,String> map =new HashMap<Integer,String>();
        String x;
        String temp;
        int curr = 1;
        while (input.hasNext()){
            temp="";
            x = input.next();

            int idx; String chr;
            String dummy = new String();

            int i;
            for ( i = 1; i < x.length();++i){
                if (x.charAt(i) == ',')
                    break;
                dummy+=x.charAt(i);
            }
            i++;
            idx = Integer.parseInt(dummy);
            chr = new String();
            for (;i<x.length()-1;++i){
                chr += x.charAt(i);
            }




            if (chr.equals("null")){
                temp = map.get(idx);
                // System.out.println("hello");
                output.format("%s",temp);
            }

            else {

                if (idx != 0) {
                    temp = map.get(idx);

                }
                temp += chr;
                map.put(curr, temp);
                curr++;
                output.format("%s", temp);
                //System.out.print(temp);
            }

        }

        input.close();
        output.close();
        JOptionPane.showMessageDialog(null,"Done !!!","3ash",JOptionPane.PLAIN_MESSAGE);
    }

}
