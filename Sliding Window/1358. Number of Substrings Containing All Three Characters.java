/**
# LeetCode 1358. Number of Substrings Containing All Three Characters

**Problem Link:** https://leetcode.com/problems/number-of-substrings-containing-all-three-characters/

## Approach 1: Brute Force

Generate every possible substring.

For each substring:

- Count the occurrences of 'a', 'b', and 'c'.
- If all three characters are present, increment the answer.

**Time Complexity:** O(N²)

**Space Complexity:** O(1)

where:

* N = Length of the input string

*/
class Solution {

    public int numberOfSubstrings(String s) {

        int n = s.length();
        int ans = 0;

        for (int i = 0; i < n; i++) {

            boolean a = false;
            boolean b = false;
            boolean c = false;

            for (int j = i; j < n; j++) {

                if (s.charAt(j) == 'a') a = true;
                else if (s.charAt(j) == 'b') b = true;
                else c = true;

                if (a && b && c) {
                    ans++;
                }
            }
        }

        return ans;
    }
}

/*

---

## Approach 2: Sliding Window

Maintain a sliding window using two pointers.

Expand the right pointer until the current window contains:

- 'a'
- 'b'
- 'c'

Once all three characters are present:

- Every substring ending after the current right pointer is also valid.
- Therefore, add:

n - right + 1

to the answer.

Then shrink the window from the left and repeat.

**Time Complexity:** O(N)

**Space Complexity:** O(1)

where:

* N = Length of the input string

*/

class Solution {

    public int numberOfSubstrings(String s) {

        int n = s.length();

        int[] cnt = new int[3];

        int right = 0;
        int ans = 0;

        for (int left = 0; left < n; left++) {

            while (right < n &&
                  (cnt[0] == 0 ||
                   cnt[1] == 0 ||
                   cnt[2] == 0)) {

                cnt[s.charAt(right) - 'a']++;

                right++;
            }

            if (cnt[0] > 0 &&
                cnt[1] > 0 &&
                cnt[2] > 0) {

                ans += n - right + 1;
            }

            cnt[s.charAt(left) - 'a']--;
        }

        return ans;
    }
}
