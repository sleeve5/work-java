/*
 * @lc app=leetcode.cn id=215 lang=java
 * @lcpr version=30400
 *
 * [215] 数组中的第K个最大元素
 *
 * https://leetcode.cn/problems/kth-largest-element-in-an-array/description/
 *
 * algorithms
 * Medium (60.36%)
 * Likes:    2931
 * Dislikes: 0
 * Total Accepted:    1.6M
 * Total Submissions: 2.7M
 * Testcase Example:  '[3,2,1,5,6,4]\n2\n[3,2,3,1,2,4,5,5,6]\n4'
 *
 * 给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。
 * 
 * 请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 * 
 * 你必须设计并实现时间复杂度为 O(n) 的算法解决此问题。
 * 
 * 
 * 
 * 示例 1:
 * 
 * 输入: [3,2,1,5,6,4], k = 2
 * 输出: 5
 * 
 * 
 * 示例 2:
 * 
 * 输入: [3,2,3,1,2,4,5,5,6], k = 4
 * 输出: 4
 * 
 * 
 * 
 * 提示： 
 * 
 * 
 * 1 <= k <= nums.length <= 10^5
 * -10^4 <= nums[i] <= 10^4
 * 
 * 
 */

// @lc code=start

import java.util.PriorityQueue;

class Solution {
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (Integer integer : nums) {
            pq.offer(integer);

            if (pq.size() > k) {
                pq.poll();
            }
        }

        return pq.peek();
    }
}
// @lc code=end

/*
 * // @lcpr case=start
 * // [3,2,1,5,6,4]\n2\n
 * // @lcpr case=end
 * 
 * // @lcpr case=start
 * // [3,2,3,1,2,4,5,5,6]\n4\n
 * // @lcpr case=end
 * 
 */
