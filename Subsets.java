// Problem Statement:
// Given an integer array nums of unique elements, return all possible subsets (the power set).
// The solution set must not contain duplicate subsets. Return the solution in any order.
//
// Example 1:
// Input: nums = [1,2,3]
// Output: [[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
//
// Example 2:
// Input: nums = [0]
// Output: [[],[0]]
//
// Constraints:
// 1 <= nums.length <= 10
// -10 <= nums[i] <= 10
// All the numbers of nums are unique.

// Approach:
// This problem is a classic backtracking problem. We want to generate all possible combinations of elements from the input array `nums`.
// The idea is to build subsets incrementally. At each step, for an element `nums[i]`, we have two choices:
// 1. Include `nums[i]` in the current subset.
// 2. Exclude `nums[i]` from the current subset.
//
// We can use a recursive helper function (backtrack) that takes the current index, the array `nums`, the current subset being built, and the final list of all subsets.
//
// The base case for the recursion is when the `start` index reaches the end of the `nums` array. At this point, the `currentSubset` is a valid subset, so we add a copy of it to our `result` list.
//
// In the recursive step, we iterate from the `start` index to the end of the `nums` array.
// For each element `nums[i]`:
// 1. Add `nums[i]` to `currentSubset`.
// 2. Recursively call `backtrack` with `i + 1` (to consider the next elements).
// 3. Remove `nums[i]` from `currentSubset` (backtrack) to explore other possibilities (i.e., subsets that do not contain `nums[i]`).
//
// Another way to think about it (and often implemented) is to consider each element `nums[i]` and decide whether to include it or not.
//
// Initial call: `backtrack(0, nums, new ArrayList<>(), result)`
//
// Alternative backtracking structure (more common for subset generation):
// The `backtrack` function first adds the `currentSubset` to the `result` list (representing a valid subset found at that point).
// Then, it iterates from the `start` index. For each element `nums[i]`, it adds `nums[i]` to `currentSubset`,
// makes a recursive call with `i + 1`, and then removes `nums[i]` to backtrack.
// This ensures that all subsets, including the empty set, are generated.

// Time Complexity:
// There are 2^N possible subsets for an array of N elements. For each subset, we perform operations (adding to a list, copying a list) that take O(N) time in the worst case (e.g., for the subset containing all N elements).
// Therefore, the total time complexity is O(N * 2^N).
// Given N <= 10, N * 2^N is approximately 10 * 1024 = 10240 operations, which is well within limits.

// Space Complexity:
// The space complexity is O(N) for the recursion stack depth.
// Additionally, storing the `result` list requires O(N * 2^N) space in the worst case, as there are 2^N subsets, and each subset can contain up to N elements.
// So, the total space complexity is effectively O(N * 2^N) if we consider the output storage.
// If we only consider the auxiliary space (excluding the output), it's O(N) for the `currentSubset` and recursion stack.

class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(0, nums, new ArrayList<>(), result);
        return result;
    }

    private void backtrack(int start, int[] nums, List<Integer> currentSubset, List<List<Integer>> result) {
        result.add(new ArrayList<>(currentSubset));
        for (int i = start; i < nums.length; i++) {
            currentSubset.add(nums[i]);
            backtrack(i + 1, nums, currentSubset, result);
            currentSubset.remove(currentSubset.size() - 1);
        }
    }
}