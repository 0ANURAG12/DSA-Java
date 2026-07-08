/*
 * LeetCode: 131. Palindrome Partitioning
 * https://leetcode.com/problems/palindrome-partitioning/
 *
 * ------------------------------------------------------------
 * Approach: Backtracking + Palindrome Checking
 * ------------------------------------------------------------
 * Intuition:
 * - Start from index 0 and recursively partition the string.
 * - At each position, try every possible substring starting from the
 *   current index.
 * - If the substring is a palindrome, include it in the current partition
 *   and recursively process the remaining suffix.
 * - Once the end of the string is reached, a valid palindrome partition
 *   has been formed, so add it to the result.
 * - Backtrack by removing the last chosen substring and continue exploring
 *   other possible partitions.
 *
 * Time Complexity: O(N × 2^N)
 * Space Complexity: O(N)
 *
 * where:
 * N = Length of the input string
 *
 * Explanation:
 * - There are O(2^N) possible partition combinations in the worst case.
 * - Checking whether each substring is a palindrome takes O(N).
 * - The recursion stack and current partition require O(N) extra space.
 *
 * Alternative Approach:
 * 1. Backtracking + Dynamic Programming
 *    - Precompute whether every substring is a palindrome using a DP table.
 *    - During backtracking, palindrome checks become O(1) instead of O(N).
 *
 * Time Complexity: O(N × 2^N)
 * Space Complexity: O(N²)
 */

class Solution {
    List<List<String>> result;

    public List<List<String>> partition(String s) {
        result = new ArrayList<>();
        backtracking(s, 0, new ArrayList<>());
        return result;
    }

    void backtracking(String s, int idx, ArrayList<String> list) {
        if (idx == s.length()) {
            result.add(new ArrayList(list));
            return;
        }

        for (int i = idx; i < s.length(); i++) {
            if (isPalindrome(s, idx, i)) {
                list.add(s.substring(idx, i + 1));
                backtracking(s, i + 1, list);
                list.remove(list.size() - 1);
            }
        }
    }

    boolean isPalindrome(String s, int i, int j) {
        while (i < j) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }
}
/*
 * ------------------------------------------------------------
 * Approach 2: Backtracking + Dynamic Programming
 * ------------------------------------------------------------
 * Intuition:
 * - Precompute whether every substring is a palindrome using a DP table.
 * - dp[i][j] is true if the substring s[i...j] is a palindrome.
 * - During backtracking, simply check dp[idx][i] in O(1) instead of
 *   checking the substring every time.
 * - This avoids repeated palindrome computations and improves efficiency.
 *
 * Time Complexity: O(N × 2^N)
 * Space Complexity: O(N²)
 *
 * where:
 * N = Length of the input string
 */

class Solution {
    List<List<String>> result = new ArrayList<>();
    boolean[][] dp;

    public List<List<String>> partition(String s) {
        int n = s.length();
        dp = new boolean[n][n];

        // Build palindrome DP table
        for (int len = 1; len <= n; len++) {
            for (int i = 0; i + len - 1 < n; i++) {
                int j = i + len - 1;

                if (s.charAt(i) == s.charAt(j)) {
                    if (len <= 2 || dp[i + 1][j - 1]) {
                        dp[i][j] = true;
                    }
                }
            }
        }

        backtrack(s, 0, new ArrayList<>());

        return result;
    }

    void backtrack(String s, int idx, List<String> current) {
        if (idx == s.length()) {
            result.add(new ArrayList<>(current));
            return;
        }

        for (int i = idx; i < s.length(); i++) {
            if (dp[idx][i]) {
                current.add(s.substring(idx, i + 1));
                backtrack(s, i + 1, current);
                current.remove(current.size() - 1);
            }
        }
    }
}
