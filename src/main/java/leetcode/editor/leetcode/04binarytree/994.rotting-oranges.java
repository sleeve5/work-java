/*
 * @lc app=leetcode.cn id=994 lang=java
 * @lcpr version=30401
 *
 * [994] 腐烂的橘子
 *
 * https://leetcode.cn/problems/rotting-oranges/description/
 *
 * algorithms
 * Medium (55.43%)
 * Likes:    1146
 * Dislikes: 0
 * Total Accepted:    436K
 * Total Submissions: 785.8K
 * Testcase Example:  '[[2,1,1],[1,1,0],[0,1,1]]\n[[2,1,1],[0,1,1],[1,0,1]]\n[[0,2]]'
 *
 * 在给定的 m x n 网格 grid 中，每个单元格可以有以下三个值之一：
 * 
 * 
 * 值 0 代表空单元格；
 * 值 1 代表新鲜橘子；
 * 值 2 代表腐烂的橘子。
 * 
 * 
 * 每分钟，腐烂的橘子 周围 4 个方向上相邻 的新鲜橘子都会腐烂。
 * 
 * 返回 直到单元格中没有新鲜橘子为止所必须经过的最小分钟数。如果不可能，返回 -1 。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 
 * 输入：grid = [[2,1,1],[1,1,0],[0,1,1]]
 * 输出：4
 * 
 * 
 * 示例 2：
 * 
 * 输入：grid = [[2,1,1],[0,1,1],[1,0,1]]
 * 输出：-1
 * 解释：左下角的橘子（第 2 行， 第 0 列）永远不会腐烂，因为腐烂只会发生在 4 个方向上。
 * 
 * 
 * 示例 3：
 * 
 * 输入：grid = [[0,2]]
 * 输出：0
 * 解释：因为 0 分钟时已经没有新鲜橘子了，所以答案就是 0 。
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 10
 * grid[i][j] 仅为 0、1 或 2
 * 
 * 
 */

// @lc code=start

import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public int orangesRotting(int[][] grid) {
        if (grid.length == 0) {
            return 0;
        }

        Queue<int[]> q = new LinkedList<>();
        int m = grid.length, n = grid[0].length;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 2) {
                    q.add(new int[] { i, j });
                }
            }
        }

        int[][] dirs = new int[][] { { 0, -1 }, { 0, 1 }, { -1, 0 }, { 1, 0 } };
        int minute = 0;

        while (!q.isEmpty()) {
            int size = q.size();

            for (int i = 0; i < size; i++) {
                int[] curr = q.poll();

                for (int[] dir : dirs) {
                    int x = dir[0] + curr[0];
                    int y = dir[1] + curr[1];
                    if (x >= 0 && x < m && y >= 0 && y < n && grid[x][y] == 1) {
                        grid[x][y] = 2;
                        q.add(new int[] { x, y });
                    }
                }
            }

            minute++;
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    return -1;
                }
            }
        }

        return minute == 0 ? 0 : minute - 1;
    }
}
// @lc code=end

/*
 * // @lcpr case=start
 * // [[2,1,1],[1,1,0],[0,1,1]]\n
 * // @lcpr case=end
 * 
 * // @lcpr case=start
 * // [[2,1,1],[0,1,1],[1,0,1]]\n
 * // @lcpr case=end
 * 
 * // @lcpr case=start
 * // [[0,2]]\n
 * // @lcpr case=end
 * 
 */
