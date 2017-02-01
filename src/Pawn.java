import java.awt.*;

/**
 * Created by TriplePi on 31.01.2017.
 */
public class Pawn extends Chessman {
    private boolean firstMove;

    public Pawn(boolean colour, int x, int y, Image icon){
        super(colour,x,y,icon);
        this.firstMove = true;
    }
}
