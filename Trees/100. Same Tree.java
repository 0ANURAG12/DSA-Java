/**
# LeetCode 100. Same Tree

**Problem Link:** https://leetcode.com/problems/same-tree/

## Approach 1: Recursive DFS

**Time Complexity:** O(N)

**Space Complexity:** O(H)

where:

* N = Number of nodes in the tree
* H = Height of the tree

*/
class Solution {
    public boolean isSameTree(TreeNode p, TreeNode q) {

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

## Approach 2: Iterative DFS (Stack)

**Time Complexity:** O(N)

**Space Complexity:** O(H)

where:

* N = Number of nodes in the tree
* H = Height of the tree

*/

import java.util.Stack;

class Solution {
    public boolean isSameTree(TreeNode p, TreeNode q) {

        Stack<TreeNode> stackP = new Stack<>();
        Stack<TreeNode> stackQ = new Stack<>();

        stackP.push(p);
        stackQ.push(q);

        while (!stackP.isEmpty() && !stackQ.isEmpty()) {

            TreeNode nodeP = stackP.pop();
            TreeNode nodeQ = stackQ.pop();

            if (nodeP == null && nodeQ == null) {
                continue;
            }

            if (nodeP == null || nodeQ == null) {
                return false;
            }

            if (nodeP.val != nodeQ.val) {
                return false;
            }

            stackP.push(nodeP.left);
            stackQ.push(nodeQ.left);

            stackP.push(nodeP.right);
            stackQ.push(nodeQ.right);
        }

        return stackP.isEmpty() && stackQ.isEmpty();
    }
}

/*

---

## Approach 3: BFS (Queue)

**Time Complexity:** O(N)

**Space Complexity:** O(W)

where:

* N = Number of nodes in the tree
* W = Maximum width of the tree

*/

import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public boolean isSameTree(TreeNode p, TreeNode q) {

        Queue<TreeNode> queueP = new LinkedList<>();
        Queue<TreeNode> queueQ = new LinkedList<>();

        queueP.offer(p);
        queueQ.offer(q);

        while (!queueP.isEmpty() && !queueQ.isEmpty()) {

            TreeNode nodeP = queueP.poll();
            TreeNode nodeQ = queueQ.poll();

            if (nodeP == null && nodeQ == null) {
                continue;
            }

            if (nodeP == null || nodeQ == null) {
                return false;
            }

            if (nodeP.val != nodeQ.val) {
                return false;
            }

            queueP.offer(nodeP.left);
            queueQ.offer(nodeQ.left);

            queueP.offer(nodeP.right);
            queueQ.offer(nodeQ.right);
        }

        return queueP.isEmpty() && queueQ.isEmpty();
    }
}
