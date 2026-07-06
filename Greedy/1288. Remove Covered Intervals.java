/*
 * LeetCode: 1288. Remove Covered Intervals
 * https://leetcode.com/problems/remove-covered-intervals/
 *
 * ------------------------------------------------------------
 * Approach: Sorting + Greedy
 * ------------------------------------------------------------
 * Intuition:
 * - Sort the intervals based on their starting points.
 * - Traverse the sorted intervals while maintaining the current merged range.
 * - If the current interval is covered by the maintained interval or extends it,
 *   update the current range.
 * - Otherwise, it represents a new independent interval that is not covered,
 *   so increment the answer and update the current range.
 * - The final count represents the number of remaining intervals after removing
 *   all covered intervals.
 *
 * Time Complexity: O(N log N)
 * Space Complexity: O(1)
 *
 * where:
 * N = Number of intervals
 *
 * Alternative Approach:
 * 1. Sorting + Maximum End Tracking (Optimal)
 *    - Sort intervals by start in ascending order and by end in descending
 *      order when the starts are equal.
 *    - Traverse once while keeping track of the maximum ending point seen so far.
 *    - If the current interval's end is less than or equal to the maximum end,
 *      it is covered; otherwise, count it and update the maximum end.
 *
 * Time Complexity: O(N log N)
 * Space Complexity: O(1)
 */

class Solution {
    public int removeCoveredIntervals(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);

        int n = intervals.length;

        int a = intervals[0][0];
        int b = intervals[0][1];

        int ans = 1;

        for (int i = 1; i < n; i++) {
            int c = intervals[i][0];
            int d = intervals[i][1];

            if ((c <= a && b <= d) || (c >= a && b >= d)) {
                a = Math.min(c, a);
                b = Math.max(d, b);
            } else {
                ans++;
                a = c;
                b = d;
            }
        }

        return ans;
    }
}
