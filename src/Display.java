import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;

/**
 * Created by TriplePi on 30.01.2017.
 */
public class Display extends JFrame {
    public JLabel[][] chessField;
    private JLabel eventLabel;

    public Display() {
        super("Test frame");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel chessFieldPanel = new JPanel();
        //chessFieldPanel.setPreferredSize(new Dimension(800, 700));
        chessFieldPanel.setLayout(new GridLayout(8,6));
        //chessFieldPanel.setLayout(new FlowLayout());

        eventLabel = new JLabel();
        eventLabel.setHorizontalAlignment(JLabel.CENTER);

        JPanel mainPanel = new JPanel();
        mainPanel.add(eventLabel);
        mainPanel.add(new JLabel("уси"));
        chessFieldPanel.addMouseListener(new CustomListener());

        fill(this.chessField, chessFieldPanel);

        getContentPane().add(chessFieldPanel, BorderLayout.EAST);
        getContentPane().add(mainPanel, BorderLayout.WEST);

    }

    public int[] convertCoordinate(double xCoord, double yCoord, JLabel label) {
        int x = (int) Math.round(xCoord / 100);
        int y = (int) Math.round(yCoord / 100);
        //System.out.println("x = " + x + " y = " + y);
        int[] coordinate = {x, y};
        System.out.println("x = " + xCoord + " y = " + yCoord);
        label.setText((String)("x = " + x + " y = " + y));
        return coordinate;
    }


    public void fill(JLabel[][] field, JPanel panel) {
        boolean a = false;
        field = new JLabel[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                field[i][j] = new JLabel();
                field[i][j].setPreferredSize(new Dimension(100, 100));
                if (a)
                    field[i][j].setBackground(Color.white);
                else
                    field[i][j].setBackground(Color.black);
                field[i][j].setOpaque(true);
                field[i][j].setText(Integer.toString((i+1))+" "+Integer.toString(j+1));
                //field[i][j].setLocation(i * 100, j * 100);
                //field[i][j].addMouseListener(new CustomListener());
                panel.add(field[i][j]);

                a = !a;

            }
            a = !a;
        }
    }

    public static JLabel createEmptyLabel() {
        JLabel label = new JLabel();
        label.setPreferredSize(new Dimension(100, 35));
        return label;
    }

    public class CustomListener implements MouseListener {

        public void mouseClicked(MouseEvent e) {
            convertCoordinate(e.getX(), e.getY(),eventLabel);
        }

        public void mouseEntered(MouseEvent e) {

        }

        public void mouseExited(MouseEvent e) {

        }

        public void mousePressed(MouseEvent e) {

        }

        public void mouseReleased(MouseEvent e) {

        }
    }
}
