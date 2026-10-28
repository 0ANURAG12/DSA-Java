/*
 * LeetCode: 15. 3Sum
 * https://leetcode.com/problems/3sum/
 *
 * Topic: Arrays / Sorting / Two Pointers
 *
 *
 * ============================================================
 * APPROACH 1: Sorting + Two Pointers
 * ============================================================
 *
 * Intuition:
 * - We need to find all unique triplets whose sum is zero.
 *
 * - First, sort the array so that we can use two pointers
 *   to efficiently search for the remaining two elements.
 *
 * - Fix the first element using index i.
 *
 * - Skip duplicate values of nums[i] to avoid generating
 *   duplicate triplets.
 *
 * - Initialize two pointers:
 *
 *       j = i + 1
 *       k = n - 1
 *
 * - Calculate the sum of nums[i], nums[j], and nums[k].
 *
 * - If the sum equals zero:
 *   Add the triplet to the answer.
 *   Skip duplicate values at j and k.
 *   Move both pointers inward.
 *
 * - If the sum is greater than zero, decrease k to reduce
 *   the sum.
 *
 * - If the sum is less than zero, increase j to increase
 *   the sum.
 *
 * - Continue until j and k meet.
 *
 *
 * Example:
 *
 *      nums = [-1, 0, 1, 2, -1, -4]
 *
 *      After sorting:
 *      [-4, -1, -1, 0, 1, 2]
 *
 *      Unique triplets:
 *      [-1, -1, 2] -> Sum = 0
 *      [-1,  0, 1] -> Sum = 0
 *
 *      Answer:
 *      [[-1, -1, 2], [-1, 0, 1]]
 *
 *
 * Time Complexity: O(n^2)
 * Space Complexity: O(log n) auxiliary space on typical
 * Java primitive-array sorting implementations.
 *
 * Why O(n^2)?
 * - Sorting takes O(n log n).
 * - For each fixed i, the two-pointer scan takes O(n).
 * - Repeating this scan for all i takes O(n^2).
 *
 * Space:
 * - The answer list is excluded from auxiliary space.
 * - Sorting may use O(log n) stack space.
 */


class Solution {

    public List<List<Integer>> threeSum(int[] nums) {

        int n = nums.length;

        // Sort the array to enable the two-pointer approach
        Arrays.sort(nums);

        List<List<Integer>> ans = new ArrayList<>();

        // Fix the first element of each triplet
        for (int i = 0; i < n - 2; i++) {

            // Skip duplicate first elements
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            int j = i + 1;
            int k = n - 1;

            // Search for the other two elements
            while (j < k) {

                int val = nums[i] + nums[j] + nums[k];

                if (val == 0) {

                    // Found a valid triplet
                    ans.add(Arrays.asList(
                        nums[i], nums[j], nums[k]
                    ));

                    // Skip duplicate values at both pointers
                    while (j < k && nums[j] == nums[j + 1]) {
                        j++;
                    }

                    while (j < k && nums[k] == nums[k - 1]) {
                        k--;
                    }

                    // Move both pointers after finding a triplet
                    j++;
                    k--;

                } else if (val > 0) {

                    // Sum is too large
                    k--;

                } else {

                    // Sum is too small
                    j++;
                }
            }
        }

        return ans;
    }
}
