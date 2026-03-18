# 数组与字符串算法题目分题型总结

> 本总结涵盖 01Array 文件夹中的所有数组与字符串算法题目

---

## 核心概念

数组与字符串算法主要涉及以下几种题型：
- **双指针技术**：在数组两端或同向移动指针解决问题
- **滑动窗口技术**：维护一个可变大小的窗口在数组上滑动
- **前缀和技巧**：利用前缀和快速计算区间和
- **N数之和问题**：通过排序和多指针解决组合求和问题

---

## 题目分类总览

| 题型 | 题目 | 核心技巧 |
|------|------|----------|
| **双指针** | 11. 盛最多水的容器 | 左右指针相向移动 |
| | 42. 接雨水 | 双指针/动态规划 |
| **滑动窗口** | 3. 无重复字符的最长子串 | 变长窗口 + 哈希表 |
| | 438. 找到字符串中所有字母异位词 | 定长窗口 + 哈希表 |
| **前缀和+哈希** | 560. 和为 K 的子数组 | 前缀和 + 哈希表计数 |
| **N数之和** | 15. 三数之和 | 排序 + 双指针 + 递归泛化 |

---

## 一、双指针问题

双指针技术分为两类：
1. **相向双指针**：左右指针从两端向中间移动（如盛最多水）
2. **同向双指针**：左右指针同向移动（如接雨水）

### 1.1 盛最多水的容器（11. 盛最多水的容器）

**题目描述**：给定一个整数数组 height，找到两条垂线使得它们与 x 轴构成的容器能容纳最多水。

**核心思想**：相向双指针，从两端向中间收敛，每次移动较短的那一边。

**关键点**：
- 容器面积 = (right - left) * min(height[left], height[right])
- 每次移动较短的那一边，因为移动较长的边不会使面积增大
- 时间复杂度：O(n)

**做题模板**：
```java
public int maxArea(int[] height) {
    int res = 0;
    int lo = 0, hi = height.length - 1;

    while (lo < hi) {
        // 计算当前面积
        res = Math.max(res, (hi - lo) * Math.min(height[lo], height[hi]));

        // 移动较短的那一边
        if (height[lo] < height[hi]) {
            lo++;
        } else {
            hi--;
        }
    }

    return res;
}
```

---

### 1.2 接雨水（42. 接雨水）

**题目描述**：给定 n 个非负整数表示每个柱子的高度图，计算下雨后能接多少雨水。

**核心思想**：双指针优化版，记录左右两侧的最大高度。

**关键点**：
- 每个位置能接的水 = min(左边最大高度, 右边最大高度) - 当前高度
- 双指针：l_max 和 r_max 分别记录左右两侧的最大高度
- 当 l_max < r_max 时，计算左边的雨水；否则计算右边的雨水

**做题模板**：
```java
public int trap(int[] height) {
    int res = 0;
    int l_max = 0, r_max = 0;
    int lo = 0, hi = height.length - 1;

    while (lo < hi) {
        l_max = Math.max(l_max, height[lo]);
        r_max = Math.max(r_max, height[hi]);

        if (l_max < r_max) {
            res += l_max - height[lo];
            lo++;
        } else {
            res += r_max - height[hi];
            hi--;
        }
    }

    return res;
}
```

---

### 1.3 双指针问题总结

| 题目 | 指针类型 | 核心技巧 | 时间复杂度 |
|------|---------|---------|-----------|
| 11. 盛最多水的容器 | 相向指针 | 移动较短边 | O(n) |
| 42. 接雨水 | 相向指针 | l_max/r_max 比较 | O(n) |

---

## 二、滑动窗口问题

滑动窗口是一种在数组或字符串上连续操作的技巧，窗口大小可以是固定的或变化的。

### 2.1 无重复字符的最长子串（3. 无重复字符的最长子串）

**题目描述**：找出不含有重复字符的最长子串的长度。

**核心思想**：变长滑动窗口，使用哈希表记录窗口中每个字符出现的次数。

**关键点**：
- 右指针扩展窗口，左指针收缩窗口
- 当窗口中出现重复字符时，移动左指针直到窗口无重复
- 使用 HashMap 记录字符出现次数

