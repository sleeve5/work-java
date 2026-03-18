# 动态规划题目分题型总结

> 本总结涵盖 08DP 和 08DP-advanced 文件夹中的所有动态规划题目

---

## 一、线性 DP

线性 DP 是最简单的 DP 形式，状态转移呈线性关系，通常通过遍历状态数组来求解。

### 1.1 爬楼梯（70. 爬楼梯）

**题目描述**：每次可以爬 1 或 2 个台阶，求爬到第 n 阶楼顶的方法数。

**DP 定义**：`dp[i]` 表示爬到第 i 阶台阶的方法数。

**状态转移方程**：
```
dp[i] = dp[i-1] + dp[i-2]  (i >= 3)
dp[1] = 1, dp[2] = 2
```

**边界条件**：`dp[0] = 0, dp[1] = 1, dp[2] = 2`

**做题模板**：
```java
public int climbStairs(int n) {
    if (n == 1 || n == 2) return n;
    int a = 1, b = 2;
    for (int i = 3; i <= n; i++) {
        int c = a + b;
        a = b;
        b = c;
    }
    return b;
}
```

---

### 1.2 不同路径（62. 不同路径）

**题目描述**：机器人从左上角走到右下角，每次只能向下或向右移动，求路径数。

**DP 定义**：`dp[i][j]` 表示从起点走到位置 (i, j) 的路径数。

**状态转移方程**：
```
dp[i][j] = dp[i-1][j] + dp[i][j-1]  (i > 0, j > 0)
dp[i][0] = 1, dp[0][j] = 1
```

**边界条件**：第一行和第一列只有一种走法。

**做题模板**：
```java
public int uniquePaths(int m, int n) {
    int[][] dp = new int[m][n];
    for (int i = 0; i < m; i++) dp[i][0] = 1;
    for (int j = 0; j < n; j++) dp[0][j] = 1;
    for (int i = 1; i < m; i++) {
        for (int j = 1; j < n; j++) {
            dp[i][j] = dp[i-1][j] + dp[i][j-1];
        }
    }
    return dp[m-1][n-1];
}
```

---

### 1.3 打家劫舍（198. 打家劫舍）

**题目描述**：不能偷相邻房屋，求能偷到的最大金额。

**DP 定义**：`dp[i]` 表示偷到第 i 个房屋时的最大金额。

**状态转移方程**：
```
dp[i] = max(dp[i-1], dp[i-2] + nums[i-1])  (i >= 2)
dp[0] = 0, dp[1] = nums[0]
```

**边界条件**：处理 i=0 和 i=1 的情况。

**做题模板**：
```java
public int rob(int[] nums) {
    int n = nums.length;
    if (n == 1) return nums[0];
    int[] dp = new int[n + 1];
    dp[0] = 0;
    dp[1] = nums[0];
    for (int i = 2; i <= n; i++) {
        dp[i] = Math.max(dp[i-1], dp[i-2] + nums[i-1]);
    }
    return dp[n];
}
```

---

## 二、背包问题

背包问题是在给定容量限制下，选择物品以达到最大价值或判断可行性。

### 2.1 0-01 背包

#### 分割等和子集（416. 分割等和子集）

**题目描述**：判断数组是否可以分割成两个元素和相等的子集。

**DP 定义**：`dp[j]` 表示是否能从数组中选取若干元素使其和为 j。

**状态转移方程**：
```
dp[j] = dp[j] || dp[j - nums[i]]  (从后往前遍历)
```

**核心思想**：将问题转化为能否凑出 sum/2 的背包问题。

**做题模板**：
```java
public boolean canPartition(int[] nums) {
    int sum = 0;
    for (int num : nums) sum += num;
    if (sum % 2 == 1) return false;

    int target = sum / 2;
    boolean[] dp = new boolean[target + 1];
    dp[0] = true;

    for (int i = 0; i < nums.length; i++) {
        for (int j = target; j >= 0; j--) {
            if (j - nums[i] >= 0) {
                dp[j] = dp[j] || dp[j - nums[i]];
            }
        }
    }
    return dp[target];
}
```

---

### 2.2 完全背包

#### 零钱兑换（322. 零钱兑换）

**题目描述**：给定硬币面额，每种硬币无限个，求凑成总金额所需的最少硬币数。

