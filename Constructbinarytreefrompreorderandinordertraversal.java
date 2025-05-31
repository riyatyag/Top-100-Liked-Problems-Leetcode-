/**
 * Problem Statement:
 * Given an integer array nums where the elements are sorted in ascending order, convert it to a height-balanced binary search tree.
 * A height-balanced binary tree is a binary tree in which the depth of the two subtrees of every node never differs by more than one.
 *
 * Approach:
 * Since the input array is sorted, we can leverage this property to construct a height-balanced BST.
 * The middle element of a sorted array makes a good root for a balanced BST because it naturally divides the array into two halves
 * of roughly equal size, which will become the left and right subtrees.
 *
 * We can use a recursive approach:
 * 1. Define a helper function that takes the array, a 'left' index, and a 'right' index as parameters.
 * 2. Base Case: If `left` is greater than `right`, it means the subarray is empty, so return null.
 * 3. Recursive Step:
 * a. Calculate the middle index: `mid = left + (right - left) / 2`.
 * b. Create a new TreeNode with the value at `nums[mid]`. This will be the root of the current subtree.
 * c. Recursively call the helper function for the left subarray: `root.left = helper(nums, left, mid - 1)`.
 * d. Recursively call the helper function for the right subarray: `root.right = helper(nums, mid + 1, right)`.
 * e. Return the `root`.
 * 4. The initial call to the helper function will be `helper(nums, 0, nums.length - 1)`.
 *
 * This approach guarantees a height-balanced tree because at each step, we are choosing the median element as the root,
 * which ensures that the number of nodes in the left and right subtrees are as balanced as possible.
 *
 * Time Complexity:
 * O(N), where N is the number of elements in the array. We visit each element exactly once to create a corresponding tree node.
 *
 * Space Complexity:
 * O(H), where H is the height of the balanced BST. Since the tree is height-balanced, H is approximately log N.
 * This space is used by the recursion stack. So, the space complexity is O(log N).
 */
class Solution {
    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        return convert(nums, 0, nums.length - 1);
    }

    private TreeNode convert(int[] nums, int left, int right) {
        if (left > right) {
            return null;
        }

        int mid = left + (right - left) / 2;
        TreeNode root = new TreeNode(nums[mid]);

        root.left = convert(nums, left, mid - 1);
        root.right = convert(nums, mid + 1, right);

        return root;
    }
}