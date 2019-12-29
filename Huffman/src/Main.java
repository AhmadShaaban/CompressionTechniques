import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.Normalizer;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Vector;

public class Main extends JFrame {
    public JButton button1;
    public JButton button2;
    public JTextField textfield1;
    public JTextField textfield2;
    public Commpress c ;
    public Decompress d ;

    public static void main(String[] args) {

        new Main();
    }
    Main(){
        this.setSize(500,150);
        this.setLocationRelativeTo(null);
        this.setTitle("Standard Huffman");
        JPanel Panel1 = new JPanel();
        JLabel labe1 = new JLabel("Enter file path");
        Panel1.add(labe1);
        textfield1 = new JTextField("enter file path");
        textfield1.setColumns(30);
        textfield1.setText("");
        JLabel Label2 = new JLabel("Enter Dict path");
        //Label2.setHorizontalTextPosition(JLabel.LEFT);
        textfield2 = new JTextField("in case of Decompression");
        textfield2.setColumns(30);
        Panel1.add(textfield1);
        Panel1.add(Label2);
        Panel1.add(textfield2);
        button1 = new JButton("COMPRESSION");
        button2 = new JButton("DECOMPRESSION");
        Panel1.add(button1);
        Panel1.add(button2);
        ListenForButton lforbutton = new ListenForButton();
        button1.addActionListener(lforbutton);
        button2.addActionListener(lforbutton);
        this.add(Panel1);
        this.setVisible(true);


    }

    private class ListenForButton implements ActionListener {




        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == button1) {
                String x = textfield1.getText();
                c = new Commpress(x);
            }
            else if (e.getSource() == button2){
                String x = textfield1.getText();
                String y = textfield2.getText();
                d = new Decompress(x,y);
            }

        }

    }

}











class node{
    node left = null;
    node right = null;
    String code = new String();
    double prob = 0;
    char c;
    public node (){
        left = null;
        right = null;
        prob = 0;
    }
    public node(node a, node b, double x){
        left = a;
        right = b;
        prob = x;
    }

}




