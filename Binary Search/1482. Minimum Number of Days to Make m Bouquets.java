/**
# LeetCode 1482. Minimum Number of Days to Make m Bouquets

**Problem Link:** https://leetcode.com/problems/minimum-number-of-days-to-make-m-bouquets/

## Approach 1: Binary Search on Answer

The answer lies between:

* Minimum bloom day
* Maximum bloom day

For a given day, greedily count how many bouquets can be formed.

If at least `m` bouquets can be made, try a smaller day.
Otherwise, search for a larger day.

**Time Complexity:** O(N × log(MaxDay))

**Space Complexity:** O(1)

where:

* N = Length of bloomDay array
* MaxDay = Maximum value present in bloomDay

*/
class Solution {

    public int minDays(int[] bloomDay, int m, int k) {

        if ((long) m * k > bloomDay.length) {
            return -1;
        }

        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for (int day : bloomDay) {
            min = Math.min(min, day);
            max = Math.max(max, day);
        }

        int ans = max;

        while (min <= max) {

            int day = min + (max - min) / 2;

            if (canMakeBouquets(bloomDay, m, k, day)) {
                ans = day;
                max = day - 1;
            } else {
                min = day + 1;
            }
        }

        return ans;
    }

    private boolean canMakeBouquets(int[] bloomDay, int m, int k, int day) {

        int flowers = 0;
        int bouquets = 0;

        for (int bloom : bloomDay) {

            if (bloom <= day) {
                flowers++;
            } else {
                bouquets += flowers / k;
                flowers = 0;
            }
        }

        bouquets += flowers / k;

        return bouquets >= m;
    }
}
