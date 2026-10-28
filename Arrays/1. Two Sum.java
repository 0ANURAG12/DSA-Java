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
/*
 * ============================================================
 * APPROACH 2: Sorting + Two Pointers (Early Termination)
 * ============================================================
 *
 * Intuition:
 * - Sort the array and fix the first element.
 *
 * - If nums[i] > 0, stop the loop because all remaining
 *   elements are also positive, so their sum cannot be zero.
 *
 * - Skip duplicate values of nums[i].
 *
 * - Use left and right pointers to find the other two elements.
 *
 * - If the sum is positive, move the right pointer left.
 *
 * - If the sum is negative, move the left pointer right.
 *
 * - If the sum is zero, add the triplet, move both pointers,
 *   and skip duplicate values at the left pointer.
 *
 *
 * Time Complexity: O(n^2)
 * Auxiliary Space Complexity: O(log n) typically for sorting.
 */


public class Solution {

    public List<List<Integer>> threeSum(int[] nums) {

        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();

        for (int i = 0; i < nums.length - 2; i++) {

            // All remaining values are positive
            if (nums[i] > 0) {
                break;
            }

            // Skip duplicate first elements
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            int l = i + 1;
            int r = nums.length - 1;

            while (l < r) {

                int sum = nums[i] + nums[l] + nums[r];

                if (sum > 0) {
                    r--;
                } else if (sum < 0) {
                    l++;
                } else {

                    res.add(Arrays.asList(
                        nums[i], nums[l], nums[r]
                    ));

                    l++;
                    r--;

                    // Skip duplicate left values
                    while (l < r && nums[l] == nums[l - 1]) {
                        l++;
                    }
                }
            }
        }

        return res;
    }
}


/*
 * ============================================================
 * APPROACH 3: Sorting + Two Pointers (Skip Both Duplicates)
 * ============================================================
 *
 * Intuition:
 * - This approach follows the same two-pointer strategy.
 *
 * - Sort the array and stop early when nums[i] > 0.
 *
 * - Skip duplicate first elements to avoid repeating triplets.
 *
 * - When the sum is zero, add the triplet and move both
 *   pointers inward.
 *
 * - Skip duplicate values on both sides:
 *
 *   Left:
 *   nums[l] == nums[l - 1]
 *
 *   Right:
 *   nums[r] == nums[r + 1]
 *
 * - Skipping duplicates on both sides ensures that the same
 *   pair is not processed repeatedly.
 *
 *
 * Time Complexity: O(n^2)
 * Auxiliary Space Complexity: O(log n) typically for sorting.
 */


public class Solution {

    public List<List<Integer>> threeSum(int[] nums) {

        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();

        for (int i = 0; i < nums.length - 2; i++) {

            // All remaining values are positive
            if (nums[i] > 0) {
                break;
            }

            // Skip duplicate first elements
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            int l = i + 1;
            int r = nums.length - 1;

            while (l < r) {

                int sum = nums[i] + nums[l] + nums[r];

                if (sum > 0) {
                    r--;
                } else if (sum < 0) {
                    l++;
                } else {

                    res.add(Arrays.asList(
                        nums[i], nums[l], nums[r]
                    ));

                    l++;
                    r--;

                    // Skip duplicate values on both sides
                    while (l < r && nums[l] == nums[l - 1]) {
                        l++;
                    }

                    while (l < r && nums[r] == nums[r + 1]) {
                        r--;
                    }
                }
            }
        }

        return res;
    }
}
