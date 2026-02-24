/*
 * @lc app=leetcode.cn id=72 lang=java
 * @lcpr version=30400
 *
 * [72] 编辑距离
 *
 * https://leetcode.cn/problems/edit-distance/description/
 *
 * algorithms
 * Medium (64.06%)
 * Likes:    3852
 * Dislikes: 0
 * Total Accepted:    807.6K
 * Total Submissions: 1.3M
 * Testcase Example:  '"horse"\n"ros"\n"intention"\n"execution"'
 *
 * 给你两个单词 word1 和 word2， 请返回将 word1 转换成 word2 所使用的最少操作数  。
 * 
 * 你可以对一个单词进行如下三种操作：
 * 
 * 
 * 插入一个字符
 * 删除一个字符
 * 替换一个字符
 * 
 * 
 * 
 * 
 * 示例 1：
 * 
 * 输入：word1 = "horse", word2 = "ros"
 * 输出：3
 * 解释：
 * horse -> rorse (将 'h' 替换为 'r')
 * rorse -> rose (删除 'r')
 * rose -> ros (删除 'e')
 * 
 * 
 * 示例 2：
 * 
 * 输入：word1 = "intention", word2 = "execution"
 * 输出：5
 * 解释：
 * intention -> inention (删除 't')
 * inention -> enention (将 'i' 替换为 'e')
 * enention -> exention (将 'n' 替换为 'x')
 * exention -> exection (将 'n' 替换为 'c')
 * exection -> execution (插入 'u')
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 0 <= word1.length, word2.length <= 500
 * word1 和 word2 由小写英文字母组成
 * 
 * 
 */

// @lc code=start

import java.util.Arrays;

class Solution {
    private int[][] memo;

    public int minDistance(String word1, String word2) {
        int m = word1.length(), n = word2.length();
        
        // // 法1：memo
        // memo = new int[m][n];

        // for (int[] i : memo) {
        //     Arrays.fill(i, -1);
        // }

        // return dp(word1, m - 1, word2, n - 1);

        // 法2：dp数组
        int[][] dp = new int[m + 1][n + 1];
        
        for (int i = 0; i < m + 1; i++) {
            dp[i][0] = i;
        }

        for (int j = 0; j < n + 1; j++) {
            dp[0][j] = j;
        }

        for (int i = 1; i < m + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = min(dp[i - 1][j] + 1, dp[i][j - 1] + 1, dp[i - 1][j - 1] + 1);
                }
            }
        }

        return dp[m][n];
    }

    private int dp(String word1, int i, String word2, int j) {
        if (i == -1) {
            return j + 1;
        }

        if (j == -1) {
            return i + 1;
        }

        if (memo[i][j] != -1) {
            return memo[i][j];
        }

        if (word1.charAt(i) == word2.charAt(j)) {
            memo[i][j] = dp(word1, i - 1, word2, j - 1);
        } else {
            memo[i][j] = min(dp(word1, i - 1, word2, j) + 1, dp(word1, i, word2, j - 1) + 1, dp(word1, i - 1, word2, j - 1) + 1);
        }

        return memo[i][j];
    }

    private int min(int a, int b, int c) {
        return Math.min(a, Math.min(b, c));
    }
}
// @lc code=end



/*
// @lcpr case=start
// "horse"\n"ros"\n
// @lcpr case=end

// @lcpr case=start
// "intention"\n"execution"\n
// @lcpr case=end

 */

