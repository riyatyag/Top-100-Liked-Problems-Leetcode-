// Problem Statement:
// Given two strings text1 and text2, return the length of their longest common subsequence. If there is no common subsequence, return 0.
// A subsequence of a string is a new string generated from the original string with some characters (can be none) deleted without changing the relative order of the remaining characters.
// For example, "ace" is a subsequence of "abcde".
// A common subsequence of two strings is a subsequence that is common to both strings.

// Approach:
// This is a classic dynamic programming problem. We can define a 2D DP array, `dp[i][j]`, which represents the length of the longest common subsequence of `text1[0...i-1]` and `text2[0...j-1]`.
// The base cases are when either `i` or `j` is 0, in which case `dp[i][j]` will be 0 (an empty string has no common subsequence with any other string).

// For the recursive step, consider `dp[i][j]`:
// 1. If `text1.charAt(i-1)` (the last character of `text1[0...i-1]`) is equal to `text2.charAt(j-1)` (the last character of `text2[0...j-1]`):
//    It means these two characters can be part of the common subsequence. So, we add 1 to the LCS of the strings without these characters:
//    `dp[i][j] = 1 + dp[i-1][j-1]`
// 2. If `text1.charAt(i-1)` is not equal to `text2.charAt(j-1)`:
//    It means we cannot include both of these characters in the common subsequence. We must choose the maximum LCS from two possibilities:
//    a. Exclude `text1.charAt(i-1)`: `dp[i-1][j]`
//    b. Exclude `text2.charAt(j-1)`: `dp[i][j-1]`
//    So, `dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1])`

// After filling the DP table, `dp[m][n]` will contain the length of the longest common subsequence of `text1` and `text2`.

// Time Complexity:
// O(m*n) where m is the length of text1 and n is the length of text2.
// We fill an m x n DP table, and each cell takes constant time to compute.

// Space Complexity:
// O(m*n) for the 2D DP array.

// Optimal Solution:
class Solution {
    public int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();
        int[][] dp = new int[m + 1][n + 1];

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[m][n];
    }
}