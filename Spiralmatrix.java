// Problem Statement:
// Given an m x n matrix, return all elements of the matrix in spiral order.

// Approach:
// The problem asks us to traverse a 2D matrix in a spiral pattern and collect all elements.
// We can achieve this by maintaining four boundaries: `top`, `bottom`, `left`, and `right`.
// We process the matrix layer by layer, moving inwards.

// Algorithm:
// 1. Initialize:
//    - `result`: An `ArrayList<Integer>` to store the elements in spiral order.
//    - Handle edge case: If the `matrix` is null or empty, return an empty `result` list.
//    - `m`: Number of rows in the matrix.
//    - `n`: Number of columns in the matrix.
//    - `top`: Row index of the top boundary, initialized to 0.
//    - `bottom`: Row index of the bottom boundary, initialized to `m - 1`.
//    - `left`: Column index of the left boundary, initialized to 0.
//    - `right`: Column index of the right boundary, initialized to `n - 1`.

// 2. Spiral Traversal Loop:
//    - Continue the loop as long as `top <= bottom` and `left <= right`. This condition ensures that there are still elements to process.

//    - Traverse Right (Top row):
//      - Iterate `col` from `left` to `right`.
//      - Add `matrix[top][col]` to `result`.
//      - Increment `top` (the top row has been processed, so shrink the top boundary).

//    - Traverse Down (Rightmost column):
//      - Check if `top <= bottom`. This is important because after the "traverse right" step, `top` might have crossed `bottom` if it was a single-row matrix.
//      - Iterate `row` from `top` to `bottom`.
//      - Add `matrix[row][right]` to `result`.
//      - Decrement `right` (the rightmost column has been processed).

//    - Traverse Left (Bottom row):
//      - Check if `top <= bottom` and `left <= right`. This double check is crucial. After traversing down, `right` might have crossed `left`, or if it was a single-column matrix, `top` might have crossed `bottom`.
//      - Iterate `col` from `right` down to `left`.
//      - Add `matrix[bottom][col]` to `result`.
//      - Decrement `bottom` (the bottom row has been processed).

//    - Traverse Up (Leftmost column):
//      - Check if `top <= bottom` and `left <= right`. Again, essential boundary checks.
//      - Iterate `row` from `bottom` down to `top`.
//      - Add `matrix[row][left]` to `result`.
//      - Increment `left` (the leftmost column has been processed).

// 3. Return `result`.

// Time Complexity:
// O(m * n), where m is the number of rows and n is the number of columns.
// Each element in the matrix is visited exactly once.

// Space Complexity:
// O(1) for the auxiliary space (excluding the `result` list which is required to store the output). The `result` list will store `m * n` elements, so if counting output space, it's O(m*n).

import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<>();

        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return result;
        }

        int m = matrix.length;
        int n = matrix[0].length;

        int top = 0;
        int bottom = m - 1;
        int left = 0;
        int right = n - 1;

        while (top <= bottom && left <= right) {
            for (int col = left; col <= right; col++) {
                result.add(matrix[top][col]);
            }
            top++;
            if (top <= bottom) { 
                for (int row = top; row <= bottom; row++) {
                    result.add(matrix[row][right]);
                }
                right--;
            }
          if (top <= bottom && left <= right) { 
                for (int col = right; col >= left; col--) {
                    result.add(matrix[bottom][col]);
                }
                bottom--;
            }
            if (top <= bottom && left <= right) { 
                for (int row = bottom; row >= top; row--) {
                    result.add(matrix[row][left]);
                }
                left++;
            }
        }
         return result;
    }
}