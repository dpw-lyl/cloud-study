package com.dpw.lyl.join.good.job.algorithm.offer;

import java.util.*;

/**
 * @Author: dengpw
 * @createTime: 2023年06月27日 09:16:22
 * @version: 1.0.0
 * 运用所掌握的数据结构，设计和实现一个 LRU (Least Recently Used，最近最少使用) 缓存机制 。
 * 实现 LRUCache 类：
 * LRUCache(int capacity) 以正整数作为容量capacity 初始化 LRU 缓存
 * int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。
 * void put(int key, int value)如果关键字已经存在，则变更其数据值；如果关键字不存在，则插入该组「关键字-值」。
 * 当缓存容量达到上限时，它应该在写入新数据之前删除最久未使用的数据值，从而为新的数据值留出空间。
 * 来源：力扣（LeetCode）
 * 链接：<a href="https://leetcode.cn/problems/OrIXps">...</a>
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * 提示：
 * 1 <= capacity <= 3000
 * 0 <= key <= 10000
 * 0 <= value <= 105
 * 最多调用 2 * 105 次 get 和 put
 * @Description: 剑指 Offer II 031. 最近最少使用缓存（中等）
 */
public class Solution31 {

    private Map<Integer,DLinkNode> cache;
    private int size;
    private int capacity;
    private DLinkNode head,tail;



    static class DLinkNode{

        DLinkNode next;

        DLinkNode prev;

        int key;
        int value;

        public DLinkNode(){}
        public DLinkNode(int key,int val){
            this.value=val;
            this.key=key;
        }
    }





    public static void main(String[] args) {


    }


    /** Initialize your data structure here. */
    public Solution31() {

    }

    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public Solution31(int capacity) {
        this.size=0;
        this.capacity=capacity;
        this.cache=new HashMap<>();
        this.head=new DLinkNode();
        this.tail=new DLinkNode();
        head.next=tail;
        tail.prev=head;
    }

    public int get(int key) {
        DLinkNode dLinkNode = cache.get(key);
        if(null == dLinkNode){
            return -1;
        }
        // TODO: 2023/7/14  使用了把node移动到Head
        this.moveToHead(dLinkNode);
        return dLinkNode.value;
    }

    public void put(int key, int value) {
        DLinkNode dLinkNode = cache.get(key);
        if(null  == dLinkNode){
            DLinkNode newNode = new DLinkNode(key, value);
            cache.put(key,newNode);
            addNodeToHead(newNode);
            ++size;
            if(size > capacity){
                // TODO: 2023/7/14 移除尾部节点数据
                DLinkNode tailNode = this.removeTailNode();
                cache.remove(tailNode.key);
                --size;
            }
        }else {
            dLinkNode.value=value;
            // TODO: 2023/7/14 移动到头节点
            this.moveToHead(dLinkNode);

        }

    }

    private void moveToHead(DLinkNode dLinkNode) {
        removeNode(dLinkNode);
        addNodeToHead(dLinkNode);
    }
    /**
     * @author: dengpw
     * @createTime: 2023年07月17 09:44:29
     * @description: 把目标节点，添加到头节点
     * @param: newNode - [DLinkNode]
     * @return: void
     */
    private void addNodeToHead(DLinkNode newNode) {
        newNode.prev=head;
        newNode.next=head.next;
        head.next.prev=newNode;
        head.next=newNode;
    }

    /**
     * @author: dengpw
     * @createTime: 2023年07月17 10:09:30
     * @description: 删除双向链表中的节点
     * @param: node - [DLinkNode]
     * @return: void
     */
    private void removeNode(DLinkNode node){
        node.prev.next=node.next;
        node.next.prev=node.prev;
    }

    private DLinkNode removeTailNode(){
        DLinkNode prev = tail.prev;
        removeNode(prev);
        return prev;
    }


}
