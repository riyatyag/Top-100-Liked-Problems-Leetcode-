/**
 * Problem Statement:
 * Given the root of a binary tree, return the level order traversal of its nodes' values. (i.e., from left to right, level by level).
 *
 * Approach:
 * This problem is a classic application of Breadth-First Search (BFS).
 * We can use a queue to perform a level-by-level traversal of the tree.
 * For each level, we will collect all node values into a list and then add that list to our final result.
 *
 * Steps:
 * 1. Initialize an empty list of lists (e.g., `List<List<Integer>> result`) to store the level order traversal.
 * 2. If the `root` is null, return the empty `result` list.
 * 3. Create a queue (e.g., `Queue<TreeNode> queue = new LinkedList<>();`) and add the `root` to it.
 * 4. While the `queue` is not empty:
 * a. Get the number of nodes currently in the queue. This `levelSize` represents the number of nodes at the current level.
 * b. Create a new empty list (e.g., `List<Integer> currentLevelNodes`) to store the values of nodes at the current level.
 * c. Loop `levelSize` times:
 * i. Dequeue a node from the front of the queue (`TreeNode currentNode = queue.poll();`).
 * ii. Add the `currentNode.val` to `currentLevelNodes`.
 * iii. If `currentNode.left` is not null, enqueue it.
 * iv. If `currentNode.right` is not null, enqueue it.
 * d. After the loop, add `currentLevelNodes` to the `result` list.
 * 5. Return the `result` list.
 *
 * Time Complexity:
 * O(N), where N is the number of nodes in the binary tree. Each node is visited and processed exactly once.
 *
 * Space Complexity:
 * O(W), where W is the maximum width of the binary tree. In the worst case, a complete binary tree's last level can hold N/2 nodes,
 * so the queue might store up to N/2 nodes. Thus, the space complexity is O(N).
 * In the best case (a skewed tree), W is 1, so the space complexity is O(1).
 */
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            List<Integer> currentLevelNodes = new ArrayList<>();

            for (int i = 0; i < levelSize; i++) {
                TreeNode currentNode = queue.poll();
                currentLevelNodes.add(currentNode.val);

                if (currentNode.left != null) {
                    queue.offer(currentNode.left);
                }
                if (currentNode.right != null) {
                    queue.offer(currentNode.right);
                }
            }
            result.add(currentLevelNodes);
        }
        return result;
    }
}