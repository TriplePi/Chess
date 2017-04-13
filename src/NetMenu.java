import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by TriplePi on 04.04.2017.
 */
public class NetMenu extends JFrame {
    NetMenu(){
        super("NET");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(2,3,0,50));
        setBounds(200,200,500,200);
        JLabel label1 = new JLabel("ip");
        JLabel label2 = new JLabel("port");
        JLabel label3 = new JLabel("choose AI destiny");
        JTextField ipTextField = new JTextField();
        JTextField portTextField = new JTextField();
        ButtonGroup group = new ButtonGroup();
        JRadioButton white = new JRadioButton("WHITE");
        JRadioButton black = new JRadioButton("BLACK");
        group.add(white);
        group.add(black);
        JButton start = new JButton("FIGHT!1!!");
        add(label1);
        add(ipTextField);
        add(label3);
        add(white);
        white.setSelected(true);
        add(black);
        add(start);
        start.addActionListener(e -> {
            Net net;
            boolean colour;
            if(white.isSelected())
                colour = true;
            else colour = false;
            net = new Net(ipTextField.getText(),colour);
        });
    }
}
