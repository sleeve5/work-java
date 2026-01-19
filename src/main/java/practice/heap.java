package practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class heap {
    List<Integer> maxHeap = new ArrayList<>();

    int left(int i) {
        return 2 * i + 1;
    }

    int right(int i) {
        return 2 * i + 2;
    }

    int parent(int i) {
        return (i - 1) / 2;
    }

    int peek() {
        if (maxHeap.isEmpty()) {
            throw new IndexOutOfBoundsException();
        }
        return maxHeap.get(0);
    }

    int size() {
        return maxHeap.size();
    }

    boolean isEmpty() {
        return maxHeap.isEmpty();
    }

    void swap(int a, int b) {
        int temp = maxHeap.get(a);
        maxHeap.set(a, maxHeap.get(b));
        maxHeap.set(b, temp);
    }

    void siftup(int i) {
        while (true) {
            int p = parent(i);
            if (p < 0 || maxHeap.get(i) <= maxHeap.get(p)) {
                break;
            }
            swap(p, i);
            i = p;
        }
    }

    void siftdown(int i) {
        while (true) {
            int l = left(i), r = right(i), ma = i;
            if (l < size() && maxHeap.get(l) > maxHeap.get(ma)) {
                ma = l;
            }
            if (r < size() && maxHeap.get(r) > maxHeap.get(ma)) {
                ma = r;
            }
            if (ma == i) {
                break;
            }
            swap(ma, i);
            i = ma;
        }
    }

    void push(int num) {
        maxHeap.add(num);
        siftup(size() - 1);
    }

    int pop() {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException();
        }
        swap(0, size() - 1);
        int val = maxHeap.remove(size() - 1);
        siftdown(0);
        return val;
    }

    Queue<Integer> topKHeap(int[] nums, int k) {
        Queue<Integer> heap = new PriorityQueue<Integer>();
        for (int i = 0; i < k; i++) {
            heap.offer(nums[i]);
        }
        for (int i = k; i < nums.length; i++) {
            if (nums[i] > heap.peek()) {
                heap.poll();
                heap.offer(nums[i]);
            }
        }
        return heap;
    }

    public static void main(String[] args) {
        Queue<Integer> minHeap = new PriorityQueue<>();
        Queue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);

        maxHeap.offer(1);
        maxHeap.offer(2);
        maxHeap.offer(3);
        maxHeap.offer(4);

        int peek = maxHeap.peek();

        peek = maxHeap.poll();

        int size = maxHeap.size();

        boolean isEmpty = maxHeap.isEmpty();

        minHeap = new PriorityQueue<>(Arrays.asList(1, 3, 2, 5, 4));

    }
}
