/**
# LeetCode 3737. Count Subarrays With Majority Element I

**Problem Link:** https://leetcode.com/problems/count-subarrays-with-majority-element-i/

## Approach 1: Brute Force

Generate every possible subarray.

For each subarray:

- Scan the entire subarray.
- Count the occurrences of `target`.
- If the frequency of `target` is greater than half of the subarray length,
  increment the answer.

This is the most straightforward solution but repeatedly scans the same
elements.

**Time Complexity:** O(N³)

**Space Complexity:** O(1)

where:

* N = Length of the input array

*/
class Solution {

    public int countMajoritySubarrays(int[] nums, int target) {

        int n = nums.length;
        int result = 0;

        for (int i = 0; i < n; i++) {

            for (int j = i; j < n; j++) {

                int count = 0;

                for (int k = i; k <= j; k++) {

                    if (nums[k] == target) {
                        count++;
                    }
                }

                int len = j - i + 1;

                if (count > len / 2) {
                    result++;
                }
            }
        }

        return result;
    }
}

/*

---

## Approach 2: Improved Brute Force

Instead of scanning the subarray every time:

- Fix the left endpoint.
- Extend the right endpoint.
- Maintain the frequency of `target` while extending.

This removes one loop and improves the complexity.

**Time Complexity:** O(N²)

**Space Complexity:** O(1)

where:

* N = Length of the input array

*/

class Solution {

    public int countMajoritySubarrays(int[] nums, int target) {

        int ans = 0;
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

## Approach 3: +1 / -1 Transformation

Observation:

Instead of counting the occurrences of `target`, transform the array as:

- target      → +1
- other value → -1

Now, for any subarray:

sum > 0  ⟺  target is the majority element

So, instead of checking:

count(target) > length / 2

we only need to check whether the transformed subarray sum is positive.

This simplifies the condition while keeping the same overall complexity.

**Time Complexity:** O(N²)

**Space Complexity:** O(1)

where:

* N = Length of the input array

*/

class Solution {

    public int countMajoritySubarrays(int[] nums, int target) {

        int n = nums.length;
        int ans = 0;

        for (int i = 0; i < n; i++) {

            int sum = 0;

            for (int j = i; j < n; j++) {

                if (nums[j] == target) {
                    sum++;
                } else {
                    sum--;
                }

                if (sum > 0) {
                    ans++;
                }
            }
        }

        return ans;
    }
}

/*

---

## Approach 4: Prefix Sum

Instead of maintaining the running transformed sum for every starting index,
first build the prefix sum of the transformed array.

Now the sum of any subarray can be computed in O(1):

subarraySum = prefix[right] - prefix[left - 1]

A subarray is valid if its transformed sum is positive.

Although this approach still checks every subarray, it introduces the
Prefix Sum concept that leads directly to the optimal solution for
LeetCode 3739 (Hard).

**Time Complexity:** O(N²)

**Space Complexity:** O(N)

where:

* N = Length of the input array

*/

class Solution {

    public int countMajoritySubarrays(int[] nums, int target) {

        int n = nums.length;

        int[] prefix = new int[n + 1];

        for (int i = 0; i < n; i++) {

            prefix[i + 1] = prefix[i]
                    + (nums[i] == target ? 1 : -1);
        }

        int ans = 0;

        for (int left = 0; left < n; left++) {

            for (int right = left; right < n; right++) {

                int sum = prefix[right + 1] - prefix[left];

                if (sum > 0) {
                    ans++;
                }
            }
        }

        return ans;
    }
}

/*

========================================================

Optimization

Approach 1 : Brute Force                      O(N³)
        ↓
Approach 2 : Running Frequency                O(N²)
        ↓
Approach 3 : +1 / -1 Transformation           O(N²)
        ↓
Approach 4 : Prefix Sum                       O(N²)
        ↓
LeetCode 3739 : Prefix Sum + HashMap          O(N)

The key observation is:

target      → +1
other value → -1

Once this transformation is made, the problem becomes counting subarrays
whose transformed sum is strictly positive.

LeetCode 3739 optimizes this further using a HashMap to count valid
prefix sums in O(N).

*/
