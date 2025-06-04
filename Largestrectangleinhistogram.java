// Problem Statement:
// Given an array of integers heights representing the histogram's bar height where the width of each bar is 1, return the area of the largest rectangle in the histogram.

// Approach:
// This problem can be efficiently solved using a monotonic stack. The key idea is that for any bar, the largest rectangle with that bar as its height extends as far left and as far right as possible, until it encounters a bar shorter than itself.
// We use a stack to store indices of bars in increasing order of their heights.
// We iterate through the `heights` array, including a virtual bar of height 0 at the end (by iterating up to `n`, where `n` is `heights.length`). This virtual bar ensures that all bars remaining in the stack at the end are processed.
// For each bar `currHeight` at index `i`:
// 1. While the stack is not empty AND the height of the bar at the top of the stack (`heights[stack.peek()]`) is greater than `currHeight`:
//    a. Pop the index `h` from the stack. This `heights[h]` is a potential height for a rectangle.
//    b. Calculate the width: If the stack becomes empty after popping `h`, it means `heights[h]` is the smallest bar encountered so far, and its rectangle extends from index 0 to `i-1`. So, the width is `i`.
//    c. If the stack is not empty, the width is `i - stack.peek() - 1`. The `stack.peek()` now points to the first bar to the left that is shorter than `heights[h]`.
//    d. Update `maxArea` with `Math.max(maxArea, heights[h] * width)`.
// 2. Push the current index `i` onto the stack.

// Time Complexity:
// O(n), where 'n' is the number of bars in the histogram. Each bar's index is pushed onto the stack and popped from the stack at most once. The loop runs 'n+1' times.

// Space Complexity:
// O(n), in the worst case, if the heights are in strictly increasing order (e.g., [1, 2, 3, ...]), all indices will be stored in the stack.

// Optimal Solution:
// The monotonic stack approach is optimal because it processes each bar exactly twice (once when pushed, once when popped), leading to a linear time complexity. It effectively finds the left and right boundaries for each bar's potential largest rectangle in a single pass, which is the most efficient way to solve this problem.
import java.util.Stack;

class Solution {
    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        Stack<Integer> stack = new Stack<>();
        int maxArea = 0;

        for (int i = 0; i <= n; i++) {
            int currHeight = (i == n) ? 0 : heights[i];

            while (!stack.isEmpty() && currHeight < heights[stack.peek()]) {
                int poppedHeightIndex = stack.pop();
                int h = heights[poppedHeightIndex]; 

                int w = stack.isEmpty() ? i : (i - stack.peek() - 1);

                maxArea = Math.max(maxArea, h * w);
            }
            stack.push(i);
        }
        return maxArea;
    }
}
