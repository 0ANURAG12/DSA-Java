/**
# LeetCode 230. Kth Smallest Element in a BST

**Problem Link:** https://leetcode.com/problems/kth-smallest-element-in-a-bst/

## Approach 1: Recursive Inorder Traversal

Inorder traversal of a BST visits nodes in sorted order.

Keep a counter while traversing:
- Increment the counter when visiting a node.
- When the counter becomes `k`, return the current node value.

**Time Complexity:** O(H + K)

**Space Complexity:** O(H)

where:

* N = Number of nodes in the tree
* H = Height of the tree
* K = Required kth smallest element

*/
class Solution {

    int count = 0;

    public int kthSmallest(TreeNode root, int k) {

        if (root == null) {
            return Integer.MAX_VALUE;
        }

        int left = kthSmallest(root.left, k);

        if (++count == k) {
            return root.val;
        }

        int right = kthSmallest(root.right, k);

        return Math.min(left, right);
    }
}

/*

---

## Approach 2: Recursive Inorder with Early Return (Recommended)

Stop traversal as soon as the kth element is found.

**Time Complexity:** O(H + K)

**Space Complexity:** O(H)

where:

* H = Height of the tree
* K = Required kth smallest element

*/

class Solution {

    private int count = 0;
    private int answer = 0;

    public int kthSmallest(TreeNode root, int k) {

        inorder(root, k);

        return answer;
    }

    private void inorder(TreeNode root, int k) {

        if (root == null) {
            return;
        }

        inorder(root.left, k);

        if (++count == k) {
            answer = root.val;
            return;
        }

        inorder(root.right, k);
    }
}

/*

---

## Approach 3: Iterative Inorder Traversal (Stack)

Since inorder traversal of a BST gives nodes in sorted order,
the kth visited node is the kth smallest element.

**Time Complexity:** O(H + K)

**Space Complexity:** O(H)

where:

* H = Height of the tree
* K = Required kth smallest element

*/

import java.util.Stack;

class Solution {

    public int kthSmallest(TreeNode root, int k) {

        Stack<TreeNode> stack = new Stack<>();

        while (root != null || !stack.isEmpty()) {

            while (root != null) {
                stack.push(root);
                root = root.left;
            }

            root = stack.pop();

            if (--k == 0) {
                return root.val;
            }

            root = root.right;
        }

        return -1;
    }
}
