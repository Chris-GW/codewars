package com.codewars.chrisgw.algorithms.kyu_3;

import java.awt.Point;
import java.util.Arrays;
import java.util.stream.Collectors;

import static java.util.Objects.requireNonNull;


/**
 * Your task, is to create a NxN spiral with a given `size`.
 * <p>
 * For example, spiral with size 5 should look like this:
 * <p>
 * ```
 * 00000
 * ....0
 * 000.0
 * 0...0
 * 00000
 * ```
 * <p>
 * and with the size 10:
 * <p>
 * ```
 * 0000000000
 * .........0
 * 00000000.0
 * 0......0.0
 * 0.0000.0.0
 * 0.0..0.0.0
 * 0.0....0.0
 * 0.000000.0
 * 0........0
 * 0000000000
 * ```
 * <p>
 * Return value should contain array of arrays, of `0` and `1`, for example for given size `5` result should be:
 * ```
 * [[1,1,1,1,1],[0,0,0,0,1],[1,1,1,0,1],[1,0,0,0,1],[1,1,1,1,1]]
 * ```
 * <p>
 * Because of the edge-cases for tiny spirals, the size will be at least 5.
 * <p>
 * General rule-of-a-thumb is, that the snake made with '1' cannot touch to itself.
 */
public class MakeASpiral {

    int[][] spiral;
    Point min;
    Point max;

    Direction direction;
    Point position;


    public MakeASpiral(int size) {
        this.spiral = new int[size][size];
        this.min = new Point(0, 0);
        this.max = new Point(size, size);

        this.direction = Direction.E;
        this.position = new Point(0, 0);
        markSpiralPositions();
        System.out.println(this);
    }

    public static int[][] spiralize(int size) {
        return new MakeASpiral(size).spiral;
    }


    private void markSpiralPositions() {
        markPosition();
        while (!(min.x >= max.x && min.y >= max.y)) {
            Point nextPosition = nextPosition();
            while (canMoveTo(nextPosition)) {
                this.position = nextPosition;
                markPosition();
                nextPosition = nextPosition();
            }

            adjustMinMaxBounds();
            direction = direction.nextClockwiseDirection();
            System.out.println(this);
            System.out.println();
        }

        int size = spiral.length;
        if (size > 3 && size % 2 == 0) {
            spiral[size / 2][size / 2 - 1] = 1;
        }
    }

    private Point nextPosition() {
        Point nextPosition = new Point(position);
        nextPosition.translate(direction.dx(), direction.dy());
        return nextPosition;
    }

    private void adjustMinMaxBounds() {
        switch (direction) {
        case N:
            min.translate(1, 0);
            max.translate(0, -1);
            break;
        case E:
            min.translate(0, 1);
            if (min.x > 0) {
                min.translate(1, 0);
            }
            break;
        case S:
            max.translate(-1, 0);
            min.translate(0, 1);
            break;
        case W:
            max.translate(0, -1);
            max.translate(-1, 0);
            break;
        }
    }

    private boolean canMoveTo(Point position) {
        boolean isInLowerBound = min.x <= position.x && min.y <= position.y;
        return isInLowerBound && max.x > position.x && max.y > position.y;
    }

    private void markPosition() {
        spiral[position.y][position.x] = 1;
    }


    @Override
    public String toString() {
        return Arrays.stream(spiral).map(Arrays::toString).collect(Collectors.joining("\n"));
    }


    enum Direction {

        N(new Point(0, -1)), //
        E(new Point(1, 0)), //
        S(new Point(0, 1)), //
        W(new Point(-1, 0)); // /* in clockwise order */

        private Point direction;

        Direction(Point direction) {
            this.direction = requireNonNull(direction);
        }

        public Direction nextClockwiseDirection() {
            Direction[] directions = Direction.values();
            int nextOrdinal = this.ordinal() + 1;
            if (nextOrdinal >= directions.length) {
                nextOrdinal = 0;
            }
            return directions[nextOrdinal];
        }

        public int dx() {
            return direction.x;
        }

        public int dy() {
            return direction.y;
        }

    }

}
