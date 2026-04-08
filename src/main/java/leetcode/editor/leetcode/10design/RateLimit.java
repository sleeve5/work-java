public class RateLimit {
    private long timeStamp = System.currentTimeMillis();
    private int count = 0;
    private final int limit = 100;
    private final long internal = 1000;

    public synchronized boolean grant() {
        long now = System.currentTimeMillis();

        if (now < timeStamp + internal) {
            if (count < limit) {
                count++;
                return true;
            } else {
                return false;
            }
        } else {
            timeStamp = now;
            count = 1;
            return true;
        }
    }
}
