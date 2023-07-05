package com.dpw.lyl.join.good.job.algorithm.offer;

import java.util.LinkedList;

/**
 * @Author: dengpw
 * @createTime: 2023年06月27日 09:16:22
 * @version: 1.0.0
 * 给定一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
 * 提示：使用0或其他任意节点->目标节点
 * 链表中结点的数目为 sz
 * 1 <= sz <= 30
 * 0 <= Node.val <= 100
 * 1 <= n <= sz
 * @Description: 剑指 Offer II 021. 删除链表的倒数第 n 个结点 （中等）
 */
public class Solution21 {


    static class ListNode {

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode listNode) {
            this.val = val;
            this.next = listNode;
        }

        int val;
        ListNode next;
    }


    public static void main(String[] args) {
        ListNode listNode = new ListNode();
        ListNode next = listNode;
        int index = 1;
        while (next.next == null && index <= 5) {
            next.next = new ListNode(index);
            next = next.next;
            index++;
        }
        System.out.println(new Solution21().removeNthFromEnd(listNode, 2));
    }

    /**
     * @author: dengpw
     * @createTime: 2023年07月04 15:54:17
     * @description: 遍历链表找到需要删除的节点
     * @param: head - [ListNode]
     * @param: n - [int]
     * @return: com.dpw.lyl.join.good.job.algorithm.offer.Solution21.ListNode
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        //默认0->下一个节点
        ListNode listNode = new ListNode(0, head);

        int length = 0;
        ListNode temp = head;
        while (temp != null) {
            length++;
            temp = temp.next;
        }
        //找到需要移除的节点
        ListNode remove = listNode;
        for (int i = 0; i < length - n ; i++) {
            remove = remove.next;
        }
        //跳过节点->下一个节点
        remove.next = remove.next.next;

        return listNode.next;
    }


}
