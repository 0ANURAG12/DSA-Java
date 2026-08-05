/*
 * LeetCode 1406. Stone Game III
 * https://leetcode.com/problems/stone-game-iii/
 *
 * Approach:
 * - Dynamic Programming (Memoization)
 * - Let solve(i) represent the maximum score difference
 *   (current player's score - opponent's score) starting from index i.
 * - At each turn, the player can take 1, 2, or 3 stones.
 * - After taking stones, the opponent plays optimally, so subtract
 *   the opponent's best score difference.
 *
 *   solve(i) = max(
 *                  take1 - solve(i + 1),
 *                  take2 - solve(i + 2),
 *                  take3 - solve(i + 3)
 *                )
 *
 * - If the final score difference is:
 *      > 0 : Alice wins
 *      < 0 : Bob wins
 *      = 0 : Tie
 *
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 */

class Solution {

    int[] stone;
    Integer[] dp;

    public String stoneGameIII(int[] stone) {
        this.stone = stone;
        dp = new Integer[stone.length];

        int scoreDifference = solve(0);

        if (scoreDifference > 0) {
            return "Alice";
        }

        if (scoreDifference < 0) {
            return "Bob";
        }

        return "Tie";
    }

    private int solve(int i) {

        if (i >= stone.length) {
            return 0;
        }

        if (dp[i] != null) {
            return dp[i];
        }

        int take = 0;
        int best = Integer.MIN_VALUE;

        for (int k = 0; k < 3 && i + k < stone.length; k++) {
            take += stone[i + k];
            best = Math.max(best, take - solve(i + k + 1));
        }

        return dp[i] = best;
    }
}
