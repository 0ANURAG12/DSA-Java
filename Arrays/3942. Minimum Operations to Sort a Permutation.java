/*
 * LeetCode: 3942. Minimum Operations to Sort a Permutation
 * https://leetcode.com/problems/minimum-operations-to-sort-a-permutation/
 *
 * Topic: Arrays / Greedy / Math
 *
 *
 * ============================================================
 * APPROACH 1: Greedy + Case Analysis
 * ============================================================
 *
 * Intuition:
 * - We are given a permutation of numbers from 0 to n - 1.
 *
 * - The allowed operations are:
 *   1. Reverse the entire array.
 *   2. Rotate the array left by one position.
 *
 * - We need to find the minimum operations required to sort
 *   the array in increasing order.
 *
 * - Since these operations preserve the cyclic order of the
 *   elements, the array must follow either an ascending or
 *   descending cyclic pattern to be sortable.
 *
 * - We maintain two counters:
 *
 *   asc_count:
 *   Counts adjacent pairs where the next element is
 *   (previous element + 1) % n.
 *
 *   dsc_count:
 *   Counts adjacent pairs where the previous element is
 *   (next element + 1) % n.
 *
 * - If asc_count == n - 1, the array follows an ascending
 *   cyclic pattern.
 *
 * - If dsc_count == n - 1, the array follows a descending
 *   cyclic pattern.
 *
 * - If neither condition holds, sorting is impossible,
 *   so we return -1.
 *
 * - For a valid pattern, we calculate the operations needed
 *   using the two possible transformation paths and return
 *   the minimum.
 *
 * - If the array is already sorted and starts with 0,
 *   return 0 immediately.
 *
 *
 * Example:
 *
 *      nums = [0, 2, 1]
 *      n = 3
 *
 *      asc_count = 0
 *      dsc_count = 2
 *
 *      Since dsc_count == n - 1, the array follows
 *      a descending cyclic pattern.
 *
 *      leftRot  = n - nums[n - 1] + 1
 *               = 3 - 1 + 1
 *               = 3
 *
 *      rightRot = (nums[0] + 1) % n + 1
 *               = (0 + 1) % 3 + 1
 *               = 2
 *
 *      Answer = min(3, 2) = 2
 *
 *      Operations:
 *      [0, 2, 1]
 *      Rotate left: [2, 1, 0]
 *      Reverse:     [0, 1, 2]
 *
 *
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 *
 * Why O(n)?
 * - We scan the array to count ascending and descending pairs.
 * - Each additional check also takes O(1) time.
 *
 * Space:
 * - Only a constant number of variables are used.
 */


class Solution {

    public int minOperations(int[] nums) {

        int n = nums.length;

        int asc_count = 0;
        int dsc_count = 0;

        // Count ascending and descending cyclic adjacent pairs
        for (int i = 1; i < n; i++) {

            asc_count += (nums[i] == (nums[i - 1] + 1) % n) ? 1 : 0;

            dsc_count += (nums[i - 1] == (nums[i] + 1) % n) ? 1 : 0;
        }

        // Already sorted
        if (asc_count == n - 1 && nums[0] == 0) {
            return 0;
        }

        // Ascending cyclic pattern
        if (asc_count == n - 1) {

            int leftRot = n - nums[0];
            int rightRot = 1 + (nums[n - 1] + 1) + 1;

            return Math.min(leftRot, rightRot);
        }

        // Descending cyclic pattern
        if (dsc_count == n - 1) {

            int leftRot = n - nums[n - 1] + 1;
            int rightRot = (nums[0] + 1) % n + 1;

            return Math.min(leftRot, rightRot);
        }

        // Neither cyclic pattern is valid
        return -1;
    }
}
