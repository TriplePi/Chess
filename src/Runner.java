import javax.swing.*;
import java.awt.*;

/**
 * Created by TriplePi on 31.01.2017.
 */
public class Runner {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame.setDefaultLookAndFeelDecorated(true);
                JFrame frame = new Display();
                frame.setPreferredSize(new Dimension(1200, 1000));
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });

    }
}
