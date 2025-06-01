// Problem Statement:
// Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.
// You may assume that each input would have exactly one solution, and you may not use the same element twice.
// You can return the answer in any order.

// Approach:
// This is a classic problem that can be efficiently solved using a HashMap.
// The idea is to iterate through the array once. For each number `nums[i]`, we calculate the `complement` needed to reach the `target`.
// The `complement` is `target - nums[i]`.
// We then check if this `complement` already exists as a key in our HashMap.
// If it does, it means we have found two numbers (`complement` and `nums[i]`) that sum up to `target`. We can then return their indices.
// If the `complement` does not exist in the map, we add the current number `nums[i]` and its index `i` to the HashMap. We do this because `nums[i]` might be the complement for a future number in the array.

// Why a HashMap?
// - It allows for O(1) average-time lookups (checking if a key exists).
// - It stores both the number and its index, which is exactly what we need to return.

// Algorithm Steps:
// 1. Create a `HashMap<Integer, Integer>` called `map`. This map will store `(number, index)` pairs.
// 2. Iterate through the `nums` array using a `for` loop with index `i` from `0` to `nums.length - 1`.
// 3. Inside the loop, for each `nums[i]`:
//    a. Calculate the `complement = target - nums[i]`.
//    b. Check if the `map` contains the `complement` as a key: `if (map.containsKey(complement))`.
//    c. If it does, we have found our pair. Return a new integer array containing `map.get(complement)` (the index of the complement) and `i` (the index of the current number): `return new int[]{map.get(complement), i};`.
//    d. If the `map` does not contain the `complement`, put the current number `nums[i]` and its index `i` into the map: `map.put(nums[i], i);`.
// 4. If the loop completes without finding a solution (this scenario won't happen based on the problem constraint "Only one valid answer exists"), return an empty array or throw an exception, although for this problem, it's guaranteed to find a solution.

// Time Complexity:
// O(N), where N is the number of elements in the `nums` array.
// We iterate through the array once. For each element, HashMap operations (put, get, containsKey) take O(1) time on average.

// Space Complexity:
// O(N) in the worst case. The HashMap can store up to N key-value pairs if no two numbers sum up to the target early in the iteration.

class Solution {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int currentNum = nums[i];
            int complement = target - currentNum;

            if (map.containsKey(complement)) {
                return new int[]{map.get(complement), i};
            }
            map.put(currentNum, i);
        }
        return new int[]{};
    }
}