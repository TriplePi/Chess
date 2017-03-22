/**
 * Created by TriplePi on 13.03.2017.
 */
class Moving implements Comparable<Moving> {
    int[] oldCoordinates;
    int[] newCoordinates;
    Collocation collocation = Collocation.getCollocation();
    int flag;

    public Moving(int[] oldCoordinates, int[] newCoordinates) {
        this.oldCoordinates = oldCoordinates;
        this.newCoordinates = newCoordinates;
        flag = 0;
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
//        System.out.println("Moving");
//        System.out.println(Integer.toString(oldCoordinates[0])+' '+Integer.toString(oldCoordinates[1]));
//        System.out.println(Integer.toString(newCoordinates[0])+' '+Integer.toString(newCoordinates[1]));
//        System.out.println("EndOfMoving");
        ((Pawn)collocation.activeChessman).changeCoordinates(newCoordinates);

        collocation.updateCollocation();

    }
}
