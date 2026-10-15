/*
 * LeetCode: 2913. Subarrays Distinct Element Sum of Squares I
 * https://leetcode.com/problems/subarrays-distinct-element-sum-of-squares-i/
 *
 * Topic: Arrays / HashSet / Brute Force
 *
 *
 * ============================================================
 * APPROACH 1: Brute Force + HashSet
 * ============================================================
 *
 * Intuition:
 * - We need to consider every possible subarray and calculate
 *   the square of its number of distinct elements.
 *
 * - Fix the starting index `i` and extend the subarray using `j`.
 *
 * - Use a HashSet to keep track of distinct elements in the
 *   current subarray.
 *
 * - Whenever a new element is encountered, add it to the set
 *   and increment `unique`.
 *
 * - For every subarray, add unique * unique to the current sum.
 *
 * - Finally, add the sum for each starting index to `ans`.
 *
 *
 * Example:
 *
 *      nums = [1, 2, 1]
 *
 *      Subarrays:
 *      [1]       -> 1 distinct -> 1^2 = 1
 *      [1, 2]    -> 2 distinct -> 2^2 = 4
 *      [1, 2, 1] -> 2 distinct -> 2^2 = 4
 *      [2]       -> 1 distinct -> 1^2 = 1
 *      [2, 1]    -> 2 distinct -> 2^2 = 4
 *      [1]       -> 1 distinct -> 1^2 = 1
 *
 *      Answer = 1 + 4 + 4 + 1 + 4 + 1 = 15
 *
 *
 * Time Complexity: O(n^2) average
 * Space Complexity: O(n)
 *
 * Why O(n^2)?
 * - There are O(n^2) possible subarrays.
 * - HashSet operations take O(1) average time.
 *
 * Space:
 * - The HashSet can store up to n distinct elements.
 */


class Solution {

    public int sumCounts(List<Integer> nums) {

        int n = nums.size();
        int ans = 0;

        // Consider every possible starting index
        for (int i = 0; i < n; i++) {

            Set<Integer> set = new HashSet<>();
            int sum = 0;
            int unique = 0;

            // Extend the subarray from index i
            for (int j = i; j < n; j++) {

                // Count a new distinct element
                if (!set.contains(nums.get(j))) {
                    set.add(nums.get(j));
                    unique++;
                }

                // Add the square of the distinct count
                sum += unique * unique;
            }

            // Add contributions of subarrays starting at i
            ans += sum;
        }

        return ans;
    }
}
