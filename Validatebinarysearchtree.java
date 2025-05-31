/**
 * Problem Statement:
 * Given the root of a binary tree, determine if it is a valid binary search tree (BST).
 * A valid BST is defined as follows:
 * - The left subtree of a node contains only nodes with keys less than the node's key.
 * - The right subtree of a node contains only nodes with keys greater than the node's key.
 * - Both the left and right subtrees must also be binary search trees.
 *
 * Approach:
 * To validate a BST, we cannot simply check if `node.left.val < node.val` and `node.right.val > node.val` for each node.
 * This is because the BST property must hold for all descendants, not just immediate children.
 * For example, a node in the left subtree of the root must be less than the root, even if it's several levels down.
 *
 * The optimal approach is to use a recursive Depth-First Search (DFS) with a valid range (min and max) for each node.
 *
 * Steps:
 * 1. Define a helper function `isValidBST(node, minVal, maxVal)`.
 * - `node`: The current node being checked.
 * - `minVal`: The minimum allowed value for `node.val` (exclusive). Initially, this can be negative infinity.
 * - `maxVal`: The maximum allowed value for `node.val` (exclusive). Initially, this can be positive infinity.
 *
 * 2. Base Case: If `node` is null, it's a valid BST (an empty tree is a valid BST), so return `true`.
 *
 * 3. Validation Step:
 * - Check if `node.val` violates the current `minVal` or `maxVal` constraints:
 * `if (node.val <= minVal || node.val >= maxVal)` return `false`.
 * Note: Using `long` for `minVal` and `maxVal` is important to handle `Node.val` range which can be `Integer.MIN_VALUE` or `Integer.MAX_VALUE`.
 *
 * 4. Recursive Calls:
 * - Recursively validate the left subtree: `isValidBST(node.left, minVal, node.val)`.
 * For the left child, its maximum allowed value becomes the current node's value. Its minimum remains the same.
 * - Recursively validate the right subtree: `isValidBST(node.right, node.val, maxVal)`.
 * For the right child, its minimum allowed value becomes the current node's value. Its maximum remains the same.
 *
 * 5. Return `true` only if both recursive calls return `true`.
 *
 * Time Complexity:
 * O(N), where N is the number of nodes in the binary tree. We visit each node exactly once.
 *
 * Space Complexity:
 * O(H), where H is the height of the binary tree. This is due to the recursion stack. In the worst case (skewed tree), H can be N,
 * leading to O(N) space. In the best case (balanced tree), H is log N, so the space complexity is O(log N).
 */
class Solution {
    public boolean isValidBST(TreeNode root) {
        return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }
     private boolean isValidBST(TreeNode node, long minVal, long maxVal) {
        if (node == null) {
            return true;
        }
        if (node.val <= minVal || node.val >= maxVal) {
            return false;
        }
        boolean leftValid = isValidBST(node.left, minVal, node.val);
        boolean rightValid = isValidBST(node.right, node.val, maxVal);

        return leftValid && rightValid;
    }
}