/*
Problem: 287. Find the Duplicate Number
Link: https://leetcode.com/problems/find-the-duplicate-number/

Difficulty: Medium
Topic: Linked List, Floyd Cycle Detection

--------------------------------------------------
Approach 1: Brute Force
Time Complexity: O(n²)
Space Complexity: O(1)
*/
class Solution {
    public int findDuplicate(int[] nums) {

        for (int i = 0; i < nums.length; i++) {

            int count = 0;

            for (int j = 0; j < nums.length; j++) {

                if (nums[i] == nums[j])
                    count++;
            }

            if (count > 1)
                return nums[i];
        }

        return -1;
    }
}
/*
--------------------------------------------------
Approach 2: HashSet
Time Complexity: O(n)
Space Complexity: O(n)
*/
class Solution {
    public int findDuplicate(int[] nums) {

        HashSet<Integer> set = new HashSet<>();

        for (int num : nums) {

            if (set.contains(num))
                return num;

            set.add(num);
        }

        return -1;
    }
}
/*
--------------------------------------------------
Approach 3: Sorting
Time Complexity: O(n log n)
Space Complexity: Depends on sorting implementation
*/
class Solution {
    public int findDuplicate(int[] nums) {

        Arrays.sort(nums);

        for (int i = 1; i < nums.length; i++) {

            if (nums[i] == nums[i - 1])
                return nums[i];
        }

        return -1;
    }
}
/*
--------------------------------------------------
Approach 4: Binary Search on Answer
Time Complexity: O(n log n)
Space Complexity: O(1)
*/
class Solution {
    public int findDuplicate(int[] nums) {

        int low = 1;
        int high = nums.length - 1;

        while (low < high) {

            int mid = low + (high - low) / 2;

            int count = 0;

            for (int num : nums) {
                if (num <= mid)
                    count++;
            }

            if (count > mid)
                high = mid;
            else
                low = mid + 1;
        }

        return low;
    }
}
/*
--------------------------------------------------
Approach 5: Floyd Cycle Detection (Optimal)
Time Complexity: O(n)
Space Complexity: O(1)

Treat:
index -> nums[index]

as a linked list.

The duplicate number becomes the entry point
of the cycle.

--------------------------------------------------
*/

class Solution {

    public int findDuplicate(int[] nums) {

        int slow = nums[0];
        int fast = nums[0];

        do {

            slow = nums[slow];
            fast = nums[nums[fast]];

        } while (slow != fast);

        slow = nums[0];

        while (slow != fast) {

            slow = nums[slow];
            fast = nums[fast];
        }

        return slow;
    }
}
