/**
 * Problem Statement:
 * Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.
 * According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes p and q as the lowest node in T that has both p and q as descendants (where we allow a node to be a descendant of itself).”
 *
 * Approach:
 * The problem can be solved using a recursive Depth-First Search (DFS) approach.
 * We traverse the tree from the root. For each node, we check three conditions:
 * 1. If the current node is null, we return null.
 * 2. If the current node is either 'p' or 'q', then this node is the LCA (because a node can be a descendant of itself).
 * 3. Otherwise, we recursively call the function on the left and right children.
 *
 * Based on the results from the left and right subtrees:
 * - If both left and right recursive calls return a non-null node, it means 'p' is found in one subtree and 'q' in the other.
 * In this case, the current node is the LCA.
 * - If only the left recursive call returns a non-null node, it means both 'p' and 'q' (or one of them, and the other is the current node) are in the left subtree. So, the LCA is in the left subtree.
 * - If only the right recursive call returns a non-null node, it means both 'p' and 'q' (or one of them, and the other is the current node) are in the right subtree. So, the LCA is in the right subtree.
 * - If both recursive calls return null, neither 'p' nor 'q' is found in the subtree rooted at the current node.
 *
 * Time Complexity:
 * O(N), where N is the number of nodes in the binary tree. In the worst case, we might visit all nodes.
 *
 * Space Complexity:
 * O(H), where H is the height of the binary tree. This is due to the recursion stack. In the worst case (skewed tree), H can be N,
 * so the space complexity can be O(N). In the best case (balanced tree), H is logN, so the space complexity is O(logN).
 */
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }
        if (root == p || root == q) {
            return root;
        }
        TreeNode leftLCA = lowestCommonAncestor(root.left, p, q);
        TreeNode rightLCA = lowestCommonAncestor(root.right, p, q);

        if (leftLCA != null && rightLCA != null) {
            return root;
        }
        else if (leftLCA != null) {
            return leftLCA;
        }
        else {
            return rightLCA;
        }
    }
}