// Problem Statement:
// You are given an array of integers nums, there is a sliding window of size k which is moving from the very left of the array to the very right. You can only see the k numbers in the window. Each time the sliding window moves right by one position.
// Return the max sliding window.

// Approach:
// This problem can be efficiently solved using a Deque (Double-Ended Queue) to store indices of elements in the current window in decreasing order of their values. This Deque will help us find the maximum element in O(1) time for each window.

// The Deque will store indices, not values. This is crucial because we need to know if the maximum element (whose index is at the front of the deque) is still within the current window.

// Algorithm:
// 1. Handle Edge Cases: If `nums` is null or `k` is less than or equal to 0, return an empty integer array.
// 2. Initialize:
//    - `n`: length of the `nums` array.
//    - `result`: an integer array of size `n - k + 1` to store the maximums of each window.
//    - `deque`: a `Deque` (e.g., `LinkedList`) to store indices.
// 3. Process the first window (0 to k-1):
//    - Iterate `i` from 0 to `k-1`:
//      - While the `deque` is not empty and the element at the index `deque.peekLast()` is less than or equal to `nums[i]`, remove the last element from the `deque`. This ensures that `deque` maintains elements in decreasing order.
//      - Add `i` to the back of the `deque`.
//    - After processing the first `k` elements, the maximum for the first window is `nums[deque.peekFirst()]`. Store this in `result[0]`.
// 4. Slide the window (from index `k` to `n-1`):
//    - Iterate `i` from `k` to `n-1`:
//      - Remove elements out of window: If the index at the front of the `deque` (`deque.peekFirst()`) is `i - k`, it means this element is no longer in the current window. Remove it from the front of the `deque`.
//      - Maintain decreasing order: While the `deque` is not empty and the element at the index `deque.peekLast()` is less than or equal to `nums[i]`, remove the last element from the `deque`.
//      - Add current element: Add `i` to the back of the `deque`.
//      - Record maximum: The maximum for the current window is `nums[deque.peekFirst()]`. Store this in `result[i - k + 1]`.
// 5. Return `result`.

// Time Complexity:
// O(N), where N is the length of the `nums` array.
// Each element is added to and removed from the deque at most once. Therefore, each operation (add, remove) takes amortized O(1) time. We iterate through the array once.

// Space Complexity:
// O(K), where K is the size of the window.
// In the worst case, all elements in the current window are in decreasing order, so the deque can store up to K indices.

import java.util.Deque;
import java.util.LinkedList;

class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || k <= 0) {
            return new int[0];
        }

        int n = nums.length;
        int[] result = new int[n - k + 1];
        Deque<Integer> deque = new LinkedList<>();

        for (int i = 0; i < k; i++) {
            while (!deque.isEmpty() && nums[deque.peekLast()] <= nums[i]) {
                deque.removeLast();
            }
            deque.addLast(i);
        }
        result[0] = nums[deque.peekFirst()];

        for (int i = k; i < n; i++) {
            if (!deque.isEmpty() && deque.peekFirst() == i - k) {
                deque.removeFirst();
            }
            while (!deque.isEmpty() && nums[deque.peekLast()] <= nums[i]) {
                deque.removeLast();
            }
            deque.addLast(i);
            result[i - k + 1] = nums[deque.peekFirst()];
        }

        return result;
    }
}