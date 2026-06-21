/**
# LeetCode 1833. Maximum Ice Cream Bars

**Problem Link:** https://leetcode.com/problems/maximum-ice-cream-bars/

## Approach 1: Greedy + Sorting

Sort the ice cream costs in ascending order.

Always buy the cheapest ice cream first to maximize the total number
of ice creams that can be purchased.

Keep buying until there are not enough coins remaining.

**Time Complexity:** O(N log N)

**Space Complexity:** O(1)

where:

* N = Number of ice cream bars

*/
class Solution {

    public int maxIceCream(int[] costs, int coins) {

        Arrays.sort(costs);

        int ans = 0;

        for (int cost : costs) {

            if (coins >= cost) {
                coins -= cost;
                ans++;
            } else {
                break;
            }
        }

        return ans;
    }
}

/*

---

## Approach 2: Counting Sort (Optimal)

Since:

1 <= costs[i] <= 100000

Count the frequency of each cost and greedily buy from the
lowest cost to the highest cost.

Avoids sorting the entire array.

**Time Complexity:** O(N + M)

**Space Complexity:** O(M)

where:

* N = Number of ice cream bars
* M = Maximum cost value

*/

class Solution {

    public int maxIceCream(int[] costs, int coins) {

        int maxCost = 0;

        for (int cost : costs) {
            maxCost = Math.max(maxCost, cost);
        }

        int[] freq = new int[maxCost + 1];

        for (int cost : costs) {
            freq[cost]++;
        }

        int ans = 0;

        for (int cost = 1; cost <= maxCost; cost++) {

            if (freq[cost] == 0) {
                continue;
            }

            int canBuy = Math.min(
                    freq[cost],
                    coins / cost
            );

            ans += canBuy;
            coins -= canBuy * cost;

            if (coins < cost) {
                break;
            }
        }

        return ans;
    }
}
