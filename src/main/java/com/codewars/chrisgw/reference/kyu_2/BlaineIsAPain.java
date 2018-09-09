package com.codewars.chrisgw.reference.kyu_2;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;


/**
 * <span style="font-weight:bold;font-size:1.5em;color:red">*Blaine is a pain, and that is the truth</span>&nbsp;- Jack Chambers*
 * <p>
 * # <span style='color:orange'>Background</span>
 * <p>
 * Blaine likes to deliberately crash toy trains!
 * <p>
 * <p>
 * ## <span style='color:orange'>*Trains*</span>
 * <p>
 * Trains look like this
 * <p>
 * * `Aaaaaaaaaa`
 * * `bbbB`
 * <p>
 * <p>
 * The engine and carriages use the same character, but because the only engine is uppercase you can tell which way the train is going.
 * <p>
 * Trains can be any alphabetic character
 * * An "Express" train uses `X`
 * * Normal suburban trains are all other letters
 * <p>
 * ## <span style='color:orange'>*Tracks*</span>
 * <p>
 * Track pieces are characters `-` `|` `/` `\` `+` `X` and they can be joined together like this
 *
 * <table>
 * <tr>
 * <td>*Straights*
 * <td width = "20%">
 * <pre style='background:black'>
 *
 * ----------
 *
 * </pre>
 * <td width = "20%">
 * <pre style='background:black'>
 * |
 * |
 * |
 * </pre>
 * <td width = "20%">
 * <pre style='background:black'>
 * \
 *  \
 *   \
 * </pre>
 * <td width = "20%">
 * <pre style='background:black'>
 *    /
 *   /
 *  /
 * </pre>
 * </tr>
 * <tr>
 * <td>*Corners*
 * <td>
 * <pre style='background:black'>
 * |
 * |
 * \\-----
 * </pre>
 * <td>
 * <pre style='background:black'>
 *      |
 *      |
 * -----/
 * </pre>
 * <td>
 * <pre style='background:black'>
 * /-----
 * |
 * |
 * </pre>
 * <td>
 * <pre style='background:black'>
 * -----\
 *      |
 *      |
 * </pre>
 *
 * </tr>
 *
 * <tr>
 * <td>*Curves*
 * <td>
 * <pre style='background:black'>
 *
 * -----\
 *       \\-----
 *
 * </pre>
 * <td>
 * <pre style='background:black'>
 *
 *       /-----
 * -----/
 *
 * </pre>
 * <td>
 * <pre style='background:black'>
 *   |
 *   /
 *  /
 *  |
 * </pre>
 * <td>
 * <pre style='background:black'>
 * |
 * \
 *  \
 *  |
 * </pre>
 *
 * </tr>
 * <tr>
 * <td>*Crossings*
 * <td>
 * <pre style='background:black'>
 *    |
 * ---+---
 *    |
 * </pre>
 * <td>
 * <pre style='background:black'>
 *   \ /
 *    X
 *   / \
 * </pre>
 * <td>
 * <td>
 *
 * </tr>
 *
 *
 * </table>
 * <p>
 * ## <span style='color:orange'>*Describing where a train is on the line*</span>
 * <p>
 * The track "zero position" is defined as the leftmost piece of track of the top row.
 * <p>
 * Other <u>track positions</u> are just distances from this *zero position* (following the line beginning clockwise).
 * <p>
 * A <u>train position</u> is the track position of the train *engine*.
 * <p>
 * <p>
 * ## <span style='color:orange'>*Stations*</span>
 * <p>
 * Train stations are represented by a letter `S`.
 * <p>
 * Stations can be on straight sections of track like this
 * <table>
 * <tr>
 * <td>*Stations*
 * <td width = "20%">
 * <pre style='background:black'>
 *
 * ----S-----
 *
 * </pre>
 * <td width = "20%">
 * <pre style='background:black'>
 * |
 * S
 * |
 * </pre>
 * <td width = "20%">
 * <pre style='background:black'>
 * \
 *  S
 *   \
 * </pre>
 * <td width = "20%">
 * <pre style='background:black'>
 *    /
 *   S
 *  /
 * </pre>
 * </tr>
 *
 * </table>
 * <p>
 * <br/>
 * When a train arrives at a station it stops there for a period of time determined by the carriageLength of the train!
 * <p>
 * The time **T** that a train will remain at the station is same as the number of *carriages* it has.
 * <p>
 * For example
 * <p>
 * * `bbbB` - will stop at a station for 3 time units
 * * `Aa` - will stop at a station for 1 time unit
 * <p>
 * Exception to the rule: The "Express" trains never stop at any station.
 * <p>
 * ## <span style='color:orange'>*Collisions*</span>
 * <p>
 * There are lots of ways to crash trains. Here are a few of Blaine's favorites...
 * * *The Chicken-Run* - Train chicken. Maximum impact.
 * * *The T-Bone* -  Two trains and one crossing
 * * *The Self-Destruct* - Nobody else to blame here
 * * *The Cabooser* - Run up the tail of a stopped train
 * * *The Kamikaze* - Crash head-on into a stopped train
 * <p>
 * <p>
 * # <span style='color:orange'>Kata Task</span>
 * <p>
 * Blaine has a variety of *continuous loop* train lines.
 * <p>
 * Two trains are then placed onto the line, and both start moving at the same time.
 * <p>
 * How long (how many iterations) before the trains collide?
 * <p>
 * <p>
 * ## <span style='color:orange'>*Input*</span>
 * * `track` - string representation of the entire train line (`\n` separators - maybe jagged, maybe not trailing)
 * * `a` - train A
 * * `aPos` - train A start position
 * * `b` - train B
 * * `bPos` - train B start position
 * * `limit` - how long before Blaine tires of waiting for a crash and gives up
 * <p>
 * ## <span style='color:orange'>*Output*</span>
 * * Return how long before the trains collide, or
 * * Return `-1` if they have not crashed before `limit` time has elapsed, or
 * * Return `0` if the trains were already crashed in their start positions. Blaine is sneaky sometimes.
 * <p>
 * # <span style='color:orange'>Notes</span>
 * <p>
 * Trains
 * * Speed...
 * * All trains (even the "Express" ones) move at the same constant speed of 1 track piece / time unit
 * * Length...
 * * Trains can be any carriageLength, but there will always be at least one carriage
 * * Stations...
 * * Suburban trains stop at every station
 * * "Express" trains don't stop at any station
 * * If the start position happens to be at a station then the train leaves at the next move
 * * Directions...
 * * Trains can travel in either direction
 * * A train that looks like `zzzzzZ` is travelling *clockwise* as it passed the track "zero position"
 * * A train that looks like `Zzzzzz` is traveliing *anti-clockwise* as it passes the track "zero position"
 * <p>
 * Tracks
 * * All tracks are single continuous loops
 * * There are no ambiguous corners / junctions in Blaine's track layouts
 * <p>
 * All input is valid
 * <p>
 * # <span style='color:orange'>Example</span>
 * <p>
 * In the following track layout:
 * * The "zero position" is  <span style='background:orange'>/</span>
 * * Train A is <span style='background:green'>Aaaa</span> and is at position `147`
 * * Train B is <span style='background:red'>Bbbbbbbbbbb</span> and is at position `288`
 * * There are 3 stations denoted by `S`
 *
 *
 * <pre style='background:black'>
 *                                 <span style='background:orange'>/</span>------------\
 * /-----<span style='background:green'>Aaaa</span>----\                /             |
 * |             |               /              S
 * |             |              /               |
 * |        /----+--------------+------\        |
 * \       /     |              |      |        |
 *  \      |     \              |      |        |
 *  |      |      \-------------+------+--------+---\
 *  |      |                    |      |        |   |
 *  \------+------S-------------+------/        /   |
 *         |                    |              /    |
 *         \--------------------+-------------/     |
 *                              |                   |
 * /-------------\              |                   |
 * |             |              |             /-----+----\
 * |             |              |             |     |     \
 * \-------------+--------------+-----S-------+-----/      \
 *               |              |             |             \
 *               |              |             |             |
 *               |              \-------------+-------------/
 *               |                            |
 *               \---------<span style='background:red'>Bbbbbbbbbbb</span>--------/
 * </pre>
 *
 * <br>
 * <hr>
 * Good Luck!
 * <p>
 * DM<br><span style='color:red'>:-)</span>
 */
