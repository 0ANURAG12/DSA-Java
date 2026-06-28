/**
# LeetCode 1846. Maximum Element After Decreasing and Rearranging

**Problem Link:** https://leetcode.com/problems/maximum-element-after-decreasing-and-rearranging/

## Approach 1: Sorting + Greedy

Sort the array in non-decreasing order.

To maximize the largest element while satisfying the conditions:

- The first element must be `1`.
- The difference between adjacent elements must not exceed `1`.

Traverse the sorted array:

- Set the first element to `1`.
- For every other element:
  - If the difference from the previous element is greater than `1`,
    reduce it to `previous + 1`.
  - Otherwise, keep it unchanged.

The last element after these adjustments is the maximum possible value.

**Time Complexity:** O(N log N)

**Space Complexity:** O(1)

where:

* N = Length of the input array

*/
class Solution {

    public int maximumElementAfterDecrementingAndRearranging(int[] arr) {

        Arrays.sort(arr);

        int n = arr.length;
        int maxElement = 1;

        for (int i = 0; i < n; i++) {

            if (i == 0) {

                arr[i] = 1;

            } else if (arr[i] - arr[i - 1] > 1) {

                arr[i] = arr[i - 1] + 1;
            }

            maxElement = Math.max(maxElement, arr[i]);
        }

        return maxElement;
    }
}
/*

---

## Approach 2: Counting Sort + Greedy (Optimal)

Instead of sorting the array, count the frequency of every value.

Observation:

- The maximum possible answer can never exceed `N`.
- Therefore, every value greater than `N` can be treated as `N`.

Store the frequency of each value and greedily construct the array:

- Start with `current = 1`.
- For every value from `2` to `N`:
    - Increase `current` by at most `1` whenever that value exists.

This avoids comparison sorting and achieves linear time complexity.

**Time Complexity:** O(N)

**Space Complexity:** O(N)

where:

* N = Length of the input array

*/

class Solution {

    public int maximumElementAfterDecrementingAndRearranging(int[] arr) {

        int n = arr.length;

        int[] freq = new int[n + 1];

        // Count frequencies
        for (int num : arr) {
            freq[Math.min(num, n)]++;
        }

        int current = 0;

        for (int value = 1; value <= n; value++) {

            while (freq[value]-- > 0) {

                current = Math.min(current + 1, value);
            }
        }

        return current;
    }
}
