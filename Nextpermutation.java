// Problem Statement:
// A permutation of an array of integers is an arrangement of its members into a sequence or linear order.
// The next permutation of an array of integers is the next lexicographically greater permutation of its integer. More formally, if all the permutations of the array are sorted in one container according to their lexicographical order, then the next permutation of that array is the permutation that follows it in the sorted container. If such arrangement is not possible, the array must be rearranged as the lowest possible order (i.e., sorted in ascending order).
// Given an array of integers nums, find the next permutation of nums.
// The replacement must be in place and use only constant extra memory.

// Approach:
// To find the next lexicographically greater permutation, we need to make the smallest possible change to the current permutation that results in a larger permutation. This typically involves finding a "pivot" element and swapping it with a larger element to its right, then sorting the suffix.

// Algorithm:
// 1. Find the first decreasing element from the right (let's call its index `i`).
//    Iterate from `nums.length - 2` down to 0. Find the first index `i` such that `nums[i] < nums[i+1]`.
//    If no such index `i` is found, it means the array is sorted in descending order (e.g., [3,2,1]), which is the largest possible permutation. In this case, reverse the entire array to get the lowest possible order (ascending order).

// 2. If such an `i` is found:
//    a. Find the smallest element to the right of `nums[i]` that is greater than `nums[i]` (let's call its index `j`).
//       Iterate from `nums.length - 1` down to `i + 1`. Find the first index `j` such that `nums[j] > nums[i]`.
//    b. Swap `nums[i]` and `nums[j]`. This step ensures that the new permutation is lexicographically larger.

// 3. Reverse the subarray to the right of `i` (from `i+1` to `nums.length - 1`).
//    This step makes the suffix lexicographically smallest, thus ensuring we get the "next" permutation.

// Example Walkthrough: nums = [1,2,3]
// 1. Find `i`:
//    i = 1 (nums.length - 2). nums[1] = 2, nums[2] = 3. `2 < 3`. So `i = 1`.

// 2. Find `j` and swap:
//    j = 2 (nums.length - 1). nums[2] = 3. `3 > nums[i]` (which is 2). So `j = 2`.
//    Swap `nums[1]` and `nums[2]`.
//    nums becomes `[1,3,2]`.

// 3. Reverse suffix:
//    Reverse from `i+1` (index 2) to `n-1` (index 2).
//    The suffix is `[2]` (after swap, originally `[3]`). Reversing `[2]` doesn't change it.
//    Final nums: `[1,3,2]`.

// Example Walkthrough: nums = [3,2,1]
// 1. Find `i`:
//    i = 1. nums[1] = 2, nums[2] = 1. `2 >= 1`.
//    i = 0. nums[0] = 3, nums[1] = 2. `3 >= 2`.
//    No such `i` found. Array is descending.

// 2. Reverse the entire array:
//    nums becomes `[1,2,3]`.

// Time Complexity: O(N)
// The first loop to find `i` iterates at most N times.
// The second loop to find `j` iterates at most N times.
// The swap operation is O(1).
// The reverse operation takes O(N) time.
// Overall, the time complexity is dominated by these linear scans, resulting in O(N).

// Space Complexity: O(1)
// The algorithm performs operations in-place on the input array and uses only a few constant extra variables.
// Thus, the space complexity is O(1).

class Solution {
    public void nextPermutation(int[] nums) {
        int n = nums.length;
        int i = n - 2;
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }
        if (i >= 0) {
            int j = n - 1;
            while (nums[j] <= nums[i]) {
                j--;
            }
            swap(nums, i, j);
        }
        reverse(nums, i + 1, n - 1);
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    private void reverse(int[] nums, int start, int end) {
        while (start < end) {
            swap(nums, start, end);
            start++;
            end--;
        }
    }
}