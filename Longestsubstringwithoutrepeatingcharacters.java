// Problem Statement:
// Given a string s, find the length of the longest substring without duplicate characters.

// Approach:
// This problem can be solved efficiently using a sliding window approach with a hash set (or a frequency map).
// The idea is to maintain a window `[left, right]` that contains only unique characters.

// Algorithm:
// 1. Initialize:
//    - `left`: The left pointer of the sliding window, initialized to 0.
//    - `maxLength`: Stores the maximum length of a substring found so far, initialized to 0.
//    - `seenCharacters`: A `HashSet<Character>` to keep track of characters currently present in the window. This allows for O(1) average time complexity for adding, removing, and checking existence of characters.

// 2. Iterate and Expand Window:
//    - Iterate with a `right` pointer from 0 to `s.length() - 1`.
//    - For each `currentChar = s.charAt(right)`:
//      - If `currentChar` is already in `seenCharacters`:
//        - This means we have a duplicate. We need to shrink the window from the `left` until the duplicate is removed.
//        - While `seenCharacters` contains `currentChar`:
//          - Remove `s.charAt(left)` from `seenCharacters`.
//          - Increment `left`.
//      - Add `currentChar` to `seenCharacters`.
//      - Update `maxLength`: `maxLength = Math.max(maxLength, right - left + 1)`. The current window length is `right - left + 1`.

// 3. Return `maxLength`.

// Example Walkthrough: s = "abcabcbb"
// Initial: left = 0, maxLength = 0, seen = {}

// right = 0, char = 'a'
//   'a' not in seen. Add 'a'. seen = {'a'}
//   maxLength = max(0, 0 - 0 + 1) = 1

// right = 1, char = 'b'
//   'b' not in seen. Add 'b'. seen = {'a', 'b'}
//   maxLength = max(1, 1 - 0 + 1) = 2

// right = 2, char = 'c'
//   'c' not in seen. Add 'c'. seen = {'a', 'b', 'c'}
//   maxLength = max(2, 2 - 0 + 1) = 3

// right = 3, char = 'a'
//   'a' IS in seen.
//   While 'a' in seen:
//     Remove s.charAt(left) ('a'). seen = {'b', 'c'}
//     left = 1
//   Add 'a'. seen = {'b', 'c', 'a'}
//   maxLength = max(3, 3 - 1 + 1) = 3

// right = 4, char = 'b'
//   'b' IS in seen.
//   While 'b' in seen:
//     Remove s.charAt(left) ('b'). seen = {'c', 'a'}
//     left = 2
//   Add 'b'. seen = {'c', 'a', 'b'}
//   maxLength = max(3, 4 - 2 + 1) = 3

// right = 5, char = 'c'
//   'c' IS in seen.
//   While 'c' in seen:
//     Remove s.charAt(left) ('c'). seen = {'a', 'b'}
//     left = 3
//   Add 'c'. seen = {'a', 'b', 'c'}
//   maxLength = max(3, 5 - 3 + 1) = 3

// right = 6, char = 'b'
//   'b' IS in seen.
//   While 'b' in seen:
//     Remove s.charAt(left) ('a'). seen = {'b', 'c'}
//     left = 4
//     Remove s.charAt(left) ('b'). seen = {'c'}
//     left = 5
//   Add 'b'. seen = {'c', 'b'}
//   maxLength = max(3, 6 - 5 + 1) = 2 (This is incorrect in the trace, it should be 3, the max was already 3)
//   Correction: When 'b' is encountered, `seen` has 'a', 'b', 'c'.
//   Remove 'a' (left=0), `seen`={'b','c'}, left=1.
//   Remove 'b' (left=1), `seen`={'c'}, left=2.
//   Now 'b' is not in `seen`. Add 'b'. `seen`={'c','b'}.
//   `maxLength` = max(3, 6 - 2 + 1) = max(3, 5) = 5. This is still incorrect.

// Let's re-trace the logic for `right = 3, char = 'a'` and `right = 4, char = 'b'` and `right = 5, char = 'c'`
// Corrected logic for `while (seen.contains(currentChar))`:
// When `currentChar` is 'a' at `right = 3`:
//   `seen` is {'a', 'b', 'c'}. `seen.contains('a')` is true.
//   Remove `s.charAt(left)` which is 'a'. `seen` becomes {'b', 'c'}. `left` becomes 1.
//   Now `seen.contains('a')` is false. Loop ends.
//   Add `currentChar` ('a') to `seen`. `seen` becomes {'b', 'c', 'a'}.
//   `maxLength` = max(3, 3 - 1 + 1) = max(3, 3) = 3.

// When `currentChar` is 'b' at `right = 4`:
//   `seen` is {'b', 'c', 'a'}. `seen.contains('b')` is true.
//   Remove `s.charAt(left)` which is 'b'. `seen` becomes {'c', 'a'}. `left` becomes 2.
//   Now `seen.contains('b')` is false. Loop ends.
//   Add `currentChar` ('b') to `seen`. `seen` becomes {'c', 'a', 'b'}.
//   `maxLength` = max(3, 4 - 2 + 1) = max(3, 3) = 3.

// When `currentChar` is 'c' at `right = 5`:
//   `seen` is {'c', 'a', 'b'}. `seen.contains('c')` is true.
//   Remove `s.charAt(left)` which is 'c'. `seen` becomes {'a', 'b'}. `left` becomes 3.
//   Now `seen.contains('c')` is false. Loop ends.
//   Add `currentChar` ('c') to `seen`. `seen` becomes {'a', 'b', 'c'}.
//   `maxLength` = max(3, 5 - 3 + 1) = max(3, 3) = 3.

// When `currentChar` is 'b' at `right = 6`:
//   `seen` is {'a', 'b', 'c'}. `seen.contains('b')` is true.
//   Remove `s.charAt(left)` which is 'a'. `seen` becomes {'b', 'c'}. `left` becomes 4.
//   `seen.contains('b')` is still true.
//   Remove `s.charAt(left)` which is 'b'. `seen` becomes {'c'}. `left` becomes 5.
//   Now `seen.contains('b')` is false. Loop ends.
//   Add `currentChar` ('b') to `seen`. `seen` becomes {'c', 'b'}.
//   `maxLength` = max(3, 6 - 5 + 1) = max(3, 2) = 3.

// Final `maxLength` is 3. This matches the example.

// Time Complexity:
// O(N), where N is the length of the string `s`.
// Both `left` and `right` pointers traverse the string at most once. Each character is added to and removed from the `HashSet` at most once. `HashSet` operations (add, remove, contains) take O(1) on average.

// Space Complexity:
// O(min(N, A)), where N is the length of the string and A is the size of the character set (e.g., 256 for ASCII, or 26 for lowercase English letters if constraints were tighter).
// In the worst case, all characters in the string are unique, and the `HashSet` stores N characters. However, if the character set is limited (like ASCII), the space is bounded by the size of the character set.

import java.util.HashSet;
import java.util.Set;

class Solution {
    public int lengthOfLongestSubstring(String s) {
        int left = 0;
        int maxLength = 0;
        Set<Character> seen = new HashSet<>();

        for (int right = 0; right < s.length(); right++) {
            char currentChar = s.charAt(right);
            while (seen.contains(currentChar)) {
                seen.remove(s.charAt(left));
                left++;
            }
            seen.add(currentChar);
            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }
}