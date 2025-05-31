/**
 * Problem Statement:
 * Given the root of a binary tree, flatten the tree into a "linked list":
 * The "linked list" should use the same TreeNode class where the right child pointer points to the next node in the list and the left child pointer is always null.
 * The "linked list" should be in the same order as a pre-order traversal of the binary tree.
 *
 * Approach:
 * The goal is to transform the tree into a linked list in pre-order traversal order, using only the right pointers.
 * This can be done efficiently in-place using a reverse pre-order traversal (right -> left -> root) approach.
 * We maintain a 'prev' pointer that keeps track of the previously visited node in the flattened list.
 *
 * Steps:
 * 1. Initialize a global or class-level `prev` TreeNode to `null`. This `prev` pointer will hold the tail of the
 * flattened list constructed so far.
 * 2. Perform a recursive traversal starting from the right child, then the left child, and finally the current node (post-order style, but adjusted for the "pre-order linked list" requirement).
 * 3. In the traversal function:
 * a. Base Case: If the current node is null, return.
 * b. Recursively flatten the right subtree: `flatten(node.right)`.
 * c. Recursively flatten the left subtree: `flatten(node.left)`.
 * d. After both subtrees are flattened, the current node's right child should point to the `prev` node (which is the head of the already flattened part of the tree that should come after the current node in pre-order).
 * e. The current node's left child should be set to `null`.
 * f. Update `prev` to be the current node, as it's now the new tail of the flattened list.
 *
 * This approach effectively processes nodes in reverse pre-order (rightmost, then right child of parent, then parent, etc.),
 * allowing us to build the flattened list by setting `node.right = prev` and then updating `prev = node`.
 *
 * Time Complexity:
 * O(N), where N is the number of nodes in the binary tree. Each node is visited exactly once.
 *
 * Space Complexity:
 * O(H), where H is the height of the binary tree, due to the recursion stack. In the worst case (skewed tree), H can be N,
 * leading to O(N) space. In the best case (balanced tree), H is logN, so the space complexity is O(logN).
 * The 'Follow up' asking for O(1) extra space (excluding recursion stack) can be achieved using a Morris Traversal approach, but the recursive solution is generally simpler and passes typical constraints. The current solution is O(H) recursion stack space.
 */
class Solution {
    private TreeNode prev = null;
    public void flatten(TreeNode root) {
        if (root == null) {
            return;
        }
        flatten(root.right);
        flatten(root.left);

        root.right = prev;
        root.left = null;
        prev = root;
    }
}