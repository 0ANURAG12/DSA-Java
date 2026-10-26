/*
 * LeetCode: 3619. Count Islands With Total Value Divisible by K
 * https://leetcode.com/problems/count-islands-with-total-value-divisible-by-k/
 *
 * Topic: Graphs / DFS / Matrix Traversal
 *
 *
 * ============================================================
 * APPROACH 1: DFS + Connected Component Sum
 * ============================================================
 *
 * Intuition:
 * - The grid contains positive values and zeros.
 *
 * - A cell with a non-zero value represents land, while a cell
 *   with value 0 is treated as water.
 *
 * - An island is a group of connected non-zero cells connected
 *   horizontally or vertically.
 *
 * - We traverse every cell in the grid.
 *
 * - Whenever we find an unvisited non-zero cell, we start DFS
 *   to explore its entire connected component.
 *
 * - During DFS:
 *
 *   1. If the cell is outside the grid, already visited,
 *      or contains zero, return 0.
 *
 *   2. Mark the current cell as visited.
 *
 *   3. Add its value to the island's total sum.
 *
 *   4. Recursively explore all four neighboring cells and
 *      add their sums to the current sum.
 *
 * - After DFS finishes, we have the total value of that island.
 *
 * - If the island's sum is divisible by k, increment `ans`.
 *
 * - After processing every connected component, return `ans`.
 *
 *
 * Example:
 *
 *      grid = [
 *          [0, 2, 0],
 *          [3, 1, 0],
 *          [0, 0, 4]
 *      ]
 *
 *      k = 3
 *
 *      Island 1:
 *      2 + 3 + 1 = 6
 *      6 % 3 == 0 -> Count this island
 *
 *      Island 2:
 *      Sum = 4
 *      4 % 3 != 0 -> Do not count
 *
 *      Answer = 1
 *
 *
 * Time Complexity: O(n * m)
 * Space Complexity: O(n * m)
 *
 * Why O(n * m)?
 * - Each cell is visited at most once.
 * - Each cell checks at most four neighboring cells.
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

    public int countIslands(int[][] grid, int k) {

        int n = grid.length;
        int m = grid[0].length;

        boolean[][] visited = new boolean[n][m];
        int ans = 0;

        // Traverse every cell in the grid
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {

                // Start DFS for every unvisited land cell
                if (grid[i][j] != 0 && !visited[i][j]) {

                    // Calculate the total value of this island
                    long x = isLand(
                        i, j, n, m, grid, visited
                    );

                    // Count islands whose sum is divisible by k
                    if (x != 0 && x % k == 0) {
                        ans++;
                    }
                }
            }
        }

        return ans;
    }

    long isLand(
            int i,
            int j,
            int n,
            int m,
            int[][] grid,
            boolean[][] visited) {

        // Stop if outside the grid, visited, or water
        if (i < 0 || j < 0 || i >= n || j >= m ||
            visited[i][j] || grid[i][j] == 0) {
            return 0;
        }

        // Mark the current cell as visited
        visited[i][j] = true;

        // Include the current cell's value
        long sum = grid[i][j];

        // Explore all four neighboring cells
        for (int[] d : dir) {

            int ni = i + d[0];
            int nj = j + d[1];

            sum += isLand(
                ni, nj, n, m, grid, visited
            );
        }

        // Return the total value of the connected component
        return sum;
    }
}
