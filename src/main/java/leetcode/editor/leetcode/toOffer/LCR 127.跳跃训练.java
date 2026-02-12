package toOffer;

/*
 * @lc app=leetcode.cn id=LCR 127 lang=java
 * @lcpr version=30307
 *
 * [LCR 127] 跳跃训练
 *
 * https://leetcode.cn/problems/qing-wa-tiao-tai-jie-wen-ti-lcof/description/
 *
 * algorithms
 * Easy (45.04%)
 * Likes:    428
 * Dislikes: 0
 * Total Accepted:    417.3K
 * Total Submissions: 926.5K
 * Testcase Example:  '2\n5'
 *
 * 今天的有氧运动训练内容是在一个长条形的平台上跳跃。平台有 num 个小格子，每次可以选择跳 一个格子 或者
 * 两个格子。请返回在训练过程中，学员们共有多少种不同的跳跃方式。
 * 
 * 结果可能过大，因此结果需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
 * 
 * 示例 1：
 * 
 * 输入：n = 2
 * 输出：2
 * 
 * 
 * 示例 2：
 * 
 * 输入：n = 5
 * 输出：8
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 0 <= n <= 100
 * 
 * 
 * 注意：本题与主站 70 题相同：https://leetcode.cn/problems/climbing-stairs/
 * 
 */

// @lc code=start
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
// @lc code=end



/*
// @lcpr case=start
// 2\n
// @lcpr case=end

// @lcpr case=start
// 5\n
// @lcpr case=end

 */

