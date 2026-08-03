/*
 * LeetCode 486. Predict the Winner
 * https://leetcode.com/problems/predict-the-winner/
 *
 * Approach:
 * - Dynamic Programming (Memoization)
 * - At every turn, Player 1 has two choices:
 *      1. Pick the leftmost element.
 *      2. Pick the rightmost element.
 * - Since Player 2 also plays optimally, after Player 1 makes a move,
 *   Player 2 will choose the option that minimizes Player 1's future score.
 * - Therefore:
 *
 *   takeLeft  = nums[left] +
 *               min(solve(left + 2, right),
 *                   solve(left + 1, right - 1))
 *
 *   takeRight = nums[right] +
 *               min(solve(left, right - 2),
 *                   solve(left + 1, right - 1))
 *
 * - Memoization stores the maximum score Player 1 can obtain
 *   for every subarray [left...right].
 *
 * Time Complexity: O(n²)
 * Space Complexity: O(n²)
 */

class Solution {

    int[][] dp;
    int[] nums;

    public boolean predictTheWinner(int[] nums) {
        this.nums = nums;

        dp = new int[21][21];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }

        int totalSum = 0;
        for (int num : nums) {
            totalSum += num;
        }

        int player1Score = solve(0, nums.length - 1);

        return player1Score >= (totalSum - player1Score);
    }

    private int solve(int left, int right) {

        if (left > right) {
            return 0;
        }

        if (left == right) {
            return nums[left];
        }

        if (dp[left][right] != -1) {
            return dp[left][right];
        }

        int takeLeft = nums[left] + Math.min(
                solve(left + 2, right),
                solve(left + 1, right - 1)
        );

        int takeRight = nums[right] + Math.min(
                solve(left, right - 2),
                solve(left + 1, right - 1)
        );

        return dp[left][right] = Math.max(takeLeft, takeRight);
    }
}
