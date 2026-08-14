/*
 * LeetCode 3090. Maximum Length Substring With Two Occurrences
 * https://leetcode.com/problems/maximum-length-substring-with-two-occurrences/
 *
 * Approach:
 * - Brute Force
 * - Consider every index as the starting point of a substring.
 * - Maintain a frequency array of size 26 while extending the substring.
 * - If any character appears more than twice, stop extending that substring.
 * - Otherwise, update the maximum valid substring length.
 *
 * Time Complexity: O(n²)
 * Space Complexity: O(1)
 */

class Solution {

    public int maximumLengthSubstring(String s) {

        int n = s.length();
        int maxLength = 1;

        for (int i = 0; i < n; i++) {

            int[] freq = new int[26];
            int length = 0;

            for (int j = i; j < n; j++) {

                int index = s.charAt(j) - 'a';
                freq[index]++;
                length++;

                if (freq[index] <= 2) {
                    maxLength = Math.max(maxLength, length);
                } else {
                    break;
                }
            }
        }

        return maxLength;
    }
}
