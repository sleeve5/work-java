# 剑指Offer - LCR 120-130 题解整理

---

## 第一天：2026年2月12日

### 今日完成题目
- LCR 120 - 寻找文件副本
- LCR 121 - 寻找目标值 - 二维数组
- LCR 125 - 图书整理 II
- LCR 126 - 斐波那契数
- LCR 127 - 跳跃训练
- LCR 128 - 库存管理 I

---

### 一、数组问题

#### LCR 120 - 寻找文件副本

##### 题目描述

在一个长度为 n 的数组里的所有数字都在 0 到 n-1 的范围内。数组中某些数字是重复的，请找出数组中任意一个重复的数字。

##### 整理方法

**方法1：暴力搜索（不推荐）**

- 时间复杂度：O(n²)
- 空间复杂度：O(1)
- 缺点：时间复杂度过高

**方法2：HashSet（推荐）**

- 时间复杂度：O(n)
- 空间复杂度：O(n)
- 优点：实现简单，效率较高

**方法3：原地交换（最优）**

- 时间复杂度：O(n)
- 空间复杂度：O(1)
- 优点：不使用额外空间，效率最高

##### 完整题解

```java
class Solution {
    public int findRepeatDocument(int[] documents) {
        int i = 0;
        while (i < documents.length) {
            if (i == documents[i]) {
                i++;
                continue;
            }
            if (documents[i] == documents[documents[i]]) {
                return documents[i];
            }
            int tmp = documents[i];
            documents[i] = documents[documents[i]];
            documents[tmp] = tmp;
        }
        return -1;
    }
}
```

##### 核心思想

利用数组元素值在 0 到 n-1 之间的特点，将每个元素放到与其值相等的索引位置。当遇到冲突时，说明找到了重复元素。

---

#### LCR 121 - 寻找目标值 - 二维数组

##### 题目描述

在一个 m x n 的二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。请完成一个高效的函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。

##### 整理方法

**方法1：暴力搜索（不推荐）**

- 时间复杂度：O(mn)
- 空间复杂度：O(1)
- 缺点：没有利用数组的有序性

**方法2：从右上角开始搜索（推荐）**

- 时间复杂度：O(m+n)
- 空间复杂度：O(1)
- 优点：利用单调性，每次排除一行或一列

**方法3：从左下角开始搜索**

- 时间复杂度：O(m+n)
- 空间复杂度：O(1)
- 优点：与从右上角搜索逻辑类似

##### 完整题解

```java
class Solution {
    public boolean findTargetIn2DPlants(int[][] plants, int target) {
        if (plants.length == 0) {
            return false;
        }
        int m = plants.length - 1, n = plants[0].length - 1;
        int i = 0, j = n;
        
        while (j >= 0 && i <= m) {
            if (plants[i][j] > target) {
                j--;
            } else if (plants[i][j] < target) {
                i++;
            } else {
                return true;
            }
        }
        return false;
    }
}
```

##### 核心思想

利用矩阵的单调性，从右上角（或左下角）开始搜索：

- 当前元素 > target：目标在左侧，左移一列
- 当前元素 < target：目标在下方，下移一行
- 每次比较可以排除一行或一列

**注意**：不要尝试将二维矩阵一维化用二分查找，除非矩阵是严格连续有序的！

---

### 二、栈与队列

#### LCR 125 - 图书整理 II

##### 题目描述

用两个栈实现一个队列。队列的声明如下，请实现它的两个函数 appendTail 和 deleteHead ，分别完成在队列尾部插入整数和在队列头部删除整数的功能。

##### 整理方法

**方法：双栈协作（唯一解法）**

- 时间复杂度：
  - appendTail：O(1)
  - deleteHead：O(n) 最坏情况，O(1) 平均情况
- 空间复杂度：O(n)

##### 完整题解

```java
import java.util.Stack;

class CQueue {
    private Stack<Integer> stack1;
    private Stack<Integer> stack2;
    
    public CQueue() {
        stack1 = new Stack<>();
        stack2 = new Stack<>();
    }
    
    public void appendTail(int value) {
        stack1.push(value);
    }
    
    public int deleteHead() {
        if (stack2.isEmpty()) {
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
        }
        return stack2.isEmpty() ? -1 : stack2.pop();
    }
}
```

