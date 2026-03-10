/*
 * @lc app=leetcode.cn id=239 lang=java
 * @lcpr version=30400
 *
 * [239] 滑动窗口最大值
 *
 * https://leetcode.cn/problems/sliding-window-maximum/description/
 *
 * algorithms
 * Hard (50.11%)
 * Likes:    3413
 * Dislikes: 0
 * Total Accepted:    1.1M
 * Total Submissions: 2.2M
 * Testcase Example:  '[1,3,-1,-3,5,3,6,7]\n3\n[1]\n1'
 *
 * 给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k
 * 个数字。滑动窗口每次只向右移动一位。
 * 
 * 返回 滑动窗口中的最大值 。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 输入：nums = [1,3,-1,-3,5,3,6,7], k = 3
 * 输出：[3,3,5,5,6,7]
 * 解释：
 * 滑动窗口的位置                最大值
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 * ⁠1 [3  -1  -3] 5  3  6  7       3
 * ⁠1  3 [-1  -3  5] 3  6  7       5
 * ⁠1  3  -1 [-3  5  3] 6  7       5
 * ⁠1  3  -1  -3 [5  3  6] 7       6
 * ⁠1  3  -1  -3  5 [3  6  7]      7
 * 
 * 
 * 示例 2：
 * 
 * 输入：nums = [1], k = 1
 * 输出：[1]
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 <= nums.length <= 10^5
 * -10^4 <= nums[i] <= 10^4
 * 1 <= k <= nums.length
 * 
 * 
 */

// @lc code=start

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

class Solution {
    class MonotonicQueue {
        LinkedList<Integer> q = new LinkedList<>();

        void push(int n) {
            while (!q.isEmpty() && q.getLast() < n) {
                q.pollLast();
            }
            q.addLast(n);
        }

        void pop(int n) {
            if (n == q.getFirst()) {
                q.pollFirst();
            }
        }

        int max() {
            return q.getFirst();
        }

    }

    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length - k + 1;
        List<Integer> res = new ArrayList<>();

        MonotonicQueue mq = new MonotonicQueue();

        for (int i = 0; i < nums.length; i++) {
            if (i < k - 1) {
                mq.push(nums[i]);
            } else {
                mq.push(nums[i]);
                res.add(mq.max());
                mq.pop(nums[i - k + 1]);
            }
        }

        return res.stream().mapToInt(Integer::intValue).toArray();
    }
}
// @lc code=end

/*
 * // @lcpr case=start
 * // [1,3,-1,-3,5,3,6,7]\n3\n
 * // @lcpr case=end
 * 
 * // @lcpr case=start
 * // [1]\n1\n
 * // @lcpr case=end
 * 
 */
