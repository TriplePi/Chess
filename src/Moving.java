import java.util.Arrays;

/**
 * Created by TriplePi on 13.03.2017.
 */
class Moving implements Comparable<Moving> {
    int[] oldCoordinates;
    int[] newCoordinates;
    Collocation collocation;
    boolean flag;

    public Moving(int[] oldCoordinates, int[] newCoordinates, boolean flag, Collocation collocation) {
        this.oldCoordinates = oldCoordinates;
        this.newCoordinates = newCoordinates;
        this.flag =flag;
        this.collocation = collocation;
//        System.out.println("wrong");
//        System.out.println(Arrays.toString(oldCoordinates));
//        System.out.println(Arrays.toString(newCoordinates));
//        System.out.println("whole");
    }

    public Moving(int[] oldCoordinates, int[] newCoordinates, Collocation collocation) {
        this.oldCoordinates = oldCoordinates;
        this.newCoordinates = newCoordinates;
        this.flag = false;
        this.collocation = collocation;
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
        this.collocation.updateCollocation();
        this.collocation.getChessman(oldCoordinates).changeCoordinates(newCoordinates);
        //System.out.println("fick");
        this.collocation.updateCollocation();
        //collocation.test();
        if (flag) {
            //System.out.println("fuck");
            ((Pawn) this.collocation.getChessman(newCoordinates)).replaceWithQueen(collocation);
        }
        this.collocation.updateCollocation();
//        collocation.paintChessman(Display.chessField,Display.icons);
//        try {
//            Thread.sleep(1500);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }
}
