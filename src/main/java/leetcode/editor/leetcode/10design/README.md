# 设计模式题目分题型总结

> 本总结涵盖 10design 文件夹中的所有设计模式相关题目

---

## 核心概念：设计模式

设计模式是软件工程中**针对常见问题的通用可复用解决方案**。按照目的可分为：
- **创建型**：解决对象创建问题
- **结构型**：解决对象组合问题
- **行为型**：解决对象交互问题

在面试中最常考察的是**并发设计模式**和**数据结构设计**。

---

## 设计模式分类

| 分类 | 题目 | 核心要点 |
| ------ | --------- | ---------- |
| **创建型** | 1. 单例模式 | 双重检查锁定、volatile |
| **结构型** | 2. LRU 缓存 | HashMap + 双向链表 |
| **结构型** | 3. 跳表 | 多层链表 + 随机层数 |
| **并发-同步** | 4. 阻塞队列 | synchronized + wait/notify |
| **并发-协调** | 5. 三线程打印 | state + wait/notifyAll |
| **并发-资源池** | 6. 连接池 | BlockingQueue + 复用 |
| **并发-限流** | 7. 滑动窗口限流 | LinkedList + 时间窗口 |
| **并发-生产者消费者** | 8. 生产者消费者 | Condition 实现 |
| **并发-线程池** | 9. 线程池 | HashSet + BlockingQueue |
| **行为型** | 10. 观察者模式 | Subject + Observer |
| **行为型** | 11. 发布订阅模式 | PubSub |

---

## 一、创建型设计模式

### 1.1 单例模式（Singleton）

**题目特点**：确保一个类只有一个实例，并提供全局访问点。

**核心思想**：
- 私有构造函数防止外部实例化
- 双重检查锁定确保线程安全
- volatile 防止指令重排序

**关键点**：
- `volatile` 修饰实例，防止指令重排序
- 两次 `null` 检查，第一次提高性能，第二次确保线程安全
- `synchronized` 加在创建逻辑上，减少锁竞争

**代码模板**：
```java
// Singleton.java
public class Singleton {
    private static volatile Singleton instance;

    private Singleton() {
    }

    public static Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }

        return instance;
    }
}
```

**面试追问**：
- 为什么需要 `volatile`？（防止指令重排序导致对象未完全构造就被访问）
- 为什么不把 `synchronized` 加在方法上？（减少锁竞争，提高性能）
- 饿汉式 vs 懒汉式？（饿汉式：类加载时创建，无线程安全问题；懒汉式：延迟加载，但需考虑线程安全）

---

## 二、结构型设计模式

### 2.1 LRU 缓存（146. LRU 缓存）

**题目描述**：设计一个最近最少使用（Least Recently Used）缓存，支持 O(1) 时间复杂度的 get 和 put 操作。

**核心思想**：
- 使用 HashMap 存储 key-node 映射，实现 O(1) 查找
- 使用双向链表维护访问顺序，O(1) 的添加和删除
- 最近使用的放在链表头部，最久未使用的在尾部

**关键点**：
- `HashMap<Integer, Node>`：快速查找，O(1) 时间复杂度
- 双向链表：维护访问顺序，快速移动和删除节点
- 虚拟头尾节点：简化边界处理
- get 操作后将该节点移到头部（表示最近使用）
- put 操作时，如果超容量，删除尾部节点（最久未使用）

**代码模板**：
```java
// lru-cache.java
import java.util.HashMap;
import java.util.Map;

class LRUCache {
    class Node {
        int key, value;
        Node prev, next;

        Node(int k, int v) {
            this.key = k;
            this.value = v;
        }
    }

    private final int capacity;
    private final Map<Integer, Node> map = new HashMap<>();
    private final Node head, tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        head = new Node(0, 0);
        tail = new Node(0, 0);
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        if (!map.containsKey(key))
            return -1;
        Node node = map.get(key);
        moveToHead(node);
        return node.value;
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            Node node = map.get(key);
            node.value = value;
            moveToHead(node);
        } else {
            if (map.size() == capacity) {
                map.remove(tail.prev.key);
                removeNode(tail.prev);
            }
            Node newNode = new Node(key, value);
            map.put(key, newNode);
            addToHead(newNode);
        }
    }

    private void removeNode(Node n) {
        n.prev.next = n.next;
        n.next.prev = n.prev;
    }

    private void addToHead(Node n) {
        n.next = head.next;
        n.prev = head;
        head.next.prev = n;
        head.next = n;
    }

    private void moveToHead(Node n) {
        removeNode(n);
        addToHead(n);
    }
}
```

