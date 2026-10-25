/*
 * LeetCode: 4088. Peak Valley Difference
 * Topic: Arrays / Traversal
 *
 *
 * ============================================================
 * APPROACH 1: Single Pass
 * ============================================================
 *
 * Intuition:
 * - We need to calculate the difference between the sum of
 *   all peaks and the sum of all valleys.
 *
 * - A peak is an element strictly greater than both of
 *   its adjacent elements.
 *
 * - A valley is an element strictly smaller than both of
 *   its adjacent elements.
 *
 * - The first and last elements cannot be peaks or valleys
 *   because they do not have two adjacent elements.
 *
 * - Traverse the array from index 1 to n - 2.
 *
 * - If nums[i] is greater than both neighbors, add it to
 *   `peak`.
 *
 * - If nums[i] is smaller than both neighbors, add it to
 *   `valley`.
 *
 * - Finally, return:
 *
 *       peak - valley
 *
 *
 * Example:
 *
 *      nums = [1, 3, 2, 4, 1]
 *
 *      Peak:
 *      3 > 1 and 3 > 2 -> Peak
 *      4 > 2 and 4 > 1 -> Peak
 *
 *      Valley:
 *      2 < 3 and 2 < 4 -> Valley
 *
 *      peak = 3 + 4 = 7
 *      valley = 2
 *
 *      Answer = 7 - 2 = 5
 *
 *
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 *
 * Why O(n)?
 * - We traverse the array once.
 * - Each element requires a constant number of comparisons.
 *
 * Space:
 * - Only two accumulator variables are used.
 */


class Solution {

    public int peakValleyDifference(int[] nums) {

        int peak = 0;
        int valley = 0;

        // First and last elements cannot be peaks or valleys
        for (int i = 1; i < nums.length - 1; i++) {

            // Check whether the current element is a peak
            if (nums[i] > nums[i - 1] &&
                nums[i] > nums[i + 1]) {

                peak += nums[i];
            }

            // Check whether the current element is a valley
            if (nums[i] < nums[i - 1] &&
                nums[i] < nums[i + 1]) {

                valley += nums[i];
            }
        }

        // Difference between the sum of peaks and valleys
        return peak - valley;
    }
}
