// Problem Statement:
// Given a sorted array of distinct integers and a target value, return the index if the target is found.
// If not, return the index where it would be if it were inserted in order.
// You must write an algorithm with O(log n) runtime complexity.

// Approach:
// This problem is a classic application of binary search.
// We need to find either the target's index or the position where it would be inserted to maintain the sorted order.
// We initialize two pointers, `left` and `right`, to the start and end of the array, respectively.
// The `while (left <= right)` loop continues as long as there is a valid search space.
// In each iteration, we calculate the middle index `mid`.
// 1. If `nums[mid]` is equal to the `target`, we have found the element, and we return `mid`.
// 2. If `nums[mid]` is less than the `target`, it means the `target` must be in the right half of the current search space (or would be inserted after `mid`). So, we update `left = mid + 1`.
// 3. If `nums[mid]` is greater than the `target`, it means the `target` must be in the left half of the current search space (or would be inserted before `mid`). So, we update `right = mid - 1`.
// After the loop terminates, `left` will be the correct insertion point for the `target`.
// This is because when `left > right`, the `left` pointer has moved past the `right` pointer, indicating that `target` was not found.
// At this point, `left` represents the first index where `target` could be inserted while maintaining the sorted order.

// Time Complexity: O(log n)
// The binary search algorithm halves the search space in each step, resulting in a logarithmic time complexity.

// Space Complexity: O(1)
// We are only using a few constant extra variables, so the space complexity is constant.

// Optimal Solution:
class Solution {
    public int searchInsert(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }
}