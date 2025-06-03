// Problem Statement:
// Given the head of a linked list, reverse the nodes of the list k at a time, and return the modified list.
// k is a positive integer and is less than or equal to the length of the linked list. If the number of nodes is not a multiple of k then left-out nodes, in the end, should remain as it is.
// You may not alter the values in the list's nodes, only nodes themselves may be changed.
// Follow-up: Can you solve the problem in O(1) extra memory space?

// Approach: Iterative Reversal with O(1) Space
// The problem asks us to reverse nodes in groups of `k` without using extra space beyond a few pointers. This can be achieved by iteratively identifying each `k`-group, reversing it, and then connecting it to the previously reversed group.

// Steps:
// 1. Handle Edge Cases: If the list is empty, has only one node, or k is 1, no reversal is needed, return head.
// 2. Dummy Node: Create a dummy node `dummy` and point its `next` to the `head`. This simplifies handling the head of the new list and the connection of the first reversed group. `prevGroupTail` will keep track of the tail of the previously reversed group, initially pointing to `dummy`.
// 3. Iterate through the list: Use a `current` pointer starting from `head`.
// 4. Check for k nodes: Before reversing, ensure there are at least `k` nodes remaining from `current` onwards. Use a helper function `getKthNode(node, k)` to find the k-th node in the current segment. If fewer than `k` nodes are available, break the loop as the remaining nodes should not be reversed.
// 5. Reverse the k-group:
//    a. `groupHead` is `current`.
//    b. `groupTail` is the `k`-th node found.
//    c. Store `nextGroupHead` as `groupTail.next`.
//    d. Break the link: `groupTail.next = null;` to isolate the `k`-group.
//    e. Reverse the `k`-group (from `groupHead` to `groupTail`) using a standard linked list reversal technique (e.g., iterative reversal). The `reverseList` helper function can be used. This will return the new head of the reversed group, which is `groupTail` (from the original perspective).
// 6. Connect the reversed group:
//    a. `prevGroupTail.next` should point to the new `groupHead` (which was `groupTail` before reversal).
//    b. The original `groupHead` (now the tail of the reversed group) should point to `nextGroupHead`.
//    c. Update `prevGroupTail` to be the `groupHead` (the tail of the just-reversed group).
//    d. Set `current` to `nextGroupHead` to continue processing the rest of the list.
// 7. **Return**: The `dummy.next` will be the head of the fully modified list.

// Helper function `getKthNode(node, k)`:
// - Takes a starting `node` and an integer `k`.
// - Iterates `k-1` times to find the `k`-th node.
// - Returns the `k`-th node. If it encounters null before `k` nodes, it returns null.

// Helper function `reverseList(head)`:
// - Reverses a linked list iteratively. (Already implemented in previous problems)

// Time Complexity:
// O(N), where N is the total number of nodes in the linked list.
// - We iterate through the list once to identify groups.
// - For each group of `k` nodes, we traverse it once to find the `k`-th node and then traverse it again (effectively) during the reversal.
// - Since each node is visited and its pointers are manipulated a constant number of times across all group reversals, the total time complexity remains linear.

// Space Complexity:
// O(1), as we only use a few pointers (dummy, prevGroupTail, current, groupHead, groupTail, nextGroupHead) for manipulation, not dependent on N.

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
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || k == 1) {
            return head;
        }

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prevGroupTail = dummy; 

        while (true) {
            ListNode groupHead = prevGroupTail.next;
            ListNode groupTail = getKthNode(groupHead, k); 

            if (groupTail == null) {
                break;
            }

            ListNode nextGroupHead = groupTail.next;

            groupTail.next = null;

            ListNode reversedGroupNewHead = reverseList(groupHead);

            prevGroupTail.next = reversedGroupNewHead;

            groupHead.next = nextGroupHead;

            prevGroupTail = groupHead;
        }

        return dummy.next;
    }
    private ListNode getKthNode(ListNode start, int k) {
        while (start != null && k > 1) {
            start = start.next;
            k--;
        }
        return start; 
    }
    private ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode current = head;
        while (current != null) {
            ListNode nextTemp = current.next;
            current.next = prev;
            prev = current;
            current = nextTemp;
        }
        return prev;
    }
}