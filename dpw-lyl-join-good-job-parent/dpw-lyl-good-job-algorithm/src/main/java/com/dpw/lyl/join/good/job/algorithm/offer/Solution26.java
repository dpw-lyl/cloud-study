package com.dpw.lyl.join.good.job.algorithm.offer;

import java.util.*;

/**
 * @Author: dengpw
 * @createTime: 2023年06月27日 09:16:22
 * @version: 1.0.0
 * 给定一个单链表 L 的头节点 head ，单链表 L 表示为：
 * L0→ L1→ … → Ln-1→ Ln
 * 请将其重新排列后变为：
 * L0→Ln→L1→Ln-1→L2→Ln-2→ …
 * 不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 * 来源：力扣（LeetCode）
 * 链接：h<a href="ttps://leetcode.cn/problems/LGjMqU">...</a>
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * 提示：
 * 链表的长度范围为 [1, 5 * 104]
 * 1 <= node.val <= 1000
 * @Description: 剑指 Offer II 026. 重排链表（中等）
 */
public class Solution26 {


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
        new Solution26().reorderListCenter(l2);

        System.out.println(l2);
    }

    /**
     * @author: dengpw
     * @createTime: 2023年07月05 17:37:39
     * @description: 使用list下标，交换node,空间复杂O(n) 时间复杂O(n)
     * @param: head - [ListNode]
     * @return: void
     */

    public void reorderList(ListNode head) {
        if (null == head) {
            return;
        }
        ListNode temp = head;
        List<ListNode> listNodes = new ArrayList<>();
        //集合存储，记录下标
        while (temp != null) {
            listNodes.add(temp);
            temp = temp.next;
        }
        int left = 0, right = listNodes.size() - 1;
        while (left<right){
            listNodes.get(left).next = listNodes.get(right);
            left ++ ;
            if(left  ==  right){
                //左右两个指针相遇交换完成
                break;
            }

            listNodes.get(right).next=listNodes.get(left);
            right--;
        }
        listNodes.get(right).next=null;

    }

    /**
     * @author: dengpw
     * @createTime: 2023年07月05 17:36:38
     * @description: 空间复杂O(1) 时间复杂O(n)
     * @param: head - [ListNode]
     * @return: void
     */
    public void reorderListCenter(ListNode head) {
        if(null == head){
            return;
        }
        ListNode midNode = getMidNode(head);
        ListNode node1=head;
        ListNode node2=midNode.next;
        midNode.next=null;
        node2 = reverse(node2);
        mergeNodes(node1,node2);

    }


    public void mergeNodes(ListNode node1,ListNode node2) {
        ListNode temp1 = node1;
        ListNode temp2 = node2;
        while ( temp1 != null && temp2 != null){
           temp1=node1.next;
           node1.next=node2;
           node1=temp1;

           temp2=node2.next;
           node2.next=node1;
           node2=temp2;
        }


    }


    private ListNode getMidNode(ListNode head){
        ListNode slow=head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null){
            slow=slow.next;
            fast=fast.next.next;
        }
        return slow;

    }

    private ListNode reverse(ListNode node){
        ListNode prev=null;
        ListNode current=node;
        while (current != null){
            ListNode next = current.next;
            current.next=prev;
            prev=current;
            current=next;
        }
        return prev;
    }


}
