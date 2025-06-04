// Problem Statement:
// Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]] such that i != j, i != k, and j != k, and nums[i] + nums[j] + nums[k] == 0.
// Notice that the solution set must not contain duplicate triplets.

// Approach:
// The most efficient approach to solve the 3Sum problem is to first sort the array.
// Sorting allows us to easily handle duplicate elements and enables the use of the two-pointer technique.
// After sorting, we iterate through the array with a single pointer 'i'. For each 'nums[i]', we treat it as the first element of a potential triplet.
// To find the remaining two elements (nums[j] and nums[k]) that sum up to -nums[i] (since nums[i] + nums[j] + nums[k] == 0),
// we use a two-pointer approach on the subarray to the right of 'i'.
// A 'left' pointer starts at 'i + 1' and a 'right' pointer starts at the end of the array.
// We calculate the sum of nums[i], nums[left], and nums[right].
// If the sum is zero, we've found a valid triplet. We add it to our result list and then move both 'left' and 'right' pointers
// inwards, skipping any duplicate values to ensure uniqueness of triplets.
// If the sum is less than zero, it means we need a larger sum, so we increment 'left'.
// If the sum is greater than zero, it means we need a smaller sum, so we decrement 'right'.
// We also handle duplicates for 'nums[i]' in the outer loop to avoid processing the same first element multiple times.

// Time Complexity:
// O(n^2), where 'n' is the number of elements in the array.
// Sorting the array takes O(n log n).
// The outer loop iterates 'n' times. Inside the outer loop, the two-pointer approach takes O(n) time in the worst case.
// Thus, the total time complexity is O(n log n + n*n) which simplifies to O(n^2).

// Space Complexity:
// O(log n) to O(n), depending on the space complexity of the sorting algorithm used.
// If we consider the space required for the output list, it could be O(n) in the worst case if many triplets sum to zero.
// However, auxiliary space used by the algorithm itself (excluding output) is O(1) if an in-place sort is used.

// Optimal Solution:
// The described approach using sorting and the two-pointer technique is considered optimal for the 3Sum problem.
// It effectively reduces the problem from O(n^3) (brute-force checking all triplets) to O(n^2),
// which is generally the best achievable time complexity for this type of problem given the constraints.
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums); 
        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            int left = i + 1; 
            int right = nums.length - 1; 
            int target = -nums[i];

            while (left < right) {
                int currentSum = nums[left] + nums[right];

                if (currentSum == target) {
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));

                    while (left < right && nums[left] == nums[left + 1]) {
                        left++;
                    }
                    while (left < right && nums[right] == nums[right - 1]) {
                        right--;
                    }
                    left++;
                    right--;
                } else if (currentSum < target) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        return result;
    }
}
