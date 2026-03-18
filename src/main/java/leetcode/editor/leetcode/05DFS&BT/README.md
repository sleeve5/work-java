# DFS 与回溯算法题目分题型总结

> 本总结涵盖 05DFS&BT 文件夹中的所有 DFS 和回溯算法题目

---

## 核心概念：回溯算法

回溯算法本质上是一种**暴力搜索**方法，通过穷举所有可能的解来找到满足条件的解。其核心思想是：**在搜索过程中尝试分步解决问题，当发现当前路径无法得到有效解时，就"回溯"返回上一步尝试其他路径**。

### 回溯算法三要素

| 要素 | 说明 |
|------|------|
| **路径** | 已做出的选择（track） |
| **选择列表** | 当前可以做的选择 |
| **结束条件** | 达到决策树底层，无法再做选择 |

### 回溯算法通用模板

```java
private List<List<Integer>> res = new LinkedList<>();
private LinkedList<Integer> track = new LinkedList<>();

public List<List<Integer>> solve(参数) {
    backtrack(参数, 起始位置);
    return res;
}

private void backtrack(参数, int start) {
    // 终止条件：找到一个合法解
    if (终止条件) {
        res.add(new LinkedList<>(track));
        return;
    }

    // 遍历选择列表
    for (int i = start; i < 选择范围; i++) {
        // 做选择
        track.addLast(选择);

        // 递归进入下一层决策树
        backtrack(参数, i + 1);  // 或 i（可重复选取）

        // 撤销选择
        track.removeLast();
    }
}
```

---

## 排列组合子集问题分类

根据**元素是否重复**和**是否可重复选取**两个维度，可分为三类：

| 分类 | 元素特点 | 选取规则 | 核心技巧 |
|------|---------|---------|----------|
| **一、元素无重不可复选** | 无重复元素 | 每个元素最多选一次 | start 参数 / used 数组 |
| **二、元素可重不可复选** | 有重复元素 | 每个元素最多选一次 | 排序 + 跳过重复 |
| **三、元素无重可复选** | 无重复元素 | 每个元素可选多次 | 递归传 i（而非 i+1） |

---

## 一、元素无重不可复选

这类问题是回溯算法的基础，元素没有重复，每个元素只能使用一次。

### 1.1 组合（77. 组合）

**题目描述**：给定两个整数 n 和 k，返回范围 [1, n] 中所有可能的 k 个数的组合。

**核心思想**：使用 start 参数控制选择范围，避免重复组合。

**关键点**：
- 使用 `start` 参数确保不重复选取
- 终止条件：`track.size() == k`

**做题模板**：
```java
private List<List<Integer>> res = new LinkedList<>();
private LinkedList<Integer> track = new LinkedList<>();

public List<List<Integer>> combine(int n, int k) {
    backtrack(0, n, k);
    return res;
}

private void backtrack(int start, int n, int k) {
    if (track.size() == k) {
        res.add(new LinkedList<>(track));
        return;
    }

    for (int i = start; i < n; i++) {
        track.addLast(i + 1);
        backtrack(i + 1, n, k);  // i + 1：确保不重复选取
        track.removeLast();
    }
}
```

---

### 1.2 子集（78. 子集）

**题目描述**：给定不含重复元素的整数数组 nums，返回该数组所有可能的子集。

**核心思想**：每个节点都是一个合法的子集，无需等到终止条件才收集结果。

**关键点**：
- **每个节点都收集结果**：进入函数就添加到 res
- 使用 `start` 参数避免重复

**做题模板**：
```java
private List<List<Integer>> res = new LinkedList<>();
private LinkedList<Integer> track = new LinkedList<>();

public List<List<Integer>> subsets(int[] nums) {
    backtrack(nums, 0);
    return res;
}

private void backtrack(int[] nums, int start) {
    res.add(new LinkedList<>(track));  // 每个节点都是一个子集

    for (int i = start; i < nums.length; i++) {
        track.addLast(nums[i]);
        backtrack(nums, i + 1);
        track.removeLast();
    }
}
```

---

### 1.3 全排列（46. 全排列）

**题目描述**：给定不含重复数字的数组 nums，返回其所有可能的全排列。

**核心思想**：使用 `used` 数组记录已使用的元素，每次从头遍历但跳过已使用的元素。

**关键点**：
- 使用 `boolean[] used` 数组标记已使用元素
- 不需要 `start` 参数，每次从头遍历
- 终止条件：`track.size() == nums.length`

