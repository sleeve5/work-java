public class ThreeThread {
    private int state;
    private static final int COUNT = 3;

    public synchronized void printA() {
        try {
            while (state % 3 != 0) {
                wait();
            }

            System.out.println("A");
            state++;
            notifyAll();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public synchronized void printB() {
        try {
            while (state % 3 != 1) {
                wait();
            }

            System.out.println("B");
            state++;
            notifyAll();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public synchronized void printC() {
        try {
            while (state % 3 != 2) {
                wait();
            }

            System.out.println("C");
            state++;
            notifyAll();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void main(String[] args) {
        ThreeThread print = new ThreeThread();

        new Thread(() -> {
            for (int i = 0; i < COUNT; i++) {
                print.printA();
            }
        }, "A").start();

        new Thread(() -> {
            for (int i = 0; i < COUNT; i++) {
                print.printB();
            }
        }, "B").start();

        new Thread(() -> {
            for (int i = 0; i < COUNT; i++) {
                print.printC();
            }
        }, "C").start();
    }
}
