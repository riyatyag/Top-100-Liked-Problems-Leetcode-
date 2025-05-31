// Problem Statement:
// Given an m x n grid filled with non-negative numbers, find a path from top left to bottom right, which minimizes the sum of all numbers along its path.
// Note: You can only move either down or right at any point in time.

// Approach:
// This is a dynamic programming problem. We can use the input grid itself to store the minimum path sums to reach each cell.
// Let `grid[i][j]` represent the minimum path sum to reach cell `(i, j)`.

// 1. Base Case:
//    `grid[0][0]` is the starting point, so its minimum path sum is just its own value.

// 2. First Row and First Column:
//    - For cells in the first row (`i=0`, `j>0`), to reach `grid[0][j]`, you can only come from `grid[0][j-1]` (moving right). So, `grid[0][j] = grid[0][j] + grid[0][j-1]`.
//    - For cells in the first column (`j=0`, `i>0`), to reach `grid[i][0]`, you can only come from `grid[i-1][0]` (moving down). So, `grid[i][0] = grid[i][0] + grid[i-1][0]`.

// 3. Remaining Cells:
//    For any other cell `grid[i][j]` (`i>0` and `j>0`), you can reach it either from `grid[i-1][j]` (moving down) or from `grid[i][j-1]` (moving right).
//    To minimize the sum, we choose the path that has the minimum sum from the previous cells:
//    `grid[i][j] = grid[i][j] + min(grid[i-1][j], grid[i][j-1])`.

// 4. Result:
//    The minimum path sum to reach the bottom-right corner will be `grid[m-1][n-1]`.

// Time Complexity:
// O(m * n) where `m` is the number of rows and `n` is the number of columns. We iterate through each cell of the grid once.

// Space Complexity:
// O(1) if we modify the input grid in place.
// O(m * n) if we use a separate DP table. The problem statement implies in-place modification is acceptable.

// Optimal Solution:
class Solution {
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) {
                    continue;
                } else if (i == 0) {
                    grid[i][j] += grid[i][j - 1];
                } else if (j == 0) {
                    grid[i][j] += grid[i - 1][j];
                } else {
                    grid[i][j] += Math.min(grid[i - 1][j], grid[i][j - 1]);
                }
            }
        }
        return grid[m - 1][n - 1];
    }
}