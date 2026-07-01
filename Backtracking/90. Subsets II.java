/*
90. Subsets II

LeetCode Link: https://leetcode.com/problems/subsets-ii/

Difficulty: Medium

Approach:

Since the array may contain duplicate elements, we first sort it so that duplicate values become adjacent.
We use backtracking to generate all possible subsets. At each recursive call, we add the current subset to the answer and then try including each remaining element.
To avoid generating duplicate subsets, whenever we encounter the same value at the same recursion level, we skip it using:
if (i > index && nums[i] == nums[i - 1])
continue;
This ensures that only the first occurrence of a duplicate element is considered at a particular level of recursion.

Time Complexity:

Sorting: O(n log n)

Backtracking: O(n × 2ⁿ)

Overall: O(n × 2ⁿ)

Space Complexity:

Recursive stack: O(n)

Excluding the output list: O(n)
*/

class Solution {

    List<List<Integer>> ans = new ArrayList<>();

    public List<List<Integer>> subsetsWithDup(int[] nums) {

        Arrays.sort(nums);

        solve(nums, 0, new ArrayList<>());

        return ans;
    }

    void solve(int[] nums, int index, List<Integer> list) {

        ans.add(new ArrayList<>(list));

        for (int i = index; i < nums.length; i++) {

            if (i > index && nums[i] == nums[i - 1])
                continue;

            list.add(nums[i]);
            solve(nums, i + 1, list);
            list.remove(list.size() - 1);
        }
    }
}
