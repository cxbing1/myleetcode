//给你一个整数数组 nums 和一个整数 target 。 
//
// 向数组中的每个整数前添加 '+' 或 '-' ，然后串联起所有整数，可以构造一个 表达式 ： 
//
// 
// 例如，nums = [2, 1] ，可以在 2 之前添加 '+' ，在 1 之前添加 '-' ，然后串联起来得到表达式 "+2-1" 。 
// 
//
// 返回可以通过上述方法构造的、运算结果等于 target 的不同 表达式 的数目。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,1,1,1,1], target = 3
//输出：5
//解释：一共有 5 种方法让最终目标和为 3 。
//-1 + 1 + 1 + 1 + 1 = 3
//+1 - 1 + 1 + 1 + 1 = 3
//+1 + 1 - 1 + 1 + 1 = 3
//+1 + 1 + 1 - 1 + 1 = 3
//+1 + 1 + 1 + 1 - 1 = 3
// 
//
// 示例 2： 
//
// 
//输入：nums = [1], target = 1
//输出：1
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 20 
// 0 <= nums[i] <= 1000 
// 0 <= sum(nums[i]) <= 1000 
// -1000 <= target <= 100 
// 
// Related Topics 深度优先搜索 动态规划 
// 👍 762 👎 0

package com.cute.leetcode.editor.cn;
public class TargetSum {
    public static void main(String[] args) {
        Solution solution = new TargetSum().new Solution();
        int[] arr = {1,1,1,1,1};
        System.out.println(solution.findTargetSumWays(arr,3));
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
        int res = 0;
    public int findTargetSumWays(int[] nums, int target) {
        //back_trace1(nums,0,target);
        //back_trace2(nums,target);
        return findTargetSumWaysdp(nums,target);
    }

    private int  findTargetSumWaysdp(int[] nums, int target) {
        int sum = 0;
        for(int i = 0;i<nums.length;i++) {
            sum +=nums[i];
        }
        if((sum - target)%2 == 1 || sum < target) {
            return 0;
        }

        int ans = (sum-target)/2;
        int[][] dp = new int[nums.length+1][ans+1];
        dp[0][0] = 1;
        for(int j = 1;j<=ans;j++) {
            dp[0][j] = 0;
        }
        for(int i = 1;i<=nums.length;i++) {
            for(int j = 0;j<=ans;j++) {
                if(nums[i-1] > j) {
                    dp[i][j] = dp[i-1][j];
                } else {
                    dp[i][j] = dp[i-1][j]+dp[i-1][j-nums[i-1]];
                }
            }
        }
        return dp[nums.length][ans];

    }


    private void back_trace1(int[] nums,int i,int target) {
        if(i == nums.length) {
            if(target == 0) {
                res++;
            }
            return;
        }

        back_trace1(nums,i+1,target+nums[i]);
        back_trace1(nums,i+1,target-nums[i]);
    }

    private void back_trace2(int[] nums,int target) {
        int[] add = new int[nums.length];
        int k = 0;
        while(k>=0) {
            if(add[k] > 0) {
                target  = target+(add[k] == 1 ? nums[k] :-nums[k]);
            }
            add[k]++;
            if(add[k] <= 2) {
                target  = target-(add[k] == 1 ? nums[k] :-nums[k]);
                if(k == nums.length-1) {
                    if(target == 0) {
                        res++;
                    }
                } else {
                    add[++k] = 0;
                }
            } else {
                k--;
            }
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}