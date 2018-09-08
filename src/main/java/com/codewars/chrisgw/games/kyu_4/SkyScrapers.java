package com.codewars.chrisgw.games.kyu_4;

import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


/**
 * <style type="text/css">
 * table {
 * width: 236px;
 * }
 * <p>
 * table, tr, td {
 * border: 0px;
 * }
 * </style>
 * In a grid of 4 by 4 squares you want to place a skyscraper in each square with only some clues:
 * <ul>
 * <li>The height of the skyscrapers is between 1 and 4</li>
 * <li>No two skyscrapers in a row or column may have the same number of floors</li>
 * <li>A clue is the number of skyscrapers that you can see in a row or column from the outside</li>
 * <li>Higher skyscrapers block the view of lower skyscrapers located behind them</li>
 * </ul>
 * <br />
 * Can you write a program that can solve this puzzle?
 * <br />
 * <br />
 * <b style='font-size:16px'>Example:</b>
 * <br />
 * <br />
 * To understand how the puzzle works, this is an example of a row with 2 clues. Seen from the left side there are 4 buildings visible while seen from the right side only 1:
 * <br />
 * <br />
 * <table>
 * <tr>
 * <td style='text-align:center; height:16px;'>&nbsp;4</td>
 * <td style='text-align:center; border: solid 1px;height:16px;border-color:gray;'>&nbsp;&nbsp;&nbsp;&nbsp;</td>
 * <td style='text-align:center; border: solid 1px;height:16px;border-color:gray;'>&nbsp;&nbsp;&nbsp;&nbsp;</td>
 * <td style='text-align:center; border: solid 1px;height:16px;border-color:gray;'>&nbsp;&nbsp;&nbsp;&nbsp;</td>
 * <td style='text-align:center; border: solid 1px;height:16px;border-color:gray;'>&nbsp;&nbsp;&nbsp;&nbsp;</td>
 * <td style='text-align:center; height:16px;'>&nbsp;1</td>
 * </tr>
 * </table>
 * <br />
 * There is only one way in which the skyscrapers can be placed. From left-to-right all four buildings must be visible and no building may hide behind another building:
 * <br />
 * <br />
 * <table>
 * <tr>
 * <td style='text-align:center; height:16px;'>&nbsp;4</td>
 * <td style='text-align:center; border: solid 1px;height:16px;border-color:gray;'>&nbsp;1</td>
 * <td style='text-align:center; border: solid 1px;height:16px;border-color:gray;'>&nbsp;2</td>
 * <td style='text-align:center; border: solid 1px;height:16px;border-color:gray;'>&nbsp;3</td>
 * <td style='text-align:center; border: solid 1px;height:16px;border-color:gray;'>&nbsp;4</td>
 * <td style='text-align:center; height:16px;'>&nbsp;1</td>
 * </tr>
 * </table>
 * <br />
 * Example of a 4 by 4 puzzle with the solution:
 * <br />
 * <br />
 * <table>
 * <tr>
 * <td style='text-align:center; border: 0px;height:16px;'>&nbsp;&nbsp;</td>
 * <td style='text-align:center; border-bottom: solid 1px;height:16px;border-color:gray;'>&nbsp;&nbsp;&nbsp;&nbsp;</td>
 * <td style='text-align:center; border-bottom: solid 1px;height:16px;border-color:gray;'>&nbsp;&nbsp;&nbsp;&nbsp;</td>
 * <td style='text-align:center; border-bottom: solid 1px;height:16px;border-color:gray;'>&nbsp;1</td>
 * <td style='text-align:center; border-bottom: solid 1px;height:16px;border-color:gray;'>&nbsp;2</td>
 * <td style='text-align:center; border: 0px;height:16px;'>&nbsp;&nbsp;</td>
 * </tr>
 * <tr>
 * <td style='text-align:center; border: 0px;height:16px;'>&nbsp;&nbsp;</td>
 * <td style='text-align:center; border: solid 1px;height:16px;border-color:gray;'>&nbsp;&nbsp;</td>
 * <td style='text-align:center; border: solid 1px;height:16px;border-color:gray;'>&nbsp;&nbsp;</td>
 * <td style='text-align:center; border: solid 1px;height:16px;border-color:gray;'>&nbsp;&nbsp;</td>
 * <td style='text-align:center; border: solid 1px;height:16px;border-color:gray;'>&nbsp;&nbsp;</td>
 * <td style='text-align:center; border: 0px;height:16px;'>&nbsp;&nbsp;</td>
 * </tr>
 * <tr>
 * <td style='text-align:center; border: 0px;height:16px;'>&nbsp;&nbsp;</td>
 * <td style='text-align:center; border: solid 1px;height:16px;border-color:gray;'>&nbsp;&nbsp;</td>
 * <td style='text-align:center; border: solid 1px;height:16px;border-color:gray;'>&nbsp;&nbsp;</td>
 * <td style='text-align:center; border: solid 1px;height:16px;border-color:gray;'>&nbsp;&nbsp;</td>
 * <td style='text-align:center; border: solid 1px;height:16px;border-color:gray;'>&nbsp;&nbsp;</td>
 * <td style='text-align:center; border: 0px;height:16px;'>&nbsp;2</td>
 * </tr>
 * <tr>
 * <td style='text-align:center; border: 0px;height:16px;'>&nbsp;1</td>
 * <td style='text-align:center; border: solid 1px;height:16px;border-color:gray;'>&nbsp;&nbsp;</td>
 * <td style='text-align:center; border: solid 1px;height:16px;border-color:gray;'>&nbsp;&nbsp;</td>
 * <td style='text-align:center; border: solid 1px;height:16px;border-color:gray;'>&nbsp;&nbsp;</td>
 * <td style='text-align:center; border: solid 1px;height:16px;border-color:gray;'>&nbsp;&nbsp;</td>
 * <td style='text-align:center; border: 0px;height:16px;'>&nbsp;&nbsp;</td>
 * </tr>
 * <tr>
 * <td style='text-align:center; border: 0px;height:16px;'>&nbsp;&nbsp;</td>
 * <td style='text-align:center; border: solid 1px;height:16px;border-color:gray;'>&nbsp;&nbsp;</td>
 * <td style='text-align:center; border: solid 1px;height:16px;border-color:gray;'>&nbsp;&nbsp;</td>
 * <td style='text-align:center; border: solid 1px;height:16px;border-color:gray;'>&nbsp;&nbsp;</td>
 * <td style='text-align:center; border: solid 1px;height:16px;border-color:gray;'>&nbsp;&nbsp;</td>
 * <td style='text-align:center; border: 0px;height:16px;'>&nbsp;&nbsp;</td>
 * </tr>
 * <tr>
 * <td style='text-align:center; border: 0px;height:16px;'>&nbsp;&nbsp;</td>
 * <td style='height:16px;'>&nbsp;&nbsp;</td>
 * <td style='height:16px;'>&nbsp;&nbsp;</td>
 * <td style='text-align:center; height:16px;'>&nbsp;3</td>
 * <td style='height:16px;'>&nbsp;&nbsp;</td>
 * <td style='text-align:center; border: 0px;height:16px;'>&nbsp;&nbsp;</td>
 * </tr>
 * </table>
 * <br />
 * <table>
 * <tr>
 * <td style='text-align:center; border: 0px;height:16px;'>&nbsp;&nbsp;</td>
 * <td style='text-align:center; border-bottom: solid 1px;height:16px;border-color:gray;'>&nbsp;&nbsp;</td>
 * <td style='text-align:center; border-bottom: solid 1px;height:16px;border-color:gray;'>&nbsp;&nbsp;</td>
 * <td style='text-align:center; border-bottom: solid 1px;height:16px;border-color:gray;'>&nbsp;1</td>
 * <td style='text-align:center; border-bottom: solid 1px;height:16px;border-color:gray;'>&nbsp;2</td>
 * <td style='text-align:center; border: 0px;height:16px;'>&nbsp;&nbsp;</td>
 * </tr>
 * <tr>
 * <td style='text-align:center; border: 0px;height:16px;'>&nbsp;&nbsp;</td>
 * <td style='text-align:center; border: solid 1px;height:16px;border-color:gray;'>&nbsp;2</td>
 * <td style='text-align:center; border: solid 1px;height:16px;border-color:gray;'>&nbsp;1</td>
 * <td style='text-align:center; border: solid 1px;height:16px;border-color:gray;'>&nbsp;4</td>
 * <td style='text-align:center; border: solid 1px;height:16px;border-color:gray;'>&nbsp;3</td>
 * <td style='text-align:center; border: 0px;height:16px;'>&nbsp;&nbsp;</td>
 * </tr>
 * <tr>
 * <td style='text-align:center; border: 0px;height:16px;'>&nbsp;&nbsp;</td>
 * <td style='text-align:center; border: solid 1px;height:16px;border-color:gray;'>&nbsp;3</td>
 * <td style='text-align:center; border: solid 1px;height:16px;border-color:gray;'>&nbsp;4</td>
 * <td style='text-align:center; border: solid 1px;height:16px;border-color:gray;'>&nbsp;1</td>
 * <td style='text-align:center; border: solid 1px;height:16px;border-color:gray;'>&nbsp;2</td>
 * <td style='text-align:center; border: 0px;height:16px;'>&nbsp;2</td>
 * </tr>
 * <tr>
 * <td style='text-align:center; border: 0px;height:16px;'>&nbsp;1</td>
 * <td style='text-align:center; border: solid 1px;height:16px;border-color:gray;'>&nbsp;4</td>
 * <td style='text-align:center; border: solid 1px;height:16px;border-color:gray;'>&nbsp;2</td>
 * <td style='text-align:center; border: solid 1px;height:16px;border-color:gray;'>&nbsp;3</td>
 * <td style='text-align:center; border: solid 1px;height:16px;border-color:gray;'>&nbsp;1</td>
 * <td style='text-align:center; border: 0px;height:16px;'>&nbsp;&nbsp;</td>
 * </tr>
 * <tr>
 * <td style='text-align:center; border: 0px;height:16px;'>&nbsp;&nbsp;</td>
 * <td style='text-align:center; border: solid 1px;height:16px;border-color:gray;'>&nbsp;1</td>
 * <td style='text-align:center; border: solid 1px;height:16px;border-color:gray;'>&nbsp;3</td>
 * <td style='text-align:center; border: solid 1px;height:16px;border-color:gray;'>&nbsp;2</td>
 * <td style='text-align:center; border: solid 1px;height:16px;border-color:gray;'>&nbsp;4</td>
 * <td style='text-align:center; border: 0px;height:16px;'>&nbsp;&nbsp;</td>
 * </tr>
 * <tr>
 * <td style='text-align:center; border: 0px;height:16px;'>&nbsp;&nbsp;</td>
 * <td style='height:16px;'>&nbsp;&nbsp;</td>
 * <td style='height:16px;'>&nbsp;&nbsp;</td>
 * <td style='text-align:center; height:16px;'>&nbsp;3</td>
 * <td style='height:16px;'>&nbsp;&nbsp;</td>
 * <td style='text-align:center; border: 0px;height:16px;'>&nbsp;&nbsp;</td>
 * </tr>
 * </table>
 * <br />
 * <b style='font-size:16px'>Task:</b>
 * <br />
 * <br />
 *
 * <ul>
 * <li>Finish:</li>
 * </ul>
 *
 * <ul>
 * <li>
 * Pass the clues in an array of 16 items. This array contains the clues around the clock, index:
 * <br />
 * <table>
 * <tr>
 * <td style='text-align:center; border: 0px;height:16px;'>&nbsp;&nbsp;</td>
 * <td style='text-align:center; border-bottom: solid 1px;height:16px;border-color:gray;'>&nbsp;0</td>
 * <td style='text-align:center; border-bottom: solid 1px;height:16px;border-color:gray;'>&nbsp;1</td>
 * <td style='text-align:center; border-bottom: solid 1px;height:16px;border-color:gray;'>&nbsp;&nbsp;&nbsp;2</td>
 * <td style='text-align:center; border-bottom: solid 1px;height:16px;border-color:gray;'>&nbsp;&nbsp;&nbsp;3</td>
 * <td style='text-align:center; border: 0px;height:16px;'>&nbsp;&nbsp;</td>
 * </tr>
 * <tr>
 * <td style='text-align:center; border: 0px;height:16px;'>&nbsp;15</td>
 * <td style='text-align:center; border: solid 1px;height:16px;border-color:gray;'>&nbsp;&nbsp;</td>
 * <td style='text-align:center; border: solid 1px;height:16px;border-color:gray;'>&nbsp;&nbsp;</td>
 * <td style='text-align:center; border: solid 1px;height:16px;border-color:gray;'>&nbsp;&nbsp;</td>
 * <td style='text-align:center; border: solid 1px;height:16px;border-color:gray;'>&nbsp;&nbsp;</td>
 * <td style='text-align:center; border: 0px;height:16px;'>&nbsp;4</td>
 * </tr>
 * <tr>
 * <td style='text-align:center; border: 0px;height:16px;'>&nbsp;14</td>
 * <td style='text-align:center; border: solid 1px;height:16px;border-color:gray;'>&nbsp;&nbsp;</td>
 * <td style='text-align:center; border: solid 1px;height:16px;border-color:gray;'>&nbsp;&nbsp;</td>
 * <td style='text-align:center; border: solid 1px;height:16px;border-color:gray;'>&nbsp;&nbsp;</td>
 * <td style='text-align:center; border: solid 1px;height:16px;border-color:gray;'>&nbsp;&nbsp;</td>
 * <td style='text-align:center; border: 0px;height:16px;'>&nbsp;5</td>
 * </tr>
 * <tr>
 * <td style='text-align:center; border: 0px;height:16px;'>&nbsp;13</td>
 * <td style='text-align:center; border: solid 1px;height:16px;border-color:gray;'>&nbsp;&nbsp;</td>
 * <td style='text-align:center; border: solid 1px;height:16px;border-color:gray;'>&nbsp;&nbsp;</td>
 * <td style='text-align:center; border: solid 1px;height:16px;border-color:gray;'>&nbsp;&nbsp;</td>
 * <td style='text-align:center; border: solid 1px;height:16px;border-color:gray;'>&nbsp;&nbsp;</td>
 * <td style='text-align:center; border: 0px;height:16px;'>&nbsp;6</td>
 * </tr>
 * <tr>
 * <td style='text-align:center; border: 0px;height:16px;'>&nbsp;12</td>
 * <td style='text-align:center; border: solid 1px;height:16px;border-color:gray;'>&nbsp;&nbsp;</td>
 * <td style='text-align:center; border: solid 1px;height:16px;border-color:gray;'>&nbsp;&nbsp;</td>
 * <td style='text-align:center; border: solid 1px;height:16px;border-color:gray;'>&nbsp;&nbsp;</td>
 * <td style='text-align:center; border: solid 1px;height:16px;border-color:gray;'>&nbsp;&nbsp;</td>
 * <td style='text-align:center; border: 0px;height:16px;'>&nbsp;7</td>
 * </tr>
 * <tr>
 * <td style='text-align:center; border: 0px;height:16px;'>&nbsp;&nbsp;</td>
 * <td style='text-align:center; height:16px;'>11</td>
 * <td style='text-align:center; height:16px;'>10</td>
 * <td style='text-align:center; height:16px;'>&nbsp;9</td>
 * <td style='text-align:center; height:16px;'>&nbsp;8</td>
 * <td style='text-align:center; border: 0px;height:16px;'>&nbsp;&nbsp;</td>
 * </tr>
 * </table>
 * </li>
 * <li>If no clue is available, add value `0`</li>
 * <li>Each puzzle has only one possible solution</li>
 * <li>`SolvePuzzle()` returns matrix `int[][]`. The first indexer is for the row, the second indexer for the column. (Python: returns 4-tuple of 4-tuples, Ruby: 4-Array of 4-Arrays)
 * </li>
 * </ul>
 *
 * <b style='font-size:16px'><i>If you finished this kata you can use your solution as a base for the more challenging  kata: <a href="https://www.codewars.com/kata/6-by-6-skyscrapers">6 By 6 Skyscrapers</a></i></b>
 */
