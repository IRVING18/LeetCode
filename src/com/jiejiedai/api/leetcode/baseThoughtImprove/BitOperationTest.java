package com.jiejiedai.api.leetcode.baseThoughtImprove;

/**
 * 位运算
 * 1、右移 >> 无符号右移 >>>
 * 2、左移 << 无符号左移 <<<
 */
public class BitOperationTest {

    /**
     * 剑指 Offer 15. 二进制中1的个数
     *
     * 编写一个函数，输入是一个无符号整数（以二进制串的形式），返回其二进制表达式中数字位数为 '1' 的个数（也被称为 汉明重量).）。
     *
     * 一、左移位运算
     * 1、判断最右位置是否为1，n & 1 判断
     * 2、计算后无符号右移 n >>> 1
     *
     *  复杂度：O(logn)
     *  n代表数字n 最高位1的所在位数（例如 log 4 = 2, log16 = 4)
     *
     * 二、方法二：巧用 n&(n−1)
     *
     * 作者：jyd
     * 链接：https://leetcode.cn/problems/er-jin-zhi-zhong-1de-ge-shu-lcof/solution/mian-shi-ti-15-er-jin-zhi-zhong-1de-ge-shu-wei-yun/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     *
     */
    public int hammingWeight(int n) {
        int count = 0;
        while (n != 0){
            count += n & 1;
            n = n >>> 1;
        }

        return count;
    }

    /**
     *
     * a(i)  b(i)  无进位和n(i)  进位c(i+1)
     * 0      0        0            0
     * 0      1        1            0
     * 1      0        1            0
     * 1      1        0            1
     *
     * 非进位和：异或运算
     * 进位和：与运算 + 左移一位
     *
     * 所以和s = {非进位和n(i)} + {进位c(i+1)}
     * => s = n + c
     */
    public int add(int a, int b) {
        //进位为0时跳出
        while (b!= 0) {
            //1、进位和 c 暂存
            int c = (a & b) << 1;
            //2、非进位和
            a ^= b;
            //3、b = 进位
            b = c;
        }
        return a;
    }

    public static void main(String[] args) {
        System.out.println("  "+ ( 3 & 15 ));
    }
}
