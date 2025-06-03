// Problem Statement:
// Given the heads of two singly linked-lists headA and headB, return the node at which the two lists intersect. If the two linked lists have no intersection at all, return null.
// The test cases are generated such that there are no cycles anywhere in the entire linked structure.
// Note that the linked lists must retain their original structure after the function returns.

// Approach:
// This problem asks us to find the intersection point of two linked lists. If they intersect, they will eventually share the same tail. The challenge is that they might have different lengths before the intersection.
// A clever approach to solve this in O(M+N) time and O(1) space is to use two pointers, let's call them `ptrA` and `ptrB`.

// The idea is to make both pointers traverse the same total distance.
// 1. Initialize `ptrA` to `headA` and `ptrB` to `headB`.
// 2. Iterate both pointers:
//    - When `ptrA` reaches the end of `listA` (becomes null), re-route it to `headB`.
//    - When `ptrB` reaches the end of `listB` (becomes null), re-route it to `headA`.
// 3. Continue this process. If the lists intersect, `ptrA` and `ptrB` will eventually meet at the intersection node.
// 4. If the lists do not intersect, both pointers will eventually become null at the same time (after traversing `Length(listA) + Length(listB)` and `Length(listB) + Length(listA)` respectively), and the loop will terminate, returning null.

// Why this works:
// Let `lenA` be the length of `listA` and `lenB` be the length of `listB`.
// Let `C` be the length of the common part (intersection).
// `listA` has `lenA - C` nodes before intersection, and `listB` has `lenB - C` nodes before intersection.

// When `ptrA` traverses `listA` and then `listB`, it covers a total distance of `lenA + lenB`.
// When `ptrB` traverses `listB` and then `listA`, it covers a total distance of `lenB + lenA`.

// If an intersection exists, after some steps:
// - `ptrA` will traverse `(lenA - C)` nodes in `listA`, then `C` nodes in the common part, then `lenB - C` nodes in `listB`, and finally `C` nodes in the common part again (if it reaches the intersection from `headB`).
// - `ptrB` will traverse `(lenB - C)` nodes in `listB`, then `C` nodes in the common part, then `lenA - C` nodes in `listA`, and finally `C` nodes in the common part again (if it reaches the intersection from `headA`).

// More simply, consider the paths:
// Path of A: `A_nodes_before_intersection` -> `Common_nodes`
// Path of B: `B_nodes_before_intersection` -> `Common_nodes`

// When ptrA finishes listA, it moves to headB.
// When ptrB finishes listB, it moves to headA.

// If they intersect, they will meet at the first common node.
// Suppose `ptrA` is at node `X` and `ptrB` is at node `Y`.
// If they meet, `X == Y`.
// The total distance traveled by `ptrA` until meeting: `(lenA - C) + C + (lenB - C)` = `lenA + lenB - C`
// The total distance traveled by `ptrB` until meeting: `(lenB - C) + C + (lenA - C)` = `lenB + lenA - C`
// Both pointers travel the same total distance `lenA + lenB - C` before they meet at the intersection node. If there is no intersection, they will both end up as null after traversing `lenA + lenB` and `lenB + lenA` steps respectively.

// Time Complexity:
// O(M + N), where M is the number of nodes in listA and N is the number of nodes in listB. In the worst case, each pointer traverses both lists once.

// Space Complexity:
// O(1), as we only use a constant number of extra pointers.

// Optimal Solution:
/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode(int x) {
 * val = x;
 * next = null;
 * }
 * }
 */
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }

        ListNode ptrA = headA;
        ListNode ptrB = headB;

        while (ptrA != ptrB) {
            ptrA = (ptrA == null) ? headB : ptrA.next;
            ptrB = (ptrB == null) ? headA : ptrB.next;
        }
        return ptrA;
    }
}