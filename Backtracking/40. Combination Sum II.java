/**
# LeetCode 40. Combination Sum II

**Problem Link:** https://leetcode.com/problems/combination-sum-ii/

## Approach 1: Backtracking + Sorting

Sort the array first.

For every candidate, we have two choices:

1. Include the current element.
2. Skip the current element and all of its duplicates.

Since each element can only be used once, after choosing an element,
the recursion proceeds to the next index.

Sorting allows us to efficiently skip duplicate values and avoid
generating duplicate combinations.

**Time Complexity:** O(2^N)

**Space Complexity:** O(N)

where:

* N = Number of elements in the input array

*/
class Solution {

    List<List<Integer>> ans = new ArrayList<>();

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {

        Arrays.sort(candidates);

        solve(candidates, 0, target, new ArrayList<>());

        return ans;
    }

    private void solve(
            int[] nums,
            int index,
            int target,
            List<Integer> current) {

        if (target == 0) {

            ans.add(new ArrayList<>(current));

            return;
        }

        if (index == nums.length || target < 0) {
            return;
        }

        // Include current element
        current.add(nums[index]);

        solve(
                nums,
                index + 1,
                target - nums[index],
                current
        );

        current.remove(current.size() - 1);

        // Skip current element and all duplicates
        int next = index + 1;

        while (next < nums.length
                && nums[next] == nums[index]) {

            next++;
        }

        solve(
                nums,
                next,
                target,
                current
        );
    }
}

/*

---

## Approach 2: Backtracking + For Loop (Most Common)

Sort the array.

At each recursive call:

- Iterate over the remaining elements.
- Skip duplicate values using:

if (i > start && nums[i] == nums[i - 1]) continue;

- Pick the current element.
- Recurse from the next index since each element can be used only once.

This is the most common implementation used in interviews.

**Time Complexity:** O(2^N)

**Space Complexity:** O(N)

where:

* N = Number of elements in the input array

*/

class Solution {

    List<List<Integer>> ans = new ArrayList<>();

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {

        Arrays.sort(candidates);

        backtrack(
                candidates,
                target,
                0,
                new ArrayList<>()
        );

        return ans;
    }

    private void backtrack(
            int[] nums,
            int target,
            int start,
            List<Integer> current) {

        if (target == 0) {

            ans.add(new ArrayList<>(current));

            return;
        }

        for (int i = start; i < nums.length; i++) {

            if (i > start && nums[i] == nums[i - 1]) {
                continue;
            }

            if (nums[i] > target) {
                break;
            }

            current.add(nums[i]);

            backtrack(
                    nums,
                    target - nums[i],
                    i + 1,
                    current
            );

            current.remove(current.size() - 1);
        }
    }
}
