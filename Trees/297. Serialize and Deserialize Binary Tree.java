/**
# LeetCode 297. Serialize and Deserialize Binary Tree

**Problem Link:** https://leetcode.com/problems/serialize-and-deserialize-binary-tree/

## Approach 1: BFS (Level Order Traversal)

Use level order traversal to serialize the tree.

- Store node values as they appear in BFS order.
- Store "_" for null nodes to preserve tree structure.
- During deserialization, rebuild the tree level by level using a queue.
- Each node popped from the queue receives its left and right children from the serialized data.

**Time Complexity:** O(N)

**Space Complexity:** O(N)

where:

* N = Number of nodes in the tree

*/
public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {

        if (root == null) {
            return "_";
        }

        StringBuilder sb = new StringBuilder();

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {

            TreeNode node = queue.poll();

            if (node == null) {
                sb.append("_,");
                continue;
            }

            sb.append(node.val).append(",");

            queue.offer(node.left);
            queue.offer(node.right);
        }

        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {

        if (data.equals("_")) {
            return null;
        }

        String[] arr = data.split(",");

        TreeNode root = new TreeNode(Integer.parseInt(arr[0]));

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        int i = 1;

        while (!queue.isEmpty()) {

            TreeNode parent = queue.poll();

            if (!arr[i].equals("_")) {
                parent.left = new TreeNode(Integer.parseInt(arr[i]));
                queue.offer(parent.left);
            }
            i++;

            if (!arr[i].equals("_")) {
                parent.right = new TreeNode(Integer.parseInt(arr[i]));
                queue.offer(parent.right);
            }
            i++;
        }

        return root;
    }
}

/*

---

## Approach 2: DFS (Preorder Traversal)

Serialize the tree using preorder traversal.

- Store node values in preorder.
- Store "_" for null nodes.
- During deserialization, recursively rebuild the tree using the preorder sequence.

**Time Complexity:** O(N)

**Space Complexity:** O(N)

where:

* N = Number of nodes in the tree

*/

public class Codec {

    public String serialize(TreeNode root) {

        StringBuilder sb = new StringBuilder();

        preorder(root, sb);

        return sb.toString();
    }

    private void preorder(TreeNode root, StringBuilder sb) {

        if (root == null) {
            sb.append("_,");
            return;
        }

        sb.append(root.val).append(",");

        preorder(root.left, sb);
        preorder(root.right, sb);
    }

    int index = 0;

    public TreeNode deserialize(String data) {

        String[] arr = data.split(",");

        return build(arr);
    }

    private TreeNode build(String[] arr) {

        if (arr[index].equals("_")) {
            index++;
            return null;
        }

        TreeNode root =
                new TreeNode(Integer.parseInt(arr[index++]));

        root.left = build(arr);
        root.right = build(arr);

        return root;
    }
}
