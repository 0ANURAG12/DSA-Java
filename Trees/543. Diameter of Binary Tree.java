/*
# LeetCode 543. Diameter of Binary Tree

**Problem Link:** https://leetcode.com/problems/diameter-of-binary-tree/

## Approach 1: DFS (Optimal)

**Time Complexity:** O(N)

**Space Complexity:** O(H)

where:

* N = Number of nodes in the tree
* H = Height of the tree


 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {

    int diameter = 0;

    public int diameterOfBinaryTree(TreeNode root) {
        height(root);
        return diameter;
    }

    private int height(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int left = height(root.left);
        int right = height(root.right);

        diameter = Math.max(diameter, left + right);

        return 1 + Math.max(left, right);
    }
}

/*

## Approach 2: Brute Force DFS

For every node:

1. Calculate left subtree height.
2. Calculate right subtree height.
3. Diameter through current node = leftHeight + rightHeight.
4. Recursively find diameter in left and right subtrees.

**Time Complexity:** O(N²)

**Space Complexity:** O(H)

where:

* N = Number of nodes in the tree
* H = Height of the tree

*/

class Solution {

    public int diameterOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int leftHeight = height(root.left);
        int rightHeight = height(root.right);

        int currentDiameter = leftHeight + rightHeight;

        int leftDiameter = diameterOfBinaryTree(root.left);
        int rightDiameter = diameterOfBinaryTree(root.right);

        return Math.max(currentDiameter,
                Math.max(leftDiameter, rightDiameter));
    }

    private int height(TreeNode root) {
        if (root == null) {
            return 0;
        }

        return 1 + Math.max(
                height(root.left),
                height(root.right)
        );
    }
}
/*


## Approach 3: Iterative Postorder Traversal (Stack)

Uses a stack to simulate postorder traversal and computes heights iteratively.

**Time Complexity:** O(N)

**Space Complexity:** O(N)

where:

* N = Number of nodes in the tree
*/

import java.util.*;

class Solution {

    public int diameterOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }

        Map<TreeNode, Integer> height = new HashMap<>();
        Stack<TreeNode> stack = new Stack<>();
        Stack<TreeNode> postorder = new Stack<>();

        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            postorder.push(node);

            if (node.left != null) {
                stack.push(node.left);
            }

            if (node.right != null) {
                stack.push(node.right);
            }
        }

        int diameter = 0;

        while (!postorder.isEmpty()) {
            TreeNode node = postorder.pop();

            int left = height.getOrDefault(node.left, 0);
            int right = height.getOrDefault(node.right, 0);

            diameter = Math.max(diameter, left + right);

            height.put(node, 1 + Math.max(left, right));
        }

        return diameter;
    }
}
