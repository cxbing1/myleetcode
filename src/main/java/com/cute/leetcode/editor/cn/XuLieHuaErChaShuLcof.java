//请实现两个函数，分别用来序列化和反序列化二叉树。 
//
// 你需要设计一个算法来实现二叉树的序列化与反序列化。这里不限定你的序列 / 反序列化算法执行逻辑，你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字
//符串反序列化为原始的树结构。 
//
// 提示：输入输出格式与 LeetCode 目前使用的方式一致，详情请参阅 LeetCode 序列化二叉树的格式。你并非必须采取这种方式，你也可以采用其他的方
//法解决这个问题。 
//
// 
//
// 示例： 
//
// 
//输入：root = [1,2,3,null,null,4,5]
//输出：[1,2,3,null,null,4,5]
// 
//
// 
//
// 注意：本题与主站 297 题相同：https://leetcode-cn.com/problems/serialize-and-deserialize-b
//inary-tree/ 
// Related Topics 树 深度优先搜索 广度优先搜索 设计 字符串 二叉树 
// 👍 222 👎 0

package com.cute.leetcode.editor.cn;

import com.cute.leetcode.editor.cn.model.TreeNode;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class XuLieHuaErChaShuLcof {
    public static void main(String[] args) {
        Codec solution = new XuLieHuaErChaShuLcof().new Codec();
        String  s= "[1, 2, 3, null, null, 4, 5]";
        TreeNode root = solution.deserialize(s);
        System.out.println(solution.serialize(root));
    }
    //leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Codec {

    public String serialize(TreeNode root) {
        List<Integer> list =new ArrayList<>();
        if(root == null) {
            return list.toString();
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if(node != null) {
                list.add(node.val);
                queue.offer(node.left);
                queue.offer(node.right);
            } else {
                list.add(null);
            }
        }
        return list.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if("[]".equals(data)) {
            return null;
        }
        String[] ss = data.substring(1,data.length()-1).split(", ");
        List<Integer> list = new ArrayList<>();
        for(String s :ss) {
           list.add("null" .equals(s)? null : Integer.valueOf(s));
        }
        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode root = new TreeNode(list.get(0));
        queue.offer(root);

        for(int i = 1;i<list.size();i+=2) {
            TreeNode node = queue.poll();
            if(list.get(i) != null) {
                node.left = new TreeNode(list.get(i));
                queue.offer(node.left);
            }

            if(list.get(i+1) != null) {
                node.right = new TreeNode(list.get(i+1));
                queue.offer(node.right);
            }
        }

        return root;
    }

}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));
//leetcode submit region end(Prohibit modification and deletion)

}