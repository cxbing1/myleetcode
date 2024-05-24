//给定一个化学式formula（作为字符串），返回每种原子的数量。 
//
// 原子总是以一个大写字母开始，接着跟随0个或任意个小写字母，表示原子的名字。 
//
// 如果数量大于 1，原子后会跟着数字表示原子的数量。如果数量等于 1 则不会跟数字。例如，H2O 和 H2O2 是可行的，但 H1O2 这个表达是不可行的。
// 
//
// 两个化学式连在一起是新的化学式。例如 H2O2He3Mg4 也是化学式。 
//
// 一个括号中的化学式和数字（可选择性添加）也是化学式。例如 (H2O2) 和 (H2O2)3 是化学式。 
//
// 给定一个化学式 formula ，返回所有原子的数量。格式为：第一个（按字典序）原子的名字，跟着它的数量（如果数量大于 1），然后是第二个原子的名字（按字
//典序），跟着它的数量（如果数量大于 1），以此类推。 
//
// 
//
// 示例 1： 
//
// 
//输入：formula = "H2O"
//输出："H2O"
//解释：
//原子的数量是 {'H': 2, 'O': 1}。
// 
//
// 示例 2： 
//
// 
//输入：formula = "Mg(OH)2"
//输出："H2MgO2"
//解释： 
//原子的数量是 {'H': 2, 'Mg': 1, 'O': 2}。
// 
//
// 示例 3： 
//
// 
//输入：formula = "K4(ON(SO3)2)2"
//输出："K4N2O14S4"
//解释：
//原子的数量是 {'K': 4, 'N': 2, 'O': 14, 'S': 4}。
// 
//
// 示例 4： 
//
// 
//输入：formula = "Be32"
//输出："Be32"
// 
//
// 
//
// 提示： 
//
// 
// 1 <= formula.length <= 1000 
// formula 由小写英文字母、数字 '(' 和 ')' 组成。 
// formula 是有效的化学式。 
// 
// Related Topics 栈 哈希表 字符串 
// 👍 181 👎 0

package com.cute.leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.TreeMap;

public class NumberOfAtoms {
    public static void main(String[] args) {
        Solution solution = new NumberOfAtoms().new Solution();
        System.out.println(solution.countOfAtoms("K4(ON(SO3)2)2"));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public String countOfAtoms(String formula) {
            Stack<Map<String, Integer>> stack = new Stack<>();
            stack.push(new HashMap<>());
            char[] cs = formula.toCharArray();
            int i = 0;
            int n = cs.length;
            while (i < n) {
                char c = cs[i];
                if (c == '(') {
                    stack.push(new HashMap<>());
                    i++;
                } else if (c == ')') {
                    int count = 0;
                    i++;
                    while (i < n && isDigit(cs[i])) {
                        count = count * 10 + cs[i] - '0';
                        i++;
                    }

                    Map<String, Integer> popMap = stack.pop();
                    Map<String, Integer> topMap = stack.peek();
                    int finalCount = count == 0 ? 1 : count;
                    popMap.forEach((k, v) -> {
                        topMap.put(k, topMap.getOrDefault(k, 0) + finalCount * v);
                    });

                } else {
                    StringBuilder sb = new StringBuilder();
                    sb.append(c);
                    int count = 0;
                    i++;
                    while (i < n && cs[i] >= 'a' && cs[i] <= 'z') {
                        sb.append(cs[i]);
                        i++;
                    }
                    while (i < n && isDigit(cs[i])) {
                        count = count * 10 + cs[i] - '0';
                        i++;
                    }
                    Map<String, Integer> topMap = stack.peek();
                    String s = sb.toString();
                    topMap.put(s, topMap.getOrDefault(s, 0) + (count > 0 ? count : 1));
                }
            }

            Map<String, Integer> resMap = new TreeMap<>();
            while (!stack.isEmpty()) {
                Map<String, Integer> map = stack.pop();
                map.forEach((k, v) -> {
                    resMap.put(k, resMap.getOrDefault(k, 0) + v);
                });
            }
            StringBuilder sb = new StringBuilder();
            resMap.forEach((k, v) -> {
                sb.append(k);
                if (v > 1) {
                    sb.append(v);
                }
            });
            return sb.toString();

        }

        private boolean isDigit(char c) {
            return c >= '0' && c <= '9';
        }


    }
//leetcode submit region end(Prohibit modification and deletion)

}