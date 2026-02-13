package toOffer;

/*
 * @lc app=leetcode.cn id=LCR 130 lang=java
 * @lcpr version=30307
 *
 * [LCR 130] 衣橱整理
 *
 * https://leetcode.cn/problems/ji-qi-ren-de-yun-dong-fan-wei-lcof/description/
 *
 * algorithms
 * Medium (53.57%)
 * Likes:    688
 * Dislikes: 0
 * Total Accepted:    343.5K
 * Total Submissions: 641.1K
 * Testcase Example:  '4\n7\n5'
 *
 * 家居整理师将待整理衣橱划分为 m x n 的二维矩阵 grid，其中 grid[i][j] 代表一个需要整理的格子。整理师自 grid[0][0] 开始
 * 逐行逐列 地整理每个格子。
 * 
 * 整理规则为：在整理过程中，可以选择 向右移动一格 或 向下移动一格，但不能移动到衣柜之外。同时，不需要整理 digit(i) + digit(j) >
 * cnt 的格子，其中 digit(x) 表示数字 x 的各数位之和。
 * 
 * 请返回整理师 总共需要整理多少个格子。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 输入：m = 4, n = 7, cnt = 5
 * 输出：18
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 <= n, m <= 100
 * 0 <= cnt <= 20
 * 
 * 
 * 
 * 
 */

// @lc code=start
class Solution {
    public int wardrobeFinishing(int m, int n, int cnt) {
        boolean[][] visited = new boolean[m][n];       
        return dfs(0, 0, m, n, visited, cnt);
    }
    
    private int dfs(int i, int j, int m, int n, boolean[][] visited, int cnt) {
        if (i >= m || j >= n || visited[i][j] || digit(i) + digit(j) > cnt) {
            return 0;
        }
        visited[i][j] = true;
        return dfs(i + 1, j, m, n, visited, cnt) + dfs(i, j + 1, m, n, visited, cnt) + 1;
    }

    private int digit(int x) {
        int res = 0;
        while (true) {
            res += x % 10;
            x = x / 10;
            if (x == 0) {
                break;
            }
        }
        return res;
    }
}
// @lc code=end



/*
// @lcpr case=start
// 4\n7\n5\n
// @lcpr case=end

 */

