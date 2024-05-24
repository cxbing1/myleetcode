//假设有从 1 到 N 的 N 个整数，如果从这 N 个数字中成功构造出一个数组，使得数组的第 i 位 (1 <= i <= N) 满足如下两个条件中的一个，
//我们就称这个数组为一个优美的排列。条件： 
//
// 
// 第 i 位的数字能被 i 整除 
// i 能被第 i 位上的数字整除 
// 
//
// 现在给定一个整数 N，请问可以构造多少个优美的排列？ 
//
// 示例1: 
//
// 
//输入: 2
//输出: 2
//解释: 
//
//第 1 个优美的排列是 [1, 2]:
//  第 1 个位置（i=1）上的数字是1，1能被 i（i=1）整除
//  第 2 个位置（i=2）上的数字是2，2能被 i（i=2）整除
//
//第 2 个优美的排列是 [2, 1]:
//  第 1 个位置（i=1）上的数字是2，2能被 i（i=1）整除
//  第 2 个位置（i=2）上的数字是1，i（i=2）能被 1 整除
// 
//
// 说明: 
//
// 
// N 是一个正整数，并且不会超过15。 
// 
// Related Topics 位运算 数组 动态规划 回溯 状态压缩 
// 👍 228 👎 0

package com.cute.leetcode.editor.cn;

import java.util.Map;

public class BeautifulArrangement {
    public static void main(String[] args) {
        Solution solution = new BeautifulArrangement().new Solution();
        System.out.println(solution.countArrangement(2));
        ;
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        boolean[] visited;
        int ans = 0;

        public int countArrangement(int n) {
            //visited = new boolean[n + 1];
            //dfs1(1,n);
            //return ans;

            //return dfs2(1,n,0);

            //return dfs3(1, n, 0, new int[n + 1][1 << (n + 1)]);

            //return dp1(n);

            return dp2(n);


        }

        private void dfs1(int index, int n) {
            if (index > n) {
                ans++;
                return;
            }
            for (int i = 1; i <= n; i++) {
                if (!visited[i] && isDivide(i, index)) {
                    visited[i] = true;
                    dfs1(index + 1, n);
                    visited[i] = false;
                }
            }
        }

        //状态压缩，替换visited数组为int，使用二进制位代替数组
        private int dfs2(int index, int n, int visited) {
            if (index > n) {
                return 1;
            }

            int ans = 0;
            for (int i = 1; i <= n; i++) {
                if (((1 << i) & visited) == 0 && isDivide(i, index)) {
                    ans += dfs2(index + 1, n, visited | (1 << i));
                }
            }
            return ans;
        }

        //备忘录记忆化，减少重复[index,visited]的计算
        private int dfs3(int index, int n, int visited, int[][] memo) {
            if (index > n) {
                return 1;
            }
            if (memo[index][visited] > 0) {
                return memo[index][visited];
            }
            int ans = 0;
            for (int i = 1; i <= n; i++) {
                if (((1 << i) & visited) == 0 && isDivide(i, index)) {
                    ans += dfs3(index + 1, n, visited | (1 << i), memo);
                }
            }
            memo[index][visited] = ans;
            return ans;
        }

        private int dp1(int n) {
            int mask = 1 << n;
            int[][] dp = new int[n + 1][mask];
            dp[0][0] = 1;
            for (int index = 1; index <= n; index++) {
                for (int visited = 0; visited < mask; visited++) {
                    if (Integer.bitCount(visited) == index) {
                        for (int num = 1; num <= n; num++) {
                            if ((1 << (num - 1) & visited) != 0 && isDivide(index, num)) {
                                dp[index][visited] += dp[index - 1][visited & (~(1 << (num - 1)))];
                            }
                        }
                    }
                }
            }
            return dp[n][mask - 1];
        }

        private int dp2(int n) {
            int mask = 1 << n;
            int[][] dp = new int[n + 1][mask];
            dp[0][0] = 1;
            for (int visited = 0; visited < mask; visited++) {
                int index = Integer.bitCount(visited);
                for (int num = 1; num <= n; num++) {
                    if ((1 << (num - 1) & visited) != 0 && isDivide(index, num)) {
                        dp[index][visited] += dp[index - 1][visited & (~(1 << (num - 1)))];
                    }
                }
            }
            return dp[n][mask - 1];
        }


        private boolean isDivide(int a, int b) {
            return (a % b == 0) || (b % a == 0);
        }

    }
//leetcode submit region end(Prohibit modification and deletion)

}