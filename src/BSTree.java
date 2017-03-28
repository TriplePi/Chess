import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

/**
 * Created by TriplePi on 21.03.2017.
 */
class BSTree {
    static class Node {
        int[] key;
        Moving value;
        Node lf, rf, lb, rb;

        Node(int[] key, Moving value) {
            this.key = key;
            this.value = value;
        }
    }

    public Node root = null;

//    public BSTree(){
//        Moving moving = new Moving(Collocation.getCollocation().activeChessman.getCoordinate(),Collocation.getCollocation().activeChessman.getCoordinate());
//        root = new Node(moving.newCoordinates,moving);
//    }

    public boolean containsKey(int[] k) {
        System.out.println("cont");
        Node x = root;
        HashSet<int[]> allKeys = flank(root);
        System.out.println("fuck");
        return allKeys.contains(k);
        //return false;
    }

    public Moving get(int[] k) {
        System.out.println("get");
        Node x = root;
        while (x != null) {
            int cmp = compareTo(k, x.key);
            if (cmp == 0)
                return x.value;
            if (cmp == 1)
                x = x.lf;
            if (cmp == 2)
                x = x.rf;
            if (cmp == 3)
                x = x.lb;
            if (cmp == 4)
                x = x.rb;
        }
        return null;
    }

    void add(int[] k, Moving v) {
//        System.out.println("start");
//        Node newNode = new Node(k, v);
//        if (root == null) {
//            root = newNode;
//            return;
//        }
//        ArrayList<Node> allNodes = anotherFlank(root);
//        for (Node one : allNodes) {
//            int cmp = compareTo(k, one.key);
//            System.out.println("fignya");
//            if (cmp == 1) {
//                if (one.lf == null)
//                    one.lf = newNode;
//            }
//            if (cmp == 2) {
//                if (one.lf == null)
//                    one.rf = newNode;
//            }
//            if (cmp == 3) {
//                if (one.lb == null)
//                    one.lb = newNode;
//            }
//            if (cmp == 4) {
//                if (one.rb == null)
//                    one.rb = newNode;
//            }
//            if (cmp == 5) {
//
//            }
//        }
//        System.out.println("first");
//        Node x = root, y = null;
//        switch (key){
//            case "lf":
//                x = x.lf;
//                break;
//            case "rf":
//                x = x.rf;
//                break;
//            case "lb":
//                x = x.lb;
//                break;
//            case "rb":
//                x = x.rb;
//                break;
//        }
//        if (root != null)
//            System.out.println("root" + Arrays.toString(x.key));
//        while (x != null) {
//            int cmp = compareTo(k, x.key);
//            System.out.println("ne zalupa");
//            if (cmp == 0) {
//                x.value = v;
//                return;
//            }
//            System.out.println("Eto to?");
//            System.out.println(Arrays.toString(k) + " " + Arrays.toString(x.key));
//            y = x;
//
//            if (cmp == 1) {
//                x = x.lf;
//            }
//            if (cmp == 2) {
//                x = x.rf;
//            }
//            if (cmp == 3) {
//                x = x.lb;
//            }
//            if (cmp == 4) {
//                x = x.rb;
//            }
//            if (cmp == 5) {
//                if (Arrays.equals(v.oldCoordinates,x.value.newCoordinates)){
//                    add(k,v,"lb");
//                }
//            }
//        }
//        System.out.println("second");
//        Node newNode = new Node(k, v);
//        if (y == null) {
//            root = newNode;
//        } else {
//            int cmp = compareTo(k, y.key);
//            if (cmp == 1)
//                y.lf = newNode;
//            if (cmp == 2)
//                y.rf = newNode;
//            if (cmp == 3)
//                y.lb = newNode;
//            if (cmp == 4)
//                y.lb = newNode;
//        }
        System.out.println("add");
        Node newNode = new Node(k,v);
        HashSet<Node> allNodes = anotherFlank(root);

        System.out.println(allNodes.size());
        System.out.println(Arrays.toString(root.value.newCoordinates));
        if(allNodes.contains(newNode))
            return;
        for (Node node:allNodes) {
            if(Arrays.equals(node.value.newCoordinates,newNode.value.oldCoordinates)){
                System.out.println("zahod");
                int cmp = compareTo(node.value.newCoordinates, newNode.value.oldCoordinates);
                System.out.println(cmp);
                if(cmp == 0){
                    return;
                }
                if (cmp == 1) {
                    node.lf = newNode;
                    System.out.println(1);
                    return;
                }
                if (cmp == 2) {
                    node.rf = newNode;
                    System.out.println(2);
                    return;
                }
                if (cmp == 3) {
                    node.lb = newNode;
                    System.out.println(3);
                    return;
                }
                if (cmp == 4) {
                    node.rb = newNode;
                    System.out.println(4);
                    return;
                }
            }
        }
    }


//    public static int compareTo(int[] first, int[] second, Moving a, Moving b) {
//        System.out.println(Arrays.toString(first) + " " + Arrays.toString(second));
//        if (first[1] == second[1] && first[0] == second[0]) {
//            System.out.println("equal");
//            return 0;
//        } else {
//            if (a.newCoordinates == b.oldCoordinates) {
//                System.out.println("Zahodish?");
//                if ((first[0] > second[0]) && (first[1] < second[1])) {
//                    System.out.println("lf");
//                    return 1;
//                    //влево вперёд
//                }
//                if ((first[0] > second[0]) && (first[1] > second[1])) {
//                    System.out.println("rf");
//                    return 2;
//                    //вправо вперёд
//                }
//                if ((first[0] < second[0]) && (first[1] < second[1])) {
//                    System.out.println("lb");
//                    return 3;
//                    //влево назад
//                }
//                if ((first[0] < second[0]) && (first[1] > second[1])) {
//                    System.out.println("rb");
//                    return 4;
//                    //вправо назад
//                }
//            }
//        }
//        System.out.println("wtf");
//        return 5;
//    }

