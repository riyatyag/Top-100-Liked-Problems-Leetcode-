// Problem Statement:
// Given an integer array nums, return true if you can partition the array into two subsets such that the sum of the elements in both subsets is equal or false otherwise.

// Approach:
// This problem is equivalent to finding if there exists a subset of the given array `nums` that sums up to `totalSum / 2`.
// If the total sum of all elements in `nums` is odd, it's impossible to partition it into two equal sum subsets, so we can immediately return false.
// If the total sum is even, let `target = totalSum / 2`. The problem then reduces to a classic 0/1 Knapsack type problem: Can we find a subset of `nums` that sums exactly to `target`?

// We can use dynamic programming for this:
// 1. Calculate the `totalSum` of all elements in `nums`.
// 2. If `totalSum` is odd, return `false`.
// 3. Set `target = totalSum / 2`.
// 4. Create a boolean array `dp` of size `target + 1`. `dp[i]` will be true if a sum of `i` can be formed using a subset of `nums`, and false otherwise.
// 5. Initialize `dp[0]` to `true` (a sum of 0 can always be formed by taking no elements).
// 6. Iterate through each `num` in the `nums` array:
//    For each `num`, iterate backwards from `target` down to `num`:
//    `dp[j] = dp[j] || dp[j - num]`
//    The backward iteration is crucial to ensure that each `num` is used at most once (0/1 Knapsack property). If we iterate forwards, a number could be used multiple times within the same `dp` calculation for a given `j`.
// 7. After processing all numbers, `dp[target]` will tell us if a subset summing to `target` exists. Return `dp[target]`.

// Time Complexity:
// O(N * S) where N is the number of elements in `nums` and S is the `target` sum (which is `totalSum / 2`).
// The outer loop runs N times, and the inner loop runs S times.

// Space Complexity:
// O(S) for the `dp` array, where S is `totalSum / 2`.

// Optimal Solution:
class Solution {
    public boolean canPartition(int[] nums) {
        int total = 0;
        for (int num : nums) {
            total += num;
        }

        if (total % 2 != 0) {
            return false;
        }

        int target = total / 2;
        boolean[] dp = new boolean[target + 1];
        dp[0] = true; 

        for (int num : nums) {
            for (int j = target; j >= num; j--) {
                dp[j] = dp[j] || dp[j - num];
            }
        }
        
        return dp[target];
    }
}