package com.jiejiedai.api.leetcode.baseThoughtImprove;

/**
 * 动态规划
 *
 * 1、思路：从前边的简单情况入手，简单分析 =》 推导动态规划方程
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


    /**
     * 输入一个整型数组，数组中的一个或连续多个整数组成一个子数组。求所有子数组的和的最大值。
     *
     * 要求时间复杂度为O(n)。
     *
     * 示例1:
     *
     * 输入: nums = [-2,1,-3,4,-1,2,1,-5,4]
     * 输出: 6
     * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
     *
     * 提示：
     *
     * 1 <= arr.length <= 10^5
     * -100 <= arr[i] <= 100
     *
     * 链接：https://leetcode.cn/problems/lian-xu-zi-shu-zu-de-zui-da-he-lcof
     *
     * 思路：
     *
     * i = 0; f(0) = -2
     * i = 1; f(1) = max(f(0) + 1) , 1) = 1
     * i = 2; f(2) = max(f(1) + -3 , -3) = -2
     * ...
     *
     * 推出动态规划方程如下：
     * f(i) = Math.max(f(i - 1) + nums[i]) , nums[i])
     */
    public int maxSubArray(int[] nums) {
        int pre = nums[0];
        int max = nums[0];
        for (int i = 1; i < nums.length; i++) {
            pre = Math.max(pre + nums[i], nums[i]);
            max = Math.max(max, pre);
        }
        return max;
    }


    /**
     * 在一个 m*n 的棋盘的每一格都放有一个礼物，每个礼物都有一定的价值（价值大于 0）。
     * 你可以从棋盘的左上角开始拿格子里的礼物，并每次向右或者向下移动一格、直到到达棋盘的右下角。
     * 给定一个棋盘及其上面的礼物的价值，请计算你最多能拿到多少价值的礼物？
     *
     *  
     *
     * 示例 1:
     *
     * 输入:
     * [
     *   [1,3,1],
     *   [1,5,1],
     *   [4,2,1]
     * ]
     * 输出: 12
     * 解释: 路径 1→3→5→2→1 可以拿到最多价值的礼物
     *  
     *
     * 提示：
     *
     * 0 < grid.length <= 200
     * 0 < grid[0].length <= 200
     *
     *
     * 链接：https://leetcode.cn/problems/li-wu-de-zui-da-jie-zhi-lcof
     *
     * 思路：
     *
     * 一、推出公式
     *      每次在矩阵中，只能向左或者向下移动，所以
     *      当grid(1,1)也就是5的值设为f(1,1)，选取最佳路径时，应该比较max(f(0,1), f(1,0))两个路径上的最大值 + grid(1,1)
     *
     *      那么我们可以大概推出动态规划方程：
     *      f(i,j) = max(f(i - 1,j), f(i, j -1)) + grid(i,j)
     *
     *      但是需要特殊处理下i = 0;j = 0时的特殊情况。
     *          1、i = 0 , j = 0; f(i,j) = grid(0,0)
     *          2、i = 0 , j != 0: f(i,j) = f(i, j -1) + grid(i,j)
     *          3、i != 0 , j = 9; f(i,j) = f(i - 1, j) + grid(i,j)
     *          4、i,j != 0; f(i,j) = max(f(i - 1,j), f(i, j -1)) + grid(i,j)
     *
     * 二、优化空间复杂度
     *      若我们把每个f(i,j)的值都存下来，需要o(N*M)，所以我们可以直接原地修改值
     *
     */
    public int maxValue(int[][] grid) {

        int n = grid.length;
        int m = grid[0].length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (i == 0 && j == 0) continue;
                if (i == 0) {
                    grid[i][j] = grid[i][j - 1] + grid[i][j];
                } else if (j == 0) {
                    grid[i][j] = grid[i - 1][j] + grid[i][j];
                } else {
                    grid[i][j] = Math.max(grid[i][j - 1], grid[i - 1][j]) + grid[i][j];
                }
            }
        }

        return grid[n-1][m-1];

    }
}
