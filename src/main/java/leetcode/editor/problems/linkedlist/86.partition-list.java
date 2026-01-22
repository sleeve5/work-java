/*
 * @lc app=leetcode.cn id=86 lang=java
 * @lcpr version=30307
 *
 * [86] 分隔链表
 */

package leetcode.editor.problems.linkedlist;

import leetcode.editor.common.ListNode;

// @lc code=start
/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode() {}
 * ListNode(int val) { this.val = val; }
 * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode partition(ListNode head, int x) {
        if (head == null) {
            return null;
        }
        ListNode dummyLow = new ListNode(-1), dummyHigh = new ListNode(-1);
        ListNode p = head, p1 = dummyLow, p2 = dummyHigh;
        while (p != null) {
            if (p.val < x) {
                p1.next = p;
                p = p.next;
                p1 = p1.next;
            } else {
                p2.next = p;
                p = p.next;
                p2 = p2.next;
            }
        }
        p2.next = null;
        p1.next = dummyHigh.next;
        return dummyLow.next;
    }
}
// @lc code=end

/*
 * // @lcpr case=start
 * // [1,4,3,2,5,2]\n3\n
 * // @lcpr case=end
 * 
 * // @lcpr case=start
 * // [2,1]\n2\n
 * // @lcpr case=end
 * 
 */
