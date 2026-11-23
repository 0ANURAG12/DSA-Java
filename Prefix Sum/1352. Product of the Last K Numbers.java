/*
 * LeetCode: 1352. Product of the Last K Numbers
 * https://leetcode.com/problems/product-of-the-last-k-numbers/
 *
 * Topic: Arrays / Prefix Product / Design
 *
 * ============================================================
 * APPROACH 1: Prefix Product + ArrayList
 * ============================================================
 *
 * Intuition:
 * - Instead of calculating the product of the last k numbers
 *   every time getProduct(k) is called, we maintain prefix
 *   products while adding elements.
 *
 * - Each element in the list stores the product of all numbers
 *   added since the most recent zero.
 *
 * - When a new number is added:
 *
 *   Case 1: If num == 0:
 *   Clear the list because every product containing this zero
 *   will be equal to 0.
 *
 *   Case 2: If the list is empty:
 *   Add num directly because it is the first number after
 *   initialization or the most recent zero.
 *
 *   Case 3: Otherwise:
 *   Multiply num with the previous prefix product and add
 *   the result to the list.
 *
 * - To calculate getProduct(k):
 *
 *   Case 1: If the list is empty or k > n:
 *   Return 0 because the requested range contains a zero.
 *
 *   Case 2: If k == n:
 *   Return the last prefix product directly.
 *
 *   Case 3: Otherwise:
 *   Divide the last prefix product by the prefix product
 *   immediately before the last k elements.
 *
 *   Formula:
 *   Product of last k numbers =
 *   prefix[n - 1] / prefix[n - 1 - k]
 *
 * Example:
 * Operations:
 *
 * add(3)
 * add(0)
 * add(2)
 * add(5)
 * add(4)
 *
 * Prefix Product List:
 * [2, 10, 40]
 *
 * Explanation:
 * - 3 is added.
 * - 0 clears the list.
 * - 2  -> [2]
 * - 5  -> [2, 10]
 * - 4  -> [2, 10, 40]
 *
 * getProduct(2):
 * = 40 / 2
 * = 20
 *
 * getProduct(3):
 * = 40
 *
 * getProduct(4):
 * = 0 (because the requested range includes zero)
 *
 * Time Complexity:
 *
 * - add(num): O(1)
 *   Clearing the ArrayList when num == 0 takes O(n) in Java,
 *   so this operation is O(n) in the worst case.
 *
 * - getProduct(k): O(1)
 *   Uses prefix product division to calculate the answer.
 *
 * Space Complexity: O(n)
 * - Stores prefix products of all numbers added since the
 *   most recent zero.
 *
 */

class ProductOfNumbers {

    ArrayList<Integer> list;

    public ProductOfNumbers() {
        list = new ArrayList<>();
    }

    public void add(int num) {

        int n = list.size();

        // If the number is zero, clear all previous
        // prefix products because any product containing
        // zero will be zero.
        if (num == 0) {
            list.clear();
        }

        // If the list is empty, add the number directly.
        else if (n < 1) {
            list.add(num);
        }

        // Otherwise, calculate the new prefix product.
        else {
            list.add(num * list.get(n - 1));
        }
    }

    public int getProduct(int k) {

        int n = list.size();

        // If the list is empty or k exceeds the number
        // of stored elements, the requested range contains zero.
        if (n < 1 || k > n) {
            return 0;
        }

        // If k equals the total number of stored elements,
        // return the complete prefix product.
        else if (k == n) {
            return list.get(n - 1);
        }

        // Divide the latest prefix product by the prefix
        // product before the last k elements.
        else {
            return list.get(n - 1) / list.get(n - 1 - k);
        }
    }
}

/**
 * Your ProductOfNumbers object will be instantiated and called as such:
 *
 * ProductOfNumbers obj = new ProductOfNumbers();
 * obj.add(num);
 * int param_2 = obj.getProduct(k);
 */
