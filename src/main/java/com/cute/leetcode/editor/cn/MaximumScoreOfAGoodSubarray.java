//给你一个整数数组 nums （下标从 0 开始）和一个整数 k 。 
//
// 一个子数组 (i, j) 的 分数 定义为 min(nums[i], nums[i+1], ..., nums[j]) * (j - i + 1) 。一个
// 好 子数组的两个端点下标需要满足 i <= k <= j 。 
//
// 请你返回 好 子数组的最大可能 分数 。 
//
// 
//
// 示例 1： 
//
// 输入：nums = [1,4,3,7,4,5], k = 3
//输出：15
//解释：最优子数组的左右端点下标是 (1, 5) ，分数为 min(4,3,7,4,5) * (5-1+1) = 3 * 5 = 15 。
// 
//
// 示例 2： 
//
// 输入：nums = [5,5,4,5,4,1,1,1], k = 0
//输出：20
//解释：最优子数组的左右端点下标是 (0, 4) ，分数为 min(5,5,4,5,4) * (4-0+1) = 4 * 5 = 20 。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 10⁵ 
// 1 <= nums[i] <= 2 * 10⁴ 
// 0 <= k < nums.length 
// 
//
// Related Topics 栈 数组 双指针 二分查找 单调栈 👍 121 👎 0

package com.cute.leetcode.editor.cn;

import java.util.Arrays;
import java.util.Stack;

public class MaximumScoreOfAGoodSubarray {
    public static void main(String[] args) {
        Solution solution = new MaximumScoreOfAGoodSubarray().new Solution();
        int[] nums = {5,5,4,5,4,1,1,1};
        int k = 0;

        System.out.println(solution.maximumScore(nums,k));
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {

        public int maximumScore(int[] nums, int k) {
            //return maximumScore1(nums,k);
            return maximumScore2(nums,k);
        }

        private int maximumScore2(int[] nums, int k) {
            int left =k,right = k;
            int ans = nums[k];
            int minH = nums[k];
            for(int i=0;i<nums.length-1;i++) {
                if(left==0 || right<nums.length-1 && nums[right+1] >= nums[left-1]) {
                    right++;
                    minH = Math.min(minH,nums[right]);
                } else {
                    left--;
                    minH = Math.min(minH,nums[left]);
                }
                ans = Math.max(ans,minH * (right-left+1));
            }
            return ans;
        }
        private int maximumScore1(int[] nums, int k) {
            Stack<Integer> stack = new Stack<>();
            int[] right = new int[nums.length];
            Arrays.fill(right,nums.length);
            for (int i = 0; i < nums.length; i++) {
                while (!stack.empty() && nums[stack.peek()] > nums[i]) {
                    right[stack.pop()] = i;
                }
                stack.push(i);
            }
            stack.clear();
            int[] left = new int[nums.length];
            Arrays.fill(left,-1);
            for (int i = nums.length - 1; i >= 0; i--) {
                while (!stack.empty() && nums[stack.peek()] > nums[i]) {
                    left[stack.pop()] = i;
                }
                stack.push(i);
            }
            int ans = 0;
            for (int i = 0; i < nums.length; i++) {
                if (left[i] < k && k < right[i]) {
                    ans = Math.max(ans, nums[i] * (right[i] - left[i] - 1));
                }
            }
            return ans;
        }
}
//leetcode submit region end(Prohibit modification and deletion)

}