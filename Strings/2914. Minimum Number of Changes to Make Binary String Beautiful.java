/*
 * LeetCode: 2914. Minimum Number of Changes to Make Binary String Beautiful
 * https://leetcode.com/problems/minimum-number-of-changes-to-make-binary-string-beautiful/
 *
 * Topic: Strings / Greedy / Two Pointers
 *
 * ============================================================
 * APPROACH 1: Greedy (Process Adjacent Pairs)
 * ============================================================
 *
 * Intuition:
 * - A beautiful binary string must have an even length.
 * - Every substring of length 2 must contain identical characters.
 * - Therefore, the string can be divided into non-overlapping pairs:
 *   (0, 1), (2, 3), (4, 5), ...
 *
 * - For each pair:
 *   - If both characters are equal, no change is required.
 *   - If the characters are different, exactly one change is needed
 *     to make both characters equal.
 *
 * - Count the number of pairs containing different characters.
 * - This count gives the minimum number of changes required.
 *
 * Example:
 * Input: s = "1001"
 *
 * Pairs:
 * (1, 0) -> Different -> 1 change
 * (0, 1) -> Different -> 1 change
 *
 * Output: 2
 *
 * Time Complexity: O(n)
 * - We traverse the string once, processing every two characters.
 *
 * Space Complexity: O(1)
 * - Only a counter and the string length variable are used.
 *
 */

class Solution {
    public int minChanges(String s) {
        int n = s.length();
        int count = 0;

        // Process the string in non-overlapping pairs.
        for (int i = 0; i + 1 < n; i += 2) {

            // If the two characters are different,
            // one character must be changed.
            if (s.charAt(i) - '0' != s.charAt(i + 1) - '0') {
                count++;
            }
        }

        // Return the minimum number of changes required.
        return count;
    }
}
