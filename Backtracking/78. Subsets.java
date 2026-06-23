/**
# LeetCode 78. Subsets

**Problem Link:** https://leetcode.com/problems/subsets/

## Approach 1: Backtracking / Pick & Not Pick

For every element, we have two choices:

1. Do not include the current element.
2. Include the current element.

Recursively explore both choices.

When all elements have been processed, the current subset is added to the answer.

**Time Complexity:** O(N × 2^N)

**Space Complexity:** O(N)

where:

* N = Number of elements in the input array

There are 2^N possible subsets and copying each subset takes up to O(N) time.

*/
class Solution {

    List<List<Integer>> ans = new ArrayList<>();

    public List<List<Integer>> subsets(int[] nums) {

        solve(nums, 0, new ArrayList<>());

        return ans;
    }

    private void solve(
            int[] nums,
            int index,
            List<Integer> subset) {

        if (index == nums.length) {

            ans.add(new ArrayList<>(subset));

            return;
        }

        // Not Pick
        solve(nums, index + 1, subset);

        // Pick
        subset.add(nums[index]);

        solve(nums, index + 1, subset);

        subset.remove(subset.size() - 1);
    }
}

/*

---

## Approach 2: Backtracking Using For Loop

Generate subsets by deciding the next element to include.

At every recursive call:

- Add the current subset to the answer.
- Try including each remaining element.

**Time Complexity:** O(N × 2^N)

**Space Complexity:** O(N)

where:

* N = Number of elements in the input array

*/

class Solution {

    List<List<Integer>> ans = new ArrayList<>();

    public List<List<Integer>> subsets(int[] nums) {

        backtrack(nums, 0, new ArrayList<>());

        return ans;
    }

    private void backtrack(
            int[] nums,
            int start,
            List<Integer> subset) {

        ans.add(new ArrayList<>(subset));

        for (int i = start; i < nums.length; i++) {

            subset.add(nums[i]);

            backtrack(nums, i + 1, subset);

            subset.remove(subset.size() - 1);
        }
    }
}
