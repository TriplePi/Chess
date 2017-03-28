/**
 * Created by TriplePi on 26.03.2017.
 */

import javax.swing.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by TriplePi on 21.03.2017.
 */
class LiteralTree {
    static class Node {
        String key;
        int[] finishCoordinates;
        Moving value;
        Node lt, rt, lb, rb, batya;

        Node(String key, Moving value, Node batya) {
            this.batya = batya;
            this.key = key;
            this.value = value;
            this.finishCoordinates = value.newCoordinates;
        }

        Node(String key, Moving value, int[] finishCoordinates, Node batya) {
            this.batya = batya;
            this.key = key;
            this.value = value;
            this.finishCoordinates = finishCoordinates;
        }
    }

    LiteralTree(int[] coordinates) {
        this.root = new Node("", null, coordinates, null);
    }

    boolean containsAction(Moving moving) {
        HashSet<Moving> allMoves = oneMoreFlank(root);
        if (allMoves.contains(moving)) {
            return true;
        } else return false;
    }

    boolean containsAction(int[] oldCoordinate,int[] newCoordinate){
        HashSet<Moving> allMoves = oneMoreFlank(root);
        for (Moving moving: allMoves) {
            if(Arrays.equals(moving.oldCoordinates,oldCoordinate)&&(Arrays.equals(moving.newCoordinates,newCoordinate )))
                return true;
        }
        return false;
    }

    public Node root = null;

    void add(Moving v) {
        //System.out.println();
        //System.out.println(Arrays.toString(v.newCoordinates));
        HashSet<Node> allNodes = anotherFlank(root);
        if (containsAction(v))
            return;
        for (Node node : allNodes) {
            int[] oldNodesCoord = node.finishCoordinates;
            int[] newNodeFinishCoord = v.newCoordinates;
            int[] newNodeOldCoordinates = v.oldCoordinates;
            if (Arrays.equals(oldNodesCoord, newNodeOldCoordinates)) {
                //System.out.println("Zahodish?");
                String cmp = compareTo(oldNodesCoord, newNodeFinishCoord);
                Node newNode = new Node(node.key + cmp, v, node);
                //System.out.println("cmp" + cmp);
                switch (cmp) {
                    case "lt":
                        //System.out.println("lt");
                        node.lt = newNode;
                        break;
                    case "rt":
                        //System.out.println("rt");
                        node.rt = newNode;
                        break;
                    case "lb":
                        node.lb = newNode;
                        break;
                    case "rb":
                        System.out.println("???");
                        node.rb = newNode;
                        break;
                }
                break;
            }
        }
    }

    public static String compareTo(int[] first, int[] second) {
        if (first[1] == second[1] && first[0] == second[0]) {
            return "";
        }
        //System.out.println("Zahodish?");
        if ((first[0] > second[0]) && (first[1] < second[1])) {
            return "rt";
            //влево вперёд
        }
        if ((first[0] > second[0]) && (first[1] > second[1])) {
            return "lt";
            //вправо вперёд
        }
        if ((first[0] < second[0]) && (first[1] < second[1])) {
            return "rb";
            //влево назад
        }
        if ((first[0] < second[0]) && (first[1] > second[1])) {
            return "lb";
            //вправо назад
        }
        return "fuck";
    }

    HashMap<int[], String> flank(Node firstNode) {
        HashMap<int[], String> moves = new HashMap<>();
//        if (firstNode != root)
//            moves.put(firstNode.finishCoordinates, firstNode.key);
//        if (firstNode.lt != null)
//            moves.putAll(flank(firstNode.lt));
//        if (firstNode.rt != null)
//            moves.putAll(flank(firstNode.rt));
//        if (firstNode.lb != null)
//            moves.putAll(flank(firstNode.lb));
//        if (firstNode.rb != null)
//            moves.putAll(flank(firstNode.rb));
//        return moves;
        HashSet<Node> allNodes = anotherFlank(root);
        for (Node node : allNodes) {
            if (node.batya != null) {
//                System.out.println(node.key);
//                System.out.println(Arrays.toString(node.finishCoordinates));
                moves.put(node.finishCoordinates, node.key);
            }
        }
        return moves;
    }

    HashSet<Node> anotherFlank(Node firstNode) {
        HashSet<Node> moves = new HashSet<>();
        moves.add(firstNode);
        if (firstNode.lt != null)
            moves.addAll(anotherFlank(firstNode.lt));
        if (firstNode.rt != null)
            moves.addAll(anotherFlank(firstNode.rt));
        if (firstNode.lb != null)
            moves.addAll(anotherFlank(firstNode.lb));
        if (firstNode.rb != null)
            moves.addAll(anotherFlank(firstNode.rb));
        return moves;
    }

    HashSet<Moving> oneMoreFlank(Node firstNode) {
        HashSet<Moving> moves = new HashSet<>();
        if(firstNode.batya!= null)
            moves.add(firstNode.value);
        if (firstNode.lt != null)
            moves.addAll(oneMoreFlank(firstNode.lt));
        if (firstNode.rt != null)
            moves.addAll(oneMoreFlank(firstNode.rt));
        if (firstNode.lb != null)
            moves.addAll(oneMoreFlank(firstNode.lb));
        if (firstNode.rb != null)
            moves.addAll(oneMoreFlank(firstNode.rb));
        return moves;
    }

    HashMap<int[], String> getMoves() {
        return flank(root);
    }

    public ArrayList<Moving> getBranch(String way) {
        //System.out.println("qqq"+way);
        String[] keys = new String[way.length() / 2];
        ArrayList<Moving> branch = new ArrayList<>();
        for (int i = 0; i < way.length(); i += 2) {
            keys[i / 2] = Character.toString(way.charAt(i)) + Character.toString(way.charAt(i + 1));
        }
        Node x = root;
        for (String key : keys) {
            switch (key) {
                case "lt":
                    branch.add(x.lt.value);
                    x = x.lt;
                    break;
                case "rt":
                    branch.add(x.rt.value);
                    x = x.rt;
                    break;
                case "lb":
                    branch.add(x.lb.value);
                    x = x.lb;
                    break;
                case "rb":
                    branch.add(x.rb.value);
                    x = x.rb;
                    break;
            }
        }
        return branch;
    }

    ArrayList<Moving> getBranchByCoord(int[] coord) {
        HashMap<int[],String> map = flank(root);
        for (int[] key : map.keySet()) {
            if (Arrays.equals(coord,key))
                return getBranch(map.get(key));
        }
        return getBranch(flank(root).get(coord));
    }
}