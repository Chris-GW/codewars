package com.codewars.chrisgw.reference.kyu;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;


/**
 * Forking Knights
 * https://www.codewars.com/kata/forking-knights
 */
public class ForkingKnightsTest {

    @Test
    public void multiple_forks_by_white() {
        char[][] board = new char[][] { //
                { 'n', ' ', 'r', ' ', ' ', ' ', ' ', ' ' }, //
                { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, //
                { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, //
                { 'p', ' ', 'p', ' ', ' ', ' ', ' ', ' ' }, //
                { ' ', ' ', 'q', ' ', 'N', ' ', 'N', ' ' }, //
                { ' ', 'q', ' ', ' ', ' ', ' ', ' ', 'p' }, //
                { ' ', ' ', ' ', ' ', ' ', 'x', ' ', ' ' }, //
                { 'N', ' ', ' ', ' ', ' ', ' ', ' ', 'k' }  //
        };
        Chess ch = new Chess(board);
        System.out.println(ch);
        Set<String> expected = new HashSet<>(Arrays.asList("Ne4-d6", "Ne4-d2", "Ne4xf2", "Ng4xf2", "Na1xb3"));
        Set<String> result = ch.knightFork("white");
        assertEquals(expected, result);
        expected = new HashSet<>();
        result = ch.knightFork("black");
        assertEquals(expected, result);
    }

    @Test
    public void single_fork_by_black() {
        char[][] board = new char[][] { //
                { 'n', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, //
                { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, //
                { ' ', 'P', ' ', ' ', ' ', ' ', ' ', ' ' }, //
                { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, //
                { 'K', ' ', 'Q', ' ', ' ', ' ', ' ', ' ' }, //
                { ' ', 'q', ' ', ' ', ' ', ' ', ' ', ' ' }, //
                { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, //
                { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }  //
        };
        Chess ch = new Chess(board);
        System.out.println(ch);
        Set<String> expected = new HashSet<>();
        Set<String> result = ch.knightFork("white");
        assertEquals(expected, result);
        expected.add("Na8xb6");
        result = ch.knightFork("black");
        assertEquals(expected, result);

    }

    @Test
    public void empty_board() {
        System.out.println();
        char[][] board = new char[][] { //
                { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, //
                { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, //
                { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, //
                { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, //
                { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, //
                { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, //
                { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, //
                { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }  //
        };
        Chess ch = new Chess(board);
        System.out.println(ch);
        Set<String> expected = new HashSet<>();
        Set<String> result = ch.knightFork("white");
        assertEquals(expected, result);
        result = ch.knightFork("black");
        assertEquals(expected, result);
    }

}