public class BlaineIsAPain {


    private TrainTrack firstTrack;
    private List<Train> trains = new ArrayList<>();


    public BlaineIsAPain(TrainTrack firstTrack) {
        firstTrack.setNextTrack(firstTrack);
        this.firstTrack = firstTrack;
    }

    public void addTrack(TrainTrack trainTrack) {
        if (firstTrack.previousTrack != null) {
            firstTrack.previousTrack.setNextTrack(trainTrack);
        }
        trainTrack.setNextTrack(firstTrack);
    }


    public static int trainCrash(final String track, final String aTrain, final int aTrainPos, final String bTrain,
            final int bTrainPos, final int waitingLimit) {
        String[] trackRows = track.split("\n");
        List<Train> trains = Stream.of(aTrain, bTrain).map(BlaineIsAPain::newTrainFromStr).collect(Collectors.toList());
        List<Integer> trainStartTrackPositions = Arrays.asList(aTrainPos, bTrainPos);
        Arrays.stream(trackRows).forEach(System.out::println);
        System.out.println(trains);
        System.out.println(trainStartTrackPositions);
        System.out.println();

        TrainTrack firstTrack = findFirstTrack(trackRows);
        BlaineIsAPain blaineIsAPain = new BlaineIsAPain(firstTrack);
        TrainTrack currentTrack = firstTrack;
        for (int i = 0; true; i++) {
            TrainTrack nextTrack = findNextNearbyTrack(currentTrack, trackRows);
            if (nextTrack.isAtPosition(firstTrack)) {
                break;
            }
            blaineIsAPain.addTrack(nextTrack);
            //            System.out.println(i + ":\n" + blaineIsAPain);
            currentTrack = nextTrack;
        }

        return blaineIsAPain.trainCrash(trains, trainStartTrackPositions, waitingLimit);
    }

