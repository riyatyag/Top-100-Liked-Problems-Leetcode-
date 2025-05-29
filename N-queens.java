// Problem Statement:
// The n-queens puzzle is the problem of placing n queens on an n x n chessboard such that no two queens attack each other.
// Given an integer n, return all distinct solutions to the n-queens puzzle. You may return the answer in any order.
// Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' both indicate a queen and an empty space, respectively.
//
// Example 1:
// Input: n = 4
// Output: [[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]
// Explanation: There exist two distinct solutions to the 4-queens puzzle as shown above
//
// Example 2:
// Input: n = 1
// Output: [["Q"]]
//
// Constraints:
// 1 <= n <= 9

// Approach:
// This is a classic backtracking problem. We want to place `n` queens on an `n x n` chessboard such that no two queens attack each other.
// Two queens attack each other if they are in the same row, same column, or same diagonal.
//
// We can use a recursive backtracking approach. We try to place one queen per row, starting from the first row.
//
// The `solveNQueens` function initializes the result list and an empty `n x n` board filled with '.'.
// It then calls a `backtrack` helper function.
//
// The `backtrack` function takes the current row `r`, the board, and the result list.
// Base Case: If `r` equals `n`, it means we have successfully placed queens in all `n` rows.
// In this case, we convert the `char[][]` board to a `List<String>` and add it to the `result`.
//
// Recursive Step: For the current row `r`, iterate through each column `c` from 0 to `n-1`.
// 1. Check if placing a queen at `(r, c)` is valid using an `isValid` helper function.
//    The `isValid` function checks if there are any queens in the same column, upper-left diagonal, or upper-right diagonal.
//    (We only need to check previous rows because we place queens row by row).
// 2. If it's valid:
//    a. Place a queen at `board[r][c]` (set it to 'Q').
//    b. Recursively call `backtrack(r + 1, board, result)` to place a queen in the next row.
//    c. Backtrack: Remove the queen from `board[r][c]` (set it back to '.') to explore other possibilities.
//
// The `isValid` function checks three conditions:
// - Same column: Iterate `i` from 0 to `r-1` and check `board[i][c]`.
// - Upper-left diagonal: Iterate `i` from `r-1` down to 0, and `j` from `c-1` down to 0. Check `board[i][j]`.
// - Upper-right diagonal: Iterate `i` from `r-1` down to 0, and `j` from `c+1` up to `n-1`. Check `board[i][j]`.
//
// To optimize `isValid`, instead of iterating, we can use boolean arrays or hash sets to keep track of occupied columns,
// occupied main diagonals (row + col), and occupied anti-diagonals (row - col).
// For diagonals, `row + col` is constant for main diagonals, and `row - col` is constant for anti-diagonals.
// The range for `row + col` is `0` to `2 * n - 2`.
// The range for `row - col` is `-(n - 1)` to `n - 1`. We can offset `row - col` by `n - 1` to get a positive index from `0` to `2 * n - 2`.

// Optimal Approach (with boolean arrays for O(1) validity check):
// Instead of a full `isValid` check which iterates through rows, we can maintain three boolean arrays:
// - `cols[n]`: `cols[j]` is true if column `j` has a queen.
// - `diag1[2*n - 1]`: `diag1[r + c]` is true if the main diagonal (sum of row and col) has a queen.
// - `diag2[2*n - 1]`: `diag2[r - c + n - 1]` is true if the anti-diagonal (difference of row and col) has a queen.
//
// When placing a queen at `(r, c)`:
// `cols[c] = true`, `diag1[r + c] = true`, `diag2[r - c + n - 1] = true`.
// When backtracking:
// `cols[c] = false`, `diag1[r + c] = false`, `diag2[r - c + n - 1] = false`.

// Time Complexity:
// The complexity is exponential, roughly O(N!) or slightly more constrained.
// For each row, we iterate through N columns. The validity check for each placement is O(1) with optimized boolean arrays.
// There are N levels of recursion. In the worst case, we might explore N branches at the first level, N-1 at the second, and so on.
// The upper bound is close to N!. The actual number of states visited is limited by valid placements.

// Space Complexity:
// O(N^2) for the `board` (char array).
// O(N) for the recursion stack depth.
// O(N) for the three boolean arrays (`cols`, `diag1`, `diag2`).
// O(N * N!) in the worst case for storing all solutions in the `result` list. Each solution is `N` strings of length `N`.
// So, the total space complexity is dominated by the output, which is O(N * N!).

class Solution {
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> result = new ArrayList<>();
        char[][] board = new char[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(board[i], '.');
        }

        boolean[] cols = new boolean[n];
        boolean[] diag1 = new boolean[2 * n - 1];
        boolean[] diag2 = new boolean[2 * n - 1]; 

        backtrack(0, n, board, cols, diag1, diag2, result);
        return result;
    }

    private void backtrack(int r, int n, char[][] board, boolean[] cols, boolean[] diag1, boolean[] diag2, List<List<String>> result) {
        if (r == n) {
            result.add(createBoardList(board));
            return;
        }

        for (int c = 0; c < n; c++) {
            int d1 = r + c;
            int d2 = r - c + n - 1;

            if (!cols[c] && !diag1[d1] && !diag2[d2]) {
                board[r][c] = 'Q';
                cols[c] = true;
                diag1[d1] = true;
                diag2[d2] = true;

                backtrack(r + 1, n, board, cols, diag1, diag2, result);

                board[r][c] = '.';
                cols[c] = false;
                diag1[d1] = false;
                diag2[d2] = false;
            }
        }
    }

    private List<String> createBoardList(char[][] board) {
        List<String> boardList = new ArrayList<>();
        for (char[] row : board) {
            boardList.add(new String(row));
        }
        return boardList;
    }
}