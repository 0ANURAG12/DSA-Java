/*
# 23. Merge k Sorted Lists

Link: https://leetcode.com/problems/merge-k-sorted-lists/

Difficulty: Hard
Topic: Linked List, Divide and Conquer, Heap

--------------------------------------------------

Approach 1: Divide and Conquer

Time Complexity: O(N log k)
Space Complexity: O(log k)

where:
N = total number of nodes across all linked lists
k = number of linked lists

--------------------------------------------------
*/
class Solution {

    public ListNode mergeKLists(ListNode[] lists) {
        return partitionAndMerge(0, lists.length - 1, lists);
    }

    private ListNode partitionAndMerge(int s, int e, ListNode[] lists) {

        if (s == e)
            return lists[s];

        if (s > e)
            return null;

        int m = s + (e - s) / 2;

        ListNode l1 = partitionAndMerge(s, m, lists);
        ListNode l2 = partitionAndMerge(m + 1, e, lists);

        return mergeTwoLists(l1, l2);
    }

    private ListNode mergeTwoLists(ListNode l1, ListNode l2) {

        if (l1 == null)
            return l2;

        if (l2 == null)
            return l1;

        if (l1.val <= l2.val) {
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        }
    }
}

/*
--------------------------------------------------

Approach 2: Priority Queue

Time Complexity: O(N log k)
Space Complexity: O(k)

where:
N = total number of nodes across all linked lists
k = number of linked lists

--------------------------------------------------
*/
class Solution {

    public ListNode mergeKLists(ListNode[] lists) {

        PriorityQueue<ListNode> pq =
            new PriorityQueue<>((a, b) -> a.val - b.val);

        for (ListNode node : lists) {
            if (node != null) {
                pq.offer(node);
            }
        }

        ListNode dummy = new ListNode(-1);
        ListNode tail = dummy;

        while (!pq.isEmpty()) {

            ListNode curr = pq.poll();

            tail.next = curr;
            tail = tail.next;

            if (curr.next != null) {
                pq.offer(curr.next);
            }
        }

        return dummy.next;
    }
}
