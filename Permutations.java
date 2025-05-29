// Problem Statement:
// Given an array nums of distinct integers, return all the possible permutations. You can return the answer in any order.
//
// Example 1:
// Input: nums = [1,2,3]
// Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
//
// Example 2:
// Input: nums = [0,1]
// Output: [[0,1],[1,0]]
//
// Example 3:
// Input: nums = [1]
// Output: [[1]]
//
// Constraints:
// 1 <= nums.length <= 6
// -10 <= nums[i] <= 10
// All the integers of nums are unique.

// Approach:
// This is a classic backtracking problem. We want to generate all possible arrangements of the elements in the input array `nums`.
// Since the elements are distinct, we don't have to worry about duplicate permutations.
//
// We can use a recursive helper function (backtrack) that builds a permutation step by step.
// We maintain a `currentPermutation` list and a `used` boolean array to keep track of which elements from `nums` have already been included in the `currentPermutation`.
//
// The `permute` function initializes the `result` list and calls the `backtrack` helper.
//
// The `backtrack` function takes:
// - `result`: The list to store all valid permutations.
// - `currentPermutation`: The permutation being built.
// - `nums`: The original array of numbers.
// - `used`: A boolean array of the same size as `nums`, where `used[i]` is true if `nums[i]` is already in `currentPermutation`.
//
// Base Case: If the `currentPermutation` list's size equals the length of `nums`, it means we have formed a complete permutation.
// In this case, we add a new `ArrayList` copied from `currentPermutation` to the `result` list.
//
// Recursive Step: Iterate through each element `nums[i]` in the original `nums` array.
// 1. Check if `nums[i]` has already been used (`!used[i]`).
// 2. If it hasn't been used:
//    a. Add `nums[i]` to `currentPermutation`.
//    b. Mark `used[i]` as true.
//    c. Recursively call `backtrack` to continue building the permutation.
//    d. Backtrack: Remove `nums[i]` from `currentPermutation` and mark `used[i]` as false. This is crucial to explore other branches (permutations).

// Time Complexity:
// There are N! (N factorial) permutations for an array of N distinct elements.
// For each permutation, we perform operations (adding to a list, copying a list) that take O(N) time.
// Therefore, the total time complexity is O(N * N!).
// Given N <= 6, 6! = 720, so 6 * 720 = 4320 operations, which is very efficient.

// Space Complexity:
// The space complexity is O(N) for the recursion stack depth.
// O(N) for the `currentPermutation` list.
// O(N) for the `used` boolean array.
// O(N * N!) in the worst case for storing all permutations in the `result` list (each permutation is a list of N elements, and there are N! permutations).
// So, the total space complexity is dominated by the output, which is O(N * N!).

class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        boolean[] used = new boolean[nums.length];
        backtrack(result, new ArrayList<>(), nums, used);
        return result;
    }

    private void backtrack(List<List<Integer>> result, List<Integer> currentPermutation, int[] nums, boolean[] used) {
        if (currentPermutation.size() == nums.length) {
            result.add(new ArrayList<>(currentPermutation));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (!used[i]) {
                currentPermutation.add(nums[i]);
                used[i] = true; 
                backtrack(result, currentPermutation, nums, used);
                currentPermutation.remove(currentPermutation.size() - 1);
                used[i] = false;
            }
        }
    }
}