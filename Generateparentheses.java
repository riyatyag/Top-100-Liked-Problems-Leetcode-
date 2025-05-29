// Problem Statement:
// Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
//
// Example 1:
// Input: n = 3
// Output: ["((()))","(()())","(())()","()(())","()()()"]
//
// Example 2:
// Input: n = 1
// Output: ["()"]
//
// Constraints:
// 1 <= n <= 8

// Approach:
// This problem is a classic application of backtracking. We need to generate all combinations of well-formed parentheses.
// A string of parentheses is well-formed if:
// 1. The total number of '(' equals the total number of ')'.
// 2. For any prefix of the string, the number of '(' is greater than or equal to the number of ')'.
//
// We can use a recursive backtracking approach to build the parentheses strings character by character.
//
// The `generateParenthesis` function initializes the `result` list and calls the `backtrack` helper.
//
// The `backtrack` function takes:
// - `result`: The list to store all valid well-formed parentheses strings.
// - `current`: The current string of parentheses being built.
// - `open`: The count of open parentheses already added to `current`.
// - `close`: The count of close parentheses already added to `current`.
// - `n`: The total number of pairs of parentheses required.
//
// Base Case:
// If the length of `current` string is `2 * n` (meaning we have placed all `n` open and `n` close parentheses),
// then `current` is a valid well-formed string, so add it to `result`.
//
// Recursive Steps (Choices):
// 1. Add an open parenthesis '(':
//    This is valid if `open < n` (we haven't used all `n` open parentheses yet).
//    If valid, append '(' to `current`, increment `open`, and recursively call `backtrack`.
//    After the recursive call, backtrack by removing '(' from `current` (implicitly by using `String` and creating new ones, or by using a `StringBuilder` and `deleteCharAt`).
//
// 2. Add a close parenthesis ')':
//    This is valid if `close < open` (we can only add a close parenthesis if there's an unmatched open parenthesis before it).
//    If valid, append ')' to `current`, increment `close`, and recursively call `backtrack`.
//    After the recursive call, backtrack by removing ')' from `current`.
//
// The approach uses a `StringBuilder` for efficient string manipulation to avoid creating many intermediate `String` objects, though using `String` concatenation directly also works for small `n`. The provided solution uses `String` concatenation which creates new String objects in each recursive call, making the backtracking simpler (no explicit removal needed).

// Time Complexity:
// The time complexity is related to the Nth Catalan number, C_n = (1/(n+1)) * (2n choose n).
// This is because the number of well-formed parentheses sequences is given by the Catalan numbers.
// For each valid sequence, we construct a string of length 2n.
// So, the total time complexity is approximately O((4^n) / (n * sqrt(n))).
// Specifically, it's O(C_n * 2n) because each of the C_n valid sequences takes O(2n) to construct.
// For n=8, C_8 is 1430, and 2n is 16, so operations are roughly 1430 * 16, which is small.

// Space Complexity:
// The space complexity is O(N) for the recursion stack depth (since the maximum length of a string is 2N, the recursion depth is 2N).
// The space to store the `result` list is O(C_n * 2n), as we store C_n strings each of length 2n.

class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        backtrack(result, "", 0, 0, n);
        return result;
    }

    private void backtrack(List<String> result, String current, int open, int close, int n) {
        if (current.length() == 2 * n) {
            result.add(current);
            return;
        }
        if (open < n) {
            backtrack(result, current + "(", open + 1, close, n);
        }
        if (close < open) {
            backtrack(result, current + ")", open, close + 1, n);
        }
    }
}