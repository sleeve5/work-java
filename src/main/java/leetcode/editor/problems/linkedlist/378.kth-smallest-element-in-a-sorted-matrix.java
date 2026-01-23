/*
 * @lc app=leetcode.cn id=378 lang=java
 * @lcpr version=30307
 *
 * [378] 有序矩阵中第 K 小的元素
 */

package leetcode.editor.problems.linkedlist;

import java.util.PriorityQueue;
import java.util.Queue;

// @lc code=start
class Solution {
    public int kthSmallest(int[][] matrix, int k) {
        if (k == 1) {
            return matrix[0][0];
        }
        Queue<int[]> queue = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        for (int i = 0; i < matrix.length; i++) {
            queue.offer(new int[] { matrix[i][0], i, 0 });
        }
        int res = -1;
        while (!queue.isEmpty() && k-- > 0) {
            int[] val = queue.poll();
            res = val[0];
            int i = val[1], j = val[2];
            if (j < matrix[i].length - 1) {
                queue.offer(new int[] { matrix[i][j + 1], i, j + 1 });
            }
        }
        return res;
    }
}
// @lc code=end

/*
 * // @lcpr case=start
 * // [[1,5,9],[10,11,13],[12,13,15]]\n8\n
 * // @lcpr case=end
 * 
 * // @lcpr case=start
 * // [[-5]]\n1\n
 * // @lcpr case=end
 * 
 */
