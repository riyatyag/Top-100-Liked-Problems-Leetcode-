/**
 * Problem Statement:
 * Given the root of a binary tree and an integer targetSum, return the number of paths where the sum of the values along the path equals targetSum.
 * The path does not need to start or end at the root or a leaf, but it must go downwards (i.e., traveling only from parent nodes to child nodes).
 *
 * Approach:
 * This problem can be solved using a pre-order traversal (DFS) combined with a hash map to store the cumulative sum of nodes encountered so far along the current path, and the frequency of each sum.
 *
 * When we traverse the tree, we maintain a 'current_sum' from the root to the current node.
 * At each node, we check if 'current_sum - targetSum' exists in our hash map.
 * If it does, it means there is a path ending at the current node whose sum equals 'targetSum'.
 * The number of such paths is given by the frequency of 'current_sum - targetSum' in the hash map.
 *
 * We increment the frequency of the 'current_sum' in the hash map.
 * After visiting a node and its children, we must backtrack by decrementing the frequency of 'current_sum' to avoid affecting paths in other branches of the tree.
 * We initialize the hash map with {0: 1} to account for paths that start from the root and sum up to 'targetSum' directly.
 *
 * Time Complexity:
 * O(N), where N is the number of nodes in the binary tree. We visit each node once.
 * Hash map operations (put, get) take O(1) on average.
 *
 * Space Complexity:
 * O(H), where H is the height of the binary tree. This is for the recursion stack. In the worst case (skewed tree), H can be N, leading to O(N) space.
 * Additionally, the hash map can store up to N distinct cumulative sums in the worst case, also contributing O(N) space.
 * So, overall space complexity is O(N).
 */

import java.util.HashMap;
import java.util.Map;
class Solution {
    int count = 0;
    Map<Long, Integer> sumFrequency = new HashMap<>();

    public int pathSum(TreeNode root, int targetSum) {
        sumFrequency.put(0L, 1);
        dfs(root, 0L, targetSum);
        return count;
    }

    private void dfs(TreeNode node, long currentSum, int targetSum) {
        if (node == null) {
            return;
        }

        currentSum += node.val;
        if (sumFrequency.containsKey(currentSum - targetSum)) {
            count += sumFrequency.get(currentSum - targetSum);
        }
        sumFrequency.put(currentSum, sumFrequency.getOrDefault(currentSum, 0) + 1);

        dfs(node.left, currentSum, targetSum);
        dfs(node.right, currentSum, targetSum);

        sumFrequency.put(currentSum, sumFrequency.get(currentSum) - 1);
    }
}