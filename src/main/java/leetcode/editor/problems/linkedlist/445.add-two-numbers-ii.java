/*
 * @lc app=leetcode.cn id=445 lang=java
 * @lcpr version=30307
 *
 * [445] 两数相加 II
 */

package leetcode.editor.problems.linkedlist;

import java.util.Stack;

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
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(-1);
        ListNode p1 = l1, p2 = l2;
        Stack<Integer> stk1 = new Stack<>();
        Stack<Integer> stk2 = new Stack<>();
        while (p1 != null) {
            stk1.push(p1.val);
            p1 = p1.next;
        }
        while (p2 != null) {
            stk2.push(p2.val);
            p2 = p2.next;
        }
        int carry = 0;
        while (!stk1.isEmpty() || !stk2.isEmpty() || carry > 0) {
            int val = carry;
            if (!stk1.isEmpty()) {
                val += stk1.pop();
            }
            if (!stk2.isEmpty()) {
                val += stk2.pop();
            }
            carry = val / 10;
            val = val % 10;
            ListNode node = new ListNode(val);
            node.next = dummy.next;
            dummy.next = node;
        }
        return dummy.next;
    }
}
// @lc code=end

/*
 * // @lcpr case=start
 * // [7,2,4,3]\n[5,6,4]\n
 * // @lcpr case=end
 * 
 * // @lcpr case=start
 * // [2,4,3]\n[5,6,4]\n
 * // @lcpr case=end
 * 
 * // @lcpr case=start
 * // [0]\n[0]\n
 * // @lcpr case=end
 * 
 */
