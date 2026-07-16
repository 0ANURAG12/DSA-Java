/*
 * LeetCode: 3867. Sum of GCD of Formed Pairs
 * https://leetcode.com/problems/sum-of-gcd-of-formed-pairs/
 *
 * ------------------------------------------------------------
 * Approach: Prefix Maximum + GCD + Sorting
 * ------------------------------------------------------------
 * Intuition:
 * - Construct the prefixGcd array where each element is the GCD of
 *   the current element and the maximum value seen so far.
 * - Sort the resulting array.
 * - Form pairs by taking the smallest and largest remaining elements.
 * - Compute the GCD of each pair and accumulate the answer.
 *
 * Time Complexity: O(N log N + N log M)
 * Space Complexity: O(N)
 *
 * where:
 * N = Number of elements
 * M = Maximum value in the array
 *
 * Explanation:
 * - Computing prefix maximums takes O(N).
 * - Each GCD computation takes O(log M).
 * - Sorting dominates with O(N log N).
 * - Pair formation is O(N).
 *
 * Alternative Approach:
 * 1. Simulation using a separate prefixGcd array
 *    - Build a dedicated prefixGcd array instead of modifying the
 *      input array.
 *    - The remaining algorithm stays the same.
 *
 * Time Complexity: O(N log N + N log M)
 * Space Complexity: O(N)
 */
class Solution {
    public long gcdSum(int[] nums) {
        int n = nums.length;
        int[] mx = new int[n];
        mx[0] = nums[0];
        for(int i = 1;i<n;i++){
            mx[i] = Math.max(mx[i-1],nums[i]);
        }

        for(int i = 0;i<n;i++){
            nums[i] = gcd(nums[i],mx[i]);
        }

        Arrays.sort(nums);

        int i = 0;
        int j = n-1;
        long ans = 0;
        while(i<j){
            ans+= (long)gcd(nums[i],nums[j]);
            i++;
            j--;
        }

        return ans;
    }
    int gcd(int a,int b){
        if(b>a) return gcd(b,a);
        int r = 0;
        while(b!=0){
            r = a%b;
            a = b;
            b = r;
        }
        return a;
    }
}
