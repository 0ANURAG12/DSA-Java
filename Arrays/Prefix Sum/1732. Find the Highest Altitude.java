/**
# LeetCode 1732. Find the Highest Altitude

**Problem Link:** https://leetcode.com/problems/find-the-highest-altitude/

## Approach 1: Prefix Sum

The biker starts at altitude 0.

For each gain:
- Update the current altitude.
- Track the maximum altitude reached so far.

The highest altitude is simply the maximum prefix sum.

**Time Complexity:** O(N)

**Space Complexity:** O(1)

where:

* N = Length of the gain array

*/
class Solution {
    public int largestAltitude(int[] gain) {

        int altitude = 0;
        int maxAltitude = 0;

        for (int g : gain) {
            altitude += g;
            maxAltitude = Math.max(maxAltitude, altitude);
        }

        return maxAltitude;
    }
}
