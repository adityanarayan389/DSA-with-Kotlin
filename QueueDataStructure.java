import java.util.Deque;
import java.util.LinkedList;

public class QueueDataStructure {

    public static void main(String[] args) {

    }

    // queue using array

    static class Queue {
        static int arr[];
        static int size;
        static int rear; 

        Queue(int size) {
            this.size = size;
            arr = new int[size];
            rear = -1;
        }

        public static boolean isEmpty() {
            return rear == -1;
        }

        public static boolean isFull() {
            return rear == size - 1;
        }

        public static void add(int data) {
            if (isFull()) {
                System.out.println("Overflow");
                return;
            }

            arr[++rear] = data;
        }

        // O(n)
        public static int remove() {
            if (isEmpty()) {
                System.out.println("empty queue");
                return -1;
            }
            int front = arr[0];
            for (int i = 0; i < rear; i++) {
                arr[i] = arr[i + 1];
            }
            rear--;

            return front;
        }

        public static int peek() {
            if (isEmpty()) {
                System.out.println("empty queue");
                return -1;
            }

            return arr[0];
        }
    }

    class MyQueue {
    private int[] arr;
    private int front, rear, size, capacity;

    public MyQueue(int capacity) {
        this.capacity = capacity;
        arr = new int[capacity];
        front = 0;
        rear = -1;
        size = 0;
    }

    // Add element
    public void enqueue(int data) {
        if (isFull()) {
            System.out.println("Queue is full!");
            return;
        }
        rear = (rear + 1) % capacity; // circular increment
        arr[rear] = data;
        size++;
    }

    // Remove element
    public int dequeue() {
        if (isEmpty()) {
            System.out.println("Queue is empty!");
            return -1;
        }
        int item = arr[front];
        front = (front + 1) % capacity; // circular increment
        size--;
        return item;
    }

    // Peek front
    public int peek() {
        if (isEmpty()) {
            System.out.println("Queue is empty!");
            return -1;
        }
        return arr[front];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == capacity;
    }

    public int getSize() {
        return size;
    }
}
//Sliding Window Maximum


public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0) return new int[0];

        int n = nums.length;
        int resIndex = 0;
        int[] result = new int[n-k+1];
        int indices =0;

        Deque<Integer> deq = new LinkedList();

        while(indices < n){
            if(!deq.isEmpty() && deq.peekFirst() <= indices - k){
                deq.pollFirst();
            }

              while(!deq.isEmpty() && nums[deq.peekLast()] < nums[indices]){
                deq.pollLast();
              }

            deq.offerLast(indices);

            if(indices >= k-1){
                 result[resIndex++] = nums[deq.peekFirst()];

            }
            indices++;


        }
        return result;
        
    }


}