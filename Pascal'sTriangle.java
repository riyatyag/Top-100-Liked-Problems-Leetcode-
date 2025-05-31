// Problem Statement:
// Given an integer numRows, return the first numRows of Pascal's triangle.
// In Pascal's triangle, each number is the sum of the two numbers directly above it.

// Approach:
// Pascal's triangle can be constructed row by row.
// Each row starts and ends with 1.
// Any other element in a row is the sum of the two elements directly above it in the previous row.

// Algorithm:
// 1. Initialize an empty list of lists called `triangle` to store the Pascal's triangle.
// 2. Iterate from `i = 0` to `numRows - 1` to generate each row.
// 3. For each row `i`:
//    a. Create a new list `row` for the current row.
//    b. The first element of every row is 1. Add `1` to `row`.
//    c. If `i > 0` (i.e., not the first row), iterate `j` from 1 to `i-1`:
//       The current element `row[j]` is the sum of the elements at `triangle.get(i-1).get(j-1)` and `triangle.get(i-1).get(j)`. Add this sum to `row`.
//    d. If `i > 0` (i.e., not the first row), the last element of every row is also 1. Add `1` to `row`.
//       (Note: for `i=0`, only step 3b runs, adding the single '1'. For `i>0`, `j=i` is handled by the last '1' added).
//    e. Add the completed `row` to `triangle`.
// 4. Return `triangle`.

// Time Complexity:
// O(numRows^2). This is because we have nested loops. The outer loop runs `numRows` times, and the inner loop for each row `i` runs `i` times. Sum of 1 to numRows is approximately numRows^2 / 2.

// Space Complexity:
// O(numRows^2). This is because we store the entire triangle. The total number of elements in Pascal's triangle up to `numRows` is proportional to `numRows^2`.

// Optimal Solution:
import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> triangle = new ArrayList<>();

        if (numRows == 0) {
            return triangle;
        }
        List<Integer> firstRow = new ArrayList<>();
        firstRow.add(1);
        triangle.add(firstRow);

        for (int i = 1; i < numRows; i++) {
            List<Integer> prevRow = triangle.get(i - 1);
            List<Integer> currentRow = new ArrayList<>();

            currentRow.add(1);

            for (int j = 1; j < i; j++) {
                currentRow.add(prevRow.get(j - 1) + prevRow.get(j));
            }
            currentRow.add(1);
            
            triangle.add(currentRow);
        }

        return triangle;
    }
}