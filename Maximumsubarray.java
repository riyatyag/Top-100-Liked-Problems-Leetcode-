// Problem Statement:
// Given an integer array nums, find the subarray with the largest sum, and return its sum.

// Approach:
// This problem is a classic dynamic programming problem that can be solved using Kadane's Algorithm.
// Kadane's algorithm is an iterative dynamic programming algorithm. It looks for a maximum sum subarray
// ending at each position.

// Algorithm:
// 1. Initialize two variables:
//    - `maxSoFar`: This will store the maximum subarray sum found so far globally. Initialize it with the first element of the array.
//    - `currentMax`: This will store the maximum subarray sum ending at the current position. Initialize it with the first element of the array.
// 2. Iterate through the array starting from the second element (index 1) to the end:
//    a. For each element `nums[i]`, `currentMax` is updated to be the maximum of:
//       - `nums[i]` itself (starting a new subarray from `nums[i]`)
//       - `currentMax + nums[i]` (extending the existing subarray)
//    b. `maxSoFar` is updated to be the maximum of `maxSoFar` and `currentMax`. This step ensures that `maxSoFar` always holds the overall maximum sum found till the current iteration.
// 3. After the loop finishes, `maxSoFar` will contain the sum of the largest subarray.

// Example Walkthrough: nums = [-2,1,-3,4,-1,2,1,-5,4]
// Initial: maxSoFar = -2, currentMax = -2

// i = 1, nums[1] = 1
// currentMax = Math.max(1, -2 + 1) = Math.max(1, -1) = 1
// maxSoFar = Math.max(-2, 1) = 1

// i = 2, nums[2] = -3
// currentMax = Math.max(-3, 1 + (-3)) = Math.max(-3, -2) = -2
// maxSoFar = Math.max(1, -2) = 1

// i = 3, nums[3] = 4
// currentMax = Math.max(4, -2 + 4) = Math.max(4, 2) = 4
// maxSoFar = Math.max(1, 4) = 4

// i = 4, nums[4] = -1
// currentMax = Math.max(-1, 4 + (-1)) = Math.max(-1, 3) = 3
// maxSoFar = Math.max(4, 3) = 4

// i = 5, nums[5] = 2
// currentMax = Math.max(2, 3 + 2) = Math.max(2, 5) = 5
// maxSoFar = Math.max(4, 5) = 5

// i = 6, nums[6] = 1
// currentMax = Math.max(1, 5 + 1) = Math.max(1, 6) = 6
// maxSoFar = Math.max(5, 6) = 6

// i = 7, nums[7] = -5
// currentMax = Math.max(-5, 6 + (-5)) = Math.max(-5, 1) = 1
// maxSoFar = Math.max(6, 1) = 6

// i = 8, nums[8] = 4
// currentMax = Math.max(4, 1 + 4) = Math.max(4, 5) = 5
// maxSoFar = Math.max(6, 5) = 6

// Result: 6

// Time Complexity: O(N)
// We iterate through the array exactly once. Each operation inside the loop (comparison, addition, assignment) takes constant time.
// Therefore, the total time complexity is linear, O(N).

// Space Complexity: O(1)
// We only use a few constant extra variables (`maxSoFar`, `currentMax`, `i`).
// Thus, the space complexity is O(1).

class Solution {
    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0; 
        }

        int maxSoFar = nums[0];
        int currentMax = nums[0];

        for (int i = 1; i < nums.length; i++) {
            currentMax = Math.max(nums[i], currentMax + nums[i]);
            maxSoFar = Math.max(maxSoFar, currentMax);
        }

        return maxSoFar;
    }
}