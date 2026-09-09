/*
 * LeetCode: 3871. Count Commas in an Integer
 * https://leetcode.com/problems/count-commas-in-an-integer/
 *
 * Topic: Math / Number Theory
 *
 *
 * ============================================================
 * APPROACH 1: Count Numbers Having Commas
 * ============================================================
 *
 * Intuition:
 * - A comma is inserted after every group of 3 digits.
 *
 * - Numbers with at least 4 digits contain a comma.
 *
 * - For a comma position:
 *
 *      1,000
 *      1,000,000
 *      1,000,000,000
 *      ...
 *
 * - For a fixed comma position x, every number from x to n
 *   contributes one comma at that position.
 *
 * - Therefore, the number of commas contributed by this
 *   position is:
 *
 *      n - x + 1
 *
 * - We start with x = 1000 and repeatedly multiply x by 1000
 *   to move to the next comma position.
 *
 *      1000
 *      1000000
 *      1000000000
 *      ...
 *
 * - Add the contribution of every valid comma position.
 *
 *
 * Example:
 *
 *      n = 2500
 *
 *      For x = 1000:
 *
 *          2500 - 1000 + 1 = 1501
 *
 *      Therefore, there are 1501 commas in total.
 *
 *
 * Time Complexity: O(log₁₀(n))
 * Space Complexity: O(1)
 *
 * Why O(log₁₀(n))?
 * - x is multiplied by 1000 in every iteration.
 * - Therefore, the number of iterations grows logarithmically
 *   with n.
 */


class Solution {

    public long countCommas(long n) {

        long ans = 0;

        // Check every possible comma position
        for (long x = 1000; x <= n; x *= 1000) {

            // Every number from x to n has a comma at this position
            ans += n - x + 1;
        }

        return ans;
    }
}
