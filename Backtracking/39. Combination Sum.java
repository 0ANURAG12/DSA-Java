/**
# LeetCode 39. Combination Sum

**Problem Link:** https://leetcode.com/problems/combination-sum/

## Approach 1: Backtracking (Pick / Not Pick)

For every candidate, we have two choices:

1. Skip the current candidate.
2. Pick the current candidate.

Since a candidate can be used unlimited times:

- After picking a candidate, stay at the same index.
- After skipping a candidate, move to the next index.

Whenever the current sum becomes equal to the target,
a valid combination is found.

**Time Complexity:** O(2^T)

**Space Complexity:** O(T)

where:

* T = Target value

The exact complexity depends on the number of valid combinations generated.

*/
class Solution {

    List<List<Integer>> ans;

    public List<List<Integer>> combinationSum(
            int[] candidates,
            int target) {

        ans = new ArrayList<>();

        solve(
                candidates,
                target,
                0,
                0,
                new ArrayList<>()
        );

        return ans;
    }

    private void solve(
            int[] candidates,
            int target,
            int index,
            int sum,
            ArrayList<Integer> list) {

        if (sum > target
                || index >= candidates.length) {
            return;
        }

        if (sum == target) {

            ans.add(new ArrayList<>(list));

            return;
        }

        // Not Pick
        solve(
                candidates,
                target,
                index + 1,
                sum,
                list
        );

        // Pick
        list.add(candidates[index]);

        solve(
                candidates,
                target,
                index,
                sum + candidates[index],
                list
        );

        list.remove(list.size() - 1);
    }
}

/*

---

## Approach 2: Backtracking with For Loop (Recommended)

At each position:

- Choose any candidate from the current index onward.
- Reuse the same candidate by passing the same index.
- Stop exploring when the remaining target becomes negative.

**Time Complexity:** O(2^T)

**Space Complexity:** O(T)

where:

* T = Target value

*/

class Solution {

    List<List<Integer>> ans = new ArrayList<>();

    public List<List<Integer>> combinationSum(
            int[] candidates,
            int target) {

        backtrack(
                candidates,
                target,
                0,
                new ArrayList<>()
        );

        return ans;
    }

    private void backtrack(
            int[] candidates,
            int target,
            int start,
            List<Integer> current) {

        if (target == 0) {

            ans.add(new ArrayList<>(current));

            return;
        }

        if (target < 0) {
            return;
        }

        for (int i = start;
             i < candidates.length;
             i++) {

            current.add(candidates[i]);

            backtrack(
                    candidates,
                    target - candidates[i],
                    i,
                    current
            );

            current.remove(current.size() - 1);
        }
    }
}