public class SkyScrapers {


    private SkyScraper[][] skyScraperGrid;
    private final int[] clues;


    public SkyScrapers(SkyScraper[][] skyScraperGrid, int[] clues) {
        this.skyScraperGrid = skyScraperGrid;
        this.clues = clues;
    }

    public static int[][] solvePuzzle(int[] clues) {
        int skyScraperGridSize = clues.length / 4;
        SkyScraper[][] skyScraperGrid = new SkyScraper[skyScraperGridSize][skyScraperGridSize];
        for (int row = 0; row < skyScraperGridSize; row++) {
            for (int column = 0; column < skyScraperGridSize; column++) {
                skyScraperGrid[row][column] = new SkyScraper(row, column, skyScraperGridSize);
                char letter = (char) ('A' + column);
                skyScraperGrid[row][column].name = letter + String.valueOf(row);
            }
        }
        return new SkyScrapers(skyScraperGrid, clues).solvePuzzle();
    }


    public int[][] solvePuzzle() {
        while (Arrays.stream(skyScraperGrid)
                .flatMap(Arrays::stream)
                .anyMatch(skyScraper -> skyScraper.possibleSkyScraperHeights.size() != 1)) {
            for (int clueIndex = 0; clueIndex < clues.length; clueIndex++) {
                int heightClue = clues[clueIndex];
                List<SkyScraper> skyScrapers = skyScraperForClueIndex(clueIndex);
                System.out.println(clueIndex + " => " + heightClue + ": " + skyScrapers.stream()
                        .map(skyScraper -> skyScraper.name)
                        .collect(Collectors.joining(", ")));
                System.out.println(toString());
                if (heightClue == 0) {
                    continue;
                }

                System.out.println("For clueIndex: " + clueIndex + " = " + heightClue);
                Set<int[]> possibleHeightCombinations = possibleHeightCombinationsForGivenClue(skyScrapers, heightClue,
                        0);

                skyScrapers.forEach(skyScraper -> skyScraper.possibleSkyScraperHeights.clear());
                for (int[] possibleHeightCombination : possibleHeightCombinations) {
                    for (int i = 0; i < possibleHeightCombination.length; i++) {
                        SkyScraper skyScraper = skyScrapers.get(i);
                        skyScraper.addPossibleHeight(possibleHeightCombination[i]);
                    }
                }
            }
        }
        return toSkyScraperGrid2D();
    }

