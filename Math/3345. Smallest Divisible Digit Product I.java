/*
 * LeetCode 3345. Smallest Divisible Digit Product I
 * https://leetcode.com/problems/smallest-divisible-digit-product-i/
 *
 * Approach:
 * - Brute Force
 * - Starting from n, iterate through each number until 100.
 * - For every number:
 *      1. Compute the product of its digits.
 *      2. Check if the product is divisible by t.
 * - Return the first number satisfying the condition.
 *
 * Time Complexity: O((100 - n) × d)
 *   where d is the number of digits (at most 3 since n <= 100).
 *
 * Space Complexity: O(1)
 */

class Solution {

    public int smallestNumber(int n, int t) {

        for (; n <= 100; n++) {

            int product = 1;
            int current = n;

            while (current > 0) {
                product *= (current % 10);
                current /= 10;
            }

            if (product % t == 0) {
                return n;
            }
        }

        return n;
    }
}
