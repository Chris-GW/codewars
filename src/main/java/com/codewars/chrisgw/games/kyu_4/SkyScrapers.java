package com.codewars.chrisgw.games.kyu_4;

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


    public static int[][] solvePuzzle(int[] clues) {
        return new int[4][4];
    }

}
