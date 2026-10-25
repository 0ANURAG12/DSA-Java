/*
 * LeetCode: 4083. Minimum Deletions
 * Topic: Arrays / Greedy / Two Pointers
 *
 *
 * ============================================================
 * APPROACH 1: Greedy + Consecutive Duplicate Counting
 * ============================================================
 *
 * Intuition:
 * - We traverse the array and count consecutive duplicate
 *   elements.
 *
 * - For each position i, initialize `count` to 0.
 *
 * - While the next element is equal to the current element,
 *   increment `count` and move i forward.
 *
 * - Once the consecutive group ends, move past the final
 *   element of that group.
 *
 * - Add `count` to `ans`.
 *
 * - For a group of k identical consecutive elements, this
 *   adds k - 1 to the answer, effectively counting how many
 *   elements would need to be deleted to leave one element
 *   from that group.
 *
 *
 * Example:
 *
 *      nums = [1, 1, 2, 2, 2, 3]
 *
 *      Group [1, 1]:
 *      Duplicate count = 1
 *
 *      Group [2, 2, 2]:
 *      Duplicate count = 2
 *
 *      Group [3]:
 *      Duplicate count = 0
 *
 *      Answer = 1 + 2 + 0 = 3
 *
 *
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 *
 * Why O(n)?
 * - The index i moves forward through the array.
 * - Each element is processed at most once.
 *
 * Space:
 * - Only a constant number of variables are used.
 */


class Solution {

    public int minDeletions(int[] nums) {

        int ans = 0;
        int n = nums.length;
        int i = 0;

        while (i < n) {

            int count = 0;

            // Count consecutive duplicates
            while (i + 1 < n && nums[i + 1] == nums[i]) {
                count++;
                i++;
            }

            // Move past the last element of this group
            i++;

            // Add the number of duplicates to the answer
            ans += count;
        }

        return ans;
    }
}
