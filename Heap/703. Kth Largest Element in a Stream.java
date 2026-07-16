/*
 * LeetCode: 703. Kth Largest Element in a Stream
 * https://leetcode.com/problems/kth-largest-element-in-a-stream/
 *
 * ------------------------------------------------------------
 * Approach: Min Heap (Priority Queue)
 * ------------------------------------------------------------
 * Intuition:
 * - Maintain a min heap containing at most k elements.
 * - The heap always stores the k largest elements seen so far.
 * - If the heap size exceeds k, remove the smallest element.
 * - The root of the min heap always represents the kth largest element.
 * - For every add() operation, insert the new value, remove the
 *   smallest element if necessary, and return the heap's root.
 *
 * Time Complexity:
 * Constructor: O(N log K)
 * add(val): O(log K)
 *
 * Space Complexity: O(K)
 *
 * where:
 * N = Number of elements in the initial array.
 * K = Required kth largest element.
 *
 * Alternative Approach:
 * 1. Binary Search Tree (BST)
 *    - Maintain an augmented BST storing subtree sizes.
 *    - Insert new elements and retrieve the kth largest by traversing
 *      the tree based on subtree sizes.
 *    - Average Time Complexity:
 *      Constructor: O(N log N)
 *      add(val): O(log N)
 *    - Worst-case Time Complexity: O(N)
 *    - Space Complexity: O(N)
 */
class KthLargest {

    int k;
    PriorityQueue<Integer> pq;

    public KthLargest(int k, int[] nums) {
        this.k = k;
        this.pq = new PriorityQueue<>();
        for(int n:nums){
            pq.offer(n);
            if(pq.size()>k) pq.poll();
        }
    }
    
    public int add(int val) {
        pq.offer(val);
        if(pq.size()>k) pq.poll();
        return pq.peek();
    }
}
