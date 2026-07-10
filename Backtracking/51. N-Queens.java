/*
 * LeetCode: 51. N-Queens
 * https://leetcode.com/problems/n-queens/
 *
 * ------------------------------------------------------------
 * Approach 1: Backtracking + Board Validation
 * ------------------------------------------------------------
 * Intuition:
 * - Place one queen in each row.
 * - Before placing a queen, check whether the current position is safe.
 * - A position is valid if no queen exists in:
 *   1. The same column.
 *   2. The upper-left diagonal.
 *   3. The upper-right diagonal.
 * - If the position is valid, place the queen and recursively solve
 *   the next row.
 * - Once all rows are processed, record the current board configuration.
 *
 * Time Complexity: O(N!)
 * Space Complexity: O(N²)
 *
 * where:
 * N = Size of the chessboard.
 *
 * Explanation:
 * - At most N choices are explored for each row.
 * - Validity checking takes O(N) for each placement.
 * - The board itself requires O(N²) space.
 *
 * Alternative Approach:
 * 1. Backtracking + Hashing (Columns & Diagonals)
 *    - Instead of scanning the board every time, maintain three boolean
 *      arrays to mark occupied columns and diagonals.
 *    - This reduces the validity check from O(N) to O(1).
 *
 * Time Complexity: O(N!)
 * Space Complexity: O(N²)
 */
class Solution {
    List<List<String>> ans;
    public List<List<String>> solveNQueens(int n) {
        ans = new ArrayList<>();
        char[][] board = new char[n][n];
        for(char[] b:board) Arrays.fill(b,'.');

        solve(board,0);
        return ans;
    }
    void solve(char[][] board, int row) {
        if (row == board.length) {
            List<String> temp = new ArrayList<>();
            for (char[] r : board) {
                temp.add(new String(r));
            }
            ans.add(temp);
            return;
        }

        for (int col = 0; col < board.length; col++) {
            if (isValid(board, row, col)) {
                board[row][col] = 'Q';
                solve(board, row + 1);
                board[row][col] = '.';
            }
        }
        return;
    }
    boolean isValid(char[][] board,int row,int col){
        //vertical
        int n = board.length;
        for(int i = 0;i<row;i++){
            if(board[i][col] == 'Q') return false;
        }

        // upper-left
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 'Q') return false;
        }

        // upper-right
        for (int i = row - 1, j = col + 1; i >= 0 && j < board.length; i--, j++) {
            if (board[i][j] == 'Q') return false;
        }

        return true;

    }
}
/*
 * ------------------------------------------------------------
 * Approach 2: Backtracking + Hashing
 * ------------------------------------------------------------
 * Intuition:
 * - Place one queen in each row.
 * - Instead of checking the board every time, maintain:
 *   1. cols[]  -> occupied columns.
 *   2. diag1[] -> occupied main diagonals (row + col).
 *   3. diag2[] -> occupied anti-diagonals (row - col + n - 1).
 * - Before placing a queen, simply check these arrays.
 * - If the position is available, place the queen, update the arrays,
 *   recursively solve the next row, and backtrack afterward.
 * - This makes validity checking O(1).
 *
 * Time Complexity: O(N!)
 * Space Complexity: O(N²)
 *
 * where:
 * N = Size of the chessboard.
 *
 * Explanation:
 * - Backtracking still explores the same search space.
 * - Using boolean arrays eliminates the O(N) safety check performed
 *   in the basic solution, making it significantly faster in practice.
 *
 * Alternative Approach:
 * 1. Bitmasking + Backtracking
 *    - Represent occupied columns and diagonals using bitmasks.
 *    - Perform validity checks and updates using bitwise operations.
 *    - This is the fastest solution and is commonly used for large N.
 *
 * Time Complexity: O(N!)
 * Space Complexity: O(N)
 */
class Solution {
    List<List<String>> ans = new ArrayList<>();

    boolean[] cols;
    boolean[] diag1;
    boolean[] diag2;

    public List<List<String>> solveNQueens(int n) {
        cols = new boolean[n];
        diag1 = new boolean[2 * n - 1];      // row + col
        diag2 = new boolean[2 * n - 1];      // row - col + n - 1

        char[][] board = new char[n][n];
        for (char[] row : board) {
            Arrays.fill(row, '.');
        }

        solve(board, 0);
        return ans;
    }

    void solve(char[][] board, int row) {
        if (row == board.length) {
            List<String> temp = new ArrayList<>();
            for (char[] r : board) {
                temp.add(new String(r));
            }
            ans.add(temp);
            return;
        }

        for (int col = 0; col < board.length; col++) {

            int d1 = row + col;
            int d2 = row - col + board.length - 1;

            if (cols[col] || diag1[d1] || diag2[d2]) {
                continue;
            }

            // Place queen
            board[row][col] = 'Q';
            cols[col] = true;
            diag1[d1] = true;
            diag2[d2] = true;

            solve(board, row + 1);

            // Backtrack
            board[row][col] = '.';
            cols[col] = false;
            diag1[d1] = false;
            diag2[d2] = false;
        }
    }
}
