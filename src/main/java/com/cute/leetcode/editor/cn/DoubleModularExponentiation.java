//给你一个下标从 0 开始的二维数组 variables ，其中 variables[i] = [ai, bi, ci, mi]，以及一个整数 target 
//。 
//
// 如果满足以下公式，则下标 i 是 好下标： 
//
// 
// 0 <= i < variables.length 
// ((aibi % 10)ci) % mi == target 
// 
//
// 返回一个由 好下标 组成的数组，顺序不限 。 
//
// 
//
// 示例 1： 
//
// 
//输入：variables = [[2,3,3,10],[3,3,3,1],[6,1,1,4]], target = 2
//输出：[0,2]
//解释：对于 variables 数组中的每个下标 i ：
//1) 对于下标 0 ，variables[0] = [2,3,3,10] ，(2³ % 10)³ % 10 = 2 。
//2) 对于下标 1 ，variables[1] = [3,3,3,1] ，(3³ % 10)³ % 1 = 0 。
//3) 对于下标 2 ，variables[2] = [6,1,1,4] ，(6¹ % 10)¹ % 4 = 2 。
//因此，返回 [0,2] 作为答案。
// 
//
// 示例 2： 
//
// 
//输入：variables = [[39,3,1000,1000]], target = 17
//输出：[]
//解释：对于 variables 数组中的每个下标 i ：
//1) 对于下标 0 ，variables[0] = [39,3,1000,1000] ，(39³ % 10)¹⁰⁰⁰ % 1000 = 1 。
//因此，返回 [] 作为答案。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= variables.length <= 100 
// variables[i] == [ai, bi, ci, mi] 
// 1 <= ai, bi, ci, mi <= 10³ 
// 0 <= target <= 10³ 
// 
//
// Related Topics 数组 数学 模拟 👍 25 👎 0

package com.cute.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

public class DoubleModularExponentiation {
    public static void main(String[] args) {
        int[][] variables = {{2, 3, 3, 10}, {3, 3, 3, 1}, {6, 1, 1, 4}};
        Solution solution = new DoubleModularExponentiation().new Solution();
        System.out.println(solution.getGoodIndices(variables, 2));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<Integer> getGoodIndices(int[][] variables, int target) {
            List<Integer> goodIndices = new ArrayList<>();
            for (int i = 0; i < variables.length; i++) {
                if (mod2(mod2(variables[i][0], variables[i][1], 10), variables[i][2], variables[i][3]) == target) {
                    goodIndices.add(i);
                }
            }
            return goodIndices;
        }

        public int mod(int x, int y, int mod) {
            if (y == 1) {
                return x % mod;
            }
            if (y == 0) {
                return 1;
            }
            int tmp = mod(x, y / 2, mod);
            return tmp * tmp * (y % 2 == 1 ? x : 1) % mod;
        }

        public int mod2(int x, int y, int mod) {
            int res = 1;
            while (y != 0) {
                if ((y & 1) == 1) {
                    res = res * x % mod;
                }
                x = x * x % mod;
                y = y >> 1;
            }
            return res;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}