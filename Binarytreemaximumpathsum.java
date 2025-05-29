// Problem Statement:
// A path in a binary tree is a sequence of nodes where each pair of adjacent nodes in the sequence has an edge connecting them.
// A node can only appear in the sequence at most once. Note that the path does not need to pass through the root.
// The path sum of a path is the sum of the node's values in the path.
// Given the root of a binary tree, return the maximum path sum of any non-empty path.

// Approach:
// This problem can be solved using a recursive Depth First Search (DFS) approach.
// We need a global variable to keep track of the maximum path sum found so far, initialized to a very small negative number
// since node values can be negative.
// The recursive function `maxPathSumHelper` will return the maximum path sum starting from the current node and going downwards (i.e., contributing to a path that extends upwards).
// Inside the helper function:
// 1. Base case: If the node is null, return 0 (as it contributes no value to the path).
// 2. Recursively calculate the maximum path sum from the left child (`leftSum`) and the right child (`rightSum`).
// 3. We only consider positive contributions from children to maximize the path sum extending upwards. So, if `leftSum` or `rightSum` is negative, we treat them as 0.
// 4. Calculate the 'current path sum' that could potentially be a global maximum. This path includes the current node's value and optionally extends through both its left and right children (node.val + leftSum + rightSum). Update the global `maxSum` with this value if it's greater.
// 5. Return the maximum sum that can be extended upwards from the current node. This will be the current node's value plus the maximum of its left or right child sums (since a path extending upwards can only go through one child branch).

// Time Complexity: O(N)
// We visit each node in the tree exactly once.

// Space Complexity: O(H)
// Where H is the height of the tree. This is due to the recursion stack. In the worst case (skewed tree), H can be N, leading to O(N) space. In the best case (balanced tree), H is log N, leading to O(log N) space.

// Optimal Solution:
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode() {}
 * TreeNode(int val) { this.val = val; }
 * TreeNode(int val, TreeNode left, TreeNode right) {
 * this.val = val;
 * this.left = left;
 * this.right = right;
 * }
 * }
 */
class Solution {
    int maxSum = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        maxPathSumHelper(root);
        return maxSum;
    }

    private int maxPathSumHelper(TreeNode node) {
        if (node == null) {
            return 0;
        }

        int leftSum = Math.max(0, maxPathSumHelper(node.left));
        int rightSum = Math.max(0, maxPathSumHelper(node.right));
        maxSum = Math.max(maxSum, node.val + leftSum + rightSum);
        return node.val + Math.max(leftSum, rightSum);
    }
}