    public static int compareTo(int[] first, int[] second) {
        //System.out.println(Arrays.toString(first) + " " + Arrays.toString(second));
        if (first[1] == second[1] && first[0] == second[0]) {
            //System.out.println("equal");
            return 0;
        }
        //System.out.println("Zahodish?");
        if ((first[0] >= second[0]) && (first[1] <= second[1])) {
            //System.out.println("lf");
            return 1;
            //влево вперёд
        }
        if ((first[0] >= second[0]) && (first[1] > second[1])) {
            //System.out.println("rf");
            return 2;
            //вправо вперёд
        }
        if ((first[0] < second[0]) && (first[1] <= second[1])) {
            //System.out.println("lb");
            return 3;
            //влево назад
        }
        if ((first[0] <= second[0]) && (first[1] > second[1])) {
            //System.out.println("rb");
            return 4;
            //вправо назад
        }

        //System.out.println("wtf");
        return 5;
    }

    HashSet<int[]> flank(Node firstNode) {
        HashSet<int[]> moves = new HashSet<>();
        if (firstNode != root) {
            moves.add(firstNode.key);
        }
        if (firstNode.lf != null)
            moves.addAll(flank(firstNode.lf));
        if (firstNode.rf != null)
            moves.addAll(flank(firstNode.rf));
        if (firstNode.lb != null)
            moves.addAll(flank(firstNode.lb));
        if (firstNode.rb != null)
            moves.addAll(flank(firstNode.rb));
        return moves;
    }

    HashSet<Node> anotherFlank(Node firstNode) {
        HashSet<Node> moves = new HashSet<>();

            moves.add(firstNode);

        if (firstNode.lf != null)
            moves.addAll(anotherFlank(firstNode.lf));
        if (firstNode.rf != null)
            moves.addAll(anotherFlank(firstNode.rf));
        if (firstNode.lb != null)
            moves.addAll(anotherFlank(firstNode.lb));
        if (firstNode.rb != null)
            moves.addAll(anotherFlank(firstNode.rb));
        return moves;
    }

    HashSet<int[]> getMoves() {
        return flank(root);
    }

