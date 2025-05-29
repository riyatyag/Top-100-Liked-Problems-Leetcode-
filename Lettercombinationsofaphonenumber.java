// Problem Statement:
// Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent. Return the answer in any order.
// A mapping of digits to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.
//
// Example 1:
// Input: digits = "23"
// Output: ["ad","ae","af","bd","be","bf","cd","ce","cf"]
//
// Example 2:
// Input: digits = ""
// Output: []
//
// Example 3:
// Input: digits = "2"
// Output: ["a","b","c"]
//
// Constraints:
// 0 <= digits.length <= 4
// digits[i] is a digit in the range ['2', '9'].

// Approach:
// This problem can be solved using backtracking. We need to generate all possible combinations of letters that correspond to the given digits.
// We can use a mapping from digits to their corresponding letters, similar to a phone keypad.
//
// The `letterCombinations` function handles the initial setup, including the edge case of an empty `digits` string and defining the mapping.
// It then calls a `backtrack` helper function.
//
// The `backtrack` function takes:
// - `result`: The list to store all valid letter combinations.
// - `currentCombination`: The current letter combination being built (using a StringBuilder for efficient modification).
// - `digits`: The input string of digits.
// - `index`: The current digit index we are processing in the `digits` string.
// - `mapping`: The predefined array mapping digits to letters.
//
// Base Case:
// If `index` equals the length of `digits`, it means we have processed all digits and formed a complete combination.
// In this case, add the `currentCombination` (converted to a String) to the `result` list.
//
// Recursive Step:
// 1. Get the current digit from `digits` using `digits.charAt(index)`.
// 2. Convert the digit character to an integer to use as an index for the `mapping` array.
// 3. Get the corresponding letters string for that digit from `mapping`.
// 4. Iterate through each character `c` in the letters string:
//    a. Append `c` to `currentCombination`.
//    b. Recursively call `backtrack` with `index + 1` to process the next digit.
//    c. Backtrack: Delete the last character from `currentCombination` to explore other possibilities.

// Time Complexity:
// The time complexity depends on the number of digits `N` and the number of letters mapped to each digit (usually 3 or 4).
// Let L be the maximum number of letters a digit maps to (L=4 for '7' and '9').
// The total number of combinations generated is approximately L^N. For each combination, we perform string concatenation (or StringBuilder appends/deletes), which takes O(N) time.
// So, the total time complexity is O(L^N * N).
// Given N <= 4, L <= 4, the max operations would be 4^4 * 4 = 256 * 4 = 1024, which is very fast.

// Space Complexity:
// The space complexity is O(N) for the recursion stack depth (where N is the length of `digits`).
// The `currentCombination` StringBuilder also takes O(N) space.
// The `result` list stores all generated combinations. In the worst case, it can store up to L^N strings, each of length N.
// So, the total space complexity is O(L^N * N).

class Solution {
    private String[] mapping = {
        "",     
        "",     
        "abc",  
        "def", 
        "ghi",  
        "jkl",  
        "mno",  
        "pqrs",
        "tuv",  
        "wxyz"  
    };
    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        if (digits == null || digits.length() == 0) {
            return result;
        }

        backtrack(result, new StringBuilder(), digits, 0);
        return result;
    }

    private void backtrack(List<String> result, StringBuilder currentCombination, String digits, int index) {
        if (index == digits.length()) {
            result.add(currentCombination.toString());
            return;
        }

        char digit = digits.charAt(index);
        String letters = mapping[digit - '0']; 
        for (char c : letters.toCharArray()) {
            currentCombination.append(c);
            backtrack(result, currentCombination, digits, index + 1);
            currentCombination.deleteCharAt(currentCombination.length() - 1);
        }
    }
}