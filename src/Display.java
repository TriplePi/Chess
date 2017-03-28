import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * Created by TriplePi on 30.01.2017.
 */
public class Display extends JFrame {
    public static JLabel[][] chessField = new JLabel[8][8];
    private JLabel eventLabel;
    private GridBagConstraints constraints;
    private GridBagLayout layout;
    private static Dimension chessCellDimension = new Dimension(100, 100);
    public Collocation startField = Collocation.getCollocation();
    static final Icons icons = new Icons();
    public int[] focus;
    private boolean moveColour = true;

    Display() {
        super("Chess");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel chessFieldPanel = new JPanel();
        Dimension chessDimension = new Dimension(800, 800);
        chessFieldPanel.setPreferredSize(chessDimension);
        chessFieldPanel.setMaximumSize(chessDimension);
        chessFieldPanel.setMinimumSize(chessDimension);
        chessFieldPanel.setBackground(Color.CYAN);
        layout = new GridBagLayout();
        chessFieldPanel.setLayout(layout);
        constraints = new GridBagConstraints();
        Display.createConstraints(constraints);
        GridBagConstraints mainConstraints = (GridBagConstraints) constraints.clone();
        mainConstraints.gridy = GridBagConstraints.RELATIVE;
        eventLabel = new JLabel();
        eventLabel.setPreferredSize(new Dimension(200, 200));
        JPanel mainPanel = new JPanel();
        mainPanel.add(eventLabel);
        //chessFieldPanel.addMouseListener(new CustomListener());
        fill(chessField, chessFieldPanel);
        setLayout(layout);
        getContentPane().add(mainPanel, mainConstraints);
        getContentPane().add(chessFieldPanel, mainConstraints);
        Icons icons = new Icons();
        startField.paintChessman(chessField, icons);
    }

    int[] convertCoordinate(double xCoord, double yCoord, JLabel label) {
        int x = (int) Math.round(xCoord) / 100;
        int y = (int) Math.round(yCoord) / 100;
        //System.out.println("x = " + x + " y = " + y);
        int[] coordinate = {x, y};
        System.out.println("x = " + xCoord + " y = " + yCoord);
        label.setText((String) ("x = " + x + " y = " + y));
        return coordinate;
    }


    void fill(JLabel[][] field, JPanel panel) {
        boolean a = false;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                field[i][j] = new JLabel();
                field[i][j].setPreferredSize(chessCellDimension);
                field[i][j].setMinimumSize(chessCellDimension);
                field[i][j].setMaximumSize(chessCellDimension);
                if (a)
                    field[i][j].setBackground(Color.black);
                else
                    field[i][j].setBackground(Color.white);
                field[i][j].setOpaque(true);
                field[i][j].addMouseListener(new CustomLableListener());
                int[] coordinate = {i, j};
                field[i][j].putClientProperty("coordinate", coordinate);
                panel.add(field[i][j], constraints);
                a = !a;

            }
            constraints.gridy++;
            a = !a;
        }
    }

    private static void createConstraints(GridBagConstraints constraints) {
        constraints.anchor = GridBagConstraints.NORTH;
        constraints.fill = GridBagConstraints.NONE;
        constraints.gridheight = 1;
        constraints.gridwidth = 1;
        constraints.gridx = GridBagConstraints.RELATIVE;
        constraints.gridy = 1;
        constraints.ipadx = 0;
        constraints.ipady = 0;
        constraints.weightx = 0.0;
        constraints.weighty = 0.0;
    }

    public static JLabel createEmptyLabel() {
        JLabel label = new JLabel();
        label.setPreferredSize(new Dimension(100, 35));
        return label;
    }


    public class CustomListener implements MouseListener {


        public void mouseClicked(MouseEvent e) {
            convertCoordinate(e.getX(), e.getY(), eventLabel);
        }

        public void mouseEntered(MouseEvent e) {
            JLabel label = (JLabel) e.getSource();

        }

        public void mouseExited(MouseEvent e) {

        }

        public void mousePressed(MouseEvent e) {

        }

        public void mouseReleased(MouseEvent e) {

        }
    }

    public class CustomLableListener implements MouseListener {

        public void mouseClicked(MouseEvent e) {
            JLabel label = (JLabel) e.getSource();
            if (startField.activeChessman!=null && startField.getChessman((int[]) label.getClientProperty("coordinate"))!= null)
                if (!startField.activeChessman.compareTo(startField.getChessman((int[]) label.getClientProperty("coordinate"))))
                    for (JLabel[] label1 : chessField)
                        for (JLabel label2 : label1)
                            if (label2.getIcon() == icons.green)
                                label2.setIcon(null);
            if(startField.getChessman((int[]) label.getClientProperty("coordinate"))!=null && startField.getChessman((int[]) label.getClientProperty("coordinate")).getColour() == moveColour)
                startField.paintPossibleMove((int[]) label.getClientProperty("coordinate"), chessField, icons);
            else{
                if (label.getIcon() == icons.green) {
                    //startField.paintChessman(chessField,icons);
                    int[] coordinatesOfLabel = (int[]) label.getClientProperty("coordinate");
                    startField.act(coordinatesOfLabel);
                    startField.test();
                    startField.activeChessman = null;
                    moveColour = !moveColour;
                    startField.paintChessman(chessField, icons);
                }
            }
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
