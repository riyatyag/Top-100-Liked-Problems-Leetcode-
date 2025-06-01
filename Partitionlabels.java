// Problem Statement:
// You are given a string s. We want to partition the string into as many parts as possible so that each letter appears in at most one part.
// Return a list of integers representing the size of these parts.

// Approach:
// The problem asks us to partition a string into the maximum number of parts such that each letter appears in at most one part.
// This means if a character 'c' appears in a partition, all occurrences of 'c' in the original string `s` must be within that same partition.
// To maximize the number of partitions, we need to make each partition as small as possible while satisfying the condition.

// The key idea is to determine the furthest right occurrence of every character.
// 1. First, we need to know the last index of each character in the string `s`. We can use an array (since there are only 26 lowercase English letters) to store this information. `int[] lastIndex = new int[26];`
//    Iterate through the string once to populate this `lastIndex` array. `lastIndex[char - 'a'] = i;`
// 2. Now, we iterate through the string again to find the partitions. We maintain two pointers: `start` and `end`.
//    `start` marks the beginning of the current partition. Initially, `start = 0`.
//    `end` tracks the furthest reach of the current partition. For any character `c` within the current partition (from `start` to current index `i`), its last occurrence `lastIndex[c - 'a']` must be within or before `end`. We continuously update `end` to be the maximum of `end` and `lastIndex` of the characters encountered.
// 3. Iterate with index `i` from `0` to `s.length() - 1`:
//    a. Update `end = Math.max(end, lastIndex[s.charAt(i) - 'a']);` This ensures `end` always points to the rightmost occurrence of any character encountered so far in the current potential partition.
//    b. If `i` reaches `end`, it means all characters encountered from `start` up to `i` have their last occurrences at or before `i`. This signifies the end of a valid partition.
//    c. When a partition ends (i.e., `i == end`):
//       i. Calculate the length of the current partition: `end - start + 1`.
//       ii. Add this length to our result list.
//       iii. Update `start = i + 1` to mark the beginning of the next potential partition.
// 4. Return the list of partition lengths.

// Example Walkthrough: s = "ababcbacadefegdehijhklij"
// 1. lastIndex array:
//    a: 8, b: 5, c: 7, d: 14, e: 15, f: 11, g: 13, h: 19, i: 22, j: 23, k: 20, l: 21
// 2. Iterate:
//    i = 0, s[0] = 'a': end = max(0, lastIndex['a'] = 8) = 8.
//    i = 1, s[1] = 'b': end = max(8, lastIndex['b'] = 5) = 8.
//    i = 2, s[2] = 'a': end = max(8, lastIndex['a'] = 8) = 8.
//    i = 3, s[3] = 'b': end = max(8, lastIndex['b'] = 5) = 8.
//    i = 4, s[4] = 'c': end = max(8, lastIndex['c'] = 7) = 8.
//    i = 5, s[5] = 'b': end = max(8, lastIndex['b'] = 5) = 8.
//    i = 6, s[6] = 'a': end = max(8, lastIndex['a'] = 8) = 8.
//    i = 7, s[7] = 'c': end = max(8, lastIndex['c'] = 7) = 8.
//    i = 8, s[8] = 'a': end = max(8, lastIndex['a'] = 8) = 8.
//    At i = 8, i == end. Found partition: length (8 - 0 + 1) = 9. partitions = [9]. start = 9.
//
//    i = 9, s[9] = 'd': end = max(9, lastIndex['d'] = 14) = 14.
//    i = 10, s[10] = 'e': end = max(14, lastIndex['e'] = 15) = 15.
//    i = 11, s[11] = 'f': end = max(15, lastIndex['f'] = 11) = 15.
//    i = 12, s[12] = 'e': end = max(15, lastIndex['e'] = 15) = 15.
//    i = 13, s[13] = 'g': end = max(15, lastIndex['g'] = 13) = 15.
//    i = 14, s[14] = 'd': end = max(15, lastIndex['d'] = 14) = 15.
//    i = 15, s[15] = 'e': end = max(15, lastIndex['e'] = 15) = 15.
//    At i = 15, i == end. Found partition: length (15 - 9 + 1) = 7. partitions = [9, 7]. start = 16.
//
//    ... and so on.

// Time Complexity:
// O(N), where N is the length of the string `s`.
// - First pass to populate `lastIndex` array: O(N).
// - Second pass to find partitions: O(N).
// - HashMap/Array operations are O(1) for character lookups.
// Total time complexity is dominated by the two linear passes.

// Space Complexity:
// O(1), as the `lastIndex` array is of fixed size (26 for lowercase English letters).
// The `partitions` list will store at most N elements in the worst case (e.g., "abcdef").

class Solution {
    public List<Integer> partitionLabels(String s) {
        List<Integer> partitions = new ArrayList<>();
        
        int[] lastIndex = new int[26];
        for (int i = 0; i < s.length(); i++) {
            lastIndex[s.charAt(i) - 'a'] = i;
        }

        int start = 0; 
        int end = 0;  

        for (int i = 0; i < s.length(); i++) {
            end = Math.max(end, lastIndex[s.charAt(i) - 'a']);

            if (i == end) {
                partitions.add(end - start + 1);
=                start = i + 1;
            }
        }
          return partitions;
    }
}