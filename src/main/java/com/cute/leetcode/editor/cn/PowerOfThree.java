//给定一个整数，写一个函数来判断它是否是 3 的幂次方。如果是，返回 true ；否则，返回 false 。 
//
// 整数 n 是 3 的幂次方需满足：存在整数 x 使得 n == 3x 
//
// 
//
// 示例 1： 
//
// 
//输入：n = 27
//输出：true
// 
//
// 示例 2： 
//
// 
//输入：n = 0
//输出：false
// 
//
// 示例 3： 
//
// 
//输入：n = 9
//输出：true
// 
//
// 示例 4： 
//
// 
//输入：n = 45
//输出：false
// 
//
// 
//
// 提示： 
//
// 
// -231 <= n <= 231 - 1 
// 
//
// 
//
// 进阶： 
//
// 
// 你能不使用循环或者递归来完成本题吗？ 
// 
// Related Topics 递归 数学 
// 👍 211 👎 0

package com.cute.leetcode.editor.cn;

public class PowerOfThree {
    public static void main(String[] args) {
        Solution solution = new PowerOfThree().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public boolean isPowerOfThree(int n) {
            //return isPowerOfThree_duigui(n);
            return isPowerOfThree_diedai(n);

        }
        private boolean isPowerOfThree_duigui(int n) {
            if (n == 1) {
                return true;
            }
            if (n==0 || n % 3 != 0) {
                return false;
            }
            return isPowerOfThree(n / 3);
        }
        private boolean isPowerOfThree_diedai(int n) {
            while(n != 0 && n%3==0) {
                n/=3;
            }
            return n==1;
        }

    }
//leetcode submit region end(Prohibit modification and deletion)

}