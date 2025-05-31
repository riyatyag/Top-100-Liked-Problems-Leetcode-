/**
 * Problem Statement:
 * Given the root of a binary tree, return the inorder traversal of its nodes' values.
 *
 * Approach:
 * Inorder traversal for a binary tree means visiting the left subtree, then the current node, and then the right subtree.
 * This problem can be solved both recursively and iteratively.
 *
 * Optimal Solution (Iterative using Stack):
 * The iterative approach simulates the recursion stack using an explicit `Stack`.
 *
 * Steps:
 * 1. Initialize an empty list `result` to store the traversal order.
 * 2. Initialize an empty `Stack<TreeNode> stack`.
 * 3. Initialize a `currentNode` pointer to `root`.
 * 4. Loop while `currentNode` is not null or `stack` is not empty:
 * a. While `currentNode` is not null:
 * i. Push `currentNode` onto the `stack`.
 * ii. Move `currentNode` to `currentNode.left` (keep going left).
 * b. Pop a node from the `stack` (`currentNode = stack.pop()`). This is the next node to visit in inorder.
 * c. Add `currentNode.val` to the `result` list.
 * d. Move `currentNode` to `currentNode.right` (now process the right subtree).
 * 5. Return the `result` list.
 *
 * Time Complexity:
 * O(N), where N is the number of nodes in the binary tree. Each node is pushed onto the stack and popped once.
 *
 * Space Complexity:
 * O(H), where H is the height of the binary tree. This is due to the `stack` used to store nodes. In the worst case (skewed tree), H can be N,
 * so the space complexity can be O(N). In the best case (balanced tree), H is logN, so the space complexity is O(logN).
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode currentNode = root;

        while (currentNode != null || !stack.isEmpty()) {
            while (currentNode != null) {
                stack.push(currentNode);
                currentNode = currentNode.left;
            }
            currentNode = stack.pop();
            result.add(currentNode.val);
            currentNode = currentNode.right;
        }
          return result;
    }
}