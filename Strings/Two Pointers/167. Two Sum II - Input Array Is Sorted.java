/*
 * LeetCode: 167. Two Sum II - Input Array Is Sorted
 * https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/
 *
 * Topic: Arrays / Two Pointers / Binary Search
 *
 *
 * ============================================================
 * APPROACH 1: Two Pointers
 * ============================================================
 *
 * Intuition:
 * - The input array is sorted in non-decreasing order.
 *
 * - We need to find two numbers whose sum equals the target.
 *
 * - Initialize two pointers:
 *
 *       l = 0
 *       r = n - 1
 *
 * - Calculate the sum of nums[l] and nums[r].
 *
 * - If the sum equals the target, return their 1-based indices.
 *
 * - If the sum is greater than the target, decrease r to
 *   obtain a smaller sum.
 *
 * - If the sum is less than the target, increase l to
 *   obtain a larger sum.
 *
 * - Continue until the pair is found or the pointers meet.
 *
 *
 * Example:
 *
 *      numbers = [2, 7, 11, 15]
 *      target = 9
 *
 *      l = 0, r = 3
 *      2 + 15 = 17 > 9 -> r--
 *
 *      l = 0, r = 2
 *      2 + 11 = 13 > 9 -> r--
 *
 *      l = 0, r = 1
 *      2 + 7 = 9
 *
 *      Answer = [1, 2]
 *
 *
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 *
 * Why O(n)?
 * - Each iteration moves one pointer toward the other.
 * - Each pointer moves at most n positions.
 *
 * Space:
 * - Only two pointers and a temporary sum are used.
 */


class Solution {

    public int[] twoSum(int[] n, int t) {

        int l = 0;
        int r = n.length - 1;

        while (l < r) {

            int val = n[l] + n[r];

            // Target found: return 1-based indices
            if (val == t) {
                return new int[]{l + 1, r + 1};
            }

            // Sum is too large; move right pointer left
            if (val > t) {
                r--;
            }

            // Sum is too small; move left pointer right
            else {
                l++;
            }
        }

        // No valid pair found
        return new int[]{-1, -1};
    }
}
