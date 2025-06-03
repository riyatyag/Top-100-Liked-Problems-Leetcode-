// Problem Statement:
// Given an m x n integer matrix matrix, if an element is 0, set its entire row and column to 0's.
// You must do it in place.

// Approach:
// The problem asks for an in-place solution with constant space complexity, beyond O(m+n) or O(mn).
// A clever approach is to use the first row and first column of the matrix itself to mark which rows and columns need to be zeroed out.

// Algorithm:
// 1. First Pass (Marking):
//    - Determine if the first row needs to be zeroed: Iterate through the first row (matrix[0][j]). If any element is 0, set `firstRowZero` to true.
//    - Determine if the first column needs to be zeroed: Iterate through the first column (matrix[i][0]). If any element is 0, set `firstColZero` to true.
//    - Iterate through the rest of the matrix (from `(1,1)` to `(m-1, n-1)`):
//      - If `matrix[i][j]` is 0, then mark its corresponding first row and first column elements as 0. That is, set `matrix[i][0] = 0` and `matrix[0][j] = 0`.
//        This uses the first row and column as auxiliary space.

// 2. Second Pass (Zeroing):
//    - Iterate through the matrix again, starting from `(1,1)` to `(m-1, n-1)`:
//      - If `matrix[i][0]` is 0 (meaning row `i` should be zeroed) OR `matrix[0][j]` is 0 (meaning column `j` should be zeroed), then set `matrix[i][j] = 0`.

// 3. Final Pass (Zeroing first row/column if needed):
//    - If `firstRowZero` is true, set all elements in the first row (`matrix[0][j]`) to 0.
//    - If `firstColZero` is true, set all elements in the first column (`matrix[i][0]`) to 0.

// Why this works:
// - We process the main matrix (excluding first row/col) first. When we encounter a 0 at `(i, j)`, we mark `(i, 0)` and `(0, j)` as 0. This way, the original zeros won't interfere with marking their own first row/col indicators later in the process.
// - The separate `firstRowZero` and `firstColZero` flags are crucial because if, for example, `matrix[0][0]` was originally 0, both the first row and first column would need to be zeroed. However, if we simply used `matrix[0][0]` as the indicator for both, and `matrix[0][0]` was marked 0 because some other element `matrix[i][0]` or `matrix[0][j]` was zero, we wouldn't know if `matrix[0][0]` was originally 0 or was marked due to another element. The flags handle this ambiguity.

// Time Complexity:
// O(m * n), where m is the number of rows and n is the number of columns.
// We iterate through the matrix a constant number of times (three passes). Each pass takes O(m*n) time.

// Space Complexity:
// O(1). We use a few boolean variables, which is constant space, and modify the matrix in place.

class Solution {
    public void setZeroes(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        boolean firstRowZero = false;
        boolean firstColZero = false;

        for (int j = 0; j < n; j++) {
            if (matrix[0][j] == 0) {
                firstRowZero = true;
                break;
            }
        }
        for (int i = 0; i < m; i++) {
            if (matrix[i][0] == 0) {
                firstColZero = true;
                break;
            }
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0; 
                    matrix[0][j] = 0; 
                }
            }
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }
        if (firstRowZero) {
            for (int j = 0; j < n; j++) {
                matrix[0][j] = 0;
            }
        }
        if (firstColZero) {
            for (int i = 0; i < m; i++) {
                matrix[i][0] = 0;
            }
        }
    }
}