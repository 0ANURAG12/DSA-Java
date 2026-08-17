/*
 * LeetCode 2029. Stone Game IX
 * https://leetcode.com/problems/stone-game-ix/
 *
 * Approach:
 * - Game Theory + Counting
 * - Only the remainder of each stone modulo 3 matters.
 * - Count stones with remainders:
 *      c0 -> remainder 0
 *      c1 -> remainder 1
 *      c2 -> remainder 2
 *
 * - If c0 is even:
 *      Alice can win if there is at least one stone with
 *      remainder 1 and at least one stone with remainder 2.
 *
 * - If c0 is odd:
 *      Alice can win only when the difference between the
 *      number of remainder-1 and remainder-2 stones is at least 3.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */

class Solution {

    public boolean stoneGameIX(int[] stones) {

        int c0 = 0;
        int c1 = 0;
        int c2 = 0;

        // Count stones based on their remainder modulo 3
        for (int stone : stones) {

            if (stone % 3 == 0) {
                c0++;
            } else if (stone % 3 == 1) {
                c1++;
            } else {
                c2++;
            }
        }

        // Even number of remainder-0 stones
        if (c0 % 2 == 0) {
            return c1 >= 1 && c2 >= 1;
        }

        // Odd number of remainder-0 stones
        return Math.abs(c1 - c2) >= 3;
    }
}
