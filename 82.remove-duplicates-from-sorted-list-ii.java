/*
 * @lc app=leetcode.cn id=82 lang=java
 * @lcpr version=30307
 *
 * [82] 删除排序链表中的重复元素 II
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
        if (head == null || head.next == null) {
            return head;
        }
        ListNode dummy = new ListNode(-1);
        ListNode fast = head, slow = dummy;
        while (fast != null) {
            if (fast.next != null && fast.val == fast.next.val) {
                while (fast.next != null && fast.val == fast.next.val) {
                    fast = fast.next;
                }
                fast = fast.next;
                if (fast == null) {
                    slow.next = null;
                }
            } else {
                slow.next = fast;
                slow = slow.next;
                fast = fast.next;
            }
        }
        return dummy.next;
    }
}
// @lc code=end

/*
 * // @lcpr case=start
 * // [1,2,3,3,4,4,5]\n
 * // @lcpr case=end
 * 
 * // @lcpr case=start
 * // [1,1,1,2,3]\n
 * // @lcpr case=end
 * 
 */
