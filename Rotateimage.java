// Problem Statement:
// You are given an n x n 2D matrix representing an image, rotate the image by 90 degrees (clockwise).
// You have to rotate the image in-place, which means you have to modify the input 2D matrix directly. DO NOT allocate another 2D matrix and do the rotation.

// Approach:
// To rotate a square matrix 90 degrees clockwise in-place, we can follow a two-step process:
// 1. Transpose the matrix.
// 2. Reverse each row of the transposed matrix.

// Let's analyze why this works:
// Original matrix element at (row, col) moves to:
// - After transpose: (col, row)
// - After reversing each row (element at (col, row) becomes element at (col, n - 1 - row)): (col, n - 1 - row)

// Let's check the desired final position for a 90-degree clockwise rotation:
// An element at (row, col) in the original matrix moves to (col, n - 1 - row).
// For example, (0,0) -> (0, n-1)
// (0,1) -> (1, n-1)
// (0,n-1) -> (n-1, n-1)

// This matches the transformation described by transpose followed by reversing each row.

// Algorithm:

// Step 1: Transpose the matrix
// - Iterate through the matrix. For each element `matrix[i][j]`, swap it with `matrix[j][i]`.
// - To avoid double-swapping elements, iterate `i` from 0 to `n-1` and `j` from `i+1` to `n-1`. This ensures each pair is swapped only once.
//   For a symmetric swap, we only need to iterate over the upper (or lower) triangle.
//   `temp = matrix[i][j];`
//   `matrix[i][j] = matrix[j][i];`
//   `matrix[j][i] = temp;`

// Step 2: Reverse each row
// - Iterate through each row of the matrix (`i` from 0 to `n-1`).
// - For each row, reverse its elements. This can be done by using two pointers, `left` and `right`, starting at the beginning and end of the row, respectively, and swapping elements as they move towards the center.
//   For each row `i`:
//     `left = 0;`
//     `right = n - 1;`
//     `while (left < right):`
//       `temp = matrix[i][left];`
//       `matrix[i][left] = matrix[i][right];`
//       `matrix[i][right] = temp;`
//       `left++;`
//       `right--;`

// Time Complexity:
// O(N^2), where N is the dimension of the square matrix.
// - Transposing involves iterating through approximately half of the matrix: O(N^2).
// - Reversing each row involves iterating through half of each row, total N rows: O(N * N/2) = O(N^2).
// Overall, the time complexity is dominated by iterating through all elements, so O(N^2).

// Space Complexity:
// O(1), as the rotation is done in-place without using any additional data structures proportional to the input size. Only a few temporary variables are used.

class Solution {
    public void rotate(int[][] matrix) {
        int n = matrix.length;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) { 
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
        for (int i = 0; i < n; i++) {
            int left = 0;
            int right = n - 1;
            while (left < right) {
                int temp = matrix[i][left];
                matrix[i][left] = matrix[i][right];
                matrix[i][right] = temp;
                left++;
                right--;
            }
        }
    }
}