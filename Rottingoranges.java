// Problem Statement:
// You are given an m x n grid where each cell can have one of three values:
// 0 representing an empty cell,
// 1 representing a fresh orange, or
// 2 representing a rotten orange.
// Every minute, any fresh orange that is 4-directionally adjacent to a rotten orange becomes rotten.
// Return the minimum number of minutes that must elapse until no cell has a fresh orange. If this is impossible, return -1.

// Approach:
// This problem can be solved using Breadth-First Search (BFS).
// We can treat this as a multi-source BFS problem where all initially rotten oranges are the starting points.
// 1. Initialize a queue and add all rotten oranges (value 2) to it.
// 2. Count the total number of fresh oranges (value 1).
// 3. If there are no fresh oranges initially, return 0.
// 4. Perform a BFS:
//    - In each step (minute), poll all oranges currently in the queue. These are the oranges that rotten in the previous minute.
//    - For each polled orange, check its 4-directionally adjacent cells.
//    - If an adjacent cell contains a fresh orange (value 1), mark it as rotten (value 2), decrement the fresh orange count, and add it to the queue for the next minute.
//    - Increment the minutes counter after processing all oranges for the current minute.
// 5. After the BFS completes, if the fresh orange count is 0, return the total minutes elapsed. Otherwise, if there are still fresh oranges left, it means they cannot be rotten, so return -1.

// Time Complexity:
// O(M*N) where M is the number of rows and N is the number of columns.
// Each cell is visited at most a constant number of times (when initially adding to the queue or when being rotten).

// Space Complexity:
// O(M*N) in the worst case, where all oranges are rotten and added to the queue.
// This is for the queue data structure.

// Optimal Solution:
class Solution {
    public int orangesRotting(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        Queue<int[]> queue = new LinkedList<>();
        int freshCount = 0;

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] == 2) {
                    queue.offer(new int[]{r, c});
                } else if (grid[r][c] == 1) {
                    freshCount++;
                }
            }
        }

        if (freshCount == 0) {
            return 0;
        }

        int minutes = 0;
        int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            boolean rottenThisMinute = false;

            for (int i = 0; i < levelSize; i++) {
                int[] current = queue.poll();
                int r = current[0];
                int c = current[1];

                for (int[] dir : directions) {
                    int nr = r + dir[0];
                    int nc = c + dir[1];

                    if (nr >= 0 && nr < rows && nc >= 0 && nc < cols && grid[nr][nc] == 1) {
                        grid[nr][nc] = 2; 
                        freshCount--;
                        queue.offer(new int[]{nr, nc});
                        rottenThisMinute = true;
                    }
                }
            }
            if (rottenThisMinute && !queue.isEmpty()) { 
                minutes++;
            }
        }

        return (freshCount == 0) ? minutes : -1;
    }
}