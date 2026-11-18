/*
 * LeetCode: 3364. Minimum Positive Sum Subarray
 * https://leetcode.com/problems/minimum-positive-sum-subarray/
 *
 * Topic: Arrays / Brute Force / Subarrays / Prefix Sum
 *
 *
 * ============================================================
 * APPROACH 1: Brute Force (Nested Loops)
 * ============================================================
 *
 * Intuition:
 * - We are given an integer list nums and two integers l and r.
 *
 * - We need to find the minimum positive sum among all
 *   contiguous subarrays whose lengths are between l and r.
 *
 * - Use two nested loops to generate every possible subarray.
 *
 * - The outer loop fixes the starting index i.
 *
 * - The inner loop extends the subarray by moving j forward.
 *
 * - Maintain a running sum instead of recalculating the
 *   sum of the subarray every time.
 *
 * - For every subarray:
 *
 *   1. Calculate its length:
 *
 *          length = j - i + 1
 *
 *   2. Check whether its length is between l and r.
 *
 *   3. Check whether its sum is positive.
 *
 *   4. Update the minimum answer if the current sum is smaller.
 *
 * - If no valid positive-sum subarray is found, return -1.
 *
 *
 * Example:
 *
 *      nums = [3, -2, 5, -1]
 *      l = 2, r = 3
 *
 *      Valid subarrays:
 *
 *      [3, -2]       -> Sum = 1
 *      [3, -2, 5]    -> Sum = 6
 *      [-2, 5]       -> Sum = 3
 *      [5, -1]       -> Sum = 4
 *      [-2, 5, -1]   -> Sum = 2
 *
 *      Minimum positive sum = 1
 *
 *      Answer = 1
 *
 *
 * Time Complexity: O(n^2)
 * Space Complexity: O(1)
 *
 * Why O(n^2)?
 * - The outer loop runs n times.
 * - The inner loop can run up to n times.
 * - Running sum allows each subarray extension to take O(1).
 *
 * Space:
 * - Only a few variables are used.
 * - No additional data structure is required.
 */


class Solution {

    public int minimumSumSubarray(List<Integer> nums, int l, int r) {

        int ans = Integer.MAX_VALUE;
        int n = nums.size();

        // Fix the starting index
        for (int i = 0; i < n; i++) {

            int sum = 0;

            // Extend the subarray
            for (int j = i; j < n; j++) {

                sum += nums.get(j);

                int length = j - i + 1;

                // Check length constraints and positive sum
                if (length >= l && length <= r && sum > 0) {
                    ans = Math.min(ans, sum);
                }
            }
        }

        // No valid positive-sum subarray found
        if (ans == Integer.MAX_VALUE) {
            return -1;
        }

        return ans;
    }
}
