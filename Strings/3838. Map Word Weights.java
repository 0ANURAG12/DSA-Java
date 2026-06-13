/*
Problem: 3838. Map Word Weights
Link: https://leetcode.com/problems/map-word-weights/

Difficulty: Easy
Topic: String

Time Complexity: O(T)
Space Complexity: O(1)

where T = total number of characters across all words.
*/

class Solution {

    public String mapWordWeights(String[] words, int[] weights) {

        StringBuilder str = new StringBuilder();

        for (String s : words) {

            int sum = 0;

            for (char c : s.toCharArray()) {
                sum += weights[c - 'a'];
            }

            sum = 25 - sum % 26;

            str.append((char) ('a' + sum));
        }

        return str.toString();
    }
}
