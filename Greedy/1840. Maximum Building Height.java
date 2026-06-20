/**
# LeetCode 1840. Maximum Building Height

**Problem Link:** https://leetcode.com/problems/maximum-building-height/

## Approach 1: Greedy + Constraint Propagation + Math

Key Observations:

1. Building 1 always has height 0.
2. Adjacent buildings can differ in height by at most 1.
3. Restrictions must be adjusted so that all restrictions are mutually valid.
4. Perform:
   - Left → Right pass
   - Right → Left pass
5. For every pair of consecutive restrictions, calculate the maximum peak that can be formed between them.
6. Finally, handle buildings after the last restriction.

**Time Complexity:** O(M log M)

**Space Complexity:** O(M)

where:

* N = Total number of buildings
* M = Number of restrictions

The O(M log M) factor comes from sorting the restrictions.

*/
class Solution {
    public int maxBuilding(int n, int[][] restrictions) {

        int len = restrictions.length;

        if (len < 1) {
            return n - 1;
        }

        Arrays.sort(
                restrictions,
                (a, b) -> Integer.compare(a[0], b[0])
        );

        List<int[]> list = new ArrayList<>();

        list.add(new int[]{1, 0});

        for (int[] restriction : restrictions) {
            list.add(restriction);
        }

        // Left -> Right Constraint Propagation
        for (int i = 1; i < list.size(); i++) {

            list.get(i)[1] = Math.min(
                    list.get(i)[1],
                    list.get(i - 1)[1]
                            + (list.get(i)[0] - list.get(i - 1)[0])
            );
        }

        // Right -> Left Constraint Propagation
        for (int i = list.size() - 2; i >= 0; i--) {

            list.get(i)[1] = Math.min(
                    list.get(i)[1],
                    list.get(i + 1)[1]
                            + (list.get(i + 1)[0] - list.get(i)[0])
            );
        }

        int ans = 0;

        for (int i = 0; i < list.size() - 1; i++) {

            int x1 = list.get(i)[0];
            int h1 = list.get(i)[1];

            int x2 = list.get(i + 1)[0];
            int h2 = list.get(i + 1)[1];

            int dist = x2 - x1;
            int diff = Math.abs(h2 - h1);

            ans = Math.max(
                    ans,
                    Math.max(h1, h2)
                            + (dist - diff) / 2
            );
        }

        int[] last = list.get(list.size() - 1);

        ans = Math.max(
                ans,
                last[1] + (n - last[0])
        );

        return ans;
    }
}
