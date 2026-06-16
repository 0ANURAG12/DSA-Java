/*
Problem: 646. Maximum Length of Pair Chain
Link: https://leetcode.com/problems/maximum-length-of-pair-chain/

Difficulty: Medium
Topic: Greedy
--------------------------------------------------
Approach 1: Dynamic Programming
Time Complexity: O(n²)
Space Complexity: O(n²)

*/
class Solution {

    int[][] dp;

    public int findLongestChain(int[][] pairs) {

        Arrays.sort(pairs, (a, b) -> Integer.compare(a[0], b[0]));

        int n = pairs.length;

        dp = new int[n][n + 1];

        for (int[] row : dp)
            Arrays.fill(row, -1);

        return solve(pairs, 0, -1);
    }

    private int solve(int[][] pairs, int i, int prev) {

        if (i == pairs.length)
            return 0;

        if (dp[i][prev + 1] != -1)
            return dp[i][prev + 1];

        int skip = solve(pairs, i + 1, prev);

        int take = 0;

        if (prev == -1 || pairs[prev][1] < pairs[i][0]) {
            take = 1 + solve(pairs, i + 1, i);
        }

        return dp[i][prev + 1] = Math.max(take, skip);
    }
}
/*
--------------------------------------------------
Approach 2: Greedy (Optimal)
Time Complexity: O(n log n)
Space Complexity: O(1)

--------------------------------------------------
*/

class Solution {

    public int findLongestChain(int[][] pairs) {

        Arrays.sort(pairs,
                (a, b) -> Integer.compare(a[1], b[1]));

        int count = 0;
        int currentEnd = Integer.MIN_VALUE;

        for (int[] p : pairs) {

            if (currentEnd < p[0]) {

                count++;
                currentEnd = p[1];
            }
        }

        return count;
    }
}
