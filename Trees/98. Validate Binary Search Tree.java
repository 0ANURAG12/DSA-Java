/**
# LeetCode 98. Validate Binary Search Tree

**Problem Link:** https://leetcode.com/problems/validate-binary-search-tree/

## Approach 1: DFS with Valid Range (Recommended)

For every node, maintain the valid range of values it can take.

- Left subtree values must be strictly less than the current node.
- Right subtree values must be strictly greater than the current node.
- Recursively update the allowed range while traversing the tree.

**Time Complexity:** O(N)

**Space Complexity:** O(H)

where:

* N = Number of nodes in the tree
* H = Height of the tree

*/
class Solution {

    public boolean isValidBST(TreeNode root) {
        return dfs(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean dfs(TreeNode root, long min, long max) {

        if (root == null) {
            return true;
        }

        if (root.val <= min || root.val >= max) {
            return false;
        }

        return dfs(root.left, min, root.val)
                && dfs(root.right, root.val, max);
    }
}

/*

---

## Approach 2: Inorder Traversal

The inorder traversal of a valid BST must produce a strictly increasing sequence.

Traverse the tree inorder and ensure every node value is greater than the
previously visited value.

**Time Complexity:** O(N)

**Space Complexity:** O(H)

where:

* N = Number of nodes in the tree
* H = Height of the tree

*/

class Solution {

    private long prev = Long.MIN_VALUE;

    public boolean isValidBST(TreeNode root) {
        return inorder(root);
    }

    private boolean inorder(TreeNode root) {

        if (root == null) {
            return true;
        }

        if (!inorder(root.left)) {
            return false;
        }

        if (root.val <= prev) {
            return false;
        }

        prev = root.val;

        return inorder(root.right);
    }
}

/*

---

## Approach 3: Iterative Inorder Traversal

Use a stack to perform inorder traversal iteratively.

A valid BST must generate a strictly increasing inorder sequence.

**Time Complexity:** O(N)

**Space Complexity:** O(H)

where:

* N = Number of nodes in the tree
* H = Height of the tree

*/

import java.util.Stack;

class Solution {

    public boolean isValidBST(TreeNode root) {

        Stack<TreeNode> stack = new Stack<>();
        long prev = Long.MIN_VALUE;

        while (root != null || !stack.isEmpty()) {

            while (root != null) {
                stack.push(root);
                root = root.left;
            }

            root = stack.pop();

            if (root.val <= prev) {
                return false;
            }

            prev = root.val;

            root = root.right;
        }

        return true;
    }
}
