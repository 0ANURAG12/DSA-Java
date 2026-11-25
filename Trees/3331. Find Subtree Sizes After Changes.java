/**
 * Problem: 3331. Find Subtree Sizes After Changes
 * Difficulty: Medium
 * LeetCode Link: https://leetcode.com/problems/find-subtree-sizes-after-changes/
 *
 * Approach:
 * 1. Build the initial tree adjacency list from the `parent` array.
 * 2. Track the most recent (deepest) ancestor seen for each lowercase character ('a' - 'z')
 *    using an array `last` of size 26 (initialized to -1).
 * 3. Perform a single post-order DFS starting from root `0`:
 *    - Save `nearestAncestor = last[c]` for the current node's character `c`, then set
 *      `last[c] = currNode` before recursing into all children.
 *    - After visiting all children (post-order), restore `last[c] = nearestAncestor` so
 *      sibling subtrees are unaffected.
 *    - Determine `newParent` for `currNode` (if `currNode != 0`): if `nearestAncestor != -1`,
 *      re-attach `currNode` directly under `nearestAncestor`; otherwise keep `parent[currNode]`.
 *    - Add `resultSize[currNode]` (which already includes its final subtree nodes) into
 *      `resultSize[newParent]`.
 *
 * Time Complexity: O(N) — each node and edge is visited once during DFS.
 * Space Complexity: O(N) — for the adjacency list, result array, and recursion stack.
 */

import java.util.*;

class Solution {
    private Map<Integer, List<Integer>> adj = new HashMap<>();
    private int[] resultSize;
    private int[] last;

    private void dfs(int currNode, String s, int[] parent) {
        int c = s.charAt(currNode) - 'a';

        int nearestAncestor = last[c];
        last[c] = currNode;

        for (int child : adj.getOrDefault(currNode, Collections.emptyList())) {
            dfs(child, s, parent);
        }

        // Backtrack: restore the previous ancestor for character c
        last[c] = nearestAncestor;

        if (currNode != 0) {
            int newParent = parent[currNode];

            if (nearestAncestor != -1) {
                newParent = nearestAncestor;
            }

            parent[currNode] = newParent;
            resultSize[newParent] += resultSize[currNode];
        }
    }

    public int[] findSubtreeSizes(int[] parent, String s) {
        int n = parent.length;

        for (int i = 1; i < n; i++) {
            int u = parent[i];
            int v = i;
            adj.computeIfAbsent(u, k -> new ArrayList<>()).add(v);
        }

        resultSize = new int[n];
        Arrays.fill(resultSize, 1);

        last = new int[26];
        Arrays.fill(last, -1);

        dfs(0, s, parent);

        return resultSize;
    }
}
