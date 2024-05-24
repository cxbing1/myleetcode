//一个公司准备组织一场会议，邀请名单上有 n 位员工。公司准备了一张 圆形 的桌子，可以坐下 任意数目 的员工。 
//
// 员工编号为 0 到 n - 1 。每位员工都有一位 喜欢 的员工，每位员工 当且仅当 他被安排在喜欢员工的旁边，他才会参加会议。每位员工喜欢的员工 不会 
//是他自己。 
//
// 给你一个下标从 0 开始的整数数组 favorite ，其中 favorite[i] 表示第 i 位员工喜欢的员工。请你返回参加会议的 最多员工数目 。 
//
//
// 
//
// 示例 1： 
//
// 
//
// 输入：favorite = [2,2,1,2]
//输出：3
//解释：
//上图展示了公司邀请员工 0，1 和 2 参加会议以及他们在圆桌上的座位。
//没办法邀请所有员工参与会议，因为员工 2 没办法同时坐在 0，1 和 3 员工的旁边。
//注意，公司也可以邀请员工 1，2 和 3 参加会议。
//所以最多参加会议的员工数目为 3 。
// 
//
// 示例 2： 
//
// 输入：favorite = [1,2,0]
//输出：3
//解释：
//每个员工都至少是另一个员工喜欢的员工。所以公司邀请他们所有人参加会议的前提是所有人都参加了会议。
//座位安排同图 1 所示：
//- 员工 0 坐在员工 2 和 1 之间。
//- 员工 1 坐在员工 0 和 2 之间。
//- 员工 2 坐在员工 1 和 0 之间。
//参与会议的最多员工数目为 3 。
// 
//
// 示例 3： 
//
// 
//
// 输入：favorite = [3,0,1,4,1]
//输出：4
//解释：
//上图展示了公司可以邀请员工 0，1，3 和 4 参加会议以及他们在圆桌上的座位。
//员工 2 无法参加，因为他喜欢的员工 0 旁边的座位已经被占领了。
//所以公司只能不邀请员工 2 。
//参加会议的最多员工数目为 4 。
// 
//
// 
//
// 提示： 
//
// 
// n == favorite.length 
// 2 <= n <= 10⁵ 
// 0 <= favorite[i] <= n - 1 
// favorite[i] != i 
// 
//
// Related Topics 深度优先搜索 图 拓扑排序 👍 167 👎 0

package com.cute.leetcode.editor.cn;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

public class MaximumEmployeesToBeInvitedToAMeeting {
    public static void main(String[] args) {
        Solution solution = new MaximumEmployeesToBeInvitedToAMeeting().new Solution();
        int[] favorite = {2,2,1,2};
        System.out.println(solution.maximumInvitations(favorite));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int maximumInvitations(int[] favorite) {
            int n = favorite.length;
            int[] in = new int[n];
            for (int f : favorite) {
                in[f]++;
            }
            Deque<Integer> queue = new ArrayDeque<>();

            for (int i = 0; i < n; i++) {
                if (in[i] == 0) {
                    queue.addLast(i);
                }
            }
            int[] maxDepth = new int[n];
            Arrays.fill(maxDepth, 1);
            //List<Integer>[] fg = new List[n];

            while (!queue.isEmpty()) {
                int cur = queue.poll();
                int fav = favorite[cur];
//                if (fg[fav] == null) {
//                    fg[fav] = new ArrayList<>();
//                }
//                fg[fav].add(cur);
                maxDepth[fav] = Math.max(maxDepth[fav],maxDepth[cur] + 1);
                if (--in[fav] == 0) {
                    queue.addLast(fav);
                }
            }
            int maxRingSize = 0, maxEachOtherSize = 0;
            for (int i = 0; i < n; i++) {
                if (in[i] == 0) {
                    continue;
                }
                int ringSize = 1;
                in[i] = 0;
                for (int j = favorite[i]; j != i; j = favorite[j]) {
                    ringSize++;
                    in[j] = 0;
                }
                if(ringSize > 2) {
                    maxRingSize = Math.max(maxRingSize, ringSize);
                } else if(ringSize == 2) {
                    //maxEachOtherSize += (maxDepth(fg, i) + maxDepth(fg, favorite[i]));
                    maxEachOtherSize +=(maxDepth[i]+maxDepth[favorite[i]]);
                }
            }
            return Math.max(maxRingSize, maxEachOtherSize);
        }
        private int maxDepth(List<Integer>[] fg, int cur) {
            if (fg[cur] == null) {
                return 1;
            }
            int maxDepth = 0;
            for (int next : fg[cur]) {
                maxDepth = Math.max(maxDepth, maxDepth(fg, next));
            }
            return maxDepth+1;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}