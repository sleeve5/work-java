/*
 * @lc app=leetcode.cn id=79 lang=java
 * @lcpr version=30400
 *
 * [79] 单词搜索
 *
 * https://leetcode.cn/problems/word-search/description/
 *
 * algorithms
 * Medium (50.49%)
 * Likes:    2138
 * Dislikes: 0
 * Total Accepted:    836K
 * Total Submissions: 1.7M
 * Testcase Example:  '[["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]]\n' +
  '"ABCCED"\n' +
  '[["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]]\n' +
  '"SEE"\n' +
  '[["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]]\n' +
  '"ABCB"'
 *
 * 给定一个 m x n 二维字符网格 board 和一个字符串单词 word 。如果 word 存在于网格中，返回 true ；否则，返回 false
 * 。
 * 
 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 输入：board = [['A','B','C','E'],['S','F','C','S'],['A','D','E','E']], word =
 * "ABCCED"
 * 输出：true
 * 
 * 
 * 示例 2：
 * 
 * 输入：board = [['A','B','C','E'],['S','F','C','S'],['A','D','E','E']], word =
 * "SEE"
 * 输出：true
 * 
 * 
 * 示例 3：
 * 
 * 输入：board = [['A','B','C','E'],['S','F','C','S'],['A','D','E','E']], word =
 * "ABCB"
 * 输出：false
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * m == board.length
 * n = board[i].length
 * 1 <= m, n <= 6
 * 1 <= word.length <= 15
 * board 和 word 仅由大小写英文字母组成
 * 
 * 
 * 
 * 
 * 进阶：你可以使用搜索剪枝的技术来优化解决方案，使其在 board 更大的情况下可以更快解决问题？
 * 
 */

// @lc code=start

import java.nio.charset.Charset;

class Solution {
    private int[][] dirs = new int[][] {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    private boolean found = false;

    public boolean exist(char[][] board, String word) {
        int m = board.length, n = board[0].length;
        boolean[][] visited = new boolean[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dfs(board, word, i, j, 0, visited);
                if (found) {
                    return true;
                }
            }
        }

        return false;
    }

    private void dfs(char[][] board, String word, int i, int j, int p, boolean[][] visited){
        if (p == word.length()) {
            found = true;
            return;
        }

        if (found) {
            return;
        }

        int m = board.length, n = board[0].length;

        if (i < 0 || i >= m || j < 0 || j >= n) {
            return;
        }

        if (visited[i][j]) {
            return;
        }

        if (board[i][j] == word.charAt(p)) {
            for (int[] dir : dirs) {
                int next_i = i + dir[0];
                int next_j = j + dir[1];
                visited[i][j] = true;
                dfs(board, word, next_i, next_j, p + 1, visited);
                visited[i][j] = false;
            }
        }
        
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

