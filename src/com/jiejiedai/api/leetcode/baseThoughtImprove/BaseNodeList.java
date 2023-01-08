package com.jiejiedai.api.leetcode.baseThoughtImprove;

import com.jiejiedai.api.leetcode.ListNode;
import com.jiejiedai.api.leetcode.Node;

import java.util.HashMap;
import java.util.Stack;

/**
 * 链表
 * 1、pre 、cur 、next
 * 2、涉及返回就 存head 用于返回
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
     *
     *     链表涉及返回就 存一份 head 用于返回
     *
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



}
