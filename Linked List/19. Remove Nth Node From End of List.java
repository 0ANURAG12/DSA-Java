/*
Problem: 19. Remove Nth Node From End of List
Link: https://leetcode.com/problems/remove-nth-node-from-end-of-list/

Difficulty: Medium
Topic: Linked List

Time Complexity: O(n)
Space Complexity: O(1)
*/

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {

    public ListNode removeNthFromEnd(ListNode head, int n) {

        ListNode f = head;
        ListNode s = head;
        ListNode p = head;

        while (n-- > 0)
            f = f.next;

        if (f == null)
            return head.next;

        while (f != null) {
            f = f.next;
            p = s;
            s = s.next;
        }

        p.next = s.next;

        return head;
    }
}
