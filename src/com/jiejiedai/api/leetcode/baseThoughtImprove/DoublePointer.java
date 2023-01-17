package com.jiejiedai.api.leetcode.baseThoughtImprove;

import com.jiejiedai.api.leetcode.ListNode;

/**
 * 双指针
 * <p>
 * 1、同向指针、对向指针、标记双指针，
 */
public class DoublePointer {


    /**
     * 给定单向链表的头指针和一个要删除的节点的值，定义一个函数删除该节点。
     * <p>
     * 返回删除后的链表的头节点。
     * <p>
     * 注意：此题对比原题有改动
     * <p>
     * 示例 1:
     * <p>
     * 输入: head = [4,5,1,9], val = 5
     * 输出: [4,1,9]
     * 解释: 给定你链表中值为 5 的第二个节点，那么在调用了你的函数之后，该链表应变为 4 -> 1 -> 9.
     * 示例 2:
     * <p>
     * 输入: head = [4,5,1,9], val = 1
     * 输出: [4,5,9]
     * 解释: 给定你链表中值为 1 的第三个节点，那么在调用了你的函数之后，该链表应变为 4 -> 5 -> 9.
     * <p>
     * 链接：https://leetcode.cn/problems/shan-chu-lian-biao-de-jie-dian-lcof
     */
    public ListNode deleteNode(ListNode head, int val) {
        if (head == null) {
            return null;
        }
        if (head.val == val) {
            return head.next;
        }
        ListNode res = head;
        ListNode cur = head.next;
        ListNode pre = head;
        while (cur != null) {
            if (cur.val == val) {
                pre.next = cur.next;
                break;
            }
            pre = cur;
            cur = cur.next;
        }

        return res;
    }

    /**
     * 输入一个链表，输出该链表中倒数第k个节点。为了符合大多数人的习惯，本题从1开始计数，即链表的尾节点是倒数第1个节点。
     * <p>
     * 例如，一个链表有 6 个节点，从头节点开始，它们的值依次是 1、2、3、4、5、6。这个链表的倒数第 3 个节点是值为 4 的节点。
     * <p>
     *  
     * <p>
     * 示例：
     * <p>
     * 给定一个链表: 1->2->3->4->5, 和 k = 2.
     * <p>
     * 返回链表 4->5.
     * <p>
     * 链接：https://leetcode.cn/problems/lian-biao-zhong-dao-shu-di-kge-jie-dian-lcof
     * <p>
     * 思路：双指针
     * 双指针：同向快慢指针、双向指针。这题我们用到的是同向快慢指针
     */
    public ListNode getKthFromEnd(ListNode head, int k) {
        ListNode fast = head;
        ListNode slow = head;

        while (fast != null && k > 0) {
            fast = fast.next;
            k--;
        }

        while (fast != null) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }


    /**
     * 剑指 Offer 25. 合并两个排序的链表
     *
     * @return
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode pre = new ListNode();
        ListNode head = pre;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                pre.next = l1;
                l1 = l1.next;
            } else {
                pre.next = l2;
                l2 = l2.next;
            }
            pre = pre.next;
        }

        pre.next = l1 != null ? l1 : l2;

        return head.next;
    }


    /**
     * 剑指 Offer 52. 两个链表的第一个公共节点
     * <p>
     * https://leetcode.cn/problems/liang-ge-lian-biao-de-di-yi-ge-gong-gong-jie-dian-lcof/
     */
    static ListNode getIntersectionNode(ListNode headA, ListNode headB) {

        //可简化成如下：
        if (headA == null || headB == null) {
            return null;
        }
        ListNode pA = headA, pB = headB;
        //这块不会死循环，PA/PB 没有交集时会走到 都为null的时候跳出循环
        while (pA != pB) {
            pA = pA == null ? headB : pA.next;
            pB = pB == null ? headA : pB.next;
        }
        return pA;
    }


