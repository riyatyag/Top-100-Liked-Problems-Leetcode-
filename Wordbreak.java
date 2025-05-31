// Problem Statement:
// Given a string s and a dictionary of strings wordDict, return true if s can be segmented into a space-separated sequence of one or more dictionary words.
// Note that the same word in the dictionary may be reused multiple times in the segmentation.

// Approach:
// This is a dynamic programming problem. We can use a boolean array `dp` where `dp[i]` is true if the substring `s[0...i-1]` can be segmented into words from `wordDict`, and false otherwise.

// 1. Initialization:
//    `dp` array of size `n + 1`, where `n` is the length of `s`.
//    `dp[0] = true` because an empty string can always be segmented (it requires no words).

// 2. Pre-processing `wordDict`:
//    Convert `wordDict` into a `HashSet` for efficient `O(1)` average time complexity lookups.

// 3. Filling the `dp` array:
//    Iterate `i` from 1 to `n` (representing the end index of the current substring in `s`).
//    For each `i`, iterate `j` from 0 to `i-1` (representing the start index of a potential last word).
//    - If `dp[j]` is true (meaning `s[0...j-1]` can be segmented) AND
//    - The substring `s.substring(j, i)` is present in `wordSet`:
//      Then, `s[0...i-1]` can be segmented, so set `dp[i] = true` and break the inner loop (no need to check further `j` for this `i`).

// 4. Result:
//    The final answer is `dp[n]`.

// Time Complexity:
// O(N^2 * L) in the worst case, where N is the length of `s` and L is the average length of words in `wordDict` (due to `substring` operation).
// If `substring` takes O(1) time (e.g., passing indices/pointers), it's O(N^2).
// The `HashSet` lookup is O(L) on average, but can be O(L) in Java String hashing worst case if many collisions occur.

// Space Complexity:
// O(N) for the `dp` array.
// O(M * L) for the `HashSet`, where M is the number of words in `wordDict` and L is their average length.

// Optimal Solution:
import java.util.List;
import java.util.HashSet;

class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        HashSet<String> wordSet = new HashSet<>(wordDict);
        int n = s.length();
        boolean[] dp = new boolean[n + 1];
        dp[0] = true; 

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < i; j++) { 
                if (dp[j] && wordSet.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break; 
                }
            }
        }
        return dp[n];
    }
}