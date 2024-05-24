//根据一棵树的中序遍历与后序遍历构造二叉树。 
//
// 注意: 
//你可以假设树中没有重复的元素。 
//
// 例如，给出 
//
// 中序遍历 inorder = [9,3,15,20,7]
//后序遍历 postorder = [9,15,7,20,3] 
//
// 返回如下的二叉树： 
//
//     3
//   / \
//  9  20
//    /  \
//   15   7
// 
// Related Topics 树 深度优先搜索 数组 
// 👍 361 👎 0

package com.cute.leetcode.editor.cn;

import com.cute.leetcode.editor.cn.model.TreeNode;

import java.util.HashMap;
import java.util.Map;

public class ConstructBinaryTreeFromInorderAndPostorderTraversal {
    public static void main(String[] args) {
        Solution solution = new ConstructBinaryTreeFromInorderAndPostorderTraversal().new Solution();
        int[] inorder = {9, 3, 20, 17, 18, 15};
        int[] postorder = {9, 18, 17, 15, 20, 3};
        TreeNode root = solution.buildTree(inorder, postorder);
        System.out.println(root);
    }
    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     * int val;
     * TreeNode left;
     * TreeNode right;
     * TreeNode(int x) { val = x; }
     * }
     */
    class Solution {
        Map<Integer, Integer> map = new HashMap<>();

        public TreeNode buildTree(int[] inorder, int[] postorder) {
            for (int i = 0; i < inorder.length; i++) {
                map.put(inorder[i], i);
            }
            return buildTree(inorder, postorder, 0, inorder.length - 1, 0, postorder.length - 1);
            //return buildTree(inorder, postorder, 0, postorder.length - 1, postorder.length );
        }

        private TreeNode buildTree(int[] inorder, int[] postorder, int il, int ir, int pl, int pr) {
            if (il > ir || pl > pr) {
                return null;
            }
            int rootVal = postorder[pr];
            TreeNode root = new TreeNode(rootVal);
            /*int i = il;
            for (; i <= ir; i++) {
                if (inorder[i] == rootVal) {
                    break;
                }
            }*/
            int i = map.get(rootVal);
            root.left = buildTree(inorder, postorder, il, i - 1, pl, pl + i - il - 1);
            root.right = buildTree(inorder, postorder, i + 1, ir, pl + i - il, pr - 1);
            return root;

        }

        private TreeNode buildTree(int[] inorder, int[] postorder, int il, int pr, int len) {
            if (len == 0) {
                return null;
            }
            int rootVal = postorder[pr];
            TreeNode root = new TreeNode(rootVal);
            int leftLen = 0;
            for (; leftLen < len; leftLen++) {
                if (inorder[il + leftLen] == rootVal) {
                    break;
                }
            }

            int rightLen = len - leftLen - 1;
            root.left = buildTree(inorder, postorder, il, pr - rightLen - 1, leftLen);
            root.right = buildTree(inorder, postorder, il + leftLen + 1, pr - 1, rightLen);
            return root;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}