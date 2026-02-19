package toOffer;

import java.util.HashMap;

import leetcode.editor.common.TreeNode;

/*
 * @lc app=leetcode.cn id=LCR 124 lang=java
 * @lcpr version=30400
 *
 * [LCR 124] 推理二叉树
 *
 * https://leetcode.cn/problems/zhong-jian-er-cha-shu-lcof/description/
 *
 * algorithms
 * Medium (70.00%)
 * Likes:    1152
 * Dislikes: 0
 * Total Accepted:    361.7K
 * Total Submissions: 516.6K
 * Testcase Example:  '[3,9,20,15,7]\n[9,3,15,20,7]'
 *
 * 某二叉树的先序遍历结果记录于整数数组 preorder，它的中序遍历结果记录于整数数组 inorder。请根据 preorder 和 inorder
 * 的提示构造出这棵二叉树并返回其根节点。
 * 
 * 
 * 
 * 注意：preorder 和 inorder 中均不含重复数字。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 
 * 输入: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
 * 
 * 输出: [3,9,20,null,null,15,7]
 * 
 * 
 * 
 * 
 * 示例 2:
 * 
 * 输入: preorder = [-1], inorder = [-1]
 * 
 * 输出: [-1]
 * 
 * 
 * 
 * 
 * 提示:
 * 
 * 
 * 1 <= preorder.length <= 3000
 * inorder.length == preorder.length
 * -3000 <= preorder[i], inorder[i] <= 3000
 * inorder 均出现在 preorder
 * preorder 保证 为二叉树的前序遍历序列
 * inorder 保证 为二叉树的中序遍历序列
 * 
 * 
 * 
 * 
 * 注意：本题与主站 105
 * 题重复：https://leetcode.cn/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
 * 
 * 
 * 
 */

// @lc code=start
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    private HashMap<Integer, Integer> inorderMap;
    public TreeNode deduceTree(int[] preorder, int[] inorder) {
        inorderMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inorderMap.put(inorder[i], i);
        }

        return buildTree(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
    }
    private TreeNode buildTree(int[] preorder, int preStart, int preEnd, int[] inorder, int inStart, int inEnd) {
        if (preStart > preEnd) {
            return null;
        }

        int rootVal = preorder[preStart];
        TreeNode root = new TreeNode(rootVal);

        int rootIndex = inorderMap.get(rootVal);
        int leftSize = rootIndex - inStart;

        root.left = buildTree(preorder, preStart + 1, preStart + leftSize, inorder, inStart, rootIndex - 1);

        root.right = buildTree(preorder, preStart + leftSize + 1, preEnd, inorder, rootIndex + 1, inEnd);

        return root;
    }
}
// @lc code=end



/*
// @lcpr case=start
// [3,9,20,15,7]\n[9,3,15,20,7]\n
// @lcpr case=end

 */

