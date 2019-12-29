import javax.swing.*;
import java.io.File;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Vector;

class Commpress{

    Formatter output1 ;
    Formatter output2 ;
    public String x = new String();
    public Scanner in ;
    String P = new String();
    public int []freq = new int[26];
    public HashMap<String,String> map =new HashMap<String,String>();
    public Vector<node> v = new Vector<node>();

    Commpress(String path){
        try{
            in = new Scanner(new File(path));
        }
        catch (Exception e){
            System.out.println("ERROR");
        }
        fun();
    }

    void SORT (){
        for (int i = 0 ; i < v.size();++i){
            for (int j = i +1 ; j < v.size(); ++j){
                if (v.elementAt(j).prob > v.elementAt(i).prob) {
                    node n = new node();
                    n = v.elementAt(j);
                    v.setElementAt(v.elementAt(i),j);
                    v.setElementAt(n,i);

                }
            }
        }

    }

    public void Traverse(node n,String c){
        n.code = c;
        if (n.right != null){
            Traverse(n.right,c + "0");
        }
        if (n.left != null){
            Traverse(n.left,c + "1");
        }
        if (n.right == null && n.left == null){
            String dummy = new String();
            dummy+=n.c;
            map.put(dummy,n.code);

            output1.format("%s %s\n",dummy,n.code);
        }

    }

    void fun(){
        try {
            output1 = new Formatter("codetable.txt");
        }
        catch (Exception e){
            System.out.println("ERROR");
        }
        try {
            output2 = new Formatter("compressedfile.txt");
        }
        catch (Exception e){
            System.out.println("ERROR");
        }

        x = in.nextLine();
        for (int i = 0 ; i < x.length();++i){
            freq[x.charAt(i) - 'a']++;
        }

        for (int i = 0 ; i < 26 ; ++i){
            if (freq[i] != 0){
                double t = freq[i]/(double)x.length();
                node n = new node();
                n.prob = t;
                n.c = (char)(i + 'a');
                v.add(n);
            }
        }

        compress();

        Traverse(v.elementAt(v.size() - 1 ),"");

        for (int i = 0 ; i < x.length();++i){
            String h = new String();
            h+=x.charAt(i);
            String ans = new String();
            ans=map.get(h);
            output2.format("%s",ans);
        }
        JOptionPane.showMessageDialog(null,"Done !!!","3ash",JOptionPane.PLAIN_MESSAGE);
        output1.close();
        output2.close();
    }

    public void compress(){

        while (v.size() != 1){


            SORT();
            node x = new node();
            node y = new node();

            x = v.elementAt(v.size() - 1);

            y = v.elementAt(v.size() - 2);
            v.remove(v.size()-1);
            v.remove(v.size()-1);
            node z = new node();
            z.prob = x.prob + y.prob;
            z.left = x;
            z.c = '0';
            z.right = y;
            v.add(z);
        }
    }

}