/*
Problem: 143. Reorder List
Link: https://leetcode.com/problems/reorder-list/

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

    public void reorderList(ListNode head) {

        if (head == null || head.next == null)
            return;

        ListNode list1 = half(head);

        ListNode h2 = list1.next;
        list1.next = null;

        ListNode list2 = reverseList(h2);

        list1 = head;

        ListNode temp = list1;
        list1 = list1.next;

        boolean flag = false;

        while (list1 != null && list2 != null) {

            if (flag) {
                temp.next = list1;
                list1 = list1.next;
                flag = false;
            } else {
                temp.next = list2;
                list2 = list2.next;
                flag = true;
            }

            temp = temp.next;
        }

        temp.next = (list1 == null) ? list2 : list1;
    }

    ListNode half(ListNode head) {

        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

    public ListNode reverseList(ListNode head) {

        ListNode node = head;
        ListNode prev = null;

        while (node != null) {

            ListNode temp = node.next;
            node.next = prev;
            prev = node;
            node = temp;
        }

        return prev;
    }
}
