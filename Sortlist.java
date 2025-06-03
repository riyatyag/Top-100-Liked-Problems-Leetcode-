// Problem Statement:
// Given the head of a linked list, return the list after sorting it in ascending order.
// Follow up: Can you sort the linked list in O(n logn) time and O(1) memory (i.e. constant space)?

// Approach: Merge Sort (Bottom-Up)
// The problem asks for an O(n logn) time and O(1) space solution for sorting a linked list. Merge Sort is a suitable algorithm for this, and specifically, a bottom-up merge sort can achieve O(1) space complexity.

// Traditional merge sort (top-down) recursively divides the list into halves, sorts them, and then merges them. This usually involves O(log N) space due to recursion stack.
// Bottom-up merge sort, on the other hand, iteratively merges sublists of increasing size. It starts by considering each node as a sorted sublist of size 1, then merges adjacent pairs of size 1 into sorted sublists of size 2, then merges adjacent pairs of size 2 into sorted sublists of size 4, and so on, until the entire list is sorted.

// Steps for Bottom-Up Merge Sort:
// 1. Calculate the length of the linked list. This is needed to determine the number of passes and the size of sublists to merge.
// 2. Create a dummy head node. This simplifies handling the head of the sorted list and merging the first sublist.
// 3. Iterate with `subLength` (size of sublists to merge), starting from 1, and doubling it in each iteration (1, 2, 4, 8...).
// 4. In each iteration of `subLength`:
//    a. Traverse the list using a `current` pointer.
//    b. Split the list into two sublists of `subLength` length each (or less if at the end of the list).
//       - `head1`: points to the first sublist.
//       - `head2`: points to the second sublist.
//       - Temporarily break the link between `head1` and `head2` and between `head2` and the rest of the list.
//    c. Merge `head1` and `head2` using a standard merge two sorted lists function.
//    d. Attach the merged list back to the `prev` pointer (from the dummy head or previous merged section).
//    e. Update `current` to continue traversing from where the merge ended.
// 5. The loop continues until `subLength` is greater than or equal to the total length of the list.

// Helper function `split(head, length)`:
// This function takes a head and a desired length, traverses `length - 1` times, breaks the link, and returns the head of the second part.

// Helper function `merge(l1, l2)`:
// This function merges two sorted linked lists `l1` and `l2` into a single sorted list. It returns the head of the merged list.

// Time Complexity:
// O(N log N).
// - Calculating length takes O(N).
// - There are log N passes (each pass doubles `subLength`).
// - In each pass, we traverse the entire list to split and merge sublists. Merging two sublists of total size K takes O(K) time. Across a full pass, all nodes are processed, contributing O(N) work.
// - Therefore, total time is O(N log N).

// Space Complexity:
// O(1).
// We only use a few pointers (current, prev, head1, head2, etc.), which consume constant extra space. No recursion stack is involved for the main sorting logic.

// Optimal Solution (Bottom-Up Merge Sort):
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
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        int n = 0;
        ListNode temp = head;
        while (temp != null) {
            n++;
            temp = temp.next;
        }
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;

        for (int subLength = 1; subLength < n; subLength <<= 1) { 
            ListNode prev = dummyHead;
            ListNode current = dummyHead.next; 

            while (current != null) {
                ListNode head1 = current;
                int count = 0;
                while (current != null && count < subLength) {
                    current = current.next;
                    count++;
                }
                ListNode head2 = null;
                if (current != null) {
                    head2 = current;
                    count = 0;
                    while (current != null && count < subLength) {
                        current = current.next;
                        count++;
                    }
                }
                ListNode temp1 = head1;
                for (int i = 0; i < subLength - 1 && temp1 != null; i++) {
                    temp1 = temp1.next;
                }
                if (temp1 != null) {
                    temp1.next = null;
                }

                ListNode temp2 = head2;
                if (head2 != null) {
                    for (int i = 0; i < subLength - 1 && temp2 != null; i++) {
                        temp2 = temp2.next;
                    }
                    if (temp2 != null) {
                        temp2.next = null;
                    }
                }
                ListNode mergedList = merge(head1, head2);

                prev.next = mergedList;

                while (prev.next != null) {
                    prev = prev.next;
                }
            }
        }
       return dummyHead.next;
    }
    private ListNode merge(ListNode l1, ListNode l2) {
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