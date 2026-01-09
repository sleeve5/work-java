package practice;

import java.util.Deque;
import java.util.LinkedList;

public class deque {
    class ArrayDeque {
        private int[] arr;
        private int front;
        private int queSize;

        public ArrayDeque(int capacity) {
            this.arr = new int[capacity];
            this.front = 0;
            this.queSize = 0;
        }

        public int capacity() {
            return arr.length;
        }

        public int size() {
            return queSize;
        }

        public boolean isEmpty() {
            return queSize == 0;
        }

        public boolean isFull() {
            return arr.length == queSize;
        }

        public int peekFirst() {
            if (isEmpty()) {
                throw new IndexOutOfBoundsException();
            }
            return arr[front];
        }

        public int peekLast() {
            if (isEmpty()) {
                throw new IndexOutOfBoundsException();
            }
            int rear = (front + queSize - 1 + capacity()) % capacity();
            return arr[rear];
        }

        public void pushFirst(int num) {
            if (isFull()) {
                throw new IndexOutOfBoundsException();
            }
            front = (front - 1 + capacity()) % capacity();
            arr[front] = num;
            queSize++;
        }

        public void pushLast(int num) {
            if (isFull()) {
                throw new IndexOutOfBoundsException();
            }
            int rear = (front + queSize) % capacity();
            arr[rear] = num;
            queSize++;
        }

        public int popFirst() {
            if (isEmpty()) {
                throw new IndexOutOfBoundsException();
            }
            int num = peekFirst();
            front = (front + 1) % capacity();
            queSize--;
            return num;
        }

        public int popLast() {
            if (isEmpty()) {
                throw new IndexOutOfBoundsException();
            }
            int num = peekLast();
            queSize--;
            return num;
        }

        public int[] toArray() {
            int[] res = new int[queSize];
            for (int i = 0; i < queSize; i++) {
                res[i] = arr[(front + i) % capacity()];
            }
            return res;
        }
    }

    public static void main(String[] args) {
        Deque<Integer> deque = new LinkedList<>();
        deque.offerFirst(2);
        deque.offerFirst(1);
        deque.offerLast(3);
        deque.offerLast(4);

        int peekFirst = deque.peekFirst();
        int peekLast = deque.peekLast();

        int popFirst = deque.pollFirst();
        int popLast = deque.pollLast();

        int size = deque.size();

        boolean isEmpty = deque.isEmpty();
    }
}
