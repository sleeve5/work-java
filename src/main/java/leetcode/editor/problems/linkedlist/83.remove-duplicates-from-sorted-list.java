/*
 * @lc app=leetcode.cn id=83 lang=java
 * @lcpr version=30307
 *
 * [83] 删除排序链表中的重复元素
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
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return head;
        }
        ListNode slow = head, fast = head;
        while (fast != null) {
            if (slow.val != fast.val) {
                slow.next = fast;
                slow = slow.next;
            }
            fast = fast.next;
            slow.next = null;
        }
        return head;
    }
}
// @lc code=end

/*
 * // @lcpr case=start
 * // [1,1,2]\n
 * // @lcpr case=end
 * 
 * // @lcpr case=start
 * // [1,1,2,3,3]\n
 * // @lcpr case=end
 * 
 */
