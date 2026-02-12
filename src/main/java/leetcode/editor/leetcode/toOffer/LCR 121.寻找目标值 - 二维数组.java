package toOffer;

/*
 * @lc app=leetcode.cn id=LCR 121 lang=java
 * @lcpr version=30307
 *
 * [LCR 121] 寻找目标值 - 二维数组
 */

// @lc code=start
class Solution {
    public boolean findTargetIn2DPlants(int[][] plants, int target) {
        if (plants.length == 0) {
            return false;
        }
        int m = plants.length - 1, n = plants[0].length - 1;
        int i = 0, j = n;
        while (j >= 0 && i <= m) {
            if (plants[i][j] > target) {
                j--;
            } else if (plants[i][j] < target) {
                i++;
            } else {
                return true;
            }
        }
        return false;
    }
}
// @lc code=end



/*
// @lcpr case=start
// [[2,3,6,8],[4,5,8,9],[5,9,10,12]]\n8\n
// @lcpr case=end

 */

