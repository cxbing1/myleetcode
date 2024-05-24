//给定一个二叉树： 
//
// 
//struct Node {
//  int val;
//  Node *left;
//  Node *right;
//  Node *next;
//} 
//
// 填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 NULL 。 
//
// 初始状态下，所有 next 指针都被设置为 NULL 。 
//
// 
//
// 示例 1： 
// 
// 
//输入：root = [1,2,3,4,5,null,7]
//输出：[1,#,2,3,#,4,5,7,#]
//解释：给定二叉树如图 A 所示，你的函数应该填充它的每个 next 指针，以指向其下一个右侧节点，如图 B 所示。序列化输出按层序遍历顺序（由 next 指
//针连接），'#' 表示每层的末尾。 
//
// 示例 2： 
//
// 
//输入：root = []
//输出：[]
// 
//
// 
//
// 提示： 
//
// 
// 树中的节点数在范围 [0, 6000] 内 
// -100 <= Node.val <= 100 
// 
//
// 进阶： 
//
// 
// 你只能使用常量级额外空间。 
// 使用递归解题也符合要求，本题中递归程序的隐式栈空间不计入额外空间复杂度。 
// 
//
// 
// 
//
// Related Topics 树 深度优先搜索 广度优先搜索 链表 二叉树 👍 783 👎 0

package com.cute.leetcode.editor.cn;

import java.util.ArrayDeque;
import java.util.Deque;

public class PopulatingNextRightPointersInEachNodeIi {
    public static void main(String[] args) {
        Solution solution = new PopulatingNextRightPointersInEachNodeIi().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)

    //Definition for a Node.
    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }

    ;


    class Solution {
        public Node connect(Node root) {
            return connect3(root);
        }

        public Node connect3(Node root) {
            Node dummy = new Node();
            Node cur = root;
            while (cur != null) {
                Node pre = dummy;
                while (cur != null) {
                    if (cur.left != null) {
                        pre.next = cur.left;
                        pre = pre.next;
                    }
                    if (cur.right != null) {
                        pre.next = cur.right;
                        pre = pre.next;
                    }
                    cur = cur.next;
                }
                cur = dummy.next;
                dummy.next = null;
            }
            return root;
        }

        public Node connect2(Node root) {
            if (root == null) {
                return null;
            }
            Deque<Node> deque = new ArrayDeque<>();
            deque.add(root);

            while (!deque.isEmpty()) {
                int levelCount = deque.size();
                Node pre = null;

                for (int i = 0; i < levelCount; i++) {
                    Node cur = deque.poll();
                    if (pre != null) {
                        pre.next = cur;
                    }
                    if (cur.left != null) {
                        deque.add(cur.left);
                    }
                    if (cur.right != null) {
                        deque.add(cur.right);
                    }
                    pre = cur;
                }
            }
            return root;
        }

        public Node connect1(Node root) {
            if (root == null) {
                return root;
            }
            Node dummy = new Node(-1);
            Deque<Node> deque = new ArrayDeque<>();
            deque.add(root);
            Node pre = dummy;
            while (!deque.isEmpty()) {
                Node cur = deque.poll();
                if (cur != dummy) {
                    if (pre == dummy) {
                        deque.addLast(dummy);
                    } else {
                        pre.next = cur;
                    }
                    if (cur.left != null) {
                        deque.addLast(cur.left);
                    }
                    if (cur.right != null) {
                        deque.addLast(cur.right);
                    }
                }
                pre = cur;
            }
            return root;

        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}