/*
 * LeetCode: 307. Range Sum Query - Mutable
 * https://leetcode.com/problems/range-sum-query-mutable/
 *
 * Topic: Segment Tree / Arrays / Range Sum Query
 *
 *
 * ============================================================
 * APPROACH 1: Segment Tree
 * ============================================================
 *
 * Intuition:
 * - We need to support two operations efficiently:
 *
 *   1. update(index, val):
 *      Update the value at the specified index.
 *
 *   2. sumRange(left, right):
 *      Calculate the sum of elements in the inclusive
 *      range [left, right].
 *
 * - A Segment Tree stores information about different
 *   segments of the array.
 *
 * - Each node stores the sum of its corresponding segment.
 *
 * - The root represents the entire array, while its children
 *   represent the left and right halves.
 *
 * - BUILD:
 *   Recursively divide the array into two halves until
 *   each leaf represents a single element.
 *
 *   Each internal node stores the sum of its two children.
 *
 * - UPDATE:
 *   Traverse the tree to find the leaf corresponding
 *   to the given index.
 *
 *   Update the leaf and recalculate the sums of all
 *   ancestors while returning from recursion.
 *
 * - QUERY:
 *   For a query range [left, right], there are three cases:
 *
 *   1. No overlap:
 *      Return 0 because this segment contributes nothing.
 *
 *   2. Complete overlap:
 *      Return the stored sum of the segment.
 *
 *   3. Partial overlap:
 *      Recursively query both children and add their results.
 *
 * - The segment tree supports updates and range sum queries
 *   in O(log n) time.
 *
 *
 * Example:
 *
 *      nums = [1, 3, 5]
 *
 *      Segment Tree:
 *
 *              9
 *            /   \
 *           4     5
 *          / \
 *         1   3
 *
 *      sumRange(0, 2):
 *      1 + 3 + 5 = 9
 *
 *      update(1, 2):
 *      nums = [1, 2, 5]
 *
 *      Updated Segment Tree:
 *
 *              8
 *            /   \
 *           3     5
 *          / \
 *         1   2
 *
 *      sumRange(0, 2):
 *      1 + 2 + 5 = 8
 *
 *
 * Time Complexity:
 * - Construction: O(n)
 * - Update: O(log n)
 * - Range Sum Query: O(log n)
 *
 * Space Complexity: O(n)
 *
 * Why O(n)?
 * - The segment tree uses an array of size 4 * n.
 * - The tree has O(n) nodes.
 */


class NumArray {

    int[] segTree;
    int[] nums;
    int n;

    public NumArray(int[] nums) {

        this.n = nums.length;
        this.nums = nums;

        // Allocate sufficient space for the segment tree
        segTree = new int[4 * n];

        // Build the tree only when the array is non-empty
        if (n > 0) {
            buildTree(0, 0, n - 1);
        }
    }

    public void update(int index, int val) {

        // Update the original array
        nums[index] = val;

        // Update the corresponding leaf and its ancestors
        updateSegTree(index, val, 0, 0, n - 1);
    }

    public int sumRange(int left, int right) {

        // Query the inclusive range [left, right]
        return query(left, right, 0, 0, n - 1);
    }

    void buildTree(int i, int l, int r) {

        // Leaf node represents a single array element
        if (l == r) {
            segTree[i] = nums[l];
            return;
        }

        int mid = (l + r) / 2;

        // Build the left and right subtrees
        buildTree(2 * i + 1, l, mid);
        buildTree(2 * i + 2, mid + 1, r);

        // Store the sum of both child segments
        segTree[i] = segTree[2 * i + 1]
                   + segTree[2 * i + 2];
    }

    void updateSegTree(int idx, int val, int i, int l, int r) {

        // Update the leaf corresponding to idx
        if (l == r) {
            segTree[i] = val;
            return;
        }

        int mid = (l + r) / 2;

        // Traverse toward the target index
        if (idx <= mid) {
            updateSegTree(idx, val, 2 * i + 1, l, mid);
        } else {
            updateSegTree(idx, val, 2 * i + 2, mid + 1, r);
        }

        // Recalculate the current segment sum
        segTree[i] = segTree[2 * i + 1]
                   + segTree[2 * i + 2];
    }

    int query(int s, int e, int i, int l, int r) {

        // Case 1: No overlap
        if (r < s || l > e) {
            return 0;
        }

        // Case 2: Complete overlap
        if (l >= s && r <= e) {
            return segTree[i];
        }

        // Case 3: Partial overlap
        int mid = (l + r) / 2;

        return query(s, e, 2 * i + 1, l, mid)
             + query(s, e, 2 * i + 2, mid + 1, r);
    }
}

/*
 * Your NumArray object will be instantiated and called as such:
 *
 * NumArray obj = new NumArray(nums);
 * obj.update(index, val);
 * int param_2 = obj.sumRange(left, right);
 */
