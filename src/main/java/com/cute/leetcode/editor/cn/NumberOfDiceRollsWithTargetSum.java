//这里有 n 个一样的骰子，每个骰子上都有 k 个面，分别标号为 1 到 k 。 
//
// 给定三个整数 n , k 和 target ，返回可能的方式(从总共 kⁿ 种方式中)滚动骰子的数量，使正面朝上的数字之和等于 target 。 
//
// 答案可能很大，你需要对 10⁹ + 7 取模 。 
//
// 
//
// 示例 1： 
//
// 
//输入：n = 1, k = 6, target = 3
//输出：1
//解释：你扔一个有 6 个面的骰子。
//得到 3 的和只有一种方法。
// 
//
// 示例 2： 
//
// 
//输入：n = 2, k = 6, target = 7
//输出：6
//解释：你扔两个骰子，每个骰子有 6 个面。
//得到 7 的和有 6 种方法：1+6 2+5 3+4 4+3 5+2 6+1。
// 
//
// 示例 3： 
//
// 
//输入：n = 30, k = 30, target = 500
//输出：222616187
//解释：返回的结果必须是对 10⁹ + 7 取模。 
//
// 
//
// 提示： 
//
// 
// 1 <= n, k <= 30 
// 1 <= target <= 1000 
// 
//
// Related Topics 动态规划 👍 236 👎 0

package com.cute.leetcode.editor.cn;

import java.util.Arrays;

public class NumberOfDiceRollsWithTargetSum {
    public static void main(String[] args) {
        Solution solution = new NumberOfDiceRollsWithTargetSum().new Solution();
        System.out.println(solution.numRollsToTarget(30,30,500));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int numRollsToTarget(int n, int k, int target) {
           //return numRollsToTargetDfs(n,k,target);
            //return numRollsToTargetDp1(n,k,target);
            //return numRollsToTargetDp2(n,k,target);
            return numRollsToTargetDp3(n,k,target);
        }

        private int numRollsToTargetDp3(int n, int k, int target) {
            if (target > n * k || target < n) {
                return 0;
            }
            int[] dp = new int[target+1];
            dp[0]=1;
            for(int i = 1;i<=n;i++) {
                for(int j = target;j>=0;j--) {
                    dp[j] = 0;
                    for(int t =1;t<=k&&t<=j;t++) {
                        dp[j] = (dp[j] + dp[j-t]) %mod;
                    }
                }
            }
            return dp[target];
        }

        private int numRollsToTargetDp2(int n, int k, int target) {
            if (target > n * k || target < n) {
                return 0;
            }
            int[][] dp = new int[2][target+1];
            dp[0][0] = 1;
            for(int i = 1;i<=n;i++) {
                int index = i&1,pIndex = (i-1)&1;
                for(int j = 0;j<=target;j++) {
                    dp[index][j] = 0;
                    for(int t=1;t<=k&&t<=j;t++) {
                        dp[index][j] = (dp[index][j] + dp[pIndex][j-t]) % mod;
                    }
                }
            }
            printDp(dp);
            return dp[n&1][target];
        }

        private int numRollsToTargetDp1(int n, int k, int target) {
            if (target > n * k || target < n) {
                return 0;
            }
            int[][] dp = new int[n+1][target+1];
            dp[0][0] = 1;
            for(int i = 1;i<=n;i++) {
                for(int j = 1;j<=target;j++) {
                    for(int t=1;t<=k&&t<=j;t++) {
                        dp[i][j] = (dp[i][j] + dp[i-1][j-t]) % mod;
                    }
                }
            }
            printDp(dp);
            return dp[n][target];
        }

        private int numRollsToTargetDfs(int n, int k, int target) {
            if (target > n * k || target < n) {
                return 0;
            }
            memo = new int[n+1][target+1];
            for(int i=0;i<=n;i++) {
                for(int j = 0;j<=target;j++) {
                    memo[i][j] = -1;
                }
            }
            return dfs(n, k, target);
        }

        int[][] memo;

        private int mod = 1000000007;

        private int dfs(int n, int k, int target) {
            if (n == 0) {
                return target == 0 ? 1 : 0;
            }

            if(memo[n][target] > -1) {
                return memo[n][target];
            }

            int res = 0;
            for (int i = 1; i <= k && i<=target; i++) {
                res = (res + dfs(n - 1, k, target - i)) % mod;
            }
            memo[n][target] = res;
            return res;
        }

        private void printDp(int[][] dp) {
            for(int[] d : dp) {
                System.out.println(Arrays.toString(d));
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}