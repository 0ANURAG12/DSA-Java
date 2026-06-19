/**
# LeetCode 124. Binary Tree Maximum Path Sum

**Problem Link:** https://leetcode.com/problems/binary-tree-maximum-path-sum/

## Approach 1: DFS + Path Contribution (Optimal)

For every node:

- Calculate the maximum contribution from the left subtree.
- Calculate the maximum contribution from the right subtree.
- Ignore negative contributions.
- Update the global answer using:
  leftContribution + rightContribution + currentNode.
- Return the maximum path that can be extended to the parent.

**Time Complexity:** O(N)

**Space Complexity:** O(H)

where:

* N = Number of nodes in the tree
* H = Height of the tree

*/
class Solution {

    int maxSum;

    public int maxPathSum(TreeNode root) {

        maxSum = Integer.MIN_VALUE;

        dfs(root);

        return maxSum;
    }

    private int dfs(TreeNode root) {

        if (root == null) {
            return 0;
        }

        int left = Math.max(0, dfs(root.left));
        int right = Math.max(0, dfs(root.right));

        maxSum = Math.max(maxSum,
                left + right + root.val);

        return root.val + Math.max(left, right);
    }
}
