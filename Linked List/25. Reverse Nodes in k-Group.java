/**
 * LeetCode 25. Reverse Nodes in k-Group
 * https://leetcode.com/problems/reverse-nodes-in-k-group/
 *
 * Time Complexity: O(N)
 *   - N = Total number of nodes in the linked list.
 *
 * Space Complexity: O(N/K)
 *   - N = Total number of nodes in the linked list.
 *   - K = Size of each group being reversed.
 *   - O(N/K) recursive calls are stored in the call stack.
 */

class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {

        // Check if there are at least k nodes available
        ListNode temp = head;
        int count = 0;

        while (temp != null && count < k) {
            temp = temp.next;
            count++;
        }

        // If fewer than k nodes remain, return as it is
        if (count < k) {
            return head;
        }

        // Reverse the current group of k nodes
        ListNode prev = null;
        ListNode curr = head;
        ListNode next = null;
        count = 0;

        while (curr != null && count < k) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
            count++;
        }

        // Connect the remaining list
        head.next = reverseKGroup(curr, k);

        // Return new head of the reversed group
        return prev;
    }
}
