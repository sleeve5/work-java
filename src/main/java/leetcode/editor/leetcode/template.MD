# 动态规划 (Dynamic Programming) 模板总结

## 一、动态规划基本概念

动态规划是一种通过将原问题分解为子问题并存储子问题结果来避免重复计算的算法设计方法。

### 核心要素
1. **状态定义**：明确子问题的状态表示
2. **状态转移方程**：描述子问题之间的关系
3. **边界条件**：初始状态的值
4. **计算顺序**：确保计算时所需的子问题已被解决
5. **空间优化**：根据状态转移的依赖关系优化空间使用

## 二、备忘录形式（自顶向下）

### 适用场景
- 问题具有重叠子问题性质
- 递归思路清晰，便于理解
- 子问题数量较少，不会导致栈溢出

### 模板结构

#### 方法1：使用哈希表作为备忘录（适用于状态非连续的情况）

```java
class Solution {
    // 备忘录：存储已计算的状态
    private Map<String, Integer> memo = new HashMap<>();
    
    public int dpFunction(int n) {
        return dfs(n);
    }
    
    private int dfs(int n) {
        // 1. 边界条件
        if (n == 0) {
            return 0; // 根据具体问题调整
        }
        if (n == 1) {
            return 1; // 根据具体问题调整
        }
        
        // 2. 检查备忘录
        String key = String.valueOf(n); // 根据状态设计键
        if (memo.containsKey(key)) {
            return memo.get(key);
        }
        
        // 3. 状态转移
        int result = dfs(n-1) + dfs(n-2); // 示例：斐波那契
        
        // 4. 存储结果到备忘录
        memo.put(key, result);
        
        return result;
    }
}
```

#### 方法2：使用数组作为备忘录（推荐，适用于状态连续的情况）

```java
class Solution {
    // 备忘录：使用数组存储已计算的状态
    private Integer[] memo;
    
    public int dpFunction(int n) {
        // 初始化备忘录：数组下标对应状态key
        memo = new Integer[n+1];
        return dfs(n);
    }
    
    private int dfs(int n) {
        // 1. 边界条件
        if (n == 0) {
            return 0; // 根据具体问题调整
        }
        if (n == 1) {
            return 1; // 根据具体问题调整
        }
        
        // 2. 检查备忘录
        if (memo[n] != null) {
            return memo[n];
        }
        
        // 3. 状态转移
        int result = dfs(n-1) + dfs(n-2); // 示例：斐波那契
        
        // 4. 存储结果到备忘录
        memo[n] = result;
        
        return result;
    }
}
```

### 示例：斐波那契数列（备忘录形式）

#### 使用哈希表

```java
class Solution {
    private Map<Integer, Integer> memo = new HashMap<>();
    
    public int fib(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;
        
        if (memo.containsKey(n)) {
            return memo.get(n);
        }
        
        int result = (fib(n-1) + fib(n-2)) % 1000000007;
        memo.put(n, result);
        
        return result;
    }
}
```

#### 使用数组（推荐）

```java
class Solution {
    private Integer[] memo;
    
    public int fib(int n) {
        memo = new Integer[n+1];
        return dfs(n);
    }
    
    private int dfs(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;
        
        if (memo[n] != null) {
            return memo[n];
        }
        
        int result = (dfs(n-1) + dfs(n-2)) % 1000000007;
        memo[n] = result;
        
        return result;
    }
}
```

## 三、DP数组形式（自底向上）

### 适用场景
- 问题的状态转移关系明确
- 可以按顺序计算所有子问题
- 避免递归带来的栈溢出风险
- 通常比备忘录形式更高效

### 模板结构

```java
class Solution {
    public int dpFunction(int n) {
        // 1. 处理边界情况
        if (n == 0) {
            return 0; // 根据具体问题调整
        }
        if (n == 1) {
            return 1; // 根据具体问题调整
        }
        
        // 2. 创建DP数组
        int[] dp = new int[n+1];
        
        // 3. 初始化边界条件
        dp[0] = 0; // 根据具体问题调整
        dp[1] = 1; // 根据具体问题调整
        
        // 4. 状态转移（自底向上计算）
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i-1] + dp[i-2]; // 示例：斐波那契
        }
        
        // 5. 返回结果
        return dp[n];
    }
}
```

