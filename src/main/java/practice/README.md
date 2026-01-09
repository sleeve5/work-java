# Java数据结构与算法基础

## 简介

这是一个Java数据结构与算法的基础练习项目，适合初学者学习和实践。项目包含了常见的数据结构和算法实现，帮助你掌握Java编程和数据结构的基本概念。

## 项目结构

```
practice/
├── loop.java           # 循环练习
├── array.java          # 数组操作
├── linkedlist.java     # 链表操作
├── list.java           # 列表操作
├── stack.java          # 栈操作
├── queue.java          # 队列操作
├── deque.java          # 双端队列
└── README.md           # 项目说明
```

## 学习内容

### 1. 循环练习 (loop.java)

**学习目标**：掌握Java中的循环结构

**核心知识点**：

- for循环
- while循环
- do-while循环
- 循环嵌套

**关键代码解析**：

```java
// for循环
for (int i = 0; i < 10; i++) {
    System.out.println(i);
}

// while循环
int i = 0;
while (i < 10) {
    System.out.println(i);
    i++;
}

// do-while循环
int j = 0;
do {
    System.out.println(j);
    j++;
} while (j < 10);
```

**时间复杂度分析**：

- 单层循环：O(n)
- 双层循环：O(n^2)
- 多层循环：O(n^k)（k为循环层数）

**实例代码**：

```java
// 打印九九乘法表
for (int i = 1; i <= 9; i++) {
    for (int j = 1; j <= i; j++) {
        System.out.print(j + "*" + i + "=" + i*j + "\t");
    }
    System.out.println();
}

// 计算1到100的和
int sum = 0;
for (int i = 1; i <= 100; i++) {
    sum += i;
}
System.out.println("Sum: " + sum);

// 寻找素数
for (int i = 2; i <= 100; i++) {
    boolean isPrime = true;
    for (int j = 2; j * j <= i; j++) {
        if (i % j == 0) {
            isPrime = false;
            break;
        }
    }
    if (isPrime) {
        System.out.println(i);
    }
}
```

### 2. 数组操作 (array.java)

**学习目标**：掌握Java数组的基本操作

**核心知识点**：

- 数组的声明和初始化
- 数组的遍历
- 数组的增删改查
- 数组的排序
- 数组的查找

**关键代码解析**：

```java
// 数组的声明和初始化
int[] arr = new int[5];
int[] arr2 = {1, 2, 3, 4, 5};

// 数组的遍历
for (int i = 0; i < arr.length; i++) {
    System.out.println(arr[i]);
}

// 数组的排序
Arrays.sort(arr2);

// 数组的查找
int index = Arrays.binarySearch(arr2, 3);
```

**应用场景**：

- 数据存储
- 矩阵运算
- 排序和搜索
- 动态规划

**时间复杂度分析**：

- 随机访问：O(1)
- 插入/删除元素：O(n)
- 查找元素：O(n)

**实例代码**：

```java
// 数组的增删改查
int[] arr = {1, 2, 3, 4, 5};

// 增加元素
int[] newArr = new int[arr.length + 1];
for (int i = 0; i < arr.length; i++) {
    newArr[i] = arr[i];
}
newArr[arr.length] = 6;

// 删除元素
int[] newArr2 = new int[arr.length - 1];
for (int i = 0; i < 2; i++) {
    newArr2[i] = arr[i];
}
for (int i = 2; i < arr.length - 1; i++) {
    newArr2[i] = arr[i + 1];
}

// 修改元素
arr[2] = 10;

// 查找元素
for (int i = 0; i < arr.length; i++) {
    if (arr[i] == 3) {
        System.out.println("Index: " + i);
        break;
    }
}
```

### 3. 链表操作 (linkedlist.java)

**学习目标**：掌握Java链表的基本操作

**核心知识点**：

- 链表的创建
- 链表的遍历
- 链表的增删改查
- 链表的反转
- 链表的合并

**关键代码解析**：

