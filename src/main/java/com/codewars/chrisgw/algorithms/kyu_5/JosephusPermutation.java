package com.codewars.chrisgw.algorithms.kyu_5;

import java.util.LinkedList;
import java.util.List;


/**
 * This problem takes its name by arguably the most important event in the life of the ancient historian Josephus: according to his tale, he and his 40 soldiers were trapped in a cave by the Romans during a siege.
 * <p>
 * Refusing to surrender to the enemy, they instead opted for mass suicide, with a twist: **they formed a circle and proceeded to kill one man every three, until one last man was left (and that it was supposed to kill himself to end the act)**.
 * <p>
 * Well, Josephus and another man were the last two and, as we now know every detail of the story, you may have correctly guessed that they didn't exactly follow through the original idea.
 * <p>
 * You are now to create a function that returns a Josephus permutation, taking as parameters the initial *array/list of items* to be permuted as if they were in a circle and counted out every *k* places until none remained.
 * <p>
 * **Tips and notes:** it helps to start counting from 1 up to n, instead of the usual range 0..n-1; k will always be >=1.
 * <p>
 * For example, with n=7 and k=3 `josephus(7,3)` should act this way.
 * ```
 * [1,2,3,4,5,6,7] - initial sequence
 * [1,2,4,5,6,7] => 3 is counted out and goes into the result [3]
 * [1,2,4,5,7] => 6 is counted out and goes into the result [3,6]
 * [1,4,5,7] => 2 is counted out and goes into the result [3,6,2]
 * [1,4,5] => 7 is counted out and goes into the result [3,6,2,7]
 * [1,4] => 5 is counted out and goes into the result [3,6,2,7,5]
 * [4] => 1 is counted out and goes into the result [3,6,2,7,5,1]
 * [] => 4 is counted out and goes into the result [3,6,2,7,5,1,4]
 * ```
 * So our final result is:
 * ```
 * josephus([1,2,3,4,5,6,7],3)==[3,6,2,7,5,1,4]
 * ```
 * For more info, browse the <a href="http://en.wikipedia.org/wiki/Josephus_problem" target="_blank">Josephus Permutation</a> page on wikipedia; related kata: <a href="http://www.codewars.com/kata/josephus-survivor" target="_blank" title="Josephus sequence - last element">Josephus Survivor</a>.
 */
public class JosephusPermutation {


    public static <T> List<T> josephusPermutation(final List<T> soldiers, final int k) {
        List<T> soldierKillingSequence = new LinkedList<>();
        boolean[] killedSoldierIndexes = new boolean[soldiers.size()];

        int killingIndex = 0;
        for (int i = 0; i < soldiers.size(); i++) {
            killingIndex = nextAliveSoldierIndex(killedSoldierIndexes, k, killingIndex);
            T killedSoldier = soldiers.get(killingIndex - 1);
            killedSoldierIndexes[killingIndex - 1] = true;
            soldierKillingSequence.add(killedSoldier);
        }
        return soldierKillingSequence;
    }

    private static int nextAliveSoldierIndex(boolean[] killedSoldierIndexes, int k, int killingIndex) {
        for (int i = 0; i < k; i++) {
            do {
                if (++killingIndex > killedSoldierIndexes.length) {
                    killingIndex = 1;
                }
            } while (killedSoldierIndexes[killingIndex - 1]);
        }
        return killingIndex;
    }

}
