/**
# LeetCode 46. Permutations

**Problem Link:** https://leetcode.com/problems/permutations/

## Approach 1: Backtracking + Visited Array

Generate permutations by choosing one unused element at each position.

At every recursive call:

- Try every element that has not been used.
- Mark it as visited.
- Add it to the current permutation.
- Recurse to build the remaining permutation.
- Backtrack by removing the element and marking it as unvisited.

When the current permutation contains all elements, add it to the answer.

**Time Complexity:** O(N × N!)

**Space Complexity:** O(N)

where:

* N = Number of elements in the input array

*/
class Solution {

    List<List<Integer>> ans = new ArrayList<>();

    public List<List<Integer>> permute(int[] nums) {

        boolean[] visited = new boolean[nums.length];

        backtrack(nums, visited, new ArrayList<>());

        return ans;
    }

    private void backtrack(
            int[] nums,
            boolean[] visited,
            List<Integer> current) {

        if (current.size() == nums.length) {

            ans.add(new ArrayList<>(current));

            return;
        }

        for (int i = 0; i < nums.length; i++) {

            if (visited[i]) {
                continue;
            }

            visited[i] = true;

            current.add(nums[i]);

            backtrack(nums, visited, current);

            current.remove(current.size() - 1);

            visited[i] = false;
        }
    }
}

/*

---

## Approach 2: Backtracking + Swapping (Optimal)

Instead of using a visited array:

- Fix one position at a time.
- Swap every remaining element into the current position.
- Recurse for the next position.
- Swap back to restore the original array.

This generates permutations in-place without extra visited space.

**Time Complexity:** O(N × N!)

**Space Complexity:** O(N)

where:

* N = Number of elements in the input array

*/

class Solution {

    List<List<Integer>> ans = new ArrayList<>();

    public List<List<Integer>> permute(int[] nums) {

        backtrack(nums, 0);

        return ans;
    }

    private void backtrack(int[] nums, int index) {

        if (index == nums.length) {

            List<Integer> permutation = new ArrayList<>();

            for (int num : nums) {
                permutation.add(num);
            }

            ans.add(permutation);

            return;
        }

        for (int i = index; i < nums.length; i++) {

            swap(nums, index, i);

            backtrack(nums, index + 1);

            swap(nums, index, i);
        }
    }

    private void swap(int[] nums, int i, int j) {

        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
