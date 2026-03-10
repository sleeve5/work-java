/*
 * @lc app=leetcode.cn id=739 lang=java
 * @lcpr version=30400
 *
 * [739] 每日温度
 *
 * https://leetcode.cn/problems/daily-temperatures/description/
 *
 * algorithms
 * Medium (69.83%)
 * Likes:    2122
 * Dislikes: 0
 * Total Accepted:    931.6K
 * Total Submissions: 1.3M
 * Testcase Example:  '[73,74,75,71,69,72,76,73]\n[30,40,50,60]\n[30,60,90]'
 *
 * 给定一个整数数组 temperatures ，表示每天的温度，返回一个数组 answer ，其中 answer[i] 是指对于第 i
 * 天，下一个更高温度出现在几天后。如果气温在这之后都不会升高，请在该位置用 0 来代替。
 * 
 * 
 * 
 * 示例 1:
 * 
 * 输入: temperatures = [73,74,75,71,69,72,76,73]
 * 输出: [1,1,4,2,1,1,0,0]
 * 
 * 
 * 示例 2:
 * 
 * 输入: temperatures = [30,40,50,60]
 * 输出: [1,1,1,0]
 * 
 * 
 * 示例 3:
 * 
 * 输入: temperatures = [30,60,90]
 * 输出: [1,1,0]
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 <= temperatures.length <= 10^5
 * 30 <= temperatures[i] <= 100
 * 
 * 
 */

// @lc code=start

import java.util.Stack;

class Solution {
    public int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length;
        int[] res = new int[n];

        Stack<Integer> stk = new Stack<>();

        for (int i = n - 1; i >= 0; i--) {
            while (!stk.isEmpty() && temperatures[stk.peek()] <= temperatures[i]) {
                stk.pop();
            }
            res[i] = stk.isEmpty() ? 0 : stk.peek() - i;
            stk.push(i);
        }

        return res;
    }
}
// @lc code=end

/*
 * // @lcpr case=start
 * // [73,74,75,71,69,72,76,73]\n
 * // @lcpr case=end
 * 
 * // @lcpr case=start
 * // [30,40,50,60]\n
 * // @lcpr case=end
 * 
 * // @lcpr case=start
 * // [30,60,90]\n
 * // @lcpr case=end
 * 
 */
