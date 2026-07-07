/*
 * LeetCode: 3794. Sum and Multiply
 * https://leetcode.com/problems/sum-and-multiply/
 *
 * ------------------------------------------------------------
 * Approach: Math + Digit Manipulation
 * ------------------------------------------------------------
 * Intuition:
 * - Traverse the digits of the given number.
 * - Compute the sum of all non-zero digits.
 * - Simultaneously build a number consisting only of the non-zero digits.
 * - Reverse the constructed number to restore the original digit order.
 * - Return the product of the digit sum and the reconstructed number.
 *
 * Time Complexity: O(D)
 * Space Complexity: O(1)
 *
 * where:
 * D = Number of digits in the input number.
 *
 * Alternative Approach:
 * 1. StringBuilder
 *    - Convert the number to a string.
 *    - Traverse each character, accumulate the digit sum, and append
 *      non-zero digits to a StringBuilder.
 *    - Convert the resulting string back to an integer and compute
 *      the required product.
 *
 * Time Complexity: O(D)
 * Space Complexity: O(D)
 */

class Solution {
    public long sumAndMultiply(int n) {
        int x = 0;
        int t = n;
        int s = 0;

        while (t > 0) {
            int d = t % 10;
            if (d > 0) {
                x = x * 10 + d;
                s += d;
            }
            t /= 10;
        }

        n = 0;

        while (x > 0) {
            int d = x % 10;
            if (d > 0) {
                n = n * 10 + d;
            }
            x /= 10;
        }

        return s * 1L * n;
    }
}

/*
 * ------------------------------------------------------------
 * Approach: Math + Digit Manipulation (Single Pass)
 * ------------------------------------------------------------
 * Intuition:
 * - Traverse the digits of the number from right to left.
 * - Ignore all zero digits.
 * - Add every non-zero digit to the running sum.
 * - Simultaneously construct the new number by placing each non-zero
 *   digit at its correct position using a place-value multiplier.
 * - Finally, return the product of the reconstructed number and the
 *   sum of its digits.
 *
 * Time Complexity: O(D)
 * Space Complexity: O(1)
 *
 * where:
 * D = Number of digits in the input number.
 *
 * Alternative Approach:
 * 1. Two-Pass Digit Manipulation
 *    - First, build the non-zero number in reverse while computing
 *      the digit sum.
 *    - Reverse the constructed number in a second pass to restore
 *      the original digit order.
 *    - Time Complexity: O(D)
 *    - Space Complexity: O(1)
 */
class Solution {
    public long sumAndMultiply(int n) {
        int x=0;
        int sum=0;
        int i=1;
        while(n>0){
            int m=n % 10;
            n /=10;
            if(m == 0) continue;
            sum += m;
            x += (m * i);
            i *=10;
        }
        return (long) x * sum;
    }
}

this approach also
