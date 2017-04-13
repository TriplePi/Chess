import java.awt.*;
import java.util.HashSet;

/**
 * Created by TriplePi on 30.01.2017.
 */
public abstract class Chessman {
    private boolean colour;
    public LiteralTree actions;
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
    }

    public boolean getColour() {
        return colour;
    }

    public int[] getCoordinate() {
        return coordinate;
    }

    void addAction(Moving action){
        actions.add(action);
    }

    public void computePossibleMove(Collocation collocation){
        computePossibleMove(this.coordinate,0,collocation);
    }

    abstract void computePossibleMove(int[] oldCoordinate, int flag,Collocation oldCollacation);

    public boolean compareTo(Chessman chessman){
        return this.getCoordinate() == chessman.getCoordinate();
    }

    public static int[] changeCoordinates(int[] oldCoordinate, int addX, int addY) {
        int[] newCoordinates = {oldCoordinate[0] + addX, oldCoordinate[1] + addY};
        return newCoordinates;
    }

    public void changeCoordinates(int[] newCoordinates) {
        this.coordinate = newCoordinates;
    }
}
