/**
# LeetCode 199. Binary Tree Right Side View

**Problem Link:** https://leetcode.com/problems/binary-tree-right-side-view/

## Approach 1: DFS (Right First Traversal)

Traverse the tree using DFS while visiting the right subtree before the left subtree.

The first node encountered at each level is the node visible from the right side.

**Time Complexity:** O(N)

**Space Complexity:** O(H)

where:

* N = Number of nodes in the tree
* H = Height of the tree

*/
class Solution {

    List<Integer> list = new ArrayList<>();

    public List<Integer> rightSideView(TreeNode root) {
        dfs(root, 0);
        return list;
    }

    private void dfs(TreeNode root, int level) {

        if (root == null) {
            return;
        }

        if (list.size() == level) {
            list.add(root.val);
        }

        dfs(root.right, level + 1);
        dfs(root.left, level + 1);
    }
}

/*

---

## Approach 2: BFS (Level Order Traversal)

For every level, the last node processed is visible from the right side.

**Time Complexity:** O(N)

**Space Complexity:** O(N)

where:

* N = Number of nodes in the tree

*/

import java.util.*;

class Solution {

    public List<Integer> rightSideView(TreeNode root) {

        List<Integer> ans = new ArrayList<>();

        if (root == null) {
            return ans;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {

            int size = queue.size();

            for (int i = 0; i < size; i++) {

                TreeNode node = queue.poll();

                if (i == size - 1) {
                    ans.add(node.val);
                }

                if (node.left != null) {
                    queue.offer(node.left);
                }

                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
        }

        return ans;
    }
}
