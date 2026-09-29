/*
 * LeetCode: 2267. Check if There Is a Valid Parentheses String Path
 * https://leetcode.com/problems/check-if-there-is-a-valid-parentheses-string-path/
 *
 * Topic: Dynamic Programming / Grid DFS / Memoization
 *
 *
 * ============================================================
 * APPROACH 1: DFS + 3D Memoization
 * ============================================================
 *
 * Intuition:
 * - We need to find a path from the top-left cell to the
 *   bottom-right cell.
 *
 * - At every step, we can move:
 *
 *      1. Down
 *      2. Right
 *
 * - The characters along the path form a parentheses string.
 *
 * - For the string to be valid:
 *
 *      1. The number of '(' encountered so far must always be
 *         greater than or equal to the number of ')'.
 *
 *      2. At the destination, the number of unmatched '('
 *         must be exactly zero.
 *
 * - We maintain an `openCount`:
 *
 *      '(' -> openCount++
 *      ')' -> openCount--
 *
 * - If openCount becomes negative, the current path can never
 *   produce a valid parentheses string, so we stop exploring it.
 *
 * - The result from a state depends only on:
 *
 *      (row, column, openCount)
 *
 * - Therefore, we use 3D memoization:
 *
 *      t[row][column][openCount]
 *
 *   to avoid solving the same state repeatedly.
 *
 * - A state stores:
 *
 *      1 -> A valid path exists from this state
 *      0 -> No valid path exists from this state
 *     -1 -> State has not been calculated yet
 *
 *
 * Time Complexity: O(n × m × (n + m))
 *
 * Space Complexity: O(n × m × (n + m))
 *
 * Where:
 * - n = Number of rows
 * - m = Number of columns
 *
 * Why O(n × m × (n + m))?
 * - There are n × m possible grid positions.
 * - openCount can range from 0 to n + m.
 * - Each state is calculated only once.
 *
 * - Each state performs O(1) work apart from its two recursive
 *   transitions.
 *
 * Space:
 * - The memoization table contains O(n × m × (n + m)) states.
 * - The recursion stack can grow up to O(n + m).
 */


class Solution {

    static int n;
    static int m;

    char[][] grid;

    int[][][] t;

    public boolean hasValidPath(char[][] grid) {

        this.n = grid.length;
        this.m = grid[0].length;
        this.grid = grid;

        /*
         * openCount can be at most n + m - 1, but we allocate
         * n + m positions for simplicity.
         */
        t = new int[n + 1][m + 1][n + m];

        // -1 means the state has not been calculated yet
        for (int[][] p : t) {
            for (int[] q : p) {
                Arrays.fill(q, -1);
            }
        }

        return solve(0, 0, 0, t);
    }

    boolean solve(int i, int j, int openCount, int[][][] t) {

        // Process the current cell
        if (grid[i][j] == '(') {
            openCount++;
        } else {
            openCount--;
        }

        // More closing brackets than opening brackets
        // means this path cannot be valid.
        if (openCount < 0) {
            return false;
        }

        // Already calculated this state
        if (t[i][j][openCount] != -1) {
            return t[i][j][openCount] == 1;
        }

        // Reached the destination
        if (i == n - 1 && j == m - 1) {
            return openCount == 0;
        }

        boolean down = false;

        if (i + 1 < n) {
            down = solve(i + 1, j, openCount, t);
        }

        boolean right = false;

        if (j + 1 < m) {
            right = solve(i, j + 1, openCount, t);
        }

        boolean result = down || right;

        // Memoize the result
        t[i][j][openCount] = result ? 1 : 0;

        return result;
    }
}
