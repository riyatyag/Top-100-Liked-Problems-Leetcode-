// Problem Statement:
// Given an integer n, return the least number of perfect square numbers that sum to n.

// Approach:
// This is a dynamic programming problem. We use an array `dp` where `dp[i]` stores the minimum perfect squares needed to sum to `i`.
// Initialize `dp[0] = 0`. For `i > 0`, `dp[i]` is initialized to a large value (e.g., `i` itself, as `i` ones sum to `i`).
// Iterate `i` from 1 to `n`. For each `i`, iterate through all perfect squares `j*j` such that `j*j <= i`.
// `dp[i] = min(dp[i], 1 + dp[i - j*j])`.
// The result is `dp[n]`.

// Time Complexity:
// O(N * sqrt(N))

// Space Complexity:
// O(N)

// Optimal Solution:
class Solution {
    public int numSquares(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 0;

        for (int i = 1; i <= n; i++) {
            dp[i] = i; 
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j * j <= i; j++) {
                int square = j * j;
                dp[i] = Math.min(dp[i], 1 + dp[i - square]);
            }
        }
        return dp[n];
    }
}