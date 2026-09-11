/*
 * LeetCode: 3483. Unique 3-Digit Even Numbers
 * https://leetcode.com/problems/unique-3-digit-even-numbers/
 *
 * Topic: Arrays / Enumeration
 *
 *
 * ============================================================
 * APPROACH 1: Brute Force + HashSet
 * ============================================================
 *
 * Intuition:
 * - We need to form all possible 3-digit even numbers.
 *
 * - Use three nested loops to choose:
 *
 *      1. Hundreds digit
 *      2. Tens digit
 *      3. Units digit
 *
 * - The same index cannot be used more than once, so we skip
 *   cases where any two indices are equal.
 *
 * - A valid number must:
 *
 *      1. Be a 3-digit number  -> num >= 100
 *      2. Be even              -> num % 2 == 0
 *
 * - Since the input can contain duplicate digits, the same
 *   number may be generated multiple times.
 *
 * - Use a HashSet to store only unique numbers.
 *
 *
 * Time Complexity: O(n³)
 * Space Complexity: O(n³)
 *
 * Why O(n³)?
 * - We use three nested loops.
 * - Each loop can iterate up to n times.
 * - Therefore, there are at most O(n³) combinations.
 *
 * Space:
 * - The HashSet stores all unique 3-digit numbers.
 * - Since there are only finitely many 3-digit numbers,
 *   this can also be considered O(1) with respect to the
 *   input size because the maximum number of possible values
 *   is bounded by 900.
 */

class BruteForce {

    public int totalNumbers(int[] digits) {

        int n = digits.length;

        Set<Integer> set = new HashSet<>();

        for (int i = 0; i < n; i++) {

            for (int j = 0; j < n; j++) {

                for (int k = 0; k < n; k++) {

                    // The same digit index cannot be reused
                    if (i == j || i == k || j == k) {
                        continue;
                    }

                    // Form the 3-digit number
                    int num = digits[i] * 100
                            + digits[j] * 10
                            + digits[k];

                    // Valid number must be 3-digit and even
                    if (num >= 100 && num % 2 == 0) {
                        set.add(num);
                    }
                }
            }
        }

        return set.size();
    }
}