**面试追问**：
- LinkedHashMap 如何实现 LRU？（accessOrder=true 时，每次 access 都会将节点移到链表尾部）
- Redis 中 LRU 和 LFU 的区别？（LRU：最近最少使用；LFU：最不经常使用）
- 为什么不用 LinkedHashMap 直接实现？（可以，但面试官想看到你手写双向链表）

---

### 2.2 跳表（skiplist）

**题目特点**：一种多层链表结构，通过随机层数实现近似 O(log n) 的查找、插入、删除。

**核心思想**：
- 多层链表，每层都是有序链表
- 查找时从高层开始，逐步向低层查找
- 插入时随机决定层数（抛硬币决定）
- 越高层节点越少，查找跳过越多节点

**关键点**：
- `DEFAULT_MAX_LEVEL = 32`：最大层数
- `DEFAULT_P_FACTOR = 0.25`：概率因子，决定层数增长
- `findClosest()`：在某一层找到最接近目标值的节点
- 高层链表节点稀疏，适合快速定位

**代码模板**：
```java
// skiplist.java
import java.util.Random;

class skiplist {
    static class Node {
        Integer value;
        Node[] next;

        public Node(Integer value, int size) {
            this.value = value;
            this.next = new Node[size];
        }

        public String toString() {
            return String.valueOf(value);
        }
    }

    private static int DEFAULT_MAX_LEVEL = 32;
    private static double DEFAULT_P_FACTOR = 0.25;

    Node head = new Node(null, DEFAULT_MAX_LEVEL);
    int currentLevel = 1;

    public skiplist() {
    }

    public boolean search(int target) {
        Node searchNode = head;
        for (int i = currentLevel - 1; i >= 0; i--) {
            searchNode = findClosest(searchNode, i, target);
            if (searchNode.next[i] != null && searchNode.next[i].value == target) {
                return true;
            }
        }
        return false;
    }

    public void add(int num) {
        int level = randomLevel();
        Node updateNode = head;
        Node newNode = new Node(num, level);

        for (int i = currentLevel - 1; i >= 0; i--) {
            updateNode = findClosest(updateNode, i, num);
            if (i < level) {
                Node temp = updateNode.next[i];
                updateNode.next[i] = newNode;
                newNode.next[i] = temp;
            }
        }

        if (level > currentLevel) {
            for (int i = currentLevel; i < level; i++) {
                head.next[i] = newNode;
            }
            currentLevel = level;
        }
    }

    public boolean erase(int num) {
        boolean flag = false;
        Node searchNode = head;
        for (int i = currentLevel - 1; i >= 0; i--) {
            searchNode = findClosest(searchNode, i, num);
            if (searchNode.next[i] != null && searchNode.next[i].value == num) {
                searchNode.next[i] = searchNode.next[i].next[i];
                flag = true;
            }
        }
        return flag;
    }

    private Node findClosest(Node node, int levelIndex, int value) {
        while ((node.next[levelIndex]) != null && value > node.next[levelIndex].value) {
            node = node.next[levelIndex];
        }
        return node;
    }

    private static int randomLevel() {
        int level = 1;
        while (Math.random() < DEFAULT_P_FACTOR && level < DEFAULT_MAX_LEVEL) {
            level++;
        }
        return level;
    }

    public static void main(String[] args) {
        skiplist list = new skiplist();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        System.out.println("查找 3：" + list.search(3));
        System.out.println("查找 7：" + list.search(7));
        System.out.println("删除 3：" + list.erase(3));
        System.out.println("再次查找 3：" + list.search(3));
        System.out.println("删除 6：" + list.erase(6));
        System.out.println("查找 6：" + list.search(6));
    }
}
```

**面试追问**：
- 跳表和红黑树的区别？（跳表：有序、范围查询简单、实现简单；红黑树：更稳定、内存占用低）
- 跳表的时间复杂度？（查找、插入、删除都是 O(log n)）
- Redis 为何用跳表而不是红黑树？（Redis ZSET 同时需要范围查询和排序，跳表更简单，区间查找高效）

---

## 三、并发设计模式

### 3.1 阻塞队列（BlockingQueue）

**题目特点**：队列满时阻塞写操作，队列空时阻塞读操作。

**核心思想**：
- 使用 `synchronized` 保证线程安全
- 使用 `wait()/notify()` 实现阻塞和唤醒
- `while` 循环判断条件，防止虚假唤醒

**关键点**：
- `put()`：队列满时等待，直到被唤醒
- `take()`：队列空时等待，直到被唤醒
- 操作完成后 `notify()` 唤醒等待的线程

