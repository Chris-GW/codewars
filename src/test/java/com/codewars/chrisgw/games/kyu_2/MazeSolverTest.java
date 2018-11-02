package com.codewars.chrisgw.games.kyu_2;

import com.codewars.chrisgw.games.kyu_2.MazeSolver.Direction;
import com.codewars.chrisgw.games.kyu_2.MazeSolver.MazeCell;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import static org.junit.Assert.*;


public class MazeSolverTest {


    @Test
    public void exampleMaze_1() {
        int[][] maze = { //
                { 4, 2, 5, 4 }, //
                { 4, 15, 11, 1 }, //
                { -1, 9, 6, 8 }, //
                { 12, 7, 7, -2 } //
        };
        MazeSolver mazeSolver = new MazeSolver(maze);
        System.out.println(mazeSolver);
        List<String> mySolution = mazeSolver.solve();
        List<String> expectedSolution = Arrays.asList("NNE", "EE", "S", "SS");
        assertEquals(expectedSolution, mySolution);
    }

    @Test
    public void exampleMaze_2() {
        int[][] maze = { //
                { 6, 3, 10, 4, 11 }, //
                { 8, 10, 4, 8, 5 }, //
                { -1, 14, 11, 3, -2 }, //
                { 15, 3, 4, 14, 15 }, //
                { 14, 7, 15, 5, 5 } //
        };
        MazeSolver mazeSolver = new MazeSolver(maze);
        System.out.println(mazeSolver);
        List<String> mySolution = mazeSolver.solve();
        List<String> expectedSolution = Arrays.asList("", "", "E", "", "E", "NESE");
        assertEquals(expectedSolution, mySolution);
    }


    @Test
    public void exampleMaze_4_noSoltuon() {
        int[][] maze = { //
                { -1, 6, 12, 15, 11 }, //
                { 8, 7, 15, 7, 10 }, //
                { 13, 7, 13, 15, -2 }, //
                { 11, 10, 8, 1, 3 }, //
                { 12, 6, 9, 14, 7 } //
        };
        MazeSolver mazeSolver = new MazeSolver(maze);
        System.out.println(mazeSolver);
        List<String> mySolution = mazeSolver.solve();
        List<String> expectedSolution = null;
        assertEquals(expectedSolution, mySolution);
    }

    @Test
    public void exampleMaze_5_noSolution() {
        int[][] maze = { //
                { 6, 3, 0, 9, 14, 13, 14 }, //
                { -1, 14, 9, 11, 15, 14, 15 }, //
                { 2, 15, 0, 12, 6, 15, -2 }, //
                { 4, 10, 7, 6, 15, 5, 3 }, //
                { 7, 3, 13, 13, 14, 7, 0 } //
        };
        MazeSolver mazeSolver = new MazeSolver(maze);
        System.out.println(mazeSolver);
        List<String> mySolution = mazeSolver.solve();
        List<String> expectedSolution = null;
        assertEquals(expectedSolution, mySolution);
    }


    @Test
    public void mazeCell_0_nonBorder() {
        int[][] maze = { new int[] { 0 } };
        MazeSolver mazeSolver = new MazeSolver(maze);
        MazeCell mazeCell = mazeSolver.getMazeCell(0, 0, 0);
        for (Direction direction : Direction.values()) {
            assertFalse("should have no border in direction " + direction, mazeCell.hasBorderTo(direction));
        }
    }

    @Test
    public void mazeCell_directions_withSingleBorder() {
        Map<Direction, Integer> directionToBorderCode = new HashMap<>(4);
        directionToBorderCode.put(Direction.N, 0b1000);
        directionToBorderCode.put(Direction.W, 0b0100);
        directionToBorderCode.put(Direction.S, 0b0010);
        directionToBorderCode.put(Direction.E, 0b0001);

        validateCellBorders(directionToBorderCode, 0);
    }

    @Test
    public void mazeCell_directions_withSingleBorder_roateRight() {
        Map<Direction, Integer> directionToBorderCode = new HashMap<>(4);
        directionToBorderCode.put(Direction.N, 0b0100);
        directionToBorderCode.put(Direction.W, 0b0010);
        directionToBorderCode.put(Direction.S, 0b0001);
        directionToBorderCode.put(Direction.E, 0b1000);

        validateCellBorders(directionToBorderCode, 1);
    }

    private void validateCellBorders(Map<Direction, Integer> directionToBorderCode, int interval) {
        for (Entry<Direction, Integer> directionToBorderCodeEntry : directionToBorderCode.entrySet()) {
            Direction directionWithBorder = directionToBorderCodeEntry.getKey();
            int borderCode = directionToBorderCodeEntry.getValue();
            int[][] maze = { new int[] { borderCode } };
            MazeSolver mazeSolver = new MazeSolver(maze);
            System.out.println(mazeSolver);
            MazeCell mazeCell = mazeSolver.getMazeCell(0, 0, interval);

            for (Direction direction : Direction.values()) {
                if (direction.equals(directionWithBorder)) {
                    assertTrue("should have border in direction " + direction, mazeCell.hasBorderTo(direction));
                } else {
                    assertFalse("should have no border in direction " + direction, mazeCell.hasBorderTo(direction));
                }
            }
        }
    }

    @Test
    public void mazeCell_15_borderAllSides() {
        int mazeCellCode = 15;
        int[][] maze = { new int[] { mazeCellCode } };
        MazeSolver mazeSolver = new MazeSolver(maze);
        System.out.println(mazeSolver);
        MazeCell mazeCell = mazeSolver.getMazeCell(0, 0, 0);

        for (Direction direction : Direction.values()) {
            assertTrue("should have border in direction " + direction, mazeCell.hasBorderTo(direction));
        }
    }

    @Test
    public void mazeCell_ball_nonBorder() {
        int mazeCellCode = -1;
        int[][] maze = { new int[] { mazeCellCode } };
        MazeSolver mazeSolver = new MazeSolver(maze);
        System.out.println(mazeSolver);
        MazeCell mazeCell = mazeSolver.getMazeCell(0, 0, 0);

        for (Direction direction : Direction.values()) {
            assertFalse("should have no border in direction " + direction, mazeCell.hasBorderTo(direction));
        }
    }

    @Test
    public void mazeCell_target_nonBorder() {
        int mazeCellCode = -2;
        int[][] maze = { new int[] { mazeCellCode } };
        MazeSolver mazeSolver = new MazeSolver(maze);
        System.out.println(mazeSolver);
        MazeCell mazeCell = mazeSolver.getMazeCell(0, 0, 0);

        for (Direction direction : Direction.values()) {
            assertFalse("should have no border in direction " + direction, mazeCell.hasBorderTo(direction));
        }
    }

}
