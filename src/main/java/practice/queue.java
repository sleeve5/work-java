package practice;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

import leetcode.editor.common.ListNode;

public class queue {
    class LinkedListQueue {
        private ListNode front, rear;
        private int queSize = 0;

        public LinkedListQueue() {
            front = null;
            rear = null;
        }

        public int size() {
            return queSize;
        }

        public boolean isEmpty() {
            return queSize == 0;
        }

        public int peek() {
            if (isEmpty()) {
                throw new IndexOutOfBoundsException();
            }
            return front.val;
        }

        public void push(int val) {
            ListNode newNode = new ListNode(val);
            if (isEmpty()) {
                front = newNode;
                rear = newNode;
            } else {
                rear.next = newNode;
                rear = newNode;
            }
            queSize++;
        }

        public int pop() {
            int num = peek();
            front = front.next;
            queSize--;
            return num;
        }

        public int[] toArray() {
            ListNode node = front;
            int[] res = new int[size()];
            for (int i = 0; i < res.length; i++) {
                res[i] = node.val;
                node = node.next;
            }
            return res;
        }
    }

    class ArrayQueue {
        private int[] arr;
        private int front;
        private int queueSize;

        public ArrayQueue(int capacity) {
            arr = new int[capacity];
            front = queueSize = 0;
        }

        public int capacity() {
            return arr.length;
        }

        public int size() {
            return queueSize;
        }

        public boolean isEmpty() {
            return size() == 0;
        }

        public int peek() {
            if (isEmpty()) {
                throw new IndexOutOfBoundsException("The Queue Is Empty!");
            }
            return arr[front];
        }

        public void push(int num) {
            if (size() == capacity()) {
                throw new IndexOutOfBoundsException("The Queue Is Full!");
            }
            int rear = (front + queueSize) % capacity();
            arr[rear] = num;
            queueSize++;
        }

        public int pop() {
            int num = peek();
            front = (front + 1) % capacity();
            queueSize--;
            return num;
        }

        public int[] toArray() {
            int[] res = new int[queueSize];
            for (int i = 0; i < queueSize; i++) {
                res[i] = arr[(front + i) % capacity()];
            }
            return res;
        }
    }

    public static void main(String[] args) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(1);
        queue.offer(2);
        queue.offer(3);

        int peek = queue.peek();
        int pop = queue.poll();
        int size = queue.size();
        boolean isEmpty = queue.isEmpty();

    }

}
