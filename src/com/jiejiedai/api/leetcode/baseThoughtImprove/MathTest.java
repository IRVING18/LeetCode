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

    /**
     * 实现 pow(x, n) ，即计算 x 的 n 次幂函数（即，xn）。不得使用库函数，同时不需要考虑大数问题。
     *
     * 示例 1：
     * 输入：x = 2.00000, n = 10
     * 输出：1024.00000
     *
     * 示例 2：
     * 输入：x = 2.10000, n = 3
     * 输出：9.26100
     *
     * 示例 3：
     * 输入：x = 2.00000, n = -2
     * 输出：0.25000
     * 解释：2-2 = 1/22 = 1/4 = 0.25
     *
     * 提示：
     *
     * -100.0 < x < 100.0
     * -231 <= n <= 231-1
     * -104 <= xn <= 104
     *
     *
     *
     *
     * https://leetcode.cn/problems/shu-zhi-de-zheng-shu-ci-fang-lcof/solution/mian-shi-ti-16-shu-zhi-de-zheng-shu-ci-fang-kuai-s/
     */
    public double myPow(double x, int n) {
        if(x == 0) return 0;
        long b = n;
        double res = 1.0;
        if(b < 0) {
            x = 1 / x;
            b = -b;
        }
        while(b > 0) {
            if((b & 1) == 1) res *= x;
            x *= x;
            b >>= 1;
        }
        return res;
    }

}
