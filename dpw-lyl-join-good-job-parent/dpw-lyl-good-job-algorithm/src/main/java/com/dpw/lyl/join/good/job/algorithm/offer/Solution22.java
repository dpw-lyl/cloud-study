package com.dpw.lyl.join.good.job.algorithm.offer;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author: dengpw
 * @createTime: 2023年06月27日 09:16:22
 * @version: 1.0.0
 * 给定一个链表，返回链表开始入环的第一个节点。
 * 从链表的头节点开始沿着 next 指针进入环的第一个节点为环的入口节点。
 * 如果链表无环，则返回null。
 * 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。
 * 注意，pos 仅仅是用于标识环的情况，并不会作为参数传递到函数中。
 * 说明：不允许修改给定的链表。
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/c32eOV
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * 提示：
 * 链表中节点的数目范围在范围 [0, 104] 内
 * -105 <= Node.val <= 105
 * pos 的值为 -1 或者链表中的一个有效索引
 * @Description: 剑指 Offer II 022. 链表中环的入口节点（中等）
 */
public class Solution22 {


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
        ListNode listNode = new ListNode();
        ListNode next = listNode;
        int index = 1;
        while (next.next == null && index <= 5) {
            next.next = new ListNode(index);
            next = next.next;
            index++;
        }
        System.out.println(new Solution22().detectCycle(listNode));
    }


    public ListNode detectCycle(ListNode head) {
       Set<ListNode> data = new HashSet<>();
        ListNode temp = head;
        while (temp != null) {
            if (data.contains(temp)){
                return temp;
            }else {
                data.add(temp);
                temp=temp.next;
            }
        }
        return null;
    }


}
