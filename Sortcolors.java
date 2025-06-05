// Problem Statement:
// Given an array nums with n objects colored red, white, or blue, sort them in-place so that objects of the same color are adjacent, with the colors in the order red, white, and blue.
// We will use the integers 0, 1, and 2 to represent the color red, white, and blue, respectively.
// You must solve this problem without using the library's sort function.

// Approach:
// This problem is a classic example of the "Dutch National Flag Problem" which can be solved using a one-pass algorithm with three pointers.
// The idea is to maintain three pointers: `low`, `mid`, and `high`.
// - `low` pointer keeps track of the boundary between 0s and 1s (all elements to the left of `low` are 0s).
// - `high` pointer keeps track of the boundary between 1s and 2s (all elements to the right of `high` are 2s).
// - `mid` pointer iterates through the array and helps in placing elements at their correct positions.

// Algorithm:
// 1. Initialize `low = 0`, `mid = 0`, and `high = nums.length - 1`.
// 2. Iterate while `mid <= high`:
//    a. If `nums[mid]` is 0:
//       Swap `nums[mid]` with `nums[low]`.
//       Increment both `low` and `mid`.
//    b. If `nums[mid]` is 1:
//       Increment `mid`. (1s are already in their correct relative position between 0s and 2s)
//    c. If `nums[mid]` is 2:
//       Swap `nums[mid]` with `nums[high]`.
//       Decrement `high`. (Note: `mid` is not incremented here because the swapped element `nums[mid]` could be a 0 or 1, and needs to be checked again in the next iteration).

// This one-pass approach correctly sorts the array into 0s, 1s, and 2s in-place.

// Time Complexity: O(N)
// The algorithm iterates through the array at most once using the `mid` pointer. Each operation (swap or increment) takes constant time.
// Therefore, the time complexity is linear, O(N).

// Space Complexity: O(1)
// The algorithm sorts the array in-place and uses only a few constant extra variables (`low`, `mid`, `high`, `temp` for swap).
// Thus, the space complexity is O(1).

class Solution {
    public void sortColors(int[] nums) {
        int low = 0;
        int mid = 0;
        int high = nums.length - 1;

        while (mid <= high) {
            if (nums[mid] == 0) {
                int temp = nums[low];
                nums[low] = nums[mid];
                nums[mid] = temp;
                low++;
                mid++;
            } else if (nums[mid] == 1) {
                mid++;
            } else { 
                int temp = nums[high];
                nums[high] = nums[mid];
                nums[mid] = temp;
                high--; 
            }
        }
    }
}