    private static Train newTrainFromStr(String trainStr) {
        char trainSymbol = trainStr.charAt(1);
        int carriageLength = trainStr.length() - 1;
        boolean movesClockwise = Character.isLowerCase(trainStr.charAt(0));
        if (trainSymbol == ExpressTrain.EXPRESS_TRAIN_SYMBOL) {
            return new ExpressTrain(carriageLength, movesClockwise);
        } else {
            return new Train(trainSymbol, carriageLength, movesClockwise);
        }
    }

    private static TrainTrack findFirstTrack(String[] trackRows) {
        String firstTrackRow = trackRows[0];
        for (int column = 0; column < firstTrackRow.length(); column++) {
            char trackSymbol = firstTrackRow.charAt(column);
            if (TrainTrack.isTrainTrackSymbol(trackSymbol)) {
                TrainTrackDirection trackDirection = TrainTrackDirection.RIGHT; // first track is always in TrainTrackDirection.RIGHT
                return newTrainTrack(0, column, trackDirection, 0, trackSymbol);
            }
        }
        throw new IllegalArgumentException("first track row doesn't contain any valid trackSymbols");
    }

    private static TrainTrack newTrainTrack(int row, int column, TrainTrackDirection trackDirection, int trackPosition,
            char trackSymbol) {
        if (TrainTrack.isTrainTrackSymbol(trackSymbol)) {
            return new TrainTrack(row, column, trackDirection, trackPosition, trackSymbol);
        } else {
            return null;
        }
    }


    private static TrainTrack findNextNearbyTrack(TrainTrack currentTrainTrack, String[] trackRows) {
        int trackPosition = currentTrainTrack.trackPosition + 1;
        return currentTrainTrack.connectableDirections()
                .map(trackDirection -> {
                    int row = currentTrainTrack.row + trackDirection.rowOffset;
                    int column = currentTrainTrack.column + trackDirection.columnOffset;
                    char trackSymbol = getTrackSymbol(trackRows, row, column);
                    if (trackSymbol == '/') {
                        trackDirection = trackDirection.getRightCornerDirection();
                    } else if (trackSymbol == '\\') {
                        trackDirection = trackDirection.getLeftCornerDirection();
                    }
                    return newTrainTrack(row, column, trackDirection, trackPosition, trackSymbol);
                })
                .filter(Objects::nonNull)
                .findFirst()
                .orElseThrow(
                        () -> new IllegalArgumentException("Could not find nearby track for: " + currentTrainTrack));
    }

    private static char getTrackSymbol(String[] trackRows, int row, int column) {
        if (0 <= row && row < trackRows.length && //
                0 <= column && column < trackRows[row].length()) {
            return trackRows[row].charAt(column);
        } else {
            return ' ';
        }
    }


