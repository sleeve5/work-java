package toOffer;

/*
 * @lc app=leetcode.cn id=LCR 122 lang=java
 * @lcpr version=30307
 *
 * [LCR 122] 路径加密
 *
 * https://leetcode.cn/problems/ti-huan-kong-ge-lcof/description/
 *
 * algorithms
 * Easy (74.92%)
 * Likes:    588
 * Dislikes: 0
 * Total Accepted:    760.3K
 * Total Submissions: 1M
 * Testcase Example:  '"a.aef.qerf.bb"'
 *
 * 假定一段路径记作字符串 path，其中以 "." 作为分隔符。现需将路径加密，加密方法为将 path 中的分隔符替换为空格 "
 * "，请返回加密后的字符串。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 输入：path = "a.aef.qerf.bb"
 * 
 * 输出："a aef qerf bb"
 * 
 * 
 * 
 * 
 * 
 * 限制：
 * 
 * 0 <= path.length <= 10000
 * 
 */

// @lc code=start
class Solution {
    public String pathEncryption(String path) {
        StringBuilder res = new StringBuilder();
        for (Character character : path.toCharArray()) {
            if (character == '.') {
                res.append(' ');
            } else {
                res.append(character);
            }
        }
        return res.toString();
    }
}
// @lc code=end



/*
// @lcpr case=start
// "a.aef.qerf.bb"\n
// @lcpr case=end

 */

