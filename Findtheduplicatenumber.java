// Problem Statement:
// Given an array of integers nums containing n + 1 integers where each integer is in the range [1, n] inclusive.
// There is only one repeated number in nums, return this repeated number.
// You must solve the problem without modifying the array nums and using only constant extra space.

// Approach:
// This problem can be solved using Floyd's Tortoise and Hare (Cycle Detection) algorithm.
// Imagine the array elements as nodes in a linked list where `i` points to `nums[i]`.
// Since there are n + 1 numbers in the range [1, n], and exactly one number is duplicated,
// there must be a cycle in this conceptual linked list. The starting point of the cycle
// is the duplicate number.

// First, find the intersection point of the two pointers (slow and fast).
// `slow` moves one step at a time: `slow = nums[slow]`.
// `fast` moves two steps at a time: `fast = nums[nums[fast]]`.
// They are guaranteed to meet inside the cycle if a cycle exists.

// Once they meet, reset `slow` to the beginning of the array (`nums[0]`).
// Move both `slow` and `fast` one step at a time. The point where they meet again
// will be the starting node of the cycle, which is the duplicate number.

// Time Complexity: O(N)
// The algorithm involves traversing the array at most a few times.
// In the first phase, slow and fast pointers traverse the array, covering at most 2N steps.
// In the second phase, both pointers traverse the array from the start to the cycle entry, covering at most N steps.
// Thus, the total time complexity is linear, O(N).

// Space Complexity: O(1)
// The algorithm uses only a few variables to store the pointers, requiring constant extra space.

class Solution {
    public int findDuplicate(int[] nums) {
        int slow = nums[0];
        int fast = nums[0];

        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while (slow != fast);

        slow = nums[0];
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }

        return slow;
    }
}