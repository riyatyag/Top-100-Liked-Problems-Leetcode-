// Problem Statement:
// Given head, the head of a linked list, determine if the linked list has a cycle in it.
// There is a cycle in a linked list if there is some node in the list that can be reached again by continuously following the next pointer.
// Return true if there is a cycle in the linked list. Otherwise, return false.
// Follow up: Can you solve it using O(1) (i.e. constant) memory?

// Approach: Floyd's Cycle-Finding Algorithm (Tortoise and Hare)
// This problem is a classic application of Floyd's Cycle-Finding Algorithm. The idea is to use two pointers, one moving slower than the other. If there's a cycle, the faster pointer will eventually catch up to the slower pointer. If there's no cycle, the faster pointer will reach the end of the list (null).

// Steps:
// 1. Initialize two pointers, `slow` and `fast`, both starting at the `head` of the linked list.
// 2. Move `slow` one step at a time (`slow = slow.next`).
// 3. Move `fast` two steps at a time (`fast = fast.next.next`).
// 4. Continue this process within a loop.
// 5. Inside the loop, check two conditions for `fast`:
//    a. If `fast` becomes `null` or `fast.next` becomes `null`, it means `fast` has reached the end of the list, indicating no cycle. In this case, return `false`.
//    b. If `slow` and `fast` pointers meet (i.e., `slow == fast`), it means there is a cycle in the linked list. In this case, return `true`.

// Time Complexity:
// O(N), where N is the number of nodes in the linked list.
// In the worst case (no cycle or a cycle involving almost all nodes), the fast pointer traverses the entire list at most twice. The slow pointer traverses it at most once. Therefore, the time complexity is linear.

// Space Complexity:
// O(1), as we only use two extra pointers (`slow` and `fast`), consuming constant extra space.

// Optimal Solution:
/**
 * Definition for singly-linked list.
 * class ListNode {
 * int val;
 * ListNode next;
 * ListNode(int x) {
 * val = x;
 * next = null;
 * }
 * }
 */
public class Solution {
    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }

        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;        
            fast = fast.next.next;   

            if (slow == fast) {
                return true;
            }
        }
        return false;
    }
}