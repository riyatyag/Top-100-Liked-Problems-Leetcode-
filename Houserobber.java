// Problem Statement:
// You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed, the only constraint stopping you from robbing each of them is that adjacent houses have security systems connected and it will automatically contact the police if two adjacent houses were broken into on the same night.
// Given an integer array nums representing the amount of money of each house, return the maximum amount of money you can rob tonight without alerting the police.

// Approach:
// This is a dynamic programming problem. Let `dp[i]` be the maximum amount of money that can be robbed from the first `i` houses.
// For each house `i`, we have two choices:
// 1. Rob house `i`: In this case, we cannot rob house `i-1`. So, the total money would be `nums[i] + dp[i-2]`.
// 2. Don't rob house `i`: In this case, we can rob house `i-1`. So, the total money would be `dp[i-1]`.
// We choose the maximum of these two options: `dp[i] = max(nums[i] + dp[i-2], dp[i-1])`.

// Base Cases:
// - `dp[0] = nums[0]` (if there's only one house, rob it)
// - `dp[1] = max(nums[0], nums[1])` (if there are two houses, rob the one with more money)

// Optimization:
// Notice that `dp[i]` only depends on `dp[i-1]` and `dp[i-2]`. We can optimize space complexity from O(N) to O(1) by using only two variables to store the previous two maximums.

// Let `prev1` be `dp[i-1]` (max money robbing up to house `i-1`)
// Let `prev2` be `dp[i-2]` (max money robbing up to house `i-2`)

// For current house `i`:
// `current_max = max(prev1, prev2 + nums[i])`
// Then update `prev2 = prev1` and `prev1 = current_max` for the next iteration.

// Time Complexity:
// O(N) where N is the number of houses. We iterate through the array once.

// Space Complexity:
// O(1) as we only use a few variables to store previous results.

// Optimal Solution:
class Solution {
    public int rob(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        int prev2 = 0; 
        int prev1 = nums[0]; 

        for (int i = 1; i < nums.length; i++) {
            int current_max = Math.max(prev1, prev2 + nums[i]);
            prev2 = prev1;
            prev1 = current_max;
        }

        return prev1;
    }
}