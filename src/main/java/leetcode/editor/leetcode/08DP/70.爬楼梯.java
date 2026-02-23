/*
 * @lc app=leetcode.cn id=70 lang=java
 * @lcpr version=30400
 *
 * [70] 爬楼梯
 *
 * https://leetcode.cn/problems/climbing-stairs/description/
 *
 * algorithms
 * Easy (55.59%)
 * Likes:    3999
 * Dislikes: 0
 * Total Accepted:    2.1M
 * Total Submissions: 3.8M
 * Testcase Example:  '2\n3'
 *
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 * 
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 * 
 * 
 * 
 * 示例 1：
 * 
 * 输入：n = 2
 * 输出：2
 * 解释：有两种方法可以爬到楼顶。
 * 1. 1 阶 + 1 阶
 * 2. 2 阶
 * 
 * 示例 2：
 * 
 * 输入：n = 3
 * 输出：3
 * 解释：有三种方法可以爬到楼顶。
 * 1. 1 阶 + 1 阶 + 1 阶
 * 2. 1 阶 + 2 阶
 * 3. 2 阶 + 1 阶
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 <= n <= 45
 * 
 * 
 */

// @lc code=start

import java.util.Arrays;

class Solution {
    private int[] memo;
    public int climbStairs(int n) {
        if (n == 1 || n == 2) {
            return n;
        }
        // 方法1：dp数组迭代法
        // int[] dp = new int[n + 1];
        // dp[0] = 0; dp[1] = 1; dp[2] = 2;
        // for (int i = 3; i < n + 1; i++) {
        //     dp[i] = dp[i - 1] + dp[i - 2];
        // }
        // return dp[n];

        // 方法2：滚动变量优化的dp数组迭代法
        // int a = 1, b = 2; int c = 0;
        // for (int i = 0; i < n - 2; i++) {
        //     c = a + b;
        //     a = b;
        //     b = c;
        // }
        // return b;

        // 方法3：memo备忘录递归
        memo = new int[n + 1];
        Arrays.fill(memo, -1);
        return dp(n);
    }

    private int dp(int n) {
        if (n == 1 || n == 2) {
            return n;
        }
        if (memo[n] != -1) {
            return memo[n];
        }
        memo[n] = dp(n - 1) + dp(n - 2);
        return memo[n];
    }
}
// @lc code=end



/*
// @lcpr case=start
// 2\n
// @lcpr case=end

// @lcpr case=start
// 3\n
// @lcpr case=end

 */

