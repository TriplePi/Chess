import javax.swing.*;
import java.util.*;

/**
 * Created by TriplePi on 30.01.2017.
 */
class Collocation implements Cloneable{
    private Chessman[][] chessField;
    Chessman activeChessman;
    private static Collocation collocationForAll;

    public Chessman[][] getChessField() {
        return chessField;
    }

    public static Collocation getCollocation() {
        if (collocationForAll == null) {
            collocationForAll = new Collocation();
        }
        return collocationForAll;
    }

    public Collocation clone() throws CloneNotSupportedException {
        return (Collocation)super.clone();
    }

    private Collocation() {
        this.chessField = new Chessman[8][8];
        for (int i = 0; i < 4; i++)
            this.chessField[0][2 * i + 1] = new Pawn(false, 0, 2 * i + 1);
        for (int i = 0; i < 4; i++)
            this.chessField[1][2 * i] = new Pawn(false, 1, 2 * i);
        for (int i = 0; i < 4; i++)
            this.chessField[2][2 * i + 1] = new Pawn(false, 2, 2 * i + 1);
        for (int i = 0; i < 4; i++)
            this.chessField[5][2 * i] = new Pawn(true, 5, 2 * i);
        for (int i = 0; i < 4; i++)
            this.chessField[6][2 * i + 1] = new Pawn(true, 6, 2 * i + 1);
        for (int i = 0; i < 4; i++)
            this.chessField[7][2 * i] = new Pawn(true, 7, 2 * i);
//        int[][] a =
//                        {{0,0,0,0,0,0,0,0},
//                        {0,0,0,1,0,0,0,0},
//                        {0,0,0,0,0,0,0,0},
//                        {0,0,0,0,0,-1,0,0},
//                        {0,0,0,0,0,0,0,0},
//                        {0,0,0,2,0,0,0,0},
//                        {0,0,0,0,0,0,0,0},
//                        {0,0,0,0,0,0,0,0}};
//        for (int i = 0;i<8;i++){
//            for (int j = 0;j<8;j++){
//                if (a[i][j]==1){
//                    chessField[i][j]=new Pawn(true,i,j);
//                }
//                if(a[i][j]==-1){
//                    chessField[i][j]=new Pawn(false,i,j);
//                }
//                if (a[i][j]==2){
//                    chessField[i][j]=new Queen(true,i,j);
//                }
//                if(a[i][j]==-2){
//                    chessField[i][j]=new Queen(false,i,j);
//                }
//            }
//        }
//        test();
    }

    void paintChessman(JLabel[][] labels, Icons icons) {
        for (JLabel[] labely : labels) {
            for (JLabel labelx : labely) {
                labelx.setIcon(null);
            }

        }
        Class<? extends Chessman> type;
        for (Chessman[] i : chessField) {
            for (Chessman j : i) {

                if (j != null) {
                    type = j.getClass();
                    if (type == Pawn.class) {
                        if (j.getColour())
                            labels[j.getCoordinate()[0]][j.getCoordinate()[1]].setIcon(icons.whitePawn);
                        else
                            labels[j.getCoordinate()[0]][j.getCoordinate()[1]].setIcon(icons.blackPawn);
                    }
                    if (type == Queen.class){
                        if (j.getColour())
                            labels[j.getCoordinate()[0]][j.getCoordinate()[1]].setIcon(icons.whiteQueen);
                        else
                            labels[j.getCoordinate()[0]][j.getCoordinate()[1]].setIcon(icons.blackQueen);
                    }
                }
            }
        }
    }

    void paintPossibleMove(int[] coordinate, JLabel[][] labels, Icons icons) {
        activeChessman = this.getChessman(coordinate);
        activeChessman.actions = new LiteralTree(activeChessman.getCoordinate());
        this.getChessman(coordinate).computePossibleMove(coordinate, 0,Collocation.getCollocation());
        HashMap<int[],String> allMovesForOneChessman = activeChessman.actions.getMoves();
        for (int[] coordinateForOneAct : allMovesForOneChessman.keySet()) {
            JLabel label = labels[coordinateForOneAct[0]][coordinateForOneAct[1]];
            label.setIcon(icons.green);
        }
    }

    void act(int[] coordinatesOfGreenPoint){
        Collocation.getCollocation().activeChessman.actions.getBranchByCoord(coordinatesOfGreenPoint).forEach(Moving::doing);
    }

    private void setChessman(Chessman chessman) {
        this.chessField[chessman.getCoordinate()[0]][chessman.getCoordinate()[1]] = chessman;
    }

    Chessman getChessman(int[] coordinate) {
        if (this.chessField[coordinate[0]][coordinate[1]] != null)
            return this.chessField[coordinate[0]][coordinate[1]];
        return null;
    }

    void unsetChessman(int[] coordinate) {
        chessField[coordinate[0]][coordinate[1]] = null;
    }

    void updateCollocation() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                int[] buffer = {i, j};
                try {
                    if (this.getChessman(buffer).getCoordinate()[0] != buffer[0] || this.getChessman(buffer).getCoordinate()[1] != buffer[1]) {
                        this.setChessman(this.getChessman(buffer));
                        this.chessField[buffer[0]][buffer[1]] = null;
                    }
                } catch (NullPointerException e) {
                }
            }
        }
    }

    void test() {
        for (Chessman[] chessmans : chessField) {
            System.out.println();
            for (Chessman chessman : chessmans) {
                if (chessman == null) {
                    System.out.print("0  ");
                } else {
                    System.out.print("1  ");
                }
            }
        }
        System.out.println();
    }

    void setQueen(int[] coord){
        chessField[coord[0]][coord[1]] = new Queen(activeChessman.getColour(),coord[0],coord[1]);
    }
}