**做题模板**：
```java
private List<List<Integer>> res = new LinkedList<>();
private LinkedList<Integer> track = new LinkedList<>();
private boolean[] used;

public List<List<Integer>> permute(int[] nums) {
    used = new boolean[nums.length];
    backtrack(nums);
    return res;
}

private void backtrack(int[] nums) {
    if (track.size() == nums.length) {
        res.add(new LinkedList<>(track));
        return;
    }

    for (int i = 0; i < nums.length; i++) {
        if (used[i]) {
            continue;  // 跳过已使用的元素
        }

        track.addLast(nums[i]);
        used[i] = true;
        backtrack(nums);
        track.removeLast();
        used[i] = false;
    }
}
```

---

### 1.4 元素无重不可复选总结

| 题目 | 问题类型 | 核心技巧 | 终止条件 |
|------|---------|---------|----------|
| 77. 组合 | 组合 | start 参数 | track.size() == k |
| 78. 子集 | 子集 | 每个节点收集 | 遍历完所有元素 |
| 46. 全排列 | 排列 | used 数组 | track.size() == nums.length |

**核心区别**：
- 组合：顺序不重要，使用 `start` 参数控制
- 子集：顺序不重要，每个节点都收集结果
- 排列：顺序重要，使用 `used` 数组控制

---

## 二、元素可重不可复选

这类问题在元素无重不可复选的基础上，增加了**去重**的步骤。核心是：**先排序，再跳过同一层的重复元素**。

### 2.1 子集 II（90. 子集 II）

**题目描述**：给定可能包含重复元素的整数数组 nums，返回该数组所有可能的子集。解集不能包含重复的子集。

**核心思想**：先排序，再使用跳过重复元素的技巧去重。

**关键点**：
- **必须先排序**：`Arrays.sort(nums)`
- **去重条件**：`i > start && nums[i] == nums[i - 1]`

**做题模板**：
```java
private List<List<Integer>> res = new LinkedList<>();
private LinkedList<Integer> track = new LinkedList<>();

public List<List<Integer>> subsetsWithDup(int[] nums) {
    Arrays.sort(nums);  // 必须先排序！
    backtrack(nums, 0);
    return res;
}

private void backtrack(int[] nums, int start) {
    res.add(new LinkedList<>(track));

    for (int i = start; i < nums.length; i++) {
        // 去重：跳过同一层重复元素
        if (i > start && nums[i] == nums[i - 1]) {
            continue;
        }

        track.addLast(nums[i]);
        backtrack(nums, i + 1);
        track.removeLast();
    }
}
```

---

### 2.2 组合总和 II（40. 组合总和 II）

**题目描述**：给定可能包含重复元素的数组 candidates 和目标数 target，找出所有和为 target 的组合。**每个数字在每个组合中只能使用一次**。

**核心思想**：先排序，再使用跳过重复元素的技巧去重。

**关键点**：
- **必须先排序**：`Arrays.sort(candidates)`
- **去重条件**：`i > start && candidates[i] == candidates[i - 1]`
- 不可重复选取：递归传入 `i + 1`

**做题模板**：
```java
private List<List<Integer>> res = new LinkedList<>();
private LinkedList<Integer> track = new LinkedList<>();
private int trackSum = 0;

public List<List<Integer>> combinationSum2(int[] candidates, int target) {
    Arrays.sort(candidates);  // 必须先排序！
    backtrack(candidates, 0, target);
    return res;
}

private void backtrack(int[] candidates, int start, int target) {
    if (trackSum == target) {
        res.add(new LinkedList<>(track));
        return;
    }

    if (trackSum > target) {
        return;
    }

    for (int i = start; i < candidates.length; i++) {
        // 去重：跳过同一层重复元素
        if (i > start && candidates[i] == candidates[i - 1]) {
            continue;
        }

        track.addLast(candidates[i]);
        trackSum += candidates[i];
        backtrack(candidates, i + 1, target);  // i + 1：不可重复选取
        trackSum -= candidates[i];
        track.removeLast();
    }
}
```

---

### 2.3 全排列 II（47. 全排列 II）

**题目描述**：给定可包含重复数字的序列 nums，返回所有不重复的全排列。

**核心思想**：先排序，再使用 `used` 数组配合跳过重复元素的技巧去重。

**关键点**：
- **必须先排序**
- **去重条件**：`i > 0 && nums[i] == nums[i - 1] && !used[i - 1]`
  - `!used[i - 1]` 表示前一个相同元素刚被撤销选择（回溯回来）
  - 此时再选择当前元素会产生重复排列，需要跳过

