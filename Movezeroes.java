// Problem Statement:
// Given an integer array nums, move all 0's to the end of it while maintaining the relative order of the non-zero elements.
// Note that you must do this in-place without making a copy of the array.

// Approach:
// This problem can be solved using a two-pointer approach.
// One pointer, 'nonZeroPointer', keeps track of the position where the next non-zero element should be placed.
// The other pointer, 'i', iterates through the entire array.
// As 'i' iterates, if nums[i] is a non-zero element, it is moved to the position indicated by 'nonZeroPointer', and 'nonZeroPointer' is incremented.
// After the first pass, all non-zero elements will be placed at the beginning of the array in their original relative order.
// The remaining positions from 'nonZeroPointer' to the end of the array will then be filled with zeros.

// Time Complexity:
// O(n), where 'n' is the number of elements in the array.
// This is because we iterate through the array a maximum of two times (one pass to move non-zero elements and one pass to fill zeros).

// Space Complexity:
// O(1), as we are modifying the array in-place and not using any additional data structures that scale with input size.

// Optimal Solution:
// The two-pointer approach is considered optimal because it achieves the task in a single pass (effectively, two conceptual passes but one iteration loop)
// and modifies the array in-place, minimizing both time and space complexity.
class Solution {
    public void moveZeroes(int[] nums) {
        int nonZeroPointer = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[nonZeroPointer] = nums[i];
                nonZeroPointer++;
            }
        }
        while (nonZeroPointer < nums.length) {
            nums[nonZeroPointer] = 0;
            nonZeroPointer++;
        }
    }
}
