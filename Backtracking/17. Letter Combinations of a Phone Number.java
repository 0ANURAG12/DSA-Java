/*
 * LeetCode: 17. Letter Combinations of a Phone Number
 * https://leetcode.com/problems/letter-combinations-of-a-phone-number/
 *
 * ------------------------------------------------------------
 * Approach 1: Backtracking + HashMap
 * ------------------------------------------------------------
 * Intuition:
 * - Create a mapping from each digit (2-9) to its corresponding
 *   characters on a phone keypad.
 * - Starting from the first digit, recursively choose one character
 *   from its mapped letters.
 * - Continue building the current string until every digit has been
 *   processed.
 * - Once a character has been chosen for every digit, add the
 *   generated combination to the answer.
 *
 * Time Complexity: O(4^N × N)
 * Space Complexity: O(N)
 *
 * where:
 * N = Length of the input string
 *
 * Explanation:
 * - Each digit contributes at most 4 choices ('7' and '9').
 * - Constructing each resulting string requires O(N) time.
 * - The recursion depth is O(N).
 *
 * Alternative Approach:
 * 1. Backtracking using a String Array
 *    - Store the keypad mapping directly in a String array instead
 *      of constructing a HashMap.
 *    - This avoids HashMap lookups and is the approach commonly
 *      used in interviews and the LeetCode editorial.
 *
 * Time Complexity: O(4^N × N)
 * Space Complexity: O(N)
 */

class Solution {
    Map<Integer,StringBuilder> map;
    List<String> ans;

    public List<String> letterCombinations(String digits) {
        if (digits.isEmpty()) return ans;
        ans = new ArrayList<>();
        map = new HashMap<>();

        int ascii = 97;

        for(int i = 2; i < 10; i++){
            int end = (i == 7 || i == 9) ? 3 + ascii : 2 + ascii;

            while(ascii <= end){
                map.put(i, map.getOrDefault(i, new StringBuilder("")).append((char)ascii));
                ascii++;
            }
        }

        backtrack(0, digits, new StringBuilder(""));

        return ans;
    }

    void backtrack(int idx, String digits, StringBuilder str){
        if(idx == digits.length()){
            ans.add(str.toString());
            return;
        }

        int digit = digits.charAt(idx) - '0';

        for(char c : map.get(digit).toString().toCharArray()){
            backtrack(idx + 1, digits, str.append(c));
            str.deleteCharAt(str.length() - 1);
        }
    }
}

/*
 * ------------------------------------------------------------
 * Approach: Backtracking + Phone Keypad Mapping
 * ------------------------------------------------------------
 * Intuition:
 * - Store the characters corresponding to each digit (2-9) in a
 *   string array representing the phone keypad.
 * - Starting from the first digit, recursively choose one character
 *   from its corresponding set of letters.
 * - Append the chosen character to the current combination and
 *   continue with the next digit.
 * - Once all digits have been processed, add the completed
 *   combination to the answer.
 * - Backtrack by removing the last appended character and explore
 *   the remaining choices.
 *
 * Time Complexity: O(4^N × N)
 * Space Complexity: O(N)
 *
 * where:
 * N = Length of the input string.
 *
 * Explanation:
 * - Each digit contributes at most 4 possible characters ('7' and '9').
 * - There are at most 4^N possible combinations.
 * - Creating each output string requires O(N) time.
 * - The recursion stack depth is O(N).
 *
 * Alternative Approach:
 * 1. Backtracking + HashMap
 *    - Store the digit-to-letter mapping in a HashMap instead of a
 *      string array.
 *    - The algorithm remains the same, but array indexing is simpler
 *      and slightly more efficient than HashMap lookups.
 *
 * Time Complexity: O(4^N × N)
 * Space Complexity: O(N)
 */

class Solution {
    List<String> ans = new ArrayList<>();

    String[] map = {
        "", "", "abc", "def", "ghi",
        "jkl", "mno", "pqrs", "tuv", "wxyz"
    };

    public List<String> letterCombinations(String digits) {
        if (digits.length() == 0) return ans;

        backtrack(0, digits, new StringBuilder());

        return ans;
    }

    void backtrack(int idx, String digits, StringBuilder sb) {
        if (idx == digits.length()) {
            ans.add(sb.toString());
            return;
        }

        String letters = map[digits.charAt(idx) - '0'];

        for (char c : letters.toCharArray()) {
            sb.append(c);
            backtrack(idx + 1, digits, sb);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}