**DP 定义**：`dp[i]` 表示凑成金额 i 所需的最少硬币数。

**状态转移方程**：
```
dp[i] = min(dp[i], dp[i - coin] + 1)  (coin <= i)
```

**核心区别**：完全背包需要**从前往后**遍历，保证物品可重复选取。

**做题模板**：
```java
public int coinChange(int[] coins, int amount) {
    int[] dp = new int[amount + 1];
    Arrays.fill(dp, amount + 1);
    dp[0] = 0;

    for (int i = 1; i <= amount; i++) {
        for (int coin : coins) {
            if (i - coin >= 0) {
                dp[i] = Math.min(dp[i], dp[i - coin] + 1);
            }
        }
    }
    return dp[amount] > amount ? -1 : dp[amount];
}
```

---

### 2.3 背包问题总结

| 类型 | 遍历顺序 | 核心方程 |
|------|----------|----------|
| 0-1 背包 | 逆序 | `dp[j] = max(dp[j], dp[j-w[i]] + v[i])` |
| 完全背包 | 顺序 | `dp[j] = max(dp[j], dp[j-w[i]] + v[i])` |
| 组合背包 | 视情况 | 多种选择 |

---

## 三、序列 DP

序列 DP 通常涉及两个序列或单个序列的最优子序列问题，状态通常与前缀相关。

### 3.1 最长递增子序列（300. 最长递增子序列）

**题目描述**：找出数组中最长严格递增子序列的长度。

**DP 定义**：`dp[i]` 表示以第 i 个元素结尾的最长递增子序列长度。

**状态转移方程**：
```
dp[i] = max(dp[j] + 1)  (j < i 且 nums[j] < nums[i])
dp[i] 最小为 1
```

**进阶解法**：贪心 + 二分，时间复杂度 O(n log n)。

**做题模板**：
```java
// O(n^2) 解法
public int lengthOfLIS(int[] nums) {
    int[] dp = new int[nums.length];
    Arrays.fill(dp, 1);
    int maxLen = 1;

    for (int i = 1; i < nums.length; i++) {
        for (int j = 0; j < i; j++) {
            if (nums[j] < nums[i]) {
                dp[i] = Math.max(dp[i], dp[j] + 1);
            }
        }
        maxLen = Math.max(maxLen, dp[i]);
    }
    return maxLen;
}

// O(n log n) 解法（贪心+二分）
public int lengthOfLIS(int[] nums) {
    int[] top = new int[nums.length];
    int piles = 0;

    for (int num : nums) {
        int left = 0, right = piles;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (top[mid] < num) left = mid + 1;
            else right = mid;
        }
        if (left == piles) piles++;
        top[left] = num;
    }
    return piles;
}
```

---

### 3.2 最长公共子序列（1143. 最长公共子序列）

**题目描述**：找出两个字符串的最长公共子序列长度。

**DP 定义**：`dp[i][j]` 表示 text1 前 i 个字符和 text2 前 j 个字符的最长公共子序列长度。

**状态转移方程**：
```
若 text1[i-1] == text2[j-1]：
    dp[i][j] = dp[i-1][j-1] + 1
否则：
    dp[i][j] = max(dp[i-1][j], dp[i][j-1])
```

**边界条件**：`dp[0][j] = 0, dp[i][0] = 0`

**做题模板**：
```java
public int longestCommonSubsequence(String text1, String text2) {
    int m = text1.length(), n = text2.length();
    int[][] dp = new int[m + 1][n + 1];

    for (int i = 1; i <= m; i++) {
        for (int j = 1; j <= n; j++) {
            if (text1.charAt(i-1) == text2.charAt(j-1)) {
                dp[i][j] = dp[i-1][j-1] + 1;
            } else {
                dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
            }
        }
    }
    return dp[m][n];
}
```

---

## 四、状态机 DP

状态机 DP 通过定义多个状态来描述复杂的状态转换，常用于股票交易等问题。

### 4.1 买卖股票的最佳时机（121. 买卖股票的最佳时机）

**题目描述**：只能买卖一次，求最大利润。

**DP 定义**：
- `dp[i][0]` 表示第 i 天**不持有**股票的最大利润
- `dp[i][1]` 表示第 i 天**持有**股票的最大利润

