/**
# LeetCode 208. Implement Trie (Prefix Tree)

**Problem Link:** https://leetcode.com/problems/implement-trie-prefix-tree/

## Approach 1: Trie (Prefix Tree)

A Trie is a tree-like data structure used for efficient storage and retrieval
of strings.

Operations:

1. Insert:
   - Traverse characters of the word.
   - Create nodes if they do not exist.
   - Mark the last node as a complete word.

2. Search:
   - Traverse characters of the word.
   - If any character path is missing, return false.
   - Return true only if the last node is marked as a complete word.

3. StartsWith:
   - Traverse characters of the prefix.
   - If all characters exist, return true.

**Time Complexity:**

* Insert: O(L)
* Search: O(L)
* StartsWith: O(L)

**Space Complexity:** O(N × L)

where:

* L = Length of the input word/prefix
* N = Number of inserted words

*/
class Trie {

    class TrieNode {

        TrieNode[] next;
        boolean isWord;

        TrieNode() {
            next = new TrieNode[26];
        }
    }

    TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    public void insert(String word) {

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

        TrieNode node = root;

        for (char c : word.toCharArray()) {

            int idx = c - 'a';

            if (node.next[idx] == null) {
                return false;
            }

            node = node.next[idx];
        }

        return node.isWord;
    }

    public boolean startsWith(String prefix) {

        TrieNode node = root;

        for (char c : prefix.toCharArray()) {

            int idx = c - 'a';

            if (node.next[idx] == null) {
                return false;
            }

            node = node.next[idx];
        }

        return true;
    }
}
