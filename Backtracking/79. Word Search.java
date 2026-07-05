/*
 * LeetCode: 79. Word Search
 * https://leetcode.com/problems/word-search/
 *
 * ------------------------------------------------------------
 * Approach: Backtracking + Depth First Search (DFS)
 * ------------------------------------------------------------
 * Intuition:
 * - Traverse every cell in the board as a possible starting point.
 * - If the current cell matches the first character of the word,
 *   start a DFS to search for the remaining characters.
 * - Explore all four possible directions (up, down, left, right).
 * - Mark the current cell as visited by replacing it with '$' to
 *   prevent revisiting it in the current search path.
 * - Restore the original character after exploring all paths
 *   (backtracking) so it can be reused in other searches.
 * - If all characters of the word are matched, return true.
 *
 * Time Complexity: O(M × N × 4^L)
 * Space Complexity: O(L)
 *
 * where:
 * M = Number of rows
 * N = Number of columns
 * L = Length of the target word
 *
 * Alternative Approach:
 * 1. Backtracking + DFS using a separate visited[][] array
 *    - Maintain a boolean visited matrix instead of modifying the board.
 *    - Time Complexity: O(M × N × 4^L)
 *    - Space Complexity: O(M × N + L)
 */

class Solution {
    public boolean exist(char[][] board, String word) {
        int m = board.length;
        int n = board[0].length;

        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(board[i][j] == word.charAt(0) && find(i, j, word, board, 0, n, m))
                    return true;
            }
        }
        return false;
    }

    boolean find(int i, int j, String word, char[][] board, int idx, int n, int m){
        if(idx == word.length())
            return true;

        if(i < 0 || j < 0 || i >= m || j >= n ||
           board[i][j] == '$' || board[i][j] != word.charAt(idx))
            return false;

        int[][] dir = {{0,1}, {1,0}, {-1,0}, {0,-1}};

        char c = board[i][j];
        board[i][j] = '$';

        for(int[] d : dir){
            int new_i = i + d[0];
            int new_j = j + d[1];

            if(find(new_i, new_j, word, board, idx + 1, n, m))
                return true;
        }

        board[i][j] = c;

        return false;
    }
}
