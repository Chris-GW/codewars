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
import java.util.stream.Stream.Builder;


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
        System.out.println(trains + " \taTrain = " + aTrain + ", bTrain = " + bTrain);
        System.out.println(trainStartTrackPositions + " and waitingLimit = " + waitingLimit);
        System.out.println();

        TrainTrack firstTrack = findFirstTrack(trackRows);
        BlaineIsAPain blaineIsAPain = new BlaineIsAPain();
        firstTrack.setNextTrack(firstTrack);
        blaineIsAPain.firstTrack = firstTrack;

        int secondTrackColumn = firstTrack.column + 1;
        char secondTrackSymbol = trackRows[0].charAt(secondTrackColumn);
        TrainTrack seocndTrack = newTrainTrack(0, secondTrackColumn, 1, secondTrackSymbol);
        blaineIsAPain.addTrack(seocndTrack);

        TrainTrack currentTrack = seocndTrack;
        for (int i = 0; true; i++) {
            TrainTrack nextTrack = findNextNearbyTrack(currentTrack, trackRows);
            if (nextTrack.isAtPosition(firstTrack)) {
                break;
            }
            blaineIsAPain.addTrack(nextTrack);
            System.out.println(i + ":\n" + blaineIsAPain);
            currentTrack = nextTrack;
        }

        return blaineIsAPain.trainCrash(trains, trainStartTrackPositions, waitingLimit);
    }

    private static Train newTrainFromStr(String trainStr) {
        char trainSymbol = trainStr.charAt(0);
        int carriageLength = trainStr.length() - 1;
        boolean movesClockwise = Character.isLowerCase(trainSymbol);
        return new Train(trainSymbol, carriageLength, movesClockwise);
    }

    private static TrainTrack findFirstTrack(String[] trackRows) {
        String firstTrackRow = trackRows[0];
        for (int column = 0; column < firstTrackRow.length(); column++) {
            char trackSymbol = firstTrackRow.charAt(column);
            TrainTrack firstTrack = newTrainTrack(0, column, 0, trackSymbol);
            if (firstTrack != null) {
                return firstTrack;
            }
        }
        throw new IllegalArgumentException("first track row doesn't contain any valid trackSymbols");
    }

    private static TrainTrack newTrainTrack(int row, int column, int trackPosition, char trackSymbol) {
        TrainTrackType trackType = TrainTrackType.valueOf(trackSymbol);
        if (trackType != null) {
            return new TrainTrack(row, column, trackPosition, trackType);
        } else {
            return null;
        }
    }


    private static TrainTrack findNextNearbyTrack(TrainTrack currentTrack, String[] trackRows) {
        List<TrainTrack> possibleTracks = currentTrack.possibleConnectableTracks() //
                .filter(trainTrack -> {
                    int row = trainTrack.row;
                    int column = trainTrack.column;
                    char actualtrackSymbol = getTrackSymbol(trackRows, row, column);
                    char expectedTrackSymbol = trainTrack.trackType.trackSymbol;
                    return actualtrackSymbol == expectedTrackSymbol;
                }).collect(Collectors.toList());
        return possibleTracks.stream()
                .filter(trainTrack -> !currentTrack.previousTrack.isAtPosition(trainTrack))
                .findFirst()
                .orElseThrow(RuntimeException::new);
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
            for (int i = 0; i < trains.size(); i++) {
                Train train = trains.get(i);
                if (train.isCrashedWithItself()) {
                    System.out.println("Train is crashed with itself: " + train);
                    System.out.println(toString());
                    return pastTime;
                }
                for (int j = i + 1; j < trains.size(); j++) {
                    Train otherTrain = trains.get(j);
                    if (train.isCrashedWith(otherTrain)) {
                        System.out.println("Trains crashed into each other: " + train + " and " + otherTrain);
                        System.out.println(toString());
                        return pastTime;
                    }
                }
            }
            trains.forEach(Train::moveTrainOneTimeUnit);
            System.out.println(toString());
        }
        System.out.println("No train trash after " + waitingLimit);
        return -1;
    }

    public void placeTrainsOnTrack(List<Integer> trainStartTrackPositions) {
        for (int i = 0; i < trainStartTrackPositions.size(); i++) {
            TrainTrack trainStartTrack = getTrack(trainStartTrackPositions.get(i));
            trains.get(i).placeTrainOnStartTrack(trainStartTrack);
        }
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
            return trainSymbol.orElse(trainTrack.trackType.trackSymbol);
        }).orElse(column % 2 == 0 ? '·' : ' ');
    }


    public static class TrainTrack {

        public static final char[] TRAIN_TRACK_SYMBOLS = new char[] { '-', '|', '/', '\\', '+', 'X', 'S' };


        int row;
        int column;
        int trackPosition;
        TrainTrackType trackType;

        TrainTrack nextTrack;
        TrainTrack previousTrack;


        public TrainTrack(int row, int column, int trackPosition, TrainTrackType trackType) {
            this.row = row;
            this.column = column;
            this.trackPosition = trackPosition;
            this.trackType = trackType;
        }


        public boolean isStation() {
            return TrainTrackType.STATION.equals(trackType);
        }

        public boolean isTrackCrossing() {
            return TrainTrackType.DIAGONAL_CROSSING.equals(trackType) || TrainTrackType.STRAIGHT_CROSSING.equals(
                    trackType);
        }

        public boolean isStationOrTrackCrossing() {
            return isStation() || isTrackCrossing();
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


        public Stream<TrainTrack> possibleConnectableTracks() {
            Builder<TrainTrack> possibleConnectableTracks = Stream.builder();
            TrainTrackConnection trackConnection = trackType.trackConnection;
            for (TrainTrackDirection connectableDirection : trackConnection.connectableDirections) {
                if (isStationOrTrackCrossing() && !connectableDirection.equals(getTrackDirection())) {
                    continue;
                }
                Set<Character> trackSymbols = trackConnection.getNeededTrackSymbols(connectableDirection);
                for (char trackSymbol : trackSymbols) {
                    int row = this.row + connectableDirection.rowOffset;
                    int column = this.column + connectableDirection.columnOffset;
                    int trackPosition = this.trackPosition + 1;
                    TrainTrack connectableTrainTrack = newTrainTrack(row, column, trackPosition, trackSymbol);
                    possibleConnectableTracks.add(connectableTrainTrack);
                }
            }
            return possibleConnectableTracks.build();
        }

        public TrainTrackDirection getTrackDirection() {
            for (TrainTrackDirection trackDirection : TrainTrackDirection.values()) {
                if (previousTrack.row + trackDirection.rowOffset == row && //
                        previousTrack.column + trackDirection.columnOffset == column) {
                    return trackDirection;
                }
            }
            return null;
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
                    .append(trackPosition, that.trackPosition)
                    .append(trackType, that.trackType)
                    .isEquals();
        }

        @Override
        public int hashCode() {
            return new HashCodeBuilder(17, 37).append(row)
                    .append(column)
                    .append(trackPosition)
                    .append(trackType)
                    .toHashCode();
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this, ToStringStyle.NO_CLASS_NAME_STYLE).append("row", row)
                    .append("column", column)
                    .append("trackPosition", trackPosition)
                    .append("trackType", trackType)
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

        public static final char EXPRESS_TRAIN_SYMBOL = 'x';

        char trainSymbol;
        int carriageLength;
        boolean movesClockwise;
        int waitingInStation = 0;

        Deque<TrainTrack> occupiedTracks = new LinkedList<>();

        public Train(char trainSymbol, int carriageLength, boolean movesClockwise) {
            this.trainSymbol = Character.toLowerCase(trainSymbol);
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
                    currentTrack = currentTrack.nextTrack;
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
            occupiedTracks.offerFirst(nextTrack);
            occupiedTracks.pollLast();

            if (nextTrack.isStation()) {
                waitingInStation = getStationWaitingTime();
            }
        }


        public int getStationWaitingTime() {
            if (trainSymbol == EXPRESS_TRAIN_SYMBOL) {
                return 0;
            } else {
                return carriageLength;
            }
        }

        public boolean isCrashedWithItself() {
            List<TrainTrack> statoinAndTrackCrossings = occupiedTracks() //
                    .filter(TrainTrack::isStationOrTrackCrossing) //
                    .collect(Collectors.toList()); //
            for (int i = 0; i < statoinAndTrackCrossings.size(); i++) {
                TrainTrack occupiedTrack = statoinAndTrackCrossings.get(i);
                for (int j = i + 1; j < statoinAndTrackCrossings.size(); j++) {
                    TrainTrack laterOccupiedTrack = statoinAndTrackCrossings.get(j);
                    if (occupiedTrack.isAtPosition(laterOccupiedTrack)) {
                        return true;
                    }
                }
            }
            return false;
        }

        public boolean isCrashedWith(Train otherTrain) {
            return occupiedTracks() // they have any occupied TrainTrack at same position
                    .anyMatch(trainTrack -> otherTrain.occupiedTracks().anyMatch(trainTrack::isAtPosition));
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


    public enum TrainTrackType {

        STRAIGTH_VERTICAL('|', new TrainTrackConnection() //
                .addConnectableDirection(TrainTrackDirection.TOP) //
                .addAnyTrackType() //
                .addConnectableDirection(TrainTrackDirection.BOTTOM) //
                .addAnyTrackType()), //

        STRAIGTH_HORIZONTAL('-', new TrainTrackConnection() //
                .addConnectableDirection(TrainTrackDirection.LEFT) //
                .addAnyTrackType() //
                .addConnectableDirection(TrainTrackDirection.RIGHT) //
                .addAnyTrackType() //
        ), //

        CURVE_RIGHT('/', new TrainTrackConnection() //
                .addConnectableDirection(TrainTrackDirection.TOP) //
                .addNeededTrackType('|', '+', 'S') //
                .addConnectableDirection(TrainTrackDirection.BOTTOM) //
                .addNeededTrackType('|', '+', 'S') //
                .addConnectableDirection(TrainTrackDirection.RIGHT) //
                .addNeededTrackType('-', '+', 'S') //
                .addConnectableDirection(TrainTrackDirection.LEFT) //
                .addNeededTrackType('-', '+', 'S') //
                .addConnectableDirection(TrainTrackDirection.TOP_RIGHT) //
                .addNeededTrackType('/', 'X', 'S') //
                .addConnectableDirection(TrainTrackDirection.BOTTOM_LEFT) //
                .addNeededTrackType('/', 'X', 'S')), //

        CURVE_LEFT('\\', new TrainTrackConnection() //
                .addConnectableDirection(TrainTrackDirection.TOP) //
                .addNeededTrackType('|', '+', 'S') //
                .addConnectableDirection(TrainTrackDirection.BOTTOM) //
                .addNeededTrackType('|', '+', 'S') //
                .addConnectableDirection(TrainTrackDirection.RIGHT) //
                .addNeededTrackType('-', '+', 'S') //
                .addConnectableDirection(TrainTrackDirection.LEFT) //
                .addNeededTrackType('-', '+', 'S') //
                .addConnectableDirection(TrainTrackDirection.TOP_LEFT) //
                .addNeededTrackType('\\', 'X', 'S') //
                .addConnectableDirection(TrainTrackDirection.BOTTOM_RIGHT) //
                .addNeededTrackType('\\', 'X', 'S')), //

        STRAIGHT_CROSSING('+', new TrainTrackConnection() //
                .addConnectableDirection(TrainTrackDirection.TOP) //
                .addNeededTrackType('|', '/', '\\', '+', 'S') //
                .addConnectableDirection(TrainTrackDirection.BOTTOM) //
                .addNeededTrackType('|', '/', '\\', '+', 'S') //
                .addConnectableDirection(TrainTrackDirection.RIGHT) //
                .addNeededTrackType('-', '/', '\\', '+', 'S') //
                .addConnectableDirection(TrainTrackDirection.LEFT) //
                .addNeededTrackType('-', '/', '\\', '+', 'S')), //

        DIAGONAL_CROSSING('X', new TrainTrackConnection() //
                .addConnectableDirection(TrainTrackDirection.TOP_RIGHT) //
                .addNeededTrackType('/', '-', '|', 'X', 'S') //
                .addConnectableDirection(TrainTrackDirection.BOTTOM_LEFT) //
                .addNeededTrackType('/', '-', '|', 'X', 'S') //
                .addConnectableDirection(TrainTrackDirection.TOP_LEFT) //
                .addNeededTrackType('\\', '-', '|', 'X', 'S') //
                .addConnectableDirection(TrainTrackDirection.BOTTOM_RIGHT) //
                .addNeededTrackType('\\', '-', '|', 'X', 'S')), //

        STATION('S', new TrainTrackConnection() //
                .addConnectableDirection(TrainTrackDirection.TOP) //
                .addNeededTrackType('|', '+') //
                .addConnectableDirection(TrainTrackDirection.BOTTOM) //
                .addNeededTrackType('|', '+') //
                .addConnectableDirection(TrainTrackDirection.RIGHT) //
                .addNeededTrackType('-', '+') //
                .addConnectableDirection(TrainTrackDirection.LEFT) //
                .addNeededTrackType('-', '+') //
                .addConnectableDirection(TrainTrackDirection.TOP_RIGHT) //
                .addNeededTrackType('/', 'X') //
                .addConnectableDirection(TrainTrackDirection.BOTTOM_LEFT) //
                .addNeededTrackType('/', 'X') //
                .addConnectableDirection(TrainTrackDirection.TOP_LEFT) //
                .addNeededTrackType('\\', 'X') //
                .addConnectableDirection(TrainTrackDirection.BOTTOM_RIGHT) //
                .addNeededTrackType('\\', 'X')), //
        ;

        char trackSymbol;
        TrainTrackConnection trackConnection;

        TrainTrackType(char trackSymbol, TrainTrackConnection trackConnection) {
            this.trackSymbol = trackSymbol;
            this.trackConnection = trackConnection;
        }

        public static TrainTrackType valueOf(char trackSymbol) {
            for (TrainTrackType trackType : values()) {
                if (trackType.trackSymbol == trackSymbol) {
                    return trackType;
                }
            }
            return null;
        }

        @Override
        public String toString() {
            return String.valueOf(trackSymbol);
        }

    }


    public static class TrainTrackConnection {

        List<TrainTrackDirection> connectableDirections = new ArrayList<>();
        List<Set<Character>> allowedTrackSymbols = new ArrayList<>();

        public TrainTrackConnection addConnectableDirection(TrainTrackDirection trackDirection) {
            connectableDirections.add(trackDirection);
            allowedTrackSymbols.add(new HashSet<>());
            return this;
        }

        public TrainTrackConnection addNeededTrackType(char trackSymbol, char... trackSymbols) {
            Set<Character> neededtrackTypes = allowedTrackSymbols.get(allowedTrackSymbols.size() - 1);
            neededtrackTypes.add(trackSymbol);
            for (char tSymbol : trackSymbols) {
                neededtrackTypes.add(tSymbol);
            }
            return this;
        }

        public TrainTrackConnection addAnyTrackType() {
            Set<Character> neededtrackTypes = allowedTrackSymbols.get(allowedTrackSymbols.size() - 1);
            for (char trackSymbol : TrainTrack.TRAIN_TRACK_SYMBOLS) {
                neededtrackTypes.add(trackSymbol);
            }
            return this;
        }

        public Set<Character> getNeededTrackSymbols(TrainTrackDirection trackDirection) {
            for (int i = 0; i < connectableDirections.size(); i++) {
                if (connectableDirections.get(i).equals(trackDirection)) {
                    return allowedTrackSymbols.get(i);
                }
            }
            return Collections.emptySet();
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

    }

}
