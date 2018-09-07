package com.codewars.chrisgw.reference.kyu_5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * John and Mary want to travel between a few towns A, B, C ... Mary has on a sheet of paper a list of distances between these towns.
 * `ls = [50, 55, 57, 58, 60]`.
 * John is tired of driving and he says to Mary that he doesn't want to drive more than `t = 174 miles` and he
 * will visit only `3` towns.
 * <p>
 * Which distances, hence which towns, they will choose so that the sum of the distances is the biggest possible
 * - to please Mary - but less than `t` - to please John- ?
 * <p>
 * Example:
 * <p>
 * With list `ls` and 3 towns to visit they can make a choice between:
 * `[50,55,57],[50,55,58],[50,55,60],[50,57,58],[50,57,60],[50,58,60],[55,57,58],[55,57,60],[55,58,60],[57,58,60]`.
 * <p>
 * The sums of distances are then:
 * `162, 163, 165, 165, 167, 168, 170, 172, 173, 175`.
 * <p>
 * The biggest possible sum taking a limit of `174` into account is then `173` and the distances of the `3`
 * corresponding towns is `[55, 58, 60]`.
 * <p>
 * The function `chooseBestSum` (or `choose_best_sum` or ... depending on the language) will take as parameters `t` (maximum sum of distances, integer >= 0), `k` (number of towns to visit, k >= 1)
 * and `ls` (list of distances, all distances are positive or null integers and this list has at least one element).
 * The function returns the "best" sum ie the biggest possible sum of `k` distances less than or equal to the given limit `t`, if that sum exists,
 * or otherwise nil, null, None, Nothing, depending on the language. With C++, C, Rust, Swift, Go, Kotlin return `-1`.
 * <p>
 * Examples:
 * <p>
 * `ts = [50, 55, 56, 57, 58]`
 * `choose_best_sum(163, 3, ts) -> 163`
 * <p>
 * `xs = [50]`
 * `choose_best_sum(163, 3, xs) -> nil (or null or ... or -1 (C++, C, Rust, Swift, Go)`
 * <p>
 * `ys = [91, 74, 73, 85, 73, 81, 87]`
 * `choose_best_sum(230, 3, ys) -> 228`
 */
public class BestTravel implements Comparable<BestTravel> {

    private int maxMiles;
    private int len;
    private int startPosition;
    private List<Integer> arr;
    private int[] result;


    public BestTravel(int maxMiles, int len, List<Integer> arr) {
        this.maxMiles = maxMiles;
        this.len = len;
        this.startPosition = 0;
        this.arr = new ArrayList<>(arr);
        this.result = new int[len];
    }


    public int getTravelMiles() {
        return Arrays.stream(result).sum();
    }


    @Override
    public int compareTo(BestTravel o) {
        return Integer.compare(getTravelMiles(), o.getTravelMiles());
    }


    public static Integer chooseBestSum(int maxMiles, int wishedTownCount, List<Integer> aviableTownDistances) {
        if (aviableTownDistances.size() < wishedTownCount) {
            return null;
        }
        BestTravel currentTravel = new BestTravel(maxMiles, wishedTownCount, aviableTownDistances);
        return chooseBestTravelMiles(currentTravel, currentTravel).getTravelMiles();
    }

    private static BestTravel chooseBestTravelMiles(BestTravel bestTravel, BestTravel currentTravel) {
        if (currentTravel.len == 0) {
            if (currentTravel.getTravelMiles() <= currentTravel.maxMiles && currentTravel.compareTo(bestTravel) > 0) {
                bestTravel = currentTravel;
                System.out.println(Arrays.toString(bestTravel.result));
            }
            return bestTravel;
        }
        for (int i = currentTravel.startPosition; i <= currentTravel.arr.size() - currentTravel.len; i++) {
            BestTravel nextTravel = new BestTravel(currentTravel.maxMiles, currentTravel.len - 1, currentTravel.arr);
            nextTravel.result = currentTravel.result;
            nextTravel.startPosition = currentTravel.startPosition + 1;

            int nextTownDistance = currentTravel.arr.get(i);
            int index = currentTravel.result.length - currentTravel.len;
            nextTravel.result[index] = nextTownDistance;
            bestTravel = chooseBestTravelMiles(bestTravel, nextTravel);
        }
        return bestTravel;
    }


}