**代码模板**：
```java
// BlockingQueue.java
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
```

**面试追问**：
- 为什么用 `while` 而不是 `if`？（防止虚假唤醒）
- `notify()` vs `notifyAll()`？（唤醒一个 vs 唤醒所有等待线程）

---

### 3.2 三线程按序打印（ThreeThread）

**题目描述**：三个线程交替打印 A、B、C，实现顺序输出。

**核心思想**：
- 使用共享状态 `state` 记录当前应该打印的线程
- 每个线程判断 `state % 3` 是否为自己对应的值
- 打印后更新状态并唤醒所有线程

**关键点**：
- `state % 3 == 0` → 打印 A
- `state % 3 == 1` → 打印 B
- `state % 3 == 2` → 打印 C
- `notifyAll()` 唤醒所有等待线程，让下一个线程有机会执行

**代码模板**：
```java
// ThreeThread.java
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
```

**面试追问**：
- 如何扩展为 N 个线程交替打印？（修改 state % n 判断）
- `notify()` 和 `notifyAll()` 该用哪个？（当不确定等待的是哪类线程时，用 `notifyAll()` 更安全）
- 为什么要在 finally 中处理中断？（确保锁能正确释放）

---

### 3.3 连接池（ConnectionPool）

**题目特点**：预先创建连接，使用后归还池中，实现连接的复用。

**核心思想**：
- 使用 `BlockingQueue` 存储可用连接
- 借出时从队列取连接，超时抛出异常
- 归还时放回队列，队列满则关闭连接

**关键点**：
- `ArrayBlockingQueue`：有界队列，固定容量
- `poll(timeoutMs, TimeUnit)`：超时等待获取
- 连接池大小固定，避免频繁创建销毁

**代码模板**：
```java
// ConnectionPool.java
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
```

**面试追问**：
- 连接池大小如何确定？（根据预估并发量、数据库连接上限、CPU 核心数等因素调整）
- 连接泄漏如何处理？（设置借出超时，定期清理空闲连接，记录连接状态）
- 为什么不自己实现，用数据库连接池不好吗？（数据库厂商的实现经过大量测试，更稳定）

---

### 3.4 滑动窗口限流（RateLimit）

**题目特点**：限制接口调用频率，保护系统资源。使用滑动窗口算法，统计窗口内的请求数。

**核心思想**：
- 使用 LinkedList 存储请求时间戳
- 每次请求时清理超过窗口大小的过期记录
- 窗口内请求数小于限制则放行，否则限流

**关键点**：
- `LinkedList<Long>`：存储请求时间戳
- `windowSize`：滑动窗口大小（毫秒）
- 每次 `tryAcquire()` 都动态清理过期请求，实现真正的滑动窗口

**代码模板**：
```java
// RateLimit.java
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
```

**限流算法对比**：

| 算法 | 原理 | 优点 | 缺点 |
| ------ | ------ | ------ | ------ |
| 令牌桶 | 按固定速率发放令牌 | 支持突发流量 | 实现复杂 |
| 漏桶 | 按固定速率处理请求 | 流量平滑 | 无法应对突发 |
| 固定窗口 | 时间窗口计数 | 简单 | 边界流量问题 |
| 滑动窗口 | 滚动时间窗口 + 清理过期 | 精度高 | 存储开销 |

**面试追问**：
- 滑动窗口和固定窗口的区别？（固定窗口在窗口边界有突增风险；滑动窗口通过不断清理过期记录实现平滑限流）
- 令牌桶和漏桶的区别？（令牌桶：允许突发，匀速发放令牌；漏桶：匀速处理，超流速拒绝）
- 分布式限流怎么做？（Redis + Lua 脚本实现计数器或令牌桶）

---

### 3.5 多线程生产者消费者（MultiPC）

**题目特点**：使用 `ReentrantLock` 和 `Condition` 实现更精确的线程协作。

**核心思想**：
- `ReentrantLock` 可重入锁替代 `synchronized`
- `Condition` 实现精确的等待和唤醒
- `notFull.await()` 等待队列不满
- `notEmpty.signal()` 通知队列不空

**关键点**：
- `lock.lock()` 获取锁
- `lock.unlock()` 释放锁（在 finally 中）
- `condition.await()` 等待条件
- `condition.signal()` 唤醒一个等待的线程

**代码模板**：
```java
// MultiPC.java
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
```

**synchronized vs ReentrantLock**：

