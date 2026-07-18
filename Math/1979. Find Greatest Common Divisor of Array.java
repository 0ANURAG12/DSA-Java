/*
 * LeetCode: 1979. Find Greatest Common Divisor of Array
 * https://leetcode.com/problems/find-greatest-common-divisor-of-array/
 *
 * ------------------------------------------------------------
 * Approach: Math + Euclidean Algorithm
 * ------------------------------------------------------------
 * Intuition:
 * - Traverse the array once to find the minimum and maximum elements.
 * - The GCD of the entire array is equal to the GCD of its minimum
 *   and maximum elements.
 * - Use the Euclidean Algorithm to efficiently compute the GCD.
 *
 * Time Complexity: O(N + log M)
 * Space Complexity: O(1)
 *
 * where:
 * N = Number of elements in the array.
 * M = Maximum value among the array elements.
 *
 * Explanation:
 * - Finding the minimum and maximum requires one traversal of the array.
 * - The Euclidean Algorithm computes the GCD in O(log M).
 *
 * Alternative Approach:
 * 1. Iterative GCD
 *    - Compute the GCD of all elements one by one.
 *    - Initialize the answer with the first element and repeatedly
 *      update it using the Euclidean Algorithm.
 *
 * Time Complexity: O(N log M)
 * Space Complexity: O(1)
 */
class Solution {
    public int findGCD(int[] nums) {
        int min = 1000;
        int max = 1;
        for(int n: nums){
            min = Math.min(min,n);
            max = Math.max(max,n);
        }
        int ans =gcd(min,max);
        return ans;
    }
    int gcd(int a,int b){
        if(b>a) return gcd(b,a);
        while(b!=0){
            int r = a%b;
            a = b;
            b = r;
        }
        return a;
    }
}
