/*
 * LeetCode: 3903. Smallest Stable Index I
 * https://leetcode.com/problems/smallest-stable-index-i/
 *
 * ============================================================
 * APPROACH 1: Brute Force
 * ============================================================
 *
 * Intuition:
 * - For every index i, calculate the instability score:
 *
 *      max(nums[0...i]) - min(nums[i...n-1])
 *
 * - If the instability score is <= k, then i is a stable index.
 * - Return the first stable index.
 *
 * - In this approach, we calculate the maximum and minimum
 *   separately for every index, which results in repeated work.
 *
 * Time Complexity: O(n²)
 * Space Complexity: O(1)
 *
 * Why O(n²)?
 * - There are n possible indices.
 * - For every index, finding the maximum and minimum
 *   can take O(n) time.
 */


class BruteForce {

    public int firstStableIndex(int[] nums, int k) {

        int n = nums.length;

        for (int i = 0; i < n; i++) {

            int max = Integer.MIN_VALUE;
            int min = Integer.MAX_VALUE;

            // Find maximum in nums[0...i]
            for (int j = 0; j <= i; j++) {
                max = Math.max(max, nums[j]);
            }

            // Find minimum in nums[i...n-1]
            for (int j = i; j < n; j++) {
                min = Math.min(min, nums[j]);
            }

            // Check if current index is stable
            if (max - min <= k) {
                return i;
            }
        }

        return -1;
    }
}


/*
 * ============================================================
 * APPROACH 2: Prefix Maximum + Suffix Minimum
 * ============================================================
 *
 * Intuition:
 * - The brute force approach repeatedly calculates the same
 *   maximum and minimum values.
 *
 * - We can precompute these values to avoid repeated work.
 *
 * - maxArr[i] stores the maximum element from index 0 to i.
 *
 * - minArr[i] stores the minimum element from index i
 *   to n - 1.
 *
 * - For every index i, the instability score becomes:
 *
 *      maxArr[i] - minArr[i]
 *
 * - If this value is <= k, then i is a stable index.
 *
 * - Since we traverse from left to right, the first valid
 *   index is the smallest stable index.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 *
 * Why O(n)?
 * - Building prefix maximum array: O(n)
 * - Building suffix minimum array: O(n)
 * - Checking every index: O(n)
 *
 * Overall: O(n)
 */


class PrefixSuffix {

    public int firstStableIndex(int[] nums, int k) {

        int n = nums.length;

        int[] maxArr = new int[n];
        int[] minArr = new int[n];

        // maxArr[i] = maximum value from nums[0...i]
        maxArr[0] = nums[0];

        for (int i = 1; i < n; i++) {
            maxArr[i] = Math.max(maxArr[i - 1], nums[i]);
        }

        // minArr[i] = minimum value from nums[i...n-1]
        minArr[n - 1] = nums[n - 1];

        for (int i = n - 2; i >= 0; i--) {
            minArr[i] = Math.min(minArr[i + 1], nums[i]);
        }

        // Find the first stable index
        for (int i = 0; i < n; i++) {

            int instability = maxArr[i] - minArr[i];

            if (instability <= k) {
                return i;
            }
        }

        return -1;
    }
}
