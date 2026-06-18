/**
# LeetCode 102. Binary Tree Level Order Traversal

**Problem Link:** https://leetcode.com/problems/binary-tree-level-order-traversal/

## Approach 1: DFS (Level Tracking)

Use DFS while keeping track of the current level.

For each node:
- If the current level does not exist in the answer list, create it.
- Add the node value to its corresponding level.
- Recursively process the left and right children.

**Time Complexity:** O(N)

**Space Complexity:** O(N)

where:

* N = Number of nodes in the tree

*/
class Solution {

    List<List<Integer>> list = new ArrayList<>();

    public List<List<Integer>> levelOrder(TreeNode root) {

        if (root == null) {
            return list;
        }

        solve(root, 0);

        return list;
    }

    private void solve(TreeNode node, int level) {

        if (list.size() == level) {
            list.add(new ArrayList<>());
        }

        list.get(level).add(node.val);

        if (node.left != null) {
            solve(node.left, level + 1);
        }

        if (node.right != null) {
            solve(node.right, level + 1);
        }
    }
}

/*

---

## Approach 2: BFS (Queue) - Recommended

Process nodes level by level using a queue.

For each level:
- Store the current queue size.
- Process exactly that many nodes.
- Add their children to the queue.
- Store the current level values in the answer.

**Time Complexity:** O(N)

**Space Complexity:** O(N)

where:

* N = Number of nodes in the tree

*/

import java.util.*;

class Solution {

    public List<List<Integer>> levelOrder(TreeNode root) {

        List<List<Integer>> ans = new ArrayList<>();

        if (root == null) {
            return ans;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {

            int size = queue.size();
            List<Integer> level = new ArrayList<>();

            while (size-- > 0) {

                TreeNode node = queue.poll();

                level.add(node.val);

                if (node.left != null) {
                    queue.offer(node.left);
                }

                if (node.right != null) {
                    queue.offer(node.right);
                }
            }

            ans.add(level);
        }

        return ans;
    }
}
