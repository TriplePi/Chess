/**
 * Created by TriplePi on 22.03.2017.
 */
public class Queen extends Chessman {
    private Queen(boolean colour, int x,int y){
        super(colour,x,y);
    }



    void computePossibleMove(int[] oldCoordinate, int flag){
        Chessman active = Collocation.getCollocation().activeChessman;
        Collocation collocation = Collocation.getCollocation();
        boolean colour = active.getColour();
        int multi;
        if (colour) {
            multi = -1;
        } else {
            multi = 1;
        }
        // проверка хода или боя вперёд
        //влево
        if (flag == 1 || flag == 0) {
            try {
                if (collocation.getChessman(changeCoordinates(oldCoordinate, multi, -1)) == null && flag == 0) {
                    active.addAction(new Moving(oldCoordinate, changeCoordinates(oldCoordinate, multi, -1)));
                    if (colour) {
                        if (changeCoordinates(oldCoordinate, multi, -1)[0] == 0) {

                        }
                    }
                } else {
                    if (collocation.getChessman(changeCoordinates(oldCoordinate, multi * 2, -2)) == null && collocation.getChessman(changeCoordinates(oldCoordinate, multi, -1)) != null &&
                            collocation.getChessman(changeCoordinates(oldCoordinate, multi, -1)).getColour() != colour && !active.actions.containsKey(changeCoordinates(oldCoordinate, multi * 2, -2))) {
                        active.addAction(new Eating(changeCoordinates(oldCoordinate, multi, -1), oldCoordinate, changeCoordinates(oldCoordinate, multi * 2, -2)));
                        computePossibleMove(changeCoordinates(oldCoordinate, multi * 2, -2), 1);
                    }
                }
            } catch (ArrayIndexOutOfBoundsException e) {
            }
        }
        //вправо
        if (flag == 2 || flag == 0) {
            try {
                if (collocation.getChessman(changeCoordinates(oldCoordinate, multi, 1)) == null && flag == 0) {
                    active.addAction(new Moving(oldCoordinate, changeCoordinates(oldCoordinate, multi, 1)));
                } else {
                    if (collocation.getChessman(changeCoordinates(oldCoordinate, multi * 2, 2)) == null && collocation.getChessman(changeCoordinates(oldCoordinate, multi, 1)) != null &&
                            collocation.getChessman(changeCoordinates(oldCoordinate, multi, 1)).getColour() != colour && !active.actions.containsKey(changeCoordinates(oldCoordinate, multi * 2, 2))) {
                        active.addAction(new Eating(changeCoordinates(oldCoordinate, multi, 1), oldCoordinate, changeCoordinates(oldCoordinate, multi * 2, 2)));
                        computePossibleMove(changeCoordinates(oldCoordinate, multi * 2, 2), 2);
                    }
                }
            } catch (ArrayIndexOutOfBoundsException e) {
            }
        }
        //проверка хода или боя назад
        //влево
        if (flag == 3 || flag == 0) {
            try {

                if (collocation.getChessman(changeCoordinates(oldCoordinate, multi * -2, 2)) == null && collocation.getChessman(changeCoordinates(oldCoordinate, multi * -1, 1)) != null &&
                        collocation.getChessman(changeCoordinates(oldCoordinate, multi * -1, 1)).getColour() != colour && !active.actions.containsKey(changeCoordinates(oldCoordinate, multi * -2, 2))) {
                    active.addAction(new Eating(changeCoordinates(oldCoordinate, multi * -1, 1), oldCoordinate, changeCoordinates(oldCoordinate, multi * -2, 2)));
                    computePossibleMove(changeCoordinates(oldCoordinate, multi * -2, 2), 1);
                }
            } catch (ArrayIndexOutOfBoundsException e) {
            }
        }
        //вправо
        if (flag == 4 || flag == 0) {
            try {
                if (collocation.getChessman(changeCoordinates(oldCoordinate, multi * -2, -2)) == null && collocation.getChessman(changeCoordinates(oldCoordinate, multi * -1, -1)) != null &&
                        collocation.getChessman(changeCoordinates(oldCoordinate, multi * -1, -1)).getColour() != colour && !active.actions.containsKey(changeCoordinates(oldCoordinate, multi * -2, -2))) {
                    active.addAction(new Eating(changeCoordinates(oldCoordinate, multi * -1, -1), oldCoordinate, changeCoordinates(oldCoordinate, multi * -2, -2)));
                    computePossibleMove(changeCoordinates(oldCoordinate, multi * -2, -2), 1);
                }
            } catch (ArrayIndexOutOfBoundsException e) {
            }
        }
    }
}
