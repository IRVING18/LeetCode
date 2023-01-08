package com.jiejiedai.api.leetcode;

public class LongestPalindromeTest {

    public static void main(String[] args) {
        String str = longestPalindrome("babad");
        System.out.println(str);

    }

    public static String longestPalindrome(String s) {

        String longest = "";
        for (int i = 0; i < s.length() - longest.length(); i++) {
            char head = s.charAt(i);
            for (int j = i; j < s.length(); j++) {
                char tail = s.charAt(j);
                String tmp = s.substring(i, j + 1);
                if (head == tail && isPalindrome(tmp)) {
                    if (tmp.length() > longest.length()) {
                        longest = tmp;
                    }
                }
            }

        }
        return longest;
    }

    public static boolean isPalindrome(String str) {
        for (int i = 0; i < str.length() / 2; i++) {
            char head = str.charAt(i);
            char tail = str.charAt(str.length() - 1 - i);
            if (head != tail) {
                return false;
            }
        }
        return true;
    }
//    public static String longestPalindrome(String s) {
//
//        String longest = "";
//        for (int i = 0; i < s.length() - longest.length(); i++) {
//            char head = s.charAt(i);
//            int times = 0;
//            for (int j = i; j < s.length(); j++) {
//                char tail = s.charAt(j);
//                if (head == tail) {
//                    times ++;
//                    String tmp = s.substring(i, j + 1);
//                    if (tmp.length() > longest.length()) {
//                        longest = tmp;
//                    }
//                    if(times > 1){
//                        break;
//                    }
//
//                }
//            }
//
//        }
//        return longest;
//    }
    /**
     * 找出最长的相同字符串
     */
//    public static String longestPalindrome(String s) {
//
//        String longest = "";
//        for (int l = 1; l <= s.length(); l++) {
//            for (int i = 0; i <= s.length() - l; i++) {
//                String str = s.substring(i, i + l);
//
//                if (s.contains(str) && s.indexOf(str) != s.lastIndexOf(str) && longest.length() < str.length()) {
//                    longest = str;
//                }
//            }
//
//        }
//        return longest;
//    }
}