##### 核心思想

- **栈1**：专门用于入队操作
- **栈2**：专门用于出队操作
- 当栈2为空时，将栈1的所有元素倒入栈2，实现队列的"先进先出"特性

**注意**：不要用单个队列直接实现，题目要求必须用栈！

---

### 三、动态规划

#### LCR 126 - 斐波那契数

##### 题目描述

写一个函数，输入 n ，求斐波那契（Fibonacci）数列的第 n 项（即 F(N)）。斐波那契数列的定义如下：

- F(0) = 0
- F(1) = 1
- F(N) = F(N-1) + F(N-2)

答案需要取模 1e9+7（1000000007）。

##### 整理方法

**方法1：递归（不推荐）**

- 时间复杂度：O(2ⁿ)
- 空间复杂度：O(n)
- 缺点：重复计算过多，会超时

**方法2：动态规划（推荐）**

- 时间复杂度：O(n)
- 空间复杂度：O(1)
- 优点：效率高，不占用额外空间

##### 完整题解

```java
class Solution {
    public int fib(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        
        int f0 = 0, f1 = 1, fn = 0;
        for (int i = 2; i <= n; i++) {
            fn = (f0 + f1) % 1000000007;
            f0 = f1;
            f1 = fn;
        }
        return f1;
    }
}
```

##### 核心思想

- 保存前两个状态的值，避免重复计算
- 每次计算当前状态后，更新前两个状态
- 每次计算后取模，防止整数溢出

---

#### LCR 127 - 跳跃训练

##### 题目描述

一只青蛙一次可以跳上1级台阶，也可以跳上2级台阶。求该青蛙跳上一个 n 级的台阶总共有多少种跳法。

答案需要取模 1e9+7（1000000007）。

##### 整理方法

**方法：动态规划（与斐波那契数列相同，但边界不同）**

- 时间复杂度：O(n)
- 空间复杂度：O(1)

##### 完整题解

```java
class Solution {
    public int trainWays(int num) {
        if (num == 0) {
            return 1;
        }
        if (num == 1) {
            return 1;
        }
        
        int a = 0, b = 1, c = 0;
        for (int i = 0; i < num; i++) {
            c = (a + b) % 1000000007;
            a = b;
            b = c;
        }
        return b;
    }
}
```

##### 核心思想

- 跳到第 n 阶的方式数 = 跳到第 n-1 阶的方式数 + 跳到第 n-2 阶的方式数
- 边界条件：num=0 时返回 1（不跳也算一种方式），num=1 时返回 1

**注意**：num=0 时返回 1 而不是 0！

---

### 四、二分查找

#### LCR 128 - 库存管理 I

##### 题目描述

把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。输入一个递增排序的数组的一个旋转，输出旋转数组的最小元素。

数组元素可能重复。

##### 整理方法

**方法：二分查找（与右边界比较）**

- 时间复杂度：O(log n) 平均，O(n) 最坏（所有元素相同）
- 空间复杂度：O(1)

##### 完整题解

```java
class Solution {
    public int inventoryManagement(int[] stock) {
        int l = 0, r = stock.length - 1;
        
        while (l < r) {
            int m = l + (r - l) / 2;
            
            if (stock[m] > stock[r]) {
                l = m + 1;
            } else if (stock[m] < stock[r]) {
                r = m;
            } else {
                r--;
            }
        }
        
        return stock[l];
    }
}
```

##### 核心思想

- **与右边界比较**，而不是左边界！
- 当 stock[mid] > stock[right]：最小值在右半部分
- 当 stock[mid] < stock[right]：最小值在左半部分
- 当 stock[mid] == stock[right]：无法判断，缩小右边界

**重要提醒**：

1. ✅ 必须与 stock[right] 比较，不要与 stock[left] 比较！
2. ✅ 使用 l < r 作为循环条件，更简洁
3. ✅ 当 stock[mid] < stock[right] 时，r = m，不是 r = m - 1！

---

### 五、今日总结

