// Problem Statement:
// Given two strings s and t of lengths m and n respectively, return the minimum window substring of s such that every character in t (including duplicates) is included in the window. If there is no such substring, return the empty string "".
// The testcases will be generated such that the answer is unique.

// Approach:
// This problem can be solved using the sliding window technique combined with two hash maps (or frequency arrays).
// One hash map (`tMap`) will store the frequency of characters required from string `t`.
// Another hash map (`windowMap`) will store the frequency of characters within the current sliding window in string `s`.

// The algorithm involves expanding the window from the right and contracting it from the left.

// 1. Initialize:
//    - Handle edge cases: If `s` or `t` are null, or `s` is shorter than `t`, return an empty string.
//    - `tMap`: A `HashMap` to store character counts for string `t`. Populate this map by iterating through `t`.
//    - `windowMap`: A `HashMap` to store character counts for the current window in `s`.
//    - `matchedChars`: An integer to keep track of how many unique characters from `t` have been matched with the required frequency in the current window.
//    - `minLength`: An integer initialized to `Integer.MAX_VALUE` to store the minimum length found so far.
//    - `startIndex`: An integer to store the starting index of the minimum window.
//    - `left`: Left pointer of the sliding window, initialized to 0.

// 2. Expand the window (Right pointer `right`):
//    - Iterate `right` from 0 to `s.length() - 1`.
//    - Get the character `charRight = s.charAt(right)`.
//    - Add `charRight` to `windowMap` and increment its count.
//    - Check if `charRight` is a character we need from `t` (i.e., `tMap.containsKey(charRight)`).
//    - If it is, and its count in `windowMap` now matches its count in `tMap`, it means we have fulfilled the requirement for this specific character. Increment `matchedChars`.

// 3. Contract the window (Left pointer `left`) when all characters are matched:
//    - While `matchedChars` equals `tMap.size()` (meaning all unique characters from `t` are covered with their required frequencies):
//      - Calculate the current window length: `currentLength = right - left + 1`.
//      - If `currentLength` is less than `minLength`:
//        - Update `minLength = currentLength`.
//        - Update `startIndex = left`.
//      - Get the character `charLeft = s.charAt(left)`.
//      - Decrement `charLeft`'s count in `windowMap`.
//      - If `charLeft` is a character from `t` AND its count in `windowMap` is now less than its count in `tMap`, it means we are losing a required character. Decrement `matchedChars`.
//      - Move the left pointer: `left++`.

// 4. Return Result:
//    - After the loops, if `minLength` is still `Integer.MAX_VALUE`, it means no valid window was found, so return an empty string.
//    - Otherwise, return the substring of `s` from `startIndex` with length `minLength`.

// Time Complexity:
// O(M + N), where M is the length of `s` and N is the length of `t`.
// - Populating `tMap` takes O(N) time.
// - The `right` pointer iterates through `s` once (O(M)).
// - The `left` pointer also iterates through `s` at most once (O(M)).
// - Hash Map operations (put, get, containsKey) take O(1) on average.
// Thus, the total time complexity is O(M + N).

// Space Complexity:
// O(1) because the hash maps will store at most 52 unique characters (26 uppercase + 26 lowercase English letters), which is a constant space.

import java.util.HashMap;
import java.util.Map;

class Solution {
    public String minWindow(String s, String t) {
        if (s == null || t == null || s.length() < t.length()) {
            return "";
        }

        Map<Character, Integer> tMap = new HashMap<>();
        for (char ch : t.toCharArray()) {
            tMap.put(ch, tMap.getOrDefault(ch, 0) + 1);
        }

        Map<Character, Integer> windowMap = new HashMap<>();
        int matchedChars = 0; 
        int minLength = Integer.MAX_VALUE;
        int startIndex = 0;
        int left = 0;

        for (int right = 0; right < s.length(); right++) {
            char charRight = s.charAt(right);
            windowMap.put(charRight, windowMap.getOrDefault(charRight, 0) + 1);

            if (tMap.containsKey(charRight) && windowMap.get(charRight).intValue() == tMap.get(charRight).intValue()) {
                matchedChars++;
            }
            while (matchedChars == tMap.size() && left <= right) {
                int currentWindowLength = right - left + 1;
                if (currentWindowLength < minLength) {
                    minLength = currentWindowLength;
                    startIndex = left;
                }

                char charLeft = s.charAt(left);
                windowMap.put(charLeft, windowMap.get(charLeft) - 1);

                if (tMap.containsKey(charLeft) && windowMap.get(charLeft).intValue() < tMap.get(charLeft).intValue()) {
                    matchedChars--;
                }
                left++;
            }
        }

        if (minLength == Integer.MAX_VALUE) {
            return "";
        } else {
            return s.substring(startIndex, startIndex + minLength);
        }
    }
}