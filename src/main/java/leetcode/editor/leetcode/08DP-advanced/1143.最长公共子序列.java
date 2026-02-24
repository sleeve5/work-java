/*
 * @lc app=leetcode.cn id=1143 lang=java
 * @lcpr version=30400
 *
 * [1143] 最长公共子序列
 *
 * https://leetcode.cn/problems/longest-common-subsequence/description/
 *
 * algorithms
 * Medium (67.47%)
 * Likes:    1861
 * Dislikes: 0
 * Total Accepted:    778.4K
 * Total Submissions: 1.2M
 * Testcase Example:  '"abcde"\n"ace"\n"abc"\n"abc"\n"abc"\n"def"'
 *
 * 给定两个字符串 text1 和 text2，返回这两个字符串的最长 公共子序列 的长度。如果不存在 公共子序列 ，返回 0 。
 * 
 * 一个字符串的 子序列
 * 是指这样一个新的字符串：它是由原字符串在不改变字符的相对顺序的情况下删除某些字符（也可以不删除任何字符）后组成的新字符串。
 * 
 * 
 * 例如，"ace" 是 "abcde" 的子序列，但 "aec" 不是 "abcde" 的子序列。
 * 
 * 
 * 两个字符串的 公共子序列 是这两个字符串所共同拥有的子序列。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 输入：text1 = "abcde", text2 = "ace" 
 * 输出：3  
 * 解释：最长公共子序列是 "ace" ，它的长度为 3 。
 * 
 * 
 * 示例 2：
 * 
 * 输入：text1 = "abc", text2 = "abc"
 * 输出：3
 * 解释：最长公共子序列是 "abc" ，它的长度为 3 。
 * 
 * 
 * 示例 3：
 * 
 * 输入：text1 = "abc", text2 = "def"
 * 输出：0
 * 解释：两个字符串没有公共子序列，返回 0 。
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 <= text1.length, text2.length <= 1000
 * text1 和 text2 仅由小写英文字符组成。
 * 
 * 
 */

// @lc code=start

import java.util.Arrays;

class Solution {
    private int[][] memo;

    public int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length(), n = text2.length();

        // // 方法1：memo
        // memo = new int[m][n];

        // for (int[] i : memo) {
        //     Arrays.fill(i, -1);
        // }

        // return dp(text1, 0, text2, 0);

        // 方法2：dp数组
        int[][] dp = new int[m + 1][n + 1];

        for (int i = 1; i < m + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = max(dp[i - 1][j], dp[i][j - 1], dp[i - 1][j - 1]);
                }
            }
        }

        return dp[m][n];
    }

    private int dp(String text1, int i, String text2, int j) {
        if (text1.length() == i || text2.length() == j) {
            return 0;
        }

        if (memo[i][j] != -1) {
            return memo[i][j];
        }

        if (text1.charAt(i) == text2.charAt(j)) {
            memo[i][j] = dp(text1, i + 1, text2, j + 1) + 1;
        } else {
            memo[i][j] = max(dp(text1, i + 1, text2, j), dp(text1, i, text2, j + 1), dp(text1, i + 1, text2, j + 1));
        }

        return memo[i][j];
    }

    private int max(int a, int b, int c) {
        return Math.max(a, Math.max(b, c));
    }
}
// @lc code=end



/*
// @lcpr case=start
// "abcde"\n"ace"\n
// @lcpr case=end

// @lcpr case=start
// "abc"\n"abc"\n
// @lcpr case=end

// @lcpr case=start
// "abc"\n"def"\n
// @lcpr case=end

 */

