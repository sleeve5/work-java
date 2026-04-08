import java.util.LinkedList;
import java.util.Queue;

public class MessageQueue {
    private final int capacity;
    private final Queue<String> queue;

    public MessageQueue(int capacity) {
        this.capacity = capacity;
        queue = new LinkedList<>();
    }

    public synchronized void produce(String msg) throws InterruptedException {
        while (queue.size() >= capacity) {
            System.out.println("[Full Queue] The producer is waiting.");
            wait();
        }

        queue.offer(msg);
        System.out.println("[Produce Successfully] Message:" + msg + ", queue size: " + queue.size());

        notify();
    }

    public synchronized String consume() throws InterruptedException {
        while (queue.isEmpty()) {
            System.out.println("[Empty Queue] The consumer is waiting.");
            wait();
        }

        String msg = queue.poll();
        System.out.println("[Consume Successfully] Message:" + msg + ", queue size: " + queue.size());

        notify();
        return msg;
    }

    public static void main(String[] args) {
        MessageQueue mq = new MessageQueue(5);

        Thread producer = new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    mq.produce("MSG-" + i);
                    Thread.sleep(100);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }, "Producer thread");

        Thread consumer = new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    mq.consume();
                    Thread.sleep(100);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }, "Consumer thread");

        producer.start();
        consumer.start();
    }
}
