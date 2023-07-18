package com.dpw.lyl.join.good.job.algorithm.offer;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: dengpw
 * @createTime: 2023年06月27日 09:16:22
 * @version: 1.0.0
 * 多级双向链表中，除了指向下一个节点和前一个节点指针之外，它还有一个子链表指针，可能指向单独的双向链表。
 * 这些子列表也可能会有一个或多个自己的子项，依此类推，生成多级数据结构，如下面的示例所示。
 * 给定位于列表第一级的头节点，请扁平化列表，即将这样的多级双向链表展平成普通的双向链表，使所有结点出现在单级双链表中
 * 来源：力扣（LeetCode）
 * 链接：<a href="https://leetcode.cn/problems/Qv1Da2">...</a>
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * 提示：
 * 节点数目不超过 1000
 * 1 <= Node.val <= 10^5
 * @Description: 剑指 Offer II 028. 展平多级双向链表（中等）
 */
public class Solution28 {


    static class Node {
        public int val;
        public Node prev;
        public Node next;
        public Node child;

        Node() {
        }

    }


    public static void main(String[] args) {
        Node node = new Node();


        new Solution28().flatten(node);

        System.out.println(node);
    }

    public Node flatten(Node head) {

        if (null == head) {
            return null;
        }
        dfs(head);

        return head;

    }

    private Node dfs(Node node) {
        Node lastNode = null;
        Node currentNode = node;
        while (null != currentNode) {
            Node next = currentNode.next;
            if (null != currentNode.child) {
                Node dfs = dfs(currentNode.child);
                next = currentNode.next;
                currentNode.next = currentNode.child;
                dfs.child.prev = currentNode;
                if (null != next) {
                    dfs.next = next;
                    next.prev = dfs;
                }
                currentNode.child = null;
                lastNode = dfs;
            } else {
                lastNode = currentNode;
            }
            currentNode = next;
        }

        return lastNode;
    }


}
