/**
# LeetCode 572. Subtree of Another Tree

**Problem Link:** https://leetcode.com/problems/subtree-of-another-tree/

## Approach 1: DFS + Same Tree Check

For every node in the main tree, check whether the subtree rooted at that node
is identical to `subRoot`. If not, recursively search in the left and right
subtrees.

**Time Complexity:** O(M × N)

**Space Complexity:** O(H)

where:

* M = Number of nodes in the main tree (`root`)
* N = Number of nodes in the subtree (`subRoot`)
* H = Height of the main tree

*/
class Solution {
    public boolean isSubtree(TreeNode root, TreeNode subRoot) {

        if (subRoot == null) {
            return true;
        }

        if (root == null) {
            return false;
        }

        if (isSameTree(root, subRoot)) {
            return true;
        }

        return isSubtree(root.left, subRoot)
                || isSubtree(root.right, subRoot);
    }

    private boolean isSameTree(TreeNode p, TreeNode q) {

        if (p == null && q == null) {
            return true;
        }

        if (p == null || q == null) {
            return false;
        }

        if (p.val != q.val) {
            return false;
        }

        return isSameTree(p.left, q.left)
                && isSameTree(p.right, q.right);
    }
}

/*

---

## Approach 2: Tree Serialization + String Matching

Serialize both trees using preorder traversal with null markers.
Then check whether the serialized `subRoot` string is a substring
of the serialized `root` string.

**Time Complexity:** O(M + N)

**Space Complexity:** O(M + N)

where:

* M = Number of nodes in the main tree (`root`)
* N = Number of nodes in the subtree (`subRoot`)

*/

class Solution {

    public boolean isSubtree(TreeNode root, TreeNode subRoot) {

        StringBuilder rootTree = new StringBuilder();
        StringBuilder subTree = new StringBuilder();

        serialize(root, rootTree);
        serialize(subRoot, subTree);

        return rootTree.toString().contains(subTree.toString());
    }

    private void serialize(TreeNode node, StringBuilder sb) {

        if (node == null) {
            sb.append("#,");
            return;
        }

        sb.append(node.val).append(",");

        serialize(node.left, sb);
        serialize(node.right, sb);
    }
}
