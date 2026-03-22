/*
 * @lc app=leetcode.cn id=92 lang=java
 * @lcpr version=30400
 *
 * [92] 反转链表 II
 *
 * https://leetcode.cn/problems/reverse-linked-list-ii/description/
 *
 * algorithms
 * Medium (57.99%)
 * Likes:    2041
 * Dislikes: 0
 * Total Accepted:    710.5K
 * Total Submissions: 1.2M
 * Testcase Example:  '[1,2,3,4,5]\n2\n4\n[5]\n1\n1'
 *
 * 给你单链表的头指针 head 和两个整数 left 和 right ，其中 left <= right 。请你反转从位置 left 到位置 right
 * 的链表节点，返回 反转后的链表 。
 * 
 * 
 * 示例 1：
 * 
 * 输入：head = [1,2,3,4,5], left = 2, right = 4
 * 输出：[1,4,3,2,5]
 * 
 * 
 * 示例 2：
 * 
 * 输入：head = [5], left = 1, right = 1
 * 输出：[5]
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 链表中节点数目为 n
 * 1 <= n <= 500
 * -500 <= Node.val <= 500
 * 1 <= left <= right <= n
 * 
 * 
 * 
 * 
 * 进阶： 你可以使用一趟扫描完成反转吗？
 * 
 */

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

        ListNode node = head;

        for (int i = 0; i < left - 2; i++) {
            node = node.next;
        }

        node.next = reverseN(node.next, right - left + 1);

        return head;
    }

    private ListNode reverseN(ListNode head, int n) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode pre, cur, nxt;
        pre = null;
        cur = head;
        nxt = head.next;

        while (n-- > 0) {
            cur.next = pre;
            pre = cur;
            cur = nxt;
            if (nxt != null) {
                nxt = cur.next;
            }
        }

        head.next = cur;
        return pre;
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
