package com.dpw.lyl.join.good.job.algorithm.mostwatercontainer;

/**
 * @Author: dengpw
 * @createTime: 2023年04月21日 14:31:27
 * @version: 1.0.0
 * @Description: 盛最多水的容器
 * Question：
 * 给你n个非负整数a1，a2，...，an，每个数代表坐标中的一个点(i,ai)。在坐标内画n条垂直线，
 * 垂直线i的两个端点分别为(i,ai)和(i,0)。找出其中的两条线，使得它们与x轴共同构成的容器可以容纳最多的水。
 * 说明：你不能倾斜容器，且n的值至少为2。
 * 示例：
 * 输入：[1,8,6,2,5,4,8,3,7]
 * 输出：49
 */
public class MostWaterContainer {


    public int getMostWaterContainer(int[] n){
        int length = n.length;
        int left = 0, right = length - 1;
        int x = right - left + 1;
        int y = Math.min(n[left], n[right]);
        int area = x * y;
        while (left != right) {
            if (n[right] >= n[left]) {
                left++;
            } else {
                right--;
            }
            x = right - left;
            y = Math.min(n[left], n[right]);
            int areaTemp = x * y;
            if (areaTemp > area) {
                area = areaTemp;
            }
        }
        return area;

    }

    public int getMaxArea(int[] a) {
        int hg = a.length;//数组长度
        int left = 0;
        int right = hg - 1;
        //寻找最大面积
        int x = right - left + 1;//长方形的长度
        int y = Math.min(a[left], a[right]);
        int area = x * y;
        while (left != right) {
            if (a[right] >= a[left]) {//左边大于右边 每次要移动高度比较小的那个 才有可能让面积增大
                left = left + 1;
            } else {
                right = right - 1;
            }
            x = right - left;
            y = Math.min(a[left], a[right]);
            int temp = x * y;
            if (temp > area) {
                area = temp;
            }
        }
        return area;
    }

    public static void main(String[] args) {

        MostWaterContainer mostWaterContainer = new MostWaterContainer();
        int[] a = new int[]{1,2,21,8,6,2,5,4,8,3,7,20};
        int maxArea = mostWaterContainer.getMostWaterContainer(a);
        System.out.println(maxArea);
    }

}