| 特性 | synchronized | ReentrantLock |
| ------ | ------ | ------ |
| 锁获取 | 自动获取/释放 | 手动 lock/unlock |
| 公平性 | 非公平 | 可公平/非公平 |
| 条件变量 | 单一 wait/notify | 多个 Condition |
| 中断响应 | 不可中断 | 可中断 |

**面试追问**：
- 为什么需要多个 Condition？（一个线程可能等待多个条件，例如读线程等待数据可读或队列关闭）
- `signal()` 和 `signalAll()` 区别？（signal 唤醒一个，signalAll 唤醒所有）
- await 和 wait 区别？（await 响应中断，wait 不响应）

---

### 3.6 线程池（ThreadPool）

**题目特点**：管理工作线程，复用线程执行多个任务。

**核心思想**：
- 使用 `HashSet` 存储工作线程
- 核心线程数固定，超出的任务加入队列
- 队列满则拒绝任务

**关键点**：
- 线程数量 < 核心线程数：创建新 Worker
- 线程数量 >= 核心线程数：加入任务队列
- 任务队列满：拒绝任务

**代码模板**：
```java
// ThreadPool.java
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
```

**面试追问**：
- Java 自带线程池有哪些？（FixedThreadPool、CachedThreadPool、ScheduledThreadPool、SingleThreadExecutor）
- 线程池的拒绝策略？（AbortPolicy：抛异常；CallerRunsPolicy：调用者执行；DiscardPolicy：丢弃；DiscardOldestPolicy：丢弃最老的）
- 线程池参数如何调优？（IO 密集型：CPU 核心数 * 2；CPU 密集型：CPU 核心数 + 1）

---

## 四、行为型设计模式

### 4.1 观察者模式（Observer Pattern）

**题目特点**：定义对象间一对多依赖关系，当一个对象状态改变时，所有依赖它的对象收到通知。

**核心思想**：
- `Subject`：主题，维护观察者列表，状态变化时通知
- `Observer`：观察者，直接定义更新方法

**关键点**：
- `attach()`：添加观察者
- `detach()`：移除观察者
- `notify()`：遍历所有观察者并更新

**代码模板**：
```java
// ObserverPattern.java
import java.util.LinkedList;
import java.util.List;

class Observer {
    String name;

    public Observer(String name) {
        this.name = name;
    }

    public void update(String msg) {
        System.out.println(name + " : " + msg);
    }
}

class Subject {
    private List<Observer> observers = new LinkedList<>();

    public void attach(Observer observer) {
        observers.add(observer);
    }

    public void detach(Observer observer) {
        observers.remove(observer);
    }

    public void notify(String msg) {
        for (Observer observer : observers) {
            observer.update(msg);
        }
    }
}

public class ObserverPattern {
    public static void main(String[] args) {
        Subject subject = new Subject();

        Observer o1 = new Observer("o1");
        Observer o2 = new Observer("o2");
        Observer o3 = new Observer("o3");

        subject.attach(o1);
        subject.attach(o2);
        subject.attach(o3);

        System.out.println("============");
        subject.notify("alive");

        System.out.println("\n============");
        subject.detach(o2);
        subject.notify("alive");
    }
}
```

**面试追问**：
- 观察者模式的应用场景？（事件监听系统：GUI 事件、消息推送、数据库变更通知）
- 观察者模式和发布订阅模式的区别？（观察者：主题和观察者直接通信；发布订阅：增加消息中间件，解耦更彻底）
- Java 内置的观察者模式？（java.util.Observable 和 java.util.Observer，但已废弃，推荐使用 PropertyChangeSupport）

---

### 4.2 发布订阅模式（PubSub）

**题目特点**：一种消息传递模式，发布者发送消息到主题，订阅者订阅主题接收消息，实现完全解耦。

**核心思想**：
- `Topic → Set<Subscriber>`：一个主题对应多个订阅者
- `subscribe()`：订阅主题
- `unsubscribe()`：取消订阅
- `publish()`：向主题发布消息，通知所有订阅者

**关键点**：
- `ConcurrentHashMap`：线程安全的主题管理
- `CopyOnWriteArraySet`：读多写少场景，适合发布订阅
- `computeIfAbsent()`：原子性地创建主题和订阅者集合

