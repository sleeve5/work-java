/*
 * @lc app=leetcode.cn id=283 lang=java
 * @lcpr version=30307
 *
 * [283] 移动零
 */

package leetcode.editor.problems.array;

// @lc code=start
class Solution {
    public void moveZeroes(int[] nums) {
        int slow = 0, fast = 0;
        while (fast < nums.length) {
            if (nums[slow] == 0) {
                while (fast < nums.length && nums[fast] == 0) {
                    fast++;
                }
                if (fast < nums.length) {
                    swap(nums, slow, fast);
                    slow++;
                }
            } else {
                slow++;
                fast++;
            }
        }
    }

    void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
// @lc code=end

/*
 * // @lcpr case=start
 * // [0,1,0,3,12]\n
 * // @lcpr case=end
 * 
 * // @lcpr case=start
 * // [0]\n
 * // @lcpr case=end
 * 
 */
