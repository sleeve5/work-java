/*
 * @lc app=leetcode.cn id=92 lang=java
 * @lcpr version=30307
 *
 * [92] 反转链表 II
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
    public ListNode reverseBetween(ListNode head, int left, int right) {
        if (left == 1) {
            return reverseN(head, right);
        }
        head.next = reverseBetween(head.next, left - 1, right - 1);
        return head;
    }

    ListNode successor = null;

    ListNode reverseN(ListNode head, int n) {
        if (n == 1) {
            successor = head.next;
            return head;
        }
        ListNode last = reverseN(head.next, n - 1);
        head.next.next = head;
        head.next = successor;
        return last;
    }
}
// @lc code=end

/*
 * // @lcpr case=start
 * // [1,2,3,4,5]\n2\n4\n
 * // @lcpr case=end
 * 
 * // @lcpr case=start
 * // [5]\n1\n1\n
 * // @lcpr case=end
 * 
 */
