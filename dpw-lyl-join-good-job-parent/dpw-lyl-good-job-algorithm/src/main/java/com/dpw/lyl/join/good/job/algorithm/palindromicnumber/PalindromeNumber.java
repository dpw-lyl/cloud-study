package com.dpw.lyl.join.good.job.algorithm.palindromicnumber;

/**
 * @Author: dengpw
 * @createTime: 2023年04月21日 09:27:49
 * @version: 1.0.0
 * @Description: 回文数
 * Question：
 * 判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
 * **示例 1:**
 *
 * ```
 * 输入: 121
 * 输出: true
 * ```
 *
 * **示例 2:**
 *
 * ```
 * 输入: -121
 * 输出: false
 * 解释: 从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。
 * ```
 *
 * **示例 3:**
 *
 * ```
 * 输入: 10
 * 输出: false
 */
public class PalindromeNumber {


    /**
     * @author: dengpw
     * @createTime: 2023年04月21 09:30:59
     * @description: 暴力解
     * @param: num - [int]
     * @return: boolean
     */
    public boolean isPalindromeNumber(int num){
        return new StringBuilder(String.valueOf(num)).reverse().toString().equals(String.valueOf(num));
    }

    /**
     * @author: dengpw
     * @createTime: 2023年04月21 09:35:21
     * @description:
     * 通过取整和取余操作获取整数中对应的数字进行比较。
     *
     * 举个例子：1221 这个数字。
     *
     * - 通过计算 1221 / 1000， 得首位1
     * - 通过计算 1221 % 10， 可得末位 1
     * - 进行比较
     * - 再将 22 取出来继续比较
     * @param: num - [int]
     * @return: boolean
     */
    public boolean isPalindromeNumber02(int num){

        //边界判断
        if (num < 0) return false;
        int div = 1;
        //计算当前最大位
        while (num / div >= 10) div *= 10;
        while (num > 0) {
            int left = num / div;
            int right = num % 10;
            if (left != right) return false;
            num = (num % div) / 10;
            div /= 100;
        }
        return true;
    }


    /**
     * @author: dengpw
     * @createTime: 2023年04月21 10:16:48
     * @description:
     *直观上来看待回文数的话，就感觉像是将数字进行对折后看能否一一对应。
        所以这个解法的操作就是 **取出后半段数字进行翻转**。
        这里需要注意的一个点就是由于回文数的位数可奇可偶，所以当它的长度是偶数时，它对折过来应该是相等的；
        当它的长度是奇数时，那么它对折过来后，有一个的长度需要去掉一位数（除以 10 并取整）。
    具体做法如下：
    - 每次进行取余操作 （ %10），取出最低的数字：`y = x % 10`
    - 将最低的数字加到取出数的末尾：`revertNum = revertNum * 10 + y`
    - 每取一个最低位数字，x 都要自除以 10
    - 判断 `x` 是不是小于 `revertNum` ，当它小于的时候，说明数字已经对半或者过半了
    - 最后，判断奇偶数情况：如果是偶数的话，revertNum 和 x 相等；如果是奇数的话，最中间的数字就在revertNum 的最低位上，将它除以 10 以后应该和 x 相等。
     * @param: num - [int]
     * @return: boolean
     */
    public boolean isPalindromeNumber03(int num) {
        if (num < 0 || (num % 10 == 0 && num != 0)) {
            return false;
        }
        int revertedNum = 0;
        while (revertedNum < num) {
            revertedNum = revertedNum * 10 + num % 10;
            num /= 10;
        }
        return num == revertedNum || num == revertedNum / 10;
    }


    public static void main(String[] args) {
        PalindromeNumber palindromeNumber = new PalindromeNumber();
        boolean palindromeNumber1 = palindromeNumber.isPalindromeNumber03(100001);
        System.out.println(palindromeNumber1);
        System.out.println();
    }


}
