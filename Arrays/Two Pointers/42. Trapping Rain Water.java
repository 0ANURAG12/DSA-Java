/*
 * LeetCode: 42. Trapping Rain Water
 * https://leetcode.com/problems/trapping-rain-water/
 *
 * ------------------------------------------------------------
 * Approach 1: Two Pointers
 * ------------------------------------------------------------
 * Intuition:
 * - Maintain two pointers at the leftmost and rightmost positions.
 * - Keep track of the maximum height seen so far from both sides.
 * - The amount of water trapped at a position depends on the smaller
 *   of the two maximum heights.
 * - Move the pointer with the smaller height inward because its water
 *   level is already determined.
 * - Accumulate the trapped water while traversing the array.
 *
 * Time Complexity: O(N)
 * Space Complexity: O(1)
 *
 * where:
 * N = Number of bars.
 *
 * Alternative Approach:
 * 1. Monotonic Stack
 *    - Maintain a decreasing stack of indices.
 *    - Whenever a taller bar is found, compute the trapped water
 *      between the current bar and the previous taller bar.
 *
 * Time Complexity: O(N)
 * Space Complexity: O(N)
 */
class Solution {
    public int trap(int[] height) {
       int left = 0;
       int right = height.length - 1;
       int leftMax = 0;
       int rightMax = 0;
       int sum=0;

        while (left < right) {
            if (height[left] <= height[right]){
                if(height[left]< leftMax) sum += leftMax-height[left];
                else leftMax= height[left];
                left++;
            }
            else {
                if(height[right]< rightMax) sum += rightMax-height[right];
                else rightMax= height[right];
                right--;
            }
        } 
        return sum;
    }    
}

/*
 * ------------------------------------------------------------
 * Approach 2: Stack
 * ------------------------------------------------------------
 * Intuition:
 * - Traverse the array while maintaining information about the
 *   right boundary using a stack.
 * - For each position, determine the effective left and right
 *   boundaries.
 * - The trapped water at the current position equals the minimum
 *   boundary height minus the current bar height.
 * - Accumulate the trapped water over the entire array.
 *
 * Time Complexity: O(N)
 * Space Complexity: O(N)
 *
 * where:
 * N = Number of bars.
 *
 * Alternative Approach:
 * 1. Two Pointers
 *    - Maintain leftMax and rightMax while moving two pointers
 *      toward each other.
 *    - Requires only constant extra space.
 *
 * Time Complexity: O(N)
 * Space Complexity: O(1)
 */
class Solution {
    public int trap(int[] height) {
        int n = height.length;
        Stack<Integer> stk = new Stack<>();
        stk.push(height[n-1]);
        for(int i = n-2;i>0;i--){
            if(stk.peek()<=height[i]) stk.push(height[i]);
        }

        int water = 0;
        int prevH = height[0];
        for(int i = 1;i<n;i++){
            int curH = height[i];
            if(prevH>curH && !stk.isEmpty()){
                int l = Math.min(prevH,stk.peek())-curH;
                water += l;
            } 
            if(!stk.isEmpty() && curH == stk.peek()) stk.pop();
            if(prevH<curH) prevH = curH;
        }

        return water;
    }
}
