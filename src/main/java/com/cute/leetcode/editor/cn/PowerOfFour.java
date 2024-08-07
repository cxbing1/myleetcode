//给定一个整数，写一个函数来判断它是否是 4 的幂次方。如果是，返回 true ；否则，返回 false 。 
//
// 整数 n 是 4 的幂次方需满足：存在整数 x 使得 n == 4x 
//
// 
//
// 示例 1： 
//
// 
//输入：n = 16
//输出：true
// 
//
// 示例 2： 
//
// 
//输入：n = 5
//输出：false
// 
//
// 示例 3： 
//
// 
//输入：n = 1
//输出：true
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
// Related Topics 位运算 
// 👍 236 👎 0

package com.cute.leetcode.editor.cn;
public class PowerOfFour {
    public static void main(String[] args) {
        Solution solution = new PowerOfFour().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
        public boolean isPowerOfFour(int n) {
           //return isPowerOfFour1(n);
            return isPowerOfFour2(n);
        }

        boolean isPowerOfFour1(int n) {
            if(n == 1) {
                return true;
            }
            if(n<4) {
                return false;
            }
            return n%4== 0 && isPowerOfFour1(n/4);
        }

        //先判断是否是2的幂，再判断二进制位1是否在奇数位上
        boolean isPowerOfFour2(int n) {
            return n>0 && (n&(n-1)) == 0 && (n&(0xaaaaaaaa)) == 0;
        }
}
//leetcode submit region end(Prohibit modification and deletion)

}