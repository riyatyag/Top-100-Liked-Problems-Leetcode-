// Problem Statement:
// Suppose an array of length n sorted in ascending order is rotated between 1 and n times.
// Given the sorted rotated array nums of unique elements, return the minimum element of this array.
// You must write an algorithm that runs in O(log n) time.

// Approach:
// This problem can be solved using a modified binary search approach.
// The key idea is that the minimum element will be the only element that is smaller than its previous element (unless the array is not rotated).
// We initialize two pointers, 'left' and 'right', to the beginning and end of the array, respectively.
// First, we check if the array is not rotated (i.e., nums[left] <= nums[right]). If so, the minimum element is nums[left].
// If the array is rotated, we enter a while loop (left < right).
// In each iteration, we calculate the middle element 'mid'.
// If nums[mid] is greater than nums[right], it means the minimum element is in the right half (including mid + 1), so we update left = mid + 1.
// Otherwise (nums[mid] is less than or equal to nums[right]), it means the minimum element is in the left half (including mid), so we update right = mid.
// The loop continues until left and right pointers meet. At this point, left (or right) will point to the minimum element.

// Time Complexity: O(log n)
// The binary search algorithm halves the search space in each step, leading to a logarithmic time complexity.

// Space Complexity: O(1)
// We are only using a few constant extra variables, so the space complexity is constant.

// Optimal Solution:
class Solution {
    public int findMin(int[] nums) {
        int left = 0, right = nums.length - 1;
        if (nums[left] <= nums[right]) {
            return nums[left];
        }
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] > nums[right]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return nums[left];
    }
}