    private Set<int[]> possibleHeightCombinationsForGivenClue(List<SkyScraper> skyScrapers, int heightClue, int index) {
        int seenSkyScrapers = getSeenSkyScrapers(skyScrapers);
        if (index >= skyScrapers.size() && seenSkyScrapers == heightClue) {
            Set<int[]> possibleHeight = new HashSet<>();
            possibleHeight.add(skyScrapers.stream().mapToInt(value -> value.height).toArray());
            System.out.println(skyScrapers + " => " + seenSkyScrapers);
            return possibleHeight;
        } else if (index >= skyScrapers.size()) {
            return new HashSet<>(0);
        }

        Set<int[]> possibleHeightCombinations = new HashSet<>();

        SkyScraper skyScraper = skyScrapers.get(index);
        Set<Integer> possibleSkyScraperHeights = new HashSet<>(skyScraper.possibleSkyScraperHeights);
        possibleSkyScraperHeights.removeAll(getUsedHeight(skyScrapers));
        for (int height : possibleSkyScraperHeights) {
            skyScraper.height = height;
            possibleHeightCombinations.addAll(
                    possibleHeightCombinationsForGivenClue(skyScrapers, heightClue, index + 1));
            skyScraper.height = 0;
        }

        return possibleHeightCombinations;
    }

