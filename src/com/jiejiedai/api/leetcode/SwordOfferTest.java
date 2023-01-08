package com.jiejiedai.api.leetcode;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.*;

/**
 * 1.1、反转链表
 * 1.2、复杂链表复制
 * 1.3、合并有序链表
 * 1.4、删除链表一个节点
 * 4、二分查找
 * 5、两个队列实现栈
 * 6、两个栈实现队列
 * 7、判断链表是否成环，找到成环交点
 * 斐波那契数列
 */
public class SwordOfferTest {

    public static void main(String[] args) {
//        StringBuilder s= new StringBuilder();
//        for (int i = 0; i < 100000000; i++) {
//            s.append(i);
//            String s0 = s.toString().intern();
//
//        }
//        Stack<String> stack = new Stack<>();
//        Queue<String> queue = new LinkedList<>();
//        System.out.println("---------String with new--------");
//        collectWeakReference(new String("dasfsafsafsafsa"));
//        System.out.println("---------String with literal--------");
//        collectWeakReference("dsafdsafxcdfeghg");

//        int i = 0100;
//        System.out.println(i);

        int[] arr = {0, 0, 2, 4, 4, 4, 4, 4, 4, 6, 6, 8};
//        System.out.println(binarySearchLeft(arr, 3));
//        System.out.println(binarySearchRight(arr, 3));
        System.out.println(binarySearch(arr, 8, true));
        System.out.println(binarySearch(arr, 8, false));
        System.out.println(binarySearch(arr, 7, true));
        System.out.println(binarySearch(arr, 7, false));
        System.out.println(binarySearch(arr, 9, true));
        System.out.println(binarySearch(arr, 9, false));
        System.out.println(binarySearch(arr, -1, true));
        System.out.println(binarySearch(arr, -1, false));
        System.out.println(binarySearch(arr, 2, true));
        System.out.println(binarySearch(arr, 2, false));
        System.out.println(binarySearch(arr, 4, true));
        System.out.println(binarySearch(arr, 4, false));
        SwordOfferTest swordOfferTest = new SwordOfferTest();
        swordOfferTest.binarySearch();
    }

    /**
     * 1.1、反转链表
     * 1、创建prev = null
     * 2、将next = current.next;保存下来
     * 3、current.next = prev ，current就被截取下来了
     * 4、prev = current；
     * 5、current = next；
     *
     * @param head
     * @return
     */
    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode current = head;
        while (current != null) {
            ListNode tmp = current.next;
            current.next = prev;
            prev = current;
            current = tmp;
        }
        return prev;

//        ListNode prev = null;
//        ListNode current = head;
//        while (current != null) {
//            ListNode next = current.next;
//            current.next = prev;
//            prev = current;
//            current = next;
//        }
//        return prev;
//

        // Stack<ListNode> stack = new Stack<ListNode>();

        // ListNode tmp = head;
        // while(tmp !=null){
        //     stack.push(tmp);
        //     tmp = tmp.next;
        // }
        // if (stack.isEmpty()){
        //     return null;
        // }

        // ListNode result = stack.pop();
        // ListNode reTmp = result;
        // while(!stack.isEmpty()){
        //     reTmp.next = stack.pop();
        //     reTmp = reTmp.next;
        //     reTmp.next = null;
        // }

