// Problem Statement:
// Implement the Trie class:
// Trie() Initializes the trie object.
// void insert(String word) Inserts the string word into the trie.
// boolean search(String word) Returns true if the string word is in the trie (i.e., was inserted before), and false otherwise.
// boolean startsWith(String prefix) Returns true if there is a previously inserted string word that has the prefix prefix, and false otherwise.

// Approach:
// A Trie (prefix tree) is a tree data structure used to efficiently store and retrieve keys in a dataset of strings.
// Each node in the Trie represents a character.
// We use an array of 26 TrieNode children for each node, representing 'a' through 'z'.
// A boolean flag `isEndOfWord` indicates if a node marks the end of a complete word.

// TrieNode Class:
// - children: An array of TrieNode of size 26, where `children[i]` points to the next character in the sequence.
// - isEndOfWord: A boolean flag, true if this node represents the end of a word, false otherwise.

// Trie Class:
// - root: The root node of the Trie. It's an empty node representing the start of any word.

// insert(String word) method:
// - Start from the root.
// - For each character in the word:
//   - Calculate the index (0-25) corresponding to the character.
//   - If the child at that index is null, create a new TrieNode.
//   - Move to the child node.
// - After iterating through all characters, set the `isEndOfWord` flag of the current node to true.

// search(String word) method:
// - Start from the root.
// - For each character in the word:
//   - Calculate the index.
//   - If the child at that index is null, the word does not exist in the Trie, return false.
//   - Move to the child node.
// - After iterating through all characters, return the `isEndOfWord` flag of the current node. This ensures we only return true if the *entire* word was inserted, not just a prefix.

// startsWith(String prefix) method:
// - Start from the root.
// - For each character in the prefix:
//   - Calculate the index.
//   - If the child at that index is null, no word with this prefix exists, return false.
//   - Move to the child node.
// - If we successfully traverse the entire prefix, it means there is at least one word starting with this prefix, so return true. The `isEndOfWord` flag does not matter here.

// Time Complexity:
// - Trie() constructor: O(1)
// - insert(String word): O(L), where L is the length of the word. In the worst case, we traverse and potentially create L nodes.
// - search(String word): O(L), where L is the length of the word. We traverse L nodes.
// - startsWith(String prefix): O(P), where P is the length of the prefix. We traverse P nodes.

// Space Complexity:
// - The space complexity is O(Total_characters_in_all_words), as each unique character in all inserted words might create a new node. In the worst case, if all words have no common prefixes, the space complexity can be O(N * L), where N is the number of words and L is the average length of a word.

class Trie {
    class TrieNode {
        TrieNode[] children;
        boolean isEndOfWord;

        public TrieNode() {
            children = new TrieNode[26];
            isEndOfWord = false;
        }
    }

    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    public void insert(String word) {
        TrieNode current = root;
        for (char c : word.toCharArray()) {
            int index = c - 'a';
            if (current.children[index] == null) {
                current.children[index] = new TrieNode();
            }
            current = current.children[index];
        }
        current.isEndOfWord = true;
    }

    public boolean search(String word) {
        TrieNode current = root;
        for (char c : word.toCharArray()) {
            int index = c - 'a';
            if (current.children[index] == null) {
                return false;
            }
            current = current.children[index];
        }
        return current.isEndOfWord;
    }

    public boolean startsWith(String prefix) {
        TrieNode current = root;
        for (char c : prefix.toCharArray()) {
            int index = c - 'a';
            if (current.children[index] == null) {
                return false;
            }
            current = current.children[index];
        }
        return true;
    }
}