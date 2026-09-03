/**
 * LeetCode 695 - Max Area of Island
 *
 * Problem:
 * https://leetcode.com/problems/max-area-of-island/
 */


/* ============================================================
 * APPROACH 1: DFS + VISITED ARRAY
 * ============================================================
 *
 * Approach:
 * Traverse the entire grid.
 *
 * Whenever we find an unvisited land cell (1), start a DFS.
 * The DFS explores all four directions and calculates the
 * total number of connected land cells.
 *
 * A separate visited[][] array is used so that we don't
 * visit the same cell multiple times.
 *
 * Time Complexity: O(m * n)
 *
 * Space Complexity: O(m * n)
 * - visited[][] takes O(m * n)
 * - Recursion stack can take O(m * n) in the worst case
 *
 * Advantage:
 * - Does not modify the input grid.
 *
 * ============================================================
 */

class DFSWithVisited {

    int rows;
    int cols;

    public int maxAreaOfIsland(int[][] grid) {

        rows = grid.length;
        cols = grid[0].length;

        boolean[][] visited = new boolean[rows][cols];

        int maxArea = 0;

        for (int i = 0; i < rows; i++) {

            for (int j = 0; j < cols; j++) {

                if (grid[i][j] == 1 && !visited[i][j]) {

                    maxArea = Math.max(
                        maxArea,
                        dfs(grid, visited, i, j)
                    );
                }
            }
        }

        return maxArea;
    }

    private int dfs(
        int[][] grid,
        boolean[][] visited,
        int row,
        int col
    ) {

        if (row < 0 || row >= rows ||
            col < 0 || col >= cols ||
            grid[row][col] == 0 ||
            visited[row][col]) {

            return 0;
        }

        visited[row][col] = true;

        return 1
            + dfs(grid, visited, row + 1, col)
            + dfs(grid, visited, row - 1, col)
            + dfs(grid, visited, row, col + 1)
            + dfs(grid, visited, row, col - 1);
    }
}


/* ============================================================
 * APPROACH 2: DFS + MODIFY GRID
 * ============================================================
 *
 * Approach:
 * Use DFS to explore each island.
 *
 * Instead of maintaining a separate visited[][] array,
 * mark a visited land cell by changing:
 *
 *      1 -> 0
 *
 * This prevents us from visiting the same cell again.
 *
 * Time Complexity: O(m * n)
 *
 * Space Complexity: O(m * n)
 * - No visited array is required.
 * - Recursion stack can take O(m * n) in the worst case.
 *
 * Advantage:
 * - Simpler than using a visited array.
 *
 * Disadvantage:
 * - Modifies the input grid.
 *
 * ============================================================
 */

class DFSModifyGrid {

    int rows;
    int cols;

    public int maxAreaOfIsland(int[][] grid) {

        rows = grid.length;
        cols = grid[0].length;

        int maxArea = 0;

        for (int i = 0; i < rows; i++) {

            for (int j = 0; j < cols; j++) {

                if (grid[i][j] == 1) {

                    maxArea = Math.max(
                        maxArea,
                        dfs(grid, i, j)
                    );
                }
            }
        }

        return maxArea;
    }

    private int dfs(int[][] grid, int row, int col) {

        if (row < 0 || row >= rows ||
            col < 0 || col >= cols ||
            grid[row][col] == 0) {

            return 0;
        }

        // Mark current cell as visited
        grid[row][col] = 0;

        return 1
            + dfs(grid, row + 1, col)
            + dfs(grid, row - 1, col)
            + dfs(grid, row, col + 1)
            + dfs(grid, row, col - 1);
    }
}


/* ============================================================
 * APPROACH 3: BFS + VISITED ARRAY
 * ============================================================
 *
 * Approach:
 * Instead of using recursion, use a Queue for BFS.
 *
 * Whenever an unvisited land cell is found:
 * - Add it to the queue.
 * - Visit all connected land cells.
 * - Count the number of cells in the island.
 *
 * Time Complexity: O(m * n)
 *
 * Space Complexity: O(m * n)
 * - visited[][] takes O(m * n)
 * - Queue can contain O(m * n) cells in the worst case.
 *
 * Advantage:
 * - Avoids recursion and possible stack overflow.
 *
 * ============================================================
 */

import java.util.*;

class BFSWithVisited {

    public int maxAreaOfIsland(int[][] grid) {

        int rows = grid.length;
        int cols = grid[0].length;

        boolean[][] visited = new boolean[rows][cols];

        int maxArea = 0;

        int[][] directions = {
            {1, 0},
            {-1, 0},
            {0, 1},
            {0, -1}
        };

        for (int i = 0; i < rows; i++) {

            for (int j = 0; j < cols; j++) {

                if (grid[i][j] == 1 && !visited[i][j]) {

                    int area = 0;

                    Queue<int[]> queue = new LinkedList<>();

                    queue.offer(new int[]{i, j});
                    visited[i][j] = true;

                    while (!queue.isEmpty()) {

                        int[] cell = queue.poll();

                        int row = cell[0];
                        int col = cell[1];

                        area++;

                        for (int[] direction : directions) {

                            int newRow = row + direction[0];
                            int newCol = col + direction[1];

                            if (newRow >= 0 && newRow < rows &&
                                newCol >= 0 && newCol < cols &&
                                grid[newRow][newCol] == 1 &&
                                !visited[newRow][newCol]) {

                                visited[newRow][newCol] = true;

                                queue.offer(
                                    new int[]{newRow, newCol}
                                );
                            }
                        }
                    }

                    maxArea = Math.max(maxArea, area);
                }
            }
        }

        return maxArea;
    }
}


