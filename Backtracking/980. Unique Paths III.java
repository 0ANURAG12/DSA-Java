/*
 * LeetCode: 980. Unique Paths III
 * https://leetcode.com/problems/unique-paths-iii/
 *
 * ------------------------------------------------------------
 * Approach: Backtracking + Depth First Search (DFS)
 * ------------------------------------------------------------
 * Intuition:
 * - Count the total number of non-obstacle cells (including the starting cell).
 * - Locate the starting position.
 * - Perform a DFS from the starting cell while marking each visited cell.
 * - Explore all four possible directions (up, down, left, right).
 * - When the ending cell is reached, check whether every non-obstacle cell
 *   has been visited exactly once.
 * - If all required cells have been visited, increment the answer.
 * - Backtrack by restoring the current cell so it can be used in other paths.
 *
 * Time Complexity: O(4^K)
 * Space Complexity: O(K)
 *
 * where:
 * K = Number of non-obstacle cells
 *
 * Note:
 * - In the worst case, each cell can branch into four recursive calls.
 * - The recursion stack depth is at most K.
 *
 * Alternative Approach:
 * 1. Backtracking + DFS using a separate visited[][] array
 *    - Instead of modifying the input grid, maintain a boolean visited matrix.
 *    - Time Complexity: O(4^K)
 *    - Space Complexity: O(K + M × N)
 */

class Solution {
    int nonObstacleCount;
    int ans;
    int[][] grid;
    int m;
    int n;

    public int uniquePathsIII(int[][] grid) {
        this.grid = grid;
        this.m = grid.length;
        this.n = grid[0].length;

        nonObstacleCount = 1;
        ans = 0;

        int start_i = 0;
        int start_j = 0;

        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(grid[i][j] == 0) nonObstacleCount++;

                if(grid[i][j] == 1){
                    start_i = i;
                    start_j = j;
                }
            }
        }

        backtrack(0, start_i, start_j);

        return ans;
    }

    void backtrack(int count, int i, int j){
        if(i < 0 || i >= m || j < 0 || j >= n || grid[i][j] == -1)
            return;

        if(grid[i][j] == 2){
            if(count == nonObstacleCount){
                ans++;
            }
            return;
        }

        int temp = grid[i][j];
        grid[i][j] = -1;

        int[][] dir = {{0,1}, {1,0}, {-1,0}, {0,-1}};

        for(int[] d : dir){
            backtrack(count + 1, i + d[0], j + d[1]);
        }

        grid[i][j] = temp;
    }
}
