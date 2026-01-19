package practice;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class binarytree {
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int x) {
            val = x;
        }
    }

    // Breadth-first Search 广度优先搜索：层序遍历
    static List<Integer> levelOrder(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        List<Integer> list = new ArrayList<>();
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            list.add(node.val);
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
        return list;
    }

    // Depth-first Search 深度优先搜索：前、中、后序遍历
    static List<Integer> res = new ArrayList<>();

    // 前序：走到哪记到哪，先左走到底再右 [1, 2, 4, 5, 3]
    static void preOrder(TreeNode root) {
        if (root == null)
            return;

        res.add(root.val);
        preOrder(root.left);
        preOrder(root.right);
    }

    // 中序：左边记了才记当前节点(二叉搜索树BST) [4, 2, 5, 1, 3]
    static void inOrder(TreeNode root) {
        if (root == null)
            return;

        inOrder(root.left);
        res.add(root.val);
        inOrder(root.right);
    }

    // 后序：左右都记完才记当前节点 [4, 5, 2, 3, 1]
    static void postOrder(TreeNode root) {
        if (root == null)
            return;
        postOrder(root.left);
        postOrder(root.right);
        res.add(root.val);
    }

    class BinarySearchTree {
        TreeNode root;

        TreeNode search(int num) {
            TreeNode cur = root;
            while (cur != null) {
                if (cur.val > num) {
                    cur = cur.left;
                } else if (cur.val < num) {
                    cur = cur.right;
                } else
                    break;
            }
            return cur;
        }

        void insert(int num) {
            if (root == null) {
                root = new TreeNode(num);
                return;
            }
            TreeNode cur = root, pre = null;
            while (cur != null) {
                if (cur.val == num) {
                    return;
                }
                pre = cur;
                if (cur.val < num) {
                    cur = cur.right;
                } else if (cur.val > num) {
                    cur = cur.left;
                }
            }
            TreeNode node = new TreeNode(num);
            if (pre.val < num) {
                pre.right = node;
            } else {
                pre.left = node;
            }
        }

        void remove(int num) {
            if (root == null) {
                return;
            }
            TreeNode cur = root, pre = null;
            while (cur != null) {
                if (cur.val == num) {
                    break;
                }
                pre = cur;
                if (cur.val < num) {
                    cur = cur.right;
                } else if (cur.val > num) {
                    cur = cur.left;
                }
            }
            if (cur == null) {
                return;
            }
            if (cur.left == null || cur.right == null) {
                TreeNode node = cur.left != null ? cur.left : cur.right;
                if (cur != root) {
                    if (pre.left == cur) {
                        pre.left = node;
                    } else {
                        pre.right = node;
                    }
                } else {
                    root = node;
                }
            } else {
                TreeNode tmp = cur.right;
                while (tmp.left != null) {
                    tmp = tmp.left;
                }
                remove(tmp.val);
                cur.val = tmp.val;
            }
        }

    }

    public static void main(String[] args) {
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        TreeNode n4 = new TreeNode(4);
        TreeNode n5 = new TreeNode(5);
        n1.left = n2;
        n1.right = n3;
        n2.left = n4;
        n2.right = n5;
        TreeNode P = new TreeNode(0);
        n1.left = P;
        P.left = n2;
        n1.left = n2;
        postOrder(n1);
        System.out.println(res);
    }
}