```java
// 链表节点的定义
class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}

// 链表的遍历
ListNode head = new ListNode(1);
head.next = new ListNode(2);
head.next.next = new ListNode(3);

ListNode curr = head;
while (curr != null) {
    System.out.println(curr.val);
    curr = curr.next;
}
```

**应用场景**：

- 动态数据结构
- 频繁插入删除
- 链表实现
- 图的邻接表表示

**时间复杂度分析**：

- 插入/删除节点：O(1)（已知前驱节点）
- 访问节点：O(n)
- 查找节点：O(n)

**实例代码**：

```java
// 链表的增删改查
ListNode head = new ListNode(1);
head.next = new ListNode(2);
head.next.next = new ListNode(3);

// 增加节点
ListNode newNode = new ListNode(4);
ListNode curr = head;
while (curr.next != null) {
    curr = curr.next;
}
curr.next = newNode;

// 删除节点
ListNode prev = head;
curr = head.next;
while (curr != null && curr.val != 2) {
    prev = curr;
    curr = curr.next;
}
prev.next = curr.next;

// 修改节点
curr = head;
while (curr != null && curr.val != 3) {
    curr = curr.next;
}
curr.val = 10;

// 查找节点
curr = head;
while (curr != null && curr.val != 10) {
    curr = curr.next;
}
System.out.println("Found: " + curr.val);
```

### 4. 列表操作 (list.java)

**学习目标**：掌握Java集合框架中List的使用

**核心知识点**：

- ArrayList的使用
- LinkedList的使用
- 列表的遍历
- 列表的增删改查操作
- 列表的排序和查找

**关键代码解析**：

```java
// ArrayList的初始化和操作
List<Integer> arrayList = new ArrayList<>();
arrayList.add(1);
arrayList.add(2);
arrayList.add(3);

// LinkedList的初始化和操作
List<Integer> linkedList = new LinkedList<>();
linkedList.add(1);
linkedList.add(2);
linkedList.add(3);
```

**应用场景**：

- 动态数组
- 链表实现
- 数据存储和管理
- 排序和搜索

**时间复杂度分析**：

- ArrayList随机访问：O(1)
- ArrayList插入/删除：O(n)
- LinkedList插入/删除：O(1)
- LinkedList访问：O(n)

**实例代码**：

```java
// 列表的遍历
for (int i = 0; i < arrayList.size(); i++) {
    System.out.println(arrayList.get(i));
}

// 增强for循环遍历
for (Integer num : arrayList) {
    System.out.println(num);
}

// 迭代器遍历
Iterator<Integer> iterator = arrayList.iterator();
while (iterator.hasNext()) {
    System.out.println(iterator.next());
}

// 列表的排序
Collections.sort(arrayList);

// 列表的查找
int index = Collections.binarySearch(arrayList, 2);
```

### 5. 栈操作 (stack.java)

**学习目标**：掌握栈的基本操作和特性

**核心知识点**：

- 栈的后进先出（LIFO）特性
- 入栈操作
- 出栈操作
- 查看栈顶元素
- 判断栈是否为空

**应用场景**：

- 表达式求值
- 括号匹配
- 函数调用栈
- 浏览器历史记录

**时间复杂度分析**：

- 入栈操作：O(1)
- 出栈操作：O(1)
- 查看栈顶元素：O(1)
- 判断栈是否为空：O(1)

**实例代码**：

```java
// 栈的实现（基于数组）
class Stack {
    private int[] arr;
    private int top;
    private int capacity;

    public Stack(int size) {
        arr = new int[size];
        capacity = size;
        top = -1;
    }

    // 入栈操作
    public void push(int x) {
        if (isFull()) {
            System.out.println("Stack Overflow");
            return;
        }
        arr[++top] = x;
    }

    // 出栈操作
    public int pop() {
        if (isEmpty()) {
            System.out.println("Stack is empty");
            return -1;
        }
        return arr[top--];
    }

    // 查看栈顶元素
    public int peek() {
        if (!isEmpty())
            return arr[top];
        else
            return -1;
    }

    // 判断栈是否为空
    public boolean isEmpty() {
        return top == -1;
    }

    // 判断栈是否满
    public boolean isFull() {
        return top == capacity - 1;
    }
}
```

