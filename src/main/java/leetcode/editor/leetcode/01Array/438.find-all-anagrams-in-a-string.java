/*
 * @lc app=leetcode.cn id=438 lang=java
 * @lcpr version=30400
 *
 * [438] 找到字符串中所有字母异位词
 *
 * https://leetcode.cn/problems/find-all-anagrams-in-a-string/description/
 *
 * algorithms
 * Medium (54.57%)
 * Likes:    1881
 * Dislikes: 0
 * Total Accepted:    940.2K
 * Total Submissions: 1.7M
 * Testcase Example:  '"cbaebabacd"\n"abc"\n"abab"\n"ab"'
 *
 * 给定两个字符串 s 和 p，找到 s 中所有 p 的 异位词 的子串，返回这些子串的起始索引。不考虑答案输出的顺序。
 * 
 * 
 * 
 * 示例 1:
 * 
 * 输入: s = "cbaebabacd", p = "abc"
 * 输出: [0,6]
 * 解释:
 * 起始索引等于 0 的子串是 "cba", 它是 "abc" 的异位词。
 * 起始索引等于 6 的子串是 "bac", 它是 "abc" 的异位词。
 * 
 * 
 * 示例 2:
 * 
 * 输入: s = "abab", p = "ab"
 * 输出: [0,1,2]
 * 解释:
 * 起始索引等于 0 的子串是 "ab", 它是 "ab" 的异位词。
 * 起始索引等于 1 的子串是 "ba", 它是 "ab" 的异位词。
 * 起始索引等于 2 的子串是 "ab", 它是 "ab" 的异位词。
 * 
 * 
 * 
 * 
 * 提示:
 * 
 * 
 * 1 <= s.length, p.length <= 3 * 10^4
 * s 和 p 仅包含小写字母
 * 
 * 
 */

// @lc code=start

import java.util.*;

class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        Map<Character, Integer> need = new HashMap<>();
        Map<Character, Integer> window = new HashMap<>();

        for (char c : p.toCharArray()) {
            need.put(c, need.getOrDefault(c, 0) + 1);
        }

        List<Integer> res = new ArrayList<>();

        int left = 0, right = 0;
        int valid = 0;

        while (right < s.length()) {
            char c = s.charAt(right);
            right++;

            if (need.containsKey(c)) {
                window.put(c, window.getOrDefault(c, 0) + 1);

                if (need.get(c).equals(window.get(c))) {
                    valid++;
                }
            }

            while (right - left >= p.length()) {
                if (valid == need.size()) {
                    res.add(left);
                }

                char d = s.charAt(left);
                left++;

                if (need.containsKey(d)) {
                    if (need.get(d).equals(window.get(d))) {
                        valid--;
                    }
                    window.put(d, window.get(d) - 1);
                }
            }
        }

        return res;
    }
}
// @lc code=end

/*
 * // @lcpr case=start
 * // "cbaebabacd"\n"abc"\n
 * // @lcpr case=end
 * 
 * // @lcpr case=start
 * // "abab"\n"ab"\n
 * // @lcpr case=end
 * 
 */