    private Set<Integer> getUsedHeight(List<SkyScraper> skyScrapers) {
        return skyScrapers.stream().map(skyScraper -> skyScraper.height).collect(Collectors.toSet());
    }


    private int[][] toSkyScraperGrid2D() {
        int[][] skyScraperGrid2D = new int[skyScraperGrid.length][];
        for (int row = 0; row < skyScraperGrid.length; row++) {
            SkyScraper[] skyScraperRow = skyScraperGrid[row];
            skyScraperGrid2D[row] = new int[skyScraperRow.length];
            for (int column = 0; column < skyScraperRow.length; column++) {
                skyScraperGrid2D[row][column] = skyScraperRow[column].getHeight();
            }
            System.out.println(Arrays.toString(skyScraperGrid2D[row]));
        }
        return skyScraperGrid2D;
    }


    public List<SkyScraper> skyScraperForClueIndex(int clueIndex) {
        int side = clueIndex / skyScraperGrid.length;
        List<SkyScraper> skyScrapers = new ArrayList<>(skyScraperGrid.length);

        for (int i = 0; i < skyScraperGrid.length; i++) {
            if (side == 0) { // top
                int column = clueIndex % skyScraperGrid.length;
                skyScrapers.add(skyScraperGrid[i][column]);
            } else if (side == 2) { // bottom
                int column = skyScraperGrid.length - 1 - clueIndex % skyScraperGrid.length;
                skyScrapers.add(skyScraperGrid[skyScraperGrid.length - 1 - i][column]);

            } else if (side == 1) { // right
                int row = clueIndex % skyScraperGrid.length;
                skyScrapers.add(skyScraperGrid[row][skyScraperGrid.length - 1 - i]);
            } else if (side == 3) { // left
                int row = skyScraperGrid.length - 1 - clueIndex % skyScraperGrid.length;
                skyScrapers.add(skyScraperGrid[row][i]);

            }
        }
        skyScrapers.forEach(skyScraper -> skyScraper.height = 0);
        return skyScrapers;
    }


