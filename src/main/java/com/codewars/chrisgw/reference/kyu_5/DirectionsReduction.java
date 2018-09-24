package com.codewars.chrisgw.reference.kyu_5;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;


/**
 * # Once upon a time, on a way through the old wild west,…
 * <p>
 * … a man was given directions to go from one point to another. The directions were "NORTH", "SOUTH", "WEST", "EAST". Clearly "NORTH" and "SOUTH" are opposite, "WEST" and "EAST" too. Going to one direction and coming back the opposite direction is a needless effort. Since this is the wild west, with dreadfull weather and not much water, it's important to save yourself some energy, otherwise you might die of thirst!
 * <p>
 * ## How I crossed the desert the smart way.
 * <p>
 * The directions given to the man are, for example, the following:
 * <p>
 * ```
 * ["NORTH", "SOUTH", "SOUTH", "EAST", "WEST", "NORTH", "WEST"].
 * ```
 * <p>
 * or
 * <p>
 * ```
 * { "NORTH", "SOUTH", "SOUTH", "EAST", "WEST", "NORTH", "WEST" };
 * ```
 * <p>
 * or (haskell)
 * <p>
 * ```
 * [North, South, South, East, West, North, West]
 * ```
 * <p>
 * You can immediatly see that going "NORTH" and then "SOUTH" is not reasonable, better stay to the same place!
 * So the task is to give to the man a simplified version of the plan. A better plan in this case is simply:
 * <p>
 * ```
 * ["WEST"]
 * ```
 * <p>
 * or
 * <p>
 * ```
 * { "WEST" }
 * ```
 * <p>
 * or (haskell)
 * <p>
 * ```
 * [West]
 * ```
 * <p>
 * or (rust)
 * <p>
 * ```
 * [WEST];
 * ```
 * <p>
 * # Other examples:
 * <p>
 * In `["NORTH", "SOUTH", "EAST", "WEST"]`, the direction `"NORTH" + "SOUTH"` is going north and coming back *right away*. What a waste of time! Better to do nothing.
 * <p>
 * The path becomes `["EAST", "WEST"]`, now `"EAST"` and `"WEST"` annihilate each other, therefore, the final result is `[]` (nil in Clojure).
 * <p>
 * In ["NORTH", "EAST", "WEST", "SOUTH", "WEST", "WEST"], "NORTH" and "SOUTH" are not directly opposite but they become directly opposite after the reduction of "EAST" and "WEST" so the whole path is reducible to ["WEST", "WEST"].
 * <p>
 * # Task
 * <p>
 * Write a function `dirReduc` which will take an array of strings and returns an array of strings with the needless directions removed (W<->E or S<->N side by side).
 * <p>
 * The Haskell version takes a list of directions with `data Direction = North | East | West | South`.
 * The Clojure version returns nil when the path is reduced to nothing.
 * The Rust version takes a slice of `enum Direction {NORTH, SOUTH, EAST, WEST}`.
 * <p>
 * # Examples
 * <p>
 * # See more examples in "Example Tests"
 * <p>
 * # Note
 * <p>
 * Not all paths can be made simpler.
 * The path ["NORTH", "WEST", "SOUTH", "EAST"] is not reducible. "NORTH" and "WEST", "WEST" and "SOUTH", "SOUTH" and "EAST" are not directly opposite of each other and can't become such. Hence the result path is itself : ["NORTH", "WEST", "SOUTH", "EAST"].
 */
public class DirectionsReduction {

    public static String[] dirReduc(String[] arr) {
        System.out.println(Arrays.toString(arr));
        List<Direction> directions = Arrays.stream(arr)
                .map(Direction::valueOf)
                .collect(ReducedDirectionList::new, ReducedDirectionList::add, ReducedDirectionList::addAll);
        System.out.println(directions);
        return directions.stream().map(Direction::toString).toArray(String[]::new);
    }

    private static class ReducedDirectionList extends LinkedList<Direction> {

        @Override
        public boolean add(Direction direction) {
            if (isLastDirectionOppositeTo(direction)) {
                removeLast();
            } else {
                addLast(direction);
            }
            return true;
        }

        public boolean isLastDirectionOppositeTo(Direction direction) {
            return !isEmpty() && getLast().isOppositeDirection(direction);
        }

        @Override
        public boolean addAll(Collection<? extends Direction> otherDirectionCollection) {
            boolean changed = false;
            for (Direction direction : otherDirectionCollection) {
                changed |= add(direction);
            }
            return changed;
        }

    }


    private enum Direction {

        NORTH, WEST, SOUTH, EAST; /* in clockwise order */

        public Direction getOppositeDirection() {
            Direction[] directions = values();
            int oppositeDirectionOrdinal = this.ordinal() + directions.length / 2;
            return directions[oppositeDirectionOrdinal % directions.length];
        }

        public boolean isOppositeDirection(Direction otherDirection) {
            return this.getOppositeDirection().equals(otherDirection);
        }

    }

}
