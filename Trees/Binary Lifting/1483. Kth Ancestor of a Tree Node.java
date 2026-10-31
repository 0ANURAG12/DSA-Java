/*
 * LeetCode: 1483. Kth Ancestor of a Tree Node
 * https://leetcode.com/problems/kth-ancestor-of-a-tree-node/
 *
 * Topic: Trees / Binary Lifting / Dynamic Programming
 *
 *
 * ============================================================
 * APPROACH 1: Binary Lifting (Ancestor Table)
 * ============================================================
 *
 * Intuition:
 * - We are given a rooted tree where parent[i] represents
 *   the parent of node i.
 *
 * - We need to find the kth ancestor of a given node.
 *
 * - A straightforward approach would move from the current
 *   node to its parent k times, taking O(k) time per query.
 *
 * - To optimize this, we use Binary Lifting.
 *
 * - Instead of storing only the immediate parent, we
 *   precompute ancestors at powers of two.
 *
 * - Define:
 *
 *       ancestorTable[node][j]
 *
 *   as the 2^j-th ancestor of the given node.
 *
 * - Base case:
 *
 *       ancestorTable[node][0] = parent[node]
 *
 *   This represents the immediate parent (2^0 = 1).
 *
 * - Transition:
 *
 *       ancestorTable[node][j] =
 *       ancestorTable[ancestorTable[node][j-1]][j-1]
 *
 *   Explanation:
 *   To find the 2^j-th ancestor:
 *   1. Move 2^(j-1) ancestors upward.
 *   2. From that ancestor, move another 2^(j-1) ancestors.
 *
 *   Therefore:
 *
 *       2^(j-1) + 2^(j-1) = 2^j
 *
 * - Once the table is constructed, answer queries using
 *   the binary representation of k.
 *
 * - Every set bit in k represents a jump of a corresponding
 *   power of two.
 *
 * - For example:
 *
 *       k = 13
 *       Binary = 1101
 *
 *       13 = 8 + 4 + 1
 *
 *   We make jumps of 8, 4, and 1 ancestors.
 *
 * - If at any point the node becomes -1, the requested
 *   ancestor does not exist.
 *
 *
 * Example:
 *
 *      n = 7
 *
 *      parent = [-1, 0, 0, 1, 1, 2, 2]
 *
 *      Tree:
 *
 *               0
 *             /   \
 *            1     2
 *           / \   / \
 *          3   4 5   6
 *
 *      Query:
 *      node = 3, k = 2
 *
 *      Immediate parent of 3 = 1
 *      Immediate parent of 1 = 0
 *
 *      2nd ancestor of 3 = 0
 *
 *      Answer = 0
 *
 *
 * Time Complexity:
 * - Preprocessing: O(n log n)
 * - Each Query: O(log n)
 *
 * Space Complexity: O(n log n)
 *
 * Why O(n log n)?
 * - We have n nodes.
 * - For each node, we store approximately log n ancestors.
 *
 * Query:
 * - We process the binary representation of k.
 * - Therefore, each query takes O(log n).
 *
 * Space:
 * - The ancestor table requires O(n log n) space.
 */


class TreeAncestor {

    int[][] ancestorTable;
    int row, col;

    public TreeAncestor(int n, int[] parent) {

        this.row = n;

        // Calculate the number of binary lifting levels
        this.col = 1;
        while ((1L << col) <= n) {
            col++;
        }

        this.ancestorTable = new int[row][col];

        // Initialize all ancestors to -1
        for (int i = 0; i < row; i++) {
            Arrays.fill(ancestorTable[i], -1);
        }

        // Level 0: Immediate parent of every node
        for (int node = 0; node < n; node++) {
            ancestorTable[node][0] = parent[node];
        }

        // Build the binary lifting table
        for (int j = 1; j < col; j++) {

            for (int node = 0; node < n; node++) {

                int mid = ancestorTable[node][j - 1];

                if (mid != -1) {
                    ancestorTable[node][j] =
                        ancestorTable[mid][j - 1];
                }
            }
        }
    }

    public int getKthAncestor(int node, int k) {

        // A node cannot have an ancestor at distance >= n
        if (k >= row) {
            return -1;
        }

        // Process every set bit in k
        for (int j = 0; j < col; j++) {

            if ((k & (1 << j)) != 0) {

                node = ancestorTable[node][j];

                if (node == -1) {
                    return -1;
                }
            }
        }

        return node;
    }
}

/*
 * Your TreeAncestor object will be instantiated and called as such:
 *
 * TreeAncestor obj = new TreeAncestor(n, parent);
 * int param_1 = obj.getKthAncestor(node, k);
 */
