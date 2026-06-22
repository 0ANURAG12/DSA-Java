/**
# LeetCode 2287. Rearrange Characters to Make Target String

**Problem Link:** https://leetcode.com/problems/rearrange-characters-to-make-target-string/

## Approach 1: Frequency Counting

Count the frequency of each character in:

- Source string `s`
- Target string `target`

For every character required in `target`:

- Determine how many times it can be formed using:
  freqS[i] / freqT[i]

The minimum value among all required characters is the maximum number
of copies of `target` that can be formed.

**Time Complexity:** O(N + M)

**Space Complexity:** O(1)

where:

* N = Length of string `s`
* M = Length of string `target`

*/
class Solution {

    public int rearrangeCharacters(String s, String target) {

        int[] freqS = new int[26];
        int[] freqT = new int[26];

        for (char c : s.toCharArray()) {
            freqS[c - 'a']++;
        }

        for (char c : target.toCharArray()) {
            freqT[c - 'a']++;
        }

        int ans = Integer.MAX_VALUE;

        for (int i = 0; i < 26; i++) {

            if (freqT[i] > 0) {
                ans = Math.min(
                        ans,
                        freqS[i] / freqT[i]
                );
            }
        }

        return ans;
    }
}

/*

---

## Approach 2: HashMap Frequency Counting

Use HashMaps instead of fixed-size arrays to store character frequencies.

**Time Complexity:** O(N + M)

**Space Complexity:** O(K)

where:

* N = Length of string `s`
* M = Length of string `target`
* K = Number of distinct characters

*/

import java.util.HashMap;
import java.util.Map;

class Solution {

    public int rearrangeCharacters(String s, String target) {

        Map<Character, Integer> source = new HashMap<>();
        Map<Character, Integer> need = new HashMap<>();

        for (char c : s.toCharArray()) {
            source.put(c, source.getOrDefault(c, 0) + 1);
        }

        for (char c : target.toCharArray()) {
            need.put(c, need.getOrDefault(c, 0) + 1);
        }

        int ans = Integer.MAX_VALUE;

        for (char c : need.keySet()) {

            ans = Math.min(
                    ans,
                    source.getOrDefault(c, 0) / need.get(c)
            );
        }

        return ans;
    }
}
