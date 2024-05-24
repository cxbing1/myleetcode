//两个整数之间的汉明距离指的是这两个数字对应二进制位不同的位置的数目。 
//
// 给出两个整数 x 和 y，计算它们之间的汉明距离。 
//
// 注意： 
//0 ≤ x, y < 231. 
//
// 示例: 
//
// 
//输入: x = 1, y = 4
//
//输出: 2
//
//解释:
//1   (0 0 0 1)
//4   (0 1 0 0)
//       ↑   ↑
//
//上面的箭头指出了对应二进制位不同的位置。
// 
// Related Topics 位运算 
// 👍 463 👎 0

package com.cute.leetcode.editor.cn;
public class HammingDistance {
    public static void main(String[] args) {
        Solution solution = new HammingDistance().new Solution();
        System.out.println(solution.hammingDistance(1,4));
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int hammingDistance(int x, int y) {
        int xor = x ^ y;
        //求xor的二进制1的个数

        //return bitCount_1(xor);
        //return bitCount_2(xor);
        return bitCount_3(xor);

    }
    //短除法
    private int bitCount_1(int x) {
        int count = 0;
        while(x >0) {
            if(x%2 == 1) {
                count++;
            }
            x /= 2;
        }
        return count;
    }
    //移位法
    private int bitCount_2(int x) {
        int count = 0;
        while(x >0) {
            count += x&1;
            x >>= 1;
        }
        return count;
    }

    //Brian Kernighan 算法
    //记 f(x) 表示 x 和 x-1 进行与运算所得的结果（即 f(x)=x & (x−1)），那么 f(x) 恰为 x 删去其二进制表示中最右侧的 1 的结果
    private int bitCount_3(int x) {
        int count = 0;
        while(x > 0) {
            x &= x-1;
            count++;
        }
        return count;
    }


}
//leetcode submit region end(Prohibit modification and deletion)

}