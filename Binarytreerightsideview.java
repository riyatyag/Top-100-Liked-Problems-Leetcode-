/**
 * Problem Statement:
 * Given the root of a binary tree, imagine yourself standing on the right side of it, return the values of the nodes you can see ordered from top to bottom.
 *
 * Approach:
 * This problem can be solved using a Breadth-First Search (BFS) or a Depth-First Search (DFS) approach.
 *
 * Optimal Solution (BFS - Level Order Traversal):
 * The most intuitive way to get the right side view is to perform a level-order traversal (BFS).
 * For each level, the last node visited will be the one visible from the right side.
 * We can use a Queue to manage nodes at each level.
 *
 * Steps:
 * 1. Initialize an empty list to store the right side view nodes.
 * 2. If the root is null, return the empty list.
 * 3. Create a queue and add the root to it.
 * 4. While the queue is not empty:
 * a. Get the number of nodes at the current level (levelSize).
 * b. Iterate 'levelSize' times:
 * i. Dequeue a node.
 * ii. If it's the last node of the current level (i.e., loop counter equals levelSize - 1), add its value to the result list.
 * iii. Enqueue its left child if it exists.
 * iv. Enqueue its right child if it exists.
 * 5. Return the result list.
 *
 * Time Complexity:
 * O(N), where N is the number of nodes in the binary tree. We visit each node exactly once.
 *
 * Space Complexity:
 * O(W), where W is the maximum width of the binary tree. In the worst case (a complete binary tree), W can be N/2,
 * so the space complexity can be O(N). In the best case (a skewed tree), W is 1, so the space complexity is O(1).
 */
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Solution {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            for (int i = 0; i < levelSize; i++) {
                TreeNode currentNode = queue.poll();

                if (i == levelSize - 1) {
                    result.add(currentNode.val);
                }
                if (currentNode.left != null) {
                    queue.offer(currentNode.left);
                }
                if (currentNode.right != null) {
                    queue.offer(currentNode.right);
                }
            }
        }
        return result;
    }
}