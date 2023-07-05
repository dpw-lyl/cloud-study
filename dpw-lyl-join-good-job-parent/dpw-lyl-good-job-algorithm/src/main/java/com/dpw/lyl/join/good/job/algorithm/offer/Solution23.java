package com.dpw.lyl.join.good.job.algorithm.offer;

import lombok.EqualsAndHashCode;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author: dengpw
 * @createTime: 2023年06月27日 09:16:22
 * @version: 1.0.0
 * 给定两个单链表的头节点headA 和 headB ，请找出并返回两个单链表相交的起始节点。如果两个链表没有交点，返回 null 。
 * 注意，函数返回结果后，链表必须 保持其原始结构
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/3u1WK4
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * 提示：
 * listA 中节点数目为 m
 * listB 中节点数目为 n
 * 0 <= m, n <= 3 * 104
 * 1 <= Node.val <= 105
 * 0 <= skipA <= m
 * 0 <= skipB <= n
 * 如果 listA 和 listB 没有交点，intersectVal 为 0
 * 如果 listA 和 listB 有交点，intersectVal == listA[skipA + 1] == listB[skipB + 1]
 * @Description: 剑指 Offer II 023. 两个链表的第一个重合节点（简单）
 */
public class Solution23 {


    static class ListNode {

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
            this.next = null;
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

        ListNode listNode = new ListNode();
        ListNode next = listNode;
        int index = 1;
        while (next.next == null && index <= 6) {
            next.next = new ListNode(index);
            next = next.next;
            index++;
        }
        ListNode temp=listNode;
        while (temp.next != null) {
            temp = temp.next;
            if(temp.next == null){
                temp.next=listNode3;
                break;
            }
        }

        ListNode listNode2 = new ListNode();
        int index2 = 1;
        ListNode next2 = listNode2;
        while (next2.next == null && index2 <= 10) {
            next2.next = new ListNode(index2);
            next2 = next2.next;
            index2++;
        }
         temp=listNode2;
        while (temp.next != null) {
            temp = temp.next;
            if(temp.next == null){
                temp.next=listNode3;
                break;
            }
        }

        System.out.println(new Solution23().getIntersectionNode(listNode, listNode2));
    }


    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (null == headA || headB == null) {
            return null;
        }
        ListNode listNode1 = headA;
        ListNode listNode2 = headB;
        while (listNode1 != listNode2) {
            listNode1 = null == listNode1 ? headB : listNode1.next;
            listNode2 = null == listNode2 ? headA : listNode2.next;
        }
        return listNode1;
    }


}
