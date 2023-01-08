package com.jiejiedai.api.leetcode.baseThoughtImprove;

public class ThoughtSearch {

    /**
     * 二分法查找
     * 先决条件：有序
     * <p>
     * 若是小->大
     * 1、计算mid角标
     * 2、mid角标位置大于target，循环在start->mid位置找，
     * 3、循环跳出条件：mid计算完小于start，或者大于end，或者找到条件
     *
     * @param nums
     * @param target
     * @param searchLeft
     * @return
     */
    private static int binarySearch(int[] nums, int target, boolean searchLeft) {
        int len = nums.length;
        int start = 0;
        int end = len - 1;
        int result = -1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] > target) {
                end = mid - 1;
            } else if (nums[mid] < target) {
                start = mid + 1;
            } else {
                result = mid;
                if (searchLeft) {
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            }
        }
        return result;
    }

    /**
     * 剑指 Offer 53 - II. 0～n-1中缺失的数字
     * <p>
     * 一个长度为n-1的递增排序数组中的所有数字都是唯一的，并且每个数字都在范围0～n-1之内。
     * 在范围0～n-1内的n个数字中有且只有一个数字不在该数组中，请找出这个数字。
     * <p>
     * 示例 1:
     * 输入: [0,1,3]
     * 输出: 2
     * <p>
     * 示例 2:
     * 输入: [0,1,2,3,5,6,7,8,9]
     * 输出: 4
     * <p>
     * 链接：https://leetcode.cn/problems/que-shi-de-shu-zi-lcof
     * <p>
     * 思路一：暴力遍历
     * 思路二：二分法
     * 1、二分查mid 位置是否 == index角标，true: 在右侧，false：在左侧 ；（num[mid] 不可能< index ，因为长度为n-1，每个数字都在范围0～n-1之内且唯一的）
     * 2、跳出条件 start > end
     */
    public int missingNumber(int[] nums) {
        int len = nums.length;
        int start = 0;
        int end = len - 1;
        while (start < end) {
            int mid = (start + end) / 2;
            if (nums[mid] == mid) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return start;


//        if (nums.length < 1) {
//            return -1;
//        }
//        if (nums[nums.length - 1] != nums.length) {
//            return nums.length;
//        }
//        for (int i = 0; i < nums.length; i++) {
//            if (nums[i] != i) {
//                return i;
//            }
//        }
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

    private boolean findBinarySearch(int[] nums, int target) {
        //1、mid = (start + end )/2 ；nums[mid] < target 在右侧，== 找到了，>在左侧
        //2、跳出条件 start > end;
        int len = nums.length;
        int start = 0;
        int end = len - 1;
        while (start <= end) {
            int mid = (start + end) / 2;
            if (nums[mid] < target) {
                start = mid + 1;
            } else if (nums[mid] > target) {
                end = mid - 1;
            } else {
                return true;
            }
        }
        return false;
    }


    /**
     * 剑指 Offer 11. 旋转数组的最小数字
     * <p>
     * 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。
     * 给你一个可能存在 重复 元素值的数组 numbers ，它原来是一个升序排列的数组，并按上述情形进行了一次旋转。
     * 请返回旋转数组的最小元素。例如，数组 [3,4,5,1,2] 为 [1,2,3,4,5] 的一次旋转，该数组的最小值为 1。  
     * 注意，数组 [a[0], a[1], a[2], ..., a[n-1]] 旋转一次 的结果为数组 [a[n-1], a[0], a[1], a[2], ..., a[n-2]] 。
     * <p>
     * 示例 1：
     * 输入：numbers = [3,4,5,1,2]
     * 输出：1
     * <p>
     * 示例 2：
     * 输入：numbers = [2,2,2,0,1]
     * 输出：0
     * <p>
     * 链接：https://leetcode.cn/problems/xuan-zhuan-shu-zu-de-zui-xiao-shu-zi-lcof
     * <p>
     * <p>
     * 理解题意：
     * numbers 原来是一个升序排序的数组，并进行了 1 至 n 次旋转
     * <p>
     * 意思是，只进行了一次旋转，旋转的数量是0-n。
     * 所以，才有后边题解中的属性（https://leetcode.cn/problems/xuan-zhuan-shu-zu-de-zui-xiao-shu-zi-lcof/solution/mian-shi-ti-11-xuan-zhuan-shu-zu-de-zui-xiao-shu-3/）
     * <p>
     * 思路：
     * 1、旋转点x，左边的数组的任一元素 >= 右边数组的任一元素
     * <p>
     * 2、所以mid = (start + end)/2；
     * 2.1 nums[mid] > nums[end] 那么说明旋转点x，在mid -> end中间
     * 2.2 nums[mid] < nums[end] 那么说明mid和end都在右边数组中，所以x在start -> mid中间
     * 2.3 nums[mid] == nums[end] 那么不容易判断x位置，所以将end-1
     * <p>
     * 3、跳出条件 start > end
     */
    public int minArray(int[] numbers) {
        int start = 0;
        int end = numbers.length - 1;
        while (start <= end) {
            int mid = (start + end) / 2;
            if (numbers[mid] > numbers[end]) {
                start = mid + 1;
            } else if (numbers[mid] < numbers[end]) {
                //关键点：当mid 和 end都在右边数组时，mid可能就是x，所以不能-1；
                end = mid;
            } else {
                end--;
            }
        }
        return numbers[start];
    }
}
