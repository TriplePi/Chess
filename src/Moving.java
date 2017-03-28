import java.util.Arrays;

/**
 * Created by TriplePi on 13.03.2017.
 */
class Moving implements Comparable<Moving> {
    int[] oldCoordinates;
    int[] newCoordinates;
    Collocation collocation = Collocation.getCollocation();
    boolean flag;

    public Moving(int[] oldCoordinates, int[] newCoordinates) {
        this.oldCoordinates = oldCoordinates;
        this.newCoordinates = newCoordinates;
        flag = false;
//        System.out.println("wrong");
//        System.out.println(Arrays.toString(oldCoordinates));
//        System.out.println(Arrays.toString(newCoordinates));
//        System.out.println("whole");
    }

    public Moving(int[] oldCoordinates, int[] newCoordinates, boolean flag) {
        this.oldCoordinates = oldCoordinates;
        this.newCoordinates = newCoordinates;
        this.flag = flag;
//        System.out.println("wrong");
//        System.out.println(Arrays.toString(oldCoordinates));
//        System.out.println(Arrays.toString(newCoordinates));
//        System.out.println("whole");
    }


    public int compareTo(Moving moving) {
        if (this.newCoordinates[1] == moving.newCoordinates[1])
            return 0;
        else {
            if ((this.newCoordinates[1] > moving.newCoordinates[1]))
                return -1;
            else return 1;
        }
    }

    void doing() {
        collocation.activeChessman.changeCoordinates(newCoordinates);
        System.out.println("fick");
        System.out.println(this.flag);
        if (flag) {
            System.out.println("fuck");
            collocation.setQueen(newCoordinates);
        }
        collocation.updateCollocation();
    }
}
