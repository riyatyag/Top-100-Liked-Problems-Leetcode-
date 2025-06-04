// Problem Statement:
// You are given an integer array height of length n. There are n vertical lines drawn such that the two endpoints of the ith line are (i, 0) and (i, height[i]).
// Find two lines that together with the x-axis form a container, such that the container contains the most water.
// Return the maximum amount of water a container can store.
// Notice that you may not slant the container.

// Approach:
// This problem can be efficiently solved using a two-pointer approach.
// We initialize two pointers, 'left' at the beginning of the array (index 0) and 'right' at the end of the array (index height.length - 1).
// We also maintain a variable 'maxArea' to store the maximum area found so far, initialized to 0.
// The idea is to calculate the area formed by the lines at the 'left' and 'right' pointers. The height of the water will be limited by the shorter of the two lines,
// and the width will be the distance between the two lines (right - left).
// After calculating the current area, we move the pointer associated with the shorter line inwards.
// The rationale for moving the shorter pointer is that moving the taller pointer inwards will definitely decrease or keep the width the same,
// and if the shorter line remains the bottleneck, the height won't increase. However, by moving the shorter pointer, there's a possibility
// of finding a taller line that could potentially increase the container's height and thus the overall area.
// This process continues until the 'left' pointer crosses the 'right' pointer.

// Time Complexity:
// O(n), where 'n' is the number of elements in the 'height' array.
// The two pointers start at opposite ends and move towards each other, traversing the array at most once.
// Each step inside the loop involves constant time operations.

// Space Complexity:
// O(1), as we are only using a few constant extra variables (left, right, maxArea, currentHeight, currentWidth, currentArea)
// and not using any data structures that scale with the input size.

// Optimal Solution:
// The two-pointer approach is considered optimal because it solves the problem in linear time complexity (O(n))
// and constant space complexity (O(1)). This is the most efficient way to find the maximum area, as we must
// at least inspect each line once to determine its potential contribution to the container.
class Solution {
    public int maxArea(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int maxArea = 0;

        while (left < right) {
            int currentHeight = Math.min(height[left], height[right]);
            int currentWidth = right - left;
            int currentArea = currentHeight * currentWidth;

            maxArea = Math.max(maxArea, currentArea);
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return maxArea;
    }
}
