// Problem Statement:
// Given two sorted arrays nums1 and nums2 of size m and n respectively, return the median of the two sorted arrays.
// The overall run time complexity should be O(log (m+n)).

// Approach:
// This problem can be solved efficiently using a binary search approach on the shorter array.
// The goal is to find a "partition" in both arrays such that:
// 1. The total number of elements to the left of the partitions is (m + n + 1) / 2 (for the median calculation).
// 2. All elements to the left of the partitions are less than or equal to all elements to the right of the partitions.
//
// Let nums1 be the shorter array (m <= n). If not, we swap them.
// We perform binary search on the `cut1` (partition index) of nums1, from `0` to `m`.
// The `cut2` (partition index) for nums2 can be derived from `cut1` to ensure the total left elements are correct:
// `cut2 = (m + n + 1) / 2 - cut1`.
//
// After determining `cut1` and `cut2`:
// Define `left1`, `right1`, `left2`, `right2` as follows:
// - `left1`: Element just before the partition in nums1. If `cut1` is 0, use `Integer.MIN_VALUE`.
// - `right1`: Element just after the partition in nums1. If `cut1` is `m`, use `Integer.MAX_VALUE`.
// - `left2`: Element just before the partition in nums2. If `cut2` is 0, use `Integer.MIN_VALUE`.
// - `right2`: Element just after the partition in nums2. If `cut2` is `n`, use `Integer.MAX_VALUE`.
//
// Now, check the condition `left1 <= right2 && left2 <= right1`:
// - If true, we found the correct partition.
//   - If `(m + n)` is even, the median is `(Math.max(left1, left2) + Math.min(right1, right2)) / 2.0`.
//   - If `(m + n)` is odd, the median is `Math.max(left1, left2)`.
// - If `left1 > right2`: This means `nums1`'s left part has elements too large, so we need to move `cut1` to the left. `high = cut1 - 1`.
// - If `left2 > right1`: This means `nums2`'s left part has elements too large (or `nums1`'s right part has elements too small), so we need to move `cut1` to the right. `low = cut1 + 1`.
//
// The binary search loop continues until the correct partition is found.

// Time Complexity: O(log(min(m, n)))
// The binary search is performed on the shorter array, which has a length of `min(m, n)`.

// Space Complexity: O(1)
// We are only using a few constant extra variables, so the space complexity is constant.

// Optimal Solution:
class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) {
            return findMedianSortedArrays(nums2, nums1);
        }

        int m = nums1.length;
        int n = nums2.length;

        int low = 0;
        int high = m; 
        while (low <= high) {
            int cut1 = low + (high - low) / 2;
            int cut2 = (m + n + 1) / 2 - cut1;

            int left1 = (cut1 == 0) ? Integer.MIN_VALUE : nums1[cut1 - 1];
            int right1 = (cut1 == m) ? Integer.MAX_VALUE : nums1[cut1];

            int left2 = (cut2 == 0) ? Integer.MIN_VALUE : nums2[cut2 - 1];
            int right2 = (cut2 == n) ? Integer.MAX_VALUE : nums2[cut2];

            if (left1 <= right2 && left2 <= right1) {
                if ((m + n) % 2 == 0) {
                    return (Math.max(left1, left2) + Math.min(right1, right2)) / 2.0;
                } else {
                    return Math.max(left1, left2);
                }
            } else if (left1 > right2) {
                high = cut1 - 1;
            } else {
                low = cut1 + 1;
            }
        }
        return 0.0;
    }
}