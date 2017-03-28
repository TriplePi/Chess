import java.util.Arrays;

/**
 * Created by TriplePi on 31.01.2017.
 */
public class Pawn extends Chessman {


    public Pawn(boolean colour, int x, int y) {
        super(colour, x, y);

    }

//    public void computePossibleMove(int[] oldCoordinate, boolean colour, int flag){
//        int[] coordinate = new int[2];
//        //Collocation collocation = Collocation.getCollocation();
//        HashSet<int[]> outputCoordinates = new HashSet<int[]>();
//        System.arraycopy(oldCoordinate,0,coordinate,0,2);
//        System.out.println(Integer.toString(coordinate[0])+" "+Integer.toString(coordinate[1]));
//        int multi;
//        if (colour){
//            multi = -1;
//        }
//        else {
//            multi = 1;
//        }
//        try {
//            if (Collocation.getCollocation().getChessman(changeCoordinates(oldCoordinate, multi, -1)) == null && flag!=1) {
//                outputCoordinates.add(changeCoordinates(oldCoordinate, multi, -1));
//            } else {
//                if (Collocation.getCollocation().getChessman(changeCoordinates(oldCoordinate, multi * 2, -2)) == null && Collocation.getCollocation().getChessman(changeCoordinates(oldCoordinate, multi, -1)).getColour()!= Collocation.getCollocation().activeChessman.getColour()) {
//                    Collocation.getCollocation().unsetChessman(changeCoordinates(oldCoordinate, multi, -1));
//                    outputCoordinates.add(changeCoordinates(oldCoordinate, multi * 2, -2));
//                    computePossibleMove(changeCoordinates(oldCoordinate, multi * 2, -2),colour,1);
//                }
//            }
//        }
//        catch (ArrayIndexOutOfBoundsException e){}
//        try {
//            if (Collocation.getCollocation().getChessman(changeCoordinates(oldCoordinate,multi,1)) == null && flag!=1){
//                outputCoordinates.add(changeCoordinates(oldCoordinate,multi,1));
//            }
//            else {
//                if (Collocation.getCollocation().getChessman(changeCoordinates(oldCoordinate,multi*2,2)) == null && Collocation.getCollocation().getChessman(changeCoordinates(oldCoordinate, multi, 1)).getColour()!= Collocation.getCollocation().activeChessman.getColour()){
//                    outputCoordinates.add(changeCoordinates(oldCoordinate,multi*2,2));
//                    Collocation.getCollocation().unsetChessman(changeCoordinates(oldCoordinate, multi, 1));
//                    computePossibleMove(changeCoordinates(oldCoordinate, multi * 2, 2),colour,1);
//                }
//            }
//        }
//        catch (ArrayIndexOutOfBoundsException e){}
//        try {
//            if (Collocation.getCollocation().getChessman(changeCoordinates(oldCoordinate,multi*-2,2)) == null && Collocation.getCollocation().getChessman(changeCoordinates(oldCoordinate, multi, 1)).getColour()!= Collocation.getCollocation().activeChessman.getColour()){
//                outputCoordinates.add(changeCoordinates(oldCoordinate,multi*2,2));
//                Collocation.getCollocation().unsetChessman(changeCoordinates(oldCoordinate, multi, 1));
//                computePossibleMove(changeCoordinates(oldCoordinate, multi * 2, 2),colour,1);
//            }
//        }
//        catch (ArrayIndexOutOfBoundsException e){}
//        try{
//            if (Collocation.getCollocation().getChessman(changeCoordinates(oldCoordinate,multi*-2,-2)) == null && Collocation.getCollocation().getChessman(changeCoordinates(oldCoordinate, multi, 1)).getColour()!= Collocation.getCollocation().activeChessman.getColour()){
//                outputCoordinates.add(changeCoordinates(oldCoordinate,multi*2,2));
//                Collocation.getCollocation().unsetChessman(changeCoordinates(oldCoordinate, multi, 1));
//                computePossibleMove(changeCoordinates(oldCoordinate, multi * 2, 2),colour,1);
//            }
//        }
//        catch (ArrayIndexOutOfBoundsException e){}
//        Collocation.getCollocation().activeChessman.possibleMove.addAll(outputCoordinates);
//    }

