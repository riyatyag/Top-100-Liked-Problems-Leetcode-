// Problem Statement:
// There is a robot on an m x n grid. The robot is initially located at the top-left corner (i.e., grid[0][0]). The robot tries to move to the bottom-right corner (i.e., grid[m - 1][n - 1]). The robot can only move either down or right at any point in time.
// Given the two integers m and n, return the number of possible unique paths that the robot can take to reach the bottom-right corner.

// Approach:
// This is a classic dynamic programming problem. We can think of it as building up the number of ways to reach each cell.
// Let `dp[i][j]` be the number of unique paths to reach cell `(i, j)`.

// 1. Base Cases:
//    - `dp[0][0] = 1` (There's one way to start at the starting cell itself).
//    - For any cell in the first row (`dp[0][j]`), there's only one way to reach it: by moving right from `(0, 0)`. So, `dp[0][j] = 1`.
//    - For any cell in the first column (`dp[i][0]`), there's only one way to reach it: by moving down from `(0, 0)`. So, `dp[i][0] = 1`.

// 2. Recurrence Relation:
//    For any other cell `dp[i][j]` (where `i > 0` and `j > 0`), the robot can reach it either from the cell directly above it (`(i-1, j)`) by moving down, or from the cell directly to its left (`(i, j-1)`) by moving right.
//    Therefore, `dp[i][j] = dp[i-1][j] + dp[i][j-1]`.

// We can use a 2D array for DP, or optimize space to 1D array if needed (though 2D is often more intuitive for grids).
// Given constraints (m, n <= 100), a 2D array is fine.

// Time Complexity:
// O(m * n) because we fill an `m x n` DP table.

// Space Complexity:
// O(m * n) for the 2D DP array.

// Alternative (Combinatorial) Approach:
// This problem can also be solved using combinatorics. To reach the bottom-right corner from the top-left, the robot must make exactly `(m-1)` down moves and `(n-1)` right moves. The total number of moves will be `(m-1) + (n-1)`.
// The problem then becomes choosing `(m-1)` positions for the down moves (or `(n-1)` for the right moves) out of the total `(m-1) + (n-1)` moves.
// This is a combination problem: `C(m-1 + n-1, m-1)` or `C(m-1 + n-1, n-1)`.
// `C(N, K) = N! / (K! * (N-K)!)`
// This approach has O(min(m, n)) time complexity for calculating the combinations iteratively, which can be faster for very large m, n where DP table is too big. However, for m,n <= 100, both are efficient.

// Optimal Solution (DP):
class Solution {
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        for (int j = 0; j < n; j++) {
            dp[0][j] = 1;
        }
        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m - 1][n - 1];
    }
}