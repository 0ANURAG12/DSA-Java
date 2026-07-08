/*
 * LeetCode: 3756. Concatenate Non-Zero Digits and Multiply by Sum II
 * https://leetcode.com/problems/concatenate-non-zero-digits-and-multiply-by-sum-ii/
 *
 * ------------------------------------------------------------
 * Approach: Prefix Sums + Prefix Concatenation + Precomputation
 * ------------------------------------------------------------
 * Intuition:
 * - Since there can be up to 10^5 queries, processing each query
 *   independently would be too slow.
 * - Precompute:
 *   1. Prefix count of non-zero digits.
 *   2. Prefix concatenated number formed by non-zero digits.
 *   3. Prefix sum of digits.
 *   4. Powers of 10 modulo 1e9+7.
 * - For each query [l, r]:
 *   - Determine how many non-zero digits belong to the substring.
 *   - Recover the concatenated non-zero number in O(1) using the
 *     prefix concatenation values and powers of 10.
 *   - Compute the digit sum using the prefix sum array.
 *   - Return (concatenatedNumber × digitSum) mod 1e9+7.
 *
 * Time Complexity:
 * - Preprocessing: O(N)
 * - Each Query: O(1)
 * - Overall: O(N + Q)
 *
 * Space Complexity: O(N)
 *
 * where:
 * N = Length of the string
 * Q = Number of queries
 *
 * Alternative Approach:
 * 1. Brute Force
 *    - Process every query independently.
 *    - Traverse the substring, build the concatenated non-zero number,
 *      compute its digit sum, and multiply the results.
 *
 *    Time Complexity: O(Q × N)
 *    Space Complexity: O(1)
 *
 * Note:
 * - Prefix preprocessing reduces each query from O(N) to O(1), making
 *   this approach efficient for a large number of queries.
 */

class Solution {
    static final long MOD = 1_000_000_007L;

    public int[] sumAndMultiply(String s, int[][] queries) {
        int n = s.length();

        int[]  nonZeroCount = new int[n];      // non-zero digits count in s[0..i]
        long[] numberUpTo   = new long[n];     // number formed from non-zero digits in s[0..i]
        long[] digitSumUpTo = new long[n];     // digit sum of s[0..i]
        long[] pow10        = new long[n + 1]; // 10^i

        pow10[0] = 1;
        for (int i = 1; i <= n; i++) pow10[i] = (pow10[i - 1] * 10) % MOD;

        nonZeroCount[0] = (s.charAt(0) != '0') ? 1 : 0;
        numberUpTo[0]   = s.charAt(0) - '0';
        digitSumUpTo[0] = s.charAt(0) - '0';

        for (int i = 1; i < n; i++) {
            int digit = s.charAt(i) - '0';
            nonZeroCount[i] = nonZeroCount[i - 1] + (digit != 0 ? 1 : 0);
        }

        for (int i = 1; i < n; i++) {
            int digit = s.charAt(i) - '0';
            if (digit != 0)
                numberUpTo[i] = (numberUpTo[i - 1] * 10 + digit) % MOD;
            else
                numberUpTo[i] = numberUpTo[i - 1];
        }

        for (int i = 1; i < n; i++) {
            digitSumUpTo[i] = digitSumUpTo[i - 1] + (s.charAt(i) - '0');
        }

        int q = queries.length;
        int[] result = new int[q];

        for (int i = 0; i < q; i++) {
            int l = queries[i][0];
            int r = queries[i][1];

            int  startCount = (l == 0) ? 0 : nonZeroCount[l - 1];
            long numBefore  = (l == 0) ? 0 : numberUpTo[l - 1];

            int endCount  = nonZeroCount[r];
            int subStrLen = endCount - startCount;

            if (subStrLen == 0) {
                result[i] = 0;
                continue;
            }

            long x   = (numberUpTo[r] - (numBefore * pow10[subStrLen] % MOD) + MOD) % MOD;
            long sum = digitSumUpTo[r] - ((l == 0) ? 0 : digitSumUpTo[l - 1]);

            result[i] = (int) ((x * sum) % MOD);
        }

        return result;
    }
}
