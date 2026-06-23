/**
# LeetCode 212. Word Search II

**Problem Link:** https://leetcode.com/problems/word-search-ii/

## Approach 1: Trie + DFS Backtracking (Optimized)

Build a Trie containing all words.

For every cell in the board:

- Start a DFS search.
- Follow Trie paths while traversing the board.
- Stop immediately if the current character does not exist in the Trie.
- Store the complete word at the terminal Trie node.
- When a word is found, add it directly to the answer.
- Mark visited cells using '#' and backtrack after exploration.

This avoids repeated StringBuilder operations and duplicate results.

**Time Complexity:** O(M × N × 4^L)

**Space Complexity:** O(W × L)

where:

* M = Number of rows in the board
* N = Number of columns in the board
* W = Number of words
* L = Maximum word length

*/
class Solution {

    class TrieNode {
        TrieNode[] next = new TrieNode[26];
        String word;
    }

    TrieNode root = new TrieNode();

    List<String> ans = new ArrayList<>();

    public List<String> findWords(char[][] board, String[] words) {

        // Build Trie
        for (String word : words) {

            TrieNode node = root;

            for (char c : word.toCharArray()) {

                int idx = c - 'a';

                if (node.next[idx] == null) {
                    node.next[idx] = new TrieNode();
                }

                node = node.next[idx];
            }

            node.word = word;
        }

        int m = board.length;
        int n = board[0].length;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dfs(board, i, j, root);
            }
        }

        return ans;
    }

    private void dfs(
            char[][] board,
            int r,
            int c,
            TrieNode node) {

        char ch = board[r][c];

        if (ch == '#'
                || node.next[ch - 'a'] == null) {
            return;
        }

        node = node.next[ch - 'a'];

        if (node.word != null) {

            ans.add(node.word);

            // Prevent duplicates
            node.word = null;
        }

        board[r][c] = '#';

        int[][] dir = {
                {1, 0},
                {-1, 0},
                {0, 1},
                {0, -1}
        };

        for (int[] d : dir) {

            int nr = r + d[0];
            int nc = c + d[1];

            if (nr >= 0
                    && nr < board.length
                    && nc >= 0
                    && nc < board[0].length) {

                dfs(board, nr, nc, node);
            }
        }

        board[r][c] = ch;
    }
}
