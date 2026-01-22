/*
 * @lc app=leetcode.cn id=264 lang=java
 * @lcpr version=30307
 *
 * [264] 丑数 II
 */

package leetcode.editor.problems.linkedlist;

// @lc code=start
class Solution {
    public int nthUglyNumber(int n) {
        int p2 = 1, p3 = 1, p5 = 1;
        int num2 = 1, num3 = 1, num5 = 1;
        int p = 1;
        int[] ugly = new int[n + 1];
        while (p <= n) {
            int min = Math.min(Math.min(num2, num3), num5);
            ugly[p] = min;
            p++;
            if (min == num2) {
                num2 = ugly[p2] * 2;
                p2++;
            }
            if (min == num3) {
                num3 = ugly[p3] * 3;
                p3++;
            }
            if (min == num5) {
                num5 = ugly[p5] * 5;
                p5++;
            }
        }
        return ugly[n];
    }
}
// @lc code=end

/*
 * // @lcpr case=start
 * // 10\n
 * // @lcpr case=end
 * 
 * // @lcpr case=start
 * // 1\n
 * // @lcpr case=end
 * 
 */
