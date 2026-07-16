/*
 * LeetCode: 215. Kth Largest Element in an Array
 * https://leetcode.com/problems/kth-largest-element-in-an-array/
 *
 * ------------------------------------------------------------
 * Approach: Min Heap (Priority Queue)
 * ------------------------------------------------------------
 * Intuition:
 * - Maintain a min heap of size k.
 * - Insert every element into the heap.
 * - If the heap size exceeds k, remove the smallest element.
 * - After processing all elements, the root of the heap represents
 *   the kth largest element.
 *
 * Time Complexity: O(N log K)
 * Space Complexity: O(K)
 *
 * where:
 * N = Number of elements in the array.
 * K = Required kth largest element.
 *
 * Explanation:
 * - Each insertion/removal from the heap takes O(log K).
 * - Every element is processed exactly once.
 *
 * Alternative Approach:
 * 1. QuickSelect
 *    - Partition the array around a pivot until the kth largest
 *      element reaches its correct position.
 *    - Average Time Complexity: O(N)
 *    - Worst-case Time Complexity: O(N²)
 *    - Space Complexity: O(1)
 */
class Solution {
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int n: nums) pq.offer(n);
        while(pq.size()>k) pq.poll();
        return pq.peek();
    }
}
