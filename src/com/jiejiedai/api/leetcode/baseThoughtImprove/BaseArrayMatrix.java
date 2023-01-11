package com.jiejiedai.api.leetcode.baseThoughtImprove;

/**
 * 二维数组的收集
 */
public class BaseArrayMatrix {
    /**
     * 剑指 Offer 04. 二维数组中的查找
     * <p>
     * 在一个 n * m 的二维数组中，每一行都按照从左到右 非递减 的顺序排序，
     * 每一列都按照从上到下 非递减 的顺序排序。请完成一个高效的函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
     * 现有矩阵 matrix 如下：
     * <p>
     * [
     * [1,   4,  7, 11, 15],
     * [2,   5,  8, 12, 19],
     * [3,   6,  9, 16, 22],
     * [10, 13, 14, 17, 24],
     * [18, 21, 23, 26, 30]
     * ]
     * 给定 target = 5，返回 true。
     * <p>
     * 给定 target = 20，返回 false。
     * <p>
     * 链接：https://leetcode.cn/problems/er-wei-shu-zu-zhong-de-cha-zhao-lcof
     * <p>
     * 思路一：暴力循环
     * 思路二：Hash 空间换时间
     * 思路三：每一行进行二分 O(n*log(m))
     * 思路四：Z字形查找
     * <p>
     * 如果 matrix[x,y]=target，说明搜索完成；
     * <p>
     * 如果 matrix[x,y]>target，由于每一列的元素都是升序排列的，那么在当前的搜索矩阵中，所有位于第 y 列的元素都是严格大于 target 的，因此我们可以将它们全部忽略，即将 y 减少 1；
     * <p>
     * 如果 matrix[x,y]<target，由于每一行的元素都是升序排列的，那么在当前的搜索矩阵中，所有位于第 x 行的元素都是严格小于 target 的，因此我们可以将它们全部忽略，即将 x 增加 1。
     */
    public boolean findNumberIn2DArray(int[][] matrix, int target) {

//        //二分
//        for (int i = 0; i < matrix.length; i++) {
//            if (findBinarySearch(matrix[i],target)){
//                return true;
//            }
//        }
//        return false;

        //Z字形
        if (matrix.length < 1) {
            return false;
        }
        int m = matrix.length;
        int n = matrix[0].length;

        int x = 0;
        int y = n - 1;
        while (x < m && y >= 0) {
            if (matrix[x][y] == target) {
                return true;
            }
            if (matrix[x][y] > target) {
                y--;
            } else if (matrix[x][y] < target) {
                x++;
            }
        }
        return false;

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
