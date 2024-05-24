//给你一棵 n 个节点的无向树，节点编号为 1 到 n 。给你一个整数 n 和一个长度为 n - 1 的二维整数数组 edges ，其中 edges[i] =
// [ui, vi] 表示节点 ui 和 vi 在树中有一条边。 
//
// 请你返回树中的 合法路径数目 。 
//
// 如果在节点 a 到节点 b 之间 恰好有一个 节点的编号是质数，那么我们称路径 (a, b) 是 合法的 。 
//
// 注意： 
//
// 
// 路径 (a, b) 指的是一条从节点 a 开始到节点 b 结束的一个节点序列，序列中的节点 互不相同 ，且相邻节点之间在树上有一条边。 
// 路径 (a, b) 和路径 (b, a) 视为 同一条 路径，且只计入答案 一次 。 
// 
//
// 
//
// 示例 1： 
//
// 
//
// 
//输入：n = 5, edges = [[1,2],[1,3],[2,4],[2,5]]
//输出：4
//解释：恰好有一个质数编号的节点路径有：
//- (1, 2) 因为路径 1 到 2 只包含一个质数 2 。
//- (1, 3) 因为路径 1 到 3 只包含一个质数 3 。
//- (1, 4) 因为路径 1 到 4 只包含一个质数 2 。
//- (2, 4) 因为路径 2 到 4 只包含一个质数 2 。
//只有 4 条合法路径。
// 
//
// 示例 2： 
//
// 
//
// 
//输入：n = 6, edges = [[1,2],[1,3],[2,4],[3,5],[3,6]]
//输出：6
//解释：恰好有一个质数编号的节点路径有：
//- (1, 2) 因为路径 1 到 2 只包含一个质数 2 。
//- (1, 3) 因为路径 1 到 3 只包含一个质数 3 。
//- (1, 4) 因为路径 1 到 4 只包含一个质数 2 。
//- (1, 6) 因为路径 1 到 6 只包含一个质数 3 。
//- (2, 4) 因为路径 2 到 4 只包含一个质数 2 。
//- (3, 6) 因为路径 3 到 6 只包含一个质数 3 。
//只有 6 条合法路径。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= n <= 10⁵ 
// edges.length == n - 1 
// edges[i].length == 2 
// 1 <= ui, vi <= n 
// 输入保证 edges 形成一棵合法的树。 
// 
//
// Related Topics 树 深度优先搜索 数学 动态规划 数论 👍 55 👎 0

package com.cute.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

public class CountValidPathsInATree {
    public static void main(String[] args) {
        Solution solution = new CountValidPathsInATree().new Solution();
        int[][] edges = {{1,2},{1,3},{2,4},{2,5}};
        int n = 5;
        System.out.println(solution.countPaths(n,edges));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        boolean[] prime;
        public long countPaths(int n, int[][] edges) {
            List<Integer>[] graph = new ArrayList[n+1];
            int[] blockSize = new int[n+1];
            prime = new boolean[n+1];
            for(int i=1;i<=n;i++) {
                graph[i] = new ArrayList<>();
                prime[i] = isPrime(i);
            }
            for(int[] edge : edges) {
                graph[edge[0]].add(edge[1]);
                graph[edge[1]].add(edge[0]);
            }
            long res = 0;
            for(int i =1 ;i<=n;i++) {
                if(!prime[i]) {
                    continue;
                }
                int nodeSize = 0;
                for(int node : graph[i]) {
                    if(prime[node]) {
                        continue;
                    }
                    if(blockSize[node] == 0) {
                        List<Integer> blockList = new ArrayList<>();
                        dfs(node,graph,i,blockList);
                        for(int block : blockList) {
                            blockSize[block] = blockList.size();
                        }
                    }
                    res += blockSize[node] * nodeSize;
                    nodeSize += blockSize[node];
                }
                res += nodeSize;
            }
            return res;
        }

        private void dfs(int node,List<Integer>[] graph,int father,List<Integer> blockList) {
            blockList.add(node);
            for(int child : graph[node]) {
                if(child != father &&!prime[child] ) {
                    dfs(child,graph,node,blockList);
                }
            }
        }

        private boolean isPrime(int n) {
            if(n==1) {
                return false;
            }
            for(int i =2;i*i<=n;i++) {
                if(n%i==0) {
                    return false;
                }
            }
            return true;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}