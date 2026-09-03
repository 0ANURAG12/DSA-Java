```java
/**
 * LeetCode 3876 - Construct Uniform Parity Array II
 *
 * Problem:
 * https://leetcode.com/problems/construct-uniform-parity-array-ii/
 *
 * Approach:
 * Find the minimum element in the array.
 *
 * - If the minimum element is odd, the array can be made uniform.
 * - If the minimum element is even, every element must be even.
 *   If any odd element exists, it is not possible.
 *
 * Time Complexity: O(n)
 *   - The array is traversed at most twice.
 *
 * Space Complexity: O(1)
 *   - Only constant extra space is used.
 */

class Solution {
    public boolean uniformArray(int[] nums1) {

        int minEl = Integer.MAX_VALUE;

        // Find the minimum element
        for (int num : nums1) {
            minEl = Math.min(minEl, num);
        }

        // If minimum is odd, the array can be made uniform
        if (minEl % 2 == 1) {
            return true;
        }

        // If minimum is even, every element must be even
        for (int num : nums1) {
            if (num % 2 == 1) {
                return false;
            }
        }

        return true;
    }
}
```
