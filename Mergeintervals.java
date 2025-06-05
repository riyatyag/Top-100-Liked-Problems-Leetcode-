// Problem Statement:
// Given an array of intervals where intervals[i] = [starti, endi], merge all overlapping intervals, and return an array of the non-overlapping intervals that cover all the intervals in the input.

// Approach:
// The key idea to merge overlapping intervals is to first sort the intervals based on their start times.
// Once sorted, we can iterate through the intervals and merge them if they overlap with the previous merged interval.

// Algorithm:
// 1. Handle edge cases: If the input array has 0 or 1 interval, return it as is.
// 2. Sort the `intervals` array based on the start time (`intervals[i][0]`). This is crucial because it ensures that when we consider an interval, all potential overlapping intervals (that start before or at the same time) have already been processed or will be processed next.
// 3. Initialize an empty `List<int[]>` called `merged` to store the non-overlapping intervals.
// 4. Take the first interval from the sorted array and add it to `merged`. This will be our `current` merged interval.
// 5. Iterate through the rest of the sorted intervals starting from the second one:
//    a. Let `nextInterval` be the current interval from the iteration.
//    b. Get the `currentEnd` from the last interval in `merged`.
//    c. If `nextInterval[0]` (start of next interval) is less than or equal to `currentEnd` (end of current merged interval), it means there's an overlap.
//       - Update the end of the `current` merged interval in `merged` to be the maximum of `currentEnd` and `nextInterval[1]` (end of next interval). This expands the merged interval to cover the overlap.
//    d. If `nextInterval[0]` is greater than `currentEnd`, it means there is no overlap.
//       - Add `nextInterval` as a new non-overlapping interval to `merged`. Update `current` to be this `nextInterval`.
// 6. Finally, convert the `List<int[]>` `merged` into a `int[][]` array and return it.

// Time Complexity: O(N log N)
// The dominant factor is the sorting step, which takes O(N log N) time, where N is the number of intervals.
// The iteration through the sorted intervals takes O(N) time.
// Therefore, the overall time complexity is O(N log N).

// Space Complexity: O(N)
// In the worst case, if no intervals overlap, the `merged` list will store all N intervals, leading to O(N) space complexity.
// If all intervals overlap, the space complexity would be O(1) for storing just one merged interval in the list.
// The sorting itself might use O(log N) or O(N) auxiliary space depending on the sort implementation.
// So, overall, the space complexity is O(N).


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

class Solution {
    public int[][] merge(int[][] intervals) {
        if (intervals.length <= 1) {
            return intervals;
        }
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        List<int[]> merged = new ArrayList<>();
        merged.add(intervals[0]);

        for (int i = 1; i < intervals.length; i++) {
            int[] currentMergedInterval = merged.get(merged.size() - 1);
            int currentEnd = currentMergedInterval[1];
            int nextStart = intervals[i][0];
            int nextEnd = intervals[i][1];

            if (nextStart <= currentEnd) {
                currentMergedInterval[1] = Math.max(currentEnd, nextEnd);
            } else {
                merged.add(intervals[i]);
            }
        }
        return merged.toArray(new int[merged.size()][]);
    }
}