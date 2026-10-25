/*
 * LeetCode: 1254. Number of Closed Islands
 * https://leetcode.com/problems/number-of-closed-islands/
 *
 * Topic: Graphs / DFS / Matrix Traversal
 *
 *
 * ============================================================
 * APPROACH 1: DFS + Boolean Propagation
 * ============================================================
 *
 * Intuition:
 * - The grid contains:
 *   0 -> Land
 *   1 -> Water
 *
 * - A closed island is a group of connected land cells that
 *   is completely surrounded by water and does not touch
 *   the boundary of the grid.
 *
 * - We traverse every cell and start DFS whenever we find
 *   an unvisited land cell.
 *
 * - The DFS explores all four directions:
 *   right, down, up, and left.
 *
 * - The `isLand()` function determines whether the connected
 *   island is closed.
 *
 * - Base cases:
 *
 *   1. If the coordinates go outside the grid, return false.
 *      This means the island touches the boundary.
 *
 *   2. If the cell is water or already visited, return true.
 *      These cells do not invalidate the current island.
 *
 *   3. Otherwise, mark the land cell as visited and explore
 *      all four neighboring cells.
 *
 * - The results of all four DFS calls are combined using AND.
 *   If any path reaches outside the grid, the result becomes
 *   false, meaning the island is not closed.
 *
 * - After DFS finishes, increment `ans` only if the entire
 *   connected island is closed.
 *
 *
 * Example:
 *
 *      grid = [
 *          [1, 1, 1, 1, 1],
 *          [1, 0, 0, 0, 1],
 *          [1, 0, 1, 0, 1],
 *          [1, 0, 0, 0, 1],
 *          [1, 1, 1, 1, 1]
 *      ]
 *
 *      The connected land cells are surrounded by water.
 *
 *      The island does not touch the grid boundary.
 *
 *      Answer = 1
 *
 *
 * Time Complexity: O(n * m)
 * Space Complexity: O(n * m)
 *
 * Why O(n * m)?
 * - Each cell is visited at most once.
 * - Every cell explores at most four neighboring cells.
 *
 * Space:
 * - The visited matrix requires O(n * m) space.
 * - Recursive DFS can use up to O(n * m) stack space.
 */


class Solution {

    int[][] dir = {
        {0, 1},
        {1, 0},
        {-1, 0},
        {0, -1}
    };

    public int closedIsland(int[][] grid) {

        int n = grid.length;
        int m = grid[0].length;

        boolean[][] visited = new boolean[n][m];
        int ans = 0;

        // Traverse every cell in the grid
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {

                // Start DFS for every unvisited land cell
                if (grid[i][j] == 0 && !visited[i][j]) {

                    if (isLand(i, j, n, m, grid, visited)) {
                        ans++;
                    }
                }
            }
        }

        return ans;
    }

    boolean isLand(
            int i,
            int j,
            int n,
            int m,
            int[][] grid,
            boolean[][] visited) {

        // Going outside the grid means the island is not closed
        if (i < 0 || i >= n || j < 0 || j >= m) {
            return false;
        }

        // Water or an already visited cell does not invalidate
        // the current island
        if (grid[i][j] == 1 || visited[i][j]) {
            return true;
        }

        // Mark the current land cell as visited
        visited[i][j] = true;

        boolean ans = true;

        // Explore all four neighboring cells
        for (int[] d : dir) {

            int ni = i + d[0];
            int nj = j + d[1];

            ans = isLand(
                ni, nj, n, m, grid, visited
            ) & ans;
        }

        return ans;
    }
}
