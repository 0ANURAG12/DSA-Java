/**
 * Problem: 3041. Maximize Consecutive Elements in an Array After Modification
 * Difficulty: Hard
 * LeetCode Link: https://leetcode.com/problems/maximize-consecutive-elements-in-an-array-after-modification/
 *
 * Approach:
 * 1. Sort the array so that consecutive sequences can be built in increasing order.
 * 2. Each element nums[i] has two choices:
 *    - Leave it unchanged (inc = 0) -> effective value = nums[i]
 *    - Increment it by 1 (inc = 1)  -> effective value = nums[i] + 1
 * 3. For a current effective value `currVal = nums[i] + inc`, the next consecutive element
 *    must have an effective value of `currVal + 1`. We use lower-bound binary search on
 *    indices [i + 1 ... n - 1] to find the earliest index `j` that can form `currVal + 1`:
 *    - Either nums[j] == currVal + 1 (taken without incrementing, state [j][0])
 *    - Or nums[j] == currVal (taken with +1 increment, state [j][1])
 * 4. Compute the maximum chain length across all starting indices and both choices.
 *
 * Time Complexity: O(N log N) — sorting takes O(N log N), and each of the 2N states does O(log N) binary search.
 * Space Complexity: O(N) — for the DP/memoization table of size N x 2 (plus recursion stack in Top-Down).
 */

import java.util.Arrays;

// ============================================================================
// Approach 1: Top-Down Dynamic Programming (Recursion + Memoization)
// ============================================================================
class Solution {
    int[][] dp;

    public int maxSelectedElements(int[] nums) {
        Arrays.sort(nums);

        int n = nums.length;
        dp = new int[n][2];

        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }

        int result = 1;

        for (int i = 0; i < n; i++) {
            result = Math.max(result, solve(i, 0, nums));
            result = Math.max(result, solve(i, 1, nums));
        }

        return result;
    }

    private int solve(int i, int inc, int[] nums) {
        if (dp[i][inc] != -1) {
            return dp[i][inc];
        }

        int currVal = nums[i] + inc;
        int len = 1;

        // Option 1: Find currVal + 1 directly (without incrementing)
        int j = findMethod(i + 1, currVal + 1, nums);
        if (j != -1) {
            len = Math.max(len, 1 + solve(j, 0, nums));
        }

        // Option 2: Find currVal and increment it by 1
        j = findMethod(i + 1, currVal, nums);
        if (j != -1) {
            len = Math.max(len, 1 + solve(j, 1, nums));
        }

        return dp[i][inc] = len;
    }

    private int findMethod(int start, int target, int[] nums) {
        int low = start;
        int high = nums.length - 1;
        int ans = -1;

        // Find first occurrence (lower bound)
        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (nums[mid] >= target) {
                if (nums[mid] == target) {
                    ans = mid;
                }
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return ans;
    }
}

// ============================================================================
// Approach 2: Bottom-Up Dynamic Programming (Tabulation)
// ============================================================================

class Solution {
    public int maxSelectedElements(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);

        int result = 1;
        int[][] memo = new int[n + 1][2];

        for (int i = n - 1; i >= 0; i--) {
            for (int inc = 0; inc < 2; inc++) {
                int currVal = nums[i] + inc;
                int len = 1;

                // Option 1: Find currVal + 1 directly (without incrementing)
                int j = findMethod(i + 1, currVal + 1, nums);
                if (j != -1) {
                    len = Math.max(len, 1 + memo[j][0]);
                }

                // Option 2: Find currVal and increment it by 1
                j = findMethod(i + 1, currVal, nums);
                if (j != -1) {
                    len = Math.max(len, 1 + memo[j][1]);
                }

                memo[i][inc] = len;
                result = Math.max(result, memo[i][inc]);
            }
        }

        return result;
    }

    private int findMethod(int i, int currVal, int[] nums) {
        int j = nums.length - 1;
        int ans = -1;

        while (i <= j) {
            int mid = i + (j - i) / 2;
            if (nums[mid] >= currVal) {
                if (nums[mid] == currVal) {
                    ans = mid;
                }
                j = mid - 1;
            } else {
                i = mid + 1;
            }
        }

        return ans;
    }
}
