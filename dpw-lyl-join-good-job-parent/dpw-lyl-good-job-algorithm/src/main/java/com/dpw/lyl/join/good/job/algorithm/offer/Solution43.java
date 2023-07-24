package com.dpw.lyl.join.good.job.algorithm.offer;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author: dengpw
 * @createTime: 2023年06月27日 09:16:22
 * @version: 1.0.0
 * 完全二叉树是每一层（除最后一层外）都是完全填充（即，节点数达到最大，第 n 层有 2n-1个节点）的，并且所有的节点都尽可能地集中在左侧。
 * 设计一个用完全二叉树初始化的数据结构CBTInserter，它支持以下几种操作：
 * CBTInserter(TreeNode root)使用根节点为root的给定树初始化该数据结构；
 * CBTInserter.insert(int v) 向树中插入一个新节点，节点类型为 TreeNode，值为 v 。使树保持完全二叉树的状态，并返回插入的新节点的父节点的值；
 * CBTInserter.get_root() 将返回树的根节点。
 * 来源：力扣（LeetCode）
 * 链接：<a href="https://leetcode.cn/problems/NaqhDT">...</a>
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * 提示：
 * 最初给定的树是完全二叉树，且包含1到1000个节点。
 * 每个测试用例最多调用CBTInserter.insert 操作10000次。
 * 给定节点或插入节点的每个值都在0到5000之间。
 * @Description: 剑指 Offer II 043. 往完全二叉树添加节点（中等）
 */
public class Solution43 {

    private TreeNode root;

    private Deque<TreeNode> treeNodes;

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }


    public static void main(String[] args) {


    }


    public Solution43(TreeNode root) {
        this.treeNodes=new LinkedList<>();
        this.root=root;



    }

    public int insert(int v) {




        return 0;
    }

    public TreeNode get_root() {

        return root;
    }

}
