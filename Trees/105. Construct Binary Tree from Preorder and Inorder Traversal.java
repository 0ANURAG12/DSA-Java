/**
# LeetCode 105. Construct Binary Tree from Preorder and Inorder Traversal

**Problem Link:** https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/

## Approach 1: DFS + HashMap (Optimal)

Preorder traversal gives the root node first.

Using the root value:
- Find its position in the inorder traversal.
- Elements to the left belong to the left subtree.
- Elements to the right belong to the right subtree.
- Recursively build both subtrees.

A HashMap is used to find the index of each node in inorder traversal in O(1) time.

**Time Complexity:** O(N)

**Space Complexity:** O(N)

where:

* N = Number of nodes in the tree

*/
class Solution {

    int preIndex = 0;
    Map<Integer, Integer> map = new HashMap<>();

    public TreeNode buildTree(int[] preorder, int[] inorder) {

        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }

        return build(preorder, 0, inorder.length - 1);
    }

    private TreeNode build(int[] preorder, int left, int right) {

        if (left > right) {
            return null;
        }

        int rootVal = preorder[preIndex++];
        TreeNode root = new TreeNode(rootVal);

        int mid = map.get(rootVal);

        root.left = build(preorder, left, mid - 1);
        root.right = build(preorder, mid + 1, right);

        return root;
    }
}

/*

---

## Approach 2: DFS Without HashMap

Instead of storing inorder indices in a HashMap,
search for the root value in the inorder array every time.

This increases the complexity because each search takes O(N).

**Time Complexity:** O(N²)

**Space Complexity:** O(H)

where:

* N = Number of nodes in the tree
* H = Height of the tree

*/

class Solution {

    int preIndex = 0;

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return build(preorder, inorder, 0, inorder.length - 1);
    }

    private TreeNode build(int[] preorder, int[] inorder,
                           int left, int right) {

        if (left > right) {
            return null;
        }

        int rootVal = preorder[preIndex++];
        TreeNode root = new TreeNode(rootVal);

        int mid = left;

        while (inorder[mid] != rootVal) {
            mid++;
        }

        root.left = build(preorder, inorder, left, mid - 1);
        root.right = build(preorder, inorder, mid + 1, right);

        return root;
    }
}