**做题模板**：
```java
public int lengthOfLongestSubstring(String s) {
    Map<Character, Integer> window = new HashMap<>();
    int lo = 0, hi = 0;
    int res = 0;

    while (hi < s.length()) {
        char c = s.charAt(hi);
        hi++;
        window.put(c, window.getOrDefault(c, 0) + 1);

        // 窗口中有重复字符，收缩左边界
        while (window.get(c) > 1) {
            char d = s.charAt(lo);
            lo++;
            window.put(d, window.get(d) - 1);
        }

        res = Math.max(res, hi - lo);
    }

    return res;
}
```

---

### 2.2 找到字符串中所有字母异位词（438. 找到字符串中所有字母异位词）

**题目描述**：给定字符串 s 和 p，找到 s 中所有 p 的异位词的子串起始索引。

**核心思想**：定长滑动窗口，维护窗口内字符计数。

**关键点**：
- 异位词：字符种类和数量相同但顺序不同
- 使用 need 和 window 两个哈希表
- valid 变量记录满足条件的字符种类数
- 窗口大小固定为 p.length()

**做题模板**：
```java
public List<Integer> findAnagrams(String s, String p) {
    Map<Character, Integer> need = new HashMap<>();
    Map<Character, Integer> window = new HashMap<>();

    // 初始化 need 哈希表
    for (char c : p.toCharArray()) {
        need.put(c, need.getOrDefault(c, 0) + 1);
    }

    List<Integer> res = new ArrayList<>();
    int left = 0, right = 0;
    int valid = 0;

    while (right < s.length()) {
        char c = s.charAt(right);
        right++;

        // 扩展窗口
        if (need.containsKey(c)) {
            window.put(c, window.getOrDefault(c, 0) + 1);
            if (need.get(c).equals(window.get(c))) {
                valid++;
            }
        }

        // 收缩窗口（定长窗口）
        while (right - left >= p.length()) {
            if (valid == need.size()) {
                res.add(left);
            }

            char d = s.charAt(left);
            left++;

            if (need.containsKey(d)) {
                if (need.get(d).equals(window.get(d))) {
                    valid--;
                }
                window.put(d, window.get(d) - 1);
            }
        }
    }

    return res;
}
```

---

### 2.3 滑动窗口问题总结

| 题目 | 窗口类型 | 核心技巧 | 关键变量 |
|------|---------|---------|----------|
| 3. 无重复字符的最长子串 | 变长窗口 | 收缩左边界去重 | HashMap 计数 |
| 438. 找到字符串中所有字母异位词 | 定长窗口 | 固定窗口大小 | valid 计数 |

### 滑动窗口通用模板

**变长窗口**：
```java
int left = 0, right = 0;
while (right < s.length()) {
    // 扩展窗口
    window.add(s.charAt(right));
    right++;

    // 收缩窗口（根据条件）
    while (需要收缩) {
        window.remove(s.charAt(left));
        left++;
    }

    // 更新结果
    res = Math.max(res, right - left);
}
```

**定长窗口**：
```java
int left = 0, right = 0;
while (right < s.length()) {
    // 扩展窗口
    window.add(s.charAt(right));
    right++;

    // 收缩窗口（当窗口达到固定大小时）
    while (right - left >= 固定长度) {
        // 处理窗口
        window.remove(s.charAt(left));
        left++;
    }
}
```

---

## 三、前缀和 + 哈希表问题

前缀和技巧可以将区间和查询从 O(n) 优化到 O(1)，结合哈希表可以快速统计满足条件的子数组。

### 3.1 和为 K 的子数组（560. 和为 K 的子数组）

**题目描述**：统计数组中和为 k 的子数组的个数。

**核心思想**：前缀和 + 哈希表。

**关键点**：
- 前缀和：preSum[i] = nums[0] + nums[1] + ... + nums[i-1]
- 子数组 nums[i:j] 的和 = preSum[j+1] - preSum[i]
- 如果 preSum[j+1] - preSum[i] = k，则 preSum[i] = preSum[j+1] - k
- 使用哈希表记录每个前缀和出现的次数

