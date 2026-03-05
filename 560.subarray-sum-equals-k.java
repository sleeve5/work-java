/*
 * @lc app=leetcode.cn id=560 lang=java
 * @lcpr version=30400
 *
 * [560] 和为 K 的子数组
 *
 * https://leetcode.cn/problems/subarray-sum-equals-k/description/
 *
 * algorithms
 * Medium (46.22%)
 * Likes:    3045
 * Dislikes: 0
 * Total Accepted:    995.6K
 * Total Submissions: 2.2M
 * Testcase Example:  '[1,1,1]\n2\n[1,2,3]\n3'
 *
 * 给你一个整数数组 nums 和一个整数 k ，请你统计并返回 该数组中和为 k 的子数组的个数 。
 * 
 * 子数组是数组中元素的连续非空序列。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 输入：nums = [1,1,1], k = 2
 * 输出：2
 * 
 * 
 * 示例 2：
 * 
 * 输入：nums = [1,2,3], k = 3
 * 输出：2
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 <= nums.length <= 2 * 10^4
 * -1000 <= nums[i] <= 1000
 * -10^7 <= k <= 10^7
 * 
 * 
 */

// @lc code=start

import java.util.HashMap;

class Solution {
    public int subarraySum(int[] nums, int k) {
        int n = nums.length;
        int sum = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int res = 0;

        for (int num : nums) {
            sum += num;
            int need = sum - k;
            if (map.containsKey(need)) {
                res += map.get(need);
            }

            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }

        return res;
    }
}
// @lc code=end

/*
 * // @lcpr case=start
 * // [1,1,1]\n2\n
 * // @lcpr case=end
 * 
 * // @lcpr case=start
 * // [1,2,3]\n3\n
 * // @lcpr case=end
 * 
 */
