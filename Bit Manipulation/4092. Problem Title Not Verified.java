/*
 * LeetCode: 4092. Problem Title Not Verified
 * Problem Link: https://leetcode.com/
 *
 * Topic: Bit Manipulation / Math
 *
 *
 * ============================================================
 * APPROACH 1: Bit Counting + Conditional Calculation
 * ============================================================
 *
 * Intuition:
 * - We are given two integers, a and b.
 *
 * - We count the number of set bits (bits equal to 1)
 *   in each integer using bit manipulation.
 *
 * - The expression (num & 1) checks whether the rightmost
 *   bit is set.
 *
 * - If the rightmost bit is 1, increment the corresponding
 *   counter.
 *
 * - Right-shift the number using >>= 1 to process the
 *   next bit.
 *
 * - After counting the set bits:
 *
 *   1. If s1 >= s2, return s1 * s2.
 *   2. Otherwise, return s2 / s1 using integer division.
 *
 *
 * Example:
 *
 *      a = 7, b = 3
 *
 *      Binary representation:
 *      a = 111 -> s1 = 3
 *      b = 011 -> s2 = 2
 *
 *      Since s1 >= s2:
 *
 *      Answer = s1 * s2
 *             = 3 * 2
 *             = 6
 *
 *
 * Time Complexity: O(log a + log b)
 * Space Complexity: O(1)
 *
 * Why O(log a + log b)?
 * - Each right shift removes one bit.
 * - The number of iterations is proportional to the
 *   number of bits in each integer.
 *
 * Space:
 * - Only two counters and a constant number of variables
 *   are used.
 */


class Solution {

    public int calculateScore(int a, int b) {

        int s1 = 0;
        int s2 = 0;

        // Count set bits in a
        while (a > 0) {
            if ((a & 1) == 1) {
                s1++;
            }

            a >>= 1;
        }

        // Count set bits in b
        while (b > 0) {
            if ((b & 1) == 1) {
                s2++;
            }

            b >>= 1;
        }

        // Apply the required conditional calculation
        return s1 >= s2 ? s1 * s2 : s2 / s1;
    }
}
