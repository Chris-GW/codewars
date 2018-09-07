package com.codewars.chrisgw.algorithms.kyu_3;

import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;


/**
 * <img src="https://i.imgur.com/ta6gv1i.png?1" title="weekly coding challenge" />
 * <p>
 * <p>
 * *I bet you won't ever catch a Lift (a.k.a. elevator) again without thinking of this Kata !*
 *
 * <hr>
 * <p>
 * # Synopsis
 * <p>
 * A multi-floorLevel building has a Lift in it.
 * <p>
 * People are queued on different floors waiting for the Lift.
 * <p>
 * Some people want to go up. Some people want to go down.
 * <p>
 * The floorLevel they want to go to is represented by a number (i.e. when they enter the Lift this is the button they will press)
 * <p>
 * ```
 * BEFORE (people waiting in queues)               AFTER (people at their destinations)
 * +--+                                          +--+
 * /----------------|  |----------------\        /----------------|  |----------------\
 * 10|                |  | 1,4,3,2        |      10|             10 |  |                |
 * |----------------|  |----------------|        |----------------|  |----------------|
 * 9|                |  | 1,10,2         |       9|                |  |                |
 * |----------------|  |----------------|        |----------------|  |----------------|
 * 8|                |  |                |       8|                |  |                |
 * |----------------|  |----------------|        |----------------|  |----------------|
 * 7|                |  | 3,6,4,5,6      |       7|                |  |                |
 * |----------------|  |----------------|        |----------------|  |----------------|
 * 6|                |  |                |       6|          6,6,6 |  |                |
 * |----------------|  |----------------|        |----------------|  |----------------|
 * 5|                |  |                |       5|            5,5 |  |                |
 * |----------------|  |----------------|        |----------------|  |----------------|
 * 4|                |  | 0,0,0          |       4|          4,4,4 |  |                |
 * |----------------|  |----------------|        |----------------|  |----------------|
 * 3|                |  |                |       3|            3,3 |  |                |
 * |----------------|  |----------------|        |----------------|  |----------------|
 * 2|                |  | 4              |       2|          2,2,2 |  |                |
 * |----------------|  |----------------|        |----------------|  |----------------|
 * 1|                |  | 6,5,2          |       1|            1,1 |  |                |
 * |----------------|  |----------------|        |----------------|  |----------------|
 * G|                |  |                |       G|          0,0,0 |  |                |
 * |====================================|        |====================================|
 * ```
 * <hr>
 * # Rules
 * <p>
 * #### Lift Rules
 * <p>
 * * The Lift only goes up or down!
 * * Each floorLevel has both UP and DOWN Lift-call buttons (except top and ground floors which have only DOWN and UP respectively)
 * * The Lift never changes direction until there are no more people wanting to get on/off in the direction it is already travelling
 * * When empty the Lift tries to be smart. For example,
 * * If it was going up then it may continue up to collect the highest floorLevel person wanting to go down
 * * If it was going down then it may continue down to collect the lowest floorLevel person wanting to go up
 * * The Lift has a maximum capacity of people
 * * When called, the Lift will stop at a floorLevel **even if it is full**, although unless somebody gets off nobody else can get on!
 * * If the lift is empty, and no people are waiting, then it will return to the ground floorLevel
 * <p>
 * #### People Rules
 * <p>
 * * People are in **"queues"** that represent their order of arrival to wait for the Lift
 * * All people can press the UP/DOWN Lift-call buttons
 * * Only people going the **same direction** as the Lift may enter it, and they do so according to their **"queue" order**
 * * If a person is unable to enter a full Lift, they will press the UP/DOWN Lift-call button again after it has departed without them
 *
 *
 * <hr>
 * # Kata Task
 * <p>
 * * Get all the people to the floors they want to go to while obeying the **Lift rules** and the **People rules**
 * * Return a list of all floors that the Lift stopped at (in the order visited!)
 * <p>
 * NOTE: The Lift always starts on the ground floorLevel (and people waiting on the ground floorLevel may enter immediately)
 *
 * <hr>
 * # I/O
 * <p>
 * #### Input
 * <p>
 * * ```queues``` a list of queues of people for all floors of the building.
 * * The height of the building varies
 * * 0 = the ground floorLevel
 * * Not all floors have queues
 * * Queue index [0] is the "head" of the queue
 * * Numbers indicate which floorLevel the person wants go to
 * * ```capacity``` maximum number of people allowed in the lift
 * <p>
 * *Parameter validation* - All input parameters can be assumed OK. No need to check for things like:
 * <p>
 * * People wanting to go to floors that do not exist
 * * People wanting to take the Lift to the floorLevel they are already on
 * * Buildings with < 2 floors
 * * Basements
 * <p>
 * #### Output
 * <p>
 * * A list of all floors that the Lift stopped at (in the order visited!)
 *
 * <hr>
 * # Example
 * <p>
 * Refer to the example test cases.
 *
 * <hr>
 * Language Notes
 * <p>
 * * Python : The object will be initialized for you in the tests
 *
 * <hr>
 * <span style="color:red">
 * Good Luck -
 * <p>
 * DM
 *
 * </span>
 */
