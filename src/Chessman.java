import java.awt.*;

/**
 * Created by TriplePi on 30.01.2017.
 */
public class Chessman {
    private boolean colour;
    /*
    true == white
    false == black
     */
    private int[] coordinate;
    private Image icon;

    public Chessman(boolean colour, int x, int y, Image icon){
        this.colour = colour;
        this.coordinate = new int[2];
        this.coordinate[0] = x;
        this.coordinate[1] = y;
        this.icon = icon;

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

    public Image getIcon() {
        return icon;
    }

    public void move(){

    }
}
