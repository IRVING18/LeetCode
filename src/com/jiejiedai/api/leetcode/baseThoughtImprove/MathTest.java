package com.jiejiedai.api.leetcode.baseThoughtImprove;

/**
 * 数学相关
 *
 * 等差数列求和公式：Sn = (n * (a1 + an)) / 2
 */
public class MathTest {

    /**
     *
     * 用乘除：直接等差数列求和 (n * (n + 1)) / 2
     * 用递归： 需要用if
     *
     * 所以结合 && 运算符结束递归
     */
    public int sumNums(int n) {
//        if (n == 1) return 1;
//        return sumNums(n - 1) + n;

        boolean f = n > 1 && (n += sumNums(n - 1)) > 0 ;
        return n;
    }

}