**状态转移方程**：
```
dp[i][0] = max(dp[i-1][0], dp[i-1][1] + prices[i-1])
dp[i][1] = max(dp[i-1][1], -prices[i-1])
```

**初始化**：`dp[0][0] = 0, dp[0][1] = -Infinity`

**做题模板**：
```java
public int maxProfit(int[] prices) {
    int n = prices.length;
    int[][] dp = new int[n + 1][2];
    dp[0][0] = 0;
    dp[0][1] = Integer.MIN_VALUE;

    for (int i = 1; i <= n; i++) {
        dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1] + prices[i-1]);
        dp[i][1] = Math.max(dp[i-1][1], -prices[i-1]);
    }
    return dp[n][0];
}
```

---

### 4.2 买卖股票的最佳时机 II（122. 买卖股票的最佳时机 II）

**题目描述**：可以买卖多次，求最大利润。

**DP 定义**：同 121 题，`dp[i][0]` 和 `dp[i][1]`。

**状态转移方程**：
```
dp[i][0] = max(dp[i-1][0], dp[i-1][1] + prices[i-1])
dp[i][1] = max(dp[i-1][1], dp[i-1][0] - prices[i-1])  // 区别：可以多次买入
```

**核心区别**：持有股票的状态可以从之前的非持有状态转换而来。

**做题模板**：
```java
public int maxProfit(int[] prices) {
    int n = prices.length;
    int[][] dp = new int[n + 1][2];
    dp[0][0] = 0;
    dp[0][1] = Integer.MIN_VALUE;

    for (int i = 1; i <= n; i++) {
        dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1] + prices[i-1]);
        dp[i][1] = Math.max(dp[i-1][1], dp[i-1][0] - prices[i-1]);
    }
    return dp[n][0];
}
```

---

### 4.3 买卖股票的最佳时机含冷冻期（309. 买卖股票的最佳时机含冷冻期）

**题目描述**：卖出股票后有一天冷冻期，不能在卖出后第二天买入。

**DP 定义**：
- `dp[i][0]`：不持有股票
- `dp[i][1]`：持有股票
- `dp[i][2]`：处于冷冻期

**状态转移方程**：
```
dp[i][0] = max(dp[i-1][0], dp[i-1][1] + prices[i-1])
dp[i][1] = max(dp[i-1][1], dp[i-2][0] - prices[i-1])  // 冷冻期后买入
dp[i][2] = dp[i-1][1] + prices[i-1]  // 当天卖出
```

**做题模板**：
```java
public int maxProfit(int[] prices) {
    int n = prices.length;
    int[][] dp = new int[n + 2][2];
    dp[0][0] = 0; dp[0][1] = Integer.MIN_VALUE;
    dp[1][0] = 0; dp[1][1] = Integer.MIN_VALUE;

    for (int i = 2; i <= n + 1; i++) {
        dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1] + prices[i-2]);
        dp[i][1] = Math.max(dp[i-1][1], dp[i-2][0] - prices[i-2]);
    }
    return dp[n+1][0];
}
```

---

### 4.4 状态机 DP 总结

| 问题 | 状态数 | 核心区别 |
|------|--------|----------|
| 121. 买卖股票一次 | 2 | 持有时只能是 `-prices[i]` |
| 122. 买卖股票多次 | 2 | 持有时可以是 `dp[i-1][0] - prices[i]` |
| 309. 含冷冻期 | 3 | 买入需要参考 i-2 的状态 |

---

## 五、区间 DP

区间 DP 通过合并小区间的最优解来得到大区间的最优解，通常需要枚举分割点。

### 5.1 最长回文子序列（516. 最长回文子序列）

**题目描述**：找出字符串中最长回文子序列的长度。

**DP 定义**：`dp[i][j]` 表示字符串 s 从索引 i 到 j 的最长回文子序列长度。

**状态转移方程**：
```
若 s[i] == s[j]：
    dp[i][j] = dp[i+1][j-1] + 2  (i < j)
    dp[i][i] = 1
否则：
    dp[i][j] = max(dp[i+1][j], dp[i][j-1])
```

**遍历顺序**：从后往前遍历 i，从 i+1 往后遍历 j。

