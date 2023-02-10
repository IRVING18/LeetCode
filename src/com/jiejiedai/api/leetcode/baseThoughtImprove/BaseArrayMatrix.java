package com.jiejiedai.api.leetcode.baseThoughtImprove;

/**
 * 二维数组的收集
 */
public class BaseArrayMatrix {

    /**
     * 输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字。
     * 示例 1：
     * 输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
     * 输出：[1,2,3,6,9,8,7,4,5]
     *
     * 链接：https://leetcode.cn/problems/shun-shi-zhen-da-yin-ju-zhen-lcof
     *
     * 思路：
     * 设 l,r,t,b 左右上下，四个标识
     *
     *      打印方向     打印   打印后   是否结束
     * 1、从左至右打印    l->r   t+1      t < b
     * 2、从上至下打印    t->b   r-1      l < r
     * 3、从右至左打印    r->l   b-1      t < b
     * 4、从下至上打印    b->t   l+1      l < r
     */
    public int[] spiralOrder(int[][] matrix) {
        if (matrix.length == 0) return new int[]{};

        int l = 0;
        int r = matrix[0].length - 1;
        int t = 0;
        int b = matrix.length - 1;
        int[] res = new int[matrix.length * matrix[0].length];
        int index = 0;

        //打印一圈为一次循环
        while (true){
            //1、从左至右打印
            for (int i = l; i <= r; i++) {
                res[index++] = matrix[t][i];
            }
            //判断是否结束
            if (++t > b) break;

            //2、从上至下打印
            for (int i = t; i <= b; i++) {
                res[index++] = matrix[i][r];
            }
            if (--r < l) break;

            //3、从右至左
            for (int i = r; i >= l; i--) {
                res[index++] = matrix[b][i];
            }
            if (--b < t) break;

            //4、从下至上
            for (int i = b; i >= t; i--) {
                res[index++] = matrix[i][l];
            }
            if (++l > r) break;
        }

        return res;
    }
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


    public static void main(String[] args) {
//        TreeNode node = new TreeNode(1);
//        TreeNode right = new TreeNode(2);
//        node.left = null;
//        node.right = right;
//        System.out.println(node.val + ' ' + (node.left != null ? node.left.val : 'null') + ' ' + node.right.val);
//
//        mirrorTree(node);
//        System.out.println(node.val + ' ' + (node.left != null ? node.left.val : 'null') + ' ' + (node.right != null ? node.right.val : 'null'));


//        char[][] arr = new char[][]{
//                {'A', 'B', 'C', 'C'},
//                {'S', 'F', 'C', 'S'},
//                {'A', 'D', 'E', 'E'}
//        };
//
//        System.out.println(exist1(arr, "ABCCED") ? "true" : "false");

        System.out.println(35  % 10 +"   "+35 / 10);
    }

    /**
     * 剑指 Offer 12. 矩阵中的路径
     * <p>
     * 给定一个 m x n 二维字符网格 board 和一个字符串单词 word 。如果 word 存在于网格中，返回 true ；否则，返回 false 。
     *  {'A', 'B', 'C', 'C'},
     *  {'S', 'F', 'C', 'S'},
     *  {'A', 'D', 'E', 'E'}
     *
     *  ABCCED
     *
     * <p>
     * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
     * <p>
     * 链接：https://leetcode.cn/problems/ju-zhen-zhong-de-lu-jing-lcof
     *
     *
     * 思路：递归
     */
    public static boolean exist1(char[][] board, String word) {
        int r = board.length;
        int l = board[0].length;

        boolean[][] visited = new boolean[r][l];
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < l; j++) {
                if (check1(board, visited, word, i, j, 0)) {
                    return true;
                }
            }
        }

        return false;
    }


    public static boolean check1(char[][] board, boolean[][] visited, String word, int r, int l, int k) {
        if (board[r][l] != word.charAt(k)) {
            return false;
        } else if (k == word.length() - 1) {
            return true;
        }

        int[][] range = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        visited[r][l] = true;
        for (int i = 0; i < range.length; i++) {
            int newr = r + range[i][0];
            int newl = l + range[i][1];
            if (newr >= 0 && newr < board.length && newl >= 0 && newl < board[0].length) {
                if (!visited[newr][newl]) {
                    if (check1(board, visited, word, newr, newl, k + 1)) {
                        return true;
                    }
                }
            }
        }
        visited[r][l] = false;
        return false;
    }


    /**
     * 面试题13. 机器人的运动范围
     *
     * 地上有一个m行n列的方格，从坐标 [0,0] 到坐标 [m-1,n-1] 。
     * 一个机器人从坐标 [0, 0] 的格子开始移动，它每次可以向左、右、上、下移动一格（不能移动到方格外），也不能进入行坐标和列坐标的数位之和大于k的格子。
     * 例如，当k为18时，机器人能够进入方格 [35, 37] ，因为3+5+3+7=18。但它不能进入方格 [35, 38]，因为3+5+3+8=19。请问该机器人能够到达多少个格子？
     *
     *
     * 示例 1：
     * 输入：m = 2, n = 3, k = 1
     * 输出：3
     *
     * 示例 2：
     * 输入：m = 3, n = 1, k = 0
     * 输出：1
     *
     * 提示：
     * 1 <= n,m <= 100
     * 0 <= k <= 20
     *
     * 链接：https://leetcode.cn/problems/ji-qi-ren-de-yun-dong-fan-wei-lcof
     *
     *
     * 思路一：递归算法
     * todo 思路二：广度优先算法：https://leetcode.cn/tag/breadth-first-search/problemset/
     */
    public int movingCount(int m, int n, int k) {
        boolean[][] visited = new boolean[m][n];
        return dfs(0, 0, m, n, k, visited);
    }


    public int dfs(int i, int j, int m, int n, int k, boolean[][] visited) {
        if (i >= m || j >= n || k < getSum(i) + getSum(j) || visited[i][j]) {
            return 0;
        }

        visited[i][j] = true;
        return 1 + dfs(i + 1, j, m, n, k, visited) + dfs(i, j + 1, m, n, k, visited);
    }

    public int getSum(int num) {
        return num % 10 + num / 10;
    }


}
