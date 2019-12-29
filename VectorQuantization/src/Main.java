import javax.imageio.ImageIO;
import javax.swing.*;
import javax.tools.Diagnostic;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Vector;

public class Main extends JFrame {

    public JButton button1;
    public JButton button2;
    public JTextField textfield1;
    public JTextField textfield2;

    public static void main(String[] args) {
        new Main();
    }
     Main(){

        this.setSize(500,100);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Vector Quantization");

        JPanel Panel1 = new JPanel();

        JLabel labe1 = new JLabel("Enter Image path");
        Panel1.add(labe1);
        textfield1 = new JTextField("enter Image path");
        textfield1.setColumns(30);
        textfield1.setText("");

        Panel1.add(textfield1);
        button1 = new JButton("COMPRESSION");
        button2 = new JButton("DECOMPRESSION");
        ListenForButton lforbutton = new ListenForButton();
        button1.addActionListener(lforbutton);
        button2.addActionListener(lforbutton);
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
                Compression c = new Compression(x);

            }
            else if (e.getSource() == button2){
                String x = textfield1.getText();
                Decompression d = new Decompression(x);
            }

        }

    }
}



