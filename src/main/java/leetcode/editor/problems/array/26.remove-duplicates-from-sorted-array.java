/*
 * @lc app=leetcode.cn id=26 lang=java
 * @lcpr version=30307
 *
 * [26] 删除有序数组中的重复项
 */

package leetcode.editor.problems.array;

// @lc code=start
class Solution {
    public int removeDuplicates(int[] nums) {
        int fast = 1, slow = 0;
        while (fast < nums.length) {
            if (nums[fast] == nums[slow]) {
                while (fast < nums.length && nums[fast] == nums[slow]) {
                    fast++;
                }
            }
            if (fast < nums.length) {
                nums[++slow] = nums[fast++];
            }
        }
        return slow + 1;
    }
}
// @lc code=end

/*
 * // @lcpr case=start
 * // [1,1,2]\n
 * // @lcpr case=end
 * 
 * // @lcpr case=start
 * // [0,0,1,1,1,2,2,3,3,4]\n
 * // @lcpr case=end
 * 
 */
