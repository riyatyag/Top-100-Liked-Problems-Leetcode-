// Problem Statement:
// A linked list of length n is given such that each node contains an additional random pointer, which could point to any node in the list, or null.
// Construct a deep copy of the list. The deep copy should consist of exactly n brand new nodes, where each new node has its value set to the value of its corresponding original node. Both the next and random pointer of the new nodes should point to new nodes in the copied list such that the pointers in the original list and copied list represent the same list state. None of the pointers in the new list should point to nodes in the original list.
// Return the head of the copied linked list.

// Approach: Optimal O(N) Time and O(1) Space Approach
// This problem requires creating a deep copy of a linked list that includes both 'next' and 'random' pointers. The challenge is handling the 'random' pointers, which can point to any node in the original list. A common approach uses a HashMap to store mappings from original nodes to copied nodes, but this uses O(N) space. The optimal solution uses O(1) space by interleaving the copied nodes within the original list.

// The optimal approach involves three passes:

// Pass 1: Create copied nodes and interleave them with original nodes.
// For each node in the original list (say, `originalNode`):
// - Create a new copied node (`copiedNode`) with the same `val`.
// - Insert `copiedNode` right after `originalNode` in the original list.
//   - `copiedNode.next = originalNode.next`
//   - `originalNode.next = copiedNode`
// After this pass, the list will look like: `Original1 -> Copy1 -> Original2 -> Copy2 -> ...`

// Pass 2: Set the random pointers for the copied nodes.
// Traverse the interleaved list. For each `originalNode` (which will be at `current` in the traversal):
// - Its corresponding `copiedNode` is `current.next`.
// - If `originalNode.random` is not null, then the `copiedNode.random` should point to the copy of `originalNode.random`.
// - The copy of `originalNode.random` is `originalNode.random.next` (because of our interleaving in Pass 1).
//   - `copiedNode.random = originalNode.random.next`
// - Move `current` to `copiedNode.next` (which is the next `originalNode`).

// Pass 3: Separate the original and copied lists.
// Create a dummy head for the new copied list.
// Traverse the interleaved list again. For each `originalNode`:
// - `copiedNode` is `originalNode.next`.
// - Relink `originalNode.next` to `copiedNode.next` (restoring original list structure).
// - Relink `copiedNode.next` to `originalNode.next` (linking copied list nodes).
// - Move `current` to the next `originalNode`.

// Time Complexity:
// O(N), where N is the number of nodes in the linked list.
// Each of the three passes iterates through the list once, performing constant-time operations for each node.

// Space Complexity:
// O(1), as we are modifying the original list temporarily and only using a few pointers for traversal. No additional data structures (like HashMaps) are used to store mappings.

// Optimal Solution:
/*
// Definition for a Node.
class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
*/
class Solution {
    public Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }
        Node current = head;
        while (current != null) {
            Node copy = new Node(current.val);
            copy.next = current.next;
            current.next = copy;
            current = copy.next;
        }
        current = head;
        while (current != null) {
            if (current.random != null) {
                current.next.random = current.random.next;
            }
            current = current.next.next; 
        }
        Node originalHead = head;
        Node copiedHead = head.next;
        Node copiedCurrent = copiedHead;

        while (originalHead != null) {
            originalHead.next = copiedCurrent.next; 
            originalHead = originalHead.next;

            if (copiedCurrent.next != null) { 
                copiedCurrent.next = originalHead.next; 
            }
            copiedCurrent = copiedCurrent.next;
        }

        return copiedHead;
    }
}