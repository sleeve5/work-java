/*
 * @lc app=leetcode.cn id=373 lang=java
 * @lcpr version=30307
 *
 * [373] 查找和最小的 K 对数字
 */

package leetcode.editor.problems.linkedlist;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.List;

// @lc code=start
class Solution {
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        Queue<int[]> queue = new PriorityQueue<>((a, b) -> (a[0] + a[1]) - (b[0] + b[1]));
        for (int i = 0; i < nums1.length; i++) {
            queue.offer(new int[] { nums1[i], nums2[0], 0 });
        }
        List<List<Integer>> res = new ArrayList<>();
        while (!queue.isEmpty() && k-- > 0) {
            int[] val = queue.poll();
            res.add(new ArrayList<>(Arrays.asList(new Integer[] { val[0], val[1] })));
            int j = val[2];
            if (j < nums2.length - 1) {
                queue.offer(new int[] { val[0], nums2[j + 1], j + 1 });
            }
        }
        return res;
    }
}
// @lc code=end

/*
 * // @lcpr case=start
 * // [1,7,11]\n[2,4,6]\n3\n
 * // @lcpr case=end
 * 
 * // @lcpr case=start
 * // [1,1,2]\n[1,2,3]\n2\n
 * // @lcpr case=end
 * 
 */
