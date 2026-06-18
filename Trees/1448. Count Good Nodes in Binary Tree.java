/**
# LeetCode 1448. Count Good Nodes in Binary Tree

**Problem Link:** https://leetcode.com/problems/count-good-nodes-in-binary-tree/

## Approach 1: DFS with Path Maximum

Keep track of the maximum value seen from the root to the current node.

A node is considered good if its value is greater than or equal to every value
encountered on the path from the root.

**Time Complexity:** O(N)

**Space Complexity:** O(H)

where:

* N = Number of nodes in the tree
* H = Height of the tree

*/
class Solution {

    int ans = 0;

    public int goodNodes(TreeNode root) {
        dfs(root, Integer.MIN_VALUE);
        return ans;
    }

    private void dfs(TreeNode root, int maxSoFar) {

        if (root == null) {
            return;
        }

        if (root.val >= maxSoFar) {
            ans++;
        }

        maxSoFar = Math.max(maxSoFar, root.val);

        dfs(root.left, maxSoFar);
        dfs(root.right, maxSoFar);
    }
}

/*

---

## Approach 2: DFS Returning Count

Instead of using a global variable, return the number of good nodes
from each subtree.

**Time Complexity:** O(N)

**Space Complexity:** O(H)

where:

* N = Number of nodes in the tree
* H = Height of the tree

*/

class Solution {

    public int goodNodes(TreeNode root) {
        return dfs(root, Integer.MIN_VALUE);
    }

    private int dfs(TreeNode root, int maxSoFar) {

        if (root == null) {
            return 0;
        }

        int count = 0;

        if (root.val >= maxSoFar) {
            count = 1;
        }

        maxSoFar = Math.max(maxSoFar, root.val);

        return count
                + dfs(root.left, maxSoFar)
                + dfs(root.right, maxSoFar);
    }
}
