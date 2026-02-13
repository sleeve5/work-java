package toOffer;

/*
 * @lc app=leetcode.cn id=LCR 129 lang=java
 * @lcpr version=30307
 *
 * [LCR 129] 字母迷宫
 *
 * https://leetcode.cn/problems/ju-zhen-zhong-de-lu-jing-lcof/description/
 *
 * algorithms
 * Medium (45.76%)
 * Likes:    848
 * Dislikes: 0
 * Total Accepted:    363.1K
 * Total Submissions: 793.5K
 * Testcase Example:  '[["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]]\n' +
  '"ABCCED"\n' +
  '[["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]]\n' +
  '"SEE"\n' +
  '[["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]]\n' +
  '"ABCB"'
 *
 * 字母迷宫游戏初始界面记作 m x n 二维字符串数组 grid，请判断玩家是否能在 grid 中找到目标单词 target。
 * 注意：寻找单词时 必须 按照字母顺序，通过水平或垂直方向相邻的单元格内的字母构成，同时，同一个单元格内的字母 不允许被重复使用 。
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 示例 1：
 * 
 * 输入：grid = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], target =
 * "ABCCED"
 * 输出：true
 * 
 * 
 * 示例 2：
 * 
 * 输入：grid = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], target =
 * "SEE"
 * 输出：true
 * 
 * 
 * 示例 3：
 * 
 * 输入：grid = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], target =
 * "ABCB"
 * 输出：false
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * m == grid.length
 * n = grid[i].length
 * 1 <= m, n <= 6
 * 1 <= target.length <= 15
 * grid 和 target 仅由大小写英文字母组成
 * 
 * 
 * 
 * 
 * 注意：本题与主站 79 题相同：https://leetcode.cn/problems/word-search/
 * 
 * 
 * 
 * 
 * 
 */

// @lc code=start
class Solution {
    public boolean wordPuzzle(char[][] grid, String target) {
        int m = grid.length, n = grid[0].length;
        boolean [][] visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (target.charAt(0) == grid[i][j]) {
                    if (dfs(grid, i, j, visited,  target, 0)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean dfs(char[][] grid, int i, int j,boolean[][] visited, String target, int index) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] != target.charAt(index) || visited[i][j]) {
            return false;
        }
        if (index == target.length() - 1) {
            return true;
        }
        visited[i][j] = true;
        boolean res = dfs(grid, i - 1, j, visited, target, index + 1) || dfs(grid, i + 1, j, visited, target, index + 1) || dfs(grid, i, j - 1, visited, target, index + 1) || dfs(grid, i, j + 1, visited, target, index + 1);
        visited[i][j] = false;
        return res;
    }
}
// @lc code=end



/*
// @lcpr case=start
// [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]]\n"ABCCED"\n
// @lcpr case=end

// @lcpr case=start
// [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]]\n"SEE"\n
// @lcpr case=end

// @lcpr case=start
// [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]]\n"ABCB"\n
// @lcpr case=end

 */

