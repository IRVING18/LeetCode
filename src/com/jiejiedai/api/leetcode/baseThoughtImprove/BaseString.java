package com.jiejiedai.api.leetcode.baseThoughtImprove;

/**
 * 字符串操作
 */
public class BaseString {

    /**
     * 剑指 Offer 05. 替换空格
     * 请实现一个函数，把字符串 s 中的每个空格替换成"%20"。
     * <p>
     * https://leetcode.cn/problems/ti-huan-kong-ge-lcof/?envType=study-plan&id=lcof&plan=lcof&plan_progress=cafihze
     */
    public String replaceSpace(String s) {
        char[] arr = new char[s.length() * 3];
        int size = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == ' ') {
                arr[size++] = '%';
                arr[size++] = '2';
                arr[size++] = '0';
            } else {
                arr[size++] = c;
            }
        }
        return new String(arr, 0, size);
    }

    /**
     * 剑指 Offer 58 - II. 左旋转字符串
     * 字符串的左旋转操作是把字符串前面的若干个字符转移到字符串的尾部。请定义一个函数实现字符串左旋转操作的功能。比如，输入字符串"abcdefg"和数字2，该函数将返回左旋转两位得到的结果"cdefgab"。
     * <p>
     * 链接：https://leetcode.cn/problems/zuo-xuan-zhuan-zi-fu-chuan-lcof
     */
    public static String reverseLeftWords(String s, int n) {
        int nReal = n % s.length();
        if(nReal == 0) {
            return s;
        }
        char[] arr = new char[s.length()];
        for(int i = nReal; i < s.length(); i++) {
            arr[i] = s.charAt(i);
        }
        for(int i = 0; i < nReal; i++) {
            arr[i] = s.charAt(i);
        }
        return new String(arr);
    }

    public static void main(String[] args) {
        reverseLeftWords("adcdfg",2);
    }

}
