package com.dpw.lyl.join.good.job.algorithm.twosum;

import java.util.Arrays;

/**
 * @Author: dengpw
 * @createTime: 2023年04月18日 17:29:20
 * @version: 1.0.0
 * @Description: (两数之和 02)
 * 给出两个 **非空** 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 **逆序** 的方式存储的，并且它们的每个节点只能存储 **一位** 数字。
 * <p>
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 * <p>
 * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 */
public class TwoSums02 {

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            this.val = x;
        }
    }

    /**
     * @author: dengpw
     * @createTime: 2023年04月19 09:51:59
     * @description: 算法思路（定义10进位变量 定义返回链表与当前链表引用 以及判断取模获得当前val）
     * @param: listNode01 - [ListNode]
     * @param: listNode02 - [ListNode]
     * @return: com.dpw.lyl.join.good.job.algorithm.twosum.TwoSums02.ListNode
     */
    public ListNode addTwoListNodeToNewListNodeSum(ListNode listNode01, ListNode listNode02) {
        //定义一个0指向的链表
        ListNode sumedListNode = new ListNode(0);
        //0指向链表指向当前链表的引用
        ListNode current=sumedListNode;
        //定义十进制控制变量
        int i = 0;
        //遍历链表，退出条件为节点为空时
        while (null != listNode01 || null != listNode02 ){
            //定义两个链表对应每个节点的和
            int sum = i;
            if(null != listNode01){
                sum+= listNode01.val;
                //移动到链表下一个节点
                listNode01=listNode01.next;
            }
            if(null != listNode02){
             sum+= listNode02.val;
             //同上
             listNode02=listNode02.next;
            }
            //判断sum值是否发生十进制进1
            i = sum / 10;
            //节点值取模
            current.next=new ListNode(sum % 10);
        }
        //如果最后节点发生进位则多一个节点
        if(i > 0){
            current.next=new ListNode(i);
        }
        //因为第一个节点为0 ，所以返回下一个节点才是完整新节点
        return sumedListNode.next;
    }

    public static void main(String[] args) {

        TwoSums02 twoSums02 = new TwoSums02();
        ListNode listNode1 = new ListNode(9);
        ListNode listNode2 = new ListNode(1);
        ListNode listNode3=listNode1;
        ListNode listNode4=listNode2;

        for (int i = 0; i < 3; i++) {
            listNode3.next=new ListNode(i+1);
            listNode3=listNode3.next;
            listNode4.next=new ListNode(i);
            listNode4=listNode4.next;
        }
        ListNode listNode = twoSums02.addTwoListNodeToNewListNodeSum(listNode1, listNode2);
        while (null != listNode){
            System.out.print("->"+listNode.val);
            listNode=listNode.next;
        }
    }


}
