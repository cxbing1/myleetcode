//给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。如果没有任何一种硬币组合能组成总金额，返回
// -1。 
//
// 你可以认为每种硬币的数量是无限的。 
//
// 
//
// 示例 1： 
//
// 
//输入：coins = [1, 2, 5], amount = 11
//输出：3 
//解释：11 = 5 + 5 + 1 
//
// 示例 2： 
//
// 
//输入：coins = [2], amount = 3
//输出：-1 
//
// 示例 3： 
//
// 
//输入：coins = [1], amount = 0
//输出：0
// 
//
// 示例 4： 
//
// 
//输入：coins = [1], amount = 1
//输出：1
// 
//
// 示例 5： 
//
// 
//输入：coins = [1], amount = 2
//输出：2
// 
//
// 
//
// 提示： 
//
// 
// 1 <= coins.length <= 12 
// 1 <= coins[i] <= 231 - 1 
// 0 <= amount <= 104 
// 
// Related Topics 动态规划 
// 👍 1311 👎 0

package com.cute.leetcode.editor.cn;

public class CoinChange {
    public static void main(String[] args) {
        Solution solution = new CoinChange().new Solution();
        int[] coins = {6};
        System.out.println(solution.coinChange(coins, 6));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        int INF = Integer.MAX_VALUE;

        public int coinChange(int[] coins, int amount) {
            //return dp1(coins, amount);
            //return dp2(coins,amount);
            // return dp3(coins,amount);
            // return dp4(coins,amount);
            return dp5(coins, amount);
        }

        //dp3状态压缩，一维数组
        private int dp5(int[] coins, int amount) {
            int[] dp = new int[amount + 1];

            for (int j = 1; j < dp.length; j++) {
                dp[j] = INF;
            }

            for (int i = 1; i < coins.length + 1; i++) {
                for (int j = 1; j < amount + 1; j++) {
                    if (j - coins[i - 1] >= 0) {
                        if (dp[j - coins[i - 1]] != INF) {
                            dp[j] = Math.min(dp[j], dp[j - coins[i - 1]] + 1);
                        }
                    }
                }
            }
            return dp[amount] == INF ? -1 : dp[amount];
        }

        //dp3状态压缩，滚动数组
        private int dp4(int[] coins, int amount) {
            int[][] dp = new int[2][amount + 1];

            for (int j = 1; j < dp[0].length; j++) {
                dp[0][j] = INF;
            }

            for (int i = 1; i < coins.length + 1; i++) {
                for (int j = 1; j < amount + 1; j++) {
                    if (j - coins[i - 1] < 0) {
                        dp[i & 1][j] = dp[(i - 1) & 1][j];
                    } else {
                        dp[i & 1][j] = dp[(i - 1) & 1][j];
                        if (dp[i & 1][j - coins[i - 1]] != INF) {
                            dp[i & 1][j] = Math.min(dp[i & 1][j], dp[i & 1][j - coins[i - 1]] + 1);
                        }
                    }
                }
            }
            return dp[coins.length & 1][amount] == INF ? -1 : dp[coins.length & 1][amount];
        }

        //循环优化，三层循环转化为两层循环
        //dp1中状态转移方程为dp[i][j] = Math.min(dp[i-1][j],dp[i-1][j-coins[i-1]]+1,dp[i-1][j-2coins[i-1]]+2,...,dpp[i-1][j-k*coins[i-1]]+k) (j>=k*coins[i-1]])
        //对j换元为j-coins[i-1],则dp[i][j-coins[i-1]] = Math.min(dp[i-1][j-coins[i-1]],dp[i-1][j-2coins[i-1]]+1,dp[i-1][j-3coins[i-1]]+2,...,dp[j-coins[j-(k+1)*coins[i-1]]]+k)
        //则有dp[i][j-coins[i-1]]+1 = Math.min(dp[i-1][j-coins[i-1]]+1,dp[i-1][j-2coins[i-1]]+2,dp[i-1][j-3coins[i-1]]+3,...,dp[j-coins[j-(k+1)*coins[i-1]]]+k+1)
        //则有dp[i][j] = Math.min(dp[i-1][j],dp[i][j-coins[i-1]]+1);
        private int dp3(int[] coins, int amount) {
            int[][] dp = new int[coins.length + 1][amount + 1];

            for (int j = 1; j < dp[0].length; j++) {
                dp[0][j] = INF;
            }

            for (int i = 1; i < coins.length + 1; i++) {
                for (int j = 1; j < amount + 1; j++) {
                    if (j - coins[i - 1] < 0) {
                        dp[i][j] = dp[i - 1][j];
                    } else {
                        dp[i][j] = dp[i - 1][j];
                        if (dp[i][j - coins[i - 1]] != INF) {
                            dp[i][j] = Math.min(dp[i][j], dp[i][j - coins[i - 1]] + 1);
                        }
                    }

                }
            }
            return dp[coins.length][amount] == INF ? -1 : dp[coins.length][amount];
        }

        //dp1状态压缩，滚动数组
        private int dp2(int[] coins, int amount) {
            int[][] dp = new int[2][amount + 1];
            for (int j = 1; j < dp[0].length; j++) {
                dp[0][j] = INF;
            }

            for (int i = 1; i < coins.length + 1; i++) {
                for (int j = 1; j < amount + 1; j++) {
                    dp[i & 1][j] = dp[(i - 1) & 1][j];
                    for (int k = 1; k * coins[i - 1] <= j; k++) {
                        int tmp = dp[(i - 1) & 1][j - k * coins[i - 1]];
                        if (tmp != INF) {
                            dp[i & 1][j] = Math.min(dp[i & 1][j], tmp + k);
                        }
                    }
                }
            }
            return dp[coins.length & 1][amount] == INF ? -1 : dp[coins.length & 1][amount];
        }

        private int dp1(int[] coins, int amount) {
            int[][] dp = new int[coins.length + 1][amount + 1];
            for (int j = 1; j < dp[0].length; j++) {
                dp[0][j] = INF;
            }

            for (int i = 1; i < dp.length; i++) {
                for (int j = 1; j < dp[0].length; j++) {
                    dp[i][j] = dp[i - 1][j];
                    for (int k = 1; k * coins[i - 1] <= j; k++) {
                        int tmp = dp[i - 1][j - k * coins[i - 1]];
                        if (tmp != INF) {
                            dp[i][j] = Math.min(dp[i][j], tmp + k);
                        }
                    }
                }
            }

            return dp[coins.length][amount] == INF ? -1 : dp[coins.length][amount];
        }


    }
//leetcode submit region end(Prohibit modification and deletion)

}