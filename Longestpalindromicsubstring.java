// Problem Statement:
// Given a string s, return the longest palindromic substring in s.

// Approach:
// This problem can be solved using the "Expand Around Center" approach. A palindrome is symmetric around its center. The center can be a single character (e.g., "aba" centered at 'b') or two characters (e.g., "abba" centered between the two 'b's).
// We iterate through each possible center and expand outwards to find the longest palindrome centered at that point.

// Algorithm:
// 1. Initialize `start = 0` and `end = 0` to keep track of the start and end indices of the longest palindrome found so far.
// 2. Iterate through each character `i` from 0 to `s.length() - 1`. For each `i`, consider two cases for the center:
//    a. **Single character center:** Call a helper function `expandAroundCenter(s, i, i)`. This handles palindromes of odd length.
//    b. **Two character center:** Call a helper function `expandAroundCenter(s, i, i + 1)`. This handles palindromes of even length.
// 3. The `expandAroundCenter` helper function takes the string `s` and two indices `left` and `right`. It expands `left` leftwards and `right` rightwards as long as `left >= 0`, `right < s.length()`, and `s.charAt(left) == s.charAt(right)`. It returns the length of the palindrome found (which is `right - left - 1`).
// 4. In the main loop, get `len1` and `len2` from the two calls to `expandAroundCenter`.
// 5. Take the maximum of `len1` and `len2`. Let this be `len`.
// 6. If `len` is greater than `end - start + 1` (the current longest palindrome length), update `start` and `end`.
//    - `start = i - (len - 1) / 2`
//    - `end = i + len / 2`
//    These formulas correctly calculate the start and end indices for both odd and even length palindromes.
// 7. After iterating through all possible centers, return `s.substring(start, end + 1)`.

// Time Complexity:
// O(N^2) where N is the length of the string. We iterate through N possible centers, and for each center, expanding outwards takes O(N) time in the worst case.

// Space Complexity:
// O(1) as we only use a few variables to store indices and lengths. The substring creation at the end takes O(L) where L is the length of the longest palindrome, which is at most O(N).

// Optimal Solution:
class Solution {
    public String longestPalindrome(String s) {
        if (s == null || s.length() < 1) {
            return "";
        }
        int start = 0;
        int end = 0;  

        for (int i = 0; i < s.length(); i++) {
            int len1 = expandAroundCenter(s, i, i);
            int len2 = expandAroundCenter(s, i, i + 1);

            int len = Math.max(len1, len2);

            if (len > end - start + 1) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }
    private int expandAroundCenter(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return right - left - 1;
    }
}