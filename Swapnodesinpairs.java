// Problem Statement:
// Given a linked list, swap every two adjacent nodes and return its head. You must solve the problem without modifying the values in the list's nodes (i.e., only nodes themselves may be changed.)

// Approach: Iterative
// To swap adjacent nodes in a linked list without modifying values, we need to manipulate the 'next' pointers. An iterative approach is suitable and allows for O(1) space complexity. We will use a dummy node to simplify the handling of the new head of the list.

// Let's consider a pair of nodes `A -> B`. We want to transform this to `B -> A`.
// We also need to keep track of the node immediately preceding this pair (let's call it `prev`) and the node immediately following this pair (let's call it `nextPairHead`).

// Steps:
// 1.  Create a Dummy Node: Initialize a `dummy` node with its `next` pointer pointing to the original `head`. This `dummy` node acts as a placeholder, especially useful when the first two nodes need to be swapped, as it provides a stable `prev` for the first pair.
// 2.  Initialize `prev` Pointer: Set `prev` to `dummy`. This `prev` pointer will always point to the node *before* the current pair being considered for swapping.
// 3.  Iterate through the list: Use a `while` loop that continues as long as there are at least two nodes remaining to be swapped. This means `prev.next` (first node of the pair) and `prev.next.next` (second node of the pair) must both be non-null.
// 4.  Identify Nodes in the Pair:
//     * `node1 = prev.next` (the first node of the current pair)
//     * `node2 = prev.next.next` (the second node of the current pair)
//     * `nextPairHead = node2.next` (the head of the next pair or the rest of the list)
// 5.  **Perform the Swap (Pointer Reassignments)**:
//     * `node2.next = node1`: The second node now points to the first node.
//     * `node1.next = nextPairHead`: The first node (now the second in the pair) points to the head of the next pair.
//     * `prev.next = node2`: The node preceding the pair now points to the new head of the swapped pair (`node2`).
// 6.  Advance `prev`: After swapping a pair, `node1` becomes the tail of the swapped pair. We update `prev` to `node1` so it's correctly positioned to handle the next pair.
// 7.  Return: The head of the modified list will be `dummy.next`.

// Time Complexity:
// O(N), where N is the number of nodes in the linked list. We traverse the list once, and for each pair, we perform a constant number of pointer reassignments.

// Space Complexity:
// O(1), as we only use a few extra pointers (`dummy`, `prev`, `node1`, `node2`, `nextPairHead`). The space used does not depend on the number of nodes in the list.

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
    public ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode prev = dummy;

        while (prev.next != null && prev.next.next != null) {
            ListNode node1 = prev.next;
            ListNode node2 = prev.next.next;

            ListNode nextPairHead = node2.next;

            node2.next = node1;
            node1.next = nextPairHead;
            prev.next = node2;
            prev = node1;
        }
        return dummy.next;
    }
}