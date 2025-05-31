/**
 * Problem Statement:
 * Given the root of a binary tree, check whether it is a mirror of itself (i.e., symmetric around its center).
 *
 * Approach:
 * To check for symmetry, we need to compare the left and right subtrees of the root.
 * Specifically, the left subtree should be a mirror image of the right subtree.
 * This can be done using a recursive helper function that compares two nodes: one from the left side and one from the right side.
 *
 * The recursive helper function `isMirror(node1, node2)` will follow these rules:
 * 1. Base Case: If both `node1` and `node2` are null, they are symmetric, return true.
 * 2. Base Case: If only one of them is null (meaning the other is not), they are not symmetric, return false.
 * 3. Comparison: If both `node1` and `node2` are not null, check if their values are equal (`node1.val == node2.val`).
 * 4. Recursive Step: If their values are equal, then recursively check for symmetry:
 * - `node1.left` must be a mirror of `node2.right`.
 * - `node1.right` must be a mirror of `node2.left`.
 * Return true only if both these recursive calls return true.
 *
 * The main `isSymmetric` function will call `isMirror(root.left, root.right)`.
 *
 * Time Complexity:
 * O(N), where N is the number of nodes in the binary tree. We visit each node once during the mirror comparison.
 *
 * Space Complexity:
 * O(H), where H is the height of the binary tree. This is due to the recursion stack. In the worst case (skewed tree), H can be N,
 * so the space complexity can be O(N). In the best case (balanced tree), H is logN, so the space complexity is O(logN).
 */
class Solution {
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        return isMirror(root.left, root.right);
    }

    private boolean isMirror(TreeNode node1, TreeNode node2) {
        if (node1 == null && node2 == null) {
            return true;
        }
        if (node1 == null || node2 == null) {
            return false;
        }
        if (node1.val != node2.val) {
            return false;
        }

        return isMirror(node1.left, node2.right) && isMirror(node1.right, node2.left);
    }
}