// Problem Statement:
// Given an integer array nums, return an array answer such that answer[i] is equal to the product of all the elements of nums except nums[i].
// The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.
// You must write an algorithm that runs in O(n) time and without using the division operation.

// Approach:
// To solve this problem in O(n) time and without using division, we can use a two-pass approach.
// The idea is to calculate the product of all elements to the left of `nums[i]` and store it in `answer[i]`.
// Then, we calculate the product of all elements to the right of `nums[i]` and multiply it with the existing `answer[i]`.

// 1. Initialize an `answer` array of the same size as `nums`.
// 2. First pass (left products):
//    - `answer[0]` will be 1 (as there are no elements to its left).
//    - For `i` from 1 to `n-1`, `answer[i]` will be `answer[i-1] * nums[i-1]`.
//    This way, `answer[i]` will store the product of all elements to the left of `nums[i]`.

// 3. Second pass (right products):
//    - Initialize a variable `rightProduct` to 1. This variable will keep track of the product of elements to the right.
//    - Iterate from `n-1` down to 0.
//    - For each `i`, multiply `answer[i]` by `rightProduct`.
//    - Update `rightProduct` by multiplying it with `nums[i]`.
//    This way, `answer[i]` will be updated to include the product of elements to its right.

// Time Complexity: O(N)
// We iterate through the array twice. Each pass takes O(N) time.
// Therefore, the total time complexity is O(N) + O(N) = O(N).

// Space Complexity: O(1) (excluding the output array)
// We are using the `answer` array for storing results, which is considered O(1) extra space as per the problem's follow-up.
// We use a single variable `rightProduct` for auxiliary calculations, which is constant space.

class Solution {
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] answer = new int[n];
        answer[0] = 1;
        for (int i = 1; i < n; i++) {
            answer[i] = answer[i - 1] * nums[i - 1];
        }
        int rightProduct = 1;
        for (int i = n - 1; i >= 0; i--) {
            answer[i] = answer[i] * rightProduct;
            rightProduct = rightProduct * nums[i];
        }

        return answer;
    }
}