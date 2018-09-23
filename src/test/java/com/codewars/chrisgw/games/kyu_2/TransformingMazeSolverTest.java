package com.codewars.chrisgw.games.kyu_2;

import com.codewars.chrisgw.games.kyu_2.TransformingMazeSolver.Direction;
import com.codewars.chrisgw.games.kyu_2.TransformingMazeSolver.MazeCell;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import static org.junit.Assert.*;


public class TransformingMazeSolverTest {

    final static private int[][][] example_tests = { { //
            { 4, 2, 5, 4 }, //
            { 4, 15, 11, 1 }, //
            { -1, 9, 6, 8 }, //
            { 12, 7, 7, -2 } //
    }, { //
            { 6, 3, 10, 4, 11 }, //
            { 8, 10, 4, 8, 5 }, //
            { -1, 14, 11, 3, -2 }, //
            { 15, 3, 4, 14, 15 }, //
            { 14, 7, 15, 5, 5 } //
    }, { //
            { 9, 1, 9, 0, 13, 0 }, //
            { 14, 1, 11, 2, 11, 4 }, //
            { -1, 2, 11, 0, 0, 15 }, //
            { 4, 3, 9, 6, 3, -2 } //
    }, { //
            { -1, 6, 12, 15, 11 }, //
            { 8, 7, 15, 7, 10 }, //
            { 13, 7, 13, 15, -2 }, //
            { 11, 10, 8, 1, 3 }, //
            { 12, 6, 9, 14, 7 } //
    }, { //
            { 6, 3, 0, 9, 14, 13, 14 }, //
            { -1, 14, 9, 11, 15, 14, 15 }, //
            { 2, 15, 0, 12, 6, 15, -2 }, //
            { 4, 10, 7, 6, 15, 5, 3 }, //
            { 7, 3, 13, 13, 14, 7, 0 } //
    } }; //

    final static private List<List<String>> example_sols = Arrays.asList( //
            Arrays.asList("NNE", "EE", "S", "SS"), //
            Arrays.asList("", "", "E", "", "E", "NESE"), //
            Arrays.asList("E", "SE", "", "E", "E", "E"), //
            null, //
            null);


    @Test
    public void exampleTests() {
        for (int i = 0; i < example_sols.size(); i++) {
            verify(example_tests[i], example_sols.get(i));
        }
    }

    private void verify(int[][] example_test, List<String> strings) {
        TransformingMazeSolver transformingMazeSolver = new TransformingMazeSolver(example_test);
        System.out.println(transformingMazeSolver);
        List<String> solve = transformingMazeSolver.solve();
        System.out.println(solve);
        System.out.println(strings);
    }


    @Test
    public void mazeCell_0_nonBoarder() {
        int mazeCellCode = 0;
        TransformingMazeSolver transformingMazeSolver = new TransformingMazeSolver(example_tests[0]);
        MazeCell mazeCell = transformingMazeSolver.new MazeCell(0, 0, mazeCellCode);
        for (Direction direction : Direction.values()) {
            assertFalse("should have no boarder in direction " + direction, mazeCell.hasBoarderTo(direction));
        }
    }

    @Test
    public void mazeCell_directions_withBoarder() {
        Direction[] directions = Direction.values();
        Map<Direction, Integer> directionToBoarderCode = new HashMap<>(directions.length);
        directionToBoarderCode.put(Direction.N, 0b0001);
        directionToBoarderCode.put(Direction.W, 0b0010);
        directionToBoarderCode.put(Direction.S, 0b0100);
        directionToBoarderCode.put(Direction.E, 0b1000);

        TransformingMazeSolver transformingMazeSolver = new TransformingMazeSolver(example_tests[0]);
        for (Entry<Direction, Integer> directionToBoarderCodeEntry : directionToBoarderCode.entrySet()) {
            Direction directionWithBoarder = directionToBoarderCodeEntry.getKey();
            int boarderCode = directionToBoarderCodeEntry.getValue();

            MazeCell mazeCell = transformingMazeSolver.new MazeCell(0, 0, boarderCode);
            for (Direction direction : directions) {
                if (direction.equals(directionWithBoarder)) {
                    assertTrue("should have boarder in direction " + direction, mazeCell.hasBoarderTo(direction));
                } else {
                    assertFalse("should have no boarder in direction " + direction, mazeCell.hasBoarderTo(direction));
                }
            }
        }
    }

    @Test
    public void mazeCell_15_boarderAllSides() {
        int mazeCellCode = 15;
        TransformingMazeSolver transformingMazeSolver = new TransformingMazeSolver(example_tests[0]);
        MazeCell mazeCell = transformingMazeSolver.new MazeCell(0, 0, mazeCellCode);
        for (Direction direction : Direction.values()) {
            assertTrue("should have boarder in direction " + direction, mazeCell.hasBoarderTo(direction));
        }
    }

    @Test
    public void mazeCell_ball_nonBoarder() {
        int mazeCellCode = -1;
        TransformingMazeSolver transformingMazeSolver = new TransformingMazeSolver(example_tests[0]);
        MazeCell mazeCell = transformingMazeSolver.new MazeCell(0, 0, mazeCellCode);
        for (Direction direction : Direction.values()) {
            assertFalse("should have no boarder in direction " + direction, mazeCell.hasBoarderTo(direction));
        }
    }

    @Test
    public void mazeCell_target_nonBoarder() {
        int mazeCellCode = -2;
        TransformingMazeSolver transformingMazeSolver = new TransformingMazeSolver(example_tests[0]);
        MazeCell mazeCell = transformingMazeSolver.new MazeCell(0, 0, mazeCellCode);
        for (Direction direction : Direction.values()) {
            assertFalse("should have no boarder in direction " + direction, mazeCell.hasBoarderTo(direction));
        }
    }

}
