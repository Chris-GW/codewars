package com.codewars.chrisgw.games.kyu_2;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * <!--Transforming Maze Solver-->
 * The objective of this kata will be to guide a ball through an `m` x `n` rectangular maze. This maze is special in that:
 * - all the cells rotate `90` degrees clockwise, in unison, at each interval
 * - each cell can have anywhere from `0` to `4` walls
 *
 * <p>Your goal is to write a function that returns a path that requires the fewest intervals for the ball to get from its starting position to a specified destination.</p>
 *
 *
 * <h2 style='color:#f88'>Input</h2>
 * <p>Your function will receive one argument — an `m` x `n` matrix.</p>
 *
 *
 * <h2 style='color:#f88'>Output</h2>
 * <p>Your function must return a path as an array/list of strings. Each string in the array will:</p>
 * <ul>
 * <li>consist only of the letters `N`, `W`, `S`, and `E` representing the directions `north`, `west`, `south`, and `east`, respectively</li>
 * <li>represent the path of the ball at each interval (based on its index position in the array)</li>
 * </ul>
 *
 * <p>Also note that empty strings are permitted in the output array.</br>
 * If there is no possible solution, return `null` or `None`.</p>
 *
 *
 * <h2 style='color:#f88'>Maze Mechanics</h2>
 * <p>Each cell in the maze is given as an integer, ranging from `0` to `15`. This number, when translated to binary form, represents the walls of the corresponding cell. That is, a `1` means there is a wall and a `0` means there is no wall. The order of the walls is north, west, south, and east.</p>
 *
 * <p>For example, a cell with the value `7` is `0111` in binary. This means it has 3 walls — initially on the west, south, and east sides. Since there is no wall on the north side of the cell, it can be entered from that side at time interval `0` (assuming that the adjacent cell to its north does not have a south wall).</p>
 *
 * <p>A cell with the value `5` (`0101` in binary) can be entered from its north and south sides at interval `0`. At the next interval, it rotates `90` degrees clockwise and can then only be entered from its west and east sides (`1010`).</p>
 *
 * <p>A cell with the value `15` is enclosed on all sides (`1111` in binary) and therefore can never be entered. Likewise, a cell with a value of `0` can always be entered from any side.</p>
 *
 * <p><b style='color:#8df'>There will be</b> `2` <b style='color:#8df'>cells that will not be given in the form of an integer.</b> Assume that these cells have no walls (the equivalent of a `0` cell):</p>
 * <ul>
 * <li>The ball's starting position, given as the letter `B` (Java: `-1`)</li>
 * <li>The destination, given as the letter `X` (Java: `-2`)</li>
 * </ul>
 *
 *
 * <h2 style='color:#f88'>Test Example</h2>
 *
 * <img src='https://i.imgur.com/N1D2rcI.png'/>
 * <p>The image above shows the state of the maze and starting position of the ball at each each interval; the order is given in the bottom right square.</br>
 * The green shaded area shows the available cells the ball can move to at each interval, but the bold number shows where it ends up (for our example solution)
 * </p>
 *
 * <h2 style='color:#f88'>Technical Details</h2>
 * <ul>
 * <li>The width and length of the matrix will range between `4` and `25` in either direction</li>
 * <li>The ball's starting position will always be on the west side and the exit will always be on the east side of the maze</li>
 * <li>For the sake of efficiency, the ball must not enter the same cell more than once per interval</li>
 * <li>Full Test Suite: `10` fixed tests, `110` random tests (Java: `200` random tests)</li>
 * <li>Each test case may have `0` or more possible solutions</li>
 * <li>Inputs will always be valid</li>
 * <li>Use Python 3+ for the Python translation</li>
 * <li>For JavaScript, `require` has been disabled and most built-in prototypes have been frozen (prototype methods can be added to `Array` and `Function`)</li>
 * </ul>
 *
 * <p>If you enjoyed this kata, be sure to check out <a href='https://www.codewars.com/users/docgunthrop/authored' style='color:#9f9;text-decoration:none'>my other katas</a></p>
 */
public class MazeSolver {

    public static final int MAZE_BALL_CODE = -1;
    public static final int MAZE_EXIT_CODE = -2;

    private MazeCell[][][] maze;
    private MazeCell startCell;
    private MazeCell exitCell;


