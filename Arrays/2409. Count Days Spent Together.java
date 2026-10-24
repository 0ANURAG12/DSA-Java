/*
 * LeetCode: 2409. Count Days Spent Together
 * https://leetcode.com/problems/count-days-spent-together/
 *
 * Topic: Arrays / Prefix Sum / Strings
 *
 *
 * ============================================================
 * APPROACH 1: Prefix Sum + Interval Intersection
 * ============================================================
 *
 * Intuition:
 * - We are given the arrival and departure dates of Alice
 *   and Bob.
 *
 * - We need to count the number of days they spend together.
 *
 * - Since all dates belong to the same non-leap year, we can
 *   convert each MM-DD date into its day number within the year.
 *
 * - Store the number of days in each month in the `month` array.
 *
 * - Use a prefix sum array to calculate the total number of
 *   days before each month.
 *
 * - Convert each date into a day number:
 *
 *       dayNumber = daysBeforeMonth + dayOfMonth
 *
 * - The first day they can meet is the later of their arrival
 *   dates:
 *
 *       sMax = max(arriveAlice, arriveBob)
 *
 * - The last day they can meet is the earlier of their
 *   departure dates:
 *
 *       eMin = min(leaveAlice, leaveBob)
 *
 * - Since arrival and departure dates are inclusive, the
 *   number of days spent together is:
 *
 *       max(0, eMin - sMax + 1)
 *
 * - If eMin < sMax, their date ranges do not overlap,
 *   so the answer is 0.
 *
 *
 * Example:
 *
 *      arriveAlice = "08-15"
 *      leaveAlice  = "08-18"
 *      arriveBob   = "08-16"
 *      leaveBob    = "08-19"
 *
 *      Alice's stay: August 15 to August 18
 *      Bob's stay:   August 16 to August 19
 *
 *      Overlapping dates:
 *      August 16, 17, 18
 *
 *      sMax = August 16
 *      eMin = August 18
 *
 *      Answer = 18 - 16 + 1 = 3 days
 *
 *
 * Time Complexity: O(1)
 * Space Complexity: O(1)
 *
 * Why O(1)?
 * - The month array and prefix sum array have fixed sizes.
 * - Only four dates are converted, regardless of input size.
 *
 * Space:
 * - Only fixed-size arrays and a constant number of variables
 *   are used.
 */


class Solution {

    public int countDaysTogether(
            String arriveAlice,
            String leaveAlice,
            String arriveBob,
            String leaveBob) {

        // Number of days in each month of a non-leap year
        int[] month = {
            31, 28, 31, 30, 31, 30,
            31, 31, 30, 31, 30, 31
        };

        // Total number of days before each month
        int[] prefSum = new int[12];
        prefSum[0] = month[0];

        for (int i = 1; i < 12; i++) {
            prefSum[i] = prefSum[i - 1] + month[i - 1];
        }

        // Convert Alice's arrival date to a day number
        int aA = prefSum[
            Integer.parseInt(arriveAlice.substring(0, 2)) - 1
        ] + Integer.parseInt(arriveAlice.substring(3, 5));

        // Convert Alice's departure date to a day number
        int lA = prefSum[
            Integer.parseInt(leaveAlice.substring(0, 2)) - 1
        ] + Integer.parseInt(leaveAlice.substring(3, 5));

        // Convert Bob's arrival date to a day number
        int aB = prefSum[
            Integer.parseInt(arriveBob.substring(0, 2)) - 1
        ] + Integer.parseInt(arriveBob.substring(3, 5));

        // Convert Bob's departure date to a day number
        int lB = prefSum[
            Integer.parseInt(leaveBob.substring(0, 2)) - 1
        ] + Integer.parseInt(leaveBob.substring(3, 5));

        // Latest arrival date
        int sMax = Math.max(aA, aB);

        // Earliest departure date
        int eMin = Math.min(lA, lB);

        // Count overlapping days, including both endpoints
        return Math.max(0, eMin - sMax + 1);
    }
}
