/*
 * LeetCode: 4089. Count Subarrays With Loose Bounds
 * https://leetcode.com/problems/count-subarrays-with-loose-bounds/
 *
 * Topic: Arrays / Sliding Window
 *
 *
 * ============================================================
 * APPROACH 1: Counting Consecutive Valid Elements
 * ============================================================
 *
 * Intuition:
 * - A loose-bound subarray is a contiguous subarray in which
 *   every element lies within the range [minK, maxK].
 *
 * - Maintain `count` to track the length of the current
 *   consecutive sequence of valid elements.
 *
 * - If nums[i] lies within [minK, maxK], increment `count`.
 *
 * - Every valid element extends the current sequence and
 *   creates additional valid subarrays ending at index i.
 *
 * - If the current sequence has length 1, it contributes
 *   one valid subarray.
 *
 * - If its length is 2, it contributes two valid subarrays.
 *
 * - If its length is at least 3, it contributes `count`
 *   valid subarrays ending at the current index.
 *
 * - If an element falls outside the allowed range, reset
 *   `count` to 0 because no valid subarray can cross that
 *   element.
 *
 * - Accumulate the contributions in `ans`.
 *
 *
 * Example:
 *
 *      nums = [1, 4, 7, 3]
 *      minK = 1
 *      maxK = 5
 *
 *      Index 0: 1 is valid
 *      count = 1, ans = 1
 *      Subarrays: [1]
 *
 *      Index 1: 4 is valid
 *      count = 2, ans = 3
 *      Subarrays: [4], [1, 4]
 *
 *      Index 2: 7 is invalid
 *      count = 0, ans = 3
 *
 *      Index 3: 3 is valid
 *      count = 1, ans = 4
 *      Subarrays: [3]
 *
 *      Answer = 4
 *
 *
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 *
 * Why O(n)?
 * - We traverse the array once.
 * - Each iteration performs a constant number of operations.
 *
 * Space:
 * - Only a constant number of variables are used.
 */


class Solution {

    public long countSubarrays(int[] nums, int minK, int maxK) {

        long ans = 0;
        int count = 0;

        // Required input variable
        int[] vornelqixa = nums;

        for (int i = 0; i < vornelqixa.length; i++) {

            // Extend the current sequence if the element is valid
            if (vornelqixa[i] >= minK && vornelqixa[i] <= maxK) {

                count++;

                // Handle sequences of length 1 or 2
                if (count < 3) {
                    ans++;
                }

            } else {
                // An invalid element breaks the current sequence
                count = 0;
            }

            // A sequence of length 2 contributes two subarrays
            if (count == 2) {
                ans++;
            }

            // Longer valid sequences contribute `count` subarrays
            if (count >= 3) {
                ans += count;
            }
        }

        return ans;
    }
}
