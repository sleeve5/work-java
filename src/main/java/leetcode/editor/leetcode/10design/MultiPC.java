import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class MultiPC<T> {
    private final int capacity;
    private final Queue<T> queue = new LinkedList<>();
    private final ReentrantLock lock = new ReentrantLock();
    private final Condition notFull = lock.newCondition();
    private final Condition notEmpty = lock.newCondition();

    public MultiPC(int capacity) {
        this.capacity = capacity;
    }

    public void produce(T t) throws InterruptedException {
        lock.lock();

        try {
            while (queue.size() == capacity) {
                notFull.await();
                System.out.println("queue full, producer waiting");
            }

            queue.offer(t);
            System.out.println("produce msg: " + t);
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    public T consume() throws InterruptedException {
        lock.lock();

        try {
            while (queue.isEmpty()) {
                notEmpty.await();
                System.out.println("queue empty, consumer waiting");
            }

            T t = queue.poll();
            System.out.println("consume msg: " + t);
            notFull.signal();
            return t;
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        MultiPC<Integer> queue = new MultiPC<>(5);

        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                try {
                    for (int j = 0; j < 8; j++) {
                        queue.produce(j);
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }, "P-" + i).start();
        }

        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                try {
                    for (int j = 0; j < 8; j++) {
                        queue.consume();
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }).start();
        }
    }
}
