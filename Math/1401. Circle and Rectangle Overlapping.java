/*
 * LeetCode: 1401. Circle and Rectangle Overlapping
 * https://leetcode.com/problems/circle-and-rectangle-overlapping/
 *
 * Topic: Geometry / Math
 *
 *
 * ============================================================
 * APPROACH 1: Closest Point on Rectangle
 * ============================================================
 *
 * Intuition:
 * - A circle and rectangle overlap if the distance between the
 *   circle's center and the closest point on the rectangle is
 *   less than or equal to the circle's radius.
 *
 * - For the circle center (xCenter, yCenter), find the closest
 *   point on the rectangle.
 *
 * - The rectangle is bounded by:
 *
 *      x1 <= x <= x2
 *      y1 <= y <= y2
 *
 * - To find the closest x-coordinate:
 *
 *      xi = max(x1, min(x2, xCenter))
 *
 * - Similarly, for the y-coordinate:
 *
 *      yi = max(y1, min(y2, yCenter))
 *
 * - This effectively "clamps" the circle center to the rectangle.
 *
 * - Once we have the closest point (xi, yi), calculate the
 *   squared distance:
 *
 *      distance² = (xi - xCenter)² + (yi - yCenter)²
 *
 * - The circle and rectangle overlap if:
 *
 *      distance² <= radius²
 *
 * - We compare squared distances instead of calculating the
 *   actual Euclidean distance, avoiding sqrt().
 *
 *
 * Time Complexity: O(1)
 * Space Complexity: O(1)
 *
 * Why O(1)?
 * - Only a constant number of arithmetic operations are performed.
 */


class Solution {

    public boolean checkOverlap(
            int radius,
            int xCenter,
            int yCenter,
            int x1,
            int y1,
            int x2,
            int y2) {

        // Find the closest point on the rectangle to the
        // center of the circle.
        int xi = Math.max(x1, Math.min(x2, xCenter));
        int yi = Math.max(y1, Math.min(y2, yCenter));

        // Calculate squared distance from circle center
        // to the closest point on the rectangle.
        long dx = xi - xCenter;
        long dy = yi - yCenter;

        long distanceSquared = dx * dx + dy * dy;

        long radiusSquared = (long) radius * radius;

        // Overlap occurs when the closest point lies
        // inside or on the circle.
        return distanceSquared <= radiusSquared;
    }
}