    public int trainCrash(List<Train> trains, List<Integer> trainStartTrackPositions, int waitingLimit) {
        this.trains.clear();
        this.trains.addAll(trains);
        placeTrainsOnTrack(trainStartTrackPositions);
        System.out.println(toString());

        for (int pastTime = 0; pastTime <= waitingLimit; pastTime++) {
            for (Train train : trains) {
                for (Train otherTrain : trains) {
                    if (train.equals(otherTrain)) {
                        continue;
                    }
                    if (isTrainCrashedIntoEachOther(train, otherTrain)) {
                        return pastTime;
                    }
                }
            }
            trains.forEach(Train::moveTrainOneTimeUnit);
            //            System.out.println(toString());
        }
        return -1;
    }

    public void placeTrainsOnTrack(List<Integer> trainStartTrackPositions) {
        for (int i = 0; i < trainStartTrackPositions.size(); i++) {
            TrainTrack trainStartTrack = getTrack(trainStartTrackPositions.get(i));
            trains.get(i).placeTrainOnStartTrack(trainStartTrack);
        }
    }

    private boolean isTrainCrashedIntoEachOther(Train train, Train otherTrain) {
        return train.occupiedTracks()
                .anyMatch(trainTrack -> otherTrain.occupiedTracks().anyMatch(trainTrack::isAtPosition));
    }


    public TrainTrack getTrack(int trackPosition) {
        int trackSystemSize = getTrackSystemSize();
        boolean searchClockwise = trackPosition < trackSystemSize / 2;
        return tracks(searchClockwise).filter(trainTrack -> trainTrack.isAtPosition(trackPosition))
                .findAny()
                .orElse(null);
    }

    public Optional<TrainTrack> getTrack(int row, int column) {
        return tracks(true).filter(trainTrack -> trainTrack.isAtPosition(row, column)).findFirst();
    }


    public Stream<TrainTrack> tracks(boolean clockwise) {
        return Stream.generate(newTrainTrackTraversal(firstTrack, clockwise)).limit(getTrackSystemSize());
    }


    private Optional<Train> getTrainOccupiengTrack(TrainTrack trainTrack) {
        return trains.stream().filter(train -> train.occupiedTracks().anyMatch(trainTrack::equals)).findFirst();
    }

    public int getTrackSystemSize() {
        return firstTrack.previousTrack.trackPosition + 1;
    }


    @Override
    public String toString() {
        int maxRow = tracks(true).mapToInt(trainTrack -> trainTrack.row).max().orElse(0);
        int maxColumn = tracks(true).mapToInt(trainTrack -> trainTrack.column).max().orElse(0);
        StringBuilder sb = new StringBuilder((maxRow + 2) * (maxColumn + 6));
        sb.append(" \\ ");
        IntStream.rangeClosed(0, maxColumn / 10).flatMap(value -> IntStream.range(0, 10)).forEach(sb::append);
        sb.append("/\n");
        for (int row = 0; row <= maxRow; row++) {
            for (int column = 0; column <= maxColumn; column++) {
                if (column == 0) {
                    sb.append(String.format("%2d ", row));
                }
                sb.append(getTrackSymbolAt(row, column));
            }
            sb.append(String.format(" %2d\n", row));
        }
        sb.append(" / ");
        IntStream.rangeClosed(0, maxColumn / 10).flatMap(value -> IntStream.range(0, 10)).forEach(sb::append);
        sb.append("\\\n");
        return sb.append("\n").toString();
    }

    private char getTrackSymbolAt(int row, int column) {
        return getTrack(row, column).map(trainTrack -> {
            Optional<Character> trainSymbol = getTrainOccupiengTrack(trainTrack).map(train -> {
                if (trainTrack.isAtPosition(train.getTrainEngineTrackPosition())) {
                    return Character.toUpperCase(train.trainSymbol);
                } else {
                    return train.trainSymbol;
                }
            });
            return trainSymbol.orElse(trainTrack.symbol);
        }).orElse(column % 2 == 0 ? '·' : ' ');
    }


    public static class TrainTrack {

        public static final char[] TRAIN_TRACK_SYMBOLS = new char[] { '-', '|', '/', '\\', '+', 'X', 'S' };
        public static final char TRAIN_STATION_TRACK_SYMBOL = 'S';

        public static boolean isTrainTrackSymbol(char symbol) {
            for (char trainTrackSymbol : TrainTrack.TRAIN_TRACK_SYMBOLS) {
                if (trainTrackSymbol == symbol) {
                    return true;
                }
            }
            return false;
        }

