/**
# LeetCode 3701. Count Majority Subarrays

**Problem Link:** https://leetcode.com/problems/count-majority-subarrays/

## Approach 1: Brute Force

Consider every possible subarray.

For each starting index:

- Extend the subarray one element at a time.
- Maintain the frequency of the target element.
- If the target appears more than half of the current subarray length,
  count the subarray.

This checks every subarray independently.

**Time Complexity:** O(N²)

**Space Complexity:** O(1)

where:

* N = Length of the input array

*/
class Solution {

    public int countMajoritySubarrays(int[] nums, int target) {

        int ans = 0;
        int n = nums.length;

        for (int i = 0; i < n; i++) {

            int count = 0;

            for (int j = i; j < n; j++) {

                if (nums[j] == target) {
                    count++;
                }

                int len = j - i + 1;

                if (count > len / 2) {
                    ans++;
                }
            }
        }

        return ans;
    }
}
