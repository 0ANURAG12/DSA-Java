/*
 * LeetCode 3731. Find Missing Elements
 * Link: https://leetcode.com/problems/find-missing-elements/
 *
 * ------------------------------------------------------------
 * Approach 1: Sorting
 * ------------------------------------------------------------
 * - Sort the array.
 * - Traverse from the minimum element to the maximum element.
 * - Whenever there is a gap between consecutive numbers,
 *   add all missing numbers to the answer.
 *
 * Time Complexity: O(n log n)
 * Space Complexity: O(1)
 */

class Solution {
    public List<Integer> findMissingElements(int[] nums) {

        int n = nums.length;
        Arrays.sort(nums);

        if (n == (nums[n - 1] - nums[0] + 1)) {
            return new ArrayList<>();
        }

        List<Integer> missing = new ArrayList<>();

        int current = nums[0];

        for (int num : nums) {
            while (current != num) {
                missing.add(current);
                current++;
            }
            current++;
        }

        return missing;
    }
}
/*
 * ------------------------------------------------------------
 * Approach 2: HashSet
 * ------------------------------------------------------------
 * - Store every element in a HashSet.
 * - Find the minimum and maximum values.
 * - Iterate through the entire range [min, max].
 * - If a number is absent from the HashSet,
 *   it is a missing element.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 */

class Solution {
    public List<Integer> findMissingElements(int[] nums) {

        Set<Integer> set = new HashSet<>();

        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for (int num : nums) {
            set.add(num);
            min = Math.min(min, num);
            max = Math.max(max, num);
        }

        List<Integer> missing = new ArrayList<>();

        for (int i = min; i <= max; i++) {
            if (!set.contains(i)) {
                missing.add(i);
            }
        }

        return missing;
    }
}
