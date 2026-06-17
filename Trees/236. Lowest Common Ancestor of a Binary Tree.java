/**
# LeetCode 236. Lowest Common Ancestor of a Binary Tree

**Problem Link:** https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/

## Approach 1: Recursive DFS (Optimal)

Search for both nodes in the left and right subtrees.

Cases:

1. If the current node is `p` or `q`, return it.
2. If both left and right recursive calls return non-null values,
   the current node is the Lowest Common Ancestor.
3. If only one side returns a non-null value, propagate it upward.
4. If both sides return null, return null.

**Time Complexity:** O(N)

**Space Complexity:** O(H)

where:

* N = Number of nodes in the tree
* H = Height of the tree

*/
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

        if (root == null) {
            return null;
        }

        if (root.val == p.val || root.val == q.val) {
            return root;
        }

        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        if (left != null && right != null) {
            return root;
        }

        return left == null ? right : left;
    }
}

/*

---

## Approach 2: Parent Map + Ancestor Set

Store each node's parent using DFS/BFS.

1. Traverse the tree and build a parent map.
2. Store all ancestors of `p` in a set.
3. Move from `q` towards the root.
4. The first ancestor present in the set is the LCA.

**Time Complexity:** O(N)

**Space Complexity:** O(N)

where:

* N = Number of nodes in the tree

*/

import java.util.*;

class Solution {

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

        Map<TreeNode, TreeNode> parent = new HashMap<>();
        Stack<TreeNode> stack = new Stack<>();

        parent.put(root, null);
        stack.push(root);

        while (!parent.containsKey(p) || !parent.containsKey(q)) {

            TreeNode node = stack.pop();

            if (node.left != null) {
                parent.put(node.left, node);
                stack.push(node.left);
            }

            if (node.right != null) {
                parent.put(node.right, node);
                stack.push(node.right);
            }
        }

        Set<TreeNode> ancestors = new HashSet<>();

        while (p != null) {
            ancestors.add(p);
            p = parent.get(p);
        }

        while (!ancestors.contains(q)) {
            q = parent.get(q);
        }

        return q;
    }
}
