//给定一个字符串，请将字符串里的字符按照出现的频率降序排列。 
//
// 示例 1: 
//
// 
//输入:
//"tree"
//
//输出:
//"eert"
//
//解释:
//'e'出现两次，'r'和't'都只出现一次。
//因此'e'必须出现在'r'和't'之前。此外，"eetr"也是一个有效的答案。
// 
//
// 示例 2: 
//
// 
//输入:
//"cccaaa"
//
//输出:
//"cccaaa"
//
//解释:
//'c'和'a'都出现三次。此外，"aaaccc"也是有效的答案。
//注意"cacaca"是不正确的，因为相同的字母必须放在一起。
// 
//
// 示例 3: 
//
// 
//输入:
//"Aabb"
//
//输出:
//"bbAa"
//
//解释:
//此外，"bbaA"也是一个有效的答案，但"Aabb"是不正确的。
//注意'A'和'a'被认为是两种不同的字符。
// 
// Related Topics 哈希表 字符串 桶排序 计数 排序 堆（优先队列） 
// 👍 301 👎 0

package com.cute.leetcode.editor.cn;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class SortCharactersByFrequency {
    public static void main(String[] args) {
        Solution solution = new SortCharactersByFrequency().new Solution();
        solution.frequencySort("tree");
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public String frequencySort(String s) {

        Character[] cs = new Character[s.length()];
        Map<Character,Integer> countMap = new HashMap<>();
        ;
        for(int i = 0;i<s.length();i++) {
            char c = s.charAt(i);
            cs[i]  = c;
            countMap.put(c,countMap.getOrDefault(c,0)+1);
        }

        Arrays.sort(cs, (c1,c2)->{
            int count1 = countMap.get(c1);
            int count2 = countMap.get(c2);
            if(count1 == count2) {
                return Character.compare(c1,c2);
            }
            return Integer.compare(count2,count1);
        });
        StringBuilder res = new StringBuilder();
        for(Character c : cs) {
            res.append(c);
        }
        return res.toString();
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}