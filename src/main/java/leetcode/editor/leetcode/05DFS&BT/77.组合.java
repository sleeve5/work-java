/*
 * @lc app=leetcode.cn id=77 lang=java
 * @lcpr version=30400
 *
 * [77] 组合
 *
 * https://leetcode.cn/problems/combinations/description/
 *
 * algorithms
 * Medium (77.69%)
 * Likes:    1824
 * Dislikes: 0
 * Total Accepted:    986.3K
 * Total Submissions: 1.3M
 * Testcase Example:  '4\n2\n1\n1'
 *
 * 给定两个整数 n 和 k，返回范围 [1, n] 中所有可能的 k 个数的组合。
 * 
 * 你可以按 任何顺序 返回答案。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 输入：n = 4, k = 2
 * 输出：
 * [
 * ⁠ [2,4],
 * ⁠ [3,4],
 * ⁠ [2,3],
 * ⁠ [1,2],
 * ⁠ [1,3],
 * ⁠ [1,4],
 * ]
 * 
 * 示例 2：
 * 
 * 输入：n = 1, k = 1
 * 输出：[[1]]
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 <= n <= 20
 * 1 <= k <= n
 * 
 * 
 */

// @lc code=start

import java.util.LinkedList;
import java.util.List;

class Solution {
    private List<List<Integer>> res = new LinkedList<>();
    private LinkedList<Integer> track = new LinkedList<>();

    public List<List<Integer>> combine(int n, int k) {
        backtrack(0, n, k);

        return res;
    }

    private void backtrack(int start, int n, int k) {
        if (track.size() == k) {
            res.add(new LinkedList<>(track));
            return;
        }

        for (int i = start; i < n; i++) {
            track.addLast(i + 1);
            backtrack(i + 1, n, k);
            track.removeLast();
        }
    }
}
// @lc code=end



/*
// @lcpr case=start
// 4\n2\n
// @lcpr case=end

// @lcpr case=start
// 1\n1\n
// @lcpr case=end

 */

