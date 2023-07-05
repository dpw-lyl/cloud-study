package com.dpw.lyl.join.good.job.algorithm.offer;

/**
 * @Author: dengpw
 * @createTime: 2023年06月27日 09:16:22
 * @version: 1.0.0
 * 给定单链表的头节点 head ，请反转链表，并返回反转后的链表的头节点。
 * 来源：力扣（LeetCode）
 * 链接：<a href="https://leetcode.cn/problems/3u1WK4">...</a>
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * 提示：
 * 链表中节点的数目范围是 [0, 5000]
 * -5000 <= Node.val <= 5000
 * @Description: 剑指 Offer II 024. 反转链表（简单）
 */
public class Solution24 {


    static class ListNode {

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
            this.next = null;
        }

        ListNode(int val, ListNode listNode) {
            this.val = val;
            this.next = listNode;
        }

        int val;
        ListNode next;
    }


    public static void main(String[] args) {

        ListNode listNode3 = new ListNode();
        int index3 = 1;
        ListNode next3 = listNode3;
        while (next3.next == null && index3 <= 5) {
            next3.next = new ListNode(index3);
            next3 = next3.next;
            index3++;
        }
        System.out.println(new Solution24().reverseList(listNode3));
    }


    /**
     * @author: dengpw
     * @createTime: 2023年07月04 18:14:54
     * @description: 1->2->3->4->5  ---->  5->4->3->2->1
     * @param: head - [ListNode]
     * @return: com.dpw.lyl.join.good.job.algorithm.offer.Solution24.ListNode
     */
    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode current = head;
        while (null != current) {
            ListNode next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }

        return prev;
    }


}
