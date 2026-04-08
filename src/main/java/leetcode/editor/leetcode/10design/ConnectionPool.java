import java.sql.*;
import java.util.concurrent.*;
import java.util.concurrent.BlockingQueue;

public class ConnectionPool {
    private final BlockingQueue<Connection> pool;
    private final int poolSize;

    private static final String URL = "jdbc:mysql://localhost:3306/test";
    private static final String USER = "root";
    private static final String PASSWORD = "123456";

    public ConnectionPool(int poolSize) {
        this.poolSize = poolSize;
        this.pool = new ArrayBlockingQueue<>(poolSize);

        for (int i = 0; i < poolSize; i++) {
            Connection conn;
            try {
                conn = createConnection();
            } catch (SQLException e) {
                throw new RuntimeException("create failed", e);
            }
            pool.add(conn);
        }
    }

    public Connection borrowConnection(long timeoutMs) throws InterruptedException {
        Connection conn = pool.poll(timeoutMs, TimeUnit.MILLISECONDS);
        if (conn == null) {
            throw new RuntimeException("timeout");
        }

        return conn;
    }

    public void releaseConnection(Connection conn) {
        if (conn != null) {
            if (!pool.offer(conn)) {
                closeConnection(conn);
            }
        }
    }

    private Connection createConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    private void closeConnection(Connection conn) {
        try {
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException("close failed", e);
        }
    }
}
