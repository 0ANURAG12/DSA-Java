/*
 * LeetCode: 1. Two Sum
 * https://leetcode.com/problems/two-sum/
 *
 * Topic: Arrays / HashMap
 *
 *
 * ============================================================
 * APPROACH 1: HashMap (One Pass)
 * ============================================================
 *
 * Intuition:
 * - We need to find two indices whose elements add up to target.
 *
 * - Use a HashMap to store previously visited elements:
 *
 *       Key   -> Array element
 *       Value -> Index of that element
 *
 * - Traverse the array from left to right.
 *
 * - For each nums[i], calculate its complement:
 *
 *       complement = target - nums[i]
 *
 * - If the complement exists in the map, we have found
 *   the required pair.
 *
 * - Otherwise, store the current element and its index.
 *
 * - Return the two indices when the complement is found.
 *
 *
 * Example:
 *
 *      nums = [2, 7, 11, 15]
 *      target = 9
 *
 *      i = 0:
 *      nums[0] = 2
 *      Complement = 9 - 2 = 7
 *      7 is not in the map.
 *      Store {2: 0}
 *
 *      i = 1:
 *      nums[1] = 7
 *      Complement = 9 - 7 = 2
 *      2 exists at index 0.
 *
 *      Answer = [1, 0]
 *
 *
 * Time Complexity: O(n) average
 * Space Complexity: O(n)
 *
 * Why O(n)?
 * - Each element is processed once.
 * - HashMap lookup and insertion take O(1) average time.
 *
 * Space:
 * - In the worst case, the map stores O(n) elements.
 */


class Solution {

    public int[] twoSum(int[] nums, int target) {

        Map<Integer, Integer> map = new HashMap<>();

        int i = 0;

        for (; i < nums.length; i++) {

            // Find the complement needed to reach target
            if (map.containsKey(target - nums[i])) {
                break;
            } else {
                // Store the current number and its index
                map.put(nums[i], i);
            }
        }

        // Return the current index and the complement's index
        return new int[]{
            i,
            map.get(target - nums[i])
        };
    }
}
