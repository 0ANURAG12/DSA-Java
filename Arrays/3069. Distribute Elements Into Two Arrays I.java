/*
 * LeetCode 3069. Distribute Elements Into Two Arrays I
 * https://leetcode.com/problems/distribute-elements-into-two-arrays-i/
 *
 * Approach:
 * - Simulation
 * - Create two separate arrays (ArrayLists).
 * - Put the first element in arr1 and the second element in arr2.
 * - For every remaining element:
 *      - If the last element of arr1 is greater than the last
 *        element of arr2, add it to arr1.
 *      - Otherwise, add it to arr2.
 * - Finally, concatenate arr1 and arr2 into the original nums array.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 */

class Solution {

    public int[] resultArray(int[] nums) {

        int n = nums.length;

        ArrayList<Integer> arr1 = new ArrayList<>();
        ArrayList<Integer> arr2 = new ArrayList<>();

        // First two elements are distributed directly
        arr1.add(nums[0]);
        arr2.add(nums[1]);

        // Distribute remaining elements
        for (int i = 2; i < n; i++) {

            if (arr1.get(arr1.size() - 1) > arr2.get(arr2.size() - 1)) {
                arr1.add(nums[i]);
            } else {
                arr2.add(nums[i]);
            }
        }

        // Merge arr1 and arr2 back into nums
        int index = 0;

        for (int num : arr1) {
            nums[index++] = num;
        }

        for (int num : arr2) {
            nums[index++] = num;
        }

        return nums;
    }
}
