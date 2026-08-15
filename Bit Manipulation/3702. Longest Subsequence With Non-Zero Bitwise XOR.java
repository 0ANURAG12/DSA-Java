/*
 * LeetCode 3702. Longest Subsequence With Non-Zero Bitwise XOR
 * https://leetcode.com/problems/longest-subsequence-with-non-zero-bitwise-xor/
 *
 * Approach:
 * - Calculate the XOR of all elements.
 * - If the total XOR is non-zero, the entire array is valid,
 *   so the answer is n.
 * - If the total XOR is zero but at least one element is non-zero,
 *   remove one non-zero element. The remaining XOR becomes that
 *   non-zero element, so the answer is n - 1.
 * - If all elements are zero, every subsequence has XOR 0,
 *   so the answer is 0.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */

class Solution {

    public int longestSubsequence(int[] nums) {

        int n = nums.length;
        int xor = 0;
        boolean hasNonZero = false;

        for (int num : nums) {
            xor ^= num;

            if (num != 0) {
                hasNonZero = true;
            }
        }

        if (xor != 0) {
            return n;
        }

        return hasNonZero ? n - 1 : 0;
    }
}
