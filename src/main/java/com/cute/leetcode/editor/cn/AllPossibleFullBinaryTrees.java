//给你一个整数 n ，请你找出所有可能含 n 个节点的 真二叉树 ，并以列表形式返回。答案中每棵树的每个节点都必须符合 Node.val == 0 。 
//
// 答案的每个元素都是一棵真二叉树的根节点。你可以按 任意顺序 返回最终的真二叉树列表。 
//
// 真二叉树 是一类二叉树，树中每个节点恰好有 0 或 2 个子节点。 
//
// 
//
// 示例 1： 
// 
// 
//输入：n = 7
//输出：[[0,0,0,null,null,0,0,null,null,0,0],[0,0,0,null,null,0,0,0,0],[0,0,0,0,0,0
//,0],[0,0,0,0,0,null,null,null,null,0,0],[0,0,0,0,0,null,null,0,0]]
// 
//
// 示例 2： 
//
// 
//输入：n = 3
//输出：[[0,0,0]]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= n <= 20 
// 
//
// Related Topics 树 递归 记忆化搜索 动态规划 二叉树 👍 384 👎 0

package com.cute.leetcode.editor.cn;

import com.cute.leetcode.editor.cn.model.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class AllPossibleFullBinaryTrees {
    public static void main(String[] args) {
        Solution solution = new AllPossibleFullBinaryTrees().new Solution();
        System.out.println(solution.allPossibleFBT(7));
    }
    //leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {

    List<TreeNode> ans = new ArrayList<>();
    TreeNode root = new TreeNode(0);
    public List<TreeNode> allPossibleFBT(int n) {
        dfs(root,n-1);
        return ans;
    }
    private void dfs(TreeNode treeNode,int n) {
        if(n == 0) {
            ans.add(copyTree(root));
            return;
        }
        if(n >= 2) {
            treeNode.left = new TreeNode(0);
            treeNode.right = new TreeNode(0);
            dfs(treeNode.left,n-2);
            dfs(treeNode.right,n-2);
            treeNode.left = null;
            treeNode.right = null;
        }
    }

    private TreeNode copyTree(TreeNode root) {
        TreeNode copyRoot = new TreeNode(0);
        if(root.left != null) {
            copyRoot.left = copyTree(root.left);
        }
        if(root.right != null) {
            copyRoot.right = copyTree(root.right);
        }
        return copyRoot;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}