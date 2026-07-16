/*
 * LeetCode: 973. K Closest Points to Origin
 * https://leetcode.com/problems/k-closest-points-to-origin/
 *
 * ------------------------------------------------------------
 * Approach: Max Heap (Priority Queue)
 * ------------------------------------------------------------
 * Intuition:
 * - Compute the squared Euclidean distance of each point from the origin.
 * - Maintain a max heap of size at most k containing the closest points.
 * - If the heap size exceeds k, remove the farthest point.
 * - After processing all points, the heap contains exactly the k closest
 *   points to the origin.
 *
 * Time Complexity: O(N log K)
 * Space Complexity: O(K)
 *
 * where:
 * N = Number of points.
 * K = Number of closest points to return.
 *
 * Explanation:
 * - Each insertion/removal from the heap takes O(log K).
 * - Every point is processed exactly once.
 *
 * Alternative Approach:
 * 1. QuickSelect
 *    - Partition the points based on their distance from the origin.
 *    - Recursively process only the partition containing the kth closest point.
 *    - Average Time Complexity: O(N)
 *    - Worst-case Time Complexity: O(N²)
 *    - Space Complexity: O(1)
 */
class Solution {
    public int[][] kClosest(int[][] points, int k) {

        PriorityQueue<int[]> pq = new PriorityQueue<>(
            (a, b) -> Integer.compare(
                b[0] * b[0] + b[1] * b[1],
                a[0] * a[0] + a[1] * a[1]
            )
        );

        for (int[] p : points) {
            pq.offer(p);
            if (pq.size() > k) {
                pq.poll();
            }
        }

        return pq.toArray(new int[0][]);
    }
}
