/*
 * LeetCode: 200. Number of Islands
 * https://leetcode.com/problems/number-of-islands/
 *
 * ============================================================
 * APPROACH 1: DFS + Visited Array
 * ============================================================
 *
 * Intuition:
 * - Traverse every cell in the grid.
 * - Whenever we find an unvisited land cell ('1'), we have
 *   discovered a new island.
 * - Increment the island count.
 * - Use DFS to visit all connected land cells belonging
 *   to that island.
 * - Mark every visited land cell so that the same island
 *   is not counted again.
 *
 * Time Complexity: O(R × C)
 * Space Complexity: O(R × C)
 *
 * Where:
 * R = Number of rows
 * C = Number of columns
 *
 * Why O(R × C)?
 * - Every cell is visited at most once.
 * - The visited[][] array takes O(R × C) space.
 * - The recursive DFS stack can also grow up to O(R × C)
 *   in the worst case.
 */

class DFSWithVisited {

    int n;
    int m;
    char[][] grid;
    boolean[][] visited;

    public int numIslands(char[][] grid) {

        this.grid = grid;
        this.n = grid.length;
        this.m = grid[0].length;

        visited = new boolean[n][m];

        int islands = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {

                // Found an unvisited land cell -> new island
                if (!visited[i][j] && grid[i][j] == '1') {
                    islands++;

                    // Visit the entire island
                    dfs(i, j);
                }
            }
        }

        return islands;
    }

    private void dfs(int i, int j) {

        // Boundary check
        if (i < 0 || i >= n || j < 0 || j >= m) {
            return;
        }

        // Stop at water or already visited cells
        if (grid[i][j] == '0' || visited[i][j]) {
            return;
        }

        // Mark current land cell as visited
        visited[i][j] = true;

        // Explore all four directions
        dfs(i + 1, j);
        dfs(i - 1, j);
        dfs(i, j + 1);
        dfs(i, j - 1);
    }
}


/*
 * ============================================================
 * APPROACH 2: DFS + Modify the Grid
 * ============================================================
 *
 * Intuition:
 * - We do not actually need a separate visited[][] array.
 * - Whenever we visit a land cell ('1'), change it to water ('0').
 * - This acts as our visited marker.
 * - Every time we encounter a '1' during the main traversal,
 *   it represents a new island.
 * - DFS converts the entire island from '1' to '0'.
 *
 * Advantage:
 * - No separate visited[][] array is required.
 *
 * Trade-off:
 * - The original grid is modified.
 *
 * Time Complexity: O(R × C)
 * Space Complexity: O(R × C)
 *
 * The auxiliary data structure is O(1), but the recursive DFS
 * stack can grow up to O(R × C) in the worst case.
 */

class DFSModifyGrid {

    int n;
    int m;
    char[][] grid;

    public int numIslands(char[][] grid) {

        this.grid = grid;
        this.n = grid.length;
        this.m = grid[0].length;

        int islands = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {

                if (grid[i][j] == '1') {

                    // Found a new island
                    islands++;

                    // Sink the entire island
                    dfs(i, j);
                }
            }
        }

        return islands;
    }

    private void dfs(int i, int j) {

        // Boundary check
        if (i < 0 || i >= n || j < 0 || j >= m) {
            return;
        }

        // Stop at water
        if (grid[i][j] == '0') {
            return;
        }

        // Mark as visited by converting land to water
        grid[i][j] = '0';

        // Explore all four directions
        dfs(i + 1, j);
        dfs(i - 1, j);
        dfs(i, j + 1);
        dfs(i, j - 1);
    }
}


/*
 * ============================================================
 * APPROACH 3: BFS + Visited Array
 * ============================================================
 *
 * Intuition:
 * - Same idea as DFS, but use a Queue instead of recursion.
 * - Whenever we find an unvisited land cell, increment the
 *   island count.
 * - Add that cell to the queue.
 * - BFS visits all connected land cells and marks them visited.
 *
 * Time Complexity: O(R × C)
 * Space Complexity: O(R × C)
 *
 * The visited[][] array requires O(R × C) space.
 * The BFS queue can also contain O(R × C) cells in the
 * worst case.
 */

import java.util.ArrayDeque;
import java.util.Queue;

class BFSWithVisited {

    public int numIslands(char[][] grid) {

        int n = grid.length;
        int m = grid[0].length;

        boolean[][] visited = new boolean[n][m];

        int islands = 0;

        // Four possible directions
        int[][] directions = {
            {1, 0},
            {-1, 0},
            {0, 1},
            {0, -1}
        };

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {

                if (!visited[i][j] && grid[i][j] == '1') {

                    islands++;

                    Queue<int[]> queue = new ArrayDeque<>();

                    queue.offer(new int[]{i, j});
                    visited[i][j] = true;

                    while (!queue.isEmpty()) {

                        int[] current = queue.poll();

                        int row = current[0];
                        int col = current[1];

                        for (int[] direction : directions) {

                            int newRow = row + direction[0];
                            int newCol = col + direction[1];

                            // Check boundaries
                            if (newRow < 0 || newRow >= n ||
                                newCol < 0 || newCol >= m) {
                                continue;
                            }

                            // Ignore water and visited cells
                            if (grid[newRow][newCol] == '0' ||
                                visited[newRow][newCol]) {
                                continue;
                            }

                            visited[newRow][newCol] = true;

                            queue.offer(new int[]{newRow, newCol});
                        }
                    }
                }
            }
        }

