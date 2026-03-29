package leetcode.editor.prepare;

/*
 * @lc app=leetcode.cn id=912 lang=java
 * @lcpr version=30401
 *
 * [912] 排序数组
 *
 * https://leetcode.cn/problems/sort-an-array/description/
 *
 * algorithms
 * Medium (47.76%)
 * Likes:    1214
 * Dislikes: 0
 * Total Accepted:    865.6K
 * Total Submissions: 1.8M
 * Testcase Example:  '[5,2,3,1]\n[5,1,1,2,0,0]'
 *
 * 给你一个整数数组 nums，请你将该数组升序排列。
 * 
 * 你必须在 不使用任何内置函数 的情况下解决问题，时间复杂度为 O(nlog(n))，并且空间复杂度尽可能小。
 * 
 * 
 * 
 * 
 * 
 * 
 * 示例 1：
 * 
 * 输入：nums = [5,2,3,1]
 * 输出：[1,2,3,5]
 * 解释：数组排序后，某些数字的位置没有改变（例如，2 和 3），而其他数字的位置发生了改变（例如，1 和 5）。
 * 
 * 
 * 示例 2：
 * 
 * 输入：nums = [5,1,1,2,0,0]
 * 输出：[0,0,1,1,2,5]
 * 解释：请注意，nums 的值不一定唯一。
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 <= nums.length <= 5 * 10^4
 * -5 * 10^4 <= nums[i] <= 5 * 10^4
 * 
 * 
 */

// @lc code=start
class Solution {
    public int[] sortArray(int[] nums) {
        if (nums == null || nums.length < 2) {
            return nums;
        }

        quickSort(nums, 0, nums.length - 1);

        return nums;
    }

    private void quickSort(int[] nums, int left, int right) {
        if (left >= right) {
            return;
        }

        int pivot = partition(nums, left, right);

        quickSort(nums, left, pivot - 1);
        quickSort(nums, pivot + 1, right);
    }

    private int partition(int[] nums, int left, int right) {
        int idx = left + (int) (Math.random() * (right - left + 1));
        swap(nums, left, idx);

        int pivot = nums[left];
        int i = left, j = right;

        while (true) {
            while (i <= j && nums[i] <= pivot) {
                i++;
            }

            while (i <= j && nums[j] >= pivot) {
                j--;
            }

            if (i >= j) {
                break;
            }
            swap(nums, i, j);
        }

        swap(nums, left, j);

        return j;
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
// @lc code=end

/*
 * // @lcpr case=start
 * // [5,2,3,1]\n
 * // @lcpr case=end
 * 
 * // @lcpr case=start
 * // [5,1,1,2,0,0]\n
 * // @lcpr case=end
 * 
 */
