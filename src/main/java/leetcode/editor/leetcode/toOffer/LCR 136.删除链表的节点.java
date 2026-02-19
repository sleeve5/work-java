package toOffer;

import leetcode.editor.common.ListNode;

/*
 * @lc app=leetcode.cn id=LCR 136 lang=java
 * @lcpr version=30400
 *
 * [LCR 136] 删除链表的节点
 *
 * https://leetcode.cn/problems/shan-chu-lian-biao-de-jie-dian-lcof/description/
 *
 * algorithms
 * Easy (58.99%)
 * Likes:    367
 * Dislikes: 0
 * Total Accepted:    417.7K
 * Total Submissions: 708.2K
 * Testcase Example:  '[4,5,1,9]\n5\n[4,5,1,9]\n1'
 *
 * 给定单向链表的头指针和一个要删除的节点的值，定义一个函数删除该节点。
 * 
 * 返回删除后的链表的头节点。
 * 
 * 示例 1：
 * 
 * 输入：head = [4,5,1,9], val = 5
 * 输出：[4,1,9]
 * 解释：给定你链表中值为 5 的第二个节点，那么在调用了你的函数之后，该链表应变为 4 -> 1 -> 9.
 * 
 * 
 * 示例 2：
 * 
 * 输入：head = [4,5,1,9], val = 1
 * 输出：[4,5,9]
 * 解释：给定你链表中值为 1 的第三个节点，那么在调用了你的函数之后，该链表应变为 4 -> 5 -> 9.
 * 
 * 
 * 
 * 
 * 说明：
 * 
 * 
 * 题目保证链表中节点的值互不相同
 * 若使用 C 或 C++ 语言，你不需要 free 或 delete 被删除的节点
 * 
 * 
 * 
 * 
 */

// @lc code=start
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode deleteNode(ListNode head, int val) {
        if (head == null) {
            return null;
        }

        if (head.val == val) {
            return head.next;
        }
        
        ListNode pre = head;
        ListNode cur = head.next;

        while (cur != null) {
            if (cur.val == val) {
                pre.next = cur.next;
                break;
            }
            cur = cur.next;
            pre = pre.next;
        }
        return head;
    }
}
// @lc code=end



/*
// @lcpr case=start
// [4,5,1,9]\n5\n
// @lcpr case=end

// @lcpr case=start
// [4,5,1,9]\n1\n
// @lcpr case=end

 */

