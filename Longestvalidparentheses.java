// Problem Statement:
// Given a string containing just the characters '(' and ')', return the length of the longest valid (well-formed) parentheses substring.

// Approach:
// This problem can be solved using a stack. The idea is to keep track of the indices of opening parentheses and the last invalid closing parenthesis.

// 1. Initialization:
//    - `maxLength = 0` to store the maximum valid length found.
//    - Create a `Stack<Integer>` and push `-1` onto it. This `-1` acts as a base for calculating lengths of valid parentheses substrings that start from index 0. It effectively marks the position *before* the start of the current valid substring.

// 2. Iterate through the string:
//    - For each character at index `i`:
//      a. If `s.charAt(i) == '('`:
//         Push the current index `i` onto the stack.
//      b. If `s.charAt(i) == ')'`:
//         Pop an element from the stack.
//         - If the stack becomes empty after popping (meaning there was no matching opening parenthesis or the previous substring ended here):
//           Push the current index `i` onto the stack. This `i` now acts as the new "base" or "last invalid character" index for future calculations.
//         - If the stack is not empty after popping:
//           The current valid substring ends at `i` and starts just after the index at the top of the stack.
//           The length of this valid substring is `i - stack.peek()`.
//           Update `maxLength = Math.max(maxLength, i - stack.peek())`.

// 3. Result:
//    Return `maxLength`.

// Example trace for s = ")()())":
// Stack: [-1]
// maxLength = 0

// i = 0, char = ')'
//   Pop. Stack empty. Push 0. Stack: [0]
// i = 1, char = '('
//   Push 1. Stack: [0, 1]
// i = 2, char = ')'
//   Pop 1. Stack: [0]. Length = 2 - stack.peek() = 2 - 0 = 2. maxLength = 2.
// i = 3, char = '('
//   Push 3. Stack: [0, 3]
// i = 4, char = ')'
//   Pop 3. Stack: [0]. Length = 4 - stack.peek() = 4 - 0 = 4. maxLength = 4.
// i = 5, char = ')'
//   Pop. Stack empty. Push 5. Stack: [5]

// Result: 4

// Time Complexity:
// O(N) where N is the length of the string `s`. We iterate through the string once. Stack operations (push, pop, peek) are O(1).

// Space Complexity:
// O(N) in the worst case (e.g., "((((("), where the stack might store up to N indices.

// Optimal Solution:
import java.util.Stack;

class Solution {
    public int longestValidParentheses(String s) {
        int maxLength = 0;
        Stack<Integer> stack = new Stack<>();
        stack.push(-1); 

        for (int i = 0; i < s.length(); i++) {
            char currentChar = s.charAt(i);
            if (currentChar == '(') {
                stack.push(i); 
            } else { 
                stack.pop();
                if (stack.empty()) {
                    stack.push(i);
                } else {
                    maxLength = Math.max(maxLength, i - stack.peek());
                }
            }
        }
        return maxLength;
    }
}