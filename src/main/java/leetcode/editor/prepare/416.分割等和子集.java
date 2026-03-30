/*
 * @lc app=leetcode.cn id=416 lang=java
 * @lcpr version=30400
 *
 * [416] 分割等和子集
 *
 * https://leetcode.cn/problems/partition-equal-subset-sum/description/
 *
 * algorithms
 * Medium (54.02%)
 * Likes:    2491
 * Dislikes: 0
 * Total Accepted:    927.7K
 * Total Submissions: 1.7M
 * Testcase Example:  '[1,5,11,5]\n[1,2,3,5]'
 *
 * 给你一个 只包含正整数 的 非空 数组 nums 。请你判断是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 输入：nums = [1,5,11,5]
 * 输出：true
 * 解释：数组可以分割成 [1, 5, 5] 和 [11] 。
 * 
 * 示例 2：
 * 
 * 输入：nums = [1,2,3,5]
 * 输出：false
 * 解释：数组不能分割成两个元素和相等的子集。
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 <= nums.length <= 200
 * 1 <= nums[i] <= 100
 * 
 * 
 */

// @lc code=start

class Solution {
    public boolean canPartition(int[] nums) {
        int sum = 0;

        for (int num : nums) {
            sum += num;
        }

        if (sum % 2 == 1) {
            return false;
        }

        int n = nums.length;
        sum = sum / 2;

        // boolean[][] dp = new boolean[n + 1][sum + 1];

        // for (int i = 0; i < n + 1; i++) {
        //     dp[i][0] = true;
        // }

        // for (int i = 1; i < n + 1; i++) {
        //     for (int j = 0; j < sum + 1; j++) {
        //         if (j - nums[i - 1] < 0) {
        //             dp[i][j] = dp[i - 1][j];
        //         } else {
        //             dp[i][j] = dp[i - 1][j] || dp[i - 1][j - nums[i - 1]];
        //         }
        //     }
        // }

        // return dp[n][sum];

        boolean[] dp = new boolean[sum + 1];
        
        dp[0] = true;

        for (int i = 0; i < n; i++) {
            for (int j = sum; j >= 0; j--) {
                if (j - nums[i] >= 0) {
                    dp[j] = dp[j] || dp[j - nums[i]];
                }
            }
        }

        return dp[sum];
    }
}
// @lc code=end



/*
// @lcpr case=start
// [1,5,11,5]\n
// @lcpr case=end

// @lcpr case=start
// [1,2,3,5]\n
// @lcpr case=end

 */

