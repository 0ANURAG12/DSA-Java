/*
 * LeetCode: 2940. Find Building Where Alice and Bob Can Meet
 * https://leetcode.com/problems/find-building-where-alice-and-bob-can-meet/
 *
 * Topic: Segment Tree / Binary Search / Range Maximum Query
 *
 *
 * ============================================================
 * APPROACH 1: Segment Tree + DFS Search
 * ============================================================
 *
 * Intuition:
 * - We are given an array `heights`, where heights[i] represents
 *   the height of the building at index i.
 *
 * - For each query [a, b], Alice starts at building a and Bob
 *   starts at building b.
 *
 * - Without loss of generality, make a <= b by swapping the
 *   indices if necessary.
 *
 * - There are three important cases:
 *
 *   1. If a == b:
 *      Both are already at the same building, so the answer is b.
 *
 *   2. If heights[a] < heights[b]:
 *      Alice can move directly to building b because it is
 *      to the right and taller than Alice's current building.
 *      Therefore, the answer is b.
 *
 *   3. Otherwise:
 *      We need to find the leftmost building to the right of b
 *      whose height is strictly greater than heights[a].
 *
 * - To answer these queries efficiently, we build a segment tree
 *   that stores the index of the maximum-height building in
 *   each segment.
 *
 * - During the search:
 *   1. Skip segments entirely before the search start.
 *   2. Skip any segment whose maximum height is <= target,
 *      because it cannot contain a valid answer.
 *   3. Search the left child first to find the leftmost answer.
 *   4. If the left child has no answer, search the right child.
 *
 * - If no suitable building exists, return -1.
 *
 *
 * Example:
 *
 *      heights = [6, 4, 8, 5, 2, 7]
 *      query = [1, 3]
 *
 *      a = 1, b = 3
 *
 *      heights[a] = 4
 *      heights[b] = 5
 *
 *      Since heights[a] < heights[b], Bob's building is
 *      already reachable by Alice.
 *
 *      Answer = 3
 *
 *
 *      Another query: [0, 3]
 *
 *      heights[0] = 6
 *      heights[3] = 5
 *
 *      We search to the right of index 3 for the first building
 *      with height > 6.
 *
 *      Index 4: height = 2 -> Not suitable
 *      Index 5: height = 7 -> Suitable
 *
 *      Answer = 5
 *
 *
 * Time Complexity:
 * - Building the segment tree: O(n)
 * - Each query: O(log n) on typical searches, but the recursive
 *   search can visit multiple nodes in the worst case.
 * - Worst-case query complexity: O(n)
 * - Total worst-case complexity: O(n + q * n)
 *
 * Space Complexity: O(n)
 *
 * Why O(n)?
 * - The segment tree uses 4 * n entries.
 * - Recursive call stack uses O(log n) space for tree traversal.
 */


class Solution {

    int[] segTree;
    int n;
    int[] heights;

    public int[] leftmostBuildingQueries(int[] heights, int[][] queries) {

        this.n = heights.length;
        this.heights = heights;
        this.segTree = new int[4 * n];

        // Build the segment tree
        buildSegTree(0, 0, n - 1);

        int[] ans = new int[queries.length];

        // Process every query
        for (int i = 0; i < queries.length; i++) {

            int a = Math.min(queries[i][0], queries[i][1]);
            int b = Math.max(queries[i][0], queries[i][1]);

            // Case 1: Both start at the same building
            // Case 2: The building at b is taller than at a
            if (a == b || heights[a] < heights[b]) {
                ans[i] = b;
            } else {

                // Find the first building to the right of b
                // whose height is strictly greater than heights[a]
                ans[i] = findFirst(
                    b + 1, heights[a], 0, 0, n - 1
                );
            }
        }

        return ans;
    }

    private void buildSegTree(int i, int l, int r) {

        // Leaf node stores its building index
        if (l == r) {
            segTree[i] = l;
            return;
        }

        int mid = (l + r) / 2;

        buildSegTree(2 * i + 1, l, mid);
        buildSegTree(2 * i + 2, mid + 1, r);

        int left = segTree[2 * i + 1];
        int right = segTree[2 * i + 2];

        // Store the index of the taller building
        segTree[i] = heights[left] >= heights[right]
            ? left
            : right;
    }

    private int findFirst(int start, int target, int i, int l, int r) {

        // Skip segments before the required starting index
        // or segments whose maximum height is not greater
        // than the target
        if (r < start || heights[segTree[i]] <= target) {
            return -1;
        }

        // A valid leaf has been found
        if (l == r) {
            return l;
        }

        int mid = (l + r) / 2;

        // Search the left segment first to get the leftmost index
        int left = findFirst(
            start, target, 2 * i + 1, l, mid
        );

        if (left != -1) {
            return left;
        }

        // If the left segment has no answer, search the right
        return findFirst(
            start, target, 2 * i + 2, mid + 1, r
        );
    }
}
