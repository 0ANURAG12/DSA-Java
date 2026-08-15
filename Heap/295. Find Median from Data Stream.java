/*
 * LeetCode 295. Find Median from Data Stream
 * https://leetcode.com/problems/find-median-from-data-stream/
 *
 * Approach:
 * - Use two heaps to maintain the numbers around the median.
 * - maxHeap stores the smaller half of the numbers.
 * - minHeap stores the larger half of the numbers.
 * - maxHeap is allowed to contain at most one more element than minHeap.
 *
 * addNum():
 * - If the number belongs to the larger half, add it to minHeap.
 * - Otherwise, add it to maxHeap.
 * - Rebalance the heaps whenever their sizes become invalid.
 *
 * findMedian():
 * - If both heaps have the same size, the median is the average
 *   of the two heap tops.
 * - Otherwise, maxHeap contains one extra element, so its top
 *   is the median.
 *
 * Time Complexity:
 * - addNum(): O(log n)
 * - findMedian(): O(1)
 *
 * Space Complexity: O(n)
 */

class MedianFinder {

    // Stores the larger half
    PriorityQueue<Integer> minHeap;

    // Stores the smaller half
    PriorityQueue<Integer> maxHeap;

    public MedianFinder() {
        minHeap = new PriorityQueue<>();
        maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
    }

    public void addNum(int num) {

        // Add to the appropriate half
        if (!maxHeap.isEmpty() && num > maxHeap.peek()) {
            minHeap.add(num);
        } else {
            maxHeap.add(num);
        }

        // Rebalance the heaps
        if (minHeap.size() > maxHeap.size()) {
            maxHeap.add(minHeap.poll());
        } else if (maxHeap.size() > minHeap.size() + 1) {
            minHeap.add(maxHeap.poll());
        }
    }

    public double findMedian() {

        // Even number of elements
        if ((maxHeap.size() + minHeap.size()) % 2 == 0) {
            return (maxHeap.peek() + minHeap.peek()) / 2.0;
        }

        // Odd number of elements
        return maxHeap.peek();
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 *
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */
