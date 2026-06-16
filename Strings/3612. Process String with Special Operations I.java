/*
Problem: 3612. Process String with Special Operations I
Link: https://leetcode.com/problems/process-string-with-special-operations-i/

Difficulty: Medium
Topic: String

Time Complexity: O(k)
Space Complexity: O(k)

where k is the length of the final generated string.
*/

class Solution {

    public String processStr(String s) {

        StringBuilder str = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {

            char c = s.charAt(i);

            boolean notEmpty = str.length() > 0;

            if (c == '*') {

                if (notEmpty)
                    str.deleteCharAt(str.length() - 1);

            } else if (c == '#') {

                if (notEmpty)
                    str.append(str);

            } else if (c == '%') {

                if (notEmpty)
                    str.reverse();

            } else {

                str.append(c);
            }
        }

        return str.toString();
    }
}
