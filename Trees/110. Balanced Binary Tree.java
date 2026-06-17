/**
# LeetCode 110. Balanced Binary Tree

**Problem Link:** https://leetcode.com/problems/balanced-binary-tree/

## Approach 1: DFS with Global Flag

**Time Complexity:** O(N)

**Space Complexity:** O(H)

where:

* N = Number of nodes in the tree
* H = Height of the tree

*/
class Solution {

    boolean flag = true;

    public boolean isBalanced(TreeNode root) {
        height(root);
        return flag;
    }

    private int height(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int left = height(root.left);
        int right = height(root.right);

        if (Math.abs(left - right) > 1) {
            flag = false;
        }

        return 1 + Math.max(left, right);
    }
}

/*

---

## Approach 2: DFS with Early Termination (Recommended)

**Time Complexity:** O(N)

**Space Complexity:** O(H)

where:

* N = Number of nodes in the tree
* H = Height of the tree

*/
class Solution {

    public boolean isBalanced(TreeNode root) {
        return height(root) != -1;
    }

    private int height(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int left = height(root.left);

        if (left == -1) {
            return -1;
        }

        int right = height(root.right);

        if (right == -1) {
            return -1;
        }

        if (Math.abs(left - right) > 1) {
            return -1;
        }

        return 1 + Math.max(left, right);
    }
}
