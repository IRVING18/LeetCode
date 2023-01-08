package com.jiejiedai.api.leetcode;

import java.util.LinkedList;

public class SwordTest {
    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    /**
     * 合并链表
     * 1->2->4
     * 1->3->4
     */
    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode result = new ListNode(0);
        ListNode cur = result;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                cur.next = l1;
                l1 = l1.next;
            } else {
                cur.next = l2;
                l2 = l2.next;
            }
            cur = cur.next;
        }
        cur.next = l1 == null ? l2 : l1;

        return result.next;
    }


    public static void main(String[] args) {
        ListNode listNode23 = new ListNode(4);
        ListNode listNode22 = new ListNode(3);
        ListNode listNode2 = new ListNode(1);
        listNode2.next = listNode22;
        listNode22.next = listNode23;

        ListNode listNode13 = new ListNode(4);
        ListNode listNode12 = new ListNode(2);
        ListNode listNode1 = new ListNode(1);
        listNode1.next = listNode12;
        listNode12.next = listNode13;
        LinkedList<Integer> integers = new LinkedList<>();

        ListNode listNode = mergeTwoLists(listNode1, listNode2);

        while (listNode != null) {
            System.out.print(listNode.val + " ");
            listNode = listNode.next;
        }
    }

//    class MinStack {
//        List<Integer> data, min;
//
//        /**
//         * initialize your data structure here.
//         */
//        public MinStack() {
//            data = new LinkedList<Integer>();
//            min = new LinkedList<Integer>();
//        }
//
//        public void push(int x) {
//            data.add(x);
//            if (min.isEmpty() || min.get(min.size() - 1) > x) {
//                min.add(x);
//            }
//        }
//
//        public void pop() {
//            if (data.isEmpty()) {
//                return;
//            }
//            int pop = data.remove(data.size() - 1);
//            if (pop == min.get(min.size() - 1)) {
//                min.remove(min.size() - 1);
//            }
//
//        }
//
//        public int top() {
//            return data.get(data.size() - 1);
//        }
//
//        public int min() {
//            if (min.isEmpty()){
//                return null;
//            }
//            return min.get(min.size() - 1);
//        }
//    }
}
