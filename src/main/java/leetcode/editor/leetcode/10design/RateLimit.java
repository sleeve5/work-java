import java.util.LinkedList;

public class RateLimit {
    private final int limit;
    private final long windowSize;
    private final LinkedList<Long> queue = new LinkedList<>();

    public RateLimit(int limit, long windowSize) {
        this.limit = limit;
        this.windowSize = windowSize;
    }

    public synchronized boolean tryAcquire() {
        long now = System.currentTimeMillis();
        while (!queue.isEmpty() && now - queue.peek() > windowSize) {
            queue.poll();
        }

        if (queue.size() < limit) {
            queue.offer(now);
            return true;
        }

        return false;
    }

    public static void main(String[] args) throws InterruptedException {
        RateLimit limiter = new RateLimit(5, 1000);

        System.out.println("===== 第一轮 10 个请求 =====");
        for (int i = 1; i <= 10; i++) {
            boolean pass = limiter.tryAcquire();
            System.out.println((pass ? "放行:" : "限流:") + i);
            Thread.sleep(100);
        }

        System.out.println("\n===== 等待1秒，窗口滑动 =====");
        Thread.sleep(1000);

        System.out.println("===== 第二轮 10 个请求 =====");
        for (int i = 1; i <= 10; i++) {
            boolean pass = limiter.tryAcquire();
            System.out.println((pass ? "放行:" : "限流:") + i);
            Thread.sleep(100);
        }
    }
}
