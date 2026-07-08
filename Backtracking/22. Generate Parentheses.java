/*
 * LeetCode: 22. Generate Parentheses
 * https://leetcode.com/problems/generate-parentheses/
 *
 * ------------------------------------------------------------
 * Approach 1: Brute Force Backtracking
 * ------------------------------------------------------------
 * Intuition:
 * - Generate every possible string of length 2 * n by recursively
 *   choosing either '(' or ')' at each position.
 * - Once a string of length 2 * n is formed, validate whether it
 *   represents a balanced parentheses sequence.
 * - Add only the valid sequences to the answer.
 *
 * Time Complexity: O(2^(2N) × N)
 * Space Complexity: O(N)
 *
 * where:
 * N = Number of pairs of parentheses
 *
 * Explanation:
 * - There are 2^(2N) possible strings.
 * - Each generated string is validated in O(N) time.
 * - The recursion depth and validation stack require O(N) space.
 *
 * Alternative Approach:
 * 1. Backtracking with Pruning (Optimal)
 *    - Maintain the number of opening and closing parentheses used.
 *    - Add '(' only if open < n.
 *    - Add ')' only if close < open.
 *    - This generates only valid parentheses sequences instead of
 *      generating all possible strings.
 *
 * Time Complexity: O(4^N / √N)
 * Space Complexity: O(N)
 */

class Solution {
    List<String> list;
    int n;

    public List<String> generateParenthesis(int n) {
        list = new ArrayList<>();
        this.n = n;
        backtrack(0, new StringBuilder(""));
        return list;
    }

    void backtrack(int i, StringBuilder str) {
        if (i == 2 * n) {
            if (isValid(str)) {
                list.add(str.toString());
            }
            return;
        }

        // open
        str.append("(");
        backtrack(i + 1, str);
        str.deleteCharAt(str.length() - 1);

        // close
        str.append(")");
        backtrack(i + 1, str);
        str.deleteCharAt(str.length() - 1);
    }

    boolean isValid(StringBuilder str) {
        Stack<Integer> stk = new Stack<>();

        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '(') {
                stk.push(i);
            } else {
                if (!stk.isEmpty()) {
                    stk.pop();
                } else {
                    return false;
                }
            }
        }

        return stk.isEmpty();
    }
}
/*
 * ------------------------------------------------------------
 * Approach 2: Backtracking with Pruning
 * ------------------------------------------------------------
 * Intuition:
 * - Build the answer incrementally.
 * - Add '(' only if fewer than n opening parentheses have been used.
 * - Add ')' only if the number of closing parentheses used is less
 *   than the number of opening parentheses.
 * - Since every partial string is guaranteed to remain valid,
 *   no validation step is required.
 *
 * Time Complexity: O(4^N / √N)
 * Space Complexity: O(N)
 */

class Solution {
    List<String> ans = new ArrayList<>();

    public List<String> generateParenthesis(int n) {
        backtrack(new StringBuilder(), 0, 0, n);
        return ans;
    }

    private void backtrack(StringBuilder sb, int open, int close, int n) {
        if (sb.length() == 2 * n) {
            ans.add(sb.toString());
            return;
        }

        if (open < n) {
            sb.append('(');
            backtrack(sb, open + 1, close, n);
            sb.deleteCharAt(sb.length() - 1);
        }

        if (close < open) {
            sb.append(')');
            backtrack(sb, open, close + 1, n);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}
