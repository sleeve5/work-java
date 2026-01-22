/*
 * @lc app=leetcode.cn id=160 lang=java
 * @lcpr version=30307
 *
 * [160] 相交链表
 */

package leetcode.editor.problems.linkedlist;

import leetcode.editor.common.ListNode;

// @lc code=start
/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode(int x) {
 * val = x;
 * next = null;
 * }
 * }
 */
class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA.next == null && headB.next == null) {
            if (headA == headB) {
                return headA;
            }
            return null;
        }
        ListNode p1 = headA, p2 = headB;
        ListNode fast = headB, slow = headB;
        while (p1.next != null) {
            p1 = p1.next;
        }
        while (p2.next != null) {
            p2 = p2.next;
        }
        if (p1 != p2) {
            return null;
        }
        p1.next = headA;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                break;
            }
        }
        fast = headB;
        while (fast != slow) {
            fast = fast.next;
            slow = slow.next;
        }
        p1.next = null;
        return slow;
    }
}
// @lc code=end

/*
 * // @lcpr case=start
 * // 8\n[4,1,8,4,5]\n[5,6,1,8,4,5]\n2\n3\n
 * // @lcpr case=end
 * 
 * // @lcpr case=start
 * // 2\n[1,9,1,2,4]\n[3,2,4]\n3\n1\n
 * // @lcpr case=end
 * 
 * // @lcpr case=start
 * // 0\n[2,6,4]\n[1,5]\n3\n2\n
 * // @lcpr case=end
 * 
 */