**做题模板**：
```java
public int longestPalindromeSubseq(String s) {
    int n = s.length();
    int[][] dp = new int[n][n];

    for (int i = 0; i < n; i++) {
        dp[i][i] = 1;
    }

    for (int i = n - 1; i >= 0; i--) {
        for (int j = i + 1; j < n; j++) {
            if (s.charAt(i) == s.charAt(j)) {
                dp[i][j] = dp[i+1][j-1] + 2;
            } else {
                dp[i][j] = Math.max(dp[i+1][j], dp[i][j-1]);
            }
        }
    }
    return dp[0][n-1];
}
```

---

### 5.2 编辑距离（72. 编辑距离）

**题目描述**：将 word1 转换成 word2 的最少操作数，可进行插入、删除、替换操作。

**DP 定义**：`dp[i][j]` 表示 word1 前 i 个字符转换成 word2 前 j 个字符的最少操作数。

**状态转移方程**：
```
若 word1[i-1] == word2[j-1]：
    dp[i][j] = dp[i-1][j-1]  // 无需操作
否则：
    dp[i][j] = min(
        dp[i-1][j] + 1,     // 删除
        dp[i][j-1] + 1,     // 插入
        dp[i-1][j-1] + 1   // 替换
    )
```

**边界条件**：
- `dp[i][0] = i`（删除 i 个字符）
- `dp[0][j] = j`（插入 j 个字符）

**做题模板**：
```java
public int minDistance(String word1, String word2) {
    int m = word1.length(), n = word2.length();
    int[][] dp = new int[m + 1][n + 1];

    for (int i = 0; i <= m; i++) dp[i][0] = i;
    for (int j = 0; j <= n; j++) dp[0][j] = j;

    for (int i = 1; i <= m; i++) {
        for (int j = 1; j <= n; j++) {
            if (word1.charAt(i-1) == word2.charAt(j-1)) {
                dp[i][j] = dp[i-1][j-1];
            } else {
                dp[i][j] = Math.min(
                    Math.min(dp[i-1][j], dp[i][j-1]),
                    dp[i-1][j-1]
                ) + 1;
            }
        }
    }
    return dp[m][n];
}
```

---

## 六、DP 的两种实现方法

动态规划主要有两种实现方法：**自顶向下（Memo 递归）**和**自底向上（DP 数组迭代）**。

### 6.1 自顶向下：Memo 递归（记忆化搜索）

**核心思想**：从问题出发，递归地尝试所有可能的选择，利用记忆化数组存储已经计算过的结果，避免重复计算。

**实现步骤**：
1. 定义递归函数 `dp(状态)`，返回该状态的最优解
2. 创建记忆化数组 `memo`，初始化为特殊值（如 -1）
3. 在递归函数中：
   - 首先检查 `memo` 是否已计算过，若有则直接返回
   - 否则，根据状态转移方程递归计算
   - 将结果存入 `memo` 并返回

**优点**：
- 思路直观，符合人类的自然思维
- 只需关注"当前状态如何由子状态推导"，无需确定遍历顺序

**缺点**：
- 需要考虑递归深度，可能导致栈溢出
- 有额外的递归调用开销

**通用模板**：
```java
class Solution {
    private int[] memo;  // 记忆化数组

    public int solve(参数) {
        memo = new int[状态数];
        Arrays.fill(memo, -1);  // 初始化为未计算状态
        return dp(初始状态);
    }

    private int dp(状态) {
        // 基础情况：递归终止条件
        if (状态达到边界) {
            return 边界值;
        }

        // 检查是否已计算过
        if (memo[状态] != -1) {
            return memo[状态];
        }

        // 状态转移：根据子状态计算当前状态
        memo[状态] = 计算公式;
        return memo[状态];
    }
}
```

**适用场景**：状态维度较少，递归深度不会过深的情况。

---

### 6.2 自底向上：DP 数组迭代

**核心思想**：从最小的问题规模开始，逐步推导出更大规模的问题解。

**实现步骤**：
1. 确定 DP 数组的含义和维度
2. 初始化边界条件（base case）
3. 确定遍历顺序（从前往后或从后往前）
4. 根据状态转移方程迭代计算
5. 返回最终结果

**优点**：
- 避免了递归调用，没有栈溢出风险
- 通常效率更高

**缺点**：
- 需要仔细确定遍历顺序
- 思维上不如递归直观

