// Problem Statement:
// There is an integer array nums sorted in ascending order (with distinct values).
// Prior to being passed to your function, nums is possibly rotated at an unknown pivot index k
// such that the resulting array is [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]].
// Given the array nums after the possible rotation and an integer target,
// return the index of target if it is in nums, or -1 if it is not in nums.
// You must write an algorithm with O(log n) runtime complexity.

// Approach:
// This problem extends the standard binary search to a rotated sorted array.
// The key challenge is that the array is divided into two sorted parts, and we don't know the pivot point.
// We can still use binary search by determining which half of the array is sorted and where the target might lie.
//
// Initialize `left = 0` and `right = nums.length - 1`.
// In a `while (left <= right)` loop:
// 1. Calculate `mid = left + (right - left) / 2`.
// 2. If `nums[mid] == target`, return `mid`.
// 3. Determine which half is sorted:
//    - If `nums[left] <= nums[mid]` (left half is sorted):
//      - Check if `target` is within the range of the left sorted half: `nums[left] <= target && target < nums[mid]`.
//      - If true, search in the left half: `right = mid - 1`.
//      - Else, search in the right half: `left = mid + 1`.
//    - Else (`nums[left] > nums[mid]`, meaning the right half is sorted):
//      - Check if `target` is within the range of the right sorted half: `nums[mid] < target && target <= nums[right]`.
//      - If true, search in the right half: `left = mid + 1`.
//      - Else, search in the left half: `right = mid - 1`.
// If the loop finishes and the target is not found, return -1.

// Time Complexity: O(log n)
// In each step, the search space is halved, similar to a standard binary search, resulting in logarithmic time complexity.

// Space Complexity: O(1)
// We are only using a few constant extra variables, so the space complexity is constant.

// Optimal Solution:
class Solution {
    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                return mid;
            }
            if (nums[left] <= nums[mid]) {
                if (nums[left] <= target && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
            else {
                if (nums[mid] < target && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        return -1;
    }
}