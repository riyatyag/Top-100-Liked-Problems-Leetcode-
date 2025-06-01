// Problem Statement:
// The median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value, and the median is the mean of the two middle values.
// Implement the MedianFinder class:
// MedianFinder() initializes the MedianFinder object.
// void addNum(int num) adds the integer num from the data stream to the data structure.
// double findMedian() returns the median of all elements so far. Answers within 10-5 of the actual answer will be accepted.

// Approach:
// This problem can be efficiently solved using two heaps: a max-heap (for the lower half of the numbers) and a min-heap (for the upper half of the numbers).
// The goal is to keep these two heaps balanced such that:
// 1. All elements in the max-heap are less than or equal to all elements in the min-heap.
// 2. The size difference between the two heaps is at most 1.

// We maintain the invariant that `maxHeap.size() >= minHeap.size()` or `maxHeap.size() == minHeap.size() + 1`.
// When `addNum(int num)` is called:
// - If `num` is less than or equal to the top of the max-heap (or max-heap is empty), add `num` to the max-heap.
// - Otherwise, add `num` to the min-heap.
// After adding, we need to rebalance the heaps to maintain the size invariant:
// - If `maxHeap.size() > minHeap.size() + 1`, move the largest element from `maxHeap` to `minHeap`.
// - If `minHeap.size() > maxHeap.size()`, move the smallest element from `minHeap` to `maxHeap`.

// When `findMedian()` is called:
// - If the total number of elements is odd (i.e., `maxHeap.size() > minHeap.size()`), the median is the top element of the max-heap.
// - If the total number of elements is even (i.e., `maxHeap.size() == minHeap.size()`), the median is the average of the top elements of both heaps.

// Time Complexity:
// `MedianFinder()`: O(1)
// `addNum(int num)`: O(log N), where N is the number of elements added so far. Each heap operation (add, poll) takes logarithmic time with respect to its size. Since the heap sizes are roughly N/2, it's O(log N).
// `findMedian()`: O(1). Accessing the top element of a heap is O(1).

// Space Complexity:
// O(N) to store all the numbers in the two heaps.

class MedianFinder {
    private PriorityQueue<Integer> maxHeap;
    private PriorityQueue<Integer> minHeap; 

    public MedianFinder() {
        maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        minHeap = new PriorityQueue<>();
    }

    public void addNum(int num) {
        if (maxHeap.isEmpty() || num <= maxHeap.peek()) {
            maxHeap.offer(num);
        } else {
            minHeap.offer(num);
        }
        if (maxHeap.size() > minHeap.size() + 1) {
            minHeap.offer(maxHeap.poll());
        } else if (minHeap.size() > maxHeap.size()) {
            maxHeap.offer(minHeap.poll());
        }
    }

    public double findMedian() {
        if (maxHeap.size() == minHeap.size()) {
            return (maxHeap.peek() + minHeap.peek()) / 2.0;
        } else {
            return maxHeap.peek();
        }
    }
}