**代码模板**：
```java
// PubSub.java
import java.util.*;
import java.util.concurrent.*;

class Subscriber {
    private String name;

    public Subscriber(String name) {
        this.name = name;
    }

    public void onMessage(String topic, Object message) {
        System.out.println("【" + name + "】收到来自 " + topic + " 的消息: " + message);
    }

    public String getName() {
        return name;
    }
}

public class PubSub {
    private final Map<String, Set<Subscriber>> topics = new ConcurrentHashMap<>();

    public void subscribe(String topic, Subscriber subscriber) {
        if (!topics.containsKey(topic)) {
            topics.put(topic, new CopyOnWriteArraySet<>());
        }

        topics.get(topic).add(subscriber);
        System.out.println(subscriber.getName() + " subscribed: " + topic);
    }

    public void unsubscribe(String topic, Subscriber subscriber) {
        Set<Subscriber> subscribers = topics.get(topic);

        if (subscribers.contains(subscriber)) {
            subscribers.remove(subscriber);
        } else {
            System.out.println("not exist");
        }
    }

    public void publish(String topic, String message) {
        Set<Subscriber> subscribers = topics.get(topic);

        if (subscribers == null || subscribers.isEmpty()) {
            System.out.println("主题 " + topic + " 暂无订阅者");
            return;
        }

        for (Subscriber sub : subscribers) {
            sub.onMessage(topic, message);
        }
    }

    public static void main(String[] args) {
        PubSub bus = new PubSub();

        Subscriber sub1 = new Subscriber("sub1");
        Subscriber sub2 = new Subscriber("sub2");

        bus.subscribe("topic1", sub1);
        bus.subscribe("topic2", sub2);
        bus.subscribe("topic2", sub1);

        bus.publish("topic1", "111");
        bus.publish("topic2", "222");

        bus.unsubscribe("topic1", sub1);
    }
}
```

**面试追问**：
- 发布订阅和观察者模式的区别？（观察者：主题和观察者直接通信；发布订阅：增加消息中间件（如 MQ），支持更复杂的路由和解耦）
- 发布订阅模式的应用场景？（消息队列、事件总线、实时通知系统）
- 为什么用 CopyOnWriteArraySet？（读操作远多于写操作，遍历比修改更频繁）

---

## 五、总结表格

### 设计模式分类汇总

| 分类 | 题目 | 核心技巧 | 应用场景 |
| ------ | --------- | ---------- | ---------- |
| **创建型** | 单例模式 | 双重检查锁定 + volatile | 全局唯一实例 |
| **结构型** | LRU 缓存 | HashMap + 双向链表 | 缓存淘汰策略 |
| **结构型** | 跳表 | 多层链表 + 随机层数 | 有序查找 O(log n) |
| **并发-同步** | 阻塞队列 | synchronized + wait/notify | 生产者消费者 |
| **并发-协调** | 三线程打印 | state + notifyAll | 多线程协作 |
| **并发-资源池** | 连接池 | BlockingQueue + 复用 | 数据库连接管理 |
| **并发-限流** | 滑动窗口限流 | LinkedList + 时间窗口 | 接口限流保护 |
| **并发-生产者消费者** | 多线程 PC | ReentrantLock + Condition | 异步任务处理 |
| **并发-线程池** | 线程池 | HashSet + BlockingQueue | 任务队列管理 |
| **行为型** | 观察者模式 | Subject + Observer | 事件监听系统 |
| **行为型** | 发布订阅模式 | PubSub + ConcurrentHashMap | 消息传递系统 |

### 核心技巧速查

| 场景 | 解决方案 | 关键类/方法 |
| ------ | ---------- | ---------- |
| 线程安全单例 | 双重检查锁定 | volatile + synchronized |
| O(1) 缓存查找 | HashMap + 双向链表 | LRUCache |
| O(log n) 有序查找 | 多层链表 + 随机层数 | skiplist |
| 阻塞读写 | wait/notify | BlockingQueue |
| 精确线程协作 | Condition | MultiPC |
| 线程池管理 | 核心线程 + 队列 | ThreadPool |
| 限流保护 | 时间窗口 | RateLimit |
| 连接复用 | 连接池 | ConnectionPool |
| 消息发布订阅 | ConcurrentHashMap | PubSub |

---

## 六、常见面试追问

### 1. synchronized 和 ReentrantLock 区别？
- synchronized 是 JVM 内置锁，自动获取释放
- ReentrantLock 是显式锁，需要手动 unlock
- ReentrantLock 支持公平锁、可中断、多个条件变量

### 2. HashMap 和 LinkedHashMap 区别？
- HashMap：不保证顺序
- LinkedHashMap：维护插入顺序或访问顺序，可直接实现 LRU

### 3. 生产者消费者模式的应用场景？
- 异步任务处理
- 日志收集系统
- 消息队列

### 4. 线程池的核心参数？
- corePoolSize：核心线程数
- maximumPoolSize：最大线程数
- keepAliveTime：空闲线程存活时间
- workQueue：任务队列
- threadFactory：线程工厂
- handler：拒绝策略