        return islands;
    }
}


/*
 * ============================================================
 * APPROACH 4: BFS + Modify the Grid
 * ============================================================
 *
 * Intuition:
 * - Instead of maintaining visited[][], modify the grid itself.
 * - When a land cell is added to the BFS queue, change it
 *   from '1' to '0'.
 * - This prevents the same cell from being added multiple times.
 *
 * Time Complexity: O(R × C)
 * Space Complexity: O(R × C)
 *
 * The grid modification uses no extra space.
 * The BFS queue can contain O(R × C) cells in the worst case.
 *
 * Trade-off:
 * - The original grid is modified.
 */

class BFSModifyGrid {

    public int numIslands(char[][] grid) {

        int n = grid.length;
        int m = grid[0].length;

        int islands = 0;

        int[][] directions = {
            {1, 0},
            {-1, 0},
            {0, 1},
            {0, -1}
        };

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {

                if (grid[i][j] == '1') {

                    islands++;

                    Queue<int[]> queue = new ArrayDeque<>();

                    // Mark as visited immediately
                    grid[i][j] = '0';

                    queue.offer(new int[]{i, j});

                    while (!queue.isEmpty()) {

                        int[] current = queue.poll();

                        int row = current[0];
                        int col = current[1];

                        for (int[] direction : directions) {

                            int newRow = row + direction[0];
                            int newCol = col + direction[1];

                            // Check boundaries
                            if (newRow < 0 || newRow >= n ||
                                newCol < 0 || newCol >= m) {
                                continue;
                            }

                            // Ignore water
                            if (grid[newRow][newCol] == '0') {
                                continue;
                            }

                            // Mark as visited
                            grid[newRow][newCol] = '0';

                            queue.offer(new int[]{newRow, newCol});
                        }
                    }
                }
            }
        }

        return islands;
    }
}


/*
 * ============================================================
 * APPROACH 5: Union-Find / Disjoint Set Union (DSU)
 * ============================================================
 *
 * Intuition:
 * - Treat every land cell as a separate node.
 * - Adjacent land cells belong to the same island, so union them.
 * - Initially, every land cell represents one island.
 * - Whenever two adjacent land cells are connected, their
 *   components are merged and the island count decreases.
 *
 * Optimization:
 * - Union by size keeps the trees shallow.
 * - Path compression makes future find operations very fast.
 *
 * Time Complexity:
 * O(R × C × α(R × C))
 *
 * Since α(N) grows extremely slowly, this is effectively:
 * O(R × C)
 *
 * Space Complexity: O(R × C)
 *
 * We store parent and size information for every cell.
 */

class UnionFind {

    int[] parent;
    int[] size;

    public int numIslands(char[][] grid) {

        int n = grid.length;
        int m = grid[0].length;

        int totalCells = n * m;

        parent = new int[totalCells];
        size = new int[totalCells];

        // Initially every land cell is its own component.
        int islands = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {

                int index = i * m + j;

                if (grid[i][j] == '1') {
                    parent[index] = index;
                    size[index] = 1;
                    islands++;
                } else {
                    parent[index] = -1;
                }
            }
        }

        /*
         * Only check right and down neighbours.
         *
         * This is enough because if we check all four directions,
         * every connection would be processed twice.
         */
        int[][] directions = {
            {1, 0},
            {0, 1}
        };

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {

                if (grid[i][j] == '0') {
                    continue;
                }

                int current = i * m + j;

                for (int[] direction : directions) {

                    int newRow = i + direction[0];
                    int newCol = j + direction[1];

                    if (newRow >= n || newCol >= m) {
                        continue;
                    }

                    if (grid[newRow][newCol] == '0') {
                        continue;
                    }

                    int neighbour = newRow * m + newCol;

                    // If they belong to different components,
                    // merge them and decrease island count.
                    if (union(current, neighbour)) {
                        islands--;
                    }
                }
            }
        }

        return islands;
    }

    private int find(int node) {

        // Path compression
        if (parent[node] != node) {
            parent[node] = find(parent[node]);
        }

        return parent[node];
    }

    private boolean union(int node1, int node2) {

        int root1 = find(node1);
        int root2 = find(node2);

        // Already part of the same island
        if (root1 == root2) {
            return false;
        }

        // Union by size
        if (size[root1] < size[root2]) {
            int temp = root1;
            root1 = root2;
            root2 = temp;
        }

        parent[root2] = root1;
        size[root1] += size[root2];

        return true;
    }
}
