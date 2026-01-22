/*
 * @lc app=leetcode.cn id=142 lang=java
 * @lcpr version=30307
 *
 * [142] 环形链表 II
 */

package leetcode.editor.problems.linkedlist;

import leetcode.editor.common.ListNode;

// @lc code=start
/**
 * Definition for singly-linked list.
 * class ListNode {
 * int val;
 * ListNode next;
 * ListNode(int x) {
 * val = x;
 * next = null;
 * }
 * }
 */
class Solution {
    public ListNode detectCycle(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode fast = head, slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (slow == fast) {
                break;
            }
        }
        if (fast == null || fast.next == null) {
            return null;
        }
        fast = head;
        while (fast != slow) {
            fast = fast.next;
            slow = slow.next;
        }
        return fast;
    }
}
// @lc code=end

/*
 * // @lcpr case=start
 * // [3,2,0,-4]\n1\n
 * // @lcpr case=end
 * 
 * // @lcpr case=start
 * // [1,2]\n0\n
 * // @lcpr case=end
 * 
 * // @lcpr case=start
 * // [1]\n-1\n
 * // @lcpr case=end
 * 
 */
