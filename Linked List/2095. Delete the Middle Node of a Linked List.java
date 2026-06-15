/*
Problem: 2095. Delete the Middle Node of a Linked List
Link: https://leetcode.com/problems/delete-the-middle-node-of-a-linked-list/

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
 *     ListNode(int val, ListNode next) {
 *         this.val = val;
 *         this.next = next;
 *     }
 * }
 */
class Solution {

    public ListNode deleteMiddle(ListNode head) {

        if (head.next == null)
            return null;

        ListNode s = head;
        ListNode f = head;
        ListNode p = head;

        while (f != null && f.next != null) {
            f = f.next.next;
            p = s;
            s = s.next;
        }

        p.next = s.next;

        return head;
    }
}