    /**
     * 剑指 Offer 21. 调整数组顺序使奇数位于偶数前面
     * <p>
     * 输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有奇数在数组的前半部分，所有偶数在数组的后半部分。
     * <p>
     * 示例：
     * 输入：nums = [1,2,3,4]
     * 输出：[1,3,2,4]
     * 注：[3,1,2,4] 也是正确的答案之一。
     * <p>
     * <p>
     * 链接：https://leetcode.cn/problems/diao-zheng-shu-zu-shun-xu-shi-qi-shu-wei-yu-ou-shu-qian-mian-lcof
     * <p>
     * 思路：相向指针
     */
    public int[] exchange(int[] nums) {
        int len = nums.length;

        int j = len - 1;
        int i = 0;
        while (i < j) {
            //i为偶数 、 j为奇数，互换
            if (nums[i] % 2 == 0 && nums[j] % 2 == 1) {
                int tmp = nums[i];
                nums[i] = nums[j];
                nums[j] = tmp;
                i++;
                j--;
            } else if (nums[i] % 2 == 0) {
                j--;
            } else {
                i++;
            }
        }

        return nums;
    }

    /**
     * 剑指 Offer 57. 和为s的两个数字
     * <p>
     * 输入一个递增排序的数组和一个数字s，在数组中查找两个数，使得它们的和正好是s。如果有多对数字的和等于s，则输出任意一对即可。
     * <p>
     * 示例 1：
     * 输入：nums = [2,7,11,15], target = 9
     * 输出：[2,7] 或者 [7,2]
     * <p>
     * 示例 2：
     * 输入：nums = [10,26,30,31,47,60], target = 40
     * 输出：[10,30] 或者 [30,10]
     * <p>
     *
     *     限制：
     *
     * 1 <= nums.length <= 10^5
     * 1 <= nums[i] <= 10^6
     *
     * 链接：https://leetcode.cn/problems/he-wei-sde-liang-ge-shu-zi-lcof
     *
     * 思路：相向指针，成立原因是数组为递增数组。
     *
     * 优化点：防止内存溢出：target 比较时，可采用target - num[i] < num[j]
     */
    public int[] twoSum(int[] nums, int target) {

        int len = nums.length;
        int i = 0;
        int j = len - 1;

        while (i < j) {
            if (nums[i] + nums[j] > target) {
                j--;
            } else if (nums[i] + nums[j] == target) {
                return new int[]{nums[i], nums[j]};
            } else {
                i++;
            }
        }
        return null;
    }

    /**
     * 剑指 Offer 58 - I. 翻转单词顺序
     *
     * 输入一个英文句子，翻转句子中单词的顺序，但单词内字符的顺序不变。为简单起见，标点符号和普通字母一样处理。例如输入字符串"I am a student. "，则输出"student. a am I"。
     *
     * 示例 1：
     * 输入: "the sky is blue"
     * 输出: "blue is sky the"
     *
     * 示例 2：
     * 输入: "  hello world!  "
     * 输出: "world! hello"
     * 解释: 输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
     *
     * 示例 3：
     * 输入: "a good   example"
     * 输出: "example good a"
     * 解释: 如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
     *
     * 链接：https://leetcode.cn/problems/fan-zhuan-dan-ci-shun-xu-lcof/solution/mian-shi-ti-58-i-fan-zhuan-dan-ci-shun-xu-shuang-z/
     *
     *
     * 思路：标记双指针，
     * 从尾部遍历：i 去定位单词头部，j去定位单词尾部
     **/
    public String reverseWords(String s) {
//        s = s.trim();
//
//        StringBuilder sb = new StringBuilder();
//        int j = s.length() - 1;
//        int i = j;
//        while (i >= 0) {
//            //1、从尾部开始找到第一个' '，截取后拼接。
//            while (i >= 0) {
//                if (s.charAt(i) == ' ') {
//                    sb.append(s.substring(i + 1, j + 1));
//                    sb.append(' ');
//                    break;
//                }
//                if (i == 0) {
//                    sb.append(s.substring(i, j + 1));
//                }
//                i -- ;
//            }
//            //2、继续查到下一个不为' '的角标，复制给j；
//            while (i >= 0) {
//                if (s.charAt(i) != ' '){
//                    j = i;
//                    break;
//                }
//                i--;
//            }
//        }
//
//        return sb.toString();


        //可简化成如下代码：思路一样，只是代码简化
        s = s.trim(); // 删除首尾空格
        int j = s.length() - 1, i = j;
        StringBuilder res = new StringBuilder();
        while (i >= 0) {
            while (i >= 0 && s.charAt(i) != ' ') i--; // 搜索首个空格
            res.append(s.substring(i + 1, j + 1)); // 添加单词
            res.append(' ');
            while (i >= 0 && s.charAt(i) == ' ') i--; // 跳过单词间空格
            j = i; // j 指向下个单词的尾字符
        }
        return res.toString().trim(); // 转化为字符串并返回

    }
}
