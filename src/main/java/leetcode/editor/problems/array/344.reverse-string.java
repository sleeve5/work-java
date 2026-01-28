/*
 * @lc app=leetcode.cn id=344 lang=java
 * @lcpr version=30307
 *
 * [344] 反转字符串
 */

package leetcode.editor.problems.array;

// @lc code=start
class Solution {
    public void reverseString(char[] s) {
        int left = 0, right = s.length - 1;
        while (left < right) {
            char c = s[left];
            s[left] = s[right];
            s[right] = c;
            left++;
            right--;
        }
    }
}
// @lc code=end

/*
 * // @lcpr case=start
 * // ["h","e","l","l","o"]\n
 * // @lcpr case=end
 * 
 * // @lcpr case=start
 * // ["H","a","n","n","a","h"]\n
 * // @lcpr case=end
 * 
 */
