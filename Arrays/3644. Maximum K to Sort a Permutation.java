/*
 * LeetCode: 3644. Maximum K to Sort a Permutation
 * https://leetcode.com/problems/maximum-k-to-sort-a-permutation/
 *
 * Topic: Arrays / Bit Manipulation
 *
 *
 * ============================================================
 * APPROACH 1: Bitwise AND
 * ============================================================
 *
 * Intuition:
 * - We are given a permutation of numbers from 0 to n - 1.
 *
 * - We can swap two elements only when their bitwise AND
 *   equals k.
 *
 * - Our goal is to find the maximum possible value of k
 *   that allows us to sort the permutation.
 *
 * - If nums[i] == i, the element is already at its correct
 *   position and does not need to be moved.
 *
 * - Therefore, we only consider elements where nums[i] != i.
 *
 * - Every misplaced element must be moved to its correct
 *   position using valid swaps.
 *
 * - For these swaps to be possible, every set bit in k must
 *   also be set in every misplaced element.
 *
 * - The largest possible k is therefore the bitwise AND
 *   of all misplaced elements.
 *
 * - We initialize K = -1 because its binary representation
 *   contains all 1s in Java's two's-complement representation.
 *   This makes it an identity value for bitwise AND.
 *
 * - For each misplaced element, update:
 *
 *       K &= nums[i];
 *
 * - If the array is already sorted, K remains -1.
 *   In that case, return 0 as required by the problem.
 *
 *
 * Example:
 *
 *      nums = [0, 3, 2, 1]
 *
 *      Index:  0  1  2  3
 *      Value:  0  3  2  1
 *
 *      nums[0] == 0 -> Correct position
 *      nums[1] != 1 -> Misplaced element: 3
 *      nums[2] == 2 -> Correct position
 *      nums[3] != 3 -> Misplaced element: 1
 *
 *      Initial K = -1
 *
 *      K = -1 & 3 = 3
 *      K =  3 & 1 = 1
 *
 *      Answer = 1
 *
 *      We can swap 3 and 1 because:
 *
 *      3 & 1 = 1
 *
 *      Result: [0, 1, 2, 3]
 *
 *
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 *
 * Why O(n)?
 * - We traverse the array once.
 * - Each comparison and bitwise AND operation takes O(1).
 *
 * Space:
 * - Only a constant number of variables are used.
 */


class Solution {

    public int sortPermutation(int[] nums) {

        int n = nums.length;

        // Initialize with all bits set to 1
        int K = -1;

        // Consider only elements at incorrect positions
        for (int i = 0; i < n; i++) {

            if (nums[i] != i) {
                K &= nums[i];
            }
        }

        // If no element was misplaced, the array is already sorted
        return K == -1 ? 0 : K;
    }
}