**通用模板**：
```java
class Solution {
    public int solve(参数) {
        // 1. 确定 DP 数组大小并创建
        int[] dp = new int[状态数 + 1];

        // 2. 初始化边界条件
        dp[0] = 边界值0;
        dp[1] = 边界值1;

        // 3. 遍历计算（顺序根据转移方程决定）
        for (int i = 2; i <= 状态数; i++) {
            // 4. 状态转移
            dp[i] = Math.max(dp[i-1], dp[i-2] + 其他);
        }

        // 5. 返回结果
        return dp[状态数];
    }
}
```

**适用场景**：所有动态规划问题，尤其适合状态维度较多的情况。

---

### 6.3 两种方法的对比

| 特征 | Memo 递归 | DP 数组迭代 |
|------|-----------|-------------|
| 思路 | 自顶向下 | 自底向上 |
| 实现方式 | 递归 + 记忆化 | 循环 + 数组 |
| 空间 | O(状态数) 栈空间 + O(状态数) memo | O(状态数) |
| 时间 | O(状态数 × 选择数) | O(状态数 × 选择数) |
| 优点 | 思路直观 | 无栈溢出风险，效率高 |
| 缺点 | 递归深度受限 | 需要确定遍历顺序 |

---

### 6.4 空间优化：滚动数组

对于某些 DP 问题，只需要保留前几个状态，可以使用滚动数组将空间复杂度从 O(n) 降低到 O(1)。

**示例：爬楼梯**
```java
// 原始 O(n) 空间
public int climbStairs(int n) {
    int[] dp = new int[n + 1];
    dp[1] = 1; dp[2] = 2;
    for (int i = 3; i <= n; i++) {
        dp[i] = dp[i-1] + dp[i-2];
    }
    return dp[n];
}

// 优化后 O(1) 空间
public int climbStairs(int n) {
    if (n == 1 || n == 2) return n;
    int a = 1, b = 2;
    for (int i = 3; i <= n; i++) {
        int c = a + b;
        a = b;
        b = c;
    }
    return b;
}
```

---

### 6.5 如何选择

1. **小数据量、状态复杂**：优先选择 Memo 递归，思路更清晰
2. **大数据量、状态简单**：选择 DP 数组迭代，效率更高
3. **面试写题**：两种都掌握，必要时可以互相验证
4. **空间优化**：优先考虑滚动数组

---

## 七、总结表格

| 题型 | 题目 | DP 定义 | 转移方程核心 |
|------|------|---------|---------------|
| 线性 DP | 70, 62, 198 | 一维数组 | 简单线性关系 |
| 背包 | 322, 416 | 一维/二维数组 | 选或不选 |
| 序列 DP | 300, 1143 | 前缀最优 | 遍历之前状态 |
| 状态机 | 121, 122, 309 | 多状态 | 状态转换 |
| 区间 DP | 516, 72 | 区间最优 | 合并小区间 |

---

## 七、做题通用模板

### 1. 确定 DP 数组含义
- 明确 `dp[i]` 或 `dp[i][j]` 代表什么

### 2. 写出状态转移方程
- 分析当前状态如何由之前状态推导

### 3. 确定遍历顺序
- 一维：从前往后
- 二维：根据依赖关系确定
- 背包：0-1 逆序，完全背包顺序

### 4. 初始化边界
- 处理空数组、边界值等情况

### 5. 返回结果
- 明确最终返回的是哪个状态

## 八、核心代码模板

```java
// 爬楼梯 - 一维线性DP
dp[0] = 0; dp[1] = 1; dp[2] = 2;
for (int i = 3; i < n + 1; i++) {
    dp[i] = dp[i - 1] + dp[i - 2];
}

```

```java
// 不同路径 - 二维线性DP
for (int i = 1; i < m; i++) {
    for (int j = 1; j < n; j++) {
        dp[i][j] = dp[i-1][j] + dp[i][j-1];
    }
}

```

```java
// 打家劫舍 - 一维线性DP
dp[0] = 0; dp[1] = nums[0];
for (int i = 2; i <= n; i++) {
    dp[i] = Math.max(dp[i-1], dp[i-2] + nums[i-1]);
}

```

```java
// 分割等和子集 - 0-1背包（逆序遍历）
for (int i = 0; i < nums.length; i++) {
    for (int j = target; j >= 0; j--) {
        if (j - nums[i] >= 0) {
            dp[j] = dp[j] || dp[j - nums[i]];
        }
    }
}

```

