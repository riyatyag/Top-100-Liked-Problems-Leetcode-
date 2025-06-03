// Problem Statement:
// Design a data structure that follows the constraints of a Least Recently Used (LRU) cache.
// Implement the LRUCache class:
// - LRUCache(int capacity): Initialize the LRU cache with positive size capacity.
// - int get(int key): Return the value of the key if the key exists, otherwise return -1.
// - void put(int key, int value): Update the value of the key if the key exists. Otherwise, add the key-value pair to the cache. If the number of keys exceeds the capacity from this operation, evict the least recently used key.
// The functions get and put must each run in O(1) average time complexity.

// Approach:
// To achieve O(1) average time complexity for both `get` and `put` operations, we need a data structure that allows for fast lookups and fast insertion/deletion at both ends. A combination of a HashMap and a Doubly Linked List is a common and efficient solution for an LRU cache.

// 1. HashMap (Map<Integer, Node>):
//    - Stores key-value pairs where the key is the integer key of the cache entry, and the value is a reference to the corresponding `Node` in the doubly linked list.
//    - This allows O(1) average time complexity for checking if a key exists and retrieving its associated node.

// 2. Doubly Linked List (Node):
//    - Stores the actual cache entries (key-value pairs) in order of their recency.
//    - The head of the list represents the most recently used (MRU) item.
//    - The tail of the list represents the least recently used (LRU) item.
//    - Doubly linked list allows O(1) time for adding/removing nodes from both ends and for moving a node within the list (after `get` or `put` operations).

// Custom Node class:
// Each node in the doubly linked list will store:
// - `key`: The key of the cache entry.
// - `value`: The value associated with the key.
// - `prev`: A pointer to the previous node.
// - `next`: A pointer to the next node.

// Dummy Head and Tail Nodes:
// To simplify operations (especially edge cases like adding/removing the first/last node), we use dummy `head` and `tail` nodes for the doubly linked list.
// - `head`: Points to the MRU node.
// - `tail`: Points to the LRU node.
// `head.next` will be the actual MRU node, and `tail.prev` will be the actual LRU node.

// LRUCache Class Variables:
// - `map`: The HashMap to store key-to-node mappings.
// - `capacity`: The maximum number of key-value pairs the cache can hold.
// - `head`: Dummy head node of the doubly linked list.
// - `tail`: Dummy tail node of the doubly linked list.

// Operations:

// 1. `LRUCache(int capacity)`:
//    - Initialize `capacity`.
//    - Initialize the `map`.
//    - Create `head` and `tail` dummy nodes.
//    - Link `head` to `tail` and `tail` to `head` (empty list).

// 2. `get(int key)`:
//    - Check if `key` exists in the `map`.
//    - If not, return -1.
//    - If it exists:
//      - Get the corresponding `Node` from the `map`.
//      - Move this node to the front of the list (make it MRU). This involves:
//        - Removing the node from its current position.
//        - Adding the node right after the dummy `head` node.
//      - Return the `value` of the node.

// 3. `put(int key, int value)`:
//    - Check if `key` already exists in the `map`.
//    - If it exists:
//      - Get the corresponding `Node`.
//      - Update its `value`.
//      - Move this node to the front of the list (make it MRU) by removing and then adding it to the front.
//    - If it does not exist:
//      - Create a new `Node` with the given `key` and `value`.
//      - Add this new node to the front of the list (make it MRU).
//      - Add the `key`-to-`Node` mapping to the `map`.
//      - Check if the cache size exceeds `capacity`:
//        - If it does, remove the LRU node (the one just before the dummy `tail` node).
//        - Remove its entry from the `map` as well.

// Helper methods for Doubly Linked List operations:
// - `addNode(Node node)`: Adds a node right after the dummy `head` (making it MRU).
// - `removeNode(Node node)`: Removes a given node from the list.

// Time Complexity:
// - `get(key)`: O(1) average. HashMap lookup is O(1) average. Doubly linked list removal and addition are O(1).
// - `put(key, value)`: O(1) average. HashMap lookup/insertion/deletion is O(1) average. Doubly linked list operations are O(1).

// Space Complexity:
// - O(capacity). The HashMap stores up to `capacity` entries, and the doubly linked list stores up to `capacity` nodes.

// Optimal Solution:
class LRUCache {
    private class Node {
        int key;
        int value;
        Node prev;
        Node next;

        Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private int capacity;
    private Map<Integer, Node> map; 
    private Node head; 
    private Node tail; 

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<>();
        this.head = new Node(0, 0);
        this.tail = new Node(0, 0); 
        head.next = tail;
        tail.prev = head;
    }
    private void addNode(Node node) {
        node.prev = head;
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
    }
    private void removeNode(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }
    private void moveToFront(Node node) {
        removeNode(node);
        addNode(node);
    }
    private Node removeLastNode() {
        Node lastNode = tail.prev;
        removeNode(lastNode);
        return lastNode;
    }

    public int get(int key) {
        if (map.containsKey(key)) {
            Node node = map.get(key);
            moveToFront(node); 
            return node.value;
        }
        return -1; 
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            Node node = map.get(key);
            node.value = value; 
            moveToFront(node); 
        } else {
            Node newNode = new Node(key, value);
            map.put(key, newNode);
            addNode(newNode); 

            if (map.size() > capacity) {
                Node lruNode = removeLastNode(); 
                map.remove(lruNode.key);        
            }
        }
    }
}
