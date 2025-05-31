// Problem Statement:
// You are given an integer array coins representing coins of different denominations and an integer amount representing a total amount of money.
// Return the fewest number of coins that you need to make up that amount. If that amount of money cannot be made up by any combination of the coins, return -1.
// You may assume that you have an infinite number of each kind of coin.

// Approach:
// This is a classic dynamic programming problem, specifically a variation of the unbounded knapsack problem or minimum coin change problem.
// We can define `dp[i]` as the minimum number of coins needed to make up the amount `i`.

// Initialization:
// - `dp[0]` should be 0, as 0 coins are needed to make up an amount of 0.
// - All other `dp[i]` (for `i > 0`) should be initialized to a value representing infinity (or a value greater than any possible answer, e.g., `amount + 1`). This is because initially, we don't know if these amounts can be formed, and we want to find the minimum.

// Recurrence Relation:
// To calculate `dp[i]`, we iterate through each `coin` in the `coins` array.
// If `i - coin` is a valid non-negative amount (meaning `i >= coin`), then we can potentially form amount `i` by using one `coin` and the minimum number of coins needed for `i - coin`.
// So, `dp[i] = min(dp[i], 1 + dp[i - coin])`
// We take the minimum because we want the fewest number of coins.

// After filling the `dp` array:
// `dp[amount]` will hold the minimum number of coins needed to make up the `amount`.
// If `dp[amount]` is still the "infinity" value (e.g., `amount + 1`), it means the `amount` cannot be made up by any combination of the given `coins`. In this case, return -1. Otherwise, return `dp[amount]`.

// Time Complexity:
// O(amount * N) where `amount` is the target amount and `N` is the number of coin denominations.
// We have an outer loop running `amount` times, and an inner loop running `N` times.

// Space Complexity:
// O(amount) for the `dp` array.

// Optimal Solution:
class Solution {
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        for (int i = 1; i <= amount; i++) {
            dp[i] = amount + 1;
        }

        dp[0] = 0; 

        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                if (i >= coin) { 
                    dp[i] = Math.min(dp[i], 1 + dp[i - coin]);
                }
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }
}