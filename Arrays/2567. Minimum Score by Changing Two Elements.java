/*
 * LeetCode: 2567. Minimum Score by Changing Two Elements
 * https://leetcode.com/problems/minimum-score-by-changing-two-elements/
 *
 * Topic: Arrays / Sorting / Greedy
 *
 * ============================================================
 * APPROACH 1: Sorting + Greedy
 * ============================================================
 *
 * Intuition:
 * - We need to change exactly two elements so that the difference
 *   between the maximum and minimum elements is minimized.
 *
 * - First, sort the array in ascending order.
 *
 * - After sorting, the minimum and maximum elements can be changed
 *   in three optimal ways:
 *
 *   Case 1:
 *   Change the two smallest elements.
 *   Remaining range = nums[n - 2] - nums[1]
 *
 *   Case 2:
 *   Change the smallest and largest elements.
 *   Remaining range = nums[n - 1] - nums[2]
 *
 *   Case 3:
 *   Change the two largest elements.
 *   Remaining range = nums[n - 3] - nums[0]
 *
 * - Calculate all three possibilities and return the minimum.
 *
 * Example:
 * Input: nums = [1, 4, 7, 8, 5]
 *
 * After sorting:
 * [1, 4, 5, 7, 8]
 *
 * Case 1: Change two smallest elements:
 * Remaining range = 7 - 5 = 2
 *
 * Case 2: Change smallest and largest elements:
 * Remaining range = 8 - 5 = 3
 *
 * Case 3: Change two largest elements:
 * Remaining range = 5 - 1 = 4
 *
 * Minimum score = min(2, 3, 4) = 2
 *
 * Output: 2
 *
 * Time Complexity: O(n log n)
 * - Sorting the array takes O(n log n).
 * - Checking the three possible cases takes O(1).
 *
 * Space Complexity:
 * - O(log n) auxiliary space for Java's primitive array sorting
 *   implementation (implementation-dependent).
 *
 */

class Solution {
    public int minimizeSum(int[] nums) {
        int n = nums.length;

        // Sort the array to easily identify the smallest
        // and largest elements.
        Arrays.sort(nums);

        // Case 1: Change the two smallest elements.
        // Case 2: Change the smallest and largest elements.
        // Case 3: Change the two largest elements.
        return Math.min(
            nums[n - 2] - nums[1],
            Math.min(
                nums[n - 1] - nums[2],
                nums[n - 3] - nums[0]
            )
        );
    }
}
