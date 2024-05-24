//在一个有向图中，节点分别标记为 0, 1, ..., n-1。图中每条边为红色或者蓝色，且存在自环或平行边。 
//
// red_edges 中的每一个 [i, j] 对表示从节点 i 到节点 j 的红色有向边。类似地，blue_edges 中的每一个 [i, j] 对表示从
//节点 i 到节点 j 的蓝色有向边。 
//
// 返回长度为 n 的数组 answer，其中 answer[X] 是从节点 0 到节点 X 的红色边和蓝色边交替出现的最短路径的长度。如果不存在这样的路径，
//那么 answer[x] = -1。 
//
// 
//
// 示例 1： 
//
// 
//输入：n = 3, red_edges = [[0,1],[1,2]], blue_edges = []
//输出：[0,1,-1]
// 
//
// 示例 2： 
//
// 
//输入：n = 3, red_edges = [[0,1]], blue_edges = [[2,1]]
//输出：[0,1,-1]
// 
//
// 示例 3： 
//
// 
//输入：n = 3, red_edges = [[1,0]], blue_edges = [[2,1]]
//输出：[0,-1,-1]
// 
//
// 示例 4： 
//
// 
//输入：n = 3, red_edges = [[0,1]], blue_edges = [[1,2]]
//输出：[0,1,2]
// 
//
// 示例 5： 
//
// 
//输入：n = 3, red_edges = [[0,1],[0,2]], blue_edges = [[1,0]]
//输出：[0,1,1]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= n <= 100 
// red_edges.length <= 400 
// blue_edges.length <= 400 
// red_edges[i].length == blue_edges[i].length == 2 
// 0 <= red_edges[i][j], blue_edges[i][j] < n 
// 
// Related Topics 广度优先搜索 图 👍 159 👎 0

package com.cute.leetcode.editor.cn;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

public class ShortestPathWithAlternatingColors {
    public static void main(String[] args) {
        Solution solution = new ShortestPathWithAlternatingColors().new Solution();
        int n = 3;
        int[][] redEdges = {{0,1},{1,2}},blueEdges = {};
        int[] answer = solution.shortestAlternatingPaths(3,redEdges,blueEdges);
        System.out.println((Arrays.toString(answer)));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int[] shortestAlternatingPaths(int n, int[][] redEdges, int[][] blueEdges) {
            List<Integer>[][] graph = new List[2][n];
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < n; j++) {
                    graph[i][j] = new ArrayList<>();
                }
            }
            for (int[] edge : redEdges) {
                graph[0][edge[0]].add(edge[1]);
            }
            for (int[] edge : blueEdges) {
                graph[1][edge[0]].add(edge[1]);
            }
            int[][] dist = new int[2][n];
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < n; j++) {
                    dist[i][j] = Integer.MAX_VALUE;
                }
            }
            dist[0][0] = 0;
            dist[1][0] = 0;

            Queue<int[]> queue = new ArrayDeque<>();
            queue.offer(new int[]{0, 0});
            queue.offer(new int[]{1, 0});

            while (!queue.isEmpty()) {
                int[] p = queue.poll();
                int color = p[0], node = p[1];
                for (int nextNode : graph[1 - color][node]) {
                    if (dist[1 - color][nextNode] != Integer.MAX_VALUE) {
                        continue;
                    }
                    dist[1 - color][nextNode] = dist[color][node] + 1;
                    queue.offer(new int[]{1 - color, nextNode});
                }
            }
            int[] answer = new int[n];

            for (int i = 0; i < n; i++) {
                answer[i] = Math.min(dist[0][i], dist[1][i]);
                if (answer[i] == Integer.MAX_VALUE) {
                    answer[i] = -1;
                }
            }
            return answer;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}