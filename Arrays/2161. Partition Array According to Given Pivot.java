/*
Problem: 2161. Partition Array According to Given Pivot
Link: https://leetcode.com/problems/partition-array-according-to-given-pivot/

Difficulty: Medium
Topic: Arrays

Approach:
1. Traverse the array and place all elements smaller than pivot.
2. Traverse again and place all elements equal to pivot.
3. Traverse again and place all elements greater than pivot.
4. Since we process elements in their original order, relative ordering is preserved.

Time Complexity: O(n)
Space Complexity: O(n)
*/

class Solution {
    public int[] pivotArray(int[] nums, int pivot) {
        int[] ans = new int[nums.length];
        int idx = 0;

        for (int n : nums)
            if (n < pivot)
                ans[idx++] = n;

        for (int n : nums)
            if (n == pivot)
                ans[idx++] = n;

        for (int n : nums)
            if (n > pivot)
                ans[idx++] = n;

        return ans;
    }
}
