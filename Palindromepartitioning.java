// Problem Statement:
// Given a string s, partition s such that every substring of the partition is a palindrome. Return all possible palindrome partitioning of s.
//
// Example 1:
// Input: s = "aab"
// Output: [["a","a","b"],["aa","b"]]
//
// Example 2:
// Input: s = "a"
// Output: [["a"]]
//
// Constraints:
// 1 <= s.length <= 16
// s contains only lowercase English letters.

// Approach:
// This problem can be solved using backtracking. We will explore all possible partitions of the string.
// For each substring starting from the current position, we check if it's a palindrome.
// If it is, we add it to the current partition and recursively call the function for the remaining part of the string.
// After the recursive call returns, we backtrack by removing the last added substring to explore other possibilities.
// The base case for the recursion is when we have processed the entire string.

// Time Complexity:
// The time complexity is difficult to express precisely with a simple formula, but it is exponential.
// In the worst case, for a string of length N, we might check O(2^N) partitions.
// For each partition, checking if a substring is a palindrome takes O(length of substring).
// A tighter upper bound for the number of palindrome partitions is O(N * 2^N).
// The overall time complexity is roughly O(N * 2^N) because for each of the 2^N possible partitions, we are iterating up to N characters to form substrings and checking for palindromes.

// Space Complexity:
// The space complexity is O(N^2) in the worst case due to the recursion stack depth and storing the list of lists.
// The recursion depth can go up to N.
// The `currentPartition` list can store up to N substrings.
// In the worst case, the total space to store the `result` list can be significant, potentially O(N * 2^N) if every single character is a palindrome (e.g., "aaaaa"). However, for the purpose of auxiliary space used by the algorithm, it's dominated by the recursion stack and the temporary `currentPartition` list.

class Solution {
    public List<List<String>> partition(String s) {
        List<List<String>> result = new ArrayList<>();
        backtrack(s, 0, new ArrayList<>(), result);
        return result;
    }

    private void backtrack(String s, int start, List<String> currentPartition, List<List<String>> result) {
        if (start == s.length()) {
            result.add(new ArrayList<>(currentPartition));
            return;
        }

        for (int i = start; i < s.length(); i++) {
            String sub = s.substring(start, i + 1);
            if (isPalindrome(sub)) {
                currentPartition.add(sub);
                backtrack(s, i + 1, currentPartition, result);
                currentPartition.remove(currentPartition.size() - 1);
            }
        }
    }

    private boolean isPalindrome(String str) {
        int left = 0;
        int right = str.length() - 1;
        while (left < right) {
            if (str.charAt(left) != str.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}