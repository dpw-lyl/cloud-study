package com.dpw.lyl.join.good.job.algorithm.offer;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author: dengpw
 * @createTime: 2023年06月27日 09:16:22
 * @version: 1.0.0
 * 根据 逆波兰表示法，求该后缀表达式的计算结果。
 * 有效的算符包括+、-、*、/。每个运算对象可以是整数，也可以是另一个逆波兰表达式。
 * 说明：
 * 整数除法只保留整数部分。
 * 给定逆波兰表达式总是有效的。换句话说，表达式总会得出有效数值且不存在除数为 0 的情况。
 * 来源：力扣（LeetCode）
 * 链接：<a href="https://leetcode.cn/problems/8Zf90G">...</a>
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * 提示：
 * 1 <= tokens.length <= 104
 * tokens[i] 要么是一个算符（"+"、"-"、"*" 或 "/"），要么是一个在范围 [-200, 200] 内的整数
 * 逆波兰表达式：
 * 逆波兰表达式是一种后缀表达式，所谓后缀就是指算符写在后面。
 * 平常使用的算式则是一种中缀表达式，如 ( 1 + 2 ) * ( 3 + 4 ) 。
 * 该算式的逆波兰表达式写法为 ( ( 1 2 + ) ( 3 4 + ) * ) 。
 * 逆波兰表达式主要有以下两个优点：
 * 去掉括号后表达式无歧义，上式即便写成 1 2 + 3 4 + * 也可以依据次序计算出正确结果。
 * 适合用栈操作运算：遇到数字则入栈；遇到算符则取出栈顶两个数字进行计算，并将结果压入栈中。
 * @Description: 剑指 Offer II 036. 后缀表达式（中等）
 */
public class Solution36 {


    public static void main(String[] args) {

        System.out.println(new Solution36().evalRPN(new String[]{"4","13","5","/","+"}));

    }


    public int evalRPN(String[] tokens) {
        Deque<Integer> data = new LinkedList<>();
        for (String token : tokens) {
            if (isNum(token)) {
                data.push(Integer.parseInt(token));
            } else {
                Integer a = data.pop();
                Integer b = data.pop();
                switch (token) {
                    case "+":
                        data.push(b + a);
                        break;
                    case "-":
                        data.push(b - a);
                        break;
                    case "*":
                        data.push(b * a);
                        break;
                    case "/":
                        data.push(b /a);
                        break;
                    default:
                }

            }
        }

        return data.pop();
    }

    private boolean isNum(String str) {

        return !("+".equals(str) || "-".equals(str) || "*".equals(str) || "/".equals(str));
    }


}
