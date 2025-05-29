// Problem Statement:
// You are given an m x n integer matrix matrix with the following two properties:
// Each row is sorted in non-decreasing order.
// The first integer of each row is greater than the last integer of the previous row.
// Given an integer target, return true if target is in matrix or false otherwise.
// You must write a solution in O(log(m * n)) time complexity.

// Approach:
// The problem can be solved by treating the 2D matrix as a 1D sorted array.
// Since each row is sorted and the first element of each row is greater than the last element of the previous row,
// the entire matrix, if flattened, would be a sorted array.
// We can apply binary search on this flattened 1D representation.
// Let 'm' be the number of rows and 'n' be the number of columns.
// The total number of elements in the matrix is m * n.
// We can map a 1D index `idx` to a 2D coordinate (row, col) using:
// row = idx / n
// col = idx % n
//
// We initialize `left` to 0 and `right` to `m * n - 1`.
// In each step of binary search:
// 1. Calculate `mid = left + (right - left) / 2`.
// 2. Convert `mid` to its corresponding 2D coordinates: `row = mid / n`, `col = mid % n`.
// 3. Compare `matrix[row][col]` with `target`:
//    - If `matrix[row][col] == target`, return `true`.
//    - If `matrix[row][col] < target`, the target must be in the right half, so set `left = mid + 1`.
//    - If `matrix[row][col] > target`, the target must be in the left half, so set `right = mid - 1`.
// If the loop finishes and the target is not found, return `false`.

// Time Complexity: O(log(m * n))
// The binary search algorithm reduces the search space by half in each iteration,
// leading to a logarithmic time complexity with respect to the total number of elements (m * n).

// Space Complexity: O(1)
// We are only using a few constant extra variables for pointers, so the space complexity is constant.

// Optimal Solution:
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }

        int m = matrix.length;
        int n = matrix[0].length;

        int left = 0;
        int right = m * n - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            int row = mid / n;
            int col = mid % n;

            if (matrix[row][col] == target) {
                return true;
            } else if (matrix[row][col] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return false;
    }
}