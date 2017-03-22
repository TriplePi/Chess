/**
 * Created by TriplePi on 13.03.2017.
 */
public class Eating extends Moving {
    int[] coordinateOfAtePawn;
    //Moving moving;

    public Eating(int[] coordinateOfAtePawn, int[] oldCoordinates, int[] newCoordinates){
        super(oldCoordinates,newCoordinates);
        this.coordinateOfAtePawn = coordinateOfAtePawn;
    }

    @Override
    void doing() {
        super.doing();
//        System.out.println("Eating");
//        System.out.println(Integer.toString(coordinateOfAtePawn[0])+' '+Integer.toString(coordinateOfAtePawn[1]));
//        System.out.println("EndOfEating");
        collocation.unsetChessman(coordinateOfAtePawn);
        collocation.updateCollocation();
    }
}
