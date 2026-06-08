/*
LeetCode #4 - Median of Two Sorted Arrays
https://leetcode.com/problems/median-of-two-sorted-arrays/

Topic: Binary Search
Difficulty: Hard
*/

--------------------------------------------------
Approach 1: Brute Force
Time Complexity: O(m+n)
Space Complexity: O(m+n)

Merge both arrays into a temporary array
and then calculate the median.
--------------------------------------------------

class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;

        int[] temp = new int[m + n + 1];

        int i = 0, j = 0, idx = 0;

        while (i < m && j < n) {
            if (nums1[i] <= nums2[j])
                temp[idx++] = nums1[i++];
            else
                temp[idx++] = nums2[j++];
        }

        while (i < m)
            temp[idx++] = nums1[i++];

        while (j < n)
            temp[idx++] = nums2[j++];

        if (idx % 2 != 0)
            return temp[idx / 2];

        return (temp[idx / 2] + temp[(idx / 2) - 1]) / 2.0;
    }
}

--------------------------------------------------
Approach 2: Better Solution
Time Complexity: O(m+n)
Space Complexity: O(1)

Track only the median positions instead of
creating the merged array.
--------------------------------------------------

class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {

        int m = nums1.length;
        int n = nums2.length;

        int size = m + n;

        int e1 = 0;
        int e2 = 0;

        int i = 0;
        int j = 0;
        int k = 0;

        int idx1 = (size / 2) - 1;
        int idx2 = size / 2;

        while (i < m && j < n) {

            if (nums1[i] < nums2[j]) {

                if (k == idx1)
                    e1 = nums1[i];

                if (k == idx2)
                    e2 = nums1[i];

                i++;

            } else {

                if (k == idx1)
                    e1 = nums2[j];

                if (k == idx2)
                    e2 = nums2[j];

                j++;
            }

            k++;
        }

        while (i < m) {

            if (k == idx1)
                e1 = nums1[i];

            if (k == idx2)
                e2 = nums1[i];

            i++;
            k++;
        }

        while (j < n) {

            if (k == idx1)
                e1 = nums2[j];

            if (k == idx2)
                e2 = nums2[j];

            j++;
            k++;
        }

        if (size % 2 == 1)
            return e2;

        return (e1 + e2) / 2.0;
    }
}

--------------------------------------------------
Approach 3: Optimal Solution
Time Complexity: O(log(min(m,n)))
Space Complexity: O(1)

Binary Search on Partition
--------------------------------------------------


class Solution {

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {

        int m = nums1.length;
        int n = nums2.length;

        if (m > n)
            return findMedianSortedArrays(nums2, nums1);

        int l = 0;
        int r = m;

        while (l <= r) {

            int px = l + (r - l) / 2;
            int py = ((m + n + 1) / 2) - px;

            int x1 = (px == 0) ? Integer.MIN_VALUE : nums1[px - 1];
            int x2 = (py == 0) ? Integer.MIN_VALUE : nums2[py - 1];

            int x3 = (px == m) ? Integer.MAX_VALUE : nums1[px];
            int x4 = (py == n) ? Integer.MAX_VALUE : nums2[py];

            if (x1 <= x4 && x2 <= x3) {

                if ((m + n) % 2 == 0)
                    return (Math.max(x1, x2) + Math.min(x3, x4)) / 2.0;

                return Math.max(x1, x2);

            } else if (x1 > x4) {

                r = px - 1;

            } else {

                l = px + 1;
            }
        }

        return -1;
    }
}
