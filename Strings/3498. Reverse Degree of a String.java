/*
 * LeetCode: 3498. Reverse Degree of a String
 * https://leetcode.com/problems/reverse-degree-of-a-string/
 *
 * Topic: Strings / Math
 *
 *
 * ============================================================
 * APPROACH 1: Direct Calculation
 * ============================================================
 *
 * Intuition:
 * - Each lowercase English letter has a normal alphabetical
 *   position:
 *
 *      a = 1, b = 2, ..., z = 26
 *
 * - The reverse alphabetical position is:
 *
 *      a = 26, b = 25, ..., z = 1
 *
 * - For a character c, its reverse position is:
 *
 *      26 - (c - 'a')
 *
 * - The reverse degree of the string is calculated by
 *   multiplying the reverse position of each character by
 *   its 1-based index.
 *
 *      reverseDegree =
 *          reverseValue(s[0]) * 1
 *        + reverseValue(s[1]) * 2
 *        + ...
 *
 * - Therefore:
 *
 *      reverseValue(c) = 26 - (c - 'a')
 *
 * - Traverse the string once and calculate the sum.
 *
 *
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 *
 * Where:
 * - n = length of the string
 *
 * Why O(n)?
 * - Every character is processed exactly once.
 */


class Solution {

    public int reverseDegree(String s) {

        int sum = 0;

        // 1-based position of the character
        int i = 1;

        for (char c : s.toCharArray()) {

            // Reverse alphabetical position:
            // a -> 26, b -> 25, ..., z -> 1
            int reverseValue = 26 - (c - 'a');

            sum += reverseValue * i;

            i++;
        }

        return sum;
    }
}
