// Problem Statement:
// Given an integer array nums, find a subarray that has the largest product, and return the product.
// The test cases are generated so that the answer will fit in a 32-bit integer.

// Approach:
// This problem is a variation of the Maximum Subarray Sum problem. The key difference is that negative numbers can turn a small product into a large one (e.g., -2 * -3 = 6). Therefore, we need to track both the maximum and minimum product ending at the current position.

// Let `maxSoFar[i]` be the maximum product of a subarray ending at index `i`.
// Let `minSoFar[i]` be the minimum product of a subarray ending at index `i`.

// For each `nums[i]`:
// 1. If `nums[i]` is positive:
//    `maxSoFar[i] = max(nums[i], nums[i] * maxSoFar[i-1])`
//    `minSoFar[i] = min(nums[i], nums[i] * minSoFar[i-1])`
// 2. If `nums[i]` is negative:
//    Multiplying a negative number with the current `maxSoFar` would yield a smaller (more negative) number.
//    Multiplying a negative number with the current `minSoFar` would yield a larger (less negative or positive) number.
//    So, we need to swap the roles of `maxSoFar` and `minSoFar` in the calculation:
//    `maxSoFar[i] = max(nums[i], nums[i] * minSoFar[i-1])`
//    `minSoFar[i] = min(nums[i], nums[i] * maxSoFar[i-1])`
// 3. If `nums[i]` is zero:
//    Both `maxSoFar[i]` and `minSoFar[i]` become 0, as any product involving zero will be zero. The subarray restarts from the next element.

// We can optimize space by only keeping track of the previous `maxSoFar` and `minSoFar` values, rather than entire arrays. We also need a global `result` variable to store the overall maximum product found.

// Algorithm:
// 1. Handle edge cases: if `nums` is empty, return 0.
// 2. Initialize `maxProduct = nums[0]`, `maxCurrent = nums[0]`, `minCurrent = nums[0]`.
// 3. Iterate from `i = 1` to `nums.length - 1`:
//    a. Store `temp_max = maxCurrent` (because `maxCurrent` will be updated first and we need its old value for `minCurrent` calculation).
//    b. `maxCurrent = max(nums[i], max(nums[i] * maxCurrent, nums[i] * minCurrent))`
//    c. `minCurrent = min(nums[i], min(nums[i] * temp_max, nums[i] * minCurrent))`
//    d. `maxProduct = max(maxProduct, maxCurrent)`
// 4. Return `maxProduct`.

// Time Complexity:
// O(N) where N is the length of `nums`. We iterate through the array once.

// Space Complexity:
// O(1) as we only use a few variables to store current and overall maximum/minimum products.

// Optimal Solution:
class Solution {
    public int maxProduct(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int maxProduct = nums[0]; 
        int maxCurrent = nums[0]; 
        int minCurrent = nums[0]; 

        for (int i = 1; i < nums.length; i++) {
            int num = nums[i];
            if (num < 0) {
                int temp = maxCurrent;
                maxCurrent = minCurrent;
                minCurrent = temp;
            }

            maxCurrent = Math.max(num, maxCurrent * num);
            minCurrent = Math.min(num, minCurrent * num);

            maxProduct = Math.max(maxProduct, maxCurrent);
        }

        return maxProduct;
    }
}