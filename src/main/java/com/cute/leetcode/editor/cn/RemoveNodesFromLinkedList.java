//给你一个链表的头节点 head 。 
//
// 移除每个右侧有一个更大数值的节点。 
//
// 返回修改后链表的头节点 head 。 
//
// 
//
// 示例 1： 
//
// 
//
// 
//输入：head = [5,2,13,3,8]
//输出：[13,8]
//解释：需要移除的节点是 5 ，2 和 3 。
//- 节点 13 在节点 5 右侧。
//- 节点 13 在节点 2 右侧。
//- 节点 8 在节点 3 右侧。
// 
//
// 示例 2： 
//
// 
//输入：head = [1,1,1,1]
//输出：[1,1,1,1]
//解释：每个节点的值都是 1 ，所以没有需要移除的节点。
// 
//
// 
//
// 提示： 
//
// 
// 给定列表中的节点数目在范围 [1, 10⁵] 内 
// 1 <= Node.val <= 10⁵ 
// 
//
// Related Topics 栈 递归 链表 单调栈 👍 82 👎 0

package com.cute.leetcode.editor.cn;

import com.cute.leetcode.editor.cn.model.ListNode;

import java.util.List;
import java.util.Stack;

public class RemoveNodesFromLinkedList {
    public static void main(String[] args) {
        Solution solution = new RemoveNodesFromLinkedList().new Solution();
        int[] values = {5,2,13,3,8};
        System.out.println(solution.removeNodes(buildList(values)));
    }

    private static ListNode buildList(int[] values) {
        ListNode head = new ListNode(-1);
        ListNode p = head;
        for(int value : values) {
            p.next = new ListNode(value);
            p = p.next;
        }
        return head.next;
    }
    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * Definition for singly-linked list.
     * public class ListNode {
     * int val;
     * ListNode next;
     * ListNode() {}
     * ListNode(int val) { this.val = val; }
     * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     * }
     */
    class Solution {
        public ListNode removeNodes(ListNode head) {
            if (head == null || head.next == null) {
                return head;
            }
            Stack<ListNode> stack = new Stack<>();
            ListNode p = head;
            while(p != null) {
                while(!stack.empty() && p.val > stack.peek().val) {
                    stack.pop();
                }
                stack.push(p);
                p = p.next;
            }
            ListNode q = null;
            while(!stack.empty()) {
                ListNode node = stack.pop();
                node.next = q;
                q = node;
            }
            return q;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}