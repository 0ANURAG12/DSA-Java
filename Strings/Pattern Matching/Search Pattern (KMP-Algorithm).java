/*
 * GeeksforGeeks: Search Pattern (KMP-Algorithm)
 * https://www.geeksforgeeks.org/problems/search-pattern0205/1
 *
 * Topic: Strings / Pattern Matching / KMP Algorithm
 *
 *
 * ============================================================
 * APPROACH 1: Knuth-Morris-Pratt (KMP) Algorithm
 * ============================================================
 *
 * Intuition:
 * - We are given a text `txt` and a pattern `pat`.
 *
 * - We need to find every occurrence of `pat` inside `txt`
 *   and return the starting indices of all matches.
 *
 * - The KMP algorithm avoids rechecking characters that have
 *   already been matched.
 *
 * - It uses an LPS (Longest Proper Prefix which is also
 *   a Suffix) array to determine how far the pattern pointer
 *   should move after a mismatch.
 *
 * - Step 1: Build the LPS array.
 *
 *   LPS[i] stores the length of the longest proper prefix of
 *   pat[0...i] that is also a suffix of pat[0...i].
 *
 *   If characters match, increase `len` and store it in LPS.
 *
 *   If they do not match:
 *   - If len != 0, update len = LPS[len - 1].
 *   - Otherwise, store 0 and move to the next character.
 *
 * - Step 2: Search for the pattern.
 *
 *   Use `i` to traverse the text and `j` to traverse the pattern.
 *
 *   If characters match, increment both pointers.
 *
 *   If j == m, the complete pattern has been found.
 *   Add i - j to the answer and use LPS[j - 1] to continue
 *   searching for overlapping matches.
 *
 *   On a mismatch:
 *   - If j != 0, update j = LPS[j - 1].
 *   - Otherwise, move the text pointer forward.
 *
 *
 * Example:
 *
 *      txt = "ababcabcabababd"
 *      pat = "ababd"
 *
 *      Pattern found at index 10.
 *
 *      Answer = [10]
 *
 *
 * Time Complexity: O(n + m)
 * Space Complexity: O(m)
 *
 * Why O(n + m)?
 * - Building the LPS array takes O(m).
 * - Searching the text takes O(n) amortized time.
 *
 * Space:
 * - The LPS array stores m integers.
 * - The answer list stores the matching indices.
 */


class Solution {

    ArrayList<Integer> search(String pat, String txt) {

        int n = txt.length();
        int m = pat.length();

        // Build the LPS array
        int[] LPS = new int[m];
        LPS[0] = 0;

        int len = 0;
        int j = 1;

        while (j < m) {

            if (pat.charAt(j) == pat.charAt(len)) {
                len++;
                LPS[j] = len;
                j++;
            } else {

                if (len != 0) {
                    len = LPS[len - 1];
                } else {
                    LPS[j] = 0;
                    j++;
                }
            }
        }

        ArrayList<Integer> ans = new ArrayList<>();

        // Search for the pattern in the text
        int i = 0;
        j = 0;

        while (i < n) {

            if (txt.charAt(i) == pat.charAt(j)) {
                i++;
                j++;
            }

            // Complete pattern found
            if (j == m) {
                ans.add(i - j);

                // Continue searching for overlapping matches
                j = LPS[j - 1];
            } else if (i < n && pat.charAt(j) != txt.charAt(i)) {

                if (j != 0) {
                    j = LPS[j - 1];
                } else {
                    i++;
                }
            }
        }

        return ans;
    }
}
