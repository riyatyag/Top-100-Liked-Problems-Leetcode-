// Problem Statement:
// You are given the heads of two sorted linked lists list1 and list2.
// Merge the two lists into one sorted list. The list should be made by splicing together the nodes of the first two lists.
// Return the head of the merged linked list.

// Approach: Iterative Merging
// We need to merge two already sorted linked lists into a single sorted linked list. An iterative approach is efficient and straightforward. We will use a dummy node to simplify the construction of the new merged list.

// Steps:
// 1.  Create a Dummy Node: Initialize a `dummy` node (e.g., `new ListNode(0)`). This node will serve as the starting point of our merged list. Its `next` pointer will eventually point to the true head of the merged list.
// 2.  Initialize `current` Pointer: Set a `current` pointer to the `dummy` node. This `current` pointer will be used to traverse and build the merged list by appending nodes.
// 3.  Iterate and Compare: Use a `while` loop that continues as long as both `list1` and `list2` are not null. In each iteration:
//     * Compare the `val` of the current node in `list1` with the `val` of the current node in `list2`.
//     * If `list1.val` is less than or equal to `list2.val`, append `list1` to `current.next`. Then, advance `list1` to its next node (`list1 = list1.next`).
//     * Else (if `list2.val` is smaller), append `list2` to `current.next`. Then, advance `list2` to its next node (`list2 = list2.next`).
//     * In either case, move `current` to its newly appended node (`current = current.next`) to prepare for the next appendage.
// 4.  Append Remaining Nodes: After the loop terminates, one of the lists (`list1` or `list2`) might still have remaining nodes (because all nodes in the other list have been appended). Append the rest of the non-null list to `current.next`.
// 5.  Return Result: The head of the fully merged and sorted list is `dummy.next`.

// Time Complexity:
// O(M + N), where M is the number of nodes in `list1` and N is the number of nodes in `list2`.
// In the worst case, we traverse each node in both lists exactly once. Each comparison and pointer assignment takes constant time.

// Space Complexity:
// O(1), as we only use a few extra pointers (`dummy`, `current`, `list1`, `list2`). The space used does not depend on the size of the input lists.

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
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;        

        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                current.next = list1;
                list1 = list1.next;
            } else {
                current.next = list2;
                list2 = list2.next;
            }
            current = current.next;
        }
        if (list1 != null) {
            current.next = list1;
        } else if (list2 != null) {
            current.next = list2;
        }
        return dummy.next;
    }
}