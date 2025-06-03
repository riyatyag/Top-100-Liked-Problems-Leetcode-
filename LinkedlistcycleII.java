// Problem Statement:
// Given the head of a linked list, return the node where the cycle begins. If there is no cycle, return null.
// There is a cycle in a linked list if there is some node in the list that can be reached again by continuously following the next pointer.
// Do not modify the linked list.
// Follow up: Can you solve it using O(1) (i.e. constant) memory?

// Approach: Floyd's Cycle-Finding Algorithm (Tortoise and Hare)
// This problem is a classic application of Floyd's Cycle-Finding Algorithm, also known as the Tortoise and Hare algorithm. It's a two-step process:

// Step 1: Detect if a cycle exists.
// - Use two pointers, `slow` and `fast`.
// - `slow` moves one step at a time (`slow = slow.next`).
// - `fast` moves two steps at a time (`fast = fast.next.next`).
// - If there is a cycle, `fast` will eventually meet `slow`.
// - If `fast` reaches `null` or `fast.next` reaches `null`, there is no cycle.

// Step 2: Find the starting node of the cycle.
// - If a cycle is detected (i.e., `slow == fast`), reset one of the pointers (say, `slow`) back to `head`.
// - Keep the other pointer (`fast` or `slow` if you've reused `slow`) at the meeting point.
// - Now, move both `slow` and `fast` one step at a time.
// - The point where they meet again will be the start of the cycle.

// Mathematical Proof for Step 2:
// Let:
// - `L` be the distance from the head to the start of the cycle.
// - `C` be the length of the cycle.
// - `K` be the distance from the start of the cycle to the meeting point.

// When slow and fast meet:
// - Distance covered by slow: `L + K`
// - Distance covered by fast: `L + K + nC` (where `n` is some integer, fast has completed `n` full cycles)

// Since fast moves twice as fast as slow:
// `2 * (Distance by slow) = (Distance by fast)`
// `2 * (L + K) = L + K + nC`
// `2L + 2K = L + K + nC`
// `L + K = nC`
// `L = nC - K`
// `L = (n-1)C + C - K`
// `L = (n-1)C + (C - K)`

// This equation tells us that the distance from the head to the cycle start (`L`) is equivalent to `(n-1)` full cycles plus `(C - K)`.
// `(C - K)` is the distance from the meeting point back to the start of the cycle (i.e., if you continue moving `C - K` steps from the meeting point, you'll reach the cycle start).

// So, if we reset `slow` to `head` and keep `fast` at the meeting point, and move both one step at a time, `slow` will cover `L` steps from the head, and `fast` will cover `C - K` steps (plus possibly full cycles) from the meeting point. They will meet at the cycle start.

// Time Complexity:
// O(N), where N is the number of nodes in the linked list.
// - Step 1: `fast` pointer traverses the list at most twice (one time to meet `slow`, plus possibly one full cycle if it overtakes `slow` on the first loop). `slow` traverses at most once. So, O(N).
// - Step 2: Both pointers traverse at most the distance `L + C`. So, O(N).
// Overall, the time complexity is linear.

// Space Complexity:
// O(1), as we only use a few pointers.

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
    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null) {
            return null; 
        }

        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) {
                ListNode entry = head;
                while (entry != slow) {
                    entry = entry.next;
                    slow = slow.next;
                }
                return entry; 
            }
        }

        return null;
    }
}