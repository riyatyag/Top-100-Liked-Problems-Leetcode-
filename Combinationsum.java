// Problem Statement:
// Given an array of distinct integers candidates and a target integer target, return a list of all unique combinations of candidates where the chosen numbers sum to target. You may return the combinations in any order.
// The same number may be chosen from candidates an unlimited number of times. Two combinations are unique if the frequency of at least one of the chosen numbers is different.
// The test cases are generated such that the number of unique combinations that sum up to target is less than 150 combinations for the given input.
//
// Example 1:
// Input: candidates = [2,3,6,7], target = 7
// Output: [[2,2,3],[7]]
// Explanation:
// 2 and 3 are candidates, and 2 + 2 + 3 = 7. Note that 2 can be used multiple times.
// 7 is a candidate, and 7 = 7.
// These are the only two combinations.
//
// Example 2:
// Input: candidates = [2,3,5], target = 8
// Output: [[2,2,2,2],[2,3,3],[3,5]]
//
// Example 3:
// Input: candidates = [2], target = 1
// Output: []
//
// Constraints:
// 1 <= candidates.length <= 30
// 2 <= candidates[i] <= 40
// All elements of candidates are distinct.
// 1 <= target <= 40

// Approach:
// This problem can be solved using backtracking. The key idea is to explore all possible combinations of numbers from `candidates` that sum up to `target`. Since the same number can be chosen an unlimited number of times, we can reuse elements.
//
// We use a recursive helper function, `backtrack`, which takes:
// - `candidates`: The array of distinct integers.
// - `target`: The remaining target sum we need to reach.
// - `start`: The starting index in the `candidates` array to avoid duplicate combinations and ensure elements are considered in non-decreasing order.
// - `currentCombination`: The current combination of numbers being built.
// - `result`: The list to store all valid combinations.
//
// Base Cases:
// 1. If `target == 0`: We have found a valid combination that sums up to the original target. Add a copy of `currentCombination` to `result`.
// 2. If `target < 0`: The current combination has exceeded the target sum, so this path is invalid. Return.
//
// Recursive Step:
// Iterate through the `candidates` array starting from the `start` index.
// For each `candidates[i]`:
// 1. Add `candidates[i]` to `currentCombination`.
// 2. Recursively call `backtrack` with the updated `target` (reduced by `candidates[i]`) and the same `start` index (`i`).
//    Using `i` instead of `i + 1` allows the same number to be chosen multiple times.
// 3. Backtrack: Remove `candidates[i]` from `currentCombination` to explore other possibilities.

// Time Complexity:
// The time complexity is difficult to express with a simple polynomial or factorial formula due to the nature of the backtracking with repetitions.
// In the worst case, consider `candidates = [1]` and `target = T`. The number of combinations is 1, but the depth of recursion is T.
// If candidates are small and target is large, the search space can be vast.
// A loose upper bound is O(target^N), where N is the number of candidates. More precisely, it can be approximated by the number of valid combinations multiplied by the average length of a combination.
// Since the problem constraints state that the number of unique combinations is less than 150, and the maximum `target` is 40, the effective complexity is manageable.
// Each node in the recursion tree represents an addition, and the maximum depth of the tree is `target / min(candidate_value)`.
// The branching factor is `N`.
// So, it's roughly O(N^(target / min(candidate_value))).

// Space Complexity:
// The space complexity is primarily determined by the depth of the recursion and the size of the `result` list.
// The maximum recursion depth can be `target / min(candidates[i])`. In the worst case, if `min(candidates[i]) = 2` and `target = 40`, the depth can be 20. So, O(target / min_candidate).
// The `currentCombination` list also takes space proportional to the recursion depth.
// The `result` list stores all unique combinations. In the worst case, the number of combinations can be large, and each combination can have a maximum length of `target / min(candidates[i])`.
// So, the total space for result is O(Number_of_combinations * target / min_candidate).
// Given the constraint that the number of combinations is < 150, this space is manageable.

class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(candidates, target, 0, new ArrayList<>(), result);
        return result;
    }

    private void backtrack(int[] candidates, int target, int start, List<Integer> currentCombination, List<List<Integer>> result) {
        if (target == 0) {
            result.add(new ArrayList<>(currentCombination));
            return;
        }
        if (target < 0) {
            return;
        }
        for (int i = start; i < candidates.length; i++) {
            currentCombination.add(candidates[i]);
            backtrack(candidates, target - candidates[i], i, currentCombination, result);
            currentCombination.remove(currentCombination.size() - 1);
        }
    }
}