public class TheLift {

    private Floor[] buildingFloors;
    private Lift lift;


    public TheLift(Floor[] buildingFloors, int personCapacity) {
        this.buildingFloors = buildingFloors;
        this.lift = new Lift(buildingFloors[0], personCapacity);
    }


    public static int[] theLift(final int[][] queues, final int personCapacity) {
        if (queues == null || queues.length == 0) {
            return new int[0];
        }
        Floor[] buildingFloors = createFloorsWithPersonQueues(queues);
        TheLift theLift = new TheLift(buildingFloors, personCapacity);
        theLift.lift.operateLift();
        return theLift.lift.stoppedFloors.stream().mapToInt(Floor::getFloorLevel).toArray();
    }

    private static Floor[] createFloorsWithPersonQueues(int[][] queues) {
        Floor[] buildingFloors = new Floor[queues.length];
        for (int floorLevel = 0; floorLevel < queues.length; floorLevel++) {
            Deque<Person> floorPersonQueue = Arrays.stream(queues[floorLevel])
                    .mapToObj(Person::new)
                    .collect(Collectors.toCollection(LinkedList::new));
            buildingFloors[floorLevel] = new Floor(floorLevel, floorPersonQueue);
        }
        return buildingFloors;
    }


    @Override
    public String toString() {
        String floorSeparationStr = StringUtils.repeat("-", 22);
        StringBuilder sb = new StringBuilder();
        sb.append("  /").append(floorSeparationStr).append("|  |");
        sb.append(floorSeparationStr).append("\\").append("\n");

        for (int floorLevel = buildingFloors.length - 1; floorLevel >= 0; floorLevel--) {
            sb.append(StringUtils.leftPad(String.valueOf(floorLevel), 2)).append("| ");
            Floor floor = buildingFloors[floorLevel];
            String personsAtDesinationFloorStr = floor.personsAtDesinationFloor()
                    .map(person -> String.valueOf(person.destinationFloor))
                    .collect(Collectors.joining(", "));
            sb.append(StringUtils.leftPad(personsAtDesinationFloorStr, 20));

            sb.append(" |  | ");

            String personsGoingToOtherFloorStr = floor.personsGoingToOtherFloor()
                    .map(person -> String.valueOf(person.destinationFloor))
                    .collect(Collectors.joining(", "));
            sb.append(StringUtils.leftPad(personsGoingToOtherFloorStr, 20));
            sb.append(" |\n");
            sb.append("  |").append(floorSeparationStr).append("|  |").append(floorSeparationStr).append("|\n");
        }
        sb.append("Persons loaded in Lift: " + lift.loadedPersons);
        return sb.toString();
    }

    private static class Floor {

        private int floorLevel;
        private Deque<Person> personQueue;


        public Floor(int floorLevel, Deque<Person> personQueue) {
            this.floorLevel = floorLevel;
            this.personQueue = personQueue;
        }

        public Stream<Person> personsAtDesinationFloor() {
            return personQueue.stream().filter(person -> floorLevel == person.destinationFloor);
        }

        public Stream<Person> personsGoingToOtherFloor() {
            return personQueue.stream().filter(person -> floorLevel != person.destinationFloor);
        }

        public Stream<Person> personsGoingUpwards() {
            return personQueue.stream().filter(person -> floorLevel < person.destinationFloor);
        }

        public Stream<Person> personsGoingDownwards() {
            return personQueue.stream().filter(person -> floorLevel > person.destinationFloor);
        }

        public int getFloorLevel() {
            return floorLevel;
        }

        @Override
        public String toString() {
            return String.valueOf(floorLevel);
        }

    }


    private class Lift {

        private Floor currentFloor;

        private boolean isGoingUpwards = true;
        private List<Floor> stoppedFloors;

        private int personCapazity;
        private List<Person> loadedPersons;


        public Lift(Floor startingFloor, int personCapazity) {
            this.currentFloor = startingFloor;
            this.personCapazity = personCapazity;
            this.loadedPersons = new LinkedList<>();
            this.stoppedFloors = new LinkedList<>();
            this.stoppedFloors.add(startingFloor);
        }


        public int getFreePersonCapazity() {
            return personCapazity - loadedPersons.size();
        }

        private boolean isEmpty() {
            return loadedPersons.isEmpty();
        }


        private void moveLift(Floor destinationFloor) {
            System.out.println("moveLift from Floor " + currentFloor + " to Floor " + destinationFloor);
            currentFloor = destinationFloor;
            stoppedFloors.add(currentFloor);
        }