    private static Set<Integer> allPossibleHeights(int maxHeight) {
        return IntStream.rangeClosed(1, maxHeight).boxed().collect(Collectors.toSet());
    }


    private static int getSeenSkyScrapers(List<SkyScraper> skyScrapers) {
        int skyScraperCount = 0;
        int currentHight = 0;
        for (SkyScraper skyScraper : skyScrapers) {
            if (currentHight < skyScraper.height) {
                skyScraperCount++;
                currentHight = skyScraper.height;
            }
        }
        return skyScraperCount;
    }


    public static class SkyScraper {

        int row;
        int column;
        String name = "A0";

        int height = 0;
        Set<Integer> possibleSkyScraperHeights;

        public SkyScraper(int row, int column, int maxHeight) {
            this.row = row;
            this.column = column;
            this.possibleSkyScraperHeights = allPossibleHeights(maxHeight);
        }

        public int getHeight() {
            return possibleSkyScraperHeights.stream().findAny().orElse(0);
        }

        public boolean addPossibleHeight(int possibleHeight) {
            if (possibleHeight > 0) {
                return possibleSkyScraperHeights.add(possibleHeight);
            }
            return false;
        }

        @Override
        public String toString() {
            return String.valueOf(height);
        }
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(StringUtils.repeat("-", 31)).append("\n");
        for (int row = 0; row < skyScraperGrid.length; row++) {
            for (int i = 0; i <= 1; i++) {
                if (i == 1) {
                    sb.append("\n");
                }
                for (int column = 0; column < skyScraperGrid.length; column++) {
                    SkyScraper skyScraper = skyScraperGrid[row][column];
                    if (i == 0) {
                        sb.append(StringUtils.leftPad(skyScraper.possibleSkyScraperHeights.stream()
                                .filter(height -> height <= 2)
                                .map(String::valueOf)
                                .collect(Collectors.joining(", ")), 5));
                    } else {
                        sb.append(StringUtils.leftPad(skyScraper.possibleSkyScraperHeights.stream()
                                .filter(height -> height >= 3)
                                .map(String::valueOf)
                                .collect(Collectors.joining(", ")), 5));
                    }
                    sb.append(" | ");
                }
            }
            sb.append("\n").append(StringUtils.repeat("-", 31)).append("\n");
        }

        return sb.toString();
    }
}
