package com.jiejiedai.api.leetcode.baseThoughtImprove;

/**
 * 动态规划
 */
public class DynamicPlanning {
    /**
     * 剑指 Offer 10- I. 斐波那契数列
     *
     * 写一个函数，输入 n ，求斐波那契（Fibonacci）数列的第 n 项（即 F(N)）。斐波那契数列的定义如下：
     *
     * F(0) = 0,   F(1) = 1
     * F(N) = F(N - 1) + F(N - 2), 其中 N > 1.
     * 斐波那契数列由 0 和 1 开始，之后的斐波那契数就是由之前的两数相加而得出。
     *
     * 答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
     *  
     * 示例 1：
     * 输入：n = 2
     * 输出：1
     *
     * 示例 2：
     * 输入：n = 5
     * 输出：5
     *
     * 0、1、1、2、3、5
     *
     * 链接：https://leetcode.cn/problems/fei-bo-na-qi-shu-lie-lcof
     */
    public int fib(int n) {
        if (n < 2) {
            return n;
        }

        //当n = 2时，n-1 = 1; n-2 = 0;
        int r_2 = 0;
        int r_1 = 1;
        int r = 1;

        int MOD = 1000000007;

        for (int i = 2; i <= n; i++) {
            r = (r_1 + r_2) % MOD;
            r_2 = r_1;
            r_1 = r;
        }

        return r;

    }


    /**
     *
     * 剑指 Offer 10- II. 青蛙跳台阶问题
     * 一只青蛙一次可以跳上1级台阶，也可以跳上2级台阶。求该青蛙跳上一个 n 级的台阶总共有多少种跳法。
     * 答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
     *
     * 示例 1：
     * 输入：n = 2
     * 输出：2
     *
     * 示例 2：
     * 输入：n = 7
     * 输出：21
     *
     * 示例 3：
     * 输入：n = 0
     * 输出：1
     *
     * 链接：https://leetcode.cn/problems/qing-wa-tiao-tai-jie-wen-ti-lcof
     *
     *
     * 思路
     * n = 1 : f(n) = 1;    1
     * n = 2 : f(n) = 2;    11、2
     * n = 3 : f(n) = 3;    111、12、21
     * n = 4 : f(n) = 5；   1111、121、211、112、22
     * n = 5 : f(n) = 8;    11111、2111、1211、1121、1112、221、122、212
     *
     * 那么推出类似菲波那切数列，但是需要满足f(n) = f(n-1) + f(n-2)，需要f(0) = 1；和菲波那切数列区别就在f(0)的值了。
     * 1、1、2、3、5、8
     *
     * @param n
     * @return
     */
    public int numWays(int n) {
        int MOD = 1000000007;
        if (n == 0) {
            return 1;
        }
        if (n < 4) {
            return n;
        }

        //当n = 3是，r-2 = 1; r-1 = 2;
        int r_2 = 2;
        int r_1 = 3;
        int r = 5;
        for (int i = 4; i <= n; i++) {
            r = (r_1 + r_2) % MOD;
            r_2 = r_1;
            r_1 = r;
        }
        return r;
    }


    /**
     * 剑指 Offer 63. 股票的最大利润
     *
     * 假设把某股票的价格按照时间先后顺序存储在数组中，请问买卖该股票一次可能获得的最大利润是多少？
     *
     * 示例 1:
     * 输入: [7,1,5,3,6,4]
     * 输出: 5
     * 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
     *      注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格。
     *
     * 示例 2:
     * 输入: [7,6,4,3,1]
     * 输出: 0
     * 解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
     *
     * 链接：https://leetcode.cn/problems/gu-piao-de-zui-da-li-run-lcof
     *
     */
    public int maxProfit(int[] prices) {
        if (prices.length < 1){
            return 0;
        }
        int min = prices[0];
        int income = 0;
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < min) {
                min = prices[i];
            }else if((prices[i] - min) > income) {
                income = prices[i] - min;
            }
        }
        return income;
    }
}