    public MazeSolver(int[][] maze) {
        this.maze = new MazeCell[maze.length][][];
        for (int y = 0; y < maze.length; y++) {
            int[] mazeRow = maze[y];
            this.maze[y] = new MazeCell[mazeRow.length][];

            for (int x = 0; x < mazeRow.length; x++) {
                setCell(maze, x, y);
            }
        }
    }

    private void setCell(int[][] mazeCellCode1, int x, int y) {
        int mazeCellCode = mazeCellCode1[y][x];
        MazeCell mazeCell = new MazeCell(x, y, 0, mazeCellCode);
        if (mazeCellCode == MAZE_BALL_CODE) {
            this.startCell = mazeCell;
        } else if (mazeCellCode == MAZE_EXIT_CODE) {
            this.exitCell = mazeCell;
        }

        this.maze[y][x] = new MazeCell[4];
        for (int interval = 0; interval < 4; interval++) {
            this.maze[y][x][interval] = mazeCell;
            mazeCell = mazeCell.newRightRotatedCell();
        }
    }

    public MazeCell getMazeCell(int x, int y, int interval) {
        if (0 <= y && y < maze.length && 0 <= x && x < maze[y].length) {
            return maze[y][x][interval % 4];
        } else {
            return null;
        }
    }


    public int length() {
        return maze.length;
    }

    public int width() {
        return maze[0].length;
    }

    private int size() {
        return length() * width() * maze[0][0].length;
    }


