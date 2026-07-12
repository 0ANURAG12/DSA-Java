/*
 * LeetCode: 1331. Rank Transform of an Array
 * https://leetcode.com/problems/rank-transform-of-an-array/
 *
 * ------------------------------------------------------------
 * Approach: Sorting + HashMap
 * ------------------------------------------------------------
 * Intuition:
 * - Create a sorted copy of the original array.
 * - Traverse the sorted array and assign a unique rank to each
 *   distinct value.
 * - Store the mapping from value to rank in a HashMap.
 * - Finally, replace every element in the original array with
 *   its corresponding rank from the HashMap.
 *
 * Time Complexity: O(N log N)
 * Space Complexity: O(N)
 *
 * where:
 * N = Number of elements in the array.
 *
 * Explanation:
 * - Cloning the array takes O(N).
 * - Sorting dominates the time complexity with O(N log N).
 * - Building the HashMap and replacing the elements each take O(N).
 *
 * Alternative Approach:
 * 1. Sorting + Binary Search
 *    - Sort a copy of the array and remove duplicates.
 *    - For each element in the original array, use binary search
 *      to find its rank in the sorted unique array.
 *
 * Time Complexity: O(N log N)
 * Space Complexity: O(N)
 */
class Solution {
    public int[] arrayRankTransform(int[] arr) {
        int[] sorted = arr.clone();
        Arrays.sort(sorted);

        Map<Integer, Integer> rank = new HashMap<>();

        int r = 1;
        for (int x : sorted) {
            if (!rank.containsKey(x))
                rank.put(x, r++);
        }

        for (int i = 0; i < arr.length; i++)
            arr[i] = rank.get(arr[i]);

        return arr;
    }
}
/*
 * ------------------------------------------------------------
 * Approach: Sorting + HashMap
 * ------------------------------------------------------------
 * Intuition:
 * - Create a sorted copy of the original array.
 * - Assign ranks only to distinct elements while traversing the sorted array.
 * - Store each value and its corresponding rank in a HashMap.
 * - Finally, replace every element in the original array with its assigned rank.
 *
 * Time Complexity: O(N log N)
 * Space Complexity: O(N)
 *
 * where:
 * N = Number of elements in the array.
 *
 * Explanation:
 * - Cloning the array takes O(N).
 * - Sorting the copied array takes O(N log N).
 * - Building the HashMap takes O(N).
 * - Replacing each element with its rank takes O(N).
 *
 * Alternative Approach:
 * 1. Sorting + HashMap (Enhanced For Loop)
 *    - Sort a cloned array.
 *    - Traverse the sorted array using a for-each loop and assign
 *      ranks to unique elements.
 *    - Replace each element in the original array using the HashMap.
 *
 * Time Complexity: O(N log N)
 * Space Complexity: O(N)
 */

class Solution {
    public int[] arrayRankTransform(int[] arr) {
        int n = arr.length;
        if(n < 1) return arr;

        int[] copy = arr.clone();
        Map<Integer, Integer> map = new HashMap<>();

        Arrays.sort(copy);

        int rank = 1;
        int prev = copy[0];
        map.put(prev, rank);

        for(int i = 1; i < n; i++){
            if(copy[i] != prev){
                prev = copy[i];
                rank++;
                map.put(prev, rank);
            }
        }

        for(int i = 0; i < n; i++){
            arr[i] = map.get(arr[i]);
        }

        return arr;
    }
}
