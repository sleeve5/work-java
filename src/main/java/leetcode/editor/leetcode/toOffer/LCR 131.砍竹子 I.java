package toOffer;

/*
 * @lc app=leetcode.cn id=LCR 131 lang=java
 * @lcpr version=30307
 *
 * [LCR 131] 砍竹子 I
 *
 * https://leetcode.cn/problems/jian-sheng-zi-lcof/description/
 *
 * algorithms
 * Medium (57.36%)
 * Likes:    633
 * Dislikes: 0
 * Total Accepted:    313.2K
 * Total Submissions: 546K
 * Testcase Example:  '12'
 *
 * 现需要将一根长为正整数 bamboo_len 的竹子砍为若干段，每段长度均为正整数。请返回每段竹子长度的最大乘积是多少。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 输入: bamboo_len = 12
 * 输出: 81
 * 
 * 提示：
 * 
 * 
 * 2 <= bamboo_len <= 58
 * 
 * 
 * 注意：本题与主站 343 题相同：https://leetcode.cn/problems/integer-break/
 * 
 */

// @lc code=start
class Solution {
    public int cuttingBamboo(int bamboo_len) {
        int[] dp = new int[bamboo_len + 1];
        if (bamboo_len == 0 || bamboo_len == 1) {
            return bamboo_len;
        }
        if (bamboo_len == 2) {
            return 1;
        }
        if (bamboo_len == 3) {
            return 2;
        }
        dp[0] = 1; dp[1] = 1; dp[2] = 2; dp[3] = 3;
        for (int i = 4; i < bamboo_len + 1; i++) {
            dp[i] = Math.max(dp[i - 2] * 2, dp[i - 3] * 3);
        }
        return dp[bamboo_len];
    }
}
// @lc code=end



/*
// @lcpr case=start
// 12\n
// @lcpr case=end

 */

