/**
 * Problem Statement:
 * Given the root of a binary tree, invert the tree, and return its root.
 *
 * Approach:
 * This problem can be solved recursively using a Depth-First Search (DFS) approach.
 * For each node, we need to swap its left and right children. This operation should be performed recursively
 * for all nodes in the tree, starting from the root.
 *
 * The steps are:
 * 1. Base Case: If the current node is null, there's nothing to invert, so return null.
 * 2. Recursive Step:
 * a. Recursively invert the left subtree: `invertedLeft = invertTree(node.left)`.
 * b. Recursively invert the right subtree: `invertedRight = invertTree(node.right)`.
 * c. Swap the left and right children of the current node:
 * `node.left = invertedRight;`
 * `node.right = invertedLeft;`
 * 3. Return the current node (which is now the root of the inverted subtree).
 *
 * Time Complexity:
 * O(N), where N is the number of nodes in the binary tree. We visit each node exactly once to perform the swap.
 *
 * Space Complexity:
 * O(H), where H is the height of the binary tree. This is due to the recursion stack. In the worst case (skewed tree), H can be N,
 * so the space complexity can be O(N). In the best case (balanced tree), H is logN, so the space complexity is O(logN).
 */
class Solution {
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode invertedLeft = invertTree(root.left);
        TreeNode invertedRight = invertTree(root.right);

        root.left = invertedRight;
        root.right = invertedLeft;

        return root;
    }
}