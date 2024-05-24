//给你一个数组 routes ，表示一系列公交线路，其中每个 routes[i] 表示一条公交线路，第 i 辆公交车将会在上面循环行驶。 
//
// 
// 例如，路线 routes[0] = [1, 5, 7] 表示第 0 辆公交车会一直按序列 1 -> 5 -> 7 -> 1 -> 5 -> 7 -> 1 
//-> ... 这样的车站路线行驶。 
// 
//
// 现在从 source 车站出发（初始时不在公交车上），要前往 target 车站。 期间仅可乘坐公交车。 
//
// 求出 最少乘坐的公交车数量 。如果不可能到达终点车站，返回 -1 。 
//
// 
//
// 示例 1： 
//
// 
//输入：routes = [[1,2,7],[3,6,7]], source = 1, target = 6
//输出：2
//解释：最优策略是先乘坐第一辆公交车到达车站 7 , 然后换乘第二辆公交车到车站 6 。 
// 
//
// 示例 2： 
//
// 
//输入：routes = [[7,12],[4,5,15],[6],[15,19],[9,12,13]], source = 15, target = 12
//输出：-1
// 
//
// 
//
// 提示： 
//
// 
// 1 <= routes.length <= 500. 
// 1 <= routes[i].length <= 105 
// routes[i] 中的所有值 互不相同 
// sum(routes[i].length) <= 105 
// 0 <= routes[i][j] < 106 
// 0 <= source, target < 106 
// 
// Related Topics 广度优先搜索 数组 哈希表 
// 👍 188 👎 0

package com.cute.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class BusRoutes {
    public static void main(String[] args) {
        Solution solution = new BusRoutes().new Solution();
        int[][] routes = {{1,2,7},{3,6,7}};
        solution.numBusesToDestination(routes,1,6);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int numBusesToDestination(int[][] routes, int source, int target) {
            if(source == target) {
                return 0;
            }
            Map<Integer, List<Integer>> siteRoutesMap = new HashMap<>();
            Map<Integer,Integer> routeStepMap = new HashMap<>();
            Queue<Integer> queue = new LinkedList<>();
            for(int i = 0;i<routes.length;i++) {
                for(int site : routes[i]) {
                    if(site == source) {
                        queue.add(i);
                        routeStepMap.put(i,1);
                    }
                    siteRoutesMap.computeIfAbsent(site,k->new ArrayList<>()).add(i);
                }
            }


            while(!queue.isEmpty()) {
                int iRoute = queue.poll();
                int step = routeStepMap.get(iRoute);
                for(int site : routes[iRoute]) {
                    if(site == target) {
                        return step;
                    }
                    List<Integer> siteRoutes = siteRoutesMap.get(site);
                    for(int siteRoute : siteRoutes) {
                        if(!routeStepMap.containsKey(siteRoute)) {
                            queue.offer(siteRoute);
                            routeStepMap.put(siteRoute,step+1);
                        }
                    }
                }
            }
            return -1;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}