        int row;
        int column;
        TrainTrackDirection trackDirection;
        int trackPosition;
        char symbol;

        TrainTrack nextTrack;
        TrainTrack previousTrack;


        public TrainTrack(int row, int column, TrainTrackDirection trackDirection, int trackPosition, char symbol) {
            this.row = row;
            this.column = column;
            this.trackDirection = trackDirection;
            this.trackPosition = trackPosition;
            this.symbol = symbol;
        }


        public boolean isStation() {
            return symbol == TRAIN_STATION_TRACK_SYMBOL;
        }


        public boolean isAtPosition(TrainTrack otherTrainTrack) {
            if (otherTrainTrack == null) {
                return false;
            }
            return isAtPosition(otherTrainTrack.trackPosition) || //
                    isAtPosition(otherTrainTrack.row, otherTrainTrack.column);
        }

        public boolean isAtPosition(int row, int column) {
            return this.row == row && this.column == column;
        }

        public boolean isAtPosition(int trackPosition) {
            return this.trackPosition == trackPosition;
        }


        public void setNextTrack(TrainTrack nextTrack) {
            this.nextTrack = nextTrack;
            nextTrack.previousTrack = this;
        }


        public TrainTrackDirection getTrackDirection() {
            return trackDirection;
        }

        public Stream<TrainTrackDirection> connectableDirections() {
            TrainTrackDirection trackDirection = getTrackDirection();
            TrainTrackDirection clockwiseTrackDirection = trackDirection.getDirectionClockwise();
            TrainTrackDirection antiClockwiseTrackDirection = trackDirection.getDirectionAntiClockwise();
            return Stream.of(trackDirection, clockwiseTrackDirection, antiClockwiseTrackDirection);
        }


        public Stream<TrainTrack> previousTracks() {
            return Stream.generate(newTrainTrackTraversal(this.previousTrack, false));
        }

        public Stream<TrainTrack> nextTracks() {
            return Stream.generate(newTrainTrackTraversal(this.nextTrack, true));
        }


        @Override
        public boolean equals(Object o) {
            if (this == o)
                return true;

            if (!(o instanceof TrainTrack))
                return false;

            TrainTrack that = (TrainTrack) o;
            return new EqualsBuilder().append(row, that.row)
                    .append(column, that.column)
                    .append(trackDirection, that.trackDirection)
                    .append(trackPosition, that.trackPosition)
                    .append(symbol, that.symbol)
                    .isEquals();
        }

