/*
 * @lc app=leetcode.cn id=20 lang=java
 * @lcpr version=30400
 *
 * [20] 有效的括号
 *
 * https://leetcode.cn/problems/valid-parentheses/description/
 *
 * algorithms
 * Easy (45.43%)
 * Likes:    4909
 * Dislikes: 0
 * Total Accepted:    2.5M
 * Total Submissions: 5.4M
 * Testcase Example:  '"()"\n"()[]{}"\n"(]"\n"([])"\n"([)]"'
 *
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
 * 
 * 有效字符串需满足：
 * 
 * 
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 * 每个右括号都有一个对应的相同类型的左括号。
 * 
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：s = "()"
 * 
 * 输出：true
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入：s = "()[]{}"
 * 
 * 输出：true
 * 
 * 
 * 示例 3：
 * 
 * 
 * 输入：s = "(]"
 * 
 * 输出：false
 * 
 * 
 * 示例 4：
 * 
 * 
 * 输入：s = "([])"
 * 
 * 输出：true
 * 
 * 
 * 示例 5：
 * 
 * 
 * 输入：s = "([)]"
 * 
 * 输出：false
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 <= s.length <= 10^4
 * s 仅由括号 '()[]{}' 组成
 * 
 * 
 */

// @lc code=start

import java.util.Stack;

class Solution {
    public boolean isValid(String s) {
        if (s.length() % 2 == 1) {
            return false;
        }

        Stack<Character> stk = new Stack<>();

        stk.push(s.charAt(0));

        for (int i = 1; i < s.length(); i++) {
            if (!stk.isEmpty()) {
                if (pair(stk.peek(), s.charAt(i))) {
                    stk.pop();
                    continue;
                }
            }
            stk.push(s.charAt(i));
        }

        return stk.isEmpty();
    }

    private boolean pair(char left, char right) {
        if (left == '(' && right == ')') {
            return true;
        }

        if (left == '[' && right == ']') {
            return true;
        }

        if (left == '{' && right == '}') {
            return true;
        }

        return false;
    }
}
// @lc code=end

/*
 * // @lcpr case=start
 * // "()"\n
 * // @lcpr case=end
 * 
 * // @lcpr case=start
 * // "()[]{}"\n
 * // @lcpr case=end
 * 
 * // @lcpr case=start
 * // "(]"\n
 * // @lcpr case=end
 * 
 * // @lcpr case=start
 * // "([])"\n
 * // @lcpr case=end
 * 
 * // @lcpr case=start
 * // "([)]"\n
 * // @lcpr case=end
 * 
 */
