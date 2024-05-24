//二进制手表顶部有 4 个 LED 代表 小时（0-11），底部的 6 个 LED 代表 分钟（0-59）。每个 LED 代表一个 0 或 1，最低位在右侧。
// 
//
// 
// 例如，下面的二进制手表读取 "3:25" 。 
// 
//
// 
//
// （图源：WikiMedia - Binary clock samui moon.jpg ，许可协议：Attribution-ShareAlike 3.0 
//Unported (CC BY-SA 3.0) ） 
//
// 给你一个整数 turnedOn ，表示当前亮着的 LED 的数量，返回二进制手表可以表示的所有可能时间。你可以 按任意顺序 返回答案。 
//
// 小时不会以零开头： 
//
// 
// 例如，"01:00" 是无效的时间，正确的写法应该是 "1:00" 。 
// 
//
// 分钟必须由两位数组成，可能会以零开头： 
//
// 
// 例如，"10:2" 是无效的时间，正确的写法应该是 "10:02" 。 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：turnedOn = 1
//输出：["0:01","0:02","0:04","0:08","0:16","0:32","1:00","2:00","4:00","8:00"]
// 
//
// 示例 2： 
//
// 
//输入：turnedOn = 9
//输出：[]
// 
//
// 
//
// 提示： 
//
// 
// 0 <= turnedOn <= 10 
// 
// Related Topics 位运算 回溯算法 
// 👍 294 👎 0

package com.cute.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

public class BinaryWatch {
    public static void main(String[] args) {
        Solution solution = new BinaryWatch().new Solution();
        System.out.println(solution.readBinaryWatch(1));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        private final int N = 10;
        private int[] a = {1, 2, 4, 8, 16, 32};
        private boolean[] pos = new boolean[N];
        private List<String> res = new ArrayList<>();

        public List<String> readBinaryWatch(int turnedOn) {
            back_trace2(0,0, 0, turnedOn);
            return res;
        }


        //组合的遍历方式，不会有重复解
        private void back_trace2(int i, int hour, int minute, int turnedOn) {
            if (i == N || turnedOn == 0) {
                if(turnedOn == 0) {
                    res.add(String.format("%d:%02d", hour, minute));
                }
                return;
            }


            for (int j = 0; j <= 1; j++) {
                if (i < 4) {
                    if (hour + a[i] * j <= 11) {
                        back_trace2(i + 1, hour + a[i] * j, minute, turnedOn - j);
                    }
                } else {
                    if (minute + a[i - 4] * j <= 59) {
                        back_trace2(i + 1, hour, minute + a[i - 4] * j, turnedOn - j);
                    }
                }
            }
        }


        //排列的遍历方式，会有重复解
        private void back_trace1(int hour, int minute, int turnedOn) {
            if (turnedOn == 0) {
                res.add(String.format("%d:%02d", hour, minute));
                return;
            }
            for (int i = 0; i < 10; i++) {
                if (!valid(i, hour, minute)) {
                    continue;
                }
                pos[i] = true;
                if (i < 4) {
                    back_trace1(hour + a[i], minute, turnedOn - 1);
                } else {
                    back_trace1(hour, minute + a[i - 4], turnedOn - 1);
                }

                pos[i] = false;
            }
        }

        private boolean valid(int i, int hour, int minute) {
            if (pos[i]) {
                return false;
            }
            if (i < 4) {
                return hour + a[i] <= 11;
            }
            return minute + a[i - 4] <= 59;
        }


    }
//leetcode submit region end(Prohibit modification and deletion)

}