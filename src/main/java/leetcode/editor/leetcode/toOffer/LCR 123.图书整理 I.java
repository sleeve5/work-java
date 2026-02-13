package toOffer;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import leetcode.editor.common.ListNode;
import practice.stack;

/*
 * @lc app=leetcode.cn id=LCR 123 lang=java
 * @lcpr version=30307
 *
 * [LCR 123] 图书整理 I
 *
 * https://leetcode.cn/problems/cong-wei-dao-tou-da-yin-lian-biao-lcof/description/
 *
 * algorithms
 * Easy (73.87%)
 * Likes:    493
 * Dislikes: 0
 * Total Accepted:    698.2K
 * Total Submissions: 945.1K
 * Testcase Example:  '[3,6,4,1]'
 *
 * 
 * 书店店员有一张链表形式的书单，每个节点代表一本书，节点中的值表示书的编号。为更方便整理书架，店员需要将书单倒过来排列，就可以从最后一本书开始整理，逐一将书放回到书架上。请倒序返回这个书单链表。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 输入：head = [3,6,4,1]
 * 
 * 输出：[1,4,6,3]
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 0 <= 链表长度 <= 10000
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
    public int[] reverseBookList(ListNode head) {
        // if (head == null) {
        //     return new int[] {};
        // }

        // int len = 0;
        // ListNode tmp = head;
        // while (tmp != null) {
        //     tmp = tmp.next;
        //     len++;
        // }

        // int[] res = new int[len];
        // tmp = head;

        // while (tmp != null) {
        //     res[--len] = tmp.val;
        //     tmp = tmp.next;
        // }

        // return res;

        dfs(head);
        return res.stream().mapToInt(i -> i).toArray();
    }

    private ArrayList<Integer> res = new ArrayList<>();

    private void dfs(ListNode head) {
        if (head == null) {
            return;
        }
        dfs(head.next);
        res.add(head.val);
    }
}
// @lc code=end



/*
// @lcpr case=start
// [3,6,4,1]\n
// @lcpr case=end

 */

