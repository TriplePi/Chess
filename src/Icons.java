import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.File;
import java.io.IOException;

/**
 * Created by TriplePi on 04.02.2017.
 */
public class Icons {
    ImageIcon whitePawn;
    ImageIcon blackPawn;
    ImageIcon green;
    ImageIcon whiteQueen;
    ImageIcon blackQueen;
    public Icons(){
        try{
            whitePawn = new ImageIcon(ImageIO.read(new File("src/whitePawn.png")));

            blackPawn = new ImageIcon(ImageIO.read(new File("src/blackPawn.png")));

            green = new ImageIcon(ImageIO.read(new File("src/green.png")));

            whiteQueen = new ImageIcon(ImageIO.read(new File("src/whiteQueen.png")));

            blackQueen = new ImageIcon(ImageIO.read(new File("src/blackQueen.png")));
        }
        catch (IOException ex){

        }
    }
}
