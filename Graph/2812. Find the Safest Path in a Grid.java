/*
# 2812. Find the Safest Path in a Grid

LeetCode Link: https://leetcode.com/problems/find-the-safest-path-in-a-grid/

Difficulty: Hard

Approach 1: Multi-Source BFS + Binary Search + BFS (Optimal)

Intuition

The safeness factor of a path is defined as the minimum distance from any cell on that path to the nearest thief.
Instead of calculating the nearest thief distance separately for every cell, we first compute the minimum distance of every cell from its nearest thief using **Multi-Source BFS**.
Once every cell knows its distance from the nearest thief, we binary search on the answer.
For a candidate safeness factor `k`:

- We only allow moving through cells whose distance from the nearest thief is at least `k`.
- Run a normal BFS from `(0,0)` to `(n-1,n-1)`.
- If the destination is reachable, then `k` is feasible.
- Otherwise, it is not.

Binary search gives the maximum possible safeness factor.

Time Complexity
Multi-Source BFS: O(n²)
Binary Search: O(log 400) iterations
BFS for each iteration: O(n²)

Overall: O(n² × log 400), which is effectively O(n²) since log(400) is a constant.

Space Complexity
Distance matrix: O(n²)
Visited matrix: O(n²)
BFS queue: O(n²)

Overall: O(n²)

*/

class Solution {
    int n;

    public int maximumSafenessFactor(List<List<Integer>> grid) {
        n = grid.size();

        int[][] distNearestThief = new int[n][n];

        for (int i = 0; i < n; i++) {
            Arrays.fill(distNearestThief[i], -1);
        }

        Queue<int[]> que = new LinkedList<>();
        boolean[][] visited = new boolean[n][n];

        // Multi-Source BFS initialization
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid.get(i).get(j) == 1) {
                    que.offer(new int[]{i, j});
                    visited[i][j] = true;
                }
            }
        }

        int[][] dir = {
            {1,0},
            {-1,0},
            {0,1},
            {0,-1}
        };

        int level = 0;

        // Compute nearest thief distance for every cell
        while (!que.isEmpty()) {
            int size = que.size();

            while (size-- > 0) {
                int[] cur = que.poll();

                int i = cur[0];
                int j = cur[1];

                distNearestThief[i][j] = level;

                for (int[] d : dir) {
                    int x = i + d[0];
                    int y = j + d[1];

                    if (x < 0 || y < 0 || x >= n || y >= n || visited[x][y])
                        continue;

                    visited[x][y] = true;
                    que.offer(new int[]{x, y});
                }
            }

            level++;
        }

        int low = 0;
        int high = 400;
        int ans = 0;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (check(distNearestThief, mid)) {
                ans = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return ans;
    }

    boolean check(int[][] dist, int sf) {
        int n = dist.length;

        if (dist[0][0] < sf)
            return false;

        Queue<int[]> q = new LinkedList<>();
        boolean[][] visited = new boolean[n][n];

        q.offer(new int[]{0, 0});
        visited[0][0] = true;

        int[][] dir = {
            {1,0},
            {-1,0},
            {0,1},
            {0,-1}
        };

        while (!q.isEmpty()) {
            int[] cur = q.poll();

            if (cur[0] == n - 1 && cur[1] == n - 1)
                return true;

            for (int[] d : dir) {
                int x = cur[0] + d[0];
                int y = cur[1] + d[1];

                if (x < 0 || y < 0 || x >= n || y >= n ||
                    visited[x][y] || dist[x][y] < sf)
                    continue;

                visited[x][y] = true;
                q.offer(new int[]{x, y});
            }
        }

        return false;
    }
}