**学习成果**：
- 完成了 6 道题目，涵盖数组、栈与队列、动态规划、二分查找等知识点
- 掌握了原地交换、边角搜索、双栈实现队列、动态规划等算法技巧
- 理解了斐波那契数列和爬楼梯问题的本质

**常见错误**：
1. ❌ LCR 120：双重循环时间复杂度过高
2. ❌ LCR 121：尝试将二维矩阵一维化用二分查找
3. ❌ LCR 125：直接用队列实现（题目要求用栈）
4. ❌ LCR 126：递归实现导致超时
5. ❌ LCR 127：num=0 时返回 0 而不是 1
6. ❌ LCR 128：与 stock[left] 比较而不是 stock[right]

---

## 第二天：2026年2月13日

### 今日完成题目
- LCR 122 - 路径加密
- LCR 129 - 字母迷宫
- LCR 130 - 衣橱整理
- LCR 123 - 图书整理 I

---

### 一、字符串处理

#### LCR 122 - 路径加密

##### 题目描述

假定一段路径记作字符串 path，其中以 "." 作为分隔符。现需将路径加密，加密方法为将 path 中的分隔符替换为空格 " "，请返回加密后的字符串。

##### 整理方法

**方法：遍历替换（推荐）**

- 时间复杂度：O(n)
- 空间复杂度：O(n)
- 优点：使用 StringBuilder，性能高效

##### 完整题解

```java
class Solution {
    public String pathEncryption(String path) {
        StringBuilder res = new StringBuilder();
        for (Character character : path.toCharArray()) {
            if (character == '.') {
                res.append(' ');
            } else {
                res.append(character);
            }
        }
        return res.toString();
    }
}
```

##### 核心思想

- 使用 StringBuilder 进行字符串拼接，避免创建大量临时对象
- 遍历字符串的每个字符，遇到 '.' 替换为空格
- 时间复杂度 O(n)，空间复杂度 O(n)

##### 知识点：StringBuilder vs String

| 特性 | String | StringBuilder |
|------|--------|---------------|
| 可变性 | 不可变 | 可变 |
| 线程安全 | 线程安全 | 不安全 |
| 性能 | 较低（频繁修改时） | 较高 |
| 适用场景 | 少量修改 | 频繁修改 |

---

### 二、深度优先搜索

#### LCR 129 - 字母迷宫

##### 题目描述

字母迷宫游戏初始界面记作 m x n 二维字符串数组 grid，请判断玩家是否能在 grid 中找到目标单词 target。注意：寻找单词时 必须 按照字母顺序，通过水平或垂直方向相邻的单元格内的字母构成，同时，同一个单元格内的字母 不允许被重复使用 。

##### 整理方法

**方法：DFS + 回溯（推荐）**

- 时间复杂度：O(m×n×4^L)
- 空间复杂度：O(L)
- 优点：系统地探索所有可能的路径

##### 完整题解

```java
class Solution {
    public boolean wordPuzzle(char[][] grid, String target) {
        int m = grid.length;
        int n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (dfs(grid, visited, i, j, target, 0)) {
                    return true;
                }
            }
        }
        
        return false;
    }
    
    private boolean dfs(char[][] grid, boolean[][] visited, int i, int j, String target, int index) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || visited[i][j] || grid[i][j] != target.charAt(index)) {
            return false;
        }
        if (index == target.length() - 1) {
            return true;
        }
        
        visited[i][j] = true;
        boolean found = dfs(grid, visited, i - 1, j, target, index + 1) ||
                        dfs(grid, visited, i + 1, j, target, index + 1) ||
                        dfs(grid, visited, i, j - 1, target, index + 1) ||
                        dfs(grid, visited, i, j + 1, target, index + 1);
        visited[i][j] = false;
        
        return found;
    }
}
```

##### 核心思想

- **DFS**：深度优先搜索，系统地探索所有可能的路径
- **回溯**：标记已访问的单元格，搜索完成后恢复
- **方向搜索**：向四个方向（上、下、左、右）递归搜索
- **终止条件**：当匹配到目标单词的最后一个字符时返回 true

##### 知识点：DFS vs 回溯

| 特性 | DFS | 回溯 |
|------|-----|------|
| 核心目标 | 遍历所有可达节点 | 枚举所有可能解 |
| 是否撤销选择 | 通常不需要 | 必须撤销 |
| 适用场景 | 连通性问题 | 排列组合、约束满足 |

