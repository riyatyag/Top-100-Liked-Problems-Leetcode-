// Problem Statement:
// Given two strings s and p, return an array of all the start indices of p's anagrams in s. You may return the answer in any order.

// Approach:
// This problem can be efficiently solved using a sliding window approach with character frequency maps.
// The core idea is that two strings are anagrams of each other if they have the same character counts.
// We maintain two frequency arrays: one for the pattern `p` (pCount) and one for the current sliding window in `s` (sCount).

// 1. Initialize:
//    - Create a List `result` to store the starting indices of anagrams.
//    - Handle edge case: if `s` is shorter than `p`, no anagram is possible, so return an empty `result` list.
//    - Initialize two frequency arrays, `pCount` and `sCount`, of size 26 (for lowercase English letters), filled with zeros.

// 2. Populate initial window:
//    - Iterate through the first `p.length()` characters of both `s` and `p`.
//    - For each character, increment its count in `pCount` (for `p`) and `sCount` (for the initial window of `s`).

// 3. Sliding Window:
//    - Initialize `left` pointer to 0.
//    - Iterate `right` pointer from `p.length()` to `s.length() - 1`.
//    - In each iteration:
//      - Check if `sCount` and `pCount` are identical. If they are, it means the current window `s.substring(left, right + 1)` is an anagram of `p`. Add `left` to the `result` list.
//      - Move the window:
//        - Decrement the count of the character at the `left` pointer in `sCount` (as it's leaving the window).
//        - Increment the count of the character at the `right` pointer in `sCount` (as it's entering the window).
//        - Increment `left` pointer by 1.

// 4. Final Check:
//    - After the loop finishes, there's one last window to check (the very last window ending at `s.length() - 1`). Check if `sCount` and `pCount` are identical for this final window and add `left` (which will be `s.length() - p.length()`) to `result` if they are.

// Helper function `arraysMatch(int[] arr1, int[] arr2)`:
// - This private helper function compares two integer arrays element by element.
// - It returns `true` if all corresponding elements are equal, `false` otherwise. This is used to check if the character counts match.

// Time Complexity:
// O(Ls + 26 * (Ls - Lp + 1)), where Ls is the length of string s and Lp is the length of string p.
// - Initial window population: O(Lp)
// - Sliding window loop runs Ls - Lp + 1 times.
// - Inside the loop, array comparisons take O(26) time.
// - So, the dominant factor is O(Ls) for iterating through string `s`.

// Space Complexity:
// O(1) because the frequency arrays are of fixed size 26, regardless of the input string lengths. The `result` list can store up to O(Ls) indices in the worst case, but the auxiliary space used for computation is constant.

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<>();
        if (s.length() < p.length()) return result;

        int[] pCount = new int[26];
        int[] sCount = new int[26];

        for (int i = 0; i < p.length(); i++) {
            pCount[p.charAt(i) - 'a']++;
            sCount[s.charAt(i) - 'a']++;
        }
        for (int i = 0; i < s.length() - p.length(); i++) {
            if (Arrays.equals(sCount, pCount)) {
                result.add(i);
            }
            sCount[s.charAt(i) - 'a']--;
            sCount[s.charAt(i + p.length()) - 'a']++;
        }
        if (Arrays.equals(sCount, pCount)) {
            result.add(s.length() - p.length());
        }

        return result;
    }
}