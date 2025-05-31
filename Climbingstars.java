// Problem Statement:
// You are climbing a staircase. It takes n steps to reach the top.
// Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?

// Approach:
// This is a classic dynamic programming problem that can be solved using Fibonacci sequence principles.
// Let `dp[i]` be the number of distinct ways to reach step `i`.

// To reach step `i`, you can either:
// 1. Take 1 step from `i-1`. The number of ways to do this is `dp[i-1]`.
// 2. Take 2 steps from `i-2`. The number of ways to do this is `dp[i-2]`.

// So, the total number of ways to reach step `i` is `dp[i] = dp[i-1] + dp[i-2]`.

// Base Cases:
// - `dp[1] = 1` (1 way to reach step 1: 1 step)
// - `dp[2] = 2` (2 ways to reach step 2: 1+1 or 2 steps)

// We can optimize space from O(N) to O(1) by only keeping track of the previous two values (`first` and `second`).

// Algorithm:
// 1. Handle base cases:
//    - If `n = 1`, return 1.
//    - If `n = 2`, return 2.
// 2. Initialize `first = 1` (ways to reach step 1) and `second = 2` (ways to reach step 2).
// 3. Iterate from `i = 3` to `n`:
//    a. `current = first + second` (ways to reach current step `i`)
//    b. `first = second` (update for next iteration)
//    c. `second = current` (update for next iteration)
// 4. Return `second` (which will hold the ways to reach step `n`).

// Time Complexity:
// O(N) because we iterate from 3 up to N.

// Space Complexity:
// O(1) as we only use a few variables to store the previous two results.

// Optimal Solution:
class Solution {
    public int climbStairs(int n) {
        if (n == 1) {
            return 1;
        }
        int first = 1; 
        int second = 2; 

        for (int i = 3; i <= n; i++) {
            int current = first + second;
            first = second;
            second = current;
        }

        return second; 
    }
}