### 示例：斐波那契数列（DP数组形式）

```java
class Solution {
    public int fib(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;
        
        int[] dp = new int[n+1];
        dp[0] = 0;
        dp[1] = 1;
        
        for (int i = 2; i <= n; i++) {
            dp[i] = (dp[i-1] + dp[i-2]) % 1000000007;
        }
        
        return dp[n];
    }
}
```

### 空间优化（滚动数组）

对于只依赖前几个状态的问题，可以使用滚动数组优化空间：

```java
class Solution {
    public int fib(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;
        
        int a = 0, b = 1, c = 0;
        
        for (int i = 2; i <= n; i++) {
            c = (a + b) % 1000000007;
            a = b;
            b = c;
        }
        
        return b;
    }
}
```

## 四、常见动态规划类型及模板

### 1. 线性DP

**问题特征**：状态转移仅依赖于前几个状态

**模板**：

```java
// 一维DP
int[] dp = new int[n+1];
dp[0] = 初始值;
for (int i = 1; i <= n; i++) {
    dp[i] = 状态转移方程;
}

// 二维DP
int[][] dp = new int[n+1][m+1];
// 初始化边界
for (int i = 1; i <= n; i++) {
    for (int j = 1; j <= m; j++) {
        dp[i][j] = 状态转移方程;
    }
}
```

**示例**：爬楼梯、最长递增子序列

### 2. 背包问题

#### 0-1背包

**模板**：

```java
int[] dp = new int[capacity+1];
for (int i = 0; i < items.length; i++) {
    int weight = items[i].weight;
    int value = items[i].value;
    for (int j = capacity; j >= weight; j--) {
        dp[j] = Math.max(dp[j], dp[j-weight] + value);
    }
}
return dp[capacity];
```

#### 完全背包

**模板**：

```java
int[] dp = new int[capacity+1];
for (int i = 0; i < items.length; i++) {
    int weight = items[i].weight;
    int value = items[i].value;
    for (int j = weight; j <= capacity; j++) {
        dp[j] = Math.max(dp[j], dp[j-weight] + value);
    }
}
return dp[capacity];
```

### 3. 区间DP

**问题特征**：状态表示区间[i,j]的最优解

**模板**：

```java
int[][] dp = new int[n][n];
// 初始化单个元素的情况
for (int i = 0; i < n; i++) {
    dp[i][i] = 初始值;
}
// 枚举区间长度
for (int len = 2; len <= n; len++) {
    // 枚举起始位置
    for (int i = 0; i <= n - len; i++) {
        int j = i + len - 1;
        // 状态转移
        for (int k = i; k < j; k++) {
            dp[i][j] = Math.max(dp[i][j], dp[i][k] + dp[k+1][j] + cost);
        }
    }
}
return dp[0][n-1];
```

**示例**：石子合并、最长回文子串

### 4. 树形DP

**问题特征**：状态定义在树的节点上

**模板**：

```java
class Solution {
    private int[][] dp;
    
    public int treeDP(TreeNode root) {
        if (root == null) return 0;
        
        // 后序遍历
        int left = treeDP(root.left);
        int right = treeDP(root.right);
        
        // 状态转移
        dp[root] = 计算当前节点的状态;
        
        return dp[root];
    }
}
```

**示例**：打家劫舍 III、二叉树中的最大路径和

## 五、动态规划解题步骤

1. **分析问题**：确定是否适合使用动态规划
   - 是否有重叠子问题？
   - 是否有最优子结构？

2. **定义状态**：明确子问题的状态表示
   - 一维：`dp[i]` 表示前i个元素的最优解
   - 二维：`dp[i][j]` 表示从i到j的最优解
   - 多维：根据问题复杂度确定

3. **推导状态转移方程**：
   - 分析当前状态与子状态之间的关系
   - 确保转移方程的正确性

4. **确定边界条件**：
   - 初始化最小子问题的解

5. **计算顺序**：
   - 自底向上：按顺序计算所有子问题
   - 自顶向下：使用备忘录递归计算

6. **空间优化**：
   - 分析状态转移的依赖关系
   - 使用滚动数组或变量替代数组

7. **验证结果**：
   - 测试边界情况
   - 验证计算结果的正确性

## 六、注意事项

