// Problem Statement:
// You are given an integer array nums. You are initially positioned at the array's first index, and each element in the array represents your maximum jump length at that position.
// Return true if you can reach the last index, or false otherwise.

// Approach:
// This problem can be solved using a greedy approach. We want to determine if the last index is reachable.
// The idea is to keep track of the `farthest` index that can be reached so far.
// We iterate through the array from the first index (index 0) up to the `farthest` reachable index.
// For each index `i`, we calculate the maximum reachable index from `i`, which is `i + nums[i]`.
// We then update our global `farthest` reachable index with the maximum of its current value and `i + nums[i]`.
// If at any point, the current index `i` becomes greater than `farthest`, it means we cannot reach the current index `i` (and thus any subsequent indices), so we can immediately return `false`.
// If we successfully iterate through the entire array (meaning `i` never exceeded `farthest` before `i` reached `nums.length - 1`), it implies that the last index is reachable.

// Algorithm Steps:
// 1. Initialize `farthest = 0`. This represents the maximum index we can reach starting from index 0.
// 2. Iterate through the array with index `i` from `0` to `nums.length - 1`.
// 3. Inside the loop:
//    a. Check if the current index `i` is beyond the `farthest` reachable index. If `i > farthest`, it means we are stuck and cannot reach the current position or any further. In this case, return `false`.
//    b. Update `farthest`: `farthest = Math.max(farthest, i + nums[i])`. We take the maximum of the current `farthest` and the maximum index reachable from the current position `i`.
//    c. An optimization: If `farthest` already reaches or exceeds the last index (`nums.length - 1`), we know we can reach the end, so we can return `true` early. This is not strictly necessary for correctness but can improve performance in some cases.
// 4. If the loop completes, it means we have successfully traversed the array and `farthest` was always at least `i`, implying the last index is reachable. Return `true`.

// Example Walkthrough: nums = [2,3,1,1,4]
// N = 5
// farthest = 0
// i = 0: nums[0] = 2. i (0) <= farthest (0). farthest = max(0, 0+2) = 2.
// i = 1: nums[1] = 3. i (1) <= farthest (2). farthest = max(2, 1+3) = 4.
// i = 2: nums[2] = 1. i (2) <= farthest (4). farthest = max(4, 2+1) = 4.
// i = 3: nums[3] = 1. i (3) <= farthest (4). farthest = max(4, 3+1) = 4.
// i = 4: nums[4] = 4. i (4) <= farthest (4). farthest = max(4, 4+4) = 8.
// Loop ends. Return true.

// Example Walkthrough: nums = [3,2,1,0,4]
// N = 5
// farthest = 0
// i = 0: nums[0] = 3. i (0) <= farthest (0). farthest = max(0, 0+3) = 3.
// i = 1: nums[1] = 2. i (1) <= farthest (3). farthest = max(3, 1+2) = 3.
// i = 2: nums[2] = 1. i (2) <= farthest (3). farthest = max(3, 2+1) = 3.
// i = 3: nums[3] = 0. i (3) <= farthest (3). farthest = max(3, 3+0) = 3.
// i = 4: i (4) > farthest (3). Return false.

// Time Complexity:
// O(N), where N is the length of the `nums` array.
// We iterate through the array at most once.

// Space Complexity:
// O(1), as we only use a few constant-space variables.

class Solution {
    public boolean canJump(int[] nums) {
        int farthest = 0; 

        for (int i = 0; i < nums.length; i++) {
            if (i > farthest) {
                return false;
            }
            farthest = Math.max(farthest, i + nums[i]);

            if (farthest >= nums.length - 1) {
                return true;
            }
        }
        return true;
    }
}