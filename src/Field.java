import javax.swing.*;

/**
 * Created by TriplePi on 30.01.2017.
 */
public class Field {
    private Chessman[][] chessField;

    public Field(){
        this.chessField = new Chessman[8][8];
        int[] i = {0,1,6,7};
        for (int j: i)
            for (int k=0;k<8;k++){
                this.chessField[j][k] = new Chessman(true,j,k,null);
            }
    }
}
