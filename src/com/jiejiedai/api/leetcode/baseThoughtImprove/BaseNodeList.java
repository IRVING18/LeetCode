package com.jiejiedai.api.leetcode.baseThoughtImprove;

import com.jiejiedai.api.leetcode.ListNode;
import com.jiejiedai.api.leetcode.Node;

import java.util.HashMap;
import java.util.Stack;

/**
 * 链表
 * 1、pre 、cur 、next
 * 2、涉及返回就 存head 用于返回
 * 3、双指针：同向、对向
 */
public class BaseNodeList {


    /**
     * 反转链表
     * 定义一个函数，输入一个链表的头节点，反转该链表并输出反转后链表的头节点。
     * <p>
     * https://leetcode.cn/problems/fan-zhuan-lian-biao-lcof/
     * <p>
     * 思路：
     * 1、pre 、cur 、next 死记吧，做了无数遍了。
     */
    public static ListNode reverseList(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        while (cur.next != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }

        return pre;
    }

    /**
     * 剑指 Offer 06. 从尾到头打印链表
     * 输入一个链表的头节点，从尾到头反过来返回每个节点的值（用数组返回）。
     * <p>
     * https://leetcode.cn/problems/cong-wei-dao-tou-da-yin-lian-biao-lcof/?envType=study-plan&id=lcof&plan=lcof&plan_progress=cafihze
     * <p>
     * 思路一：
     * 通过栈的特性操作：
     * 1、将node 遍历一遍，add到栈中
     * 2、将栈中数据pop 出来就是反转后的了
     *
     * @param head
     * @return
     */
    public static int[] reversePrint(ListNode head) {
        Stack<Integer> stack = new Stack<>();

        ListNode cur = head;
        while (cur != null) {
            stack.add(cur.val);
            cur = cur.next;
        }

        int[] arr = new int[stack.size()];
        int i = 0;
        while (!stack.isEmpty()) {
            arr[i] = stack.pop();
            i++;
        }
        return arr;
    }

    /**
     * 剑指 Offer 35. 复杂链表的复制
     * <p>
     * 请实现 copyRandomList 函数，复制一个复杂链表。在复杂链表中，每个节点除了有一个 next 指针指向下一个节点，还有一个 random 指针指向链表中的任意节点或者 null。
     * <p>
     * https://leetcode.cn/problems/fu-za-lian-biao-de-fu-zhi-lcof/
     * <p>
     * <p>
     * 链表涉及返回就 存一份 head 用于返回
     * <p>
     * 思路一：迭代+节点拆分
     * 1、遍历node节点,创建新节点，拆到当前节点和当前节点.next中间，newNode().next = node.next,  node.next = newNode 上，
     * 2、再遍历node节点，将newNode 的 random节点，指向random.next节点。
     * 3、拆分节点，newNode = node.next； node.next = node.next.next； newNode.next = nodeNew.next.next
     * <p>
     * 思路二：回溯+哈希表
     * 注意：存储到map的时机，要在回溯之前
     * 1、创建HashMap，将node当成key，存储newNode节点
     * 2、创建newNode节点前，先判断map中是否已创建node的复制节点
     * 3、newNode.next 以回溯方式获取下一个节点的复制节点
     */
    public Node copyRandomList(Node head) {

        //思路一：
//
//        if (head == null) {
//            return null;
//        }
//
//        //1、遍历节点，添加newNode节点
//        for (Node node = head; node != null; node = node.next.next) {
//            Node newNode = new Node(node.val);
//            newNode.next = node.next;
//            node.next = newNode;
//        }
//
//        //2、遍历节点，将random指向调整
//        for (Node node = head; node != null; node = node.next.next) {
//            Node newNode = node.next;
//            newNode.random = node.random != null ? node.random.next : null;
//        }
//
//        //3、拆分节点
//        Node returnNode = head.next;
//        for (Node node = head; node != null; node = node.next) {
//            Node newNode = node.next;
//            node.next = node.next.next;
//            newNode.next = newNode.next != null ? newNode.next.next : null;
//        }
//
//        return returnNode;


        //思路二：

        if (head == null) {
            return null;
        }

        if (map.containsKey(head)) {
            return map.get(head);
        }

        Node newNode = new Node(head.val);
        map.put(head, newNode);
        newNode.next = copyRandomList(head.next);
        newNode.random = copyRandomList(head.random);
        return newNode;
    }

    private HashMap<Node, Node> map = new HashMap<>();


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
//        if (headA == null || headB == null) {
//            return null;
//        }
//        ListNode pre1A = headA;
//        ListNode pre1B = headB;
//        ListNode pre2A = headA;
//        ListNode pre2B = headB;
//
//        ListNode cur1 = pre1A;
//        ListNode cur2 = pre2B;
//
//        while (cur1 != null && cur2 != null) {
//
//            if (cur1 == cur2) {
//                return cur1;
//            }
//            if (pre1A != null) {
//                cur1 = pre1A;
//                pre1A = pre1A.next;
//            } else {
//                cur1 = pre1B;
//                pre1B = pre1B == null ? null : pre1B.next;
//            }
//            if (pre2B != null) {
//                cur2 = pre2B;
//                pre2B = pre2B.next;
//            } else{
//                cur2 = pre2A;
//                pre2A = pre2A==null ? null: pre2A.next;
//            }
//
//        }
//
//        return null;


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


    public static void main(String[] args) {
        getIntersectionNode(new ListNode(1), new ListNode(2));
    }
}
