/*
 * LeetCode: 1524. Number of Sub-arrays With Odd Sum
 * https://leetcode.com/problems/number-of-sub-arrays-with-odd-sum/
 *
 * Topic: Arrays / Prefix Sum / Parity Counting / Math
 *
 *
 * ============================================================
 * APPROACH 1: Prefix Sum + Parity Counting
 * ============================================================
 *
 * Intuition:
 * - We need to count the number of contiguous subarrays
 *   whose sum is odd.
 *
 * - The key observation is that the difference between
 *   two prefix sums gives the sum of a subarray.
 *
 * - A subarray has an odd sum when its two prefix sums
 *   have different parities:
 *
 *      Odd  - Even = Odd
 *      Even - Odd = Odd
 *
 * - Maintain three important variables:
 *
 *      oddCount  = Number of odd prefix sums encountered.
 *      evenCount = Number of even prefix sums encountered.
 *      sum       = Current prefix sum.
 *
 * - For every element:
 *
 *   1. Update the current prefix sum.
 *
 *   2. If the prefix sum is even:
 *      - Add the number of previous odd prefix sums
 *        to the answer.
 *      - Increment evenCount.
 *
 *   3. If the prefix sum is odd:
 *      - Add the number of previous even prefix sums
 *        to the answer.
 *      - Increment oddCount.
 *      - Also count the subarray starting from index 0.
 *
 * - We take modulo 10^9 + 7 to prevent integer overflow.
 *
 *
 * Example:
 *
 *      arr = [1, 3, 5]
 *
 *      Prefix sums:
 *
 *      1       -> Odd
 *      1 + 3   -> Even
 *      1 + 3 + 5 -> Odd
 *
 *      Odd-sum subarrays:
 *
 *      [1]         -> Sum = 1
 *      [1, 3, 5]   -> Sum = 9
 *      [3]         -> Sum = 3
 *      [5]         -> Sum = 5
 *
 *      Total odd-sum subarrays = 4
 *
 *      Answer = 4
 *
 *
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 *
 * Why O(n)?
 * - We traverse the array exactly once.
 * - Each iteration performs constant-time operations.
 *
 * Space:
 * - Only a fixed number of variables are used.
 * - No additional data structure is required.
 */


class Solution {

    public int numOfSubarrays(int[] arr) {

        int MOD = (int) 1e9 + 7;

        int ans = 0;
        int oddCount = 0;
        int evenCount = 0;
        int sum = 0;

        // Traverse the array and calculate prefix sums
        for (int a : arr) {

            sum += a;

            if (sum % 2 == 0) {

                // Current prefix sum is even
                evenCount++;

                // Pair with previous odd prefix sums
                if (oddCount != 0) {
                    ans = (ans + oddCount) % MOD;
                }

            } else {

                // Current prefix sum is odd
                oddCount++;

                // Count the subarray starting from index 0
                ans++;

                // Pair with previous even prefix sums
                if (evenCount != 0) {
                    ans = (ans + evenCount) % MOD;
                }
            }
        }

        return ans % MOD;
    }
}
