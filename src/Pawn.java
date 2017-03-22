
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

    public void computePossibleMove(int[] oldCoordinate, int flag) {
        //int[] coordinate = new int[2];
        //Collocation collocation = Collocation.getCollocation();
        //HashSet<int[]> outputCoordinates = new HashSet<int[]>();
        Chessman active = Collocation.getCollocation().activeChessman;
        Collocation collocation = Collocation.getCollocation();
//        System.out.println("Check");
//        for (int[] phantom:collocation.phantomAteChessman) {
//            System.out.println(Integer.toString(phantom[0])+" "+Integer.toString(phantom[1]));
//        }
//        System.out.println("EndOfCheck");
        //System.arraycopy(oldCoordinate, 0, coordinate, 0, 2);
        boolean colour = active.getColour();
        //System.out.println(Integer.toString(coordinate[0]) + " " + Integer.toString(coordinate[1]));
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
                active.addAction(new Moving(oldCoordinate, changeCoordinates(oldCoordinate, multi, -1)));
                if (colour) {
                    if (changeCoordinates(oldCoordinate, multi, -1)[0] == 0) {

                    }
                }
            } else {
                if (collocation.getChessman(changeCoordinates(oldCoordinate, multi * 2, -2)) == null && collocation.getChessman(changeCoordinates(oldCoordinate, multi, -1)) != null &&
                        collocation.getChessman(changeCoordinates(oldCoordinate, multi, -1)).getColour() != colour && !active.actions.containsKey(changeCoordinates(oldCoordinate, multi * 2, -2))) {
                    active.addAction(new Eating(changeCoordinates(oldCoordinate, multi, -1), oldCoordinate, changeCoordinates(oldCoordinate, multi * 2, -2)));

//                    System.out.println("phantom");
//                    System.out.println(Integer.toString(changeCoordinates(oldCoordinate, multi, -1)[0])+" "+Integer.toString(changeCoordinates(oldCoordinate, multi, -1)[1]));
                    computePossibleMove(changeCoordinates(oldCoordinate, multi * 2, -2), 1);
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
        }
        //вправо
        try {
            if (collocation.getChessman(changeCoordinates(oldCoordinate, multi, 1)) == null && flag == 0) {
                active.addAction(new Moving(oldCoordinate, changeCoordinates(oldCoordinate, multi, 1)));
            } else {
                if (collocation.getChessman(changeCoordinates(oldCoordinate, multi * 2, 2)) == null && collocation.getChessman(changeCoordinates(oldCoordinate, multi, 1)) != null &&
                        collocation.getChessman(changeCoordinates(oldCoordinate, multi, 1)).getColour() != colour && !active.actions.containsKey(changeCoordinates(oldCoordinate, multi * 2, 2))) {
                    active.addAction(new Eating(changeCoordinates(oldCoordinate, multi, 1), oldCoordinate, changeCoordinates(oldCoordinate, multi * 2, 2)));

//                    System.out.println("phantom");
//                    System.out.println(Integer.toString(changeCoordinates(oldCoordinate, multi, 1)[0])+" "+Integer.toString(changeCoordinates(oldCoordinate, multi, 1)[1]));
                    computePossibleMove(changeCoordinates(oldCoordinate, multi * 2, 2), 1);
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
        }
        //проверка съедания назад
        //влево
        try {

            if (collocation.getChessman(changeCoordinates(oldCoordinate, multi * -2, 2)) == null && collocation.getChessman(changeCoordinates(oldCoordinate, multi * -1, 1)) != null &&
                    collocation.getChessman(changeCoordinates(oldCoordinate, multi * -1, 1)).getColour() != colour && !active.actions.containsKey(changeCoordinates(oldCoordinate, multi * -2, 2))) {
                active.addAction(new Eating(changeCoordinates(oldCoordinate, multi * -1, 1), oldCoordinate, changeCoordinates(oldCoordinate, multi * -2, 2)));
//                System.out.println("phantom");
//                System.out.println(Integer.toString(changeCoordinates(oldCoordinate, multi * -1, 1)[0])+" "+Integer.toString(changeCoordinates(oldCoordinate, multi * -1, 1)[1]));
                computePossibleMove(changeCoordinates(oldCoordinate, multi * -2, 2), 1);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
        }
        //вправо
        try {
            if (collocation.getChessman(changeCoordinates(oldCoordinate, multi * -2, -2)) == null && collocation.getChessman(changeCoordinates(oldCoordinate, multi * -1, -1)) != null &&
                    collocation.getChessman(changeCoordinates(oldCoordinate, multi * -1, -1)).getColour() != colour && !active.actions.containsKey(changeCoordinates(oldCoordinate, multi * -2, -2))) {
                active.addAction(new Eating(changeCoordinates(oldCoordinate, multi * -1, -1), oldCoordinate, changeCoordinates(oldCoordinate, multi * -2, -2)));
//                System.out.println("phantom");
//                System.out.println(Integer.toString(changeCoordinates(oldCoordinate, multi * -1, -1)[0])+" "+Integer.toString(changeCoordinates(oldCoordinate, multi * -1, -1)[1]));
                computePossibleMove(changeCoordinates(oldCoordinate, multi * -2, -2), 1);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
        }
    }


    void replaceWithQueen() {

    }

}