/* ============================================================
 * APPROACH 4: BFS + MODIFY GRID
 * ============================================================
 *
 * Approach:
 * Use BFS to explore each island.
 *
 * Instead of using visited[][], mark every visited land cell
 * as 0.
 *
 * This allows us to track visited cells without an additional
 * boolean array.
 *
 * Time Complexity: O(m * n)
 *
 * Space Complexity: O(m * n)
 * - Queue can contain O(m * n) cells in the worst case.
 *
 * Advantage:
 * - No separate visited array.
 *
 * Disadvantage:
 * - Modifies the input grid.
 *
 * ============================================================
 */

class BFSModifyGrid {

    public int maxAreaOfIsland(int[][] grid) {

        int rows = grid.length;
        int cols = grid[0].length;

        int maxArea = 0;

        int[][] directions = {
            {1, 0},
            {-1, 0},
            {0, 1},
            {0, -1}
        };

        for (int i = 0; i < rows; i++) {

            for (int j = 0; j < cols; j++) {

                if (grid[i][j] == 1) {

                    int area = 0;

                    Queue<int[]> queue = new LinkedList<>();

                    queue.offer(new int[]{i, j});

                    // Mark as visited
                    grid[i][j] = 0;

                    while (!queue.isEmpty()) {

                        int[] cell = queue.poll();

                        int row = cell[0];
                        int col = cell[1];

                        area++;

                        for (int[] direction : directions) {

                            int newRow = row + direction[0];
                            int newCol = col + direction[1];

                            if (newRow >= 0 && newRow < rows &&
                                newCol >= 0 && newCol < cols &&
                                grid[newRow][newCol] == 1) {

                                // Mark before adding to queue
                                grid[newRow][newCol] = 0;

                                queue.offer(
                                    new int[]{newRow, newCol}
                                );
                            }
                        }
                    }

                    maxArea = Math.max(maxArea, area);
                }
            }
        }

        return maxArea;
    }
}


/* ============================================================
 * APPROACH 5: UNION-FIND / DISJOINT SET UNION
 * ============================================================
 *
 * Approach:
 * Treat every land cell as a node.
 *
 * If two adjacent cells contain 1, union them into the
 * same connected component.
 *
 * The size of each connected component represents the
 * area of that island.
 *
 * We only check the RIGHT and DOWN directions because
 * checking LEFT and UP would process the same connection
 * twice.
 *
 * Time Complexity:
 * O(m * n * α(m * n))
 *
 * α = inverse Ackermann function, which grows extremely
 * slowly, so this is practically O(m * n).
 *
 * Space Complexity: O(m * n)
 * - parent[] -> O(m * n)
 * - size[]   -> O(m * n)
 *
 * Advantage:
 * - Useful for learning connected components and DSU.
 *
 * Disadvantage:
 * - More complicated than DFS/BFS for this problem.
 *
 * ============================================================
 */

class UnionFind {

    int[] parent;
    int[] size;

    public int maxAreaOfIsland(int[][] grid) {

        int rows = grid.length;
        int cols = grid[0].length;

        int totalCells = rows * cols;

        parent = new int[totalCells];
        size = new int[totalCells];

        // Initialize DSU
        for (int i = 0; i < totalCells; i++) {

            parent[i] = i;
            size[i] = 1;
        }

        int maxArea = 0;

        // Only right and down are required
        int[][] directions = {
            {1, 0},
            {0, 1}
        };

        for (int i = 0; i < rows; i++) {

            for (int j = 0; j < cols; j++) {

                if (grid[i][j] == 0) {
                    continue;
                }

                int current = i * cols + j;

                maxArea = Math.max(maxArea, 1);

                for (int[] direction : directions) {

                    int newRow = i + direction[0];
                    int newCol = j + direction[1];

                    if (newRow < rows &&
                        newCol < cols &&
                        grid[newRow][newCol] == 1) {

                        int next = newRow * cols + newCol;

                        union(current, next);

                        int root = find(current);

                        maxArea = Math.max(
                            maxArea,
                            size[root]
                        );
                    }
                }
            }
        }

        return maxArea;
    }

    // Find with path compression
    private int find(int x) {

        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }

        return parent[x];
    }

    // Union by size
    private void union(int a, int b) {

        int rootA = find(a);
        int rootB = find(b);

        if (rootA == rootB) {
            return;
        }

        // Attach smaller component to larger component
        if (size[rootA] < size[rootB]) {

            int temp = rootA;
            rootA = rootB;
            rootB = temp;
        }

        parent[rootB] = rootA;
        size[rootA] += size[rootB];
    }
}
```
