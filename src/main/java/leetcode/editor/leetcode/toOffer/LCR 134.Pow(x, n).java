package toOffer;

/*
 * @lc app=leetcode.cn id=LCR 134 lang=java
 * @lcpr version=30400
 *
 * [LCR 134] Pow(x, n)
 *
 * https://leetcode.cn/problems/shu-zhi-de-zheng-shu-ci-fang-lcof/description/
 *
 * algorithms
 * Medium (35.27%)
 * Likes:    446
 * Dislikes: 0
 * Total Accepted:    258.9K
 * Total Submissions: 733.9K
 * Testcase Example:  '2.00000\n10\n2.10000\n3\n2.00000\n-2'
 *
 * 实现 pow(x, n) ，即计算 x 的 n 次幂函数（即，x^n）。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 输入：x = 2.00000, n = 10
 * 输出：1024.00000
 * 
 * 
 * 示例 2：
 * 
 * 输入：x = 2.10000, n = 3
 * 输出：9.26100
 * 
 * 示例 3：
 * 
 * 输入：x = 2.00000, n = -2
 * 输出：0.25000
 * 解释：2^-2 = 1/2^2 = 1/4 = 0.25
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * -100.0 < x < 100.0
 * -2^31 <= n <= 2^31-1
 * -10^4 <= x^n <= 10^4
 * 
 * 
 * 
 * 
 * 注意：本题与主站 50 题相同：https://leetcode.cn/problems/powx-n/
 * 
 * 
 * 
 */

// @lc code=start
class Solution {
    public double myPow(double x, int n) {
        long N = n;
        return N > 0 ? quickPow(x, N) : 1.0 / quickPow(x, -N);
    }
    
    private double quickPow(double x, long N) {
        if (N == 0) {
            return 1.0;
        }
        double y = quickPow(x, N / 2);
        return N % 2 == 0 ? y * y : y * y * x;
    }
}
// @lc code=end



/*
// @lcpr case=start
// 2.00000\n10\n
// @lcpr case=end

// @lcpr case=start
// 2.10000\n3\n
// @lcpr case=end

// @lcpr case=start
// 2.00000\n-2\n
// @lcpr case=end

 */

