/**
# LeetCode 104. Maximum Depth of Binary Tree

**Problem Link:** https://leetcode.com/problems/maximum-depth-of-binary-tree/

## Approach 1: Recursive DFS

**Time Complexity:** O(N)

**Space Complexity:** O(H)

where:

* N = Number of nodes in the tree
* H = Height of the tree

*/
class Solution {
    public int maxDepth(TreeNode root) {
        if(root == null) return 0;

        int left = maxDepth(root.left) + 1;
        int right = maxDepth(root.right) + 1;

        return Math.max(left, right);
    }
}

/*

---

## Approach 2: Iterative DFS (Stack)

**Time Complexity:** O(N)

**Space Complexity:** O(H)

where:

* N = Number of nodes in the tree
* H = Height of the tree

*/

import java.util.Stack;

class Solution {
    public int maxDepth(TreeNode root) {
        if(root == null) return 0;

        Stack<TreeNode> nodeStack = new Stack<>();
        Stack<Integer> depthStack = new Stack<>();

        nodeStack.push(root);
        depthStack.push(1);

        int ans = 0;

        while(!nodeStack.isEmpty()) {
            TreeNode node = nodeStack.pop();
            int depth = depthStack.pop();

            ans = Math.max(ans, depth);

            if(node.left != null) {
                nodeStack.push(node.left);
                depthStack.push(depth + 1);
            }

            if(node.right != null) {
                nodeStack.push(node.right);
                depthStack.push(depth + 1);
            }
        }

        return ans;
    }
}

/*

---

## Approach 3: BFS (Level Order Traversal)

**Time Complexity:** O(N)

**Space Complexity:** O(W)

where:

* N = Number of nodes in the tree
* W = Maximum width of the tree

*/

import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public int maxDepth(TreeNode root) {
        if(root == null) return 0;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        int depth = 0;

        while(!queue.isEmpty()) {
            int size = queue.size();

            while(size-- > 0) {
                TreeNode node = queue.poll();

                if(node.left != null) {
                    queue.offer(node.left);
                }

                if(node.right != null) {
                    queue.offer(node.right);
                }
            }

            depth++;
        }

        return depth;
    }
}
