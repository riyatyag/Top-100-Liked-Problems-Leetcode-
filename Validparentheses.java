// Problem Statement:
// Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
// An input string is valid if:
// 1. Open brackets must be closed by the same type of brackets.
// 2. Open brackets must be closed in the correct order.
// 3. Every close bracket has a corresponding open bracket of the same type.

// Approach:
// This problem can be effectively solved using a stack data structure.
// We iterate through each character of the input string.
// 1. If the character is an opening bracket ('(', '{', '['), we push it onto the stack.
// 2. If the character is a closing bracket (')', '}', ']'):
//    a. We first check if the stack is empty. If it is, it means there's a closing bracket without a corresponding opening bracket, so the string is invalid.
//    b. If the stack is not empty, we pop the top element from the stack.
//    c. We then check if the popped opening bracket matches the current closing bracket (e.g., if ')' is encountered, the popped element should be '('). If they don't match, the order is incorrect, and the string is invalid.
// After iterating through all characters in the string, if the stack is empty, it means all opening brackets have been correctly closed. If the stack is not empty, it means there are unclosed opening brackets, and thus the string is invalid.

// Time Complexity:
// O(n), where 'n' is the length of the input string. We iterate through the string once, and each stack operation (push, pop, isEmpty, peek) takes O(1) time.

// Space Complexity:
// O(n), in the worst case. For example, if the input string consists only of opening brackets like "((((((", the stack will store all 'n' characters.

// Optimal Solution:
// The stack-based approach is optimal for this problem as it processes the string in a single pass with constant time operations per character, leading to a linear time complexity. It correctly handles the nested and ordered nature of parentheses validation.
import java.util.Stack;

class Solution {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();

        for (char ch : s.toCharArray()) {
            if (ch == '(' || ch == '{' || ch == '[') {
                stack.push(ch);
            } else {
                if (stack.isEmpty()) {
                    return false;
                }

                char top = stack.pop(); 

                if (ch == ')' && top != '(') {
                    return false;
                }
                if (ch == '}' && top != '{') {
                    return false;
                }
                if (ch == ']' && top != '[') {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }
}
