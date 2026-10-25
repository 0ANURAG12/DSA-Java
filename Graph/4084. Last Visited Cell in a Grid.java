/*
 * LeetCode: 4084. Last Visited Cell in a Grid
 * Topic: Matrix / DFS / Simulation
 *
 *
 * ============================================================
 * APPROACH 1: DFS + Visited Matrix
 * ============================================================
 *
 * Intuition:
 * - We start at cell (0, 0) and follow the direction
 *   represented by each cell.
 *
 * - Each cell contains one of four directions:
 *
 *   'R' -> Move right
 *   'L' -> Move left
 *   'U' -> Move up
 *   'D' -> Move down
 *
 * - Use a visited matrix to ensure that each cell is
 *   processed at most once.
 *
 * - Before processing a cell, check whether its coordinates
 *   are within the grid and whether it has already been visited.
 *
 * - Mark the current cell as visited and update `r` and `c`
 *   to store the most recently visited coordinates.
 *
 * - Based on the current cell's direction, recursively move
 *   to the neighboring cell.
 *
 * - If the next position is outside the grid or has already
 *   been visited, stop the recursion.
 *
 * - Finally, return the coordinates of the last visited cell.
 *
 *
 * Example:
 *
 *      grid = [
 *          ['R', 'D'],
 *          ['R', 'L']
 *      ]
 *
 *      Start at (0, 0):
 *      (0, 0) -> 'R' -> (0, 1)
 *      (0, 1) -> 'D' -> (1, 1)
 *      (1, 1) -> 'L' -> (1, 0)
 *      (1, 0) -> 'R' -> (1, 1)
 *
 *      Cell (1, 1) has already been visited, so stop.
 *
 *      Last visited cell = (1, 0)
 *
 *
 * Time Complexity: O(n * m)
 * Space Complexity: O(n * m)
 *
 * Why O(n * m)?
 * - Each cell is processed at most once.
 *
 * Space:
 * - The visited matrix requires O(n * m) space.
 * - Recursive calls can use up to O(n * m) stack space.
 */


class Solution {

    int n;
    int m;
    char[][] grid;
    boolean[][] visited;

    int r = 0;
    int c = 0;

    public int[] lastVisitedCell(char[][] grid) {

        this.n = grid.length;
        this.m = grid[0].length;

        this.grid = grid;
        this.visited = new boolean[n][m];

        // Start traversal from the top-left cell
        process(0, 0);

        // Return the last visited coordinates
        return new int[]{r, c};
    }

    void process(int i, int j) {

        // Stop if outside the grid or already visited
        if (i >= n || j >= m || i < 0 || j < 0 ||
            visited[i][j]) {
            return;
        }

        // Mark the current cell as visited
        visited[i][j] = true;

        // Update the last visited coordinates
        this.r = i;
        this.c = j;

        // Move according to the current cell's direction
        if (grid[i][j] == 'R') {
            process(i, j + 1);
        }

        if (grid[i][j] == 'L') {
            process(i, j - 1);
        }

        if (grid[i][j] == 'U') {
            process(i - 1, j);
        }

        if (grid[i][j] == 'D') {
            process(i + 1, j);
        }
    }
}
