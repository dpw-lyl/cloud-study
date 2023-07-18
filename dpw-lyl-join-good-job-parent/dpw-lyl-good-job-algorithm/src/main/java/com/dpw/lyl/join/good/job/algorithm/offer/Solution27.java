package com.dpw.lyl.join.good.job.algorithm.offer;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: dengpw
 * @createTime: 2023年06月27日 09:16:22
 * @version: 1.0.0
 * 给定一个链表的 头节点 head ，请判断其是否为回文链表。
 * 如果一个链表是回文，那么链表节点序列从前往后看和从后往前看是相同的。
 * 提示：
 * 链表 L 的长度范围为 [1, 105]
 * 0 <= node.val <= 9
 * @Description: 剑指 Offer II 027. 回文链表（简单）
 */
public class Solution27 {


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
        index3 = 0;
        ListNode temp = l2;
        while (temp.next == null && index3 <= 5) {
            temp.next = new ListNode(index3);
            temp = temp.next;
            index3++;
        }
        new Solution27().isPalindrome(l2);

        System.out.println(l2);
    }


    /**
     * @author: dengpw
     * @createTime: 2023年07月13 15:44:29
     * @description: 时间复杂O(n),空间复杂O(n)
     * @param: head - [ListNode]
     * @return: boolean
     */

    public boolean isPalindrome(ListNode head) {
        if (null == head) {
            return false;
        }
        ListNode temp = head;
        List<Integer> listNodes = new ArrayList<>();
        //集合存储，记录下标
        while (temp != null) {
            listNodes.add(temp.val);
            temp = temp.next;
        }
        int left = 0, right = listNodes.size() - 1;
        while (left<right){
            if(listNodes.get(left).intValue() != listNodes.get(right).intValue()){
                return false;
            }
            left++;
            right--;

        }
        return true;
    }

    /**
     * @author: dengpw
     * @createTime: 2023年07月13 15:44:25
     * @description: 时间复杂O(n),空间复杂O(1)
     * @param: head - [ListNode]
     * @return: boolean
     */
    public boolean isPalindrome1(ListNode head) {
        if(null == head){
            return false;
        }
        ListNode midNode= getMidNode(head);
        ListNode reversNode=getReverseNode(midNode);
        ListNode node1=head;
        ListNode node2=reversNode;
        boolean res=true;
        while (res && node1 != null){
            if(node1.val != node2.val){
                res=false;
            }
            node1=node1.next;
            node2=node2.next;
        }
        midNode.next=getReverseNode(reversNode);
        return res;
    }

    private ListNode getMidNode(ListNode reversNode) {
        ListNode slow=reversNode;
        ListNode fast=reversNode;
        while (null != fast.next && null != fast.next.next){
            fast=fast.next.next;
            slow=slow.next;
        }
        return slow;
    }

    private ListNode getReverseNode(ListNode head) {
        ListNode prev = null;
        ListNode nodeTemp=head;
        while (null != nodeTemp){
            ListNode next = nodeTemp.next;
            nodeTemp.next=prev;
            prev=nodeTemp;
            nodeTemp=next;
        }
        return prev;
    }


}
