/*
 * LeetCode: 1081. Smallest Subsequence of Distinct Characters
 * https://leetcode.com/problems/smallest-subsequence-of-distinct-characters/
 *
 * ------------------------------------------------------------
 * Approach: Greedy + Monotonic Stack
 * ------------------------------------------------------------
 * Intuition:
 * - Record the last occurrence of every character in the string.
 * - Traverse the string from left to right.
 * - If a character is already included in the answer, skip it.
 * - Otherwise, while the current character is lexicographically smaller
 *   than the stack's top and the top character appears again later,
 *   remove the top character to obtain a smaller subsequence.
 * - Push the current character and mark it as visited.
 * - The resulting stack forms the lexicographically smallest subsequence
 *   containing every distinct character exactly once.
 *
 * Time Complexity: O(N)
 * Space Complexity: O(1)
 *
 * where:
 * N = Length of the input string.
 *
 * Explanation:
 * - Each character is pushed onto the stack at most once and popped
 *   at most once.
 * - The auxiliary arrays (lastOccurrence and visited) have fixed size 26,
 *   so they require constant extra space.
 *
 * Alternative Approach:
 * 1. Stack + Frequency Count
 *    - Maintain the remaining frequency of each character instead of
 *      storing its last occurrence.
 *    - Decrease the frequency while traversing and pop characters only
 *      if they still appear later (frequency > 0).
 *    - Time Complexity: O(N)
 *    - Space Complexity: O(1)
 */
class Solution {
    public String smallestSubsequence(String s) {
        int n = s.length();
        StringBuilder str = new StringBuilder("");

        int[] lastOccurence = new int[26];
        boolean[] visited = new boolean[26];

        Arrays.fill(lastOccurence,-1);

        for(int i = 0;i<n;i++) lastOccurence[s.charAt(i)-'a'] = i;

        for(int i = 0;i<n;i++){
            char c = s.charAt(i);
            int idx = c-'a';

            if(visited[idx]) continue;

            while(str.length()>0 && c<str.charAt(str.length()-1) && lastOccurence[str.charAt(str.length()-1)-'a']>i){
                visited[str.charAt(str.length()-1)-'a'] = false;
                str.deleteCharAt(str.length()-1);
            }

            str.append(c);
            visited[idx] = true;
        }
        return str.toString();
    }
}