**做题模板**：
```java
private List<List<Integer>> res = new LinkedList<>();
private LinkedList<Integer> track = new LinkedList<>();
private boolean[] used;

public List<List<Integer>> permuteUnique(int[] nums) {
    Arrays.sort(nums);  // 必须先排序！
    used = new boolean[nums.length];
    backtrack(nums);
    return res;
}

private void backtrack(int[] nums) {
    if (track.size() == nums.length) {
        res.add(new LinkedList<>(track));
        return;
    }

    for (int i = 0; i < nums.length; i++) {
        if (used[i]) {
            continue;
        }

        // 去重：跳过重复元素
        if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) {
            continue;
        }

        track.addLast(nums[i]);
        used[i] = true;
        backtrack(nums);
        track.removeLast();
        used[i] = false;
    }
}
```

---

### 2.4 元素可重不可复选总结

| 题目 | 问题类型 | 去重方式 | 核心区别 |
|------|---------|----------|----------|
| 90. 子集 II | 子集 | 排序 + `i > start && nums[i] == nums[i-1]` | 每个节点收集 |
| 40. 组合总和 II | 组合 | 排序 + `i > start && nums[i] == nums[i-1]` | 有限定 target |
| 47. 全排列 II | 排列 | 排序 + `i > 0 && nums[i] == nums[i-1] && !used[i-1]` | used 数组 |

### 去重原理详解

**组合/子集去重**（同一层不能重复）：
```java
if (i > start && nums[i] == nums[i - 1]) {
    continue;  // 跳过
}
```
**原理**：同一层中，如果当前元素与前一个元素相同，说明前一个元素已经处理过所有可能的情况，当前元素会产生重复解。

**排列去重**（同一位置不能重复）：
```java
if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) {
    continue;  // 跳过
}
```
**原理**：`!used[i - 1]` 表示前一个相同元素刚被撤销选择（回溯回来），此时选择当前元素会得到与前一个元素相同的排列。

---

## 三、元素无重可复选

这类问题在元素无重不可复选的基础上，允许**重复选取**同一个元素。核心是：**递归时传入 i（而非 i+1）**。

### 3.1 组合总和（39. 组合总和）

**题目描述**：给定无重复元素的整数数组 candidates 和目标整数 target，找出 candidates 中所有和为 target 的组合。**同一数字可以无限制重复被选取**。

**核心思想**：允许重复选取，递归时传入 `i` 而非 `i + 1`。

**关键点**：
- 可重复选取：`backtrack(candidates, i, target)`（注意是 `i` 不是 `i + 1`）
- 剪枝优化：当 `trackSum > target` 时提前返回

**做题模板**：
```java
private List<List<Integer>> res = new LinkedList<>();
private LinkedList<Integer> track = new LinkedList<>();
private int trackSum = 0;

public List<List<Integer>> combinationSum(int[] candidates, int target) {
    backtrack(candidates, 0, target);
    return res;
}

private void backtrack(int[] candidates, int start, int target) {
    if (trackSum == target) {
        res.add(new LinkedList<>(track));
        return;
    }

    if (trackSum > target) {
        return;  // 剪枝
    }

    for (int i = start; i < candidates.length; i++) {
        track.addLast(candidates[i]);
        trackSum += candidates[i];
        backtrack(candidates, i, target);  // i：可重复选取
        trackSum -= candidates[i];
        track.removeLast();
    }
}
```

---

### 3.2 元素无重可复选总结

| 题目 | 问题类型 | 核心技巧 | 与不可复选的区别 |
|------|---------|---------|-----------------|
| 39. 组合总和 | 组合 | 递归传 `i` | 递归传 `i+1` → 传 `i` |

**关键区别**：
- 不可复选：`backtrack(nums, i + 1)` — 确保每个元素只选一次
- 可复选：`backtrack(nums, i)` — 允许同一元素重复选取

---

## 四、网格 DFS 问题

网格遍历问题的特点：在二维网格上进行 DFS 搜索，常用于岛屿类问题。

### 4.1 岛屿数量（200. 岛屿数量）

**题目描述**：给定由 '1'（陆地）和 '0'（水）组成的二维网格，计算网格中岛屿的数量。

**核心思想**：遍历网格，遇到陆地就进行 DFS 将整个岛屿"淹没"（标记为水），同时计数器加一。

**关键点**：
- 遇到 '1' 就计数并 DFS
- DFS 过程中将访问过的陆地标记为 '0'（避免重复访问）
- 四个方向递归搜索

