/*
 * @lc app=leetcode.cn id=42 lang=java
 * @lcpr version=30400
 *
 * [42] 接雨水
 *
 * https://leetcode.cn/problems/trapping-rain-water/description/
 *
 * algorithms
 * Hard (65.86%)
 * Likes:    6117
 * Dislikes: 0
 * Total Accepted:    1.7M
 * Total Submissions: 2.5M
 * Testcase Example:  '[0,1,0,2,1,0,1,3,2,1,2,1]\n[4,2,0,3,2,5]'
 *
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 
 * 输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
 * 输出：6
 * 解释：上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。 
 * 
 * 
 * 示例 2：
 * 
 * 输入：height = [4,2,0,3,2,5]
 * 输出：9
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * n == height.length
 * 1 <= n <= 2 * 10^4
 * 0 <= height[i] <= 10^5
 * 
 * 
 */

// @lc code=start
class Solution {
    public int trap(int[] height) {
        int res = 0;
        int n = height.length;

        //// 1.暴力
        // for (int i = 1; i < n - 1; i++) {
        // int l_max = 0, r_max = 0;

        // for (int j = i; j < n; j++) {
        // r_max = Math.max(r_max, height[j]);
        // }

        // for (int j = i; j >= 0; j--) {
        // l_max = Math.max(l_max, height[j]);
        // }

        // res += Math.min(l_max, r_max) - height[i];
        // }

        //// 2.备忘录
        // int[] l_max = new int[n];
        // int[] r_max = new int[n];

        // l_max[0] = height[0];
        // r_max[n - 1] = height[n - 1];

        // for (int i = 1; i < n; i++) {
        // l_max[i] = Math.max(height[i], l_max[i - 1]);
        // }

        // for (int i = n - 2; i >= 0; i--) {
        // r_max[i] = Math.max(height[i], r_max[i + 1]);
        // }

        // for (int i = 1; i < n - 1; i++) {
        // res += Math.min(l_max[i], r_max[i]) - height[i];
        // }

        // 3.双指针
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
}
// @lc code=end

/*
 * // @lcpr case=start
 * // [0,1,0,2,1,0,1,3,2,1,2,1]\n
 * // @lcpr case=end
 * 
 * // @lcpr case=start
 * // [4,2,0,3,2,5]\n
 * // @lcpr case=end
 * 
 */