### 6. 队列操作 (queue.java)

**学习目标**：掌握队列的基本操作和特性

**核心知识点**：

- 队列的先进先出（FIFO）特性
- 入队操作
- 出队操作
- 查看队首元素
- 判断队列是否为空

**应用场景**：

- 任务调度
- 消息队列
- 广度优先搜索

**时间复杂度分析**：

- 入队操作：O(1)
- 出队操作：O(1)
- 查看队首元素：O(1)
- 判断队列是否为空：O(1)

**实例代码**：

```java
// 队列的实现（基于数组）
class Queue {
    private int[] arr;
    private int front;
    private int rear;
    private int capacity;
    private int count;

    public Queue(int size) {
        arr = new int[size];
        capacity = size;
        front = 0;
        rear = -1;
        count = 0;
    }

    // 入队操作
    public void enqueue(int x) {
        if (isFull()) {
            System.out.println("Queue Overflow");
            return;
        }
        rear = (rear + 1) % capacity;
        arr[rear] = x;
        count++;
    }

    // 出队操作
    public int dequeue() {
        if (isEmpty()) {
            System.out.println("Queue is empty");
            return -1;
        }
        int temp = arr[front];
        front = (front + 1) % capacity;
        count--;
        return temp;
    }

    // 查看队首元素
    public int peek() {
        if (!isEmpty())
            return arr[front];
        else
            return -1;
    }

    // 判断队列是否为空
    public boolean isEmpty() {
        return count == 0;
    }

    // 判断队列是否满
    public boolean isFull() {
        return count == capacity;
    }
}
```

### 7. 双端队列 (deque.java)

**学习目标**：掌握双端队列的基本操作和特性

**核心知识点**：

- 双端队列的特性（两端都可以插入和删除）
- 前端插入和删除
- 后端插入和删除
- 查看两端元素

**应用场景**：

- 滑动窗口问题
- 回文判断
- 高效的队列操作

**时间复杂度分析**：

- 前端插入：O(1)
- 前端删除：O(1)
- 后端插入：O(1)
- 后端删除：O(1)
- 查看两端元素：O(1)

**实例代码**：

```java
// 双端队列的实现（基于数组）
class Deque {
    private int[] arr;
    private int front;
    private int rear;
    private int capacity;
    private int count;

    public Deque(int size) {
        arr = new int[size];
        capacity = size;
        front = 0;
        rear = -1;
        count = 0;
    }

    // 前端插入
    public void addFront(int x) {
        if (isFull()) {
            System.out.println("Deque Overflow");
            return;
        }
        front = (front - 1 + capacity) % capacity;
        arr[front] = x;
        count++;
    }

    // 后端插入
    public void addRear(int x) {
        if (isFull()) {
            System.out.println("Deque Overflow");
            return;
        }
        rear = (rear + 1) % capacity;
        arr[rear] = x;
        count++;
    }

    // 前端删除
    public int removeFront() {
        if (isEmpty()) {
            System.out.println("Deque is empty");
            return -1;
        }
        int temp = arr[front];
        front = (front + 1) % capacity;
        count--;
        return temp;
    }

    // 后端删除
    public int removeRear() {
        if (isEmpty()) {
            System.out.println("Deque is empty");
            return -1;
        }
        int temp = arr[rear];
        rear = (rear - 1 + capacity) % capacity;
        count--;
        return temp;
    }

    // 查看前端元素
    public int getFront() {
        if (!isEmpty())
            return arr[front];
        else
            return -1;
    }

    // 查看后端元素
    public int getRear() {
        if (!isEmpty())
            return arr[rear];
        else
            return -1;
    }

    // 判断双端队列是否为空
    public boolean isEmpty() {
        return count == 0;
    }

    // 判断双端队列是否满
    public boolean isFull() {
        return count == capacity;
    }
}
```

# 