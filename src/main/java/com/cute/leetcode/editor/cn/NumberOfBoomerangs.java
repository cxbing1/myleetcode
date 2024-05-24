//给定平面上 n 对 互不相同 的点 points ，其中 points[i] = [xi, yi] 。回旋镖 是由点 (i, j, k) 表示的元组 ，其中
// i 和 j 之间的距离和 i 和 k 之间的欧式距离相等（需要考虑元组的顺序）。 
//
// 返回平面上所有回旋镖的数量。 
//
// 示例 1： 
//
// 
//输入：points = [[0,0],[1,0],[2,0]]
//输出：2
//解释：两个回旋镖为 [[1,0],[0,0],[2,0]] 和 [[1,0],[2,0],[0,0]]
// 
//
// 示例 2： 
//
// 
//输入：points = [[1,1],[2,2],[3,3]]
//输出：2
// 
//
// 示例 3： 
//
// 
//输入：points = [[1,1]]
//输出：0
// 
//
// 
//
// 提示： 
//
// 
// n == points.length 
// 1 <= n <= 500 
// points[i].length == 2 
// -10⁴ <= xi, yi <= 10⁴ 
// 所有点都 互不相同 
// 
//
// Related Topics 数组 哈希表 数学 👍 299 👎 0

package com.cute.leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;

public class NumberOfBoomerangs {
    public static void main(String[] args) {
        Solution solution = new NumberOfBoomerangs().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int numberOfBoomerangs(int[][] points) {
        Map<Integer,Integer>[] disMapArray =  (Map<Integer, Integer>[]) new Map[points.length];
        for(int i = 0;i<points.length;i++) {
            disMapArray[i] = new HashMap<>();
        }

        for(int i = 0;i<points.length;i++) {
            for(int j = i+1;j<points.length;j++) {
                int dis = (points[i][0]-points[j][0]) * (points[i][0]-points[j][0]) + (points[i][1]-points[j][1]) * (points[i][1]-points[j][1]);
                int iCount =  disMapArray[i].getOrDefault(dis,0);
                disMapArray[i].put(dis,iCount+1);
                int jCount =  disMapArray[j].getOrDefault(dis,0);
                disMapArray[j].put(dis,jCount+1);
            }
        }
        int ans = 0;

        for(int i = 0;i<points.length;i++) {
            for(int count : disMapArray[i].values()) {
                ans += count * (count-1);
            }
        }
        return ans;

    }
}
//leetcode submit region end(Prohibit modification and deletion)

}