/*
 * @lc app=leetcode.cn id=21 lang=java
 * @lcpr version=30307
 *
 * [21] 合并两个有序链表
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
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode(-1), p = dummy;
        ListNode p1 = list1, p2 = list2;
        if (p1 == null && p2 == null) {
            return null;
        }
        while (p1 != null && p2 != null) {
            if (p1.val < p2.val) {
                p.next = p1;
                p1 = p1.next;
                p = p.next;
            } else {
                p.next = p2;
                p2 = p2.next;
                p = p.next;
            }
        }
        if (p1 == null) {
            p.next = p2;
        }
        if (p2 == null) {
            p.next = p1;
        }
        return dummy.next;
    }
}
// @lc code=end

/*
 * // @lcpr case=start
 * // [1,2,4]\n[1,3,4]\n
 * // @lcpr case=end
 * 
 */
