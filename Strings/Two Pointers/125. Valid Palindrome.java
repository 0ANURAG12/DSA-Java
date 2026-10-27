/*
 * LeetCode: 125. Valid Palindrome
 * https://leetcode.com/problems/valid-palindrome/
 *
 * Topic: Strings / Two Pointers
 *
 *
 * ============================================================
 * APPROACH 1: Two Pointers
 * ============================================================
 *
 * Intuition:
 * - A string is a palindrome if it reads the same forward
 *   and backward, ignoring non-alphanumeric characters
 *   and differences in letter case.
 *
 * - Initialize two pointers:
 *
 *       l = 0
 *       r = s.length() - 1
 *
 * - Move the left pointer forward and the right pointer
 *   backward until both point to alphanumeric characters.
 *
 * - Use Character.isLetterOrDigit() to skip spaces,
 *   punctuation, and other non-alphanumeric characters.
 *
 * - Compare the characters after converting them to lowercase.
 *
 * - If the characters differ, the string is not a palindrome,
 *   so return false.
 *
 * - If they match, move both pointers toward the center.
 *
 * - If the pointers meet or cross without finding a mismatch,
 *   return true.
 *
 *
 * Example:
 *
 *      s = "A man, a plan, a canal: Panama"
 *
 *      Ignoring non-alphanumeric characters and case:
 *
 *      "amanaplanacanalpanama"
 *
 *      The string reads the same forward and backward.
 *
 *      Answer = true
 *
 *
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 *
 * Why O(n)?
 * - Each pointer moves toward the center.
 * - Every character is examined at most a constant number
 *   of times.
 *
 * Space:
 * - Only two pointers and temporary character values are used.
 */


class Solution {

    public boolean isPalindrome(String s) {

        int l = 0;
        int r = s.length() - 1;

        while (l < r) {

            // Skip non-alphanumeric characters from the left
            if (!Character.isLetterOrDigit(s.charAt(l))) {
                l++;
                continue;
            }

            // Skip non-alphanumeric characters from the right
            else if (!Character.isLetterOrDigit(s.charAt(r))) {
                r--;
                continue;
            }

            // Compare characters without case sensitivity
            else if (Character.toLowerCase(s.charAt(l)) ==
                     Character.toLowerCase(s.charAt(r))) {

                l++;
                r--;
            }

            // Mismatch means the string is not a palindrome
            else {
                return false;
            }
        }

        return true;
    }
}