    public ArrayList<Moving> getBranch(int[] k) {
        System.out.println("getBranch");
        Node x = root;
        ArrayList<Moving> listOfMoves = new ArrayList<>();
        while (x != null) {
//            if (x.value instanceof Moving) {
//                System.out.println(" Moving");
//                System.out.println(Integer.toString(x.value.oldCoordinates[0]) + ' ' + Integer.toString(x.value.oldCoordinates[1]));
//                System.out.println(Integer.toString(x.value.newCoordinates[0]) + ' ' + Integer.toString(x.value.newCoordinates[1]));
//            }
//            if (x.value instanceof Eating) {
//                System.out.println(" Eating");
//                System.out.println(Integer.toString(x.value.oldCoordinates[0]) + ' ' + Integer.toString(x.value.oldCoordinates[1]));
//                System.out.println(Integer.toString(x.value.newCoordinates[0]) + ' ' + Integer.toString(x.value.newCoordinates[1]));
//                System.out.println(Integer.toString(((Eating) x.value).coordinateOfAtePawn[0]) + " " + Integer.toString(((Eating) x.value).coordinateOfAtePawn[1]));
//            } else
//                System.out.println();
            int cmp = compareTo(k, x.key);
            if (cmp == 0) {
                listOfMoves.add(x.value);
                System.out.println("endOfgetBranch");
                return listOfMoves;
            }
            Moving moving = x.value;
            listOfMoves.add(moving);
            if (cmp == 1)
                x = x.lf;
            if (cmp == 2)
                x = x.rf;
            if (cmp == 3)
                x = x.lb;
            if (cmp == 4)
                x = x.rb;
        }
        //System.out.println("endOfgetBranch");
        return null;
    }

//    public boolean containsKey(int[] k) {
//        Node x = root;
//        while (x != null) {
//            int cmp = compareTo(k, x.key);
//            if (cmp == 0) {
//                return true;
//            }
//            if (cmp < 0) {
//                x = x.lf;
//            } else {
//                x = x.rf;
//            }
//        }
//        return false;
//    }
//
//    public Moving get(int[] k) {
//        Node x = root;
//        while (x != null) {
//            int cmp = compareTo(k, x.key);
//            if (cmp == 0) {
//                return x.value;
//            }
//            if (cmp < 0) {
//                x = x.lf;
//            } else {
//                x = x.rf;
//            }
//        }
//        return null;
//    }
//
//    void add(int[] k, Moving v) {
//        Node x = root, y = null;
//        while (x != null) {
//            int cmp = compareTo(k, x.key);
//            if (cmp == 0) {
//                x.value = v;
//                return;
//            } else {
//                y = x;
//                if (cmp < 0) {
//                    x = x.lf;
//                } else {
//                    x = x.rf;
//                }
//            }
//        }
//        Node newNode = new Node(k, v);
//        if (y == null) {
//            root = newNode;
//        } else {
//            if (compareTo(k, y.key) < 0) {
//                y.lf = newNode;
//            } else {
//                y.rf = newNode;
//            }
//        }
//    }
//
//    private static int compareTo(int[] first, int[] second) {
//        if (first[1] == second[1] && first[0] == second[0])
//            return 0;
//        else {
//            if ((first[1] < second[1]) || (first[0] > second[0]))
//                return -1;
//            else return 1;
//        }
//    }
//
//    ArrayList<int[]> flank(Node firstNode) {
//        ArrayList<int[]> moves = new ArrayList<>();
//        if (firstNode != root) {
//            moves.add(firstNode.key);
//        }
//        if (firstNode.lf != null)
//            moves.addAll(flank(firstNode.lf));
//        if (firstNode.rf != null)
//            moves.addAll(flank(firstNode.rf));
//        return moves;
//    }
//
//    ArrayList<int[]> getMoves() {
//        return flank(root);
//    }
//
//    public ArrayList<Moving> getBranch(int[] k) {
//        System.out.println("getBranch");
//        Node x = root;
//        ArrayList<Moving> listOfMoves = new ArrayList<>();
//        while (x != null) {
//            if (x.value instanceof Moving) {
//                System.out.println(" Moving");
//                System.out.println(Integer.toString(x.value.oldCoordinates[0]) + ' ' + Integer.toString(x.value.oldCoordinates[1]));
//                System.out.println(Integer.toString(x.value.newCoordinates[0]) + ' ' + Integer.toString(x.value.newCoordinates[1]));
//            }
//            if (x.value instanceof Eating) {
//                System.out.println(" Eating");
//                System.out.println(Integer.toString(x.value.oldCoordinates[0]) + ' ' + Integer.toString(x.value.oldCoordinates[1]));
//                System.out.println(Integer.toString(x.value.newCoordinates[0]) + ' ' + Integer.toString(x.value.newCoordinates[1]));
//                System.out.println(Integer.toString(((Eating) x.value).coordinateOfAtePawn[0]) + " " + Integer.toString(((Eating) x.value).coordinateOfAtePawn[1]));
//            } else
//                System.out.println();
//            int cmp = compareTo(k, x.key);
//            if (cmp == 0) {
//                listOfMoves.add(x.value);
//                System.out.println("endOfgetBranch");
//                return listOfMoves;
//            }
//            Moving moving = x.value;
//            listOfMoves.add(moving);
//            if (cmp < 0) {
//                x = x.lf;
//            } else {
//                x = x.rf;
//            }
//        }
//        //System.out.println("endOfgetBranch");
//        return null;
//    }

}