---

#### LCR 130 - 衣橱整理

##### 题目描述

家居整理师将待整理衣橱划分为 m x n 的二维矩阵 grid，其中 grid[i][j] 代表一个需要整理的格子。整理师自 grid[0][0] 开始逐行逐列地整理每个格子。整理规则为：在整理过程中，可以选择 向右移动一格 或 向下移动一格，但不能移动到衣柜之外。同时，不需要整理 digit(i) + digit(j) > cnt 的格子，其中 digit(x) 表示数字 x 的各数位之和。请返回整理师 总共需要整理多少个格子。

##### 整理方法

**方法：DFS（推荐）**

- 时间复杂度：O(m×n)
- 空间复杂度：O(m×n)
- 优点：从起点开始，只统计可达的格子

##### 完整题解

```java
class Solution {
    public int wardrobeFinishing(int m, int n, int cnt) {
        boolean[][] visited = new boolean[m][n];       
        return dfs(0, 0, m, n, visited, cnt);
    }
    
    private int dfs(int i, int j, int m, int n, boolean[][] visited, int cnt) {
        if (i >= m || j >= n || visited[i][j] || digit(i) + digit(j) > cnt) {
            return 0;
        }
        visited[i][j] = true;
        return dfs(i + 1, j, m, n, visited, cnt) + dfs(i, j + 1, m, n, visited, cnt) + 1;
    }

    private int digit(int x) {
        int res = 0;
        while (true) {
            res += x % 10;
            x = x / 10;
            if (x == 0) {
                break;
            }
        }
        return res;
    }
}
```

##### 核心思想

- **DFS**：从起点 (0,0) 开始，向右和向下搜索
- **可达性**：只统计从起点可达的格子，不是所有满足条件的格子
- **标记访问**：使用 visited 数组标记已访问的格子，避免重复访问
- **终止条件**：越界、已访问、数字和超过阈值时返回 0

##### 知识点：DFS vs 回溯

本题使用 DFS 而不是回溯，因为：
1. **不需要撤销选择**：访问过的格子不会被再次访问
2. **移动方向单向**：只能向右或向下移动，不会形成环
3. **目标是统计数量**：不需要枚举所有路径

---

### 三、链表

#### LCR 123 - 图书整理 I

##### 题目描述

书店店员有一张链表形式的书单，每个节点代表一本书，节点中的值表示书的编号。为更方便整理书架，店员需要将书单倒过来排列，就可以从最后一本书开始整理，逐一将书放回到书架上。请倒序返回这个书单链表。

##### 整理方法

**方法1：栈（推荐）**

- 时间复杂度：O(n)
- 空间复杂度：O(n)
- 优点：逻辑清晰，易于理解

**方法2：倒序填充（最优）**

- 时间复杂度：O(n)
- 空间复杂度：O(n)
- 优点：不需要额外栈结构，空间最优

##### 完整题解（方法1：栈）

```java
import java.util.Stack;

class Solution {
    public int[] reverseBookList(ListNode head) {
        if (head == null) {
            return new int[] {};
        }
        Stack<Integer> stack = new Stack<>();
        while (head != null) {
            stack.push(head.val);
            head = head.next;
        }
        int[] res = new int[stack.size()];
        for (int i = 0; i < res.length; i++) {
            res[i] = stack.pop();
        }
        return res;
    }
}
```

##### 完整题解（方法2：倒序填充）

```java
class Solution {
    public int[] reverseBookList(ListNode head) {
        if (head == null) {
            return new int[] {};
        }
        
        ListNode temp = head;
        int len = 0;
        while (temp != null) {
            len++;
            temp = temp.next;
        }
        
        int[] res = new int[len];
        temp = head;
        while (temp != null) {
            res[--len] = temp.val;
            temp = temp.next;
        }
        return res;
    }
}
```

##### 核心思想

- **方法1（栈）**：利用栈的"后进先出"特性，自然实现反转
- **方法2（倒序填充）**：先计算链表长度，然后倒序填充数组
- **时间复杂度**：两种方法都是 O(n)
- **空间复杂度**：方法1 使用栈 O(n)，方法2 仅结果数组 O(n)

