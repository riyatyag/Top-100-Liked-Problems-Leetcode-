/**
 * Problem Statement:
 * Given the root of a binary search tree, and an integer k, return the kth smallest value (1-indexed) of all the values of the nodes in the tree.
 *
 * Approach:
 * The key property of a Binary Search Tree (BST) is that an in-order traversal visits nodes in ascending order of their values.
 * Therefore, to find the kth smallest element, we can perform an in-order traversal and keep a count of the nodes visited.
 * When the count reaches 'k', the current node's value is our answer.
 *
 * We can use a recursive approach for in-order traversal. We'll maintain two global variables:
 * 'count' to keep track of the number of nodes visited so far in ascending order, and
 * 'result' to store the kth smallest value once found.
 *
 * Time Complexity:
 * O(K) in the average case, where K is the target smallest element. In the worst case (if K is close to N, or the tree is skewed),
 * we might visit all N nodes. So, it's effectively O(N) in the worst case, where N is the number of nodes in the tree.
 *
 * Space Complexity:
 * O(H), where H is the height of the BST. This is due to the recursion stack. In the worst case (skewed tree), H can be N,
 * leading to O(N) space. In the best case (balanced tree), H is logN, so the space complexity is O(logN).
 */
class Solution {
    private int count = 0;
    private int result = -1;

    public int kthSmallest(TreeNode root, int k) {
        inorderTraversal(root, k);
        return result;
    }

    private void inorderTraversal(TreeNode node, int k) {
        if (node == null) {
            return;
        }
        inorderTraversal(node.left, k);
        count++;
        if (count == k) {
            result = node.val;
            return; 
        }
        inorderTraversal(node.right, k);
    }
}
