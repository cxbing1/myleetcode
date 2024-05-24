//输入一个字符串，打印出该字符串中字符的所有排列。 
//
// 
//
// 你可以以任意顺序返回这个字符串数组，但里面不能有重复元素。 
//
// 
//
// 示例: 
//
// 输入：s = "abc"
//输出：["abc","acb","bac","bca","cab","cba"]
// 
//
// 
//
// 限制： 
//
// 1 <= s 的长度 <= 8 
// Related Topics 回溯算法 
// 👍 345 👎 0

package com.cute.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ZiFuChuanDePaiLieLcof {
    public static void main(String[] args) {
        Solution solution = new ZiFuChuanDePaiLieLcof().new Solution();
        System.out.println(Arrays.toString(solution.permutation("abc")));
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
        private boolean[] visited;
        private List<String> resList = new ArrayList<>();
    public String[] permutation(String s) {
        visited = new boolean[s.length()];
        char[] cs = s.toCharArray();
        Arrays.sort(cs);
        back_trace(0,"",cs);
        String[] ss = new String[resList.size()];
        int i = 0;
        for(String str:resList) {
            ss[i++] = str;
        }
        return ss;
    }

    private void back_trace(int k,String res,char[] cs) {
        if(k==cs.length) {
            resList.add(res);
            return;
        }

        for(int i = 0;i<cs.length;i++) {
            if(i>0 && cs[i] == cs[i-1] &&!visited[i-1]) {
                continue;
            }
            if(visited[i]) {
                continue;
            }
            visited[i] = true;
            back_trace(k+1,res+cs[i],cs);
            visited[i] = false;
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}