##### 知识点：List 转 int[]

```java
List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);

// 方法1：Stream API
int[] arr = list.stream().mapToInt(i -> i).toArray();

// 方法2：方法引用
int[] arr = list.stream().mapToInt(Integer::intValue).toArray();

// 方法3：传统循环
int[] arr = new int[list.size()];
for (int i = 0; i < list.size(); i++) {
    arr[i] = list.get(i);
}
```

---

### 四、今日总结

**学习成果**：
- 完成了 4 道题目，涵盖字符串、深度优先搜索、链表等知识点
- 掌握了 StringBuilder 的使用、DFS 与回溯的区别、链表反转等技巧
- 理解了 Stream API 的使用和 List 与 int[] 的转换

**常见错误**：
7. ❌ LCR 129：DFS 中忘记检查 visited[i][j]
8. ❌ LCR 130：只统计满足条件的格子，不考虑可达性
9. ❌ LCR 122：使用 String 拼接而不是 StringBuilder
10. ❌ LCR 123：使用 Stack 而不是 ArrayDeque（性能优化）

---

## 题目分类总览

| 数据结构/算法 | 题目 | 难度 | 日期 |
|--------------|------|------|------|
| 数组 | LCR 120 - 寻找文件副本 | 简单 | 2026年2月12日 |
| 数组 | LCR 121 - 寻找目标值 - 二维数组 | 中等 | 2026年2月12日 |
| 栈与队列 | LCR 125 - 图书整理 II | 简单 | 2026年2月12日 |
| 动态规划 | LCR 126 - 斐波那契数 | 简单 | 2026年2月12日 |
| 动态规划 | LCR 127 - 跳跃训练 | 简单 | 2026年2月12日 |
| 二分查找 | LCR 128 - 库存管理 I | 简单 | 2026年2月12日 |
| 字符串 | LCR 122 - 路径加密 | 简单 | 2026年2月13日 |
| 深度优先搜索 | LCR 129 - 字母迷宫 | 中等 | 2026年2月13日 |
| 深度优先搜索 | LCR 130 - 衣橱整理 | 中等 | 2026年2月13日 |
| 链表 | LCR 123 - 图书整理 I | 简单 | 2026年2月13日 |

---

## 算法与数据结构总结

### 数组操作技巧

| 技巧 | 应用题目 | 说明 |
|------|---------|------|
| 原地交换 | LCR 120 | 利用数组索引与元素值的对应关系 |
| 边角搜索 | LCR 121 | 从右上角或左下角开始，利用单调性 |

### 栈与队列

| 数据结构 | 应用场景 | 核心思想 |
|---------|---------|---------|
| 双栈实现队列 | LCR 125 | 一个栈入队，一个栈出队 |
| 栈反转 | LCR 123 | 利用栈的"后进先出"特性 |

### 动态规划

| 问题类型 | 核心思想 | 关键变量 |
|---------|---------|---------|
| 斐波那契数列 | F(n) = F(n-1) + F(n-2) | 保存前两个状态 |
| 爬楼梯问题 | 与斐波那契相同，但边界不同 | 注意 num=0 返回 1 |

### 二分查找

| 问题类型 | 比较对象 | 关键点 |
|---------|---------|--------|
| 旋转数组找最小值 | 右边界 | stock[mid] 与 stock[right] 比较 |

### 深度优先搜索

| 问题类型 | 核心思想 | 关键点 |
|---------|---------|--------|
| 单词搜索 | DFS + 回溯 | 标记已访问，搜索完成后恢复 |
| 可达格子统计 | DFS | 只统计从起点可达的格子 |

### 字符串处理

| 问题类型 | 核心思想 | 关键点 |
|---------|---------|--------|
| 字符替换 | 遍历替换 | 使用 StringBuilder 提高性能 |

---

## 学习时间线

- **2026年2月12日**：完成 LCR 120-128 的学习与整理
- **2026年2月13日**：完成 LCR 122、129、130、123 的学习与整理

---

*整理时间：2026年2月12日 - 2026年2月13日*
*完成题目：LCR 120、121、122、123、125、126、127、128、129、130*
