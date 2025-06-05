// Problem Statement:
// Given a non-empty array of integers nums, every element appears twice except for one. Find that single one.
// You must implement a solution with a linear runtime complexity and use only constant extra space.

// Approach:
// This problem can be solved efficiently using the bitwise XOR (exclusive OR) operation.
// The key properties of XOR that are relevant here are:
// 1. x ^ 0 = x (XORing any number with 0 results in the number itself)
// 2. x ^ x = 0 (XORing any number with itself results in 0)
// 3. XOR is associative and commutative (a ^ b ^ c = a ^ (b ^ c) = (a ^ b) ^ c, and a ^ b = b ^ a)

// Given that every element appears twice except for one, if we XOR all the numbers in the array,
// the pairs of identical numbers will cancel each other out (result in 0), and the single unique number
// will remain (because x ^ 0 = x).

// For example, if nums = [4, 1, 2, 1, 2]:
// result = 0 (initial value)
// result = 0 ^ 4 = 4
// result = 4 ^ 1
// result = (4 ^ 1) ^ 2
// result = (4 ^ 1 ^ 2) ^ 1  // Here, 1 ^ 1 cancels out to 0
// result = (4 ^ 2) ^ 2     // Here, 2 ^ 2 cancels out to 0
// result = 4

// Time Complexity: O(N)
// We iterate through the array once, performing a constant-time XOR operation for each element.
// Therefore, the total time complexity is linear, O(N).

// Space Complexity: O(1)
// We only use a single variable `single` to store the XOR sum. This requires constant extra space.

class Solution {
    public int singleNumber(int[] nums) {
        int single = 0;
        for (int num : nums) {
            single ^= num;
        }
        return single;
    }
}