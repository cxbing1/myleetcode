//给出一个字符串 s（仅含有小写英文字母和括号）。 
//
// 请你按照从括号内到外的顺序，逐层反转每对匹配括号中的字符串，并返回最终的结果。 
//
// 注意，您的结果中 不应 包含任何括号。 
//
// 
//
// 示例 1： 
//
// 输入：s = "(abcd)"
//输出："dcba"
// 
//
// 示例 2： 
//
// 输入：s = "(u(love)i)"
//输出："iloveu"
// 
//
// 示例 3： 
//
// 输入：s = "(ed(et(oc))el)"
//输出："leetcode"
// 
//
// 示例 4： 
//
// 输入：s = "a(bcdefghijkl(mno)p)q"
//输出："apmnolkjihgfedcbq"
// 
//
// 
//
// 提示： 
//
// 
// 0 <= s.length <= 2000 
// s 中只有小写英文字母和括号 
// 我们确保所有括号都是成对出现的 
// 
// Related Topics 栈 
// 👍 148 👎 0

package com.cute.leetcode.editor.cn;

import java.util.Stack;

public class ReverseSubstringsBetweenEachPairOfParentheses {
    public static void main(String[] args) {
        Solution solution = new ReverseSubstringsBetweenEachPairOfParentheses().new Solution();
        System.out.println(solution.reverseParentheses("a(bcdefghijkl(mno)p)q"));
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public String reverseParentheses(String s) {
        Stack<Character> stack = new Stack<>();
        int leftCount = 0;
        int rightCount = 0;
        for(int i = 0;i<s.length();i++) {
            char c = s.charAt(i);
            if(c == ')') {
                StringBuilder sb = new StringBuilder();
                char pc;
                while((pc =stack.pop()) != '(') {
                    sb.append(pc);
                }
                for(int j = 0;j<sb.length();j++) {
                    stack.push(sb.charAt(j));
                }
            }  else{
                stack.push(c);
            }
        }

        StringBuilder sb = new StringBuilder();
        while(!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        return sb.reverse().toString();
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}