import javax.swing.*;
import java.io.File;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Scanner;

class LZWDecompression {
    Formatter output;
    Scanner input ;
    LZWDecompression(String x){
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
        String prev = new String();
        String temp;
        int val;
        boolean flag = false;
        int curr = 128;
        String Curr = new String();
        while (input.hasNext()){

            temp = input.nextLine();
            val = Integer.parseInt(temp);
            if (val<=127){
                Curr="";
                Curr+=(char)(val);
            }
            else if (map.containsKey(val)){
                Curr = map.get(val);
            }
            else{
                //output.format("%s",prev);
                //System.out.print(prev);
                Curr = prev + prev.charAt(0);
                System.out.println("special");

                if (!map.containsValue(Curr)) {
                    map.put(curr, Curr);
                    curr++;
                }
                output.format("%s",Curr);
                //System.out.println(Curr);
                prev = Curr;
                continue;

            }

            if (flag){
                String tt = prev + Curr.charAt(0);
                if (!map.containsValue(tt)) {
                    map.put(curr, tt);
                    curr++;
                }
            }
            flag = true;
            output.format("%s",Curr);
            //System.out.println(Curr);
            prev = Curr;
            Curr = "";


        }

        input.close();
        output.close();
        JOptionPane.showMessageDialog(null,"Done !!!","3ash",JOptionPane.PLAIN_MESSAGE);
    }

}
