package toOffer;

/*
 * @lc app=leetcode.cn id=LCR 120 lang=java
 * @lcpr version=30307
 *
 * [LCR 120] 寻找文件副本
 */

// @lc code=start
class Solution {
    public int findRepeatDocument(int[] documents) {
        // for (int i = 0; i < documents.length; i++) {
        //     for (int j = i + 1; j < documents.length; j++) {
        //         if (documents[i] == documents[j]) {
        //             return documents[i];
        //         }
        //     }
        // }
        // return -1;
        int i = 0;
        while (i < documents.length) {
            if (i == documents[i]) {
                i++;
                continue;
            }
            if (documents[i] == documents[documents[i]]) {
                return documents[i];
            }
            int tmp = documents[i];
            documents[i] = documents[documents[i]];
            documents[tmp] = tmp;
        }
        return -1;
    }
}
// @lc code=end



/*
// @lcpr case=start
// [2, 5, 3, 0, 5, 0]\n
// @lcpr case=end

 */

