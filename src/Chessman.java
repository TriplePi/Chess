import java.awt.*;
import java.util.HashSet;

/**
 * Created by TriplePi on 30.01.2017.
 */
public abstract class Chessman {
    private boolean colour;
    //public HashSet<int[]> possibleMove;
    public BSTree actions;
    /*
    true == white
    false == black
     */
    public int[] coordinate;

    public Chessman(boolean colour, int x, int y){
        this.colour = colour;
        this.coordinate = new int[2];
        this.coordinate[0] = x;
        this.coordinate[1] = y;
        actions = new BSTree();

    }

    public boolean getColour() {
        return colour;
    }

    public int[] getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(int[] coordinate) {
        this.coordinate = coordinate;
    }

    public void clearMove(){
        this.actions = new BSTree();
    }

//    public void move(int[] newCoordinates,Collocation collocation){
//        collocation.activeChessman.setCoordinate(newCoordinates);
//        collocation.activeChessman.clearMove();
//        //collocation.chessField.
//        collocation.updateCollocation();
//    }

    void addAction(Moving action){
        System.out.println("Adding");
        if(action instanceof Eating) {
            System.out.println(Integer.toString(action.oldCoordinates[0])+' '+Integer.toString(action.oldCoordinates[1]));
            System.out.println(Integer.toString(action.newCoordinates[0]) + ' ' + Integer.toString(action.newCoordinates[1]));
            System.out.println(Integer.toString(((Eating) action).coordinateOfAtePawn[0]) + ' ' + Integer.toString(((Eating) action).coordinateOfAtePawn[1]));
        }
        System.out.println("EndOfAdding");
        actions.add(action.newCoordinates,action);
    }

    abstract void computePossibleMove(int[] oldCoordinate, int flag);

    public boolean compareTo(Chessman chessman){
        return this.getCoordinate() == chessman.getCoordinate();
    }

    public int[] changeCoordinates(int[] oldCoordinate, int addX, int addY) {
        int[] newCoordinates = {oldCoordinate[0] + addX, oldCoordinate[1] + addY};
        return newCoordinates;
    }

    public void changeCoordinates(int[] newCoordinates) {
//        System.out.println("Change");
//        System.out.println("old ");
//        System.out.print(Integer.toString(this.getCoordinate()[0]) + " " + Integer.toString(this.getCoordinate()[1]));
//        System.out.println("new");
//        System.out.print(Integer.toString(newCoordinates[0]) + " " + Integer.toString(newCoordinates[1]));
        this.coordinate = newCoordinates;
    }
}
