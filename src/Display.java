import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by TriplePi on 30.01.2017.
 */
public class Display extends JFrame {
    public static JLabel[][] chessField = new JLabel[8][8];
    static private JTextArea eventLabel;
    private GridBagConstraints constraints;
    private GridBagLayout layout;
    private static Dimension chessCellDimension = new Dimension(100, 100);
    public Collocation startField = Collocation.getCollocation();
    static final Icons icons = new Icons();
    public int[] focus;
    private boolean moveColour = true;
    static ArrayList<String> log = new ArrayList<>();
    AI ai;

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
        eventLabel = new JTextArea();
        eventLabel.setEditable(false);
        eventLabel.setPreferredSize(new Dimension(200, 700));
        ButtonGroup group = new ButtonGroup();
        JRadioButton white = new JRadioButton("WHITE");
        JRadioButton black = new JRadioButton("BLACK");
        group.add(white);
        group.add(black);
        white.setSelected(true);
        JButton restart = new JButton("Restart");
        restart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                log.clear();
                Collocation.stash();
                startField = Collocation.getCollocation();
                if (white.isSelected()) {
                    moveColour = true;
                    ai = new AI(startField, false);
                } else {
                    moveColour = false;
                    ai = new AI(startField, true);
                    ai.colculate();
                }
                startField.paintChessman(chessField, icons);
            }
        });
        eventLabel.setAutoscrolls(true);
        //eventLabel.add(restart);
        JPanel mainPanel = new JPanel();
        GridBagLayout mainPanelLayout = new GridBagLayout();
        mainPanel.setLayout(mainPanelLayout);
        GridBagConstraints mP = new GridBagConstraints();
        mP.anchor = GridBagConstraints.NORTH;
        mP.fill = GridBagConstraints.NONE;
        mP.gridheight = 1;
        mP.gridwidth = 1;
        mP.gridx = GridBagConstraints.RELATIVE;
        mP.gridy = 1;
        mP.ipadx = 0;
        mP.ipady = 0;
        mP.weightx = 0.0;
        mP.weighty = 0.0;
        mainPanel.add(eventLabel, mP);
        mP.gridy++;
        mainPanel.add(white, mP);
        mainPanel.add(black, mP);
        mP.gridy++;
        mainPanel.add(restart);
        //chessFieldPanel.addMouseListener(new CustomListener());
        fill(chessField, chessFieldPanel);
        setLayout(layout);
        getContentPane().add(mainPanel, mainConstraints);
        getContentPane().add(chessFieldPanel, mainConstraints);
        Icons icons = new Icons();
        startField.paintChessman(chessField, icons);
        //moveColour = false;
        ai = new AI(Collocation.getCollocation(), !moveColour);
//        if (!moveColour)
//            ai.colculate();
//        startField.paintChessman(chessField, icons);
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

    static void addToLog(String s) {
        System.out.println(s);
        log.add(s);
        eventLabel.append(s.concat("\n"));
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


    public class CustomLableListener implements MouseListener {

        public void mouseClicked(MouseEvent e) {
            if (!startField.checkOnStab()) {
                JLabel label = (JLabel) e.getSource();
                if (startField.activeChessman != null && startField.getChessman((int[]) label.getClientProperty("coordinate")) != null)
                    if (!startField.activeChessman.compareTo(startField.getChessman((int[]) label.getClientProperty("coordinate"))))
                        for (JLabel[] label1 : chessField)
                            for (JLabel label2 : label1)
                                if (label2.getIcon() == icons.green)
                                    label2.setIcon(null);
                if (startField.getChessman((int[]) label.getClientProperty("coordinate")) != null && startField.getChessman((int[]) label.getClientProperty("coordinate")).getColour() == moveColour)
                    startField.paintPossibleMove((int[]) label.getClientProperty("coordinate"), chessField, icons);
                else {
                    if (label.getIcon() == icons.green) {
                        //startField.paintChessman(chessField,icons);
                        int[] coordinatesOfLabel = (int[]) label.getClientProperty("coordinate");
                        startField.act(coordinatesOfLabel);
                        startField.test();
                        startField.activeChessman = null;
                        startField.paintChessman(chessField, icons);
                        if (!startField.checkOnStab())
                            //moveColour = !moveColour;
                            if (!moveColour == ai.colour) {
                                ai.colculate();
                            }
                        startField.checkOnStab();
                        startField.paintChessman(chessField, icons);
                    }
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
