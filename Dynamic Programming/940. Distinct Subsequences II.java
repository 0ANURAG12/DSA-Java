/*
 * LeetCode: 940. Distinct Subsequences II
 * https://leetcode.com/problems/distinct-subsequences-ii/
 *
 * Topic: Dynamic Programming / String DP
 *
 *
 * ============================================================
 * APPROACH 1: Top-Down DP + Previous Occurrence
 * ============================================================
 *
 * Intuition:
 * - Let solve(i) represent the number of distinct subsequences
 *   that can be formed using the first i characters.
 *
 * - For every new character, every existing subsequence can:
 *
 *      1. Exclude the current character.
 *      2. Include the current character.
 *
 * - Therefore, without considering duplicates:
 *
 *      solve(i) = 2 * solve(i - 1)
 *
 * - However, if the current character has appeared before,
 *   some subsequences will be counted twice.
 *
 * - prev[i] stores the previous occurrence of the character
 *   at index i.
 *
 * - If the character appeared previously at prev[i], the
 *   duplicate subsequences are exactly:
 *
 *      solve(prev[i] - 1)
 *
 * - Therefore:
 *
 *      solve(i) = 2 * solve(i - 1)
 *                 - solve(prev[i] - 1)
 *
 * - solve(0) = 1 represents the empty subsequence.
 * - Since the problem asks for non-empty subsequences, subtract
 *   1 from the final answer.
 *
 *
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 *
 * Why O(n)?
 * - Building the previous occurrence array: O(n)
 * - Each DP state is calculated once because of memoization.
 * - Each state performs O(1) work.
 *
 * Space:
 * - dp array: O(n)
 * - prev array: O(n)
 * - Recursion stack: O(n)
 *
 * Overall: O(n)
 */


class TopDownDP {

    int M = 1000000007;

    int[] dp = new int[2001];
    int[] prev;

    public int distinctSubseqII(String s) {

        int n = s.length();

        Arrays.fill(dp, -1);

        prev = new int[n + 1];

        int[] lastSeen = new int[26];

        // Store the previous occurrence of every character
        for (int i = 1; i <= n; i++) {

            int idx = s.charAt(i - 1) - 'a';

            prev[i] = lastSeen[idx];

            lastSeen[idx] = i;
        }

        // Subtract 1 to exclude the empty subsequence
        return (solve(n) - 1 + M) % M;
    }

    int solve(int n) {

        // Empty string has one subsequence: ""
        if (n == 0) {
            return 1;
        }

        // Already calculated
        if (dp[n] != -1) {
            return dp[n];
        }

        // Include or exclude the current character
        int total = (int) (2L * solve(n - 1) % M);

        // Remove duplicate subsequences
        if (prev[n] != 0) {

            int duplicates = solve(prev[n] - 1);

            total = (total - duplicates + M) % M;
        }

        return dp[n] = total;
    }
}


/*
 * ============================================================
 * APPROACH 2: Bottom-Up DP + Previous Occurrence
 * ============================================================
 *
 * Intuition:
 * - We can convert the recursive DP into an iterative DP.
 *
 * - dp[i] represents the number of distinct subsequences
 *   (including the empty subsequence) using the first i
 *   characters.
 *
 * - For every character:
 *
 *      dp[i] = 2 * dp[i - 1]
 *
 * - If this character appeared before at position prev[i],
 *   the subsequences created from before that occurrence were
 *   already counted.
 *
 * - Therefore, remove:
 *
 *      dp[prev[i] - 1]
 *
 * - The recurrence becomes:
 *
 *      dp[i] = 2 * dp[i - 1]
 *              - dp[prev[i] - 1]
 *
 * - Finally, subtract 1 to remove the empty subsequence.
 *
 *
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 */


class BottomUpDP {

    public int distinctSubseqII(String s) {

        int M = 1000000007;
        int n = s.length();

        int[] dp = new int[n + 1];
        int[] lastSeen = new int[26];

        // Empty subsequence
        dp[0] = 1;

        for (int i = 1; i <= n; i++) {

            int idx = s.charAt(i - 1) - 'a';

            // Double the previous number of subsequences
            dp[i] = (int) (2L * dp[i - 1] % M);

            // Remove duplicates caused by this character
            if (lastSeen[idx] != 0) {

                dp[i] = (dp[i] - dp[lastSeen[idx] - 1] + M) % M;
            }

            // Update last occurrence
            lastSeen[idx] = i;
        }

        // Remove the empty subsequence
        return (dp[n] - 1 + M) % M;
    }
}


/*
 * ============================================================
 * APPROACH 3: DP by Ending Character
 * ============================================================
 *
 * Intuition:
 * - Since the string contains only lowercase English letters,
 *   we can maintain the number of distinct subsequences ending
 *   with each character.
 *
 * - end[c] represents the number of distinct subsequences
 *   whose last character is c.
 *
 * - When we process a new character c:
 *
 *      end[c] = total + 1
 *
 *   where:
 *
 *      total = sum of end[0...25]
 *
 * - The +1 represents the subsequence consisting only of c.
 *
 * - Replacing end[c] is important because all subsequences
 *   ending with c created by the previous occurrence are now
 *   represented by the new set.
 *
 * - At the end, summing all 26 values gives the number of
 *   distinct non-empty subsequences.
 *
 *
 * Time Complexity: O(26 × n) = O(n)
 * Space Complexity: O(26) = O(1)
 *
 * Since the alphabet size is fixed at 26, O(26n) simplifies
 * to O(n).
 */


class EndingCharacterDP {

    static final int M = 1000000007;

    public int distinctSubseqII(String s) {

        int[] end = new int[26];

        for (char c : s) {

            int idx = c - 'a';

            int total = 0;

            // Count all currently known subsequences
            for (int value : end) {
                total = (total + value) % M;
            }

            // Every existing subsequence can be extended
            // with the current character, plus the character
            // itself as a new subsequence.
            end[idx] = (total + 1) % M;
        }

        int answer = 0;

        // Sum subsequences ending with each character
        for (int value : end) {
            answer = (answer + value) % M;
        }

        return answer;
    }
}
