/*
 * @lc app=leetcode.cn id=5 lang=java
 * @lcpr version=30307
 *
 * [5] 最长回文子串
 */

package leetcode.editor.problems.array;

// @lc code=start
class Solution {
    public String longestPalindrome(String s) {
        String res = "";
        int i = 0;
        while (i < s.length()) {
            String s1 = palindrome(s, i, i);
            String s2 = palindrome(s, i, i + 1);
            res = res.length() > s1.length() ? res : s1;
            res = res.length() > s2.length() ? res : s2;
            i++;
        }
        return res;
    }

    String palindrome(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return s.substring(left + 1, right);
    }
}
// @lc code=end

/*
 * // @lcpr case=start
 * // "babad"\n
 * // @lcpr case=end
 * 
 * // @lcpr case=start
 * // "cbbd"\n
 * // @lcpr case=end
 * 
 */
