import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("ms paint 2.0");
        frame.getContentPane().add(new drawPanel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 800);
        frame.setVisible(true);
        new Controls();
    }
}