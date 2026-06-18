/**
# LeetCode 1344. Angle Between Hands of a Clock

**Problem Link:** https://leetcode.com/problems/angle-between-hands-of-a-clock/

## Approach 1: Mathematical Calculation

Calculate the positions of the hour and minute hands separately.

- Hour hand moves 30° per hour and 0.5° per minute.
- Minute hand moves 6° per minute.
- Find the absolute difference between the two angles.
- Return the smaller angle between them.

**Time Complexity:** O(1)

**Space Complexity:** O(1)

*/
class Solution {
    public double angleClock(int hour, int minutes) {

        if (hour == 12) {
            hour = 0;
        }

        double hourDegree = hour * 30 + (minutes * 0.5);
        double minuteDegree = minutes * 6;

        double degree = Math.abs(hourDegree - minuteDegree);

        return Math.min(degree, 360 - degree);
    }
}

/*

---

## Approach 2: Mathematical Formula (Compact)

Directly compute both angles and return the minimum angle.

**Time Complexity:** O(1)

**Space Complexity:** O(1)

*/

class Solution {
    public double angleClock(int hour, int minutes) {

        double hourAngle = (hour % 12) * 30 + minutes * 0.5;
        double minuteAngle = minutes * 6;

        double diff = Math.abs(hourAngle - minuteAngle);

        return Math.min(diff, 360 - diff);
    }
}
