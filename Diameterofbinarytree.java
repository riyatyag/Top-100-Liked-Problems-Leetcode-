/**
 * Problem Statement:
 * Given the root of a binary tree, return the length of the diameter of the tree.
 * The diameter of a binary tree is the length of the longest path between any two nodes in a tree.
 * This path may or may not pass through the root.
 * The length of a path between two nodes is represented by the number of edges between them.
 *
 * Approach:
 * The diameter of a binary tree can be found by performing a Depth First Search (DFS) traversal.
 * For each node, we want to calculate the maximum depth of its left and right subtrees.
 * The diameter passing through a particular node would be (depth of left subtree) + (depth of right subtree).
 * The overall diameter of the tree is the maximum of these values across all nodes.
 * We'll use a global variable to keep track of the maximum diameter found so far.
 * The DFS function will return the height of the current subtree.
 *
 * Time Complexity:
 * O(N), where N is the number of nodes in the binary tree.
 * We visit each node exactly once.
 *
 * Space Complexity:
 * O(H), where H is the height of the binary tree.
 * This is due to the recursion stack. In the worst case (skewed tree), H can be N,
 * so the space complexity can be O(N). In the best case (balanced tree), H is logN,
 * so the space complexity is O(logN).
 */
class Solution {
    int maxDiameter = 0;

    public int diameterOfBinaryTree(TreeNode root) {
        calculateDepth(root);
        return maxDiameter;
    }

    private int calculateDepth(TreeNode node) {
        if (node == null) {
            return 0;
        }

        int leftDepth = calculateDepth(node.left);
        int rightDepth = calculateDepth(node.right);
        maxDiameter = Math.max(maxDiameter, leftDepth + rightDepth);
        
        return 1 + Math.max(leftDepth, rightDepth);
    }
}