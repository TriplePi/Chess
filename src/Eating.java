/**
 * Created by TriplePi on 13.03.2017.
 */
public class Eating extends Moving {
    int[] coordinateOfAtePawn;
    //Moving moving;

    public Eating(int[] coordinateOfAtePawn, int[] oldCoordinates, int[] newCoordinates,boolean flag, Collocation collocation) {
        super(oldCoordinates, newCoordinates, flag,collocation);
        this.coordinateOfAtePawn = coordinateOfAtePawn;
    }

//    public Eating(int[] coordinateOfAtePawn, int[] oldCoordinates, int[] newCoordinates, boolean flag) {
//        super(oldCoordinates, newCoordinates, flag);
//        this.coordinateOfAtePawn = coordinateOfAtePawn;
//    }

    @Override
    void doing() {
        super.doing();
//        System.out.println("Eating");
//        System.out.println(Integer.toString(coordinateOfAtePawn[0])+' '+Integer.toString(coordinateOfAtePawn[1]));
//        System.out.println("EndOfEating");
        this.collocation.unsetChessman(coordinateOfAtePawn);
        this.collocation.updateCollocation();
    }
}

