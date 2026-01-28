/*
 * @lc app=leetcode.cn id=167 lang=java
 * @lcpr version=30307
 *
 * [167] 两数之和 II - 输入有序数组
 */

package leetcode.editor.problems.array;

// @lc code=start
class Solution {
    public int[] twoSum(int[] numbers, int target) {
        int left = 0, right = numbers.length - 1;
        while (left <= right) {
            if (numbers[left] + numbers[right] == target) {
                return new int[] { left + 1, right + 1 };
            } else if (numbers[left] + numbers[right] < target) {
                left++;
            } else {
                right--;
            }
        }
        return new int[] {};
    }
}
// @lc code=end

/*
 * // @lcpr case=start
 * // [2,7,11,15]\n9\n
 * // @lcpr case=end
 * 
 * // @lcpr case=start
 * // [2,3,4]\n6\n
 * // @lcpr case=end
 * 
 * // @lcpr case=start
 * // [-1,0]\n-1\n
 * // @lcpr case=end
 * 
 */
