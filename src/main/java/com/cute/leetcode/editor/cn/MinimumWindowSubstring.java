//给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。 
//
// 注意：如果 s 中存在这样的子串，我们保证它是唯一的答案。 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "ADOBECODEBANC", t = "ABC"
//输出："BANC"
// 
//
// 示例 2： 
//
// 
//输入：s = "a", t = "a"
//输出："a"
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length, t.length <= 105 
// s 和 t 由英文字母组成 
// 
//
// 
//进阶：你能设计一个在 o(n) 时间内解决此问题的算法吗？ Related Topics 哈希表 双指针 字符串 Sliding Window 
// 👍 1178 👎 0

package com.cute.leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;

public class MinimumWindowSubstring {
    public static void main(String[] args) {
        Solution solution = new MinimumWindowSubstring().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public String minWindow(String s, String t) {
            //保存"要求字符"及对应个数
            Map<Character, Integer> need = new HashMap<>();
            //保存滑动窗口中 "要求字符"及对应个数
            Map<Character, Integer> window = new HashMap<>();


            for (int i = 0; i < t.length(); i++) {
                add(need, t.charAt(i), 1);
            }

            //滑动窗口[left,right)
            int left = 0, right = 0;
            //最优可行解的起始位置和长度
            int start = 0, len = s.length() + 1;
            //滑动窗口滑动过程中 "要求字符"个数满足要求 的"要求字符"个数
            int valid = 0;

            while (right < s.length()) {
                //右指针开始移动
                char c = s.charAt(right++);
                //找到一个 "要求字符"
                if (need.containsKey(c)) {
                    //窗口中"要求字符"个数+1
                    add(window, c, 1);
                    //如果窗口中"要求字符"个数达到要求,则valid++
                    if (window.get(c).equals(need.get(c))) {
                        valid++;
                    }
                }
                //找到一个可行解
                while (valid == need.size()) {
                    //比较可行解和当前最优可行解
                    if (right - left < len) {
                        start = left;
                        len = right - left;
                    }
                    //左指针开始移动
                    char d = s.charAt(left++);
                    //即将被剔除的字符为"要求字符"
                    if (need.containsKey(d)) {
                        //如果剔除前，窗口中"要求字符"个数刚好达到要求，则valid--
                        if (window.get(d).equals(need.get(d))) {
                            valid--;
                        }
                        //窗口中"要求字符"个数-1
                        add(window, d, -1);
                    }
                }
            }

            return len == s.length() + 1 ? "" : s.substring(start, start + len);
        }

        private void add(Map<Character, Integer> map, char c, int amount) {
            map.put(c, map.getOrDefault(c, 0) + amount);
        }

    }
//leetcode submit region end(Prohibit modification and deletion)

}