//错误的集合
// 集合 s 包含从 1 到 n 的整数。不幸的是，因为数据错误，导致集合里面某一个数字复制了成了集合里面的另外一个数字的值，导致集合 丢失了一个数字 并且 有
//一个数字重复 。 
//
// 给定一个数组 nums 代表了集合 S 发生错误后的结果。 
//
// 请你找出重复出现的整数，再找到丢失的整数，将它们以数组的形式返回。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,2,2,4]
//输出：[2,3]
// 
//
// 示例 2： 
//
// 
//输入：nums = [1,1]
//输出：[1,2]
// 
//
// 
//
// 提示： 
//
// 
// 2 <= nums.length <= 104 
// 1 <= nums[i] <= 104 
// 
// Related Topics 位运算 数组 哈希表 排序 
// 👍 217 👎 0

package com.cute.leetcode.editor.cn;

import java.util.Arrays;

public class SetMismatch {
    public static void main(String[] args) {
        Solution solution = new SetMismatch().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int[] findErrorNums(int[] nums) {
            return findErrorNums_1(nums);
        }
        public int[] findErrorNums_2(int[] nums) {
            int n = nums.length;
            int oriSum = (1 + n) * n / 2;
            int[] count = new int[n];
            int nowSetSum = 0;
            int nowSum = 0;
            for(int num : nums) {
                if(count[num-1] == 0) {
                    nowSetSum+=num;
                }
                nowSum+=num;
                count[num-1]++;
            }
            return new int[]{nowSum-nowSetSum,oriSum-nowSetSum};
        }
        public int[] findErrorNums_1(int[] nums) {
            int n = nums.length;
            int repeatNum = -1;
            int oriSum = (1 + n) * n / 2;
            int nowSum = 0;
            int[] count = new int[n];
            for (int num : nums) {
                nowSum += num;
            }
            for(int num : nums) {
                if (count[num - 1] == 1) {
                    repeatNum = num;
                    break;
                }
                count[num - 1]++;
            }
            int oriNum = oriSum - nowSum + repeatNum;
            return new int[]{repeatNum, oriNum};
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}