import javax.swing.*;
import java.util.*;

/**
 * Created by TriplePi on 30.01.2017.
 */
class Collocation {
    Chessman[][] chessField;
    Chessman activeChessman;
    HashSet<int[]> phantomAteChessman;
    private static Collocation collocationForAll;

    public static Collocation getCollocation() {
        if (collocationForAll == null) {
            collocationForAll = new Collocation();
        }
        return collocationForAll;
    }

    private Collocation() {
        this.chessField = new Chessman[8][8];
//        for (int i = 0; i < 4; i++)
//            this.chessField[0][2 * i + 1] = new Pawn(false, 0, 2 * i + 1);
//        for (int i = 0; i < 4; i++)
//            this.chessField[1][2 * i] = new Pawn(false, 1, 2 * i);
//        for (int i = 0; i < 4; i++)
//            this.chessField[2][2 * i + 1] = new Pawn(false, 2, 2 * i + 1);
//        for (int i = 0; i < 4; i++)
//            this.chessField[5][2 * i] = new Pawn(true, 5, 2 * i);
//        for (int i = 0; i < 4; i++)
//            this.chessField[6][2 * i + 1] = new Pawn(true, 6, 2 * i + 1);
//        for (int i = 0; i < 4; i++)
//            this.chessField[7][2 * i] = new Pawn(true, 7, 2 * i);
        //this.chessField[4][2] = new Pawn(false, 4, 2);
        int[][] a =
                        {{0,0,0,0,0,0,0,0},
                        {0,0,0,0,0,0,0,0},
                        {0,-1,0,-1,0,0,0,0},
                        {0,0,0,0,1,0,0,0},
                        {0,-1,0,0,0,0,0,0},
                        {0,0,0,0,0,0,0,0},
                        {0,0,0,0,0,0,0,0},
                        {0,0,0,0,0,0,0,0}};
        for (int i = 0;i<8;i++){
            for (int j = 0;j<8;j++){
                if (a[i][j]==1){
                    chessField[i][j]=new Pawn(true,i,j);
                }
                else if(a[i][j]==-1){
                    chessField[i][j]=new Pawn(false,i,j);
                }
            }
        }
        test();
        phantomAteChessman = new HashSet<>();
    }

    void paintChessman(JLabel[][] labels, Icons icons) {
        int k = 1;
        int l = 1;
        for (JLabel[] labely : labels) {
            for (JLabel labelx : labely) {
                labelx.setIcon(null);
            }

        }
        Class<? extends Chessman> type;
        for (Chessman[] i : this.chessField) {
            for (Chessman j : i) {

                if (j != null) {
                    type = j.getClass();
                    if (type == Pawn.class) {
                        if (j.getColour())
                            labels[j.getCoordinate()[0]][j.getCoordinate()[1]].setIcon(icons.whitePawn);
                        else
                            labels[j.getCoordinate()[0]][j.getCoordinate()[1]].setIcon(icons.blackPawn);
                    }
                }
                k++;
            }
            k = 1;
            l++;
        }
    }

    void paintPossibleMove(int[] coordinate, JLabel[][] labels, Icons icons) {
//        for (JLabel label1[] : labels) {
//            for (JLabel label2 : label1) {
//                if (label2.getIcon() == icons.green) {
//                    label2.setIcon(null);
//                }
//            }
//        }
        phantomAteChessman.clear();
        if (activeChessman != null)
            activeChessman.clearMove();
        activeChessman = this.getChessman(coordinate);
        activeChessman.addAction(new Moving(activeChessman.getCoordinate(), activeChessman.getCoordinate()));
        Moving moving = new Moving(activeChessman.getCoordinate(),activeChessman.getCoordinate());
        activeChessman.actions.add(moving.newCoordinates,moving);
        ((Pawn) (this.getChessman(coordinate))).computePossibleMove(coordinate, 0);

        for (int[] coordinateForOneAct : activeChessman.actions.getMoves()) {
            //System.out.println(coordinateForOneAct[0] + coordinateForOneAct[1]);
            labels[coordinateForOneAct[0]][coordinateForOneAct[1]].setIcon(icons.green);
        }
    }

    private void setChessman(Chessman chessman) {
        this.chessField[chessman.getCoordinate()[0]][chessman.getCoordinate()[1]] = chessman;
    }

    Chessman getChessman(int[] coordinate) {
        Chessman chessman = null;
        if (this.chessField[coordinate[0]][coordinate[1]] != null)
            return this.chessField[coordinate[0]][coordinate[1]];
        return null;
    }

    void unsetChessman(int[] coordinate) {
        this.chessField[coordinate[0]][coordinate[1]] = null;
    }

    void updateCollocation() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                int[] buffer = {i, j};
                try {
                    if (this.getChessman(buffer).getCoordinate()[0] != buffer[0] || this.getChessman(buffer).getCoordinate()[1] != buffer[1]) {
//                        System.out.println();
//                        System.out.println(Integer.toString(buffer[0]) + Integer.toString(buffer[1]));
//                        System.out.println(Integer.toString(this.getChessman(buffer).getCoordinate()[0]) + Integer.toString(this.getChessman(buffer).getCoordinate()[1]));
//                        System.out.println();
                        this.setChessman(this.getChessman(buffer));
                        this.chessField[buffer[0]][buffer[1]] = null;
                        //System.out.println(Integer.toString(buffer[0]) + Integer.toString(buffer[1]));
                    }
                } catch (NullPointerException e) {
                }
            }
        }
    }

    void test() {
        for (Chessman[] chessmans : this.chessField) {
            System.out.println();
            for (Chessman chessman : chessmans) {
                if (chessman == null) {
                    System.out.print("0  ");
                } else {
                    System.out.print("1  ");
                }

            }

        }
    }


}


