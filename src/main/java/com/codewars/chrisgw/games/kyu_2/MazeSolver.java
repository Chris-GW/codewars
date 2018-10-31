package com.codewars.chrisgw.games.kyu_2;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.*;
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
 * <li>consist only of the letters `N`, `W`, `S`, and `E` representing the ballMoves `north`, `west`, `south`, and `east`, respectively</li>
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

    private MazeCell[][] maze;
    private MazeCell startCell;
    private MazeCell exitCell;


    public MazeSolver(int[][] maze) {
        this.maze = new MazeCell[maze.length][];
        for (int y = 0; y < maze.length; y++) {
            int[] mazeRow = maze[y];
            this.maze[y] = new MazeCell[mazeRow.length];

            for (int x = 0; x < mazeRow.length; x++) {
                int mazeCellCode = maze[y][x];
                MazeCell mazeCell = new MazeCell(x, y, mazeCellCode);
                this.maze[y][x] = mazeCell;

                if (mazeCellCode == MAZE_BALL_CODE) {
                    this.startCell = mazeCell;
                } else if (mazeCellCode == MAZE_EXIT_CODE) {
                    this.exitCell = mazeCell;
                }
            }
        }
    }


    public List<String> solve() {
        List<MazeBallPath> possibleBallPaths = Collections.singletonList(new MazeBallPath(startCell));
        Optional<MazeBallPath> ballPathSolution = Optional.empty();

        int tick = 0;
        while (!ballPathSolution.isPresent()) {
            possibleBallPaths = possibleBallPaths.stream()
                    .map(MazeBallPath::possibleExtendedPaths)
                    .flatMap(Set::stream)
                    .collect(Collectors.toList());


            ballPathSolution = possibleBallPaths.stream()
                    .filter(mazeBallPath -> mazeBallPath.endsWith(exitCell))
                    .min(Comparator.naturalOrder());
            if (ballPathSolution.isPresent()) {
                possibleBallPaths.stream()
                        .filter(mazeBallPath -> mazeBallPath.endsWith(exitCell))
                        .sorted()
                        .forEachOrdered(System.out::println);
                System.out.println();
            }

            nextMazeTick();
            //        System.out.println(this);
            if (++tick > 10) {
                return null;
            }
        }

        return ballPathSolution.get()
                .ballMoves()
                .map(MazeBallMove::directions)
                .map(directionStream -> directionStream.map(Direction::toString).collect(Collectors.joining()))
                .collect(Collectors.toList());
    }


    void nextMazeTick() {
        Arrays.stream(maze).flatMap(Arrays::stream).forEach(MazeCell::rotateCellRight);
    }

    void previousMazeTick() {
        Arrays.stream(maze).flatMap(Arrays::stream).forEach(MazeCell::rotateCellLeft);
    }


    public MazeCell getMazeCell(int x, int y) {
        if (0 <= y && y < maze.length && 0 <= x && x < maze[y].length) {
            return maze[y][x];
        } else {
            return null;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (int y = 0; y < maze.length; y++) {
            appendVerticalBorders(sb, y, Direction.N);
            for (int x = 0; x < maze[y].length; x++) {
                MazeCell mazeCell = getMazeCell(x, y);
                appendHorizontalBorder(sb, mazeCell, Direction.W);
                appendMazeCell(sb, mazeCell);
                appendHorizontalBorder(sb, mazeCell, Direction.E);
            }
            sb.append("\n");
            appendVerticalBorders(sb, y, Direction.S);
        }
        sb.deleteCharAt(sb.length() - 1); // delete last '\n'
        return sb.toString();
    }

    private void appendMazeCell(StringBuilder sb, MazeCell mazeCell) {
        if (mazeCell.equals(startCell)) {
            sb.append("BB");
        } else if (mazeCell.equals(exitCell)) {
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

    private void appendVerticalBorders(StringBuilder sb, int y, Direction direction) {
        sb.append('+');
        for (int x = 0; x < maze[y].length; x++) {
            MazeCell mazeCell = getMazeCell(x, y);
            if (mazeCell.hasBorderTo(direction)) {
                sb.append("--++");
            } else {
                sb.append("  ++");
            }
        }
        sb.setCharAt(sb.length() - 1, '\n');
    }


    class MazeCell {

        final int x;
        final int y;
        private byte borderCode;

        public MazeCell(int x, int y, int mazeCellCode) {
            this.x = x;
            this.y = y;
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
            //            System.out.println(toBinaryBorder(borderCodeForDirection));
            int borderCodeResult = borderCode & borderCodeForDirection;
            //            System.out.println(toBinaryBorder(borderCodeResult));
            return borderCodeResult == borderCodeForDirection;
        }

        public MazeCell cellTo(Direction direction) {
            int x = this.x + direction.dx;
            int y = this.y + direction.dy;
            return getMazeCell(x, y);
        }


        public int distanceTo(MazeCell otherCell) {
            int dx = Math.abs(this.x - otherCell.x);
            int dy = Math.abs(this.y - otherCell.y);
            return dx + dy;
        }


        public byte getBorderCode() {
            return borderCode;
        }

        void rotateCellLeft() {
            borderCode = (byte) ((borderCode >>> 1) | (borderCode << 3 & 1 << 3));
        }

        void rotateCellRight() {
            borderCode = (byte) (((borderCode << 1 & 0b1111) | borderCode >>> 3));
        }


        @Override
        public boolean equals(Object o) {
            if (this == o)
                return true;

            if (!(o instanceof MazeCell))
                return false;

            MazeCell mazeCell = (MazeCell) o;
            return new EqualsBuilder().append(x, mazeCell.x).append(y, mazeCell.y).isEquals();
        }

        @Override
        public int hashCode() {
            return new HashCodeBuilder(17, 37).append(x).append(y).toHashCode();
        }

        @Override
        public String toString() {
            return String.format("(%2d;%2d)", x, y);
        }

    }


    static class MazeBallPath implements Comparable<MazeBallPath> {

        private MazeCell startCell;
        private Deque<MazeBallMove> ballMoves;
        private MazeCell endCell;


        public MazeBallPath(MazeCell startCell) {
            this.startCell = startCell;
            this.ballMoves = new ArrayDeque<>();
            this.endCell = startCell;
        }

        private MazeBallPath(MazeBallPath mazeBallPath) {
            this.startCell = mazeBallPath.startCell;
            this.ballMoves = new ArrayDeque<>(mazeBallPath.ballMoves);
            this.endCell = mazeBallPath.endCell;
        }


        public Set<MazeBallPath> possibleExtendedPaths() {
            Set<MazeBallPath> possibleExtendedPaths = new HashSet<>();
            MazeBallPath noMovePath = new MazeBallPath(this);
            noMovePath.ballMoves.add(new MazeBallMove(endCell, endCell, Collections.emptyList()));
            possibleExtendedPaths.add(noMovePath);

            possibleExtendedPathsDepthFirst(possibleExtendedPaths, endCell, new ArrayList<>());
            return possibleExtendedPaths;
        }

        private void possibleExtendedPathsDepthFirst(Set<MazeBallPath> possibleExtendedPaths, MazeCell currentCell,
                List<Direction> directions) {
            for (Direction direction : Direction.values()) {
                if (!currentCell.canAccessCellTo(direction)) {
                    continue;
                }
                MazeCell nextCell = currentCell.cellTo(direction);
                if (nextCell.equals(endCell)) {
                    continue;
                }

                directions.add(direction);
                MazeBallPath newBallPath = new MazeBallPath(this);
                MazeBallMove ballMove = new MazeBallMove(this.endCell, nextCell, directions);
                newBallPath.ballMoves.add(ballMove);
                newBallPath.endCell = nextCell;

                boolean isNewPossibleBallPath = possibleExtendedPaths.add(newBallPath);
                if (isNewPossibleBallPath) {
                    possibleExtendedPathsDepthFirst(possibleExtendedPaths, nextCell, directions);
                }
                directions.remove(directions.size() - 1);
            }
        }


        public Stream<MazeBallMove> ballMoves() {
            return ballMoves.stream();
        }


        public int length() {
            return ballMoves.size();
        }


        public MazeCell getStartCell() {
            return startCell;
        }

        public boolean startsWith(MazeCell mazeCell) {
            return startCell.equals(mazeCell);
        }


        public MazeCell getEndCell() {
            return endCell;
        }

        public boolean endsWith(MazeCell mazeCell) {
            return endCell.equals(mazeCell);
        }


        @Override
        public int compareTo(MazeBallPath otherBallPath) {
            Iterator<MazeBallMove> myBallMoves = this.ballMoves().iterator();
            Iterator<MazeBallMove> otherBallMoves = otherBallPath.ballMoves().iterator();
            while (myBallMoves.hasNext() && otherBallMoves.hasNext()) {
                MazeBallMove myBallMove = myBallMoves.next();
                MazeBallMove otherBallMove = otherBallMoves.next();
                int ballMoveCompare = myBallMove.compareTo(otherBallMove);
                if (ballMoveCompare != 0) {
                    return ballMoveCompare;
                }
            }
            return Boolean.compare(myBallMoves.hasNext(), otherBallMoves.hasNext());
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
            StringBuilder sb = new StringBuilder("Path from ").append(startCell);
            for (MazeBallMove ballMove : ballMoves) {
                String joinedDirectionsStr = ballMove.directions()
                        .map(Direction::toString)
                        .collect(Collectors.joining(""));
                sb.append(String.format(" [%6s] ", joinedDirectionsStr)).append(ballMove.getToCell());
            }
            List<String> joinedBallMovesStr = ballMoves().map(MazeBallMove::directions)
                    .map(directionStream -> directionStream.map(Direction::toString).collect(Collectors.joining()))
                    .collect(Collectors.toList());
            sb.append(" = ").append(joinedBallMovesStr);
            return sb.toString();
        }

    }


    static class MazeBallMove implements Comparable<MazeBallMove> {

        private final MazeCell fromCell;
        private final MazeCell toCell;
        private final List<Direction> directions;


        public MazeBallMove(MazeCell fromCell, MazeCell toCell, List<Direction> directions) {
            this.fromCell = fromCell;
            this.toCell = toCell;
            this.directions = new ArrayList<>(directions);
        }


        public MazeCell getFromCell() {
            return fromCell;
        }

        public MazeCell getToCell() {
            return toCell;
        }


        public Stream<Direction> directions() {
            return directions.stream();
        }

        public int length() {
            return directions.size();
        }


        @Override
        public int compareTo(MazeBallMove otherBallMove) {
            return Integer.compare(this.length(), otherBallMove.length());
        }

        @Override
        public boolean equals(Object o) {
            if (this == o)
                return true;

            if (!(o instanceof MazeBallMove))
                return false;

            MazeBallMove that = (MazeBallMove) o;
            return new EqualsBuilder().append(getFromCell(), that.getFromCell())
                    .append(getToCell(), that.getToCell())
                    .append(directions, that.directions)
                    .isEquals();
        }

        @Override
        public int hashCode() {
            return new HashCodeBuilder(17, 37).append(getFromCell())
                    .append(getToCell())
                    .append(directions)
                    .toHashCode();
        }

        @Override
        public String toString() {
            String joinedDirectionsStr = directions().map(Direction::toString).collect(Collectors.joining(""));
            return String.format("Move from %s over %6s to %s", fromCell, joinedDirectionsStr, toCell);
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
