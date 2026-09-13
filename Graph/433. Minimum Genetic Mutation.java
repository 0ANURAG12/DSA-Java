/*
 * LeetCode: 433. Minimum Genetic Mutation
 * https://leetcode.com/problems/minimum-genetic-mutation/
 *
 * Topic: Graph / BFS / Shortest Path
 *
 *
 * ============================================================
 * APPROACH 1: BFS + HashSet
 * ============================================================
 *
 * Intuition:
 * - Consider every valid gene sequence as a node in a graph.
 *
 * - Two genes are connected if they differ by exactly one
 *   character.
 *
 * - Every mutation costs exactly 1 step, so this becomes a
 *   shortest path problem.
 *
 * - BFS is ideal because it explores states level by level.
 *
 * - At each step:
 *
 *      1. Take the current gene from the queue.
 *      2. Change each character to one of:
 *             A, C, G, T
 *      3. Check whether the resulting gene exists in the bank.
 *      4. If it has not been visited, add it to the queue.
 *
 * - The first time we reach endGene, we have found the minimum
 *   number of mutations.
 *
 * - The bankSet provides O(1) average-time lookup to check
 *   whether a generated mutation is valid.
 *
 * - The visited set prevents processing the same gene multiple
 *   times and avoids cycles.
 *
 *
 * Time Complexity: O(N × L × 4 × L)
 * Space Complexity: O(N × L)
 *
 * Where:
 * - N = Number of genes in the bank
 * - L = Length of each gene
 *
 * Why O(N × L × 4 × L)?
 * - There can be up to N genes to process.
 * - For every gene, we try L positions.
 * - For every position, we try 4 possible characters.
 * - Creating a new String from char[] takes O(L).
 *
 * Since L is fixed at 8 for this problem:
 *
 *      O(N × L × 4 × L) ≈ O(N)
 *
 * Space:
 * - bankSet stores N genes.
 * - visited stores up to N genes.
 * - queue can contain up to N genes.
 * - Therefore, O(N × L) space is used to store the strings.
 */

class Solution {

    public int minMutation(String startGene, String endGene, String[] bank) {

        // Store valid genes for O(1) average lookup
        Set<String> bankSet = new HashSet<>();

        for (String gene : bank) {
            bankSet.add(gene);
        }

        Queue<String> queue = new LinkedList<>();
        queue.offer(startGene);

        // Prevent revisiting the same gene
        Set<String> visited = new HashSet<>();
        visited.add(startGene);

        char[] chars = {'A', 'C', 'G', 'T'};

        int mutation = 0;

        // BFS
        while (!queue.isEmpty()) {

            int size = queue.size();

            // Process one BFS level
            while (size-- > 0) {

                String gene = queue.poll();

                // Reached the target
                if (gene.equals(endGene)) {
                    return mutation;
                }

                // Try mutating every position
                for (int i = 0; i < gene.length(); i++) {

                    // Try all four possible characters
                    for (char c : chars) {

                        char[] charGene = gene.toCharArray();
                        charGene[i] = c;

                        String newGene = new String(charGene);

                        // Only valid and unvisited mutations
                        // are added to the queue
                        if (bankSet.contains(newGene)
                                && !visited.contains(newGene)) {

                            visited.add(newGene);
                            queue.offer(newGene);
                        }
                    }
                }
            }

            // Move to the next mutation level
            mutation++;
        }

        // No valid mutation sequence exists
        return -1;
    }
}
