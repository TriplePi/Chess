import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by TriplePi on 04.04.2017.
 */
public class Menu extends JFrame{

    Menu(){
        super("Menu");
        setBounds(100,100,250,150);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JButton button1 = new JButton("Player vs AI");
        button1.setPreferredSize(new Dimension(50,50));
        JButton button2 = new JButton("AI vs AI");
        button2.setPreferredSize(new Dimension(50,50));
        button1.addActionListener(e -> {
            JFrame.setDefaultLookAndFeelDecorated(true);
            JFrame frame = new Display();
            frame.setPreferredSize(new Dimension(1100, 900));
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
        button2.addActionListener(e -> {
            NetMenu netMenu = new NetMenu();
            netMenu.setPreferredSize(new Dimension(200,200));
            netMenu.setVisible(true);
        });
        setLayout(new GridLayout());
        add(button1);
        add(button2);
    }

}
