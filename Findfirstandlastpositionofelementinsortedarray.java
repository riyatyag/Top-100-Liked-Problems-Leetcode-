// Problem Statement:
// Given an array of integers nums sorted in non-decreasing order, find the starting and ending position of a given target value.
// If target is not found in the array, return [-1, -1].
// You must write an algorithm with O(log n) runtime complexity.

// Approach:
// This problem requires finding both the first and last occurrences of a target value in a sorted array.
// We can achieve this by performing two separate binary searches: one to find the first occurrence and another to find the last occurrence.
//
// We'll implement a helper function, say `findIndex`, which takes the array, target, and a boolean flag `findFirst`.
// - If `findFirst` is true, the function searches for the first occurrence of the target.
// - If `findFirst` is false, the function searches for the last occurrence of the target.
//
// Inside `findIndex`:
// Initialize `ans = -1`, `left = 0`, `right = nums.length - 1`.
// Use a `while (left <= right)` loop for binary search.
// Calculate `mid = left + (right - left) / 2`.
// - If `nums[mid] == target`:
//   - Set `ans = mid`.
//   - If `findFirst` is true, we want to find an even earlier occurrence, so search in the left half: `right = mid - 1`.
//   - If `findFirst` is false, we want to find an even later occurrence, so search in the right half: `left = mid + 1`.
// - If `nums[mid] < target`: The target must be in the right half: `left = mid + 1`.
// - If `nums[mid] > target`: The target must be in the left half: `right = mid - 1`.
// Return `ans`.
//
// In the main `searchRange` function, call `findIndex` twice, once for `findFirst = true` and once for `findFirst = false`, and return the results in an array.

// Time Complexity: O(log n)
// Each binary search takes O(log n) time. Since we perform two binary searches, the total time complexity remains O(log n).

// Space Complexity: O(1)
// We are only using a few constant extra variables, so the space complexity is constant.

// Optimal Solution:
class Solution {
    public int[] searchRange(int[] nums, int target) {
        int[] result = {-1, -1};
        result[0] = findIndex(nums, target, true); 
        result[1] = findIndex(nums, target, false); 
        return result;
    }

    private int findIndex(int[] nums, int target, boolean findFirst) {
        int ans = -1;
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                ans = mid;
                if (findFirst) {
                    right = mid - 1; 
                } else {
                    left = mid + 1; 
                }
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return ans;
    }
}