```java
// 零钱兑换 - 完全背包（正序遍历）
for (int i = 1; i <= amount; i++) {
    for (int coin : coins) {
        if (i - coin >= 0) {
            dp[i] = Math.min(dp[i], dp[i - coin] + 1);
        }
    }
}

```

```java
// 最长递增子序列 - 序列DP
for (int i = 1; i < nums.length; i++) {
    for (int j = 0; j < i; j++) {
        if (nums[j] < nums[i]) {
            dp[i] = Math.max(dp[i], dp[j] + 1);
        }
    }
}

```

```java
// 最长递增子序列 - 贪心+二分（进阶解法）
int[] top = new int[nums.length];
int piles = 0;
for (int num : nums) {
    int left = 0, right = piles;
    while (left < right) {
        int mid = left + (right - left) / 2;
        if (top[mid] < num) left = mid + 1;
        else right = mid;
    }
    if (left == piles) piles++;
    top[left] = num;
}

```

```java
// 最长公共子序列 - 序列DP
for (int i = 1; i <= m; i++) {
    for (int j = 1; j <= n; j++) {
        if (text1.charAt(i-1) == text2.charAt(j-1)) {
            dp[i][j] = dp[i-1][j-1] + 1;
        } else {
            dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
        }
    }
}

```

```java
// 买卖股票的最佳时机（一次交易）- 状态机DP
dp[0][0] = 0; dp[0][1] = Integer.MIN_VALUE;
for (int i = 1; i <= n; i++) {
    dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1] + prices[i-1]);
    dp[i][1] = Math.max(dp[i-1][1], -prices[i-1]);
}

```

```java
// 买卖股票的最佳时机 II（多次交易）- 状态机DP
dp[0][0] = 0; dp[0][1] = Integer.MIN_VALUE;
for (int i = 1; i <= n; i++) {
    dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1] + prices[i-1]);
    dp[i][1] = Math.max(dp[i-1][1], dp[i-1][0] - prices[i-1]);
}

```

```java
// 买卖股票的最佳时机含冷冻期 - 状态机DP
dp[0][0] = 0; dp[0][1] = Integer.MIN_VALUE;
dp[1][0] = 0; dp[1][1] = Integer.MIN_VALUE;
for (int i = 2; i <= n + 1; i++) {
    dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1] + prices[i-2]);
    dp[i][1] = Math.max(dp[i-1][1], dp[i-2][0] - prices[i-2]);
}

```

```java
// 最长回文子序列 - 区间DP
for (int i = 0; i < n; i++) {
    dp[i][i] = 1;
}
for (int i = n - 1; i >= 0; i--) {
    for (int j = i + 1; j < n; j++) {
        if (s.charAt(i) == s.charAt(j)) {
            dp[i][j] = dp[i+1][j-1] + 2;
        } else {
            dp[i][j] = Math.max(dp[i+1][j], dp[i][j-1]);
        }
    }
}

```

```java
// 编辑距离 - 区间DP
for (int i = 0; i <= m; i++) dp[i][0] = i;
for (int j = 0; j <= n; j++) dp[0][j] = j;
for (int i = 1; i <= m; i++) {
    for (int j = 1; j <= n; j++) {
        if (word1.charAt(i-1) == word2.charAt(j-1)) {
            dp[i][j] = dp[i-1][j-1];
        } else {
            dp[i][j] = Math.min(Math.min(dp[i-1][j], dp[i][j-1]), dp[i-1][j-1]) + 1;
        }
    }
}

```

```java
// Memo递归模板
private int[] memo;
private int dp(int 状态) {
    if (状态达到边界) return 边界值;
    if (memo[状态] != -1) return memo[状态];
    memo[状态] = dp[...]; //计算公式
    return memo[状态];
}

```

```java
// DP数组迭代模板
int[] dp = new int[状态数 + 1];
dp[0] = 边界值0;
dp[1] = 边界值1;
for (int i = 2; i <= 状态数; i++) {
    dp[i] = dp[...]; //计算公式
}

```

```java
// 空间优化 - 滚动数组
if (n == 1 || n == 2) return n;
int a = 1, b = 2;
for (int i = 3; i <= n; i++) {
    int c = a + b;
    a = b;
    b = c;
}
return b;

```
