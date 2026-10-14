/*
 * LeetCode: 3723. Maximize Sum of Squares of Digits
 * https://leetcode.com/problems/maximize-sum-of-squares-of-digits/
 *
 * Topic: Greedy / Math / Strings
 *
 *
 * ============================================================
 * APPROACH 1: Greedy
 * ============================================================
 *
 * Intuition:
 * - We need to construct a number with exactly `num` digits
 *   whose digits add up to `sum`.
 *
 * - The score is the sum of the squares of its digits.
 *
 * - To maximize the score, we should concentrate the sum
 *   into as few digits as possible.
 *
 * - Since 9 is the largest possible digit, we greedily place
 *   as many 9s as possible from left to right.
 *
 * - If the remaining sum is between 1 and 8, place it as
 *   the next digit.
 *
 * - Fill all remaining positions with 0 because they do not
 *   change the digit sum or the score.
 *
 * - Placing larger digits first also ensures the largest
 *   number when multiple answers have the same score.
 *
 * - If sum > num * 9, no valid number can be constructed,
 *   so return an empty string.
 *
 *
 * Example:
 *
 *      num = 2, sum = 17
 *
 *      First digit: 9
 *      Remaining sum: 17 - 9 = 8
 *
 *      Second digit: 8
 *
 *      Answer: "98"
 *
 *      Score = 9^2 + 8^2 = 145
 *
 *
 * Time Complexity: O(num)
 * Space Complexity: O(num)
 *
 * Why O(num)?
 * - At most num digits are appended to the result.
 *
 * Space:
 * - The StringBuilder stores the resulting num-digit string.
 */


class Solution {

    public String maxSumOfSquares(int num, int sum) {

        // Maximum possible digit sum for num digits is 9 * num
        if (sum > num * 9) {
            return "";
        }

        StringBuilder str = new StringBuilder();

        // Greedily place the largest possible digits first
        while (sum >= 9 && num > 0) {
            str.append('9');
            sum -= 9;
            num--;
        }

        // Place the remaining sum, if any
        if (sum > 0 && num > 0) {
            str.append(sum);
            num--;
        }

        // Fill remaining positions with zeros
        while (num > 0) {
            str.append('0');
            num--;
        }

        return str.toString();
    }
}
