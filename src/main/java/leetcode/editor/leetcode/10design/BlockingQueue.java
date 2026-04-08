import java.util.LinkedList;
import java.util.Queue;

public class BlockingQueue {
    private final int capacity;
    private final Queue<String> queue;

    public BlockingQueue(int capacity) {
        this.capacity = capacity;
        queue = new LinkedList<>();
    }

    public synchronized void put(String msg) throws InterruptedException {
        while (queue.size() >= capacity) {
            System.out.println("Queue id full, put blocking.");
            wait();
        }

        queue.offer(msg);
        System.out.println("Put: " + msg + ", queue size: " + queue.size());

        notify();
    }

    public synchronized String take() throws InterruptedException {
        while (queue.isEmpty()) {
            System.out.println("Queue is empty, take blocking.");
            wait();
        }

        String msg = queue.poll();
        System.out.println("Take: " + msg + ", queue size: " + queue.size());

        notify();

        return msg;
    }

    public static void main(String[] args) {
        BlockingQueue queue = new BlockingQueue(5);

        Thread producer = new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    queue.put("MSG-" + i);
                    Thread.sleep(100);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }, "producer");

        Thread consumer = new Thread(() -> {
            try {
                Thread.sleep(1000);
                for (int i = 0; i < 10; i++) {
                    queue.take();
                    Thread.sleep(200);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }, "consumer");

        producer.start();
        consumer.start();
    }
}
