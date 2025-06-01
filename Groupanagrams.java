// Problem Statement:
// Given an array of strings strs, group the anagrams together. You can return the answer in any order.
// An anagram is a word or phrase formed by rearranging the letters of a different word or phrase, typically using all the original letters exactly once.

// Approach:
// The core idea to group anagrams is to find a canonical representation (a "key") for each string such that all anagrams share the same key.
// A common and effective key is the sorted version of the string. For example, "eat", "tea", and "ate" all sort to "aet".

// Algorithm Steps:
// 1. Create a HashMap where the keys are the sorted strings (canonical form) and the values are lists of strings that are anagrams of each other.
//    `Map<String, List<String>> map = new HashMap<>();`
// 2. Iterate through each string `s` in the input array `strs`.
// 3. For each string `s`:
//    a. Convert the string `s` into a character array: `char[] chars = s.toCharArray();`
//    b. Sort the character array: `Arrays.sort(chars);`
//    c. Convert the sorted character array back into a string. This sorted string will be our key: `String sortedStr = new String(chars);`
//    d. Check if `sortedStr` already exists as a key in the `map`.
//       i. If it does not exist, create a new empty list and associate it with this `sortedStr` key: `map.put(sortedStr, new ArrayList<>());`
//       ii. Retrieve the list associated with `sortedStr`: `map.get(sortedStr)`.
//       iii. Add the original string `s` to this list.
// 4. After iterating through all strings, the `map` will contain all anagrams grouped by their sorted key.
// 5. Finally, collect all the values (which are `List<String>`) from the `map` into a `List<List<String>>` and return it.

// Time Complexity:
// O(N * K log K), where N is the number of strings in `strs` and K is the maximum length of a string.
// - We iterate through N strings.
// - For each string of length K, we convert it to a character array (O(K)), sort it (O(K log K)), and convert it back to a string (O(K)).
// - HashMap operations (put, get) take O(K) time in the worst case (due to string hashing/comparison which depends on string length K), but on average they are considered O(1) for constant length strings. Here, the string length K is variable.
// - So, the dominant factor is sorting each string. N strings * K log K per string = O(N * K log K).

// Space Complexity:
// O(N * K), where N is the number of strings and K is the average length of a string.
// - In the worst case, all strings are unique anagram keys, and we store all N strings in the HashMap's values. Each string takes O(K) space.
// - The keys (sorted strings) also take O(K) space per entry.

class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();

        for (String s : strs) {
            char[] chars = s.toCharArray();
            Arrays.sort(chars);
            String sortedStr = new String(chars);

            if (!map.containsKey(sortedStr)) {
                map.put(sortedStr, new ArrayList<>());
            }
            map.get(sortedStr).add(s);
        }
        return new ArrayList<>(map.values());
    }
}