// Problem Statement:
// Given an m x n grid of characters board and a string word, return true if word exists in the grid.
// The word can be constructed from letters of sequentially adjacent cells, where adjacent cells are horizontally or vertically neighboring. The same letter cell may not be used more than once.
//
// Example 1:
// Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
// Output: true
//
// Example 2:
// Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "SEE"
// Output: true
//
// Example 3:
// Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCB"
// Output: false
//
// Constraints:
// m == board.length
// n = board[i].length
// 1 <= m, n <= 6
// 1 <= word.length <= 15
// board and word consists of only lowercase and uppercase English letters.
//
// Follow up: Could you use search pruning to make your solution faster with a larger board?

// Approach:
// This problem can be solved using Depth-First Search (DFS) with backtracking.
// We iterate through each cell in the board. If a cell's character matches the first character of the word,
// we start a DFS from that cell.
// In the DFS function, we check if the current character matches the character at the current index of the word.
// If it matches, we mark the current cell as visited (e.g., by changing its character to a temporary value)
// to prevent using the same cell multiple times.
// Then, we recursively call DFS for all four adjacent cells (up, down, left, right) with the next character index in the word.
// If any of these recursive calls returns true, it means we found the word, so we return true.
// If none of the recursive calls finds the word, we backtrack by restoring the character of the current cell
// to its original value and return false.
// The base cases for the DFS are:
// 1. If the current word index equals the length of the word, it means we have found all characters of the word, so return true.
// 2. If the current cell is out of bounds or its character does not match the current character of the word, return false.

// Time Complexity:
// The time complexity is approximately O(M * N * 3^L), where M is the number of rows, N is the number of columns,
// and L is the length of the word.
// In the worst case, we might start a DFS from each of the M*N cells.
// For each DFS path, at each step, we have at most 3 choices (since we cannot go back to the cell we came from).
// The depth of the recursion is L (length of the word).
// So, it's M * N (starting points) * 4 (initial directions, effectively reducing to 3 for subsequent steps) * 3^(L-1).
// This simplifies to O(M * N * 3^L).

// Space Complexity:
// The space complexity is O(L) for the recursion stack, where L is the length of the word.
// This is because the maximum depth of the DFS recursion is the length of the word.
// No extra data structures proportional to M*N are used besides the input board itself (which is modified and restored).

class Solution {
    public boolean exist(char[][] board, String word) {
        int m = board.length;
        int n = board[0].length;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == word.charAt(0)) {
                    if (dfs(board, i, j, word, 0)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean dfs(char[][] board, int r, int c, String word, int index) {
        if (index == word.length()) {
            return true;
        }

        if (r < 0 || r >= board.length || c < 0 || c >= board[0].length || board[r][c] != word.charAt(index)) {
            return false;
        }

        char originalChar = board[r][c];
        board[r][c] = '#'; 
        boolean found = dfs(board, r + 1, c, word, index + 1) ||
                        dfs(board, r - 1, c, word, index + 1) ||
                        dfs(board, r, c + 1, word, index + 1) ||
                        dfs(board, r, c - 1, word, index + 1);

        board[r][c] = originalChar; 
        return found;
    }
}