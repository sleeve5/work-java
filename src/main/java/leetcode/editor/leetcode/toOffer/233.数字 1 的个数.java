package toOffer;

/*
 * @lc app=leetcode.cn id=233 lang=java
 * @lcpr version=30400
 *
 * [233] 数字 1 的个数
 *
 * https://leetcode.cn/problems/number-of-digit-one/description/
 *
 * algorithms
 * Hard (49.76%)
 * Likes:    630
 * Dislikes: 0
 * Total Accepted:    76.4K
 * Total Submissions: 153.5K
 * Testcase Example:  '13\n0'
 *
 * 给定一个整数 n，计算所有小于等于 n 的非负整数中数字 1 出现的个数。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 输入：n = 13
 * 输出：6
 * 
 * 
 * 示例 2：
 * 
 * 输入：n = 0
 * 输出：0
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 0 <= n <= 10^9
 * 
 * 
 */

// @lc code=start
class Solution {
    public int countDigitOne(int n) {
        if (n == 0 || n == 1) {
            return n;
        }
        int cur = n % 10, low = 0, high = n / 10, digit = 1;
        int count = 0;
        
        while (high != 0 || cur != 0) {
            if (cur == 0) {
                count += digit * high;
            } else if (cur == 1) {
                count += digit * high + low + 1;
            } else {
                count += digit * (high + 1);
            }

            low += cur * digit;
            cur = high % 10;
            high /= 10;
            digit *= 10;
            
        }
        return count;
    }
}
// @lc code=end



/*
// @lcpr case=start
// 13\n
// @lcpr case=end

// @lcpr case=start
// 0\n
// @lcpr case=end

 */

