import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import static java.lang.Integer.parseInt;

public class Controls{
    public static int redInput = 0;
    public static int blueInput = 0;
    public static int greenInput = 0;
    public static boolean stoggled = false;

    public Controls() {
        JFrame f = new JFrame("scuffed ms paint settings");
        JButton b = new JButton("Submit Color");
        JButton line = new JButton("\uD83D\uDC80Toggle Straight Line\uD83D\uDC80");
        JTextField red,blue,green;
        red=new JTextField("Replace with RED[0,255]");
        red.setBounds(0,100, 200,30);

        blue=new JTextField("Replace with BLUE[0,255]");
        blue.setBounds(0,150, 200,30);

        green=new JTextField("Replace with GREEN[0,255]");
        green.setBounds(0,200, 200,30);

        b.setBounds(0,250,200,50);
        b.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                redInput = parseInt(red.getText().toString());
                blueInput = parseInt(blue.getText().toString());
                greenInput = parseInt(green.getText().toString());
                /*
                if(redInput >= 255) {red.setText("Please input a value between 0 and 255");}
                if(blueInput >= 255) {blue.setText("Please input a value between 0 and 255");}
                if(greenInput >= 255) {green.setText("Please input a value between 0 and 255");}
                */
                System.out.println("R: " + redInput + ", G: " + blueInput + ", B: " + greenInput);
            }
        });

        line.setBounds(0,350,200,50);
        line.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //🤓wtf is a ternary operator🤓
                if (stoggled) {
                    stoggled = false;
                    line.setText("Straight Line Disabled");
                } else {
                    stoggled = true;
                    line.setText("Straight Line Enabled");
                }
            }
        });

        f.add(red);
        f.add(blue);
        f.add(green);
        f.add(b);
        f.add(line);
        f.setSize(200,500);
        f.setLayout(null);
        f.setVisible(true);
    }


}