// Problem Statement:
// You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order, and each of their nodes contains a single digit. Add the two numbers and return the sum as a linked list.
// You may assume the two numbers do not contain any leading zero, except the number 0 itself.

// Approach:
// The problem asks us to add two numbers represented by linked lists where digits are stored in reverse order. This is analogous to how we perform manual addition, starting from the least significant digit (which is the head of the linked list). We can iterate through both lists simultaneously, summing the corresponding digits and carrying over any excess to the next position.

// Steps:
// 1. Create a dummy head node for the result list. This simplifies handling the initial head and avoids special checks for the first node.
// 2. Initialize a `current` pointer to the dummy node. This pointer will be used to build the result list.
// 3. Initialize a `carry` variable to 0.
// 4. Iterate using a `while` loop as long as either `l1` is not null, `l2` is not null, or `carry` is not 0. This ensures all digits are processed and any final carry is added.
// 5. Inside the loop:
//    a. Get the value of the current node from `l1`. If `l1` is null, consider its value as 0.
//    b. Get the value of the current node from `l2`. If `l2` is null, consider its value as 0.
//    c. Calculate the `sum` of `val1`, `val2`, and the `carry`.
//    d. Update the `carry` for the next iteration: `carry = sum / 10`.
//    e. Create a new `ListNode` with the digit `sum % 10`.
//    f. Append this new node to the `current.next`.
//    g. Move the `current` pointer to this newly added node.
//    h. Advance `l1` and `l2` to their next nodes if they are not null.
// 6. After the loop finishes, the `dummy.next` will point to the head of the resulting sum linked list.

// Time Complexity:
// O(max(M, N)), where M is the number of nodes in `l1` and N is the number of nodes in `l2`.
// We iterate through both linked lists at most once. The number of operations is proportional to the length of the longer list.

// Space Complexity:
// O(max(M, N)), as the length of the new linked list representing the sum can be at most `max(M, N) + 1` (in case of a final carry). We are creating new nodes for the result.

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
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(0);
        ListNode current = dummyHead;
        int carry = 0;

        while (l1 != null || l2 != null || carry != 0) {
            int val1 = (l1 != null) ? l1.val : 0;
            int val2 = (l2 != null) ? l2.val : 0;

            int sum = val1 + val2 + carry;
            carry = sum / 10;
            int digit = sum % 10;

            current.next = new ListNode(digit);
            current = current.next;

            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }

        return dummyHead.next;
    }
}