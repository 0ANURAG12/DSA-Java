/*
Problem: 3689. Maximum Total Subarray Value I
Link: https://leetcode.com/problems/maximum-total-subarray-value-i/

Difficulty: Medium
Topic: Greedy

Time Complexity: O(n)
Space Complexity: O(1)
*/

class Solution {

    public long maxTotalValue(int[] nums, int k) {

        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for (int n : nums) {
            max = Math.max(max, n);
            min = Math.min(min, n);
        }

        return (long) (max - min) * k;
    }
}