**做题模板**：
```java
public int subarraySum(int[] nums, int k) {
    int sum = 0;
    HashMap<Integer, Integer> map = new HashMap<>();
    map.put(0, 1);  // 前缀和为 0 出现 1 次

    int res = 0;

    for (int num : nums) {
        sum += num;  // 当前前缀和
        int need = sum - k;  // 需要的前缀和

        // 如果存在 need，说明有子数组和为 k
        if (map.containsKey(need)) {
            res += map.get(need);
        }

        // 记录当前前缀和出现的次数
        map.put(sum, map.getOrDefault(sum, 0) + 1);
    }

    return res;
}
```

---

### 3.2 前缀和 + 哈希表总结

| 题目 | 核心技巧 | 关键点 |
|------|---------|--------|
| 560. 和为 K 的子数组 | 前缀和 + 哈希计数 | map.put(0, 1) 初始化 |

---

## 四、N 数之和问题

N 数之和是经典的算法问题，可以通过排序和双指针高效解决。

### 4.1 三数之和（15. 三数之和）

**题目描述**：找出数组中所有和为 0 的三元组，要求不能重复。

**核心思想**：排序 + 双指针 + 递归泛化。

**关键点**：
- 先排序：便于跳过重复元素
- 固定一个数，双指针寻找另外两个数
- 使用递归实现 nSumTarget 函数，可以推广到任意 n 数之和
- 跳过重复元素避免重复解

**做题模板**：
```java
public List<List<Integer>> threeSum(int[] nums) {
    Arrays.sort(nums);
    return nSumTarget(nums, 3, 0, 0);
}

// 泛化的 nSum 函数
private List<List<Integer>> nSumTarget(int[] nums, int n, int start, int target) {
    int sz = nums.length;
    List<List<Integer>> res = new ArrayList<>();

    if (n < 2 || sz < n) {
        return res;
    }

    if (n == 2) {
        // 双指针求两数之和
        int lo = start, hi = sz - 1;
        while (lo < hi) {
            int left = nums[lo], right = nums[hi];
            int sum = left + right;

            if (sum == target) {
                res.add(new ArrayList<>(Arrays.asList(left, right)));
                while (lo < hi && nums[lo] == left) lo++;
                while (lo < hi && nums[hi] == right) hi--;
            } else if (sum < target) {
                while (lo < hi && nums[lo] == left) lo++;
            } else {
                while (lo < hi && nums[hi] == right) hi--;
            }
        }
    } else {
        // 递归处理 n > 2 的情况
        for (int i = start; i < sz; i++) {
            List<List<Integer>> sub = nSumTarget(nums, n - 1, i + 1, target - nums[i]);
            for (List<Integer> arr : sub) {
                arr.add(nums[i]);
                res.add(arr);
            }
            while (i < sz - 1 && nums[i] == nums[i + 1]) {
                i++;
            }
        }
    }

    return res;
}
```

---

### 4.2 N 数之和问题总结

| 题目 | 核心技巧 | 关键点 |
|------|---------|--------|
| 15. 三数之和 | 排序 + 双指针 + 递归 | nSumTarget 泛化解法 |

---

## 五、总结表格

### 算法技巧对比

| 题型 | 题目 | 时间复杂度 | 空间复杂度 | 核心技巧 |
|------|------|-----------|-----------|----------|
| 双指针 | 11. 盛最多水的容器 | O(n) | O(1) | 相向移动 |
| 双指针 | 42. 接雨水 | O(n) | O(1) | l_max/r_max |
| 滑动窗口 | 3. 无重复字符的最长子串 | O(n) | O(min(n,charset)) | 变长窗口 |
| 滑动窗口 | 438. 找到字符串中所有字母异位词 | O(n) | O(1) | 定长窗口 |
| 前缀和 | 560. 和为 K 的子数组 | O(n) | O(n) | 前缀和+哈希 |
| N数之和 | 15. 三数之和 | O(n²) | O(n) | 排序+双指针 |

---

## 六、核心代码模板汇总

### 模板一：相向双指针（盛最多水）
```java
int lo = 0, hi = nums.length - 1;
while (lo < hi) {
    res = Math.max(res, (hi - lo) * Math.min(nums[lo], nums[hi]));
    if (nums[lo] < nums[hi]) {
        lo++;
    } else {
        hi--;
    }
}
```

