// Problem Statement:
// Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
// Implement the MinStack class:
// MinStack() initializes the stack object.
// void push(int val) pushes the element val onto the stack.
// void pop() removes the element on the top of the stack.
// int top() gets the top element of the stack.
// int getMin() retrieves the minimum element in the stack.
// You must implement a solution with O(1) time complexity for each function.

// Approach:
// To achieve O(1) time complexity for all operations, including getMin, we can use two stacks.
// One stack, `stack`, will store all the elements pushed onto the stack in their normal order.
// The second stack, `minStack`, will store the minimum element encountered so far at each corresponding level of the `stack`.
// When an element is pushed:
// - It is always pushed onto the `stack`.
// - For the `minStack`, we push the minimum of the current value and the current minimum element (if `minStack` is not empty). If `minStack` is empty, the current value is the first minimum. This ensures that `minStack.peek()` always holds the minimum value among all elements currently in `stack`.
// When an element is popped:
// - Both `stack` and `minStack` must pop their top elements. This maintains synchronization between the two stacks, ensuring that `minStack` correctly reflects the minimum of the remaining elements.
// The `top()` operation simply returns the top of the main `stack`.
// The `getMin()` operation simply returns the top of the `minStack`.

// Time Complexity:
// All operations (push, pop, top, getMin) have a time complexity of O(1).
// This is because stack operations (push, pop, peek, isEmpty) take constant time.

// Space Complexity:
// O(n), where 'n' is the number of elements in the stack. In the worst case, both stacks will store 'n' elements.

// Optimal Solution:
// This two-stack approach is optimal as it satisfies the O(1) time complexity requirement for all operations while using only linear space, which is necessary to store the elements and track the minimums.
import java.util.Stack;

class MinStack {
    private Stack<Integer> stack;
    private Stack<Integer> minStack;

    public MinStack() {
        stack = new Stack<>();
        minStack = new Stack<>();
    }

    public void push(int val) {
        stack.push(val);
        if (minStack.isEmpty() || val <= minStack.peek()) {
            minStack.push(val);
        } else {
            minStack.push(minStack.peek()); 
        }
    }

    public void pop() {
        stack.pop();
        minStack.pop(); 
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }
}

