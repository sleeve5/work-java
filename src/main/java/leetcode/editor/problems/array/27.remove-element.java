/*
 * @lc app=leetcode.cn id=27 lang=java
 * @lcpr version=30307
 *
 * [27] 移除元素
 */

package leetcode.editor.problems.array;

// @lc code=start
class Solution {
    public int removeElement(int[] nums, int val) {
        int slow = 0, fast = 0;
        while (fast < nums.length) {
            if (nums[fast] == val) {
                fast++;
            } else {
                nums[slow] = nums[fast];
                slow++;
                fast++;
            }
        }
        return slow;
    }
}
// @lc code=end

/*
 * // @lcpr case=start
 * // [3,2,2,3]\n3\n
 * // @lcpr case=end
 * 
 * // @lcpr case=start
 * // [0,1,2,2,3,0,4,2]\n2\n
 * // @lcpr case=end
 * 
 */
