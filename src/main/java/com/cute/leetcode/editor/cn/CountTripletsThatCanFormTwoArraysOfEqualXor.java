//给你一个整数数组 arr 。 
//
// 现需要从数组中取三个下标 i、j 和 k ，其中 (0 <= i < j <= k < arr.length) 。 
//
// a 和 b 定义如下： 
//
// 
// a = arr[i] ^ arr[i + 1] ^ ... ^ arr[j - 1] 
// b = arr[j] ^ arr[j + 1] ^ ... ^ arr[k] 
// 
//
// 注意：^ 表示 按位异或 操作。 
//
// 请返回能够令 a == b 成立的三元组 (i, j , k) 的数目。 
//
// 
//
// 示例 1： 
//
// 输入：arr = [2,3,1,6,7]
//输出：4
//解释：满足题意的三元组分别是 (0,1,2), (0,2,2), (2,3,4) 以及 (2,4,4)
// 
//
// 示例 2： 
//
// 输入：arr = [1,1,1,1,1]
//输出：10
// 
//
// 示例 3： 
//
// 输入：arr = [2,3]
//输出：0
// 
//
// 示例 4： 
//
// 输入：arr = [1,3,5,7,9]
//输出：3
// 
//
// 示例 5： 
//
// 输入：arr = [7,11,12,9,5,2,7,17,22]
//输出：8
// 
//
// 
//
// 提示： 
//
// 
// 1 <= arr.length <= 300 
// 1 <= arr[i] <= 10^8 
// 
// Related Topics 位运算 数组 数学 
// 👍 134 👎 0

package com.cute.leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;

public class CountTripletsThatCanFormTwoArraysOfEqualXor {
    public static void main(String[] args) {
        int[] array = {2, 3, 1, 6, 7};
        Solution solution = new CountTripletsThatCanFormTwoArraysOfEqualXor().new Solution();
        solution.countTriplets(array);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int countTriplets(int[] arr) {

            /** 异或性质
             * 1.任何数和0异或等于它本身
             * 2.任何数和自身异或等于0
             * 3.异或满足交换律
             */

            /**
             * 设xos[i] = a0^a1^a2...^ai-1
             * 则ai^ai+1...^aj = (a0^a1^a2...^ai-1)^(a0^a1^a2...^ai-1) ^ (a0^a1^a2...^aj) = xos[i] ^ xos[j+1]
             * a = xos[i] ^ xos[j],b=xos[j] ^ xos[k+1];
             * 要使a==b，则xos[i] ^ xos[j] == xos[j] ^ xos[k+1]，即xos[i] == xos[k+1];
             * 由于i<j<=k,则每一对xos[i] == xos[k+1]，有(k+1)-1-i个(i,j,k)符合条件
             * 因此遍历xos,当xos[m] == xos[n],则将结果加(n-1-i)
             *
             */


            int res = 0;
            int[] xos = new int[arr.length + 1];

            for (int i = 0; i < arr.length; i++) {
                xos[i + 1] = xos[i] ^ arr[i];
            }
            for (int i = 0; i < xos.length; i++) {
                for (int j = i + 1; j < xos.length; j++) {
                    if (xos[i] == xos[j]) {
                        res += (j - 1 - i);
                    }
                }
            }
            return res;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}