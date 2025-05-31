// Problem Statement:
// Given an m x n 2D binary grid grid which represents a map of '1's (land) and '0's (water), return the number of islands.
// An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.

// Approach:
// This problem can be solved using Depth-First Search (DFS) or Breadth-First Search (BFS).
// The idea is to iterate through each cell of the grid. If we find a '1' (land), it means we have found a part of a new island.
// We then increment our island count and proceed to "sink" (mark as visited or '0') all connected land cells of this island using either DFS or BFS.
// This ensures that we don't count parts of the same island multiple times.

// DFS Approach:
// 1. Initialize `numIslands` to 0.
// 2. Iterate through each cell (r, c) of the grid.
// 3. If `grid[r][c]` is '1':
//    a. Increment `numIslands`.
//    b. Call a DFS helper function `dfs(grid, r, c)` to mark all connected land cells as '0'.
// 4. The `dfs` function will:
//    a. Check for boundary conditions and if the cell is '0'. If so, return.
//    b. Mark `grid[r][c]` as '0'.
//    c. Recursively call `dfs` for all 4-directionally adjacent cells (up, down, left, right).

// BFS Approach:
// 1. Initialize `numIslands` to 0.
// 2. Iterate through each cell (r, c) of the grid.
// 3. If `grid[r][c]` is '1':
//    a. Increment `numIslands`.
//    b. Create a queue and add the current cell (r, c) to it.
//    c. Mark `grid[r][c]` as '0'.
//    d. While the queue is not empty:
//       i. Dequeue a cell (currR, currC).
//       ii. For each of its 4-directionally adjacent cells (nextR, nextC):
//           - Check for boundary conditions.
//           - If `grid[nextR][nextC]` is '1':
//             - Mark `grid[nextR][nextC]` as '0'.
//             - Enqueue (nextR, nextC).

// Time Complexity:
// O(M*N) where M is the number of rows and N is the number of columns.
// Each cell is visited at most a constant number of times (once when iterating and once when performing DFS/BFS to mark it).

// Space Complexity:
// O(M*N) in the worst case.
// For DFS, this is the maximum depth of the recursion stack (e.g., a grid full of land).
// For BFS, this is the maximum size of the queue (e.g., a grid full of land).

// Optimal Solution (using DFS):
class Solution {
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }

        int numIslands = 0;
        int rows = grid.length;
        int cols = grid[0].length;

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] == '1') {
                    numIslands++;
                    dfs(grid, r, c);
                }
            }
        }
        return numIslands;
    }

    private void dfs(char[][] grid, int r, int c) {
        int rows = grid.length;
        int cols = grid[0].length;

        if (r < 0 || r >= rows || c < 0 || c >= cols || grid[r][c] == '0') {
            return;
        }

        grid[r][c] = '0'; 

        dfs(grid, r + 1, c);
        dfs(grid, r - 1, c); 
        dfs(grid, r, c + 1); 
        dfs(grid, r, c - 1);
    }
}