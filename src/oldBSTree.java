import java.util.ArrayList;

/**
 * Created by TriplePi on 23.03.2017.
 */
public class oldBSTree {
    static class Node {
        int[] key;
        Moving value;
        Node lf, rf, lb, rb;

        Node(int[] key, Moving value) {
            this.key = key;
            this.value = value;
        }
    }

    private Node root = null;

    public boolean containsKey(int[] k) {
        Node x = root;
        while (x != null) {
            int cmp = compareTo(k, x.key);
            if (cmp == 0) {
                return true;
            }
            if (cmp < 0) {
                x = x.lf;
            } else {
                x = x.rf;
            }
        }
        return false;
    }

    public Moving get(int[] k) {
        Node x = root;
        while (x != null) {
            int cmp = compareTo(k, x.key);
            if (cmp == 0) {
                return x.value;
            }
            if (cmp < 0) {
                x = x.lf;
            } else {
                x = x.rf;
            }
        }
        return null;
    }

    void add(int[] k, Moving v) {
        Node x = root, y = null;
        while (x != null) {
            int cmp = compareTo(k, x.key);
            if (cmp == 0) {
                x.value = v;
                return;
            } else {
                y = x;
                if (cmp < 0) {
                    x = x.lf;
                } else {
                    x = x.rf;
                }
            }
        }
        Node newNode = new Node(k, v);
        if (y == null) {
            root = newNode;
        } else {
            if (compareTo(k, y.key) < 0) {
                y.lf = newNode;
            } else {
                y.rf = newNode;
            }
        }
    }

    private static int compareTo(int[] first, int[] second) {
        if (first[1] == second[1] && first[0] == second[0])
            return 0;
        else {
            if ((first[1] < second[1]) || (first[0] > second[0]))
                return -1;
            else return 1;
        }
    }

    ArrayList<int[]> flank(Node firstNode) {
        ArrayList<int[]> moves = new ArrayList<>();
        if (firstNode != root) {
            moves.add(firstNode.key);
        }
        if (firstNode.lf != null)
            moves.addAll(flank(firstNode.lf));
        if (firstNode.rf != null)
            moves.addAll(flank(firstNode.rf));
        return moves;
    }

    ArrayList<int[]> getMoves() {
        return flank(root);
    }

    public ArrayList<Moving> getBranch(int[] k) {
        System.out.println("getBranch");
        Node x = root;
        ArrayList<Moving> listOfMoves = new ArrayList<>();
        while (x != null) {
            if (x.value instanceof Moving) {
                System.out.println(" Moving");
                System.out.println(Integer.toString(x.value.oldCoordinates[0]) + ' ' + Integer.toString(x.value.oldCoordinates[1]));
                System.out.println(Integer.toString(x.value.newCoordinates[0]) + ' ' + Integer.toString(x.value.newCoordinates[1]));
            }
            if (x.value instanceof Eating) {
                System.out.println(" Eating");
                System.out.println(Integer.toString(x.value.oldCoordinates[0]) + ' ' + Integer.toString(x.value.oldCoordinates[1]));
                System.out.println(Integer.toString(x.value.newCoordinates[0]) + ' ' + Integer.toString(x.value.newCoordinates[1]));
                System.out.println(Integer.toString(((Eating) x.value).coordinateOfAtePawn[0]) + " " + Integer.toString(((Eating) x.value).coordinateOfAtePawn[1]));
            } else
                System.out.println();
            int cmp = compareTo(k, x.key);
            if (cmp == 0) {
                listOfMoves.add(x.value);
                System.out.println("endOfgetBranch");
                return listOfMoves;
            }
            Moving moving = x.value;
            listOfMoves.add(moving);
            if (cmp < 0) {
                x = x.lf;
            } else {
                x = x.rf;
            }
        }
        //System.out.println("endOfgetBranch");
        return null;
    }
}
