/*
 * @lc app=leetcode.cn id=101 lang=java
 * @lcpr version=30401
 *
 * [101] 对称二叉树
 *
 * https://leetcode.cn/problems/symmetric-tree/description/
 *
 * algorithms
 * Easy (63.39%)
 * Likes:    3112
 * Dislikes: 0
 * Total Accepted:    1.6M
 * Total Submissions: 2.5M
 * Testcase Example:  '[1,2,2,3,4,4,3]\n[1,2,2,null,3,null,3]'
 *
 * 给你一个二叉树的根节点 root ， 检查它是否轴对称。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 输入：root = [1,2,2,3,4,4,3]
 * 输出：true
 * 
 * 
 * 示例 2：
 * 
 * 输入：root = [1,2,2,null,3,null,3]
 * 输出：false
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 树中节点数目在范围 [1, 1000] 内
 * -100 <= Node.val <= 100
 * 
 * 
 * 
 * 
 * 进阶：你可以运用递归和迭代两种方法解决这个问题吗？
 * 
 */

// @lc code=start

import java.util.*;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode() {}
 * TreeNode(int val) { this.val = val; }
 * TreeNode(int val, TreeNode left, TreeNode right) {
 * this.val = val;
 * this.left = left;
 * this.right = right;
 * }
 * }
 */
class Solution {
    private List<Integer> nums = new ArrayList<>();

    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }

        return check(root.left, root.right);
    }

    private boolean check(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        }

        if (left == null || right == null) {
            return false;
        }

        return left.val == right.val && check(left.right, right.left) && check(left.left, right.right);
    }
}
// @lc code=end

/*
 * // @lcpr case=start
 * // [1,2,2,3,4,4,3]\n
 * // @lcpr case=end
 * 
 * // @lcpr case=start
 * // [1,2,2,null,3,null,3]\n
 * // @lcpr case=end
 * 
 */
