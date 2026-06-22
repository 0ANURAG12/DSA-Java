/**
# LeetCode 211. Design Add and Search Words Data Structure

**Problem Link:** https://leetcode.com/problems/design-add-and-search-words-data-structure/

## Approach 1: Trie + DFS

Use a Trie to store all inserted words.

Operations:

1. addWord()
   - Insert characters into the Trie.
   - Mark the last node as a complete word.

2. search()
   - For normal characters, move to the corresponding child node.
   - For '.', recursively explore all possible child nodes.
   - Return true if any path forms a valid word.

The wildcard '.' can match any lowercase English letter.

**Time Complexity:**

* addWord(): O(L)
* search():
    * Best Case: O(L)
    * Worst Case: O(26^L)

**Space Complexity:** O(N × L)

where:

* N = Number of inserted words
* L = Length of the search word

*/
class WordDictionary {

    class TrieNode {
        TrieNode[] next = new TrieNode[26];
        boolean isWord;
    }

    TrieNode root;

    public WordDictionary() {
        root = new TrieNode();
    }

    public void addWord(String word) {

        TrieNode node = root;

        for (char c : word.toCharArray()) {

            int idx = c - 'a';

            if (node.next[idx] == null) {
                node.next[idx] = new TrieNode();
            }

            node = node.next[idx];
        }

        node.isWord = true;
    }

    public boolean search(String word) {
        return dfs(word, 0, root);
    }

    private boolean dfs(String word, int pos, TrieNode node) {

        if (node == null) {
            return false;
        }

        if (pos == word.length()) {
            return node.isWord;
        }

        char c = word.charAt(pos);

        if (c == '.') {

            for (int i = 0; i < 26; i++) {

                if (dfs(word, pos + 1, node.next[i])) {
                    return true;
                }
            }

            return false;
        }

        return dfs(
                word,
                pos + 1,
                node.next[c - 'a']
        );
    }
}
