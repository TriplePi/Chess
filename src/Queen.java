import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by TriplePi on 22.03.2017.
 */
public class Queen extends Chessman {
    Queen(boolean colour, int x, int y) {
        super(colour, x, y);
    }

    void computePossibleMove(int[] oldCoordinate, int flag, Collocation oldCollocation) {
        Collocation collocation = Collocation.getCollocation();
//        try {
//            collocation = Collocation.getCollocation().clone();
//        }
//        catch (CloneNotSupportedException e){
//        }
        Chessman active = Collocation.getCollocation().activeChessman;
        boolean colour = active.getColour();
        int multi;
        if (colour) {
            multi = -1;
        } else {
            multi = 1;
        }
        // проверка хода или съедания вперёд
        //влево

        if (flag == 1 || flag == 0)
            try {

                if (collocation.getChessman(changeCoordinates(oldCoordinate, multi, -1)) == null) {
                    active.addAction(new Moving(oldCoordinate, changeCoordinates(oldCoordinate, multi, -1)));
                    computePossibleMove(changeCoordinates(oldCoordinate, multi, -1), 1, collocation);
                }
                if (collocation.getChessman(changeCoordinates(oldCoordinate, multi * 2, -2)) == null && collocation.getChessman(changeCoordinates(oldCoordinate, multi, -1)) != null &&
                        collocation.getChessman(changeCoordinates(oldCoordinate, multi, -1)).getColour() != colour &&
                        !active.actions.containsAction(oldCoordinate, changeCoordinates(oldCoordinate, multi * 2, -2))) {
                    active.addAction(new Eating(changeCoordinates(oldCoordinate, multi, -1), oldCoordinate, changeCoordinates(oldCoordinate, multi * 2, -2)));
                    //computePossibleMove(changeCoordinates(oldCoordinate, multi * 2, -2), 5,collocation);
                }

            } catch (ArrayIndexOutOfBoundsException e) {
            }
        //вправо???
        if (flag == 2 || flag == 0)
            try {
                if (collocation.getChessman(changeCoordinates(oldCoordinate, multi, 1)) == null) {
                    System.out.println(1);
                    System.out.println(Arrays.toString(oldCoordinate) + Arrays.toString(changeCoordinates(oldCoordinate, multi, 1)));
                    active.addAction(new Moving(oldCoordinate, changeCoordinates(oldCoordinate, multi, 1)));
                    computePossibleMove(changeCoordinates(oldCoordinate, multi, 1), 2, collocation);
                    System.out.println(2);
                }

                if (collocation.getChessman(changeCoordinates(oldCoordinate, multi * 2, 2)) == null && collocation.getChessman(changeCoordinates(oldCoordinate, multi, 1)) != null &&
                        collocation.getChessman(changeCoordinates(oldCoordinate, multi, 1)).getColour() != colour &&
                        !active.actions.containsAction(oldCoordinate, changeCoordinates(oldCoordinate, multi * 2, 2))) {
                    active.addAction(new Eating(changeCoordinates(oldCoordinate, multi, 1), oldCoordinate, changeCoordinates(oldCoordinate, multi * 2, 2)));
                    //computePossibleMove(changeCoordinates(oldCoordinate, multi * 2, 2), 5,collocation);
                }
            } catch (ArrayIndexOutOfBoundsException e) {
            }
        //проверка съедания назад
        //влево
        if (flag == 3 || flag == 0)
            try {
                if (collocation.getChessman(changeCoordinates(oldCoordinate, multi * -1, 1)) == null) {
//                        if (changeCoordinates(oldCoordinate, multi, 1)[0] == 0 || changeCoordinates(oldCoordinate, multi, 1)[0] == 7 ||
//                                changeCoordinates(oldCoordinate, multi, 1)[1] == 0 || changeCoordinates(oldCoordinate, multi, 1)[1] == 7)
//                            break;
                    active.addAction(new Moving(oldCoordinate, changeCoordinates(oldCoordinate, multi * -1, 1)));
                    computePossibleMove(changeCoordinates(oldCoordinate, multi * -1, 1), 3, collocation);
                }
                if (collocation.getChessman(changeCoordinates(oldCoordinate, multi * -2, 2)) == null && collocation.getChessman(changeCoordinates(oldCoordinate, multi * -1, 1)) != null &&
                        collocation.getChessman(changeCoordinates(oldCoordinate, multi * -1, 1)).getColour() != colour &&
                        !active.actions.containsAction(oldCoordinate, changeCoordinates(oldCoordinate, multi * -2, 2))) {
                    active.addAction(new Eating(changeCoordinates(oldCoordinate, multi * -1, 1), oldCoordinate, changeCoordinates(oldCoordinate, multi * -2, 2)));
                    //computePossibleMove(changeCoordinates(oldCoordinate, multi * -2, 2), 5,collocation);
                }
            } catch (ArrayIndexOutOfBoundsException e) {
            }
        //вправо
        if (flag == 4 || flag == 0)
            try {
                if (collocation.getChessman(changeCoordinates(oldCoordinate, multi * -1, -1)) == null) {
//                        if (changeCoordinates(oldCoordinate, multi * -1, -1)[0] == 0 || changeCoordinates(oldCoordinate, multi * -1, -1)[0] == 7 ||
//                                changeCoordinates(oldCoordinate, multi * -1, -1)[1] == 0 || changeCoordinates(oldCoordinate, multi * -1, -1)[1] == 7)
//                            break;
                    active.addAction(new Moving(oldCoordinate, changeCoordinates(oldCoordinate, multi * -1, -1)));
                    computePossibleMove(changeCoordinates(oldCoordinate, multi * -1, -1), 4, collocation);
                }
                if (collocation.getChessman(changeCoordinates(oldCoordinate, multi * -2, -2)) == null && collocation.getChessman(changeCoordinates(oldCoordinate, multi * -1, -1)) != null &&
                        collocation.getChessman(changeCoordinates(oldCoordinate, multi * -1, -1)).getColour() != colour &&
                        !active.actions.containsAction(oldCoordinate, changeCoordinates(oldCoordinate, multi * -2, -2))) {
                    active.addAction(new Eating(changeCoordinates(oldCoordinate, multi * -1, -1), oldCoordinate, changeCoordinates(oldCoordinate, multi * -2, -2)));
                    //computePossibleMove(changeCoordinates(oldCoordinate, multi * -2, -2), 5,collocation);
                }
            } catch (ArrayIndexOutOfBoundsException e) {
            }

    }

    public ArrayList<Integer> checkDiagonal(int[] coord) {
        ArrayList<Integer> diagonals = new ArrayList<>();

        return diagonals;
    }
}