1. **初始化**：确保边界条件的初始化正确
2. **数组越界**：注意数组索引的范围
3. **整数溢出**：处理大数情况，使用long类型或取模
4. **状态设计**：状态定义要准确，避免冗余
5. **转移方程**：确保状态转移的逻辑正确
6. **计算顺序**：自底向上时要按正确的顺序计算
7. **空间使用**：根据问题规模选择合适的空间优化策略
8. **时间复杂度**：分析时间复杂度，确保在合理范围内

## 七、经典例题模板

### 1. 斐波那契数列

```java
// 备忘录形式
class Solution {
    private Map<Integer, Integer> memo = new HashMap<>();
    
    public int fib(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;
        
        if (memo.containsKey(n)) {
            return memo.get(n);
        }
        
        int result = (fib(n-1) + fib(n-2)) % 1000000007;
        memo.put(n, result);
        
        return result;
    }
}

// DP数组形式
class Solution {
    public int fib(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;
        
        int[] dp = new int[n+1];
        dp[0] = 0;
        dp[1] = 1;
        
        for (int i = 2; i <= n; i++) {
            dp[i] = (dp[i-1] + dp[i-2]) % 1000000007;
        }
        
        return dp[n];
    }
}

// 空间优化
class Solution {
    public int fib(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;
        
        int a = 0, b = 1, c = 0;
        
        for (int i = 2; i <= n; i++) {
            c = (a + b) % 1000000007;
            a = b;
            b = c;
        }
        
        return b;
    }
}
```

### 2. 爬楼梯

```java
// DP数组形式
class Solution {
    public int climbStairs(int n) {
        if (n == 1) return 1;
        if (n == 2) return 2;
        
        int[] dp = new int[n+1];
        dp[1] = 1;
        dp[2] = 2;
        
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i-1] + dp[i-2];
        }
        
        return dp[n];
    }
}

// 空间优化
class Solution {
    public int climbStairs(int n) {
        if (n == 1) return 1;
        if (n == 2) return 2;
        
        int a = 1, b = 2, c = 0;
        
        for (int i = 3; i <= n; i++) {
            c = a + b;
            a = b;
            b = c;
        }
        
        return b;
    }
}
```

### 3. 最长递增子序列

```java
class Solution {
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        int maxLen = 1;
        
        // 初始化：每个元素自身构成一个长度为1的子序列
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
        }
        
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            maxLen = Math.max(maxLen, dp[i]);
        }
        
        return maxLen;
    }
}
```

### 4. 0-1背包

```java
class Solution {
    public int knapsack(int capacity, int[] weights, int[] values) {
        int n = weights.length;
        int[] dp = new int[capacity + 1];
        
        for (int i = 0; i < n; i++) {
            int weight = weights[i];
            int value = values[i];
            for (int j = capacity; j >= weight; j--) {
                dp[j] = Math.max(dp[j], dp[j - weight] + value);
            }
        }
        
        return dp[capacity];
    }
}
```

## 八、总结

动态规划是一种强大的算法设计方法，通过合理的状态定义和状态转移方程，可以高效解决许多复杂问题。选择备忘录形式还是DP数组形式取决于具体问题的特点：

- **备忘录形式**：适用于递归思路清晰、子问题数量较少的情况
- **DP数组形式**：适用于需要按顺序计算所有子问题、避免栈溢出的情况

掌握动态规划的关键在于多练习、多总结，熟悉各种类型的问题和解题思路。通过不断实践，能够快速识别问题是否适合使用动态规划，并设计出高效的解决方案。

## 九、推荐练习题目

1. **基础**：
   - 509. 斐波那契数
   - 70. 爬楼梯
   - 746. 使用最小花费爬楼梯

2. **进阶**：
   - 300. 最长递增子序列
   - 198. 打家劫舍
   - 213. 打家劫舍 II
   - 518. 零钱兑换 II
   - 322. 零钱兑换

3. **高级**：
   - 121. 买卖股票的最佳时机
   - 122. 买卖股票的最佳时机 II
   - 123. 买卖股票的最佳时机 III
   - 188. 买卖股票的最佳时机 IV
   - 309. 最佳买卖股票时机含冷冻期
   - 714. 买卖股票的最佳时机含手续费

通过这些题目的练习，可以逐步掌握动态规划的精髓，提高解题能力。