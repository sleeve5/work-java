/*
 * @lc app=leetcode.cn id=15 lang=java
 * @lcpr version=30400
 *
 * [15] 三数之和
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// @lc code=start
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        return nSumTarget(nums, 3, 0, 0);
    }

    private List<List<Integer>> nSumTarget(int[] nums, int n, int start, int target) {
        int sz = nums.length;
        List<List<Integer>> res = new ArrayList<>();

        if (n < 2 || sz < n) {
            return res;
        }

        if (n == 2) {
            int lo = start, hi = sz - 1;
            while (lo < hi) {
                int left = nums[lo], right = nums[hi];
                int sum = left + right;
                if (sum == target) {
                    res.add(new ArrayList<>(Arrays.asList(left, right)));
                    while (lo < hi && nums[lo] == left)
                        lo++;
                    while (lo < hi && nums[hi] == right)
                        hi--;
                } else if (sum < target) {
                    while (lo < hi && nums[lo] == left)
                        lo++;
                } else {
                    while (lo < hi && nums[hi] == right)
                        hi--;
                }
            }
        } else {
            for (int i = start; i < sz; i++) {
                List<List<Integer>> sub = nSumTarget(nums, n - 1, i + 1, target - nums[i]);
                for (List<Integer> arr : sub) {
                    arr.add(nums[i]);
                    res.add(arr);
                }
                while (i < sz - 1 && nums[i] == nums[i + 1]) {
                    i++;
                }
            }
        }
        return res;
    }
}
// @lc code=end

/*
 * // @lcpr case=start
 * // [-1,0,1,2,-1,-4]\n
 * // @lcpr case=end
 * 
 * // @lcpr case=start
 * // [0,1,1]\n
 * // @lcpr case=end
 * 
 * // @lcpr case=start
 * // [0,0,0]\n
 * // @lcpr case=end
 * 
 */
