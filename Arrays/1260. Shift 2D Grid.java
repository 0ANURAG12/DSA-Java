/*
 * LeetCode: 1260. Shift 2D Grid
 * https://leetcode.com/problems/shift-2d-grid/
 *
 * ------------------------------------------------------------
 * Approach: Matrix Simulation + In-Place Shifting
 * ------------------------------------------------------------
 * Intuition:
 * - Treat the 2D grid as a flattened 1D array of size (rows × columns).
 * - Save the last k elements in a temporary array since they wrap around
 *   to the beginning after shifting.
 * - Shift the remaining elements backward in-place from the end to avoid
 *   overwriting values.
 * - Copy the saved elements into the first k positions.
 * - Finally, convert the modified grid into the required
 *   List<List<Integer>> format.
 *
 * Time Complexity: O(R × C)
 * Space Complexity: O(K)
 *
 * where:
 * R = Number of rows.
 * C = Number of columns.
 * K = k % (R × C).
 *
 * Explanation:
 * - Every element is moved at most once.
 * - The temporary array stores only the last K elements.
 *
 * Alternative Approach:
 * 1. Extra Matrix
 *    - Compute the new position of every element directly using
 *      modular arithmetic:
 *
 *          newIndex = (oldIndex + k) % (R × C)
 *
 *    - Place each element into a new matrix and return it.
 *    - Time Complexity: O(R × C)
 *    - Space Complexity: O(R × C)
 */
class Solution {
    public List<List<Integer>> shiftGrid(int[][] grid, int k) {
        int n = grid.length;
        int m = grid[0].length;

        k %= (n * m);
        if (k == 0) {
            List<List<Integer>> ans = new ArrayList<>();
            for (int[] row : grid) {
                List<Integer> list = new ArrayList<>();
                for (int x : row) list.add(x);
                ans.add(list);
            }
            return ans;
        }

        int[] temp = new int[k];

        int idx = n * m - k;
        for (int i = 0; i < k; i++) {
            temp[i] = grid[idx / m][idx % m];
            idx++;
        }

        int prev = n * m - k - 1;
        int next = n * m - 1;

        while (prev >= 0) {
            grid[next / m][next % m] = grid[prev / m][prev % m];
            prev--;
            next--;
        }

        for (int i = 0; i < k; i++) {
            grid[i / m][i % m] = temp[i];
        }

        List<List<Integer>> ans = new ArrayList<>();

        for (int[] row : grid) {
            List<Integer> list = new ArrayList<>();
            for (int x : row) {
                list.add(x);
            }
            ans.add(list);
        }

        return ans;
    }
}
