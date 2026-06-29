/**
# LeetCode 1967. Number of Strings That Appear as Substrings in Word

**Problem Link:** https://leetcode.com/problems/number-of-strings-that-appear-as-substrings-in-word/

## Approach 1: Built-in String Search

Iterate through every string in `patterns`.

For each pattern:

- Check whether it appears as a substring of `word` using the built-in
  `contains()` method.
- If it does, increment the answer.

This approach is simple and leverages Java's optimized substring search.

**Time Complexity:** O(N × M)

**Space Complexity:** O(1)

where:

* N = Number of strings in `patterns`
* M = Length of `word`

*/
class Solution {

    public int numOfStrings(String[] patterns, String word) {

        int count = 0;

        for (String pattern : patterns) {

            if (word.contains(pattern)) {
                count++;
            }
        }

        return count;
    }
}

/*

---

## Approach 2: Trie

Insert every pattern into a Trie.

For every starting index in `word`:

- Traverse the Trie while moving forward in the string.
- Whenever a Trie node marks the end of a pattern,
  increment the answer.

This approach searches all patterns simultaneously and is more efficient
when the number of patterns is large.

**Time Complexity:** O(P + W²)

**Space Complexity:** O(P)

where:

* P = Total number of characters in all patterns
* W = Length of `word`

*/

class Solution {

    class TrieNode {
        TrieNode[] next = new TrieNode[26];
        int count;
    }

    public int numOfStrings(String[] patterns, String word) {

        TrieNode root = new TrieNode();

        for (String pattern : patterns) {

            TrieNode node = root;

            for (char c : pattern.toCharArray()) {

                int idx = c - 'a';

                if (node.next[idx] == null) {
                    node.next[idx] = new TrieNode();
                }

                node = node.next[idx];
            }

            node.count++;
        }

        int ans = 0;

        for (int i = 0; i < word.length(); i++) {

            TrieNode node = root;

            for (int j = i; j < word.length(); j++) {

                int idx = word.charAt(j) - 'a';

                if (node.next[idx] == null) {
                    break;
                }

                node = node.next[idx];

                ans += node.count;

                // Prevent counting the same pattern multiple times
                node.count = 0;
            }
        }

        return ans;
    }
}
