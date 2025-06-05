// Problem Statement:
// Given an unsorted integer array nums. Return the smallest positive integer that is not present in nums.
// You must implement an algorithm that runs in O(n) time and uses O(1) auxiliary space.

// Approach:
// This problem can be solved in O(N) time and O(1) space by using the array itself as a hash map.
// The idea is to place each positive number `x` at its correct index `x-1`.
// We only care about positive integers from 1 to `n`, because if the smallest missing positive integer is greater than `n`,
// it means all numbers from 1 to `n` are present in the array, and thus the answer would be `n+1`.

// Algorithm:
// 1. Iterate through the array `nums` from `i = 0` to `n-1`.
// 2. For each `nums[i]`:
//    We want to place `nums[i]` at its correct position `nums[i] - 1`.
//    A number `x` is in its correct place if `nums[x-1] == x`.
//    So, while `nums[i]` is positive, within the range `[1, n]`, AND `nums[i]` is not already at its correct place (`nums[nums[i] - 1] != nums[i]`):
//    - Swap `nums[i]` with `nums[nums[i] - 1]`.
//    This loop ensures that numbers are moved to their correct positions. Once a number is moved, the element now at `nums[i]` might also need to be placed correctly, so we continue the `while` loop.

// 3. After the first pass, the array will have positive numbers placed at their correct indices as much as possible.
//    Now, iterate through the array again from `i = 0` to `n-1`:
//    - If `nums[i]` is not equal to `i + 1`, then `i + 1` is the smallest positive integer missing. Return `i + 1`.

// 4. If the loop completes without finding such an `i`, it means all numbers from 1 to `n` are present in the array.
//    In this case, the smallest missing positive integer is `n + 1`.

// Example Walkthrough: nums = [3,4,-1,1]
// n = 4

// First pass (placement):
// i = 0, nums[0] = 3
//   nums[0] = 3 (positive, <=4) and nums[3-1] (nums[2]) != 3. Swap nums[0] and nums[2].
//   nums becomes [-1, 4, 3, 1]
//   nums[0] = -1. Loop condition fails.
// i = 1, nums[1] = 4
//   nums[1] = 4 (positive, <=4) and nums[4-1] (nums[3]) != 4. Swap nums[1] and nums[3].
//   nums becomes [-1, 1, 3, 4]
//   nums[1] = 1 (positive, <=4) and nums[1-1] (nums[0]) != 1. Swap nums[1] and nums[0].
//   nums becomes [1, -1, 3, 4]
//   nums[1] = -1. Loop condition fails.
// i = 2, nums[2] = 3
//   nums[2] = 3 (positive, <=4) and nums[3-1] (nums[2]) == 3. Loop condition fails.
// i = 3, nums[3] = 4
//   nums[3] = 4 (positive, <=4) and nums[4-1] (nums[3]) == 4. Loop condition fails.

// Array after first pass: [1, -1, 3, 4]

// Second pass (find missing):
// i = 0, nums[0] = 1. nums[0] == 0 + 1. Continue.
// i = 1, nums[1] = -1. nums[1] != 1 + 1 (2). Smallest missing positive is 2. Return 2.

// Time Complexity: O(N)
// The first loop has a nested `while` loop. However, each number is swapped at most once into its correct position.
// Once a number is at its correct position, it is never swapped again. So, the total number of swaps across all iterations
// of the outer loop is at most N. This makes the effective time complexity O(N).
// The second loop also iterates N times.
// Thus, the total time complexity is O(N).

// Space Complexity: O(1)
// We are modifying the input array in-place and use a few constant variables.
// No auxiliary data structures proportional to input size are used.

class Solution {
    public int firstMissingPositive(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            while (nums[i] > 0 && nums[i] <= n && nums[nums[i] - 1] != nums[i]) {
                int correctIndex = nums[i] - 1;
                int temp = nums[i];
                nums[i] = nums[correctIndex];
                nums[correctIndex] = temp;
            }
        }
        for (int i = 0; i < n; i++) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }
        return n + 1;
    }
}