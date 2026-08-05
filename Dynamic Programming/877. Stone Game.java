/*
 * LeetCode 877. Stone Game
 * https://leetcode.com/problems/stone-game/
 *
 * Approach:
 * - Dynamic Programming (Memoization)
 * - At each turn, the current player has two choices:
 *      1. Pick the leftmost pile.
 *      2. Pick the rightmost pile.
 * - Since both players play optimally, after Player 1 picks a pile,
 *   Player 2 will choose the move that minimizes Player 1's future score.
 *
 *   takeLeft  = piles[left] +
 *               min(solve(left + 2, right),
 *                   solve(left + 1, right - 1))
 *
 *   takeRight = piles[right] +
 *               min(solve(left, right - 2),
 *                   solve(left + 1, right - 1))
 *
 * - Memoization stores the maximum stones Player 1 can collect
 *   from every subarray [left...right].
 * - Finally, compare Player 1's score with the remaining stones.
 *
 * Time Complexity: O(n²)
 * Space Complexity: O(n²)
 */

class Solution {

    int[] piles;
    int[][] dp;

    public boolean stoneGame(int[] piles) {
        this.piles = piles;

        int n = piles.length;
        dp = new int[n][n];

        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }

        int totalStones = 0;
        for (int pile : piles) {
            totalStones += pile;
        }

        int player1 = solve(0, n - 1);

        return player1 > totalStones - player1;
    }

    private int solve(int left, int right) {

        if (left > right) {
            return 0;
        }

        if (left == right) {
            return piles[left];
        }

        if (dp[left][right] != -1) {
            return dp[left][right];
        }

        // Pick the left pile
        int takeLeft = piles[left] + Math.min(
                solve(left + 2, right),
                solve(left + 1, right - 1)
        );

        // Pick the right pile
        int takeRight = piles[right] + Math.min(
                solve(left, right - 2),
                solve(left + 1, right - 1)
        );

        return dp[left][right] = Math.max(takeLeft, takeRight);
    }
}
