```java
/**
 * LeetCode 2058 - Find the Minimum and Maximum Number of Nodes Between Critical Points
 *
 * Approach:
 * Traverse the linked list once and identify critical points.
 *
 * A node is a critical point if it is either:
 * 1. A local maximum: curr.val > prev.val && curr.val > curr.next.val
 * 2. A local minimum: curr.val < prev.val && curr.val < curr.next.val
 *
 * We keep track of:
 * - firstCriticalPointPosition -> position of the first critical point
 * - lastCriticalPointPosition  -> position of the previous critical point
 * - minDistance                -> minimum distance between consecutive critical points
 *
 * The maximum distance is simply the distance between the first and last
 * critical points.
 *
 * Time Complexity: O(n)
 *   - We traverse the linked list only once.
 *
 * Space Complexity: O(1)
 *   - Only a constant number of variables are used.
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
    public int[] nodesBetweenCriticalPoints(ListNode head) {

        int[] ans = new int[]{-1, -1};

        int firstCriticalPointPosition = 0;
        int lastCriticalPointPosition = 0;
        int minDistance = Integer.MAX_VALUE;

        int i = 1;
        ListNode curr = head.next;
        ListNode prev = head;

        while (curr.next != null) {

            // Check if current node is a local maximum or local minimum
            if ((curr.val > prev.val && curr.val > curr.next.val) ||
                (curr.val < prev.val && curr.val < curr.next.val)) {

                // Store the position of the first critical point
                if (firstCriticalPointPosition == 0) {
                    firstCriticalPointPosition = i;
                } else {
                    // Distance from the previous critical point
                    minDistance = Math.min(
                        minDistance,
                        i - lastCriticalPointPosition
                    );
                }

                lastCriticalPointPosition = i;
            }

            prev = curr;
            curr = curr.next;
            i++;
        }

        // Minimum distance exists only if there are at least two critical points
        if (minDistance != Integer.MAX_VALUE) {
            ans[0] = minDistance;
        }

        // Maximum distance = last critical point - first critical point
        if (lastCriticalPointPosition != 0 &&
            firstCriticalPointPosition != lastCriticalPointPosition) {
            ans[1] = lastCriticalPointPosition - firstCriticalPointPosition;
        }

        return ans;
    }
}
```

**Complexity:**

* **Time:** `O(n)` — one traversal of the linked list.
* **Space:** `O(1)` — no extra data structures are used.

One small improvement I made is adding parentheses around the critical-point condition. It makes the operator precedence explicit and makes the code easier to read on GitHub.