        // return result;

//        ListNode prev = null;
//        ListNode curr = head;
//        while (curr != null) {
//            ListNode next = curr.next;
//            curr.next = prev;
//            prev = curr;
//            curr = next;
//        }
//        return prev;
    }

    /**
     * 1.2、复杂链表复制
     * 1、将A->B->C 复制成 A->A1->B->B1->C->C1
     * 2、将A.Random.next 设置给A1
     * 3、将A1->B1->C1 拆分开
     */
    public Node copyRandomList(Node head) {
//        if (head == null) {
//            return null;
//        }
//        for (Node current = head; current != null; current = current.next.next) {
//            Node newNode = new Node(current.val);
//            newNode.next = current.next;
//            current.next = newNode;
//        }
//
//        for (Node current = head; current != null; current = current.next.next) {
//            head.next.random = current.random != null ? current.random.next : null;
//        }
//
//        Node reNode = head.next;
//        for (Node current = head; current != null; current = current.next) {
//            Node newNode = head.next;
//            //老的链表也拆出来
//            head.next = head.next.next;
//            //新的链表拆出来
//            newNode.next = newNode.next != null ? newNode.next.next : null;
//        }
//        return reNode;
//

        if (head == null) {
            return null;
        }
        //1、将A->B->C 变成 A->A1->B->B1->C->C1
        //node.next.next就是跨两个遍历
        for (Node node = head; node != null; node = node.next.next) {
            Node newNode = new Node(node.val);
            //将新newNode插到node后边
            newNode.next = node.next;
            node.next = newNode;
        }
        //2、将A1/B1/C1的Random赋值
        for (Node node = head; node != null; node = node.next.next) {
            //获取新node
            Node newNode = node.next;
            newNode.random = node.random != null ? node.random.next : null;
        }
        //3、拆分A->B-C  A1->B1->C1
        Node headNext = head.next;
        for (Node node = head; node != null; node = node.next) {
            Node newNode = node.next;
            node.next = node.next.next;
            newNode.next = newNode.next != null ? newNode.next.next : null;
        }
        return headNext;
    }

    /**
     * 1.3、合并有序链表
     * https://leetcode-cn.com/problems/he-bing-liang-ge-pai-xu-de-lian-biao-lcof/submissions/
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode reNode = new ListNode(0);
        ListNode head = reNode;
        while (l1 != null && l2 != null) {
            if (l1.val > l2.val) {
                reNode.next = l2;
                l2 = l2.next;
            } else {
                reNode.next = l1;
                l1 = l1.next;
            }
            reNode = reNode.next;
        }
        if (l1 != null) {
            reNode.next = l1;
        }
        if (l2 != null) {
            reNode.next = l2;
        }
        return head.next;
    }

    /**
     * 1.4、删除链表一个节点
     */
    public ListNode deleteNode(ListNode head, int val) {
        ListNode current = new ListNode(0);
        current.next = head;
        ListNode result = current;
        while (current != null) {
            ListNode next = current.next;
            if (next.val == val) {
                current.next = current.next.next;
                break;
            }
            current = current.next;
        }
        return result.next;


//        if (head.val == val) {
//            return head.next;
//        }
//        ListNode pre = head, cur = head.next;
//        while (cur != null && cur.val != val) {
//            pre = cur;
//            cur = cur.next;
//        }
//        if (cur != null) {
//            pre.next = cur.next;
//        }
//        return head;
    }

    /**
     * 4、二分查找：普通查找
     * https://leetcode-cn.com/problems/binary-search/solution/er-fen-cha-zhao-xiang-jie-by-labuladong/
     */
    public static int binarySearch(int[] arr, int target) {
        int start = 0;
        int end = arr.length - 1;
        int reIndex = -1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (arr[mid] > target) {
                end = mid - 1;
            } else if (arr[mid] < target) {
                start = mid + 1;
            } else if (arr[mid] == target) {
                reIndex = mid;
                break;
            }
        }
        return reIndex;
    }

    /**
     * 4、二分查找：找左边界
     * https://leetcode-cn.com/problems/binary-search/solution/er-fen-cha-zhao-xiang-jie-by-labuladong/
     */
    public static int binarySearchLeft(int[] arr, int target) {
        int start = 0;
        int end = arr.length - 1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (arr[mid] > target) {
                end = mid - 1;
            } else if (arr[mid] < target) {
                start = mid + 1;
            } else if (arr[mid] == target) {
                end = mid - 1;
            }
        }
        if (start >= arr.length || arr[start] != target) {
            return -1;
        }
        return start;
    }

    /**
     * 4、二分查找：找右边界
     * https://leetcode-cn.com/problems/binary-search/solution/er-fen-cha-zhao-xiang-jie-by-labuladong/
     */
    public static int binarySearchRight(int[] arr, int target) {
        int start = 0;
        int end = arr.length - 1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (arr[mid] > target) {
                end = mid - 1;
            } else if (arr[mid] < target) {
                start = mid + 1;
            } else if (arr[mid] == target) {
                start = mid + 1;
            }
        }
        if (end < 0 || arr[end] != target) {
            return -1;
        }
        return end;
    }

    /**
     * 4、二分查找：找两个边界，自创
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
            if (nums[mid] < target) {
                start = mid + 1;
            } else if (nums[mid] > target) {
                end = mid - 1;
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

    private int binarySearch() {
        return 0;
    }

    /**
     * 5、队列实现栈
     * https://leetcode-cn.com/problems/implement-stack-using-queues/
     */
    class TwoQueueToStack extends SwordOfferTest {
        Queue<Integer> queueIn;
        Queue<Integer> queueOut;


        /**
         * Initialize your data structure here.
         */
        public TwoQueueToStack() {
            queueIn = new LinkedList<Integer>();
            queueOut = new LinkedList<Integer>();
        }

        /**
         * Push element x onto stack.
         */
        public void push(int x) {
            queueIn.offer(x);
            while (!queueOut.isEmpty()) {
                queueIn.offer(queueOut.poll());
            }
            Queue<Integer> tmp = queueIn;
            queueIn = queueOut;
            queueOut = tmp;
        }

        /**
         * Removes the element on top of the stack and returns that element.
         */
        public int pop() {
            return queueOut.poll();
        }

        /**
         * Get the top element.
         */
        public int top() {
            return queueOut.peek();
        }

        /**
         * Returns whether the stack is empty.
         */
        public boolean empty() {
            return queueOut.isEmpty();
        }
    }

    /**
     * 6、栈实现队列
     * https://leetcode-cn.com/problems/implement-queue-using-stacks/
     */
    class TwoStackToQueue {
        Stack<Integer> stackIn;
        Stack<Integer> stackOut;

        public TwoStackToQueue() {
            stackIn = new Stack<>();
            stackOut = new Stack<>();
        }

        public void offer(int x) {
            stackIn.push(x);
        }

        public Integer poll() {
            if (stackOut.isEmpty()) {
                while (!stackIn.isEmpty()) {
                    stackOut.push(stackIn.pop());
                }
            }
            return stackOut.isEmpty() ? -1 : stackOut.pop();
        }

        public Integer peek() {
            if (stackOut.isEmpty()) {
                while (!stackIn.isEmpty()) {
                    stackOut.push(stackIn.pop());
                }
            }
            return stackOut.isEmpty() ? -1 : stackOut.peek();
        }

        public boolean isEmpty() {
            return stackIn.isEmpty() && stackOut.isEmpty();
        }
    }

    /**
     * 7、链表是否成环：快慢指针，空间复杂度O(1)
     * https://leetcode-cn.com/problems/linked-list-cycle/
     *
     * @param head
     * @return
     */
    private static boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        ListNode slow = head;
        ListNode fast = head.next;
        while (slow != fast) {
            if (fast == null || fast.next == null) {
                return false;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        return true;
    }

    /**
     * 7、链表是否成环：hash算法，空间复杂度O(n)
     * https://leetcode-cn.com/problems/linked-list-cycle/
     *
     * @param head
     * @return
     */
    private static boolean hasCycleByHash(ListNode head) {
        Set<ListNode> seen = new HashSet<ListNode>();
        ListNode current = head;
        while (current != null) {
            if (!seen.add(current)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    /**
     * 7、返回环形链表的入环口，空间复杂度O(1)
     * https://leetcode-cn.com/problems/linked-list-cycle-ii/solution/142-huan-xing-lian-biao-ii-jian-hua-gong-shi-jia-2/
     */
    public ListNode detectCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            //说明有环
            if (slow == fast) {
                //从环开始和从头开始，一次移动一个就会相遇在入环口上。
                ListNode index1 = slow;
                ListNode index2 = head;
                while (index1 != index2) {
                    index1 = index1.next;
                    index2 = index2.next;
                }
                return index1;
            }
        }
        return null;
    }

    /**
     * 7、返回环形链表的入环口，hash算法，空间复杂度O(n)
     * https://leetcode-cn.com/problems/linked-list-cycle-ii/
     */
    public ListNode detectCycleByHash(ListNode head) {
        Set<ListNode> seen = new HashSet<ListNode>();
        ListNode current = head;
        while (current != null) {
            if (!seen.add(current)) {
                return current;
            }
            current = current.next;
        }
        return null;
    }

    /**
     * 统计一个数字在排序数组中出现的次数。
     * <p>
     * 重要条件：排序数组，也就是有序数组，所以选择二分法
     * 1、找到target在数组中的第一个出现位置start,再找到数组中最后一次位置end
     * 2、总数= end - start + 1
     *
     * @param nums
     * @param target
     * @return
     */
    public static int search(int[] nums, int target) {
        //1、找到target数组中第一次出现的角标
        int startIndex = binarySearch(nums, target, true);
        //没找到直接返回0
        if (startIndex == -1) {
            return 0;
        }
        //2、找到target数组中最后一次出现的角标
        int endIndex = binarySearch(nums, target, false);
        return endIndex - startIndex + 1;
    }


    /**
     * 一个长度为n-1的递增排序数组中的所有数字都是唯一的，
     * 并且每个数字都在范围0～n-1之内。在范围0～n-1内的n个数字中有且只有一个数字不在该数组中，请找出这个数字。
     */
    public static int binarySearchNotIn(int[] nums) {
        int len = nums.length;
        int start = 0;
        int end = len - 1;
        while (start <= end) {
            int mid = (start + end) / 2;
            //[0,1,2,4]
            // => 3
            if (nums[mid] == mid) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        return start;
    }

    /**
     * 在一个 n * m 的二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。
     * 请完成一个高效的函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
     * <p>        int[][] arr = new int[][]
     * {{1,4,7,11,15},{2,5,8,12,19},{3,6,9,16,22},{10,13,14,17,24},{18,21,23,26,30}};
     * 1、二分查找横列位置，
     * 2、二分查找纵列位置
     */
    public static boolean findNumFrom2DArray(int[][] matrix, int target) {
        int n = matrix.length;
        int m = matrix[0].length;

        int nStart = 0;
        int nEnd = n - 1;
        while (nStart <= nEnd) {
            int nMid = (nStart + nEnd) / 2;
            int nMidValue = matrix[nMid][0];
            if (nMidValue < target) {
                nStart = nMid + 1;
            } else if (nMidValue > target) {
                nEnd = nMid - 1;
            } else {
                return true;
            }
        }
        //上边数组[0][0]位置就是最小数，如果没找到就直接返回
        if (nStart - 1 < 0) {
            return false;
        }
        //n横列的坐标确定
        int nIndex = nStart - 1;
        int mStart = 0;
        int mEnd = m - 1;
        while (mStart <= mEnd) {
            int mMid = (mStart + mEnd) / 2;
            int mMidValue = matrix[nIndex][mMid];
            if (mMidValue < target) {
                mStart = mMid + 1;
            } else if (mMidValue > target) {
                mEnd = mMid - 1;
            } else {
                return true;
            }
        }
        return false;
    }


    private static void collectWeakReference(String obj) {
        ReferenceQueue<String> rq = new ReferenceQueue<>();
        Reference<String> r = new WeakReference<>(obj, rq);
        obj = null;
        Reference rf;
        int gccount = 10;
        //一次System.gc()并不一定会回收A，所以要多试几次
        while ((rf = rq.poll()) == null && gccount >= 0) {
            System.gc();
            gccount--;
        }
        System.out.println(rf);
        if (rf != null) {//如果对象被回收则弱引用会加入引用队列
            //引用指向的对象已经被回收，存入引入队列的是弱引用本身,所以这里最终返回null
            System.out.println(rf.get());
        }
    }

}

