// Problem Statement:
// Given two strings word1 and word2, return the minimum number of operations required to convert word1 to word2.
// You have the following three operations permitted on a word: Insert a character, Delete a character, Replace a character.

// Approach:
// This is a classic dynamic programming problem. We can use a 2D DP array, `dp[i][j]`, which represents the minimum edit distance between the first `i` characters of `word1` and the first `j` characters of `word2`.

// 1. Initialization:
//    - `dp[0][0] = 0` (empty string to empty string requires 0 operations).
//    - `dp[i][0] = i` for `i` from 1 to `m` (to convert `word1[0...i-1]` to an empty string, we need `i` deletions).
//    - `dp[0][j] = j` for `j` from 1 to `n` (to convert an empty string to `word2[0...j-1]`, we need `j` insertions).

// 2. Recurrence Relation:
//    Iterate `i` from 1 to `m` and `j` from 1 to `n`:
//    - If `word1.charAt(i-1) == word2.charAt(j-1)` (current characters match):
//      No operation is needed for these characters. So, `dp[i][j] = dp[i-1][j-1]`.
//    - If `word1.charAt(i-1) != word2.charAt(j-1)` (current characters do not match):
//      We need to consider three possibilities and take the minimum:
//      a. Insert: Insert `word2.charAt(j-1)` into `word1`. This is equivalent to converting `word1[0...i-1]` to `word2[0...j-2]` and then inserting the last character. So, `1 + dp[i][j-1]`.
//      b. Delete: Delete `word1.charAt(i-1)` from `word1`. This is equivalent to converting `word1[0...i-2]` to `word2[0...j-1]` and then deleting the last character. So, `1 + dp[i-1][j]`.
//      c. Replace: Replace `word1.charAt(i-1)` with `word2.charAt(j-1)`. This is equivalent to converting `word1[0...i-2]` to `word2[0...j-2]` and then replacing the last character. So, `1 + dp[i-1][j-1]`.
//      Therefore, `dp[i][j] = 1 + min(dp[i][j-1], dp[i-1][j], dp[i-1][j-1])`.
// 3. Result:
//    `dp[m][n]` will contain the minimum edit distance.

// Time Complexity:
// O(m * n) where `m` is the length of `word1` and `n` is the length of `word2`. We fill an `m x n` DP table.

// Space Complexity:
// O(m * n) for the 2D DP array.

// Optimal Solution:
class Solution {
    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();

        int[][] dp = new int[m + 1][n + 1];

        for (int i = 0; i <= m; i++) {
            dp[i][0] = i;
        }
        for (int j = 0; j <= n; j++) {
            dp[0][j] = j;
        }
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    int insert = dp[i][j - 1];
                    int delete = dp[i - 1][j];  
                    int replace = dp[i - 1][j - 1]; 
                    dp[i][j] = 1 + Math.min(insert, Math.min(delete, replace));
                }
            }
        }
        return dp[m][n];
    }
}