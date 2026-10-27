/*
 * LeetCode: 3963. Create Grid
 * Topic: Arrays / Strings / Simulation
 *
 *
 * ============================================================
 * APPROACH 1: Nested Loops + StringBuilder
 * ============================================================
 *
 * Intuition:
 * - We need to create a grid with m rows and n columns.
 *
 * - Since the return type is String[], each row of the grid
 *   is represented as a separate string.
 *
 * - Use an outer loop to construct each row.
 *
 * - Use an inner loop to decide which character to append:
 *
 *   1. If j == 0, append '.' to the first column.
 *
 *   2. If i == m - 1, append '.' to every position in the
 *      last row.
 *
 *   3. Otherwise, append '#'.
 *
 * - After constructing a row, convert the StringBuilder into
 *   a string and store it in ans[i].
 *
 * - Finally, return the completed grid.
 *
 *
 * Example:
 *
 *      m = 3, n = 4
 *
 *      Row 0: .###
 *      Row 1: .###
 *      Row 2: ....
 *
 *      Answer:
 *      [".###", ".###", "...."]
 *
 *
 * Time Complexity: O(m * n)
 * Space Complexity: O(m * n)
 *
 * Why O(m * n)?
 * - We process every cell in the grid exactly once.
 *
 * Space:
 * - The result stores m strings, each containing n characters.
 */


class Solution {

    public String[] createGrid(int m, int n) {

        String[] ans = new String[m];

        // Construct each row
        for (int i = 0; i < m; i++) {

            StringBuilder str = new StringBuilder();

            // Construct each column
            for (int j = 0; j < n; j++) {

                // First column or last row
                if (j == 0 || i == m - 1) {
                    str.append('.');
                } else {
                    str.append('#');
                }
            }

            // Store the completed row
            ans[i] = str.toString();
        }

        return ans;
    }
}
