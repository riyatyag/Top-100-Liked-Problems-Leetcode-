// Problem Statement:
// Given the head of a singly linked list, reverse the list, and return the reversed list.

// Approach (Iterative):
// We'll use three pointers: `prev`, `current`, and `nextTemp`.
// 1. `prev` will keep track of the previously processed node, which will become the next node in the reversed list. Initially, it's null.
// 2. `current` will iterate through the original list, starting from the head.
// 3. `nextTemp` will temporarily store the `current.next` before we change `current.next` to `prev`, so we don't lose the rest of the list.
// In each step, we change `current.next` to `prev`, then move `prev` to `current`, and `current` to `nextTemp`. This effectively reverses the direction of the `next` pointer for each node.
// We continue this process until `current` becomes null, meaning we've traversed the entire original list. At this point, `prev` will be pointing to the new head of the reversed list.

// Time Complexity:
// O(N), where N is the number of nodes in the linked list. We iterate through each node exactly once.

// Space Complexity:
// O(1), as we only use a constant number of extra pointers.

// Optimal Solution (Iterative):
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
    public ListNode reverseList(ListNode head) {
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