/*
 * @lc app=leetcode.cn id=56 lang=java
 * @lcpr version=30401
 *
 * [56] 合并区间
 *
 * https://leetcode.cn/problems/merge-intervals/description/
 *
 * algorithms
 * Medium (53.09%)
 * Likes:    2763
 * Dislikes: 0
 * Total Accepted:    1.5M
 * Total Submissions: 2.8M
 * Testcase Example:  '[[1,3],[2,6],[8,10],[15,18]]\n[[1,4],[4,5]]\n[[4,7],[1,4]]'
 *
 * 以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi]
 * 。请你合并所有重叠的区间，并返回 一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间 。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 输入：intervals = [[1,3],[2,6],[8,10],[15,18]]
 * 输出：[[1,6],[8,10],[15,18]]
 * 解释：区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
 * 
 * 
 * 示例 2：
 * 
 * 输入：intervals = [[1,4],[4,5]]
 * 输出：[[1,5]]
 * 解释：区间 [1,4] 和 [4,5] 可被视为重叠区间。
 * 
 * 示例 3：
 * 
 * 输入：intervals = [[4,7],[1,4]]
 * 输出：[[1,7]]
 * 解释：区间 [1,4] 和 [4,7] 可被视为重叠区间。
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 <= intervals.length <= 10^4
 * intervals[i].length == 2
 * 0 <= starti <= endi <= 10^4
 * 
 * 
 */

// @lc code=start

import java.util.*;
import java.util.Arrays;

class Solution {
    public int[][] merge(int[][] intervals) {
        if (intervals.length <= 1) {
            return intervals;
        }

        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        List<int[]> res = new LinkedList<>();
        res.add(intervals[0]);

        for (int i = 1; i < intervals.length; i++) {
            int[] curr = intervals[i];
            int[] last = res.get(res.size() - 1);

            if (curr[0] <= last[1]) {
                last[1] = Math.max(curr[1], last[1]);
            } else {
                res.add(curr);
            }
        }

        return res.toArray(new int[0][]);
    }
}
// @lc code=end

/*
 * // @lcpr case=start
 * // [[1,3],[2,6],[8,10],[15,18]]\n
 * // @lcpr case=end
 * 
 * // @lcpr case=start
 * // [[1,4],[4,5]]\n
 * // @lcpr case=end
 * 
 * // @lcpr case=start
 * // [[4,7],[1,4]]\n
 * // @lcpr case=end
 * 
 */