**做题模板**：
```java
private int[][] directions = new int[][] {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
private int res = 0;

public int numIslands(char[][] grid) {
    int m = grid.length, n = grid[0].length;
    for (int i = 0; i < m; i++) {
        for (int j = 0; j < n; j++) {
            if (grid[i][j] == '1') {
                res++;
                dfs(grid, i, j);
            }
        }
    }
    return res;
}

private void dfs(char[][] grid, int i, int j) {
    int m = grid.length, n = grid[0].length;

    if (i < 0 || i >= m || j < 0 || j >= n) {
        return;
    }

    if (grid[i][j] == '0') {
        return;
    }

    grid[i][j] = '0';

    for (int[] dir : directions) {
        dfs(grid, i + dir[0], j + dir[1]);
    }
}
```

---

### 4.2 岛屿的最大面积（695. 岛屿的最大面积）

**题目描述**：给定二进制矩阵 grid，计算 grid 中最大的岛屿面积。

**核心思想**：DFS 返回当前岛屿的面积，取所有岛屿面积的最大值。

**关键点**：
- DFS 函数返回岛屿面积
- 递归计算四个方向的面积并求和

**做题模板**：
```java
public int maxAreaOfIsland(int[][] grid) {
    int m = grid.length, n = grid[0].length;
    int res = 0;

    for (int i = 0; i < m; i++) {
        for (int j = 0; j < n; j++) {
            if (grid[i][j] == 1) {
                res = Math.max(res, dfs(grid, i, j));
            }
        }
    }
    return res;
}

private int dfs(int[][] grid, int i, int j) {
    int m = grid.length, n = grid[0].length;

    if (i < 0 || i >= m || j < 0 || j >= n) {
        return 0;
    }

    if (grid[i][j] == 0) {
        return 0;
    }

    grid[i][j] = 0;

    return 1 + dfs(grid, i + 1, j) + dfs(grid, i - 1, j)
             + dfs(grid, i, j + 1) + dfs(grid, i, j - 1);
}
```

---

### 4.3 网格 DFS 模板总结

```java
private int[][] directions = new int[][] {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

private void dfs(网格, int i, int j) {
    // 1. 边界检查
    if (i < 0 || i >= m || j < 0 || j >= n) {
        return;
    }

    // 2. 已访问或不符合条件
    if (已访问 || 不符合条件) {
        return;
    }

    // 3. 标记已访问
    标记(i, j);

    // 4. 四个方向递归
    for (int[] dir : directions) {
        dfs(网格, i + dir[0], j + dir[1]);
    }
}
```

---

## 五、其他回溯应用

### 5.1 分割回文串（131. 分割回文串）

**题目描述**：给定字符串 s，将 s 分割成一些子串，使每个子串都是回文串。返回 s 所有可能的分割方案。

**核心思想**：回溯 + 判断回文，每次尝试一个回文前缀，然后递归处理剩余部分。

**关键点**：
- 使用 `start` 参数表示当前分割位置
- 每次尝试从 `start` 到 `i` 的子串是否为回文
- 终止条件：`start == s.length()`

**做题模板**：
```java
private List<List<String>> res = new LinkedList<>();
private LinkedList<String> track = new LinkedList<>();

public List<List<String>> partition(String s) {
    backtrack(s, 0);
    return res;
}

private void backtrack(String s, int start) {
    if (start == s.length()) {
        res.add(new LinkedList<>(track));
        return;
    }

    for (int i = start; i < s.length(); i++) {
        if (!isPalindrome(s, start, i)) {
            continue;
        }

        track.addLast(s.substring(start, i + 1));
        backtrack(s, i + 1);
        track.removeLast();
    }
}

private boolean isPalindrome(String s, int start, int end) {
    while (start < end) {
        if (s.charAt(start) != s.charAt(end)) {
            return false;
        }
        start++;
        end--;
    }
    return true;
}
```

---

### 5.2 单词搜索（79. 单词搜索）

**题目描述**：给定二维字符网格 board 和字符串单词 word，判断 word 是否存在于网格中。同一单元格内的字母不允许被重复使用。

**核心思想**：DFS 搜索 + 访问标记，从每个位置开始尝试匹配单词。

**关键点**：
- 使用 `visited` 数组标记已访问位置
- 使用 `p` 参数表示当前匹配到单词的第几个字符
- 找到匹配后可以提前返回

