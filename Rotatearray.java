// Problem Statement:
// Given an integer array nums, rotate the array to the right by k steps, where k is non-negative.

// Approach:
// This problem can be efficiently solved in-place using the three-reversal algorithm.
// The idea is based on the observation that if we rotate an array `[1,2,3,4,5,6,7]` by `k=3` steps,
// the result is `[5,6,7,1,2,3,4]`.
// Notice that the elements `[5,6,7]` (the last `k` elements) move to the front, and `[1,2,3,4]` (the first `n-k` elements) move to the end.

// The three-reversal algorithm works as follows:
// 1. First, take `k` modulo `n` (array length) to handle cases where `k` is greater than `n`. `k = k % n;`
// 2. Reverse the entire array `nums`.
//    Example: `[1,2,3,4,5,6,7]` becomes `[7,6,5,4,3,2,1]` (for `k=3`).
// 3. Reverse the first `k` elements of the array.
//    Example: `[7,6,5,4,3,2,1]` becomes `[5,6,7,4,3,2,1]` (reversing first 3 elements).
// 4. Reverse the remaining `n-k` elements (from index `k` to `n-1`).
//    Example: `[5,6,7,4,3,2,1]` becomes `[5,6,7,1,2,3,4]` (reversing elements from index 3 to 6).

// This approach effectively moves the last `k` elements to the beginning and the first `n-k` elements to the end,
// achieving the desired rotation.

// Time Complexity: O(N)
// Each reversal operation takes O(N) time, where N is the length of the array.
// Since we perform three such reversals, the total time complexity is O(N).

// Space Complexity: O(1)
// The algorithm modifies the array in-place and uses only a few constant extra variables for the `reverse` function.
// Therefore, the space complexity is O(1).

class Solution {
    public void rotate(int[] nums, int k) {
        int n = nums.length;
        k = k % n; 

        reverse(nums, 0, n - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, n - 1);
    }

    private void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }
}