/*
Problem: 2130. Maximum Twin Sum of a Linked List
Link: https://leetcode.com/problems/maximum-twin-sum-of-a-linked-list/

Difficulty: Medium
Topic: Linked List

Approach 1:
Time Complexity: O(n)
Space Complexity: O(n)

Store first half in an array and compute twin sums
while traversing the second half.

--------------------------------------------------
*/

class Solution {
    public int pairSum(ListNode head) {
        ListNode temp = head;
        int n = 0;

        while (temp != null) {
            n++;
            temp = temp.next;
        }

        int[] arr = new int[n / 2];

        temp = head;

        for (int i = 0; i < n / 2; i++) {
            arr[i] = temp.val;
            temp = temp.next;
        }

        int ans = 0;

        for (int i = n / 2; i < n; i++) {
            arr[n - 1 - i] += temp.val;
            ans = Math.max(ans, arr[n - 1 - i]);
            temp = temp.next;
        }

        return ans;
    }
}

/*
--------------------------------------------------

Approach 2 (Optimal):
Time Complexity: O(n)
Space Complexity: O(1)

Find middle -> Reverse second half -> Compute twin sums

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

    public int pairSum(ListNode head) {

        ListNode slow = head;
        ListNode fast = head;
        ListNode read = head;

        while (fast != null) {

            fast = fast.next.next;

            if (slow != head)
                read = read.next;

            slow = slow.next;
        }

        read.next = null;

        fast = head;

        ListNode prev = null;

        while (slow != null) {

            ListNode next = slow.next;
            slow.next = prev;
            prev = slow;
            slow = next;
        }

        int maxSum = 0;

        while (prev != null) {

            maxSum = Math.max(maxSum, prev.val + fast.val);

            prev = prev.next;
            fast = fast.next;
        }

        return maxSum;
    }
}
