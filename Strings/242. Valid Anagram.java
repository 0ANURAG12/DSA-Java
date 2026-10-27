/*
 * LeetCode: 242. Valid Anagram
 * https://leetcode.com/problems/valid-anagram/
 *
 * Topic: Strings / Arrays / Frequency Counting
 *
 *
 * ============================================================
 * APPROACH 1: Character Frequency Counting
 * ============================================================
 *
 * Intuition:
 * - Two strings are anagrams if they contain the same
 *   characters with exactly the same frequencies.
 *
 * - Create an integer array of size 26 to store the frequency
 *   of each lowercase English letter.
 *
 * - Traverse string s and increment the frequency of each
 *   character.
 *
 * - Traverse string t and decrement the frequency of each
 *   character.
 *
 * - If both strings are anagrams, every frequency must
 *   eventually become zero.
 *
 * - If any frequency is non-zero, the strings are not anagrams.
 *
 * - If all frequencies are zero, return true.
 *
 *
 * Example:
 *
 *      s = "anagram"
 *      t = "nagaram"
 *
 *      After processing s:
 *      Frequencies of its characters are incremented.
 *
 *      After processing t:
 *      The corresponding frequencies are decremented.
 *
 *      All frequencies = 0
 *
 *      Answer = true
 *
 *
 * Time Complexity: O(n + m)
 * Space Complexity: O(1)
 *
 * Where:
 * - n = Length of s
 * - m = Length of t
 *
 * Why O(n + m)?
 * - We traverse both strings once.
 * - Checking all 26 frequency entries takes constant time.
 *
 * Space:
 * - The frequency array always contains 26 integers.
 */


class Solution {

    public boolean isAnagram(String s, String t) {

        int[] count = new int[26];

        // Increment frequencies for characters in s
        for (char c : s.toCharArray()) {
            int idx = c - 'a';
            count[idx]++;
        }

        // Decrement frequencies for characters in t
        for (char c : t.toCharArray()) {
            int idx = c - 'a';
            count[idx]--;
        }

        // All frequencies must be zero for anagrams
        for (int n : count) {
            if (n != 0) {
                return false;
            }
        }

        return true;
    }
}
