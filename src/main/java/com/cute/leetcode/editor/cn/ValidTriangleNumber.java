//给定一个包含非负整数的数组，你的任务是统计其中可以组成三角形三条边的三元组个数。 
//
// 示例 1: 
//
// 
//输入: [2,2,3,4]
//输出: 3
//解释:
//有效的组合是: 
//2,3,4 (使用第一个 2)
//2,3,4 (使用第二个 2)
//2,2,3
// 
//
// 注意: 
//
// 
// 数组长度不超过1000。 
// 数组里整数的范围为 [0, 1000]。 
// 
// Related Topics 贪心 数组 双指针 二分查找 排序 
// 👍 257 👎 0

package com.cute.leetcode.editor.cn;

import java.util.Arrays;

public class ValidTriangleNumber {
    public static void main(String[] args) {
        Solution solution = new ValidTriangleNumber().new Solution();
        int[] nums = {2,2,3,4};
        System.out.println(solution.triangleNumber(nums));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        public int triangleNumber(int[] nums) {
            Arrays.sort(nums);
            int count = 0;
            for(int i = nums.length-1;i>=2;i--) {
                int left = 0,right = i-1;
                while(left < right) {
                    if(nums[left] + nums[right] > nums[i]) {
                        count+=(right-left);
                        right--;
                    } else {
                        left++;
                    }
                }
            }
            return count;
        }
        public int triangleNumber1(int[] nums) {
            Arrays.sort(nums);
            int count = 0;
            for (int i = 0; i < nums.length; i++) {
                for (int j = i + 1; j < nums.length; j++) {
                    for (int k = j + 1; k < nums.length; k++) {
                        if(nums[i] + nums[j] > nums[k]) {
                            count++;
                        }
                    }
                }
            }
            return count;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}