    public List<String> solve() {
        long startTime = System.nanoTime();
        startCell.intervalPathLength = 0;
        startCell.ballPathLength = 0;

        PriorityQueue<MazeCell> priorityQueue = cells().collect(
                Collectors.toCollection(() -> new PriorityQueue<>(size())));
        while (priorityQueue.size() > 0) {
            MazeCell u = priorityQueue.poll();
            List<MazeBallPath> connectedBallPaths = u.getConnectedBallPaths();
            for (MazeBallPath connectedBallPath : connectedBallPaths) {
                MazeCell v = connectedBallPath.getEndCell();

                if (priorityQueue.contains(v)) {
                    int altIntervalPathLength = u.intervalPathLength + 1;
                    int altBallPathLength = u.ballPathLength + connectedBallPath.length();
                    if (altIntervalPathLength < v.intervalPathLength || //
                            (altIntervalPathLength == v.intervalPathLength && altBallPathLength < v.ballPathLength)) {
                        v.intervalPathLength = altIntervalPathLength;
                        v.ballPathLength = altBallPathLength;
                        v.prev = connectedBallPath;

                        // reinsert with changed distance
                        priorityQueue.remove(v);
                        priorityQueue.add(v);
                    }
                }
            }
        }

        Deque<MazeBallPath> shortestPath = null;
        for (int interval = 0; interval < 4; interval++) {
            Deque<MazeBallPath> otherPaths = reverseIterate(getMazeCell(exitCell.x, exitCell.y, interval));
            if (shortestPath == null || otherPaths != null && shortestPath.size() > otherPaths.size()) {
                shortestPath = otherPaths;
            }
        }

        long durationMs = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startTime);
        System.out.printf("Transforming maze solved after %9dms: %s%n", durationMs, shortestPath);
        if (shortestPath != null) {
            return shortestPath.stream().map(MazeBallPath::directionAsString).collect(Collectors.toList());
        }
        return null;
    }

    private Deque<MazeBallPath> reverseIterate(MazeCell targetCell) {
        Deque<MazeBallPath> S = new ArrayDeque<>();
        MazeCell u = targetCell;

        if (u.prev == null) {
            return null;
        }
        while (u.prev != null) {
            S.addFirst(u.prev);
            u = u.prev.startCell;
        }
        if (!S.peekFirst().getStartCell().equals(startCell)) {
            return null;
        }
        return S;
    }


    public Stream<MazeCell> cells() {
        return Arrays.stream(maze).flatMap(Arrays::stream).flatMap(Arrays::stream);
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int interval = 0; interval < 4; interval++) {
            for (int y = 0; y < maze.length; y++) {
                if (y == 0) {
                    sb.append("   ");
                    for (int x = 0; x < maze[y].length; x++) {
                        sb.append(String.format(" %2d ", x));
                    }
                    sb.append("\n");
                }

                appendVerticalBorders(sb, y, interval, Direction.N);
                for (int x = 0; x < maze[y].length; x++) {
                    MazeCell mazeCell = getMazeCell(x, y, interval);
                    if (x == 0) {
                        sb.append(String.format("%2d ", y));
                    }
                    appendHorizontalBorder(sb, mazeCell, Direction.W);
                    appendMazeCell(sb, mazeCell);
                    appendHorizontalBorder(sb, mazeCell, Direction.E);
                }
                sb.append("\n");
                appendVerticalBorders(sb, y, interval, Direction.S);

                if (y == maze.length - 1) {
                    sb.append("   ");
                    for (int x = 0; x < maze[y].length; x++) {
                        sb.append(String.format(" %2d ", x));
                    }
                    sb.append("\n");
                }
            }
        }


        String[] rows = sb.toString().split("\n");
        sb.setLength(0);

        int mazeRowHeight = length() * 3 + 2;
        for (int row = 0; row < mazeRowHeight; row++) {
            for (int interval = 0; interval < 4; interval++) {
                int asfasd = interval * mazeRowHeight + row;
                String rowStr = rows[asfasd];
                sb.append(rowStr).append("\t");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    private void appendMazeCell(StringBuilder sb, MazeCell mazeCell) {
        if (mazeCell.isAtSameLocation(startCell)) {
            sb.append("BB");
        } else if (mazeCell.isAtSameLocation(exitCell)) {
            sb.append("XX");
        } else {
            sb.append(String.format("%2d", mazeCell.borderCode));
        }
    }

    private void appendHorizontalBorder(StringBuilder sb, MazeCell mazeCell, Direction direction) {
        if (mazeCell.hasBorderTo(direction)) {
            sb.append('|');
        } else {
            sb.append(' ');
        }
    }

    private void appendVerticalBorders(StringBuilder sb, int y, int interval, Direction direction) {
        sb.append("   +");
        for (int x = 0; x < maze[y].length; x++) {
            MazeCell mazeCell = getMazeCell(x, y, interval);
            if (mazeCell.hasBorderTo(direction)) {
                sb.append("--++");
            } else {
                sb.append("  ++");
            }
        }
        sb.setCharAt(sb.length() - 1, '\n');
    }


    class MazeCell implements Comparable<MazeCell> {

        final int x;
        final int y;
        final int interval;
        private byte borderCode;

        int ballPathLength = Integer.MAX_VALUE;
        int intervalPathLength = Integer.MAX_VALUE;
        MazeBallPath prev = null;
        List<MazeBallPath> connectedBallPaths = null;


        public MazeCell(int x, int y, int interval, int mazeCellCode) {
            this.x = x;
            this.y = y;
            this.interval = interval;
            if (mazeCellCode < 0) {
                mazeCellCode = 0;
            }
            borderCode = (byte) mazeCellCode;
        }


        public boolean canAccessCellTo(Direction direction) {
            MazeCell otherMazeCell = cellTo(direction);
            return otherMazeCell != null && !this.hasBorderTo(direction) //
                    && !otherMazeCell.hasBorderTo(direction.oppositeDirection());
        }

        public boolean hasBorderTo(Direction direction) {
            int borderCodeForDirection = 1 << (Direction.values().length - direction.ordinal() - 1);
            int borderCodeResult = borderCode & borderCodeForDirection;
            return borderCodeResult == borderCodeForDirection;
        }

        public MazeCell cellTo(Direction direction) {
            int x = this.x + direction.dx;
            int y = this.y + direction.dy;
            return getMazeCell(x, y, interval);
        }


        public boolean isAtSameLocation(MazeCell otherCell) {
            return otherCell != null && this.x == otherCell.x && this.y == otherCell.y;
        }


        public List<MazeBallPath> getConnectedBallPaths() {
            if (connectedBallPaths == null) {
                Set<MazeCell> visitedCells = new HashSet<>();
                visitedCells.add(this);

                connectedBallPaths = new ArrayList<>();
                connectedBallPaths.add(new MazeBallPath(this));
                connectedBallPaths.addAll(depthFirstBallPathSearch(visitedCells, this, new ArrayDeque<>()));
            }
            return connectedBallPaths;
        }

        private List<MazeBallPath> depthFirstBallPathSearch(Set<MazeCell> visitedCells, MazeCell currentCell,
                Deque<Direction> currentDirections) {
            List<MazeBallPath> connectedBallPaths = new ArrayList<>();

            for (Direction direction : Direction.values()) {
                if (!currentCell.canAccessCellTo(direction)) {
                    continue;
                }
                MazeCell nextCell = currentCell.cellTo(direction);
                if (visitedCells.add(nextCell)) {
                    currentDirections.addLast(direction);
                    MazeBallPath newBallPath = new MazeBallPath(this, nextCell, currentDirections);
                    connectedBallPaths.add(newBallPath);
                    connectedBallPaths.addAll(depthFirstBallPathSearch(visitedCells, nextCell, currentDirections));
                    currentDirections.pollLast();
                }
            }
            return connectedBallPaths;
        }


        public MazeCell newRightRotatedCell() {
            byte rotatedBorderCode = (byte) (((borderCode << 1 & 0b1111) | borderCode >>> 3));
            return new MazeCell(x, y, interval + 1, rotatedBorderCode);
        }

        public MazeCell asNextInterval() {
            return getMazeCell(x, y, interval + 1);
        }


        @Override
        public int compareTo(MazeCell otherCell) {
            int compare = Integer.compare(this.intervalPathLength, otherCell.intervalPathLength);
            if (compare == 0) {
                compare = Integer.compare(this.ballPathLength, otherCell.ballPathLength);
            }
            return compare;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o)
                return true;

            if (!(o instanceof MazeCell))
                return false;

            MazeCell mazeCell = (MazeCell) o;
            return new EqualsBuilder().append(x, mazeCell.x)
                    .append(y, mazeCell.y)
                    .append(interval, mazeCell.interval)
                    .append(borderCode, mazeCell.borderCode)
                    .isEquals();
        }

        @Override
        public int hashCode() {
            return new HashCodeBuilder(17, 37).append(x).append(y).append(interval).append(borderCode).toHashCode();
        }

        @Override
        public String toString() {
            return String.format("(%2d;%2d@%1d)", x, y, interval);
        }
    }


    static class MazeBallPath implements Comparable<MazeBallPath> {

        private final MazeCell startCell;
        private final MazeCell endCell;
        private final Deque<Direction> directions;


        public MazeBallPath(MazeCell standingCell) {
            this(standingCell, standingCell, new LinkedList<>());
        }

        public MazeBallPath(MazeCell startCell, MazeCell endCell, Deque<Direction> directions) {
            this.startCell = startCell;
            this.endCell = endCell.asNextInterval();
            this.directions = new ArrayDeque<>(directions);
        }


        public MazeCell getStartCell() {
            return startCell;
        }


        public MazeCell getEndCell() {
            return endCell;
        }


        public Stream<Direction> directions() {
            return directions.stream();
        }

        public String directionAsString() {
            return directions().map(Direction::toString).collect(Collectors.joining());
        }

        public int length() {
            return directions.size();
        }


        @Override
        public int compareTo(MazeBallPath otherBallPath) {
            return Integer.compare(this.length(), otherBallPath.length());
        }

        @Override
        public boolean equals(Object o) {
            if (this == o)
                return true;

            if (!(o instanceof MazeBallPath))
                return false;

            MazeBallPath that = (MazeBallPath) o;
            return new EqualsBuilder().append(getStartCell(), that.getStartCell())
                    .append(getEndCell(), that.getEndCell())
                    .isEquals();
        }

        @Override
        public int hashCode() {
            return new HashCodeBuilder(17, 37).append(getStartCell()).append(getEndCell()).toHashCode();
        }

        @Override
        public String toString() {
            return "Path from " + startCell + " " + directionAsString() + " to " + endCell;
        }

    }


    enum Direction {

        N(0, -1), W(-1, 0), S(0, 1), E(1, 0); /* in anti clockwise order */

        final int dx;
        final int dy;

        Direction(int dx, int dy) {
            this.dx = dx;
            this.dy = dy;
        }

        public Direction nextAntiClockwiseDirection() {
            Direction[] directions = Direction.values();
            int nextOrdinal = this.ordinal() - 1;
            if (nextOrdinal < 0) {
                nextOrdinal = directions.length - 1;
            }
            return directions[nextOrdinal];
        }

        public Direction nextClockwiseDirection() {
            return oppositeDirection().nextAntiClockwiseDirection();
        }

        public Direction oppositeDirection() {
            return nextAntiClockwiseDirection().nextAntiClockwiseDirection();
        }

    }


    static String toBinaryBorder(int borderCode) {
        return StringUtils.leftPad(Integer.toBinaryString(borderCode), 5, "0");
    }

}