### 模板二：双指针求雨水
```java
int l_max = 0, r_max = 0;
int lo = 0, hi = nums.length - 1;
while (lo < hi) {
    l_max = Math.max(l_max, nums[lo]);
    r_max = Math.max(r_max, nums[hi]);
    if (l_max < r_max) {
        res += l_max - nums[lo];
        lo++;
    } else {
        res += r_max - nums[hi];
        hi--;
    }
}
```

### 模板三：变长滑动窗口
```java
Map<Character, Integer> window = new HashMap<>();
int left = 0, right = 0;
while (right < s.length()) {
    char c = s.charAt(right);
    right++;
    window.put(c, window.getOrDefault(c, 0) + 1);

    while (window.get(c) > 1) {  // 收缩条件
        char d = s.charAt(left);
        left++;
        window.put(d, window.get(d) - 1);
    }

    res = Math.max(res, right - left);
}
```

### 模板四：定长滑动窗口
```java
Map<Character, Integer> need = new HashMap<>();
Map<Character, Integer> window = new HashMap<>();
for (char c : p.toCharArray()) {
    need.put(c, need.getOrDefault(c, 0) + 1);
}

int left = 0, right = 0, valid = 0;
while (right < s.length()) {
    char c = s.charAt(right);
    right++;

    if (need.containsKey(c)) {
        window.put(c, window.getOrDefault(c, 0) + 1);
        if (need.get(c).equals(window.get(c))) {
            valid++;
        }
    }

    while (right - left >= 窗口大小) {
        if (valid == need.size()) {
            res.add(left);
        }

        char d = s.charAt(left);
        left++;

        if (need.containsKey(d)) {
            if (need.get(d).equals(window.get(d))) {
                valid--;
            }
            window.put(d, window.get(d) - 1);
        }
    }
}
```

### 模板五：前缀和 + 哈希表
```java
HashMap<Integer, Integer> map = new HashMap<>();
map.put(0, 1);  // 初始化

int sum = 0;
for (int num : nums) {
    sum += num;
    int need = sum - k;

    if (map.containsKey(need)) {
        res += map.get(need);
    }

    map.put(sum, map.getOrDefault(sum, 0) + 1);
}
```

### 模板六：N 数之和（泛化解法）
```java
public List<List<Integer>> nSumTarget(int[] nums, int n, int start, int target) {
    int sz = nums.length;
    List<List<Integer>> res = new ArrayList<>();

    if (n < 2 || sz < n) return res;

    if (n == 2) {
        int lo = start, hi = sz - 1;
        while (lo < hi) {
            int left = nums[lo], right = nums[hi];
            int sum = left + right;

            if (sum == target) {
                res.add(Arrays.asList(left, right));
                while (lo < hi && nums[lo] == left) lo++;
                while (lo < hi && nums[hi] == right) hi--;
            } else if (sum < target) {
                while (lo < hi && nums[lo] == left) lo++;
            } else {
                while (lo < hi && nums[hi] == right) hi--;
            }
        }
    } else {
        for (int i = start; i < sz; i++) {
            List<List<Integer>> sub = nSumTarget(nums, n - 1, i + 1, target - nums[i]);
            for (List<Integer> arr : sub) {
                arr.add(nums[i]);
                res.add(arr);
            }
            while (i < sz - 1 && nums[i] == nums[i + 1]) i++;
        }
    }

    return res;
}
```

---

## 七、解题思维总结

### 何时使用双指针？
- 数组有序或需要从两端处理
- 需要 O(1) 空间复杂度
- 典型题目：盛最多水、接雨水

### 何时使用滑动窗口？
- 需要在连续区间上操作
- 寻找满足条件的最大/最小子串
- 典型题目：无重复字符最长子串、字母异位词

### 何时使用前缀和 + 哈希？
- 求子数组和为某值的问题
- 需要快速计算区间和
- 典型题目：和为 K 的子数组

### 何时使用 N 数之和解法？
- 多数之和问题（如 3Sum, 4Sum）
- 需要去重
- 典型题目：三数之和
