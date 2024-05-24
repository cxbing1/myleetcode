//n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。 
//
// 
//
// 上图为 8 皇后问题的一种解法。 
//
// 给定一个整数 n，返回所有不同的 n 皇后问题的解决方案。 
//
// 每一种解法包含一个明确的 n 皇后问题的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。 
//
// 
//
// 示例： 
//
// 输入：4
//输出：[
// [".Q..",  // 解法 1
//  "...Q",
//  "Q...",
//  "..Q."],
//
// ["..Q.",  // 解法 2
//  "Q...",
//  "...Q",
//  ".Q.."]
//]
//解释: 4 皇后问题存在两个不同的解法。
// 
//
// 
//
// 提示： 
//
// 
// 皇后彼此不能相互攻击，也就是说：任何两个皇后都不能处于同一条横行、纵行或斜线上。 
// 
// Related Topics 回溯算法 
// 👍 616 👎 0

package com.cute.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

public class NQueens {
    public static void main(String[] args) {
        Solution solution = new NQueens().new Solution();
        long beginTime = System.currentTimeMillis();
        solution.solveNQueens(9);
        System.out.println(System.currentTimeMillis()-beginTime);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //结果集
        List<List<String>> res = new ArrayList<>();

        //数组记录冲突情况-主对角线，即斜率为-1的对角线，设直线上有一点(a,b),则有y-b = -(x-a),即x+y = a+b;
        //即主对角线上的点横纵坐标之和为定值
        boolean[] mainLine;
        //数组记录冲突情况-副对角线，即斜率为1的对角线，设直线上有一点(a,b),有y-b = x-a,即y-x = b-a;
        //即副对角线上的点横纵坐标之差为定值
        //对于n皇后，y-x最小值为0-(n-1) = 1-n,所以可设定值索引为y-x+n-1(使最小值为0,便于数组存储)
        boolean[] subLine;
        //数组记录冲突情况-列
        boolean[] colLine;

        public List<List<String>> solveNQueens(int n) {
           // return solveNQueens1(n);
            return solveNQueens2(n);
          //  return solveNQueens3(n);
        }

        public List<List<String>> solveNQueens3(int n) {
            int[] x = new int[n];
            x[0] = -1;
            int k = 0;
            while(k>=0) {
                x[k]++;
                while(x[k]>=0 && x[k]<n && !valid(x,k)) {
                    x[k]++;
                }
                if(x[k]>=0 && x[k]<n) {
                    if(k == n-1) {
                        //res.add(convert(x,n));
                    } else {
                        k++;
                        x[k] = -1;
                    }
                } else {
                    k--;
                }
            }
            return res;
        }
        private boolean valid(int[] x, int k) {
            for (int i = 0; i < k; i++) {
                if (x[i] == x[k]) {
                    return false;
                }

            }
            return true;
        }

        private List<String> convert(int[] x,int n) {
            List<String> res = new ArrayList<>();
            for(int i = 0;i<n;i++) {
                StringBuilder sb = new StringBuilder();

                for(int j = 0;j<n;j++) {
                    if(j == x[i]) {
                        sb.append('Q');
                    } else {
                        sb.append('.');
                    }
                }
                res.add(sb.toString());
            }
            return res;
        }


        //回溯方式1-使用遍历列及两个对角线的方式剪枝
        public List<List<String>> solveNQueens1(int n) {
            int[][] queens = new int[n][n];
            backtrace1(queens, 0);
            return res;
        }

        //回溯方式2-空间换时间，使用数组记录列及两个对角线的冲突情况
        public List<List<String>> solveNQueens2(int n) {
            int[][] queens = new int[n][n];
            mainLine = new boolean[2 * n - 1];
            subLine = new boolean[2 * n - 1];
            colLine = new boolean[n];
            backtrace2(queens, 0);
            return res;
        }


        //回溯方式1-使用遍历列及两个对角线的方式剪枝
        private void backtrace1(int[][] queens, int row) {

            //尝试到row行,即(0...row-1)行均已尝试完毕，找到一个可行解
            if (row == queens.length) {
                res.add(convert(queens));
                return;
            }

            //尝试当前行的(0...col-1)列
            for (int col = 0; col < queens[row].length; col++) {
                //当前预放置位置(row,col)将会发生冲突，剪枝
                if (isValid(queens, row, col)) {
                    continue;
                }
                //正式放置位置(row,col)
                queens[row][col] = 1;

                //尝试下一行
                backtrace1(queens, row + 1);

                //尝试完成，取消位置(row,col)的放置
                queens[row][col] = 0;

            }
        }


        //回溯方式2-空间换时间，使用数组记录列及两个对角线的冲突情况
        private void backtrace2(int[][] queens, int row) {
            //尝试到row行,即(0...n-1)行均已尝试完毕，找到一个可行解
            if (row == queens.length) {
                //res.add(convert(queens));
                return;
            }

            int n = queens[row].length;
            //尝试当前行的(0...n-1)列
            for (int col = 0; col < n; col++) {
                //当前预放置位置(row,col)将会发生冲突，剪枝
                if (colLine[col]) //|| mainLine[row + col] || subLine[col - row + n - 1])
                {
                    continue;
                }
                //正式放置位置(row,col)
                queens[row][col] = 1;
                //设置列线/主对角线/副对角线为已占用
                colLine[col] = true;
               // mainLine[row + col] = true;
               // subLine[col - row + n - 1] = true;

                //尝试下一行
                backtrace2(queens, row + 1);

                //尝试完成，取消位置(row,col)的放置
                queens[row][col] = 0;
                //取消列线/主对角线/副对角线占用状态
                colLine[col] = false;
              //  mainLine[row + col] = false;
               // subLine[col - row + n - 1] = false;

            }
        }

        //回溯方式1-使用遍历列及两个对角线的方式剪枝
        private boolean isValid(int[][] queens, int i, int j) {
            int n = queens.length;
            for (int k = 0; k < n; k++) {
                //列
                if (queens[k][j] == 1) {
                    return true;
                }
                //斜率为-1的对角线 y=i+j-k;
                if (i + j - k >= 0 && i + j - k < n && queens[k][i + j - k] == 1) {
                    return true;
                }
                //斜率为1的对角线 y = k+j-i;
                if (j - i + k >= 0 && j - i + k < n && queens[k][j - i + k] == 1) {
                    return true;
                }
            }

            return false;
        }

        private List<String> convert(int[][] queens) {
            List<String> board = new ArrayList<>();
            for (int i = 0; i < queens.length; i++) {
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < queens[i].length; j++) {
                    if (queens[i][j] == 1) {
                        sb.append('Q');
                    } else {
                        sb.append('.');
                    }
                }
                board.add(sb.toString());
            }
            return board;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}