// Problem Statement:
// Write an efficient algorithm that searches for a value target in an m x n integer matrix matrix. This matrix has the following properties:
// - Integers in each row are sorted in ascending from left to right.
// - Integers in each column are sorted in ascending from top to bottom.

// Approach:
// The key properties of the matrix (sorted rows and sorted columns) allow for an optimized search starting from a specific corner.
// If we start from the top-right corner (or bottom-left corner), we can eliminate a row or a column in each step.

// Let's choose the top-right corner (matrix[0][n-1]) as our starting point.
// - If the `target` is equal to the `current` element: We found the target, return true.
// - If the `target` is less than the `current` element: Since the current column is sorted in ascending order from top to bottom, and all elements to the left in the current row are smaller, the target cannot be in the current column below the current element, nor can it be to the right of the current element in this row. Therefore, we must move left to a smaller column. Decrement `col`.
// - If the `target` is greater than the `current` element: Since the current row is sorted in ascending order from left to right, and all elements above in the current column are smaller, the target cannot be in the current row to the left of the current element, nor can it be above the current element in this column. Therefore, we must move down to a larger row. Increment `row`.

// This process continues until either the target is found, or we move out of bounds of the matrix (either `row` becomes `m` or `col` becomes -1).

// Time Complexity:
// O(m + n), where m is the number of rows and n is the number of columns.
// In the worst case, the search path will traverse from one corner to the opposite side, covering at most `m` rows and `n` columns. Each step reduces either the row index or the column index, effectively eliminating a row or a column from the search space.

// Space Complexity:
// O(1), as we only use a few variables for pointers and don't use any auxiliary data structures.

class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }

        int m = matrix.length;    
        int n = matrix[0].length; 

        int row = 0;
        int col = n - 1;

        while (row < m && col >= 0) {
            int current = matrix[row][col];
            if (current == target) {
                return true;
            } else if (current > target) {
                col--;
            } else {
                row++;
            }
        }
        return false;
    }
}