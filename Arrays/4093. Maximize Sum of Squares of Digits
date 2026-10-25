/*
 * LeetCode: 4093. Maximize Sum of Squares of Digits
 * Topic: Arrays / Greedy / Priority Queue / Sorting
 *
 *
 * ============================================================
 * APPROACH 1: Greedy + Max Heap
 * ============================================================
 *
 * Intuition:
 * - Initially, calculate the sum of all elements in nums.
 *
 * - For each number, rearrange its digits in descending order
 *   to construct the maximum possible number using those digits.
 *
 * - Sort the digits in ascending order, then traverse them
 *   backward to build the rearranged number.
 *
 * - Calculate the gain:
 *
 *       gain = rearranged - num
 *
 * - If the gain is positive, store it in a max heap.
 *
 * - The max heap always places the largest gain at the top.
 *
 * - Perform up to k operations by repeatedly extracting the
 *   largest gain and adding it to the original sum.
 *
 * - If the heap becomes empty, no further positive gains
 *   are available, so stop early.
 *
 *
 * Example:
 *
 *      nums = [123, 456]
 *      k = 1
 *
 *      Initial sum = 123 + 456 = 579
 *
 *      Number 123:
 *      Rearranged = 321
 *      Gain = 321 - 123 = 198
 *
 *      Number 456:
 *      Rearranged = 654
 *      Gain = 654 - 456 = 198
 *
 *      Max heap = [198, 198]
 *
 *      Take the largest gain once:
 *      Answer = 579 + 198 = 777
 *
 *
 * Time Complexity: O(n log n + n log n + k log n)
 * Space Complexity: O(n)
 *
 * Why O(n log n)?
 * - Rearranging each number's digits takes O(d log d),
 *   where d is the number of digits.
 * - Inserting up to n gains into the priority queue takes
 *   O(n log n).
 * - Extracting up to min(k, n) gains takes O(k log n).
 *
 * Space:
 * - The priority queue can store up to n gains.
 * - The character array uses O(d) temporary space.
 */


class Solution {

    public long maximizeSum(int[] nums, int k) {

        long ans = 0;

        // Max heap: largest gain is extracted first
        PriorityQueue<int[]> pq = new PriorityQueue<>(
            (a, b) -> Integer.compare(b[0], a[0])
        );

        for (int num : nums) {

            // Add the original number to the total
            ans += num;

            // Rearrange digits to obtain the maximum number
            int rearranged = rearrangeNums(num);

            // Calculate the improvement
            int gain = rearranged - num;

            // Store only positive gains
            if (gain > 0) {
                pq.offer(new int[]{gain});
            }
        }

        // Apply the largest available gains first
        while (k-- > 0 && !pq.isEmpty()) {
            ans += pq.poll()[0];
        }

        return ans;
    }

    private int rearrangeNums(int num) {

        // Convert the number into a character array
        char[] arr = String.valueOf(num).toCharArray();

        // Sort digits in ascending order
        Arrays.sort(arr);

        int ans = 0;

        // Traverse backward to construct the maximum number
        for (int i = arr.length - 1; i >= 0; i--) {
            ans = ans * 10 + (arr[i] - '0');
        }

        return ans;
    }
}
