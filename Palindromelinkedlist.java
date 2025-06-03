// Problem Statement:
// Given the head of a singly linked list, return true if it is a palindrome or false otherwise.

// Approach:
// The problem asks us to determine if a given singly linked list is a palindrome. A straightforward approach would be to copy the linked list values into an array or ArrayList and then check if the array is a palindrome. However, this would require O(N) space. The follow-up question asks for an O(1) space solution.

// To achieve O(1) space, we can use the "fast and slow pointer" approach to find the middle of the linked list. Once we find the middle, we reverse the second half of the linked list. Then, we compare the first half with the reversed second half. If they are identical, the linked list is a palindrome. Finally, we should reverse the second half again to restore the original linked list structure (though this is not strictly necessary for the problem's output, it's good practice for general linked list manipulation).

// Steps:
// 1. Find the middle of the linked list using two pointers, 'slow' and 'fast'. 'slow' moves one step at a time, 'fast' moves two steps at a time. When 'fast' reaches the end (or null), 'slow' will be at the middle.
// 2. Reverse the second half of the linked list starting from 'slow'. This will give us a new head for the reversed second half.
// 3. Compare the first half of the original linked list (starting from 'head') with the reversed second half. Iterate through both halves simultaneously, comparing their values. If any values mismatch, it's not a palindrome.
// 4. If all values match, it is a palindrome.
// 5. (Optional but good practice) Reverse the second half again to restore the original linked list.

// Time Complexity:
// O(N), where N is the number of nodes in the linked list.
// - Finding the middle takes O(N) time.
// - Reversing the second half takes O(N) time.
// - Comparing the two halves takes O(N) time.
// Overall, the dominant factor is O(N).

// Space Complexity:
// O(1).
// We are only using a few pointers, which consume constant extra space.

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
    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode secondHalfHead = reverseList(slow.next);
        slow.next = null; 

        ListNode p1 = head;
        ListNode p2 = secondHalfHead;
        boolean isPalindrome = true;
        while (p1 != null && p2 != null) {
            if (p1.val != p2.val) {
                isPalindrome = false;
                break;
            }
            p1 = p1.next;
            p2 = p2.next;
        }
        return isPalindrome;
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