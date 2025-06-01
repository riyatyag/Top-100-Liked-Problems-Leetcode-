// Problem Statement:
// Given an integer array nums and an integer k, return the kth largest element in the array.
// Note that it is the kth largest element in the sorted order, not the kth distinct element.
// Can you solve it without sorting?

// Approach:
// The problem asks us to find the kth largest element in an unsorted array. While sorting the array (O(N log N)) and then picking the element at `nums.length - k` would work, the problem explicitly asks to solve it without sorting, implying a more efficient approach.

// A common and efficient way to solve this is using a min-heap (PriorityQueue in Java).
// We maintain a min-heap of size `k`.
// 1. Iterate through each number in the `nums` array.
// 2. For each `num`, add it to the `minHeap`.
// 3. If the `minHeap`'s size exceeds `k`, remove the smallest element from the heap (which is `minHeap.poll()`).
// By doing this, the min-heap will always contain the `k` largest elements encountered so far. The smallest among these `k` largest elements will be at the root of the min-heap.
// After iterating through all elements in `nums`, the root of the `minHeap` will be the `kth` largest element in the entire array.

// Time Complexity:
// O(N log k), where N is the number of elements in `nums`.
// - For each of the N elements, we perform an `offer` operation (which is O(log size of heap)) and potentially a `poll` operation (also O(log size of heap)).
// - The size of the heap is at most `k`.
// - Therefore, each operation takes O(log k) time.
// - Total time complexity = N * O(log k) = O(N log k).

// Space Complexity:
// O(k), as the min-heap stores at most `k` elements.

// Optimal Solution (Quickselect Algorithm):
// The Quickselect algorithm is an average O(N) time complexity solution, which is generally faster than the heap-based approach for large N.
// Quickselect is a selection algorithm that finds the k-th smallest (or largest) element in an unordered list. It is a variant of the Quicksort sorting algorithm.
// The basic idea is:
// 1. Pick a pivot element.
// 2. Partition the array around the pivot such that all elements smaller than the pivot are on its left, and all elements larger are on its right.
// 3. After partitioning, if the pivot's final position is `N - k` (for the k-th largest), then we found our element.
// 4. If the pivot's position is greater than `N - k`, recursively search in the left subarray.
// 5. If the pivot's position is less than `N - k`, recursively search in the right subarray.
// Average time complexity: O(N)
// Worst-case time complexity: O(N^2) (can be mitigated with a good pivot selection strategy, like randomized pivot).
// Space complexity: O(log N) for recursion stack in average case, O(N) in worst case.

class Solution {
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        for (int num : nums) {
            minHeap.offer(num);

            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }
        return minHeap.peek();
    }
}