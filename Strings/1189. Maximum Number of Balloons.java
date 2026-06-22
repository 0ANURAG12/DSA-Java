/**
# LeetCode 1189. Maximum Number of Balloons

**Problem Link:** https://leetcode.com/problems/maximum-number-of-balloons/

## Approach 1: Frequency Counting

Count the frequency of every character in the string.

The word "balloon" requires:

* b → 1
* a → 1
* l → 2
* o → 2
* n → 1

The maximum number of times we can form "balloon" is determined by the
minimum available count among the required characters.

**Time Complexity:** O(N)

**Space Complexity:** O(1)

where:

* N = Length of the input string

*/
class Solution {

    public int maxNumberOfBalloons(String text) {

        int[] freq = new int[26];

        for (char c : text.toCharArray()) {
            freq[c - 'a']++;
        }

        int b = freq['b' - 'a'];
        int a = freq['a' - 'a'];
        int l = freq['l' - 'a'] / 2;
        int o = freq['o' - 'a'] / 2;
        int n = freq['n' - 'a'];

        return Math.min(
                Math.min(b, a),
                Math.min(
                        Math.min(l, o),
                        n
                )
        );
    }
}

/*

---

## Approach 2: Direct Frequency Check

Store the required frequencies of the characters in "balloon"
and compute the limiting character.

**Time Complexity:** O(N)

**Space Complexity:** O(1)

where:

* N = Length of the input string

*/

class Solution {

    public int maxNumberOfBalloons(String text) {

        int[] freq = new int[26];

        for (char c : text.toCharArray()) {
            freq[c - 'a']++;
        }

        int ans = Integer.MAX_VALUE;

        ans = Math.min(ans, freq['b' - 'a']);
        ans = Math.min(ans, freq['a' - 'a']);
        ans = Math.min(ans, freq['l' - 'a'] / 2);
        ans = Math.min(ans, freq['o' - 'a'] / 2);
        ans = Math.min(ans, freq['n' - 'a']);

        return ans;
    }
}
