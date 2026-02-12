package leetcode.editor.leetcode.toOffer;
/*
 * @lc app=leetcode.cn id=LCR 126 lang=java
 * @lcpr version=30307
 *
 * [LCR 126] 斐波那契数
 */

// @lc code=start
class Solution {
    public int fib(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        int f0 = 0, f1 = 1, fn = 0;
        for (int i = 2; i <= n; i++) {
            fn = (f0 + f1) % 1000000007;
            f0 = f1;
            f1 = fn;
        }
        return f1;
    }
}
// @lc code=end



/*
// @lcpr case=start
// 44\n
// @lcpr case=end

 */

