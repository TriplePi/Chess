import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.NoSuchElementException;

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
        for (Chessman[] y : collocation.getChessField()) {
            for (Chessman x : y) {
                if (x != null)
                    if (x.getColour() == colour) {
                        enemy.add(x);
                    }
            }

        }

        HashMap<Integer, ArrayList<Moving>> bestBranches = new HashMap<>();

        for (Chessman one : enemy) {
            collocation.activeChessman = one;
            one.actions = new LiteralTree(one.coordinate);
            one.computePossibleMove();
            HashMap<ArrayList<Moving>, Integer> allBranches = new HashMap<>();
            if (one.actions.getAllBranches() != null)
                for (ArrayList<Moving> oneBranch : one.actions.getAllBranches()) {
                    int rate = 0;
                    for (Moving moving : oneBranch) {
                        if (moving instanceof Eating) {
                            rate += 2;
                        }
                    }
                    int[] lastMove = oneBranch.get(oneBranch.size()).newCoordinates;
                    int buff = 0;
                    while (buff == 0) {
                        if (collocation.getChessman(Chessman.changeCoordinates(lastMove, 1, 1)) != null && collocation.getChessman(Chessman.changeCoordinates(lastMove, -1, -1)) == null) {
                            buff -= 2;
                            continue;
                        }
                        if (collocation.getChessman(Chessman.changeCoordinates(lastMove, 1, -1)) != null && collocation.getChessman(Chessman.changeCoordinates(lastMove, -1, 1)) == null) {
                            buff -= 2;
                            continue;
                        }
                        if (collocation.getChessman(Chessman.changeCoordinates(lastMove, 1, 1)) == null && collocation.getChessman(Chessman.changeCoordinates(lastMove, -1, -1)) != null) {
                            buff -= 2;
                            continue;
                        }
                        if (collocation.getChessman(Chessman.changeCoordinates(lastMove, 1, -1)) == null && collocation.getChessman(Chessman.changeCoordinates(lastMove, -1, 1)) != null) {
                            buff -= 2;
                            continue;
                        }
                    }
                    rate += buff;
                    if (rate == 0) {
                        rate++;
                    }
                    allBranches.put(oneBranch, rate);
                }
            int maxRate = 0;
            try {
                maxRate = Collections.max(allBranches.values());
            }
            catch (NoSuchElementException e){
                maxRate = (int)allBranches.values().toArray()[0];
            }

            for (ArrayList<Moving> branch : allBranches.keySet()) {
                if (allBranches.get(branch) == maxRate) {
                    bestBranches.put(maxRate, branch);
                    break;
                }
            }
        }
        int maxRate = Collections.max(bestBranches.keySet());
        bestBranches.get(maxRate).forEach(Moving::doing);
        //return collocation;
    }
}
