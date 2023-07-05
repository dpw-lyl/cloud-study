package com.dpw.lyl.join.good.job.algorithm.offer;

/**
 * @Author: dengpw
 * @createTime: 2023年06月27日 09:16:22
 * @version: 1.0.0
 * 给定两个 非空链表 l1和 l2来代表两个非负整数。数字最高位位于链表开始位置。
 * 它们的每个节点只存储一位数字。将这两数相加会返回一个新的链表。
 * 可以假设除了数字 0 之外，这两个数字都不会以零开头。
 * 来源：力扣（LeetCode）
 * 链接：<a href="https://leetcode.cn/problems/3u1WK4">...</a>
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * 提示：
 * 链表的长度范围为 [1, 100]
 * 0 <= node.val <= 9
 * 输入数据保证链表代表的数字无前导 0
 * @Description: 剑指 Offer II 025. 链表中的两数相加（中等）
 */
public class Solution25 {


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

        ListNode listNode3 = new ListNode(6);
        int index3 = 1;
        ListNode next3 = listNode3;
       /* while (next3.next == null && index3 <= 5) {
            next3.next = new ListNode(index3);
            next3 = next3.next;
            index3++;
        }*/
        ListNode l2 = new ListNode(4);
       /* index3 = 0;
        ListNode temp = l2;
        while (temp.next == null && index3 <= 5) {
            temp.next = new ListNode(index3);
            temp = temp.next;
            index3++;
        }*/
        System.out.println(new Solution25().addTwoNumbers(listNode3, l2));
    }


    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        l1 = reverseListNode(l1);
        l2 = reverseListNode(l2);
        ListNode res = null;
        int index = 0;
        int sum;
        while (l1 != null || l2 != null || index > 0) {
            sum = (null == l1 ? 0 : l1.val) + (null == l2 ? 0 : l2.val)+index;
            index = sum / 10;
            ListNode listNode = new ListNode(sum % 10);
            listNode.next = res;
            res = listNode;
            l1 = (null == l1 ? null : l1.next);
            l2 = (null == l2 ? null : l2.next);
        }
        return res;
    }

    private ListNode reverseListNode(ListNode node) {
        ListNode prev = null;
        ListNode current = node;
        while (current != null) {
            ListNode next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        return prev;
    }


    /**
     * @author: dengpw
     * @createTime: 2023年07月05 09:12:59
     * @description: 获取链表长度
     * @param: listNode - [ListNode]
     * @return: int
     */
    private int getListNodeSize(ListNode listNode) {
        int size = 0;
        while (listNode != null) {
            size++;
            listNode = listNode.next;
        }
        return size;
    }


}
