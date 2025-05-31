// Problem Statement:
// Given an integer array nums, return the length of the longest strictly increasing subsequence.

// Approach:
// This is a classic dynamic programming problem.

// Approach 1: Dynamic Programming (O(N^2) time complexity)
// 1. Define `dp[i]` as the length of the longest strictly increasing subsequence ending at `nums[i]`.
// 2. Initialize `dp[i]` to 1 for all `i`, because each number itself forms a subsequence of length 1.
// 3. Iterate through the array `nums` from `i = 0` to `n-1`.
// 4. For each `nums[i]`, iterate through all previous elements `nums[j]` where `j < i`.
// 5. If `nums[i] > nums[j]`, it means `nums[i]` can extend the increasing subsequence ending at `nums[j]`.
//    So, we can update `dp[i] = Math.max(dp[i], 1 + dp[j])`.
// 6. Keep track of the `maxLength` encountered in the `dp` array.
// 7. The final `maxLength` will be the answer.

// Time Complexity (O(N^2)):
// The nested loops iterate N times each, resulting in O(N^2) operations.

// Space Complexity (O(N)):
// O(N) for the `dp` array.

// Approach 2: Dynamic Programming with Binary Search (O(N log N) time complexity)
// This is a more optimized approach.
// The key idea is to maintain a `tails` array, where `tails[i]` stores the smallest tail of all increasing subsequences of length `i+1`.
// - `tails` will always be sorted.
// - When we iterate through `nums`:
//   - If `num` is greater than all elements in `tails`, it means we can extend the longest increasing subsequence found so far. We append `num` to `tails`.
//   - If `num` is not greater than all elements in `tails`, we find the first element in `tails` that is greater than or equal to `num` using binary search. We replace that element with `num`. This is because `num` can form an increasing subsequence of the same length but with a smaller ending value, which is more "favorable" for future extensions.
// The length of the `tails` array at the end will be the length of the longest increasing subsequence.

// Time Complexity (O(N log N)):
// We iterate through `nums` once (N times).
// Inside the loop, we perform a binary search, which takes O(log N) time.
// Total time complexity: O(N log N).

// Space Complexity (O(N)):
// O(N) for the `tails` array in the worst case (e.g., if all numbers are increasing, `tails` will store all numbers).

// Optimal Solution (using O(N log N) approach):

import java.util.Arrays;

class Solution {
    public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int[] tails = new int[nums.length];
        int size = 0; 

        for (int num : nums) {
            int low = 0;
            int high = size; 
            
            while (low < high) {
                int mid = low + (high - low) / 2;
                if (tails[mid] < num) {
                    low = mid + 1;
                } else {
                    high = mid;
                }
            }
            tails[low] = num;
            if (low == size) {
                size++;
            }
        }
        return size;
    }
}