/**
# LeetCode 3739. Count Subarrays With Majority Element II

**Problem Link:** https://leetcode.com/problems/count-subarrays-with-majority-element-ii/

## Approach 1: Brute Force

Consider every possible subarray.

For each starting index:

- Extend the subarray one element at a time.
- Maintain the frequency of the target element.
- If the frequency of `target` is greater than half of the current subarray
  length, increment the answer.

**Time Complexity:** O(N²)

**Space Complexity:** O(1)

where:

* N = Length of the input array

*/
class Solution {

    public long countMajoritySubarrays(int[] nums, int target) {

        long ans = 0;
        int n = nums.length;

        for (int i = 0; i < n; i++) {

            int count = 0;

            for (int j = i; j < n; j++) {

                if (nums[j] == target) {
                    count++;
                }

                int len = j - i + 1;

                if (count > len / 2) {
                    ans++;
                }
            }
        }

        return ans;
    }
}

/*

---

## Approach 2: Prefix Sum + HashMap (Optimal)

Transform the array as follows:

- Replace every occurrence of `target` with `+1`.
- Replace every other element with `-1`.

Now, a subarray has `target` as its majority element if and only if the
sum of the transformed values in that subarray is strictly greater than 0.

Maintain:

- `cumSum` = Prefix sum of the transformed array.
- `map` = Frequency of every prefix sum encountered.
- `validLeftPoints` = Number of valid starting indices for the current ending index.

For every element:

- If the current element is `target`, increase the prefix sum and add
  newly valid starting positions.
- Otherwise, decrease the prefix sum and remove starting positions that
  are no longer valid.

Accumulate the number of valid starting positions for every ending index.

**Time Complexity:** O(N)

**Space Complexity:** O(N)

where:

* N = Length of the input array

*/

class Solution {

    public long countMajoritySubarrays(int[] nums, int target) {

        Map<Integer, Integer> map = new HashMap<>();

        int cumSum = 0;

        map.put(0, 1);

        long validLeftPoints = 0;
        long result = 0;

        for (int num : nums) {

            if (num == target) {

                validLeftPoints += map.get(cumSum);

                cumSum++;

            } else {

                cumSum--;

                validLeftPoints -= map.getOrDefault(cumSum, 0);
            }

            map.put(
                    cumSum,
                    map.getOrDefault(cumSum, 0) + 1
            );

            result += validLeftPoints;
        }

        return result;
    }
}
