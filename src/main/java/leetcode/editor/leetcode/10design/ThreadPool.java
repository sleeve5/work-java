import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class ThreadPool {
    private final int coreSize;
    private final Set<Worker> workers = new HashSet<>();
    private final BlockingQueue<Runnable> taskQueue;

    public ThreadPool(int coreSize, int queueSize) {
        this.coreSize = coreSize;
        this.taskQueue = new LinkedBlockingDeque<>(queueSize);
    }

    public void execute(Runnable task) {
        synchronized (workers) {
            if (workers.size() < coreSize) {
                Worker worker = new Worker(task);
                workers.add(worker);
                worker.thread.start();
            } else {
                if (!taskQueue.offer(task)) {
                    System.out.println("Queue is full, refused.");
                }
            }
        }
    }

    private class Worker implements Runnable {
        private Runnable firstTask;
        private Thread thread;

        public Worker(Runnable task) {
            this.firstTask = task;
            this.thread = new Thread(this);
        }

        @Override
        public void run() {
            Runnable task = firstTask;
            firstTask = null;
            try {
                while (task != null || (task = taskQueue.take()) != null) {
                    try {
                        task.run();
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        task = null;
                    }
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public static void main(String[] args) {
        ThreadPool pool = new ThreadPool(3, 10);

        for (int i = 0; i < 20; i++) {
            int taskId = i;

            pool.execute(() -> {
                System.out.println(Thread.currentThread().getName() + " execute " + taskId);

                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }

                System.out.println(Thread.currentThread().getName() + " finish " + taskId);
            });
        }
    }

}