        @Override
        public int hashCode() {
            return new HashCodeBuilder(17, 37).append(row)
                    .append(column)
                    .append(trackDirection)
                    .append(trackPosition)
                    .append(symbol)
                    .toHashCode();
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this, ToStringStyle.NO_CLASS_NAME_STYLE).append("row", row)
                    .append("column", column)
                    .append("trackDirection", trackDirection)
                    .append("trackPosition", trackPosition)
                    .append("symbol", symbol)
                    .toString();
        }

    }


    public static Supplier<TrainTrack> newTrainTrackTraversal(TrainTrack firstTrainTrack, boolean clockwise) {
        return new Supplier<TrainTrack>() {

            private TrainTrack currentTrack = firstTrainTrack;

            @Override
            public TrainTrack get() {
                TrainTrack nextTrack = currentTrack;
                if (clockwise) {
                    currentTrack = currentTrack.nextTrack;
                } else {
                    currentTrack = currentTrack.previousTrack;
                }
                return nextTrack;
            }
        };
    }


    public static class Train {

        char trainSymbol;
        int carriageLength;
        boolean movesClockwise;
        int waitingInStation = 0;

        Deque<TrainTrack> occupiedTracks = new LinkedList<>();

        public Train(char trainSymbol, int carriageLength, boolean movesClockwise) {
            this.trainSymbol = trainSymbol;
            this.carriageLength = carriageLength;
            this.movesClockwise = movesClockwise;
        }


        public void placeTrainOnStartTrack(TrainTrack startTrack) {
            TrainTrack currentTrack = startTrack;
            if (movesClockwise) {
                for (int i = 0; i <= carriageLength; i++) {
                    occupiedTracks.addLast(currentTrack);
                    currentTrack = currentTrack.previousTrack;
                }
            } else {
                for (int i = 0; i <= carriageLength; i++) {
                    occupiedTracks.addLast(currentTrack);
                    currentTrack = currentTrack.previousTrack;
                }
            }
        }


        public void moveTrainOneTimeUnit() {
            if (occupiedTracks.isEmpty()) {
                throw new IllegalArgumentException("Could note move Train, which seems to be not placed on tracks");
            }
            if (waitingInStation > 0) {
                waitingInStation--;
                return;
            }
            TrainTrack nextTrack;
            if (movesClockwise) {
                nextTrack = occupiedTracks.getFirst().nextTrack;
            } else {
                nextTrack = occupiedTracks.getFirst().previousTrack;
            }
            occupiedTracks.addFirst(nextTrack);
            occupiedTracks.removeLast();

            if (nextTrack.isStation()) {
                waitingInStation = getStationWaitingTime();
            }
        }


        public int getStationWaitingTime() {
            return carriageLength;
        }


        public TrainTrack getTrainEngineTrackPosition() {
            return occupiedTracks.getFirst();
        }

        public Stream<TrainTrack> occupiedTracks() {
            return occupiedTracks.stream();
        }


        @Override
        public boolean equals(Object o) {
            if (this == o)
                return true;

            if (!(o instanceof Train))
                return false;

            Train train = (Train) o;

            return new EqualsBuilder().append(trainSymbol, train.trainSymbol)
                    .append(carriageLength, train.carriageLength)
                    .append(movesClockwise, train.movesClockwise)
                    .append(waitingInStation, train.waitingInStation)
                    .append(occupiedTracks, train.occupiedTracks)
                    .isEquals();
        }

        @Override
        public int hashCode() {
            return new HashCodeBuilder(17, 37).append(trainSymbol)
                    .append(carriageLength)
                    .append(movesClockwise)
                    .append(waitingInStation)
                    .append(occupiedTracks)
                    .toHashCode();
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this, ToStringStyle.NO_CLASS_NAME_STYLE).append("trainSymbol", trainSymbol)
                    .append("carriageLength", carriageLength)
                    .append("movesClockwise", movesClockwise)
                    .toString();
        }

    }


    public static class ExpressTrain extends Train {

        public static final char EXPRESS_TRAIN_SYMBOL = 'X';

        public ExpressTrain(int carriageLength, boolean movesClockwise) {
            super(EXPRESS_TRAIN_SYMBOL, carriageLength, movesClockwise);
        }

        @Override
        public int getStationWaitingTime() {
            return 0;
        }

    }


    public enum TrainTrackDirection {

        // all direcions in clockwise order
        TOP_LEFT(-1, -1), //
        TOP(-1, 0), //
        TOP_RIGHT(-1, 1), //
        RIGHT(0, 1),  //
        BOTTOM_RIGHT(1, 1), //
        BOTTOM(1, 0), //
        BOTTOM_LEFT(1, -1), //
        LEFT(0, -1),
        ; //

        int rowOffset;
        int columnOffset;

        TrainTrackDirection(int rowOffset, int columnOffset) {
            this.rowOffset = rowOffset;
            this.columnOffset = columnOffset;
        }


        public TrainTrackDirection getOppositeDirection() {
            TrainTrackDirection[] trainTrackDirections = values();
            int oppositeSideOrdinal = this.ordinal() + trainTrackDirections.length / 2;
            oppositeSideOrdinal %= trainTrackDirections.length;
            return trainTrackDirections[oppositeSideOrdinal];
        }

        public TrainTrackDirection getDirectionClockwise() {
            int nextOrdinal = this.ordinal() + 1;
            if (nextOrdinal == values().length) {
                nextOrdinal = 0;
            }
            return values()[nextOrdinal];
        }

        public TrainTrackDirection getDirectionAntiClockwise() {
            int nextOrdinal = this.ordinal() - 1;
            if (nextOrdinal == -1) {
                nextOrdinal = values().length - 1;
            }
            return values()[nextOrdinal];
        }


        public TrainTrackDirection getRightCornerDirection() {
            if (RIGHT.equals(this) || LEFT.equals(this)) {
                return getDirectionAntiClockwise();
            } else if (BOTTOM.equals(this) || TOP.equals(this)) {
                return getDirectionClockwise();
            } else {
                return this;
            }
        }

        public TrainTrackDirection getLeftCornerDirection() {
            if (RIGHT.equals(this) || LEFT.equals(this)) {
                return getDirectionClockwise();
            } else if (BOTTOM.equals(this) || TOP.equals(this)) {
                return getDirectionAntiClockwise();
            } else {
                return this;
            }
        }

    }


}
