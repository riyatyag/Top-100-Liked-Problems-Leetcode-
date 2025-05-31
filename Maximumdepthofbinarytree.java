/**
 * Problem Statement:
 * Given the root of a binary tree, return its maximum depth.
 * A binary tree's maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.
 *
 * Approach:
 * This problem can be solved efficiently using a recursive Depth-First Search (DFS) approach.
 * The maximum depth of a binary tree can be defined recursively as:
 * 1. If the tree is empty (root is null), its depth is 0.
 * 2. Otherwise, its depth is 1 (for the root itself) plus the maximum depth of its left or right subtree.
 *
 * The recursive function will calculate the depth of the subtree rooted at the current node.
 * It will return 0 for a null node. For a non-null node, it will recursively call itself for the left and right children,
 * take the maximum of the depths returned by these calls, and add 1 to it (to account for the current node).
 *
 * Time Complexity:
 * O(N), where N is the number of nodes in the binary tree. We visit each node exactly once.
 *
 * Space Complexity:
 * O(H), where H is the height of the binary tree. This is due to the recursion stack. In the worst case (skewed tree), H can be N,
 * so the space complexity can be O(N). In the best case (balanced tree), H is logN, so the space complexity is O(logN).
 */
class Solution {
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftDepth = maxDepth(root.left);
        int rightDepth = maxDepth(root.right);

        return 1 + Math.max(leftDepth, rightDepth);
    }
}