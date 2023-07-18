package com.dpw.lyl.join.good.job.algorithm.offer;

/**
 * @Author: dengpw
 * @createTime: 2023年06月27日 09:16:22
 * @version: 1.0.0
 * 给定循环单调非递减列表中的一个点，写一个函数向这个列表中插入一个新元素insertVal ，使这个列表仍然是循环升序的。
 * 给定的可以是这个列表中任意一个顶点的指针，并不一定是这个列表中最小元素的指针。
 * 如果有多个满足条件的插入位置，可以选择任意一个位置插入新的值，插入后整个列表仍然保持有序。
 * 如果列表为空（给定的节点是 null），需要创建一个循环有序列表并返回这个节点。否则。请返回原先给定的节点。
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/4ueAj6
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * 提示：
 * 0 <= Number of Nodes <= 5 * 10^4
 * -10^6 <= Node.val <= 10^6
 * -10^6 <=insertVal <= 10^6
 * @Description: 剑指 Offer II 029. 排序的循环链表（中等）
 */
public class Solution29 {


    static class Node {
        public int val;
        public Node next;

        public Node() {
        }

        public Node(int val) {
            this.val = val;
        }

        public Node(int val, Node next) {
            this.val = val;
            this.next = next;
        }

    }


    public static void main(String[] args) {
        Node node = new Node();


        new Solution29().insert(node, 1);

        System.out.println(node);
    }

    public Node insert(Node head, int insertVal) {
        Node node = new Node(insertVal);
        if (null == head) {
            node.next = node;
            return node;
        }

        if (head.next == head) {
            head.next = node;
            node.next = head;
            return head;
        }
        Node currentNode = head;
        Node next = currentNode.next;
        while (next != head) {
            if (currentNode.val <= insertVal && next.val >= insertVal) {
                break;
            }
            if (currentNode.val > next.val) {
                if (insertVal > currentNode.val || insertVal < next.val) {
                    break;
                }
            }
            currentNode = currentNode.next;
            next = next.next;
        }
        currentNode.next = node;
        node.next = next;
        return head;

    }


}
