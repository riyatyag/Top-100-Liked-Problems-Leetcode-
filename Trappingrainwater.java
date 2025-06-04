// Problem Statement:
// Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it can trap after raining.

// Approach:
// This problem can be solved efficiently using a two-pointer approach. We maintain two pointers, 'left' starting from the beginning of the array
// and 'right' starting from the end. We also keep track of the maximum height encountered from the left side ('leftMax') and from the right side ('rightMax').
// The idea is that the amount of water trapped at a given position depends on the minimum of the maximum height to its left and the maximum height to its right.
// By moving the pointer associated with the smaller height, we ensure that we are always limited by the smaller of the two maximums, allowing us to correctly calculate trapped water.
// If height[left] is less than height[right], it means the water trapped at height[left] is determined by leftMax. We update leftMax if the current height is greater,
// otherwise, we add the difference (leftMax - height[left]) to the total trapped water. Then, we increment 'left'.
// Conversely, if height[right] is less than or equal to height[left], the water trapped at height[right] is determined by rightMax. We update rightMax if the current height is greater,
// otherwise, we add the difference (rightMax - height[right]) to the total trapped water. Then, we decrement 'right'.
// This process continues until the left pointer crosses the right pointer.

// Time Complexity:
// O(n), where 'n' is the number of elements in the height array. This is because we iterate through the array with two pointers, and each element is visited at most once.

// Space Complexity:
// O(1), as we are using a constant amount of extra space for pointers and maximum height variables, regardless of the input array size.

// Optimal Solution:
// The two-pointer approach is considered optimal because it achieves the solution in a single pass through the array,
// providing a linear time complexity and constant space complexity. It avoids the need for pre-calculating left and right maximums in separate arrays,
// which would require O(n) space.
class Solution {
    public int trap(int[] height) {
        int left = 0, right = height.length - 1;
        int leftMax = 0, rightMax = 0;
        int trappedWater = 0;

        while (left < right) {
            if (height[left] < height[right]) {
                if (height[left] >= leftMax) {
                    leftMax = height[left];
                } else {
                    trappedWater += leftMax - height[left];
                }
                left++;
            } else {
                if (height[right] >= rightMax) {
                    rightMax = height[right];
                } else {
                    trappedWater += rightMax - height[right];
                }
                right--;
            }
        }
        return trappedWater;
    }
}
