package toOffer;

/*
 * @lc app=leetcode.cn id=400 lang=java
 * @lcpr version=30400
 *
 * [400] 第 N 位数字
 *
 * https://leetcode.cn/problems/nth-digit/description/
 *
 * algorithms
 * Medium (45.73%)
 * Likes:    438
 * Dislikes: 0
 * Total Accepted:    73.2K
 * Total Submissions: 160K
 * Testcase Example:  '3\n11'
 *
 * 给你一个整数 n ，请你在无限的整数序列 [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ...] 中找出并返回第 n
 * 位上的数字。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 输入：n = 3
 * 输出：3
 * 
 * 
 * 示例 2：
 * 
 * 输入：n = 11
 * 输出：0
 * 解释：第 11 位数字在序列 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ... 里是 0 ，它是 10 的一部分。
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 <= n <= 2^31 - 1
 * 
 * 
 */

// @lc code=start
class Solution {
    public int findNthDigit(int n) {
        if (n < 10) {
            return n;
        }
        int digit = 1;
        long start = 1, count = 9;
        while (n > count) {
            n -= count;
            digit++;
            start *= 10;
            count = 9 * digit * start;
        }
        long num = start + (n - 1) / digit;
        int index = (n - 1) % digit;
        return ("" + num).charAt(index) - '0';
    }
}
// @lc code=end



/*
// @lcpr case=start
// 3\n
// @lcpr case=end

// @lcpr case=start
// 11\n
// @lcpr case=end

 */

