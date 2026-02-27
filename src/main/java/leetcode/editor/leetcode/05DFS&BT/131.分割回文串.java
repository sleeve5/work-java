/*
 * @lc app=leetcode.cn id=131 lang=java
 * @lcpr version=30400
 *
 * [131] 分割回文串
 *
 * https://leetcode.cn/problems/palindrome-partitioning/description/
 *
 * algorithms
 * Medium (75.32%)
 * Likes:    2170
 * Dislikes: 0
 * Total Accepted:    725.9K
 * Total Submissions: 963.6K
 * Testcase Example:  '"aab"\n"a"'
 *
 * 给你一个字符串 s，请你将 s 分割成一些 子串，使每个子串都是 回文串 。返回 s 所有可能的分割方案。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 输入：s = "aab"
 * 输出：[["a","a","b"],["aa","b"]]
 * 
 * 
 * 示例 2：
 * 
 * 输入：s = "a"
 * 输出：[["a"]]
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 <= s.length <= 16
 * s 仅由小写英文字母组成
 * 
 * 
 */

// @lc code=start

import java.util.LinkedList;
import java.util.List;

class Solution {
    private List<List<String>> res = new LinkedList<>();
    private List<String> track = new LinkedList<>();

    public List<List<String>> partition(String s) {
        backtrack(s, 0);

        return res;
    }

    private void backtrack(String s, int start) {
        if (start == s.length()) {
            res.add(new LinkedList<String>(track));
            return;
        }

        for (int i = start; i < s.length(); i++) {
            if (!isPalindrome(s, start, i)) {
                continue;
            }

            track.addLast(s.substring(start, i + 1));
            backtrack(s, i + 1);
            track.removeLast();
        }
    }

    private boolean isPalindrome(String s, int start, int end) {
        while (start < end) {
            if (s.charAt(start) != s.charAt(end)) {
                return false;
            }
            start++;
            end--;
        }
        
        return true;
    }
}
// @lc code=end



/*
// @lcpr case=start
// "aab"\n
// @lcpr case=end

// @lcpr case=start
// "a"\n
// @lcpr case=end

 */