    public void computePossibleMove(int[] oldCoordinate, int flag, Collocation oldCollacation) {
        Chessman active = Collocation.getCollocation().activeChessman;
        Collocation collocation = Collocation.getCollocation();
        boolean colour = active.getColour();
        int multi;
        if (colour) {
            multi = -1;
        } else {
            multi = 1;
        }
        // проверка хода или съедания вперёд
        //влево
        try {
            if (collocation.getChessman(changeCoordinates(oldCoordinate, multi, -1)) == null && flag == 0) {
                if (check(changeCoordinates(oldCoordinate, multi, -1),collocation)) {
                    active.addAction(new Moving(oldCoordinate, changeCoordinates(oldCoordinate, multi, -1), true));
                }
                active.addAction(new Moving(oldCoordinate, changeCoordinates(oldCoordinate, multi, -1)));
            } else {
                if (collocation.getChessman(changeCoordinates(oldCoordinate, multi * 2, -2)) == null && collocation.getChessman(changeCoordinates(oldCoordinate, multi, -1)) != null &&
                        collocation.getChessman(changeCoordinates(oldCoordinate, multi, -1)).getColour() != colour &&
                        !active.actions.containsAction(oldCoordinate, changeCoordinates(oldCoordinate, multi * 2, -2))) {
                    if (check(changeCoordinates(oldCoordinate, multi * 2, -2),collocation)) {
                        active.addAction(new Eating(changeCoordinates(oldCoordinate, multi, -1), oldCoordinate, changeCoordinates(oldCoordinate, multi * 2, -2), true));
                        return;
                    }
                    active.addAction(new Eating(changeCoordinates(oldCoordinate, multi, -1), oldCoordinate, changeCoordinates(oldCoordinate, multi * 2, -2)));
                    computePossibleMove(changeCoordinates(oldCoordinate, multi * 2, -2), 1, null);
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
        }
        //вправо
        try {
            if (collocation.getChessman(changeCoordinates(oldCoordinate, multi, 1)) == null && flag == 0) {
                if (check(changeCoordinates(oldCoordinate, multi, 1),collocation)) {
                    active.addAction(new Moving(oldCoordinate, changeCoordinates(oldCoordinate, multi, 1), true));
                }
                active.addAction(new Moving(oldCoordinate, changeCoordinates(oldCoordinate, multi, 1)));
            } else {
                if (collocation.getChessman(changeCoordinates(oldCoordinate, multi * 2, 2)) == null && collocation.getChessman(changeCoordinates(oldCoordinate, multi, 1)) != null &&
                        collocation.getChessman(changeCoordinates(oldCoordinate, multi, 1)).getColour() != colour &&
                        !active.actions.containsAction(oldCoordinate, changeCoordinates(oldCoordinate, multi * 2, 2))) {
                    if (check(changeCoordinates(oldCoordinate, multi * 2, 2),collocation)) {
                        active.addAction(new Eating(changeCoordinates(oldCoordinate, multi, 1), oldCoordinate, changeCoordinates(oldCoordinate, multi * 2, 2), true));
                        return;
                    }
                    active.addAction(new Eating(changeCoordinates(oldCoordinate, multi, 1), oldCoordinate, changeCoordinates(oldCoordinate, multi * 2, 2)));
                    computePossibleMove(changeCoordinates(oldCoordinate, multi * 2, 2), 1, null);
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
        }
        //проверка съедания назад
        //влево
        try {
            if (collocation.getChessman(changeCoordinates(oldCoordinate, multi * -2, 2)) == null && collocation.getChessman(changeCoordinates(oldCoordinate, multi * -1, 1)) != null &&
                    collocation.getChessman(changeCoordinates(oldCoordinate, multi * -1, 1)).getColour() != colour &&
                    !active.actions.containsAction(oldCoordinate, changeCoordinates(oldCoordinate, multi * -2, 2))) {
                if(check(changeCoordinates(oldCoordinate, multi * -2, 2),collocation)){
                    active.addAction(new Eating(changeCoordinates(oldCoordinate, multi * -1, 1), oldCoordinate, changeCoordinates(oldCoordinate, multi * -2, 2),true));
                    return;
                }
                active.addAction(new Eating(changeCoordinates(oldCoordinate, multi * -1, 1), oldCoordinate, changeCoordinates(oldCoordinate, multi * -2, 2)));
                computePossibleMove(changeCoordinates(oldCoordinate, multi * -2, 2), 1, null);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
        }
        //вправо
        try {
            if (collocation.getChessman(changeCoordinates(oldCoordinate, multi * -2, -2)) == null && collocation.getChessman(changeCoordinates(oldCoordinate, multi * -1, -1)) != null &&
                    collocation.getChessman(changeCoordinates(oldCoordinate, multi * -1, -1)).getColour() != colour &&
                    !active.actions.containsAction(oldCoordinate, changeCoordinates(oldCoordinate, multi * -2, -2))) {
                if(check(changeCoordinates(oldCoordinate, multi * -2, -2),collocation)){
                    active.addAction(new Eating(changeCoordinates(oldCoordinate, multi * -1, 1), oldCoordinate, changeCoordinates(oldCoordinate, multi * -2, -2),true));
                    return;
                }
                active.addAction(new Eating(changeCoordinates(oldCoordinate, multi * -1, -1), oldCoordinate, changeCoordinates(oldCoordinate, multi * -2, -2)));
                computePossibleMove(changeCoordinates(oldCoordinate, multi * -2, -2), 1, null);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
        }
    }


    void replaceWithQueen(int[] coord) {
        Collocation.getCollocation().unsetChessman(coord);
        Collocation.getCollocation().setQueen(coord);
    }

    boolean check(int[] coord,Collocation collocation) {
        if (collocation.activeChessman.getColour()) {
            if (coord[0] == 0) {
                return true;
            }
        } else {
            if (coord[0] == 7) {
                return true;
            }
        }
        return false;
    }

}
