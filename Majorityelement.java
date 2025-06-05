// Problem Statement:
// Given an array nums of size n, return the majority element.
// The majority element is the element that appears more than ⌊n / 2⌋ times. You may assume that the majority element always exists in the array.

// Approach:
// This problem can be solved efficiently using Boyer-Moore Voting Algorithm.
// The core idea is that if an element is a majority element (appears more than n/2 times),
// then even if we pair up each occurrence of the majority element with a different element,
// there will still be occurrences of the majority element left over.

// The algorithm works as follows:
// 1. Initialize two variables: `count` to 0 and `candidate` to an arbitrary value (or the first element).
// 2. Iterate through the `nums` array:
//    a. If `count` is 0, it means the previous `candidate` (if any) has been effectively "cancelled out" by other elements. So, set the current `num` as the new `candidate`.
//    b. If the current `num` is equal to the `candidate`, increment `count`.
//    c. If the current `num` is not equal to the `candidate`, decrement `count`.
// 3. After iterating through the entire array, the `candidate` variable will hold the majority element.
//    The problem statement guarantees that a majority element always exists, so we don't need a second pass to verify.

// Time Complexity: O(N)
// We iterate through the array once. Each operation inside the loop takes constant time.
// Therefore, the total time complexity is O(N).

// Space Complexity: O(1)
// We only use two variables (`count` and `candidate`) for auxiliary storage.
// This requires constant extra space.

class Solution {
    public int majorityElement(int[] nums) {
        int count = 0;
        int candidate = 0; 
        for (int num : nums) {
            if (count == 0) {
                candidate = num;
            }
            if (num == candidate) {
                count++;
            } else {
                count--;
            }
        }

        return candidate;
    }
}