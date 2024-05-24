//给你一个整数数组 coins 表示不同面额的硬币，另给一个整数 amount 表示总金额。 
//
// 请你计算并返回可以凑成总金额的硬币组合数。如果任何硬币组合都无法凑出总金额，返回 0 。 
//
// 假设每一种面额的硬币有无限个。 
//
// 题目数据保证结果符合 32 位带符号整数。 
//
// 
//
// 
// 
//
// 示例 1： 
//
// 
//输入：amount = 5, coins = [1, 2, 5]
//输出：4
//解释：有四种方式可以凑成总金额：
//5=5
//5=2+2+1
//5=2+1+1+1
//5=1+1+1+1+1
// 
//
// 示例 2： 
//
// 
//输入：amount = 3, coins = [2]
//输出：0
//解释：只用面额 2 的硬币不能凑成总金额 3 。
// 
//
// 示例 3： 
//
// 
//输入：amount = 10, coins = [10] 
//输出：1
// 
//
// 
//
// 提示： 
//
// 
// 1 <= coins.length <= 300 
// 1 <= coins[i] <= 5000 
// coins 中的所有值 互不相同 
// 0 <= amount <= 5000 
// 
// 👍 524 👎 0

package com.cute.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CoinChange2 {
    public static void main(String[] args) {
        Solution solution = new CoinChange2().new Solution();
        int[] coins = {1,2,5};
        System.out.println(solution.change(5, coins));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        int res = 0;

        public int change(int amount, int[] coins) {
            //dfs(0,amount,coins);
            //res =  dp1(amount,coins);
            //res = dp2(amount, coins);
            //res = dp3(amount,coins);
            //res = dp4(amount,coins);
            res = dp5(amount,coins);
            return res;
        }


        //dp3状态压缩，一维数组
        private int dp5(int amount, int[] coins) {
            int[] dp = new int[amount + 1];
            dp[0] = 1;

            for (int i = 1; i < coins.length+1; i++) {
                for (int j = 1; j < amount+1; j++) {
                    /*if(j-coins[i-1] < 0) {
                        dp[j] = dp[j];
                    } else {
                        dp[j] = dp[j] + dp[j-coins[i-1]];
                    }*/
                    if(j-coins[i-1] >=0) {
                        dp[j] += dp[j-coins[i-1]];
                    }

                }
            }
            return dp[amount];
        }

        //dp3状态压缩，滚动数组
        private int dp4(int amount, int[] coins) {
            int[][] dp = new int[2][amount + 1];
            for (int i = 0; i < dp.length; i++) {
                dp[i][0] = 1;
            }

            for (int i = 1; i < coins.length+1; i++) {
                for (int j = 1; j < dp[0].length; j++) {
                    if(j-coins[i-1] < 0) {
                        dp[i&1][j] = dp[(i-1)&1][j];
                    } else {
                        dp[i&1][j] = dp[(i-1)&1][j] + dp[i&1][j-coins[i-1]];
                    }

                }
            }
            print(dp);
            return dp[coins.length&1][amount];
        }

        //循环优化，改三层循环为双层循环
        //dp1状态转移方程为 dp[i][j]=dp[i-1][j]+dp[i-1][j-coin[i-1]]+dp[i-1][j-2coins[i-1])]+...dp[i-1][j-k*coins[i-1]] (k*coins[i-1]<=j)
        //我们将j换元为j-coin[i-1],则有dp[i][j-coin[i-1]] = dp[i-1][j-coin[i-1]] + dp[i-1][j-2coin[i-1]]+dp[i-1][j-3coins[i-1])]+...dp[i-1][j-（k+1）*coins[i-1]] (k*coins[i-1]<=j)
        //则优化后的状态转移方程为 dp[i][j] = dp[i-1][j] + dp[i][j-coin[i-1]];
        private int dp3(int amount, int[] coins) {
            int[][] dp = new int[coins.length + 1][amount + 1];
            for (int i = 0; i < dp.length; i++) {
                dp[i][0] = 1;
            }

            for (int i = 1; i < dp.length; i++) {
                for (int j = 1; j < dp[0].length; j++) {
                    if(j-coins[i-1] < 0) {
                        dp[i][j] = dp[i-1][j];
                    } else {
                        dp[i][j] = dp[i-1][j] + dp[i][j-coins[i-1]];
                    }

                }
            }
            return dp[coins.length][amount];
        }

        //dp1状态压缩，滚动数组
        //存在dp1的基础上，对dp数组i下标的地方对2取余即可，我们用&1代替%2
        private int dp2(int amount, int[] coins) {
            int m = coins.length+1;
            int[][] dp = new int[2][amount + 1];
            for (int i = 0; i < dp.length; i++) {
                dp[i][0] = 1;
            }

            for (int i = 1; i < m; i++) {
                for (int j = 1; j < dp[0].length; j++) {
                    dp[i&1][j] = 0;
                    for (int k = 0; k * coins[i - 1] <= j; k++) {
                        dp[i & 1][j] += dp[(i - 1) & 1][j - k * coins[i - 1]];
                    }
                }
            }
            print(dp);
            return dp[coins.length&1][amount];
        }

        private int dp1(int amount, int[] coins) {
            int[][] dp = new int[coins.length + 1][amount + 1];
            for (int i = 0; i < dp.length; i++) {
                dp[i][0] = 1;
            }

            for (int i = 1; i < dp.length; i++) {
                for (int j = 1; j < dp[0].length; j++) {
                    for (int k = 0; k * coins[i - 1] <= j; k++) {
                        dp[i][j] += dp[i - 1][j - k * coins[i - 1]];
                    }
                }
            }
            return dp[coins.length][amount];
        }

        private void dfs(int i, int amount, int[] coins) {
            if (i == coins.length) {
                if (amount == 0) {
                    res++;
                }
                return;
            }


            for (int j = 0; j * coins[i] <= amount; j++) {
                dfs(i + 1, amount - j * coins[i], coins);
            }
        }

        private void print(int[][] dp) {
            for(int i = 0;i<dp.length;i++) {
                StringBuilder sb = new StringBuilder();
                for(int j = 0;j<dp[0].length;j++) {
                    sb.append(dp[i][j] + " ");
                }
                System.out.println(sb.toString());
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}