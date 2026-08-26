/*
 * LeetCode: 200. Number of Islands
 * https://leetcode.com/problems/number-of-islands/
 *
 * ------------------------------------------------------------
 * Approach: Depth-First Search (DFS)
 * ------------------------------------------------------------
 * Intuition:
 * - Traverse every cell in the grid.
 * - Whenever we find an unvisited land cell ('1'), we have found
 *   a new island, so increment the island count.
 * - Use DFS to explore all connected land cells belonging to
 *   that island and mark them as visited.
 * - Since every connected land cell is marked visited, the same
 *   island will never be counted again.
 *
 * Time Complexity: O(R × C)
 * Space Complexity: O(R × C)
 *
 * where:
 * R = Number of rows.
 * C = Number of columns.
 *
 * Explanation:
 * - Every cell is visited at most once.
 * - The visited array requires O(R × C) space.
 * - The recursive DFS stack can also grow up to O(R × C)
 *   in the worst case.
 *
 * Alternative Approach:
 * 1. Breadth-First Search (BFS)
 *    - Instead of recursion, use a Queue to explore each island.
 *    - Time Complexity: O(R × C)
 *    - Space Complexity: O(R × C)
 *
 * 2. Union-Find (Disjoint Set Union)
 *    - Treat every land cell as a node and union adjacent
 *      land cells.
 *    - Time Complexity: approximately O(R × C)
 *    - Space Complexity: O(R × C)
 */
class Solution {
    int n;
    int m;
    char[][] grid;
    boolean[][] visited;

    public int numIslands(char[][] grid) {
        this.grid = grid;
        this.n = grid.length;
        this.m = grid[0].length;
        this.visited = new boolean[n][m];

        int ans = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {

                if (!visited[i][j] && grid[i][j] == '1') {
                    ans++;
                    isLand(i, j);
                }
            }
        }

        return ans;
    }

    public void isLand(int i, int j) {
        if (i < 0 || i >= n || j < 0 || j >= m || visited[i][j]) {
            return;
        }

        if (grid[i][j] == '0') {
            return;
        }

        visited[i][j] = true;

        isLand(i + 1, j);
        isLand(i - 1, j);
        isLand(i, j + 1);
        isLand(i, j - 1);
    }
}
