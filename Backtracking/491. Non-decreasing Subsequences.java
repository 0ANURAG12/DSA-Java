/*
 * LeetCode: 491. Non-decreasing Subsequences
 * https://leetcode.com/problems/non-decreasing-subsequences/
 *
 * ------------------------------------------------------------
 * Approach: Backtracking + HashSet
 * ------------------------------------------------------------
 * Intuition:
 * - Generate all possible subsequences using backtracking.
 * - At each index, decide whether to include or exclude the current element.
 * - Include the current element only if it maintains the non-decreasing order.
 * - Whenever the end of the array is reached, add the subsequence to the
 *   HashSet if it contains at least two elements.
 * - The HashSet automatically removes duplicate subsequences.
 *
 * Time Complexity: O(2^N × N)
 * Space Complexity: O(2^N × N)
 *
 * where:
 * N = Length of the input array
 *
 * Explanation:
 * - There are 2^N possible subsequences.
 * - Copying a valid subsequence into the HashSet takes O(N) time.
 * - In the worst case, the HashSet may store O(2^N) subsequences,
 *   each of length up to N.
 */

class Solution {
    Set<List<Integer>> set;

    public List<List<Integer>> findSubsequences(int[] nums) {
        set = new HashSet<>();
        solve(0, nums, new ArrayList<>());
        return new ArrayList<>(set);
    }

    void solve(int i, int[] nums, List<Integer> list) {
        if (i == nums.length) {
            if (list.size() >= 2) {
                set.add(new ArrayList<>(list));
            }
            return;
        }

        if (list.isEmpty() || list.get(list.size() - 1) <= nums[i]) {
            list.add(nums[i]);
            solve(i + 1, nums, list);
            list.remove(list.size() - 1);
        }

        solve(i + 1, nums, list);
    }
}
/*
 * Alternative Approach: Backtracking + Per-Level HashSet
 *
 * Intuition:
 * - Generate subsequences using backtracking.
 * - At each recursion level, maintain a HashSet to record which values
 *   have already been used at that level.
 * - Skip duplicate values at the same recursion depth to prevent
 *   generating identical subsequences.
 * - This eliminates duplicates during generation instead of removing
 *   them afterward with a global HashSet.
 *
 * Time Complexity: O(2^N × N)
 * Space Complexity: O(N)
 *
 * where:
 * N = Length of the input array
 */

class Solution {
    List<List<Integer>> ans = new ArrayList<>();

    public List<List<Integer>> findSubsequences(int[] nums) {
        backtrack(0, nums, new ArrayList<>());
        return ans;
    }

    private void backtrack(int index, int[] nums, List<Integer> path) {
        if (path.size() >= 2) {
            ans.add(new ArrayList<>(path));
        }

        HashSet<Integer> used = new HashSet<>();

        for (int i = index; i < nums.length; i++) {

            if (used.contains(nums[i])) {
                continue;
            }

            if (!path.isEmpty() && nums[i] < path.get(path.size() - 1)) {
                continue;
            }

            used.add(nums[i]);
            path.add(nums[i]);

            backtrack(i + 1, nums, path);

            path.remove(path.size() - 1);
        }
    }
}
