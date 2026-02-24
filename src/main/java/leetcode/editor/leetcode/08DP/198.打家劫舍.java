/*
 * @lc app=leetcode.cn id=198 lang=java
 * @lcpr version=30400
 *
 * [198] 打家劫舍
 *
 * https://leetcode.cn/problems/house-robber/description/
 *
 * algorithms
 * Medium (56.30%)
 * Likes:    3453
 * Dislikes: 0
 * Total Accepted:    1.5M
 * Total Submissions: 2.7M
 * Testcase Example:  '[1,2,3,1]\n[2,7,9,3,1]'
 *
 * 
 * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
 * 
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 输入：[1,2,3,1]
 * 输出：4
 * 解释：偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
 * 偷窃到的最高金额 = 1 + 3 = 4 。
 * 
 * 示例 2：
 * 
 * 输入：[2,7,9,3,1]
 * 输出：12
 * 解释：偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，接着偷窃 5 号房屋 (金额 = 1)。
 * 偷窃到的最高金额 = 2 + 9 + 1 = 12 。
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 <= nums.length <= 100
 * 0 <= nums[i] <= 400
 * 
 * 
 */

// @lc code=start

import java.util.Arrays;

class Solution {
    private int[] memo;

    public int rob(int[] nums) {
        int n = nums.length;

        if (n == 1) {
            return nums[0];
        }

        // int[] dp = new int[n + 1];
        // dp[0] = 0; dp[1] = nums[0];

        // for (int i = 2; i < n + 1; i++) {
        //     dp[i] = Math.max(dp[i - 1], nums[i - 1] + dp[i - 2]);
        // }

        // return dp[n];

        memo = new int[n + 1];
        Arrays.fill(memo, -1);
        return dp(n, nums);
    }

    private int dp(int n, int[] nums) {
        if (n < 1) {
            return 0;
        }

        if (memo[n] != -1) {
            return memo[n];
        }

        memo[n] = Math.max(dp(n - 1, nums), dp(n - 2, nums) + nums[n - 1]);
        
        return memo[n];
    }
}
// @lc code=end



/*
// @lcpr case=start
// [1,2,3,1]\n
// @lcpr case=end

// @lcpr case=start
// [2,7,9,3,1]\n
// @lcpr case=end

 */

