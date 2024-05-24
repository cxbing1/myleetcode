//给你一个数组 points ，其中 points[i] = [xi, yi] 表示 X-Y 平面上的一个点。求最多有多少个点在同一条直线上。 
//
// 
//
// 示例 1： 
//
// 
//输入：points = [[1,1],[2,2],[3,3]]
//输出：3
// 
//
// 示例 2： 
//
// 
//输入：points = [[1,1],[3,2],[5,3],[4,1],[2,3],[1,4]]
//输出：4
// 
//
// 
//
// 提示： 
//
// 
// 1 <= points.length <= 300 
// points[i].length == 2 
// -104 <= xi, yi <= 104 
// points 中的所有点 互不相同 
// 
// Related Topics 几何 哈希表 数学 
// 👍 307 👎 0

package com.cute.leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;

public class MaxPointsOnALine {
    public static void main(String[] args) {
        Solution solution = new MaxPointsOnALine().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int maxPoints(int[][] points) {
            int ans = 1;
            for (int i = 0; i < points.length; i++) {
                for (int j = i + 1; j < points.length; j++) {
                    int res = 2;
                    for (int k = j + 1; k < points.length; k++) {
                        int s1 = (points[k][1] - points[j][1]) * (points[k][0] - points[i][0]);
                        int s2 = (points[k][1] - points[i][1]) * (points[k][0] - points[j][0]);
                        if(s1 == s2) {
                            res++;
                        }
                    }
                    ans = Math.max(ans,res);
                }

            }
            return ans;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}