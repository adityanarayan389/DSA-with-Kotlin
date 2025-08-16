import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class StackDataStructure {

    public static void main(String[] args) {

    }

    class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    class LinkedStack {
        private Node top;

        LinkedStack() {
            this.top = null;
        }

        public void push(int x) {
            Node newNode = new Node(x);
            newNode.next = top; // link new node to old top
            top = newNode; // move top to new node
        }

        public int pop() {
            if (isEmpty()) {
                System.out.println("Stack Underflow");
                return -1;
            }
            int value = top.data;
            top = top.next; // move top down one node
            return value;
        }

        public int peek() {
            if (isEmpty())
                return -1;
            return top.data;
        }

        public boolean isEmpty() {
            return top == null;
        }
    }

    class MyStack {
        private int arr[];
        private int top;
        private int capacity;

        // Constructor
        MyStack(int size) {
            arr = new int[size];
            capacity = size;
            top = -1;
        }

        // Push
        public void push(int x) {
            if (top == capacity - 1) {
                System.out.println("Stack Overflow");
                return;
            }
            arr[++top] = x;
        }

        // Pop
        public int pop() {
            if (isEmpty()) {
                System.out.println("Stack Underflow");
                return -1;
            }
            return arr[top--];
        }

        // Peek
        public int peek() {
            if (isEmpty())
                return -1;
            return arr[top];
        }

        // Check empty
        public boolean isEmpty() {
            return top == -1;
        }

        // Size
        public int size() {
            return top + 1;
        }
    }

    class MinStack {
        private Stack<Integer> mainStack;
        private Stack<Integer> minStack;

        public MinStack() {
            mainStack = new Stack<>();
            minStack = new Stack<>();
        }

        public void push(int val) {
            mainStack.push(val);
            // push into minStack if smaller or equal to current min
            if (minStack.isEmpty() || val <= minStack.peek()) {
                minStack.push(val);
            }
        }

        public void pop() {
            if (mainStack.isEmpty())
                return;
            int removed = mainStack.pop();
            // if removed element was the minimum, pop from minStack too
            if (removed == minStack.peek()) {
                minStack.pop();
            }
        }

        public int top() {
            return mainStack.peek();
        }

        public int getMin() {
            return minStack.peek();
        }
    }

    public boolean isValid(String s) {

        Stack<Character> stack = new Stack<>();

        for (char c : s.toCharArray()) {

            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else {
                if (stack.isEmpty()) {
                    return false;
                }

                if ((stack.peek() == '(' && c == ')') || (stack.peek() == '{' && c == '}')
                        || (stack.peek() == '[' && c == ']')) {
                    stack.pop();
                } else {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    public int[] nextGreaterElement(int[] nums1, int[] nums2) {

        Map<Integer, Integer> ngemap = new HashMap();
        Stack<Integer> stack = new Stack();

        for (int num : nums2) {

            while (!stack.isEmpty() && stack.peek() < num) {
                ngemap.put(stack.pop(), num);
            }

            stack.push(num);
        }

        while (!stack.isEmpty()) {
            ngemap.put(stack.pop(), -1);
        }

        int[] result = new int[nums1.length];

        for (int i = 0; i < nums1.length; i++) {
            result[i] = ngemap.get(nums1[i]);
        }
        return result;
    }

    // Evaluate Reverse Polish Notation

    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();

        for (String token : tokens) {
            if (token.equals("+")) {
                stack.push(stack.pop() + stack.pop());
            } else if (token.equals("-")) {
                int b = stack.pop();
                int a = stack.pop();
                stack.push(a - b);
            } else if (token.equals("*")) {
                stack.push(stack.pop() * stack.pop());
            } else if (token.equals("/")) {
                int b = stack.pop();
                int a = stack.pop();
                stack.push(a / b); // integer division (truncated toward zero)
            } else {
                stack.push(Integer.parseInt(token));
            }
        }

        return stack.pop();
    }

}
