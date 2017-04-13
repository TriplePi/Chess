import java.util.*;

/**
 * Created by TriplePi on 28.03.2017.
 */
public class AI {
    Collocation collocation;
    boolean colour;

    AI(Collocation collocation, boolean colour) {
        this.collocation = collocation;
        this.colour = colour;
    }

    void colculate() {
        ArrayList<Chessman> enemy = new ArrayList<>();
        for (Chessman[] y : collocation.getChessField())
            for (Chessman x : y)
                if (x != null)
                    if (x.getColour() == colour) {
                        enemy.add(x);
                    }
        ArrayList<ArrayList<Moving>> bestBranches = new ArrayList<>();
        ArrayList<Integer> bestRates = new ArrayList<>();
        //System.out.println("qqq");
        for (Chessman one : enemy) {
            collocation.activeChessman = one;
            one.actions = new LiteralTree(one.coordinate);
            //System.out.println(Arrays.toString(one.coordinate));
            one.computePossibleMove(collocation);
            HashMap<ArrayList<Moving>, Integer> allBranches = new HashMap<>();
            if (one.actions.getAllBranches() != null && one.actions.getAllBranches().size() != 0) {
                //System.out.println("zzz");
                for (ArrayList<Moving> oneBranch : one.actions.getAllBranches()) {
                    int rate = 0;
                    for (Moving moving : oneBranch) {
                        if (moving instanceof Eating) {
                            rate += 4;
                        }
                    }
                    int[] lastMoveNew = oneBranch.get(oneBranch.size() - 1).newCoordinates;
                    int[] lastMoveOld = oneBranch.get(oneBranch.size() - 1).oldCoordinates;
                    //System.out.println(Arrays.toString(oneBranch.get(0).oldCoordinates)+" "+Arrays.toString(lastMove));
                    int buff = 0;
                    for (int i = 0; i < 4; i++) {
                        //System.out.println(Arrays.toString(lastMoveNew));
                        //System.out.println(Arrays.toString(Chessman.changeCoordinates(lastMoveNew, 1, 1)) + " " + Arrays.toString(Chessman.changeCoordinates(lastMoveNew, -1, -1)));
                        try {
                            if (collocation.getChessman(Chessman.changeCoordinates(lastMoveNew, 1, 1)) != null && collocation.getChessman(Chessman.changeCoordinates(lastMoveNew, 1, 1)).getColour() != this.colour) {
                                System.out.println("1");
                                if (collocation.getChessman(Chessman.changeCoordinates(lastMoveNew, -1, -1)) == null || Arrays.equals(Chessman.changeCoordinates(lastMoveNew, -1, -1), lastMoveOld)) {
                                    //System.out.println(Arrays.toString(lastMoveNew));
                                    buff -= 2;
                                    //System.out.println("11");
                                    break;
                                }
                            }
                        } catch (Exception e) {
                            System.out.println(e.toString());
                        }
                        try {
                            if (collocation.getChessman(Chessman.changeCoordinates(lastMoveNew, 1, -1)) != null && collocation.getChessman(Chessman.changeCoordinates(lastMoveNew, 1, -1)).getColour() != this.colour) {
                                if (collocation.getChessman(Chessman.changeCoordinates(lastMoveNew, -1, 1)) == null || Arrays.equals(Chessman.changeCoordinates(lastMoveNew, -1, 1), lastMoveOld)) {
                                    //System.out.println(Arrays.toString(lastMoveNew));
                                    buff -= 2;
                                    //System.out.println("22");
                                    break;
                                }
                            }
                        } catch (Exception e) {
                            System.out.println(e.toString());
                        }
                        try {
                            if (collocation.getChessman(Chessman.changeCoordinates(lastMoveNew, -1, -1)) != null && collocation.getChessman(Chessman.changeCoordinates(lastMoveNew, -1, -1)).getColour() != this.colour)
                                if (collocation.getChessman(Chessman.changeCoordinates(lastMoveNew, 1, 1)) == null || Arrays.equals(Chessman.changeCoordinates(lastMoveNew, 1, 1), lastMoveOld)) {
                                    //System.out.println(Arrays.toString(lastMoveNew));
                                    buff -= 2;
                                    //System.out.println("33");
                                    break;
                                }
                        } catch (Exception e) {
                            System.out.println(e.toString());
                        }
                        try {
                            if (collocation.getChessman(Chessman.changeCoordinates(lastMoveNew, -1, 1)) != null && collocation.getChessman(Chessman.changeCoordinates(lastMoveNew, -1, 1)).getColour() != this.colour) {
                                if (collocation.getChessman(Chessman.changeCoordinates(lastMoveNew, 1, -1)) == null || Arrays.equals(Chessman.changeCoordinates(lastMoveNew, 1, -1), lastMoveOld)) {
                                    //System.out.println(Arrays.toString(lastMoveNew));
                                    buff -= 2;
                                    //System.out.println("44");
                                    break;
                                }
                            }
                        } catch (Exception e) {
                            System.out.println(e.toString());
                        }
                    }
                    rate += buff;
                    if (rate == 0) {
                        rate++;
                    }
                    allBranches.put(oneBranch, rate);
                    //System.out.println(rate + " rate");
                }
                int maxRate = 0;
                try {
                    maxRate = Collections.max(allBranches.values());
                } catch (NoSuchElementException e) {
                    maxRate = (int) allBranches.values().toArray()[0];
                }
                //System.out.println(maxRate+" max");
                for (ArrayList<Moving> branch : allBranches.keySet()) {
                    if (allBranches.get(branch) == maxRate) {
                        bestBranches.add(branch);
                        bestRates.add(maxRate);
                        break;
                    }
                }
            }
        }
        int maxRate = Collections.max(bestRates);
        ArrayList<ArrayList<Moving>> bestMoves = new ArrayList<>();
        for (int i = 0; i < bestRates.size(); i++) {
            if (bestRates.get(i) == maxRate)
                bestMoves.add(bestBranches.get(i));
        }
        Random random = new Random();
        ArrayList<Moving> bestMove = bestMoves.get(random.nextInt(bestMoves.size()));
        bestMove.forEach(Moving::doing);
        Collocation.logging(bestMove);
        //return collocation;
    }
}