**做题模板**：
```java
private int[][] dirs = new int[][] {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
private boolean found = false;

public boolean exist(char[][] board, String word) {
    int m = board.length, n = board[0].length;
    boolean[][] visited = new boolean[m][n];

    for (int i = 0; i < m; i++) {
        for (int j = 0; j < n; j++) {
            dfs(board, word, i, j, 0, visited);
            if (found) {
                return true;
            }
        }
    }
    return false;
}

private void dfs(char[][] board, String word, int i, int j, int p, boolean[][] visited) {
    if (p == word.length()) {
        found = true;
        return;
    }

    if (found) {
        return;
    }

    int m = board.length, n = board[0].length;

    if (i < 0 || i >= m || j < 0 || j >= n) {
        return;
    }

    if (visited[i][j]) {
        return;
    }

    if (board[i][j] == word.charAt(p)) {
        visited[i][j] = true;
        for (int[] dir : dirs) {
            dfs(board, word, i + dir[0], j + dir[1], p + 1, visited);
        }
        visited[i][j] = false;
    }
}
```

---

## 六、总结表格

### 排列组合子集问题分类汇总

| 分类 | 题目 | 问题类型 | 核心技巧 |
|------|------|---------|----------|
| **元素无重不可复选** | 77. 组合 | 组合 | start 参数 |
| | 78. 子集 | 子集 | 每个节点收集 |
| | 46. 全排列 | 排列 | used 数组 |
| **元素可重不可复选** | 90. 子集 II | 子集 | 排序 + 跳过重复 |
| | 40. 组合总和 II | 组合 | 排序 + 跳过重复 + target |
| | 47. 全排列 II | 排列 | 排序 + used + 跳过重复 |
| **元素无重可复选** | 39. 组合总和 | 组合 | 递归传 i |

### 其他问题汇总

| 题目 | 问题类型 | 核心技巧 |
|------|---------|----------|
| 200. 岛屿数量 | 网格 DFS | 标记已访问 |
| 695. 岛屿的最大面积 | 网格 DFS | 返回面积 |
| 131. 分割回文串 | 分割+判断 | 回文判断 |
| 79. 单词搜索 | 网格搜索 | visited 数组 |

---

## 七、核心代码模板汇总

### 模板一：组合/子集（元素无重不可复选）
```java
private void backtrack(int[] nums, int start) {
    // 子集：每个节点收集
    res.add(new LinkedList<>(track));

    for (int i = start; i < nums.length; i++) {
        track.addLast(nums[i]);
        backtrack(nums, i + 1);  // i + 1：不可复选
        track.removeLast();
    }
}
```

### 模板二：组合/子集（元素可重不可复选）
```java
Arrays.sort(nums);  // 必须先排序！

private void backtrack(int[] nums, int start) {
    res.add(new LinkedList<>(track));

    for (int i = start; i < nums.length; i++) {
        if (i > start && nums[i] == nums[i - 1]) {
            continue;  // 跳过重复
        }
        track.addLast(nums[i]);
        backtrack(nums, i + 1);
        track.removeLast();
    }
}
```

### 模板三：组合（元素无重可复选）
```java
private void backtrack(int[] nums, int start, int target) {
    if (trackSum == target) {
        res.add(new LinkedList<>(track));
        return;
    }

    for (int i = start; i < nums.length; i++) {
        track.addLast(nums[i]);
        trackSum += nums[i];
        backtrack(nums, i, target);  // i：可复选
        trackSum -= nums[i];
        track.removeLast();
    }
}
```

### 模板四：排列（元素无重不可复选）
```java
private void backtrack(int[] nums) {
    if (track.size() == nums.length) {
        res.add(new LinkedList<>(track));
        return;
    }

    for (int i = 0; i < nums.length; i++) {
        if (used[i]) {
            continue;
        }
        track.addLast(nums[i]);
        used[i] = true;
        backtrack(nums);
        track.removeLast();
        used[i] = false;
    }
}
```

### 模板五：排列（元素可重不可复选）
```java
Arrays.sort(nums);  // 必须先排序！

private void backtrack(int[] nums) {
    if (track.size() == nums.length) {
        res.add(new LinkedList<>(track));
        return;
    }

    for (int i = 0; i < nums.length; i++) {
        if (used[i]) {
            continue;
        }
        if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) {
            continue;  // 跳过重复
        }
        track.addLast(nums[i]);
        used[i] = true;
        backtrack(nums);
        track.removeLast();
        used[i] = false;
    }
}
```

### 模板六：网格 DFS
```java
private int[][] directions = new int[][] {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

private void dfs(char[][] grid, int i, int j) {
    int m = grid.length, n = grid[0].length;
    if (i < 0 || i >= m || j < 0 || j >= n) return;
    if (grid[i][j] == '0') return;

    grid[i][j] = '0';

    for (int[] dir : directions) {
        dfs(grid, i + dir[0], j + dir[1]);
    }
}
```
