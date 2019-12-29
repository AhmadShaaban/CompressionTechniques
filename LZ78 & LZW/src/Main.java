

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.io.*;
import java.awt.event.*;


public class Main extends JFrame {
    public JButton button1;
    public JButton button2;
    public JTextField textfield1;
    public JRadioButton lzw;
    public  JRadioButton lz78;
    LZWCompression lzwc;
    LZWDecompression lzwd;
    Lz78Compression lz78c;
    Lz78Decompression lz78d;
    public static void main(String[] args) {
        //Scanner in = new Scanner(System.in);

        //System.out.println("1 for Compression 2 for Decompression");
        new Main();

    }
    Main(){
        this.setSize(500,100);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Compression App");

        JPanel Panel1 = new JPanel();
        JLabel labe1 = new JLabel("Enter file path");
        Panel1.add(labe1);
        textfield1 = new JTextField("enter file path");
        textfield1.setColumns(30);
        textfield1.setText("");

        Panel1.add(textfield1);
        button1 = new JButton("COMPRESSION");
        button2 = new JButton("DECOMPRESSION");
        ListenForButton lforbutton = new ListenForButton();
        button1.addActionListener(lforbutton);
        button2.addActionListener(lforbutton);

        lzw = new JRadioButton("LZW");
        lz78 = new JRadioButton("LZ78");
        ButtonGroup BG = new ButtonGroup();
        BG.add(lzw);
        BG.add(lz78);
        lzw.setSelected(true);
        Panel1.add(lz78);
        Panel1.add(lzw);
        Panel1.add(button1);
        Panel1.add(button2);

        this.add(Panel1);
        this.setVisible(true);

    }
    private class ListenForButton implements ActionListener {




        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == button1){
                String x = textfield1.getText();
                if (lzw.isSelected())
                     lzwc = new LZWCompression(x);
                else
                    lz78c  = new Lz78Compression(x);

            }
            else if (e.getSource() == button2){
                String x = textfield1.getText();
                if (lzw.isSelected())
                    lzwd  = new LZWDecompression(x);
                else
                    lz78d  = new Lz78Decompression(x);
            }

        }

    }


}


