//给你两个整数 n 和 start。你的任务是返回任意 (0,1,2,,...,2^n-1) 的排列 p，并且满足： 
//
// 
// p[0] = start 
// p[i] 和 p[i+1] 的二进制表示形式只有一位不同 
// p[0] 和 p[2^n -1] 的二进制表示形式也只有一位不同 
// 
//
// 
//
// 示例 1： 
//
// 输入：n = 2, start = 3
//输出：[3,2,0,1]
//解释：这个排列的二进制表示是 (11,10,00,01)
//     所有的相邻元素都有一位是不同的，另一个有效的排列是 [3,1,0,2]
// 
//
// 示例 2： 
//
// 输出：n = 3, start = 2
//输出：[2,6,7,5,4,0,1,3]
//解释：这个排列的二进制表示是 (010,110,111,101,100,000,001,011)
// 
//
// 
//
// 提示： 
//
// 
// 1 <= n <= 16 
// 0 <= start < 2^n 
// 
// Related Topics 位运算 数学 回溯 👍 97 👎 0

package com.cute.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CircularPermutationInBinaryRepresentation {
    public static void main(String[] args) {
        Solution solution = new CircularPermutationInBinaryRepresentation().new Solution();
        System.out.println(solution.circularPermutation(7,12));
    }
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        int[] countOne;
        public List<Integer> circularPermutation(int n, int start) {
            int length = 1<<n;
            int[] x = new int[length];
            countOne = new int[length];
            Arrays.fill(countOne,-1);
            boolean[] pos = new boolean[length];
            x[0] =start;
            pos[start] = true;
            int k = 1;
            x[1] = -1;
            while(k>=1) {
                if(x[k] == -1) {
                    x[k] = 0;
                } else {
                    pos[x[k]] = false;
                    x[k]++;
                }
                while(x[k]>=0&&x[k]<=length-1) {
                    if(!pos[x[k]] && isOneDiff(x,k)) {
                        break;
                    }
                    x[k]++;
                }
                if(x[k]>=0&&x[k]<=length-1) {
                    if(k == length-1) {
                        return Arrays.stream(x).boxed().collect(Collectors.toList());
                    } else {
                        pos[x[k]] = true;
                        x[++k] = -1;
                    }
                } else {
                    k--;
                }
            }
            return new ArrayList<>();
        }

        private boolean isOneDiff(int[] x,int k) {
            if(!isOneDiff(x[k],x[k-1])) {
                return false;
            }
            if(k == x.length-1) {
                return isOneDiff(x[k],x[0]);
            }
            return true;
        }


        private boolean isOneDiff(int x,int y) {
            int  xor = x^y;
            if(countOne[xor] == -1) {
                countOne[xor] = count(xor);
            }
            return countOne[xor] == 1;
        }

        private int count(int x) {
            int count = 0;
            while (x != 0) {
                x = x & (x - 1);
                count++;
            }
            return count;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}