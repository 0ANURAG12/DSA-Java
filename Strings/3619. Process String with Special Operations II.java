/**
 * LeetCode 3619. Process String with Special Operations II
 * https://leetcode.com/problems/process-string-with-special-operations-ii/
 *
 * Tags:
 * String
 * Simulation
 * Reverse Traversal
 * Index Mapping
 *
 * Thought Process:
 * A brute-force solution would build the final string after processing all
 * operations and then return the kth character. However, the '#' operation
 * duplicates the current string, causing exponential growth in size.
 *
 * Instead of constructing the string, we only track its final length.
 * After computing the length, we traverse the operations in reverse and
 * undo their effects to determine where the kth character originated from.
 *
 * - Character  -> length decreases by 1
 * - '*'        -> undo deletion, length increases by 1
 * - '%'        -> reverse index mapping: k = length - k - 1
 * - '#'        -> map k back to the corresponding position in the first half
 *
 * By tracing the index backwards, we find the answer without ever building
 * the actual string.
 *
 * Time Complexity: O(N)
 * N = Length of the input string.
 *
 * Space Complexity: O(1)
 */

class Solution {
    public char processStr(String s, long k) {
        int n = s.length();

        // Calculate the final length after all operations
        long l = 0;

        for (char c : s.toCharArray()) {
            if (c == '*') {
                // Remove the last character if possible
                if (l > 0) l--;
            } else if (c == '#') {
                // Duplicate the current string
                l *= 2;
            } else if (c == '%') {
                // Reverse operation does not change length
                continue;
            } else {
                // Append a character
                l++;
            }
        }

        // k is outside the final string length
        if (k >= l) return '.';

        // Traverse backwards to find the kth character
        for (int i = n - 1; i >= 0; i--) {

            if (s.charAt(i) == '*') {
                l++;
            } else if (s.charAt(i) == '%') {
                // Reverse mapping
                k = l - k - 1;
            } else if (s.charAt(i) == '#') {
                // Map k to the corresponding position
                // in the first half of the duplicated string
                l = l / 2;
                k = (k >= l) ? k - l : k;
            } else {
                l--;
            }

            // Found the character responsible for position k
            if (k == l) return s.charAt(i);
        }

        return '.';
    }
}