        public void operateLift() {
            while (true) {
                unloadPersons();
                loadPersons();
                Optional<Floor> nextFloor = nextFloorInTravelDirection();
                if (!nextFloor.isPresent()) {
                    nextFloor = latestFloorWithPersonsWantToEnter();
                    isGoingUpwards = !isGoingUpwards;
                }
                if (nextFloor.isPresent()) {
                    moveLift(nextFloor.get());
                } else {
                    moveLift(buildingFloors[0]);
                    break;
                }
                System.out.println(TheLift.this.toString());
            }
        }


        private Optional<Floor> nextFloorInTravelDirection() {
            Optional<Floor> firstFloorPersonsWantToExit = firstFloorInDirectionWherePersonsWantExit();
            Optional<Floor> firstFloorPersonsWantToEnter = firstFloorInDirectionWherePersonsWantToEnter();
            Floor nextFloor;
            if (isGoingUpwards) {
                nextFloor = Stream.of(firstFloorPersonsWantToEnter, firstFloorPersonsWantToExit)
                        .filter(Optional::isPresent)
                        .map(Optional::get)
                        .min(Comparator.comparingInt(Floor::getFloorLevel))
                        .orElse(null);
            } else {
                nextFloor = Stream.of(firstFloorPersonsWantToEnter, firstFloorPersonsWantToExit)
                        .filter(Optional::isPresent)
                        .map(Optional::get)
                        .max(Comparator.comparingInt(Floor::getFloorLevel))
                        .orElse(null);
            }
            return Optional.ofNullable(nextFloor);
        }


        private Optional<Floor> latestFloorWithPersonsWantToEnter() {
            if (isGoingUpwards) {
                return floorsInTravelDirection() //
                        .filter(floor -> floor.personsGoingDownwards().findAny().isPresent()) //
                        .max(Comparator.comparingInt(Floor::getFloorLevel)); //
            } else {
                return floorsInTravelDirection() //
                        .filter(floor -> floor.personsGoingUpwards().findAny().isPresent()) //
                        .min(Comparator.comparingInt(Floor::getFloorLevel)); //
            }
        }


        private Optional<Floor> firstFloorInDirectionWherePersonsWantExit() {
            return floorsInTravelDirection().filter(this::hasLoadedPersonWithDestination).findFirst();
        }

        private boolean hasLoadedPersonWithDestination(Floor floor) {
            return loadedPersons.stream()
                    .map(Person::getDestinationFloor)
                    .anyMatch(personDesinationFloorLevel -> floor.getFloorLevel() == personDesinationFloorLevel);
        }


        private Optional<Floor> firstFloorInDirectionWherePersonsWantToEnter() {
            return floorsInTravelDirection().filter(floor -> {
                if (isGoingUpwards) {
                    return floor.personsGoingUpwards().findAny().isPresent();
                } else {
                    return floor.personsGoingDownwards().findAny().isPresent();
                }
            }).findFirst();
        }


        private void unloadPersons() {
            for (int i = 0; i < loadedPersons.size(); i++) {
                Person person = loadedPersons.get(i);
                if (person.destinationFloor == currentFloor.floorLevel) {
                    loadedPersons.remove(i--);
                    currentFloor.personQueue.addLast(person);
                }
            }
            System.out.println("unloadPersons to Floor " + currentFloor + ": " + loadedPersons);
        }

        private void loadPersons() {
            List<Person> personsEntering;
            if (isGoingUpwards) {
                personsEntering = currentFloor.personsGoingUpwards() //
                        .limit(getFreePersonCapazity()) //
                        .collect(Collectors.toList());
            } else {
                personsEntering = currentFloor.personsGoingDownwards() //
                        .limit(getFreePersonCapazity()) //
                        .collect(Collectors.toList());
            }
            personsEntering.forEach(person -> {
                currentFloor.personQueue.remove(person);
                loadedPersons.add(person);
            });
            System.out.println("loadPersons from Floor " + currentFloor + ": " + loadedPersons);
        }


        private Stream<Floor> floorsInTravelDirection() {
            if (isGoingUpwards) {
                return IntStream.range(currentFloor.floorLevel + 1, buildingFloors.length)
                        .mapToObj(floorLevel -> buildingFloors[floorLevel]);
            } else {
                return IntStream.range(1, currentFloor.floorLevel)
                        .mapToObj(floorLevel -> buildingFloors[currentFloor.floorLevel - floorLevel]);
            }
        }

    }


    private static class Person {

        int destinationFloor;


        public Person(int destinationFloor) {
            this.destinationFloor = destinationFloor;
        }

        public int getDestinationFloor() {
            return destinationFloor;
        }

        @Override
        public String toString() {
            return String.valueOf(destinationFloor);
        }

    }

}
