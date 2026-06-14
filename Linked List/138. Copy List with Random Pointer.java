/*
Problem: 138. Copy List with Random Pointer
Link: https://leetcode.com/problems/copy-list-with-random-pointer/

Difficulty: Medium
Topic: Linked List

--------------------------------------------------
Approach 1: HashMap
Time Complexity: O(n)
Space Complexity: O(n)
*/
class Solution {
    public Node copyRandomList(Node head) {

        Map<Node, Node> map = new HashMap<>();

        Node dummy = new Node(-1);
        Node curr = head;
        Node copy = dummy;

        while (curr != null) {
            copy.next = new Node(curr.val);

            map.put(curr, copy.next);

            copy = copy.next;
            curr = curr.next;
        }

        curr = head;
        copy = dummy.next;

        while (curr != null) {
            copy.random = map.get(curr.random);

            curr = curr.next;
            copy = copy.next;
        }

        return dummy.next;
    }
}
/*
--------------------------------------------------
Approach 2: Interweaving Nodes (Optimal)
Time Complexity: O(n)
Space Complexity: O(1)
--------------------------------------------------
*/

/*
// Definition for a Node.
class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
*/

class Solution {

    public Node copyRandomList(Node head) {

        if (head == null)
            return null;

        Node curr = head;

        // Step 1: Insert copied nodes
        while (curr != null) {

            Node nextNode = curr.next;

            curr.next = new Node(curr.val);
            curr.next.next = nextNode;

            curr = nextNode;
        }

        // Step 2: Assign random pointers
        curr = head;

        while (curr != null) {

            Node copy = curr.next;

            copy.random =
                    (curr.random == null)
                    ? null
                    : curr.random.next;

            curr = copy.next;
        }

        // Step 3: Separate original and copied lists
        curr = head;

        Node dummy = new Node(-1);
        Node tail = dummy;

        while (curr != null) {

            Node copy = curr.next;

            tail.next = copy;
            tail = tail.next;

            curr.next = copy.next;
            curr = copy.next;
        }

        return dummy.next;
    }
}
