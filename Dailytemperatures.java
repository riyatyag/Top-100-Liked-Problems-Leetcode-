// Problem Statement:
// Given an array of integers temperatures represents the daily temperatures, return an array answer such that answer[i] is the number of days you have to wait after the ith day to get a warmer temperature. If there is no future day for which this is possible, keep answer[i] == 0 instead.

// Approach:
// This problem can be efficiently solved using a monotonic stack. We iterate through the temperatures array from right to left.
// The stack will store the indices of days in decreasing order of their temperatures.
// For each day 'i' (starting from the last day and moving backwards):
// 1. While the stack is not empty and the temperature at the index on top of the stack is less than or equal to the current day's temperature, pop elements from the stack. This is because these popped days cannot be the next warmer day for any previous days, as the current day 'i' is warmer or equal.
// 2. If the stack is not empty after popping, it means the top of the stack now holds the index of the next warmer day. The number of days to wait is the difference between the index on top of the stack and the current day's index. Store this value in answer[i].
// 3. Push the current day's index 'i' onto the stack. This is because the current day's temperature might be a warmer day for days to its left.

// Time Complexity:
// O(n), where 'n' is the number of elements in the temperatures array. Each element is pushed onto the stack and popped from the stack at most once. The main loop runs 'n' times.

// Space Complexity:
// O(n), in the worst case, if the temperatures are in strictly decreasing order (e.g., [100, 90, 80, ...]), all indices will be stored in the stack.

// Optimal Solution:
// The monotonic stack approach is optimal because it solves the problem in a single pass (from right to left) with linear time complexity and linear space complexity. It avoids nested loops or repeated scans, which would lead to higher time complexities.
import java.util.Stack;

class Solution {
    public int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length;
        int[] answer = new int[n];
        Stack<Integer> stack = new Stack<>();

        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && temperatures[stack.peek()] <= temperatures[i]) {
                stack.pop();
            }
            if (!stack.isEmpty()) {
                answer[i] = stack.peek() - i;
            }
            stack.push(i);
        }
        return answer;
    }
}
