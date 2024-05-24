//给你一个 不含重复 单词的字符串数组 words ，请你找出并返回 words 中的所有 连接词 。 
//
// 连接词 定义为：一个完全由给定数组中的至少两个较短单词组成的字符串。 
//
// 
//
// 示例 1： 
//
// 
//输入：words = ["cat","cats","catsdogcats","dog","dogcatsdog","hippopotamuses",
//"rat","ratcatdogcat"]
//输出：["catsdogcats","dogcatsdog","ratcatdogcat"]
//解释："catsdogcats" 由 "cats", "dog" 和 "cats" 组成; 
//     "dogcatsdog" 由 "dog", "cats" 和 "dog" 组成; 
//     "ratcatdogcat" 由 "rat", "cat", "dog" 和 "cat" 组成。
// 
//
// 示例 2： 
//
// 
//输入：words = ["cat","dog","catdog"]
//输出：["catdog"] 
//
// 
//
// 提示： 
//
// 
// 1 <= words.length <= 10⁴ 
// 0 <= words[i].length <= 1000 
// words[i] 仅由小写字母组成 
// 0 <= sum(words[i].length) <= 10⁵ 
// 
// Related Topics 深度优先搜索 字典树 数组 字符串 动态规划 👍 223 👎 0

package com.cute.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class ConcatenatedWords {
    public static void main(String[] args) {
        Solution solution = new ConcatenatedWords().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        private TrieNode root = new TrieNode();
        public List<String> findAllConcatenatedWordsInADict(String[] words) {
            Arrays.sort(words, Comparator.comparingInt(String::length));
            List<String> res = new ArrayList<>();

            for(String word : words) {
                if(word.isEmpty()) {
                    continue;
                }
                if(dfs(word,0)) {
                    res.add(word);
                } else {
                    insert(word);
                }
            }
            return res;
        }

        private boolean dfs(String word,int i) {
            if(i==word.length()) {
                return true;
            }
            TrieNode node = root;
            for(;i<word.length();i++) {
                char c = word.charAt(i);
                int index = getIndex(c);
                if(node.nexts[index] == null) {
                    return false;
                }
                node = node.nexts[index];
                if(node.end && dfs(word,i+1)) {
                    return true;
                }
            }
            return false;
        }

        public void insert(String word) {
            if(word.isEmpty()) {
                return;
            }
            TrieNode node = root;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                int index = getIndex(c);
                if (node.nexts[index] == null) {
                    node.nexts[index] = new TrieNode();
                }
                node = node.nexts[index];
            }
            node.end = true;
        }

        private int getIndex(char c) {
            return c - 'a';
        }

    }

    class TrieNode {
        boolean end;
        TrieNode[] nexts = new TrieNode[26];
    }


//leetcode submit region end(Prohibit modification and deletion)

}