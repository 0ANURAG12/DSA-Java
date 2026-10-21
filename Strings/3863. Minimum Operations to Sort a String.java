/*
 * LeetCode: 3863. Minimum Operations to Sort a String
 * https://leetcode.com/problems/minimum-operations-to-sort-a-string/
 *
 * Topic: Strings / Greedy / Case Analysis
 *
 *
 * ============================================================
 * APPROACH 1: Greedy + Case Analysis
 * ============================================================
 *
 * Intuition:
 * - In one operation, we can sort any proper substring,
 *   but we cannot sort the entire string.
 *
 * - If the string is already sorted, no operation is needed.
 *
 * - If the string has length 2 and is not sorted, it is
 *   impossible to sort because the only substring containing
 *   both characters is the entire string.
 *
 * - Find the minimum character and maximum character.
 *
 * - If the first character is the minimum character, or the
 *   last character is the maximum character, one operation
 *   is sufficient.
 *
 * - Otherwise, check the middle characters:
 *
 *   1. If the middle contains the minimum or maximum character,
 *      the answer is 2.
 *
 *   2. If neither extreme character appears in the middle,
 *      the answer is 3.
 *
 * - These cases determine the minimum number of operations
 *   without actually sorting substrings.
 *
 *
 * Example:
 *
 *      s = "card"
 *
 *      Minimum character = 'a'
 *      Maximum character = 'r'
 *
 *      The string is not sorted.
 *      Length = 4, so sorting is possible.
 *
 *      First character = 'c' != 'a'
 *      Last character  = 'd' != 'r'
 *
 *      Middle characters = 'a', 'r'
 *
 *      The middle contains the minimum and maximum characters.
 *      Therefore, the answer is 2.
 *
 *
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 *
 * Why O(n)?
 * - Checking whether the string is sorted takes O(n).
 * - Finding the minimum and maximum characters takes O(n).
 * - Finding the minimum and maximum middle characters takes O(n).
 *
 * Space:
 * - The character array stores n characters.
 */


class Solution {

    public int minOperations(String s) {

        int n = s.length();
        char[] arr = s.toCharArray();

        // Check whether the string is already sorted
        boolean sorted = true;

        for (int i = 1; i < n; i++) {
            if (arr[i] < arr[i - 1]) {
                sorted = false;
                break;
            }
        }

        if (sorted) {
            return 0;
        }

        // A reversed string of length 2 cannot be sorted
        // because the entire string cannot be selected
        if (n == 2) {
            return -1;
        }

        // Find the minimum and maximum characters
        char minChar = arr[0];
        char maxChar = arr[0];

        for (int i = 1; i < n; i++) {
            minChar = (char) Math.min(minChar, arr[i]);
            maxChar = (char) Math.max(maxChar, arr[i]);
        }

        // If an extreme character is already at the correct end,
        // one operation is sufficient
        if (arr[0] == minChar || arr[n - 1] == maxChar) {
            return 1;
        }

        // Find the minimum and maximum characters in the middle
        char minMid = arr[1];
        char maxMid = arr[1];

        for (int i = 2; i < n - 1; i++) {
            minMid = (char) Math.min(minMid, arr[i]);
            maxMid = (char) Math.max(maxMid, arr[i]);
        }

        // If the first character is greater than every middle
        // character and the last is smaller than every middle
        // character, three operations are required
        if (arr[0] > maxMid && arr[n - 1] < minMid) {
            return 3;
        }

        // Otherwise, two operations are sufficient
        return 2;
    }
}
