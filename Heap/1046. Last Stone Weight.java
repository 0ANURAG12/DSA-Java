/*
 * LeetCode: 1046. Last Stone Weight
 * https://leetcode.com/problems/last-stone-weight/
 *
 * ------------------------------------------------------------
 * Approach: Max Heap (Priority Queue)
 * ------------------------------------------------------------
 * Intuition:
 * - Store all stone weights in a max heap.
 * - Repeatedly remove the two heaviest stones.
 * - If their weights are different, insert the remaining weight
 *   back into the heap.
 * - Continue until at most one stone remains.
 * - The remaining stone's weight is the answer, or 0 if no stones
 *   are left.
 *
 * Time Complexity: O(N log N)
 * Space Complexity: O(N)
 *
 * where:
 * N = Number of stones.
 *
 * Explanation:
 * - Building the max heap takes O(N).
 * - Each smash operation performs two removals and at most one insertion,
 *   each costing O(log N).
 * - There are at most N - 1 smash operations.
 *
 * Alternative Approach:
 * 1. Sorting
 *    - Repeatedly sort the array, remove the two largest stones,
 *      and insert their difference.
 *    - Time Complexity: O(N² log N)
 *    - Space Complexity: O(1)
 */
class Solution {
    public int lastStoneWeight(int[] stones) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for(int s: stones) pq.offer(s);
        while(pq.size()>1){
            int x = pq.poll();
            int y = pq.poll();
            if(x!=y) pq.offer(x-y);
        }
        return pq.peek() == null ? 0: pq.poll();
    }
}
