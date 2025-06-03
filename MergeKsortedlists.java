// Problem Statement:
// You are given an array of k linked-lists lists, each linked-list is sorted in ascending order.
// Merge all the linked-lists into one sorted linked-list and return it.

// Approach: Divide and Conquer
// This problem can be efficiently solved using a divide and conquer strategy, similar to the merge sort algorithm. The core idea is to recursively merge pairs of sorted linked lists until all lists are combined into a single sorted list.

// Steps:
// 1.  Main Function `mergeKLists(ListNode[] lists)`:
//     * Handle edge cases: If the input array `lists` is null or empty, return null.
//     * Call a recursive helper function `merge(lists, start, end)` to initiate the merging process. The initial call will cover the entire range of lists from index 0 to `lists.length - 1`.

// 2.  Recursive Helper Function `merge(ListNode[] lists, int start, int end)`:
//     * Base Cases:
//         * If `start == end`, it means there's only one list in this segment, so return `lists[start]` directly. This is the smallest unit to merge.
//         * If `start > end`, it indicates an invalid segment (e.g., when the array is empty or a sub-segment becomes invalid), so return `null`.
//     * Recursive Step:
//         * Calculate the middle index: `mid = start + (end - start) / 2`. This prevents potential integer overflow compared to `(start + end) / 2`.
//         * Recursively merge the left half of the lists: `list1 = merge(lists, start, mid)`.
//         * Recursively merge the right half of the lists: `list2 = merge(lists, mid + 1, end)`.
//         * Once `list1` and `list2` (which are now sorted linked lists) are obtained, merge them using a standard `mergeTwoLists` helper function and return the result.

// 3.  Helper Function `mergeTwoLists(ListNode l1, ListNode l2)`:
//     * This is a standard utility function that takes two sorted linked lists, `l1` and `l2`, and merges them into a single sorted linked list.
//     * Create a `dummy` node (e.g., `new ListNode(0)`) to serve as the starting point of the merged list. This simplifies appending nodes without worrying about the initial head.
//     * Initialize a `current` pointer to `dummy`. This pointer will be used to build the merged list.
//     * Iterate while both `l1` and `l2` are not null:
//         * Compare the `val` of `l1` and `l2`.
//         * Append the node with the smaller value to `current.next`.
//         * Advance the pointer of the list from which the node was taken (e.g., `l1 = l1.next`).
//         * Move `current` to its newly appended node (`current = current.next`).
//     * After the loop, one of the lists (`l1` or `l2`) might still have remaining nodes. Append the rest of that list to `current.next`.
//     * Return `dummy.next`, which is the head of the fully merged and sorted list.

// Time Complexity:
// O(N log k), where N is the total number of nodes across all linked lists, and k is the number of linked lists.
// - The divide and conquer strategy forms a balanced binary tree of merge operations. There are `log k` levels in this tree.
// - At each level of the tree, every node from the original `k` lists is processed exactly once when two sub-lists are merged. Merging two lists of total length `X` takes O(X) time. Since all `N` nodes are collectively processed across all merges at a given level, each level contributes O(N) work.
// - Therefore, the total time complexity is the number of levels multiplied by the work per level: O(log k * N).

// Space Complexity:
// O(log k), due to the recursion stack space used by the divide and conquer approach. The maximum depth of the recursion tree is `log k`. The `mergeTwoLists` helper function itself uses O(1) auxiliary space.

// Optimal Solution:
/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode() {}
 * ListNode(int val) { this.val = val; }
 * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        return merge(lists, 0, lists.length - 1);
    }

    private ListNode merge(ListNode[] lists, int start, int end) {
        if (start == end) {
            return lists[start];
        }
        if (start > end) {
            return null;
        }

        int mid = start + (end - start) / 2;
        ListNode list1 = merge(lists, start, mid);
        ListNode list2 = merge(lists, mid + 1, end);

        return mergeTwoLists(list1, list2);
    }

    private ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;

        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                current.next = l1;
                l1 = l1.next;
            } else {
                current.next = l2;
                l2 = l2.next;
            }
            current = current.next;
        }

        if (l1 != null) {
            current.next = l1;
        } else if (l2 != null) {
            current.next = l2;
        }

        return dummy.next;
    }
}