// Problem Statement:
// Given an integer array nums and an integer k, return the k most frequent elements. You may return the answer in any order.

// Approach:
// The problem asks us to find the k most frequent elements in an array. We can solve this using a frequency map and a min-heap (priority queue).
// First, we iterate through the input array `nums` to build a frequency map, where keys are the numbers and values are their frequencies.
// Next, we use a min-heap to keep track of the k most frequent elements. The min-heap will store `Map.Entry<Integer, Integer>` objects,
// sorted by their frequency (value).
// We iterate through the frequency map's entries. For each entry, we add it to the min-heap. If the heap size exceeds k, we remove the
// element with the smallest frequency (which will be at the top of the min-heap). This ensures that the min-heap always contains
// at most k elements, and these k elements are the ones with the highest frequencies encountered so far.
// Finally, after processing all entries, the min-heap will contain the k most frequent elements. We extract these elements from the
// min-heap and store them in an array to be returned.

// Time Complexity:
// O(N) where N is the number of elements in the input array.
// Building the frequency map takes O(N) time.
// Iterating through the frequency map (at most N unique elements) and adding/removing from the min-heap takes O(U log k) time, where U is the number of unique elements. Since U <= N, this is at most O(N log k).
// In the worst case, k can be up to N. However, the follow-up asks for better than O(n log n).
// A more accurate analysis: Using a min-heap of size k:
// - Building frequency map: O(N)
// - Adding to min-heap: There are at most N distinct numbers. For each distinct number, we add it to the heap (log k). So, O(N log k).
// - Extracting from heap: O(k log k)
// Total time complexity: O(N + N log k + k log k). Since k <= N, this simplifies to O(N log k).
// This approach is better than O(N log N) if k << N.
// For the follow-up, an O(N) solution can be achieved using Bucket Sort.

// Space Complexity:
// O(N) for the frequency map in the worst case (all elements are unique).
// O(k) for the min-heap.
// Total space complexity: O(N).

// Optimal Solution (Bucket Sort for O(N) time complexity):
// The follow-up requests a time complexity better than O(N log N). This can be achieved using a bucket sort approach.
// 1. Create a frequency map (HashMap) to count the occurrences of each number. This takes O(N) time.
// 2. Create an array of lists (buckets), where the index of the array represents the frequency, and the list at that index contains numbers with that frequency. The size of this array will be `nums.length + 1` because the maximum possible frequency for any number is `nums.length`.
// 3. Iterate through the frequency map. For each (number, frequency) pair, add the number to the list at `buckets[frequency]`. This takes O(U) time, where U is the number of unique elements.
// 4. Iterate backward from the highest possible frequency (from `nums.length` down to 1). For each frequency, add all numbers in `buckets[frequency]` to the result list until `k` elements are collected. This takes at most O(N) time.
// This bucket sort approach has a time complexity of O(N) and a space complexity of O(N).

class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> freqMap = new HashMap<>();
        for (int num : nums) {
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }
        PriorityQueue<Map.Entry<Integer, Integer>> minHeap = new PriorityQueue<>(
                (a, b) -> a.getValue() - b.getValue()
        );

        for (Map.Entry<Integer, Integer> entry : freqMap.entrySet()) {
            minHeap.offer(entry);
            if (minHeap.size() > k) {
                minHeap.poll(); 
            }
        }

        int[] result = new int[k];
        for (int i = k - 1; i >= 0; i--) {
            result[i] = minHeap.poll().getKey();
        }

        return result;
    }
}