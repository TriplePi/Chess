import java.util.Comparator;

/**
 * Created by TriplePi on 19.03.2017.
 */
public class ActionComparator implements Comparator<int[]> {
//    public int colour;
//    public ActionComparator(int colour){
//        this.colour = colour;
//    }

    public int compare(int[] first,int[] second){
        if(first[1]==second[1])
            return 0;
        else {
            if ((first[1] > second[1]))
                return -1;
            else return 1;
        }
    }
}
