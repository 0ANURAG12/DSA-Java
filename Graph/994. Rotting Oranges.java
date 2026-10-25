/*
 * LeetCode: 994. Rotting Oranges
 * https://leetcode.com/problems/rotting-oranges/
 *
 * Topic: Graphs / BFS / Multi-Source BFS / Matrix Traversal
 *
 *
 * ============================================================
 * APPROACH 1: Multi-Source BFS
 * ============================================================
 *
 * Intuition:
 * - The grid contains:
 *   0 -> Empty cell
 *   1 -> Fresh orange
 *   2 -> Rotten orange
 *
 * - Every minute, a rotten orange causes its adjacent fresh
 *   oranges to become rotten.
 *
 * - Since multiple rotten oranges can spread simultaneously,
 *   we use Multi-Source BFS.
 *
 * - Initially, add every rotten orange to the queue and count
 *   all fresh oranges.
 *
 * - Each BFS level represents one minute.
 *
 * - For every rotten orange, check its four neighboring cells:
 *   down, right, up, and left.
 *
 * - If a neighbor contains a fresh orange and has not been
 *   visited, mark it visited, add it to the queue, and
 *   decrease the fresh-orange count.
 *
 * - Increase the time only when at least one fresh orange
 *   becomes rotten during the current BFS level.
 *
 * - Edge cases:
 *
 *   1. If there are no fresh oranges initially, return 0.
 *
 *   2. If fresh oranges exist but there are no rotten oranges,
 *      return -1.
 *
 *   3. After BFS, if fresh oranges remain, some oranges could
 *      not be reached. Return -1.
 *
 *   4. Otherwise, return the total time required.
 *
 *
 * Example:
 *
 *      grid = [
 *          [2, 1, 1],
 *          [1, 1, 0],
 *          [0, 1, 1]
 *      ]
 *
 *      Initially:
 *      Rotten oranges = [(0, 0)]
 *      Fresh oranges = 6
 *
 *      Minute 1:
 *      (0, 1), (1, 0) become rotten.
 *
 *      Minute 2:
 *      (0, 2), (1, 1) become rotten.
 *
 *      Minute 3:
 *      (2, 1) becomes rotten.
 *
 *      Minute 4:
 *      (2, 2) becomes rotten.
 *
 *      No fresh oranges remain.
 *
 *      Answer = 4
 *
 *
 * Time Complexity: O(n * m)
 * Space Complexity: O(n * m)
 *
 * Why O(n * m)?
 * - Each cell is added to the queue at most once.
 * - Each cell checks at most four neighboring cells.
 *
 * Space:
 * - The visited matrix requires O(n * m) space.
 * - The queue can contain up to O(n * m) cells.
 */


class Solution {

    public int orangesRotting(int[][] grid) {

        int n = grid.length;
        int m = grid[0].length;

        boolean[][] visited = new boolean[n][m];
        Queue<int[]> q = new ArrayDeque<>();

        // Four possible movement directions
        int[][] dir = {
            {1, 0},
            {0, 1},
            {-1, 0},
            {0, -1}
        };

        int fresh = 0;

        // Add all initially rotten oranges to the queue
        // and count the fresh oranges
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {

                if (grid[i][j] == 2) {
                    visited[i][j] = true;
                    q.add(new int[]{i, j});

                } else if (grid[i][j] == 1) {
                    fresh++;
                }
            }
        }

        // No fresh oranges need to rot
        if (fresh == 0) {
            return 0;
        }

        // Fresh oranges exist, but no orange can spread rot
        if (q.isEmpty()) {
            return -1;
        }

        int time = 0;

        // Multi-Source BFS
        while (!q.isEmpty()) {

            int newRotten = 0;
            int size = q.size();

            // Process all oranges that were rotten at the
            // beginning of this minute
            while (size-- > 0) {

                int[] curr = q.poll();

                int row = curr[0];
                int col = curr[1];

                // Check all four neighboring cells
                for (int[] d : dir) {

                    int nr = row + d[0];
                    int nc = col + d[1];

                    // Process only unvisited fresh oranges
                    if (nr >= 0 && nr < n &&
                        nc >= 0 && nc < m &&
                        !visited[nr][nc] &&
                        grid[nr][nc] == 1) {

                        visited[nr][nc] = true;
                        q.add(new int[]{nr, nc});

                        newRotten++;
                    }
                }
            }

            // Advance time only if at least one orange rotted
            if (newRotten > 0) {
                time++;
                fresh -= newRotten;
            }
        }

        // Return the time if every fresh orange became rotten
        return fresh == 0 ? time : -1;
    }
}
