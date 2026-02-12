package leetcode.editor.leetcode.toOffer;

/*
 * @lc app=leetcode.cn id=LCR 125 lang=java
 * @lcpr version=30307
 *
 * [LCR 125] 图书整理 II
 */

// @lc code=start

import java.util.ArrayDeque;
import java.util.Queue;


class CQueue {
    private Queue<Integer> q;
    public CQueue() {
        q = new ArrayDeque<>();
    }
    
    public void appendTail(int value) {
        q.add(value);
    }
    
    public int deleteHead() {
        if (q.isEmpty()) {
            return -1;
        }
        return q.poll();
    }
}

/**
 * Your CQueue object will be instantiated and called as such:
 * CQueue obj = new CQueue();
 * obj.appendTail(value);
 * int param_2 = obj.deleteHead();
 */
// @lc code=end



/*
// @lcpr case=start
// ["CQueue","appendTail","deleteHead","deleteHead","deleteHead"]\n[[],[3],[],[],[]]\n
// @lcpr case=end

 */

