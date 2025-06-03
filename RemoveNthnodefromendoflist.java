// Problem Statement:
// Given the head of a linked list, remove the nth node from the end of the list and return its head.
// Follow up: Could you do this in one pass?

// Approach: One-Pass Two-Pointer (Fast and Slow Pointers)
// To remove the nth node from the end in a single pass, we can use two pointers: a `fast` pointer and a `slow` pointer. The key idea is to create a gap of `n` nodes between the `fast` and `slow` pointers. When the `fast` pointer reaches the end of the list, the `slow` pointer will be positioned exactly before the node that needs to be removed.

// Steps:
// 1.  Create a Dummy Node: Create a `dummy` node and point its `next` to the `head` of the original list. This dummy node is crucial for handling edge cases, especially when the node to be removed is the original head of the list (e.g., removing the 1st node from the end of a list of size 1).
// 2.  Initialize Pointers:
//     * Set both `fast` and `slow` pointers to the `dummy` node.
// 3.  Advance `fast` Pointer: Move the `fast` pointer `n + 1` steps ahead. This creates the desired gap of `n` nodes between `fast` and `slow`. The `+1` is important because we want `slow` to stop *before* the node to be removed, so it can correctly update the `next` pointer.
// 4.  Move Both Pointers Simultaneously: Now, move both `fast` and `slow` pointers one step at a time until the `fast` pointer reaches `null` (meaning it has gone past the end of the list).
// 5.  Remove the Node: When the `fast` pointer is `null`, the `slow` pointer will be pointing to the node *before* the `nth` node from the end. To remove the `nth` node from the end, simply bypass it: set `slow.next = slow.next.next`.
// 6.  Return Result: The head of the modified list is `dummy.next`.

// Example walkthrough: head = [1,2,3,4,5], n = 2
// Initial: dummy -> 1 -> 2 -> 3 -> 4 -> 5
//          ^slow, ^fast

// Advance fast by n+1 = 3 steps:
// fast moves 1: dummy -> 1 -> 2 -> 3 -> 4 -> 5
//                   ^slow      ^fast
// fast moves 2: dummy -> 1 -> 2 -> 3 -> 4 -> 5
//                   ^slow           ^fast
// fast moves 3: dummy -> 1 -> 2 -> 3 -> 4 -> 5
//                   ^slow                ^fast (at node 3)

// Now, move slow and fast simultaneously until fast is null:
// 1st iter: slow moves to 1, fast moves to 4
// dummy -> 1 -> 2 -> 3 -> 4 -> 5
//          ^slow           ^fast

// 2nd iter: slow moves to 2, fast moves to 5
// dummy -> 1 -> 2 -> 3 -> 4 -> 5
//               ^slow           ^fast

// 3rd iter: slow moves to 3, fast moves to null
// dummy -> 1 -> 2 -> 3 -> 4 -> 5
//                    ^slow      ^fast (null)

// Now slow is at node 3. The node to be removed is `slow.next` (node 4).
// Set `slow.next = slow.next.next`: `slow.next` (node 4) will now point to `node.next.next` (node 5).
// Effectively: `3 -> 5` (node 4 is bypassed/removed).
// Result: `dummy -> 1 -> 2 -> 3 -> 5`.
// Return `dummy.next` which is `1 -> 2 -> 3 -> 5`.

// Time Complexity:
// O(L), where L is the length of the linked list.
// The `fast` pointer traverses the list once. The `slow` pointer also traverses most of the list once. This is a single pass solution.

// Space Complexity:
// O(1), as we only use a constant number of extra pointers (`dummy`, `fast`, `slow`).

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
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode fast = dummy;
        ListNode slow = dummy;

        for (int i = 0; i <= n; i++) {
            fast = fast.next;
        }
        while (fast != null) {
            slow = slow.next;
            fast = fast.next;
        }

        slow.next = slow.next.next;
        return dummy.next;
    }
}