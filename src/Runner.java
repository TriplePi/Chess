import javax.swing.*;
import java.awt.*;

/**
 * Created by TriplePi on 31.01.2017.
 */
public class Runner {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            Menu menu = new Menu();
            menu.setVisible(true);
        });

    }
}
