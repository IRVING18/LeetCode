package com.jiejiedai.api.leetcode.baseThoughtImprove;

import java.util.*;

/**
 * 数组，队列，栈
 * <p>
 * 注意：
 * 1、有序数据 一定要想到 二分法
 * 队列的操作符：
 *      offer() 添加
 *      poll() 获取并删除
 *      peek() 只获取
 * 栈操作符：
 *      push() 压栈
 *      pop() 弹栈
 *      peek() 获取栈顶
 *
 * 2、思路
 *  利用辅助栈操作 validateStackSequences
 *  双栈实现：min O(1) 的栈
 *  双队列（一个双向队列）实现：max O(1)的队列
 *
 * 3、重复数据
 *      => Hash
 *      => 数组标记
 *      => 摩尔投票法
 */
public class BaseArrayQueueStack {


    public int majorityElement(int[] nums) {
        //hash
//        HashMap<Integer, Integer> map = new HashMap<>();
//
//        for (int i = 0; i < nums.length; i++) {
//            if (map.containsKey(nums[i])) {
//                map.put(nums[i], map.get(nums[i]) + 1);
//            } else {
//                map.put(nums[i], 1);
//            }
//            if (map.get(nums[i]) > nums.length / 2) {
//                return nums[i];
//            }
//        }
//        return -1;

        //排序法：排序结束，数组中间的数一定是众数。

        //摩尔投票法：
        //推论一：若有众数，那么记众数 +1 ，非众数 -1 ，那么所有数字的票数一定 > 0
        //推论二：若前 a 个数字的 票数 = 0，那么剩余的数 的票数和 一定还>0，而且众数 在剩余的数中 仍是众数。

        //根据上边的结论，
        // 1、设众数为 i=0 的数
        // 2、计算票数，== 0 的时候，就设i + 1为新的众数
        // 3、直到数组结束

        int x = 0;
        int votes = 0;
        for (int i = 0; i < nums.length; i++) {
            if (votes == 0) {
                x = nums[i];
            }
            if (x == nums[i]) {
                votes++;
            } else {
                votes--;
            }
        }
        return x;
    }

    /**
     * 剑指 Offer 09. 用两个栈实现队列
     * 思路：
     * 1、一个栈stackAdd用来添加数据
     * 2、第二个栈stackPop用来pop数据
     * 3、pop前，若stackPop为空，那么先将stackAdd栈中数据拿出来添加到stackPop栈中
     * 4、若stackPop中不为空，直接pop出来。
     * <p>
     * https://leetcode.cn/problems/yong-liang-ge-zhan-shi-xian-dui-lie-lcof/?envType=study-plan&id=lcof&plan=lcof&plan_progress=cafihze
     */
    static class CQueue {
        Stack<Integer> stackAdd;
        Stack<Integer> stackPop;

        public CQueue() {
            stackAdd = new Stack<Integer>();
            stackPop = new Stack<Integer>();
        }

        public void appendTail(int value) {
            synchronized (CQueue.class) {
                stackAdd.add(value);
            }
        }

        public int deleteHead() {

            synchronized (CQueue.class) {
                if (stackPop.isEmpty()) {
                    while (!stackAdd.isEmpty()) {
                        stackPop.add(stackAdd.pop());
                    }
                }
            }

            if (!stackPop.isEmpty()) {
                return stackPop.pop();
            }

            return -1;
        }
    }

    /**
     * 剑指 Offer 30. 包含min函数的栈
     * 定义栈的数据结构，请在该类型中实现一个能够得到栈的最小元素的 min 函数在该栈中，调用 min、push 及 pop 的时间复杂度都是 O(1)。
     * <p>
     * 思路一：
     * 两个栈保持相同size相同，存储的A数据大于StackMin top数据时，再存一次StackMin top数据。
     * 1、双栈实现，stackAll栈存储所有数据，stackMin栈存储最小数据，
     * 2、当存储数据a时，若a < stackMin栈顶数据，直接存进去。若a > stackMin栈顶数据，那么再存一份stackMin栈顶数据
     * 3、pop数据时，两个栈同时pop数据
     * 4、min数据时，获取StackMin的top数据
     * <p>
     * <p>
     * 思路二：
     * 和思路一差别在于StackMin的处理上，
     * stackMin不冗余存储top数据，若A大于StackMin top数据，那就不存储。所以StackMin的数据量比StackAll小。
     * 那么
     * 1、在pop数据时，比较StackAll pop出来的数据等于StackMin的top数据。那么就把StackMin top数据pop出来。
     * 2、在add数据时，若A <= stackMin的top数据，就存入stackMin中。注意是小于等于，等于时也存一次。
     * https://leetcode.cn/problems/bao-han-minhan-shu-de-zhan-lcof/
     */
    class MinStack {
//      方法一：插入时操作min栈，保证min和主栈数量一致，方便pop
//        Stack<Integer> stackAll;
//        Stack<Integer> stackMin;
//
//        public MinStack() {
//            stackAll = new Stack<>();
//            stackMin = new Stack<>();
//        }
//
//        public void push(int x) {
//            stackAll.add(x);
//            if (stackMin.isEmpty()) {
//                stackMin.add(x);
//                return;
//            }
//            if (x < stackMin.peek()) {
//                stackMin.add(x);
//            } else {
//                stackMin.add(stackMin.peek());
//            }
//        }
//
//        public void pop() {
//            stackMin.pop();
//            stackAll.pop();
//        }
//
//        public int top() {
//            return stackAll.peek();
//        }
//
//        public int min() {
//            return stackMin.peek();
//        }

//      方法二：插入时，<= 时插入，pop时判断是否==min.peek()
        Stack<Integer> stackAll;
        Stack<Integer> stackMin;

        public MinStack() {
            stackAll = new Stack<>();
            stackMin = new Stack<>();
        }

        public void push(int x) {
            stackAll.add(x);
            if (stackMin.isEmpty() || x <= stackMin.peek()) {
                stackMin.add(x);
            }
        }

        public void pop() {
            int res = stackAll.pop();
            if (res == stackMin.peek()) {
                stackMin.pop();
            }
        }

        public int top() {
            return stackAll.peek();
        }

        public int min() {
            return stackMin.peek();
        }
    }
    /**
     * 面试题59 - II. 队列的最大值
     * 请定义一个队列并实现函数 max_value 得到队列里的最大值，要求函数max_value、push_back 和 pop_front 的均摊时间复杂度都是O(1)。
     *
     * 若队列为空，pop_front 和 max_value 需要返回 -1
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/dui-lie-de-zui-da-zhi-lcof
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * 思路：https://leetcode.cn/problems/dui-lie-de-zui-da-zhi-lcof/solution/ru-he-jie-jue-o1-fu-za-du-de-api-she-ji-ti-by-z1m/
     * 和楼上min栈的相似，这题是队列的操作。
     * 1、初始化一个maxQueue，和一个主队列
     * 2、在offer时，while判断队尾数据是否小于 x，若小于就都出队列。循环结束将x插入队尾。（需要操作队尾，所以用双向队列deque）
     * 3、在poll时，判断主队列poll的数据 == maxQueue的队首数据，若相等maxQueue.pollFirst()
     */
    class MaxQueue {
        Queue<Integer> queue;
        Deque<Integer> maxDeque;
        public MaxQueue() {
            queue = new LinkedList<>();
            maxDeque = new LinkedList<>();
        }

        public int max_value() {
            if (maxDeque.isEmpty()){
                return -1;
            }
            return maxDeque.peekFirst();
        }

        public void push_back(int value) {
            queue.offer(value);
            //2、在插入时，while判断队尾数据是否小于 x，若小于就都出队列。循环结束将x插入队尾。（需要操作队尾，所以用双向队列deque）
            while (!maxDeque.isEmpty() && maxDeque.peekLast() < value){
                maxDeque.pollLast();
            }
            maxDeque.offerLast(value);
        }

        public int pop_front() {
            if (queue.isEmpty()) {
                return -1;
            }
            int res = queue.poll();
            //3、在poll时，判断主队列poll的数据 == maxQueue的队首数据，若相等maxQueue.pollFirst()
            if (maxDeque.peekFirst() == res) {
                maxDeque.pollFirst();
            }
            return res;
        }
    }

    /**
     * 217. 存在重复元素
     * <p>
     * 给你一个整数数组 nums 。如果任一值在数组中出现 至少两次 ，返回 true ；如果数组中每个元素互不相同，返回 false 。
     * <p>
     * https://leetcode.cn/problems/contains-duplicate/?envType=study-plan&id=shu-ju-jie-gou-ru-men&plan=data-structures&plan_progress=cdw3acd
     * <p>
     * 思路一：排序 再比对相邻
     * <p>
     * 思路二：哈希，存到HashSet里
     * <p>
     * 思路三：双层遍历
     */
    public boolean containsDuplicate(int[] nums) {
//        Arrays.sort(nums);
//
//        for (int i = 0; i < nums.length - 1; i++) {
//            if (nums[i] == nums[i + 1]) {
//                return true;
//            }
//        }
//        return false;

        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (!set.add(nums[i])) {
                return true;
            }
        }
        return false;
    }

    /**
     * 剑指 Offer 03. 数组中重复的数字
     * <p>
     * 在一个长度为 n 的数组 nums 里的所有数字都在 0～n-1 的范围内。数组中某些数字是重复的，但不知道有几个数字重复了，也不知道每个数字重复了几次。请找出数组中任意一个重复的数字。
     * <p>
     * 链接：https://leetcode.cn/problems/shu-zu-zhong-zhong-fu-de-shu-zi-lcof
     * <p>
     * 常规思路：排序、哈希。和楼上containsDuplicate一样
     * <p>
     * 特殊思路：时间复杂度o(n) 空间复杂度o(1)的解法
     * 1、nums 里的所有数字都在 0～n-1 ，也就是说k = nums[i] ,在nums[k]并不会角标越界。
     * 2、故利用这个性质，k = num[i] ,num[k] -= n(保证一定是负数)
     * 3、若没有重复数据，num[k1 + n] 不会是负数，若有重复数据，那么k1 = num[i] ,num[k1 + n] 就会是负数。
     */
    public int findRepeatNumber(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            int k = nums[i];
            if (k < 0) k += n; //还原数据
            if (nums[k] < 0) return k;
            nums[k] -= n;
        }

        return -1;
    }

    /**
     * 统计一个数字在排序数组中出现的次数。
     * <p>
     * 示例 1:
     * 输入: nums = [5,7,7,8,8,10], target = 8
     * 输出: 2
     * <p>
     * 示例 2:
     * 输入: nums = [5,7,7,8,8,10], target = 6
     * 输出: 0
     * <p>
     * 提示：
     * <p>
     * 0 <= nums.length <= 105
     * -109 <= nums[i] <= 109
     * nums 是一个非递减数组
     * -109 <= target <= 109
     * <p>
     * 链接：https://leetcode.cn/problems/zai-pai-xu-shu-zu-zhong-cha-zhao-shu-zi-lcof
     * <p>
     * 思路一：遍历计数
     * 思路二：二分法
     * 重要条件：排序数组，也就是有序数组，所以选择二分法
     * * 1、找到target在数组中的第一个出现位置start,再找到数组中最后一次位置end
     * * 2、总数= end - start + 1
     * <p>
     * 注意：有序数据 一定要想到 二分法
     */
    public int search(int[] nums, int target) {
//        int count = 0;
//        for (int i = 0; i < nums.length; i++) {
//            if (nums[i] > target) {
//                break;
//            }
//            if (nums[i] == target) {
//                count++;
//            }
//        }
//        return count;


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
     * 输入: [0,1,2,3,4,5,6,7,9]
     * 输出: 8
     * <p>
     * 链接：https://leetcode.cn/problems/que-shi-de-shu-zi-lcof
     * <p>
     * 思路一：暴力遍历
     * 思路二：二分法
     */
    public int missingNumber(int[] nums) {
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
        int len = nums.length;
        int start = 0;
        int end = len - 1;
        while (start <= end) {
            int mid = (end + start) / 2;
            if (nums[mid] == mid) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return start;
    }


    /**
     * 剑指 Offer 50. 第一个只出现一次的字符
     * <p>
     * 在字符串 s 中找出第一个只出现一次的字符。如果没有，返回一个单空格。 s 只包含小写字母。
     * <p>
     * 示例 1:
     * <p>
     * 输入：s = "abaccdeff"
     * 输出：'b'
     * 示例 2:
     * <p>
     * 输入：s = ""
     * 输出：' '
     * <p>
     * 链接：https://leetcode.cn/problems/di-yi-ge-zhi-chu-xian-yi-ci-de-zi-fu-lcof
     * <p>
     * 思路一：
     * 直接用hashMap，存储char=>count ，然后再遍历取count == 1的
     * <p>
     * 思路二：
     * hashMap + 队列，
     * 1、中间判断是否在hashMap中已有，有的话value设置为-1，并将队列中为-1的数据都poll出来。
     * 2、在hashmap中没有的数据，将value设置为i，并将该char插入队列中。
     */
    public char firstUniqChar(String s) {
        HashMap<Character, Integer> hashMap = new HashMap<>();
        Queue<Character> queue = new LinkedList<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (hashMap.containsKey(c)) {
                hashMap.put(c, -1);
                while (!queue.isEmpty() && hashMap.get(queue.peek()) == -1) {
                    queue.poll();
                }
            } else {
                hashMap.put(c, i);
                queue.offer(c);
            }
        }
        return queue.isEmpty() ? ' ' : queue.poll();
    }

    /**
     * 剑指 Offer 31. 栈的压入、弹出序列
     * 输入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个序列是否为该栈的弹出顺序。假设压入栈的所有数字均不相等。例如，序列 {1,2,3,4,5} 是某栈的压栈序列，序列 {4,5,3,2,1} 是该压栈序列对应的一个弹出序列，但 {4,3,5,1,2} 就不可能是该压栈序列的弹出序列。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/zhan-de-ya-ru-dan-chu-xu-lie-lcof
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * 思路：
     * 利用辅助栈操作
     *
     * 1、将pushed数据压入tmp栈，同时使用i记录poped角标位置
     * 2、循环
     *      2.1遍历pushed数组，将数据压入tmp栈中
     *      2.2判断tmp栈顶数据是否和 poped数组 的i角标的值相同，相同tmp.pop
     * 3、判断tmp是否为空，为空说明pushed和poped是一组
     *
     */
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        //1、辅助栈
        Stack<Integer> tmp = new Stack<>();
        //用于记录popped数组的角标
        int i = 0;
        //2、循环遍历pushed数组
        for (int num : pushed) {
            tmp.push(num);
            //判断tmp栈顶数据和popped数组 i位置数据是否相同，相同pop；
            while (!tmp.isEmpty() && tmp.peek() == popped[i]){
                tmp.pop();
                i ++;
            }
        }
        //3、判断是否为空
        return tmp.isEmpty();
    }

    /**
     * 给定一个数组 nums 和滑动窗口的大小 k，请找出所有滑动窗口里的最大值。
     *
     * 示例:
     *
     * 输入: nums = [1,3,-1,-3,5,3,6,7], 和 k = 3
     * 输出: [3,3,5,5,6,7]
     * 解释:
     *
     *   滑动窗口的位置                最大值
     * ---------------               -----
     * [1  3  -1] -3  5  3  6  7       3
     *  1 [3  -1  -3] 5  3  6  7       3
     *  1  3 [-1  -3  5] 3  6  7       5
     *  1  3  -1 [-3  5  3] 6  7       5
     *  1  3  -1  -3 [5  3  6] 7       6
     *  1  3  -1  -3  5 [3  6  7]      7
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/hua-dong-chuang-kou-de-zui-da-zhi-lcof
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * 思路：
     * 1、使用双端队列存储窗口的 最大值->最小值（不需要严格存储，和最小值的栈一样。例如[-1,2,3,5] 从[-1,2,3] 变为[2,3,5]时，我们只存着5也是ok的）
     * 滑动窗口：设窗口左边界为i，右边界j。那么i ∈ [1-k, n-k] ，j ∈ [0,n-1]
     *      1、处理左边界i：若 i>0且队首数据deque.peekFirst() == num[i-1]（就是刚划过的数据），那么需要removeFirst();
     *      2、处理有边界j：遍历删除deque中，小于num[j]的数，并将num[j]添加到队尾。
     *      3、获取res窗口值，若 i >= 0时，说明窗口已经在nums中了。res[i]=deque.First();
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums.length == 0 || k == 0)return new int[]{};

        //1、双端队列
        Deque<Integer> deque = new LinkedList<>();
        //初始化res
        int[] res = new int[nums.length - k + 1];

        //滑动窗口
        for (int i = 1 - k, j = 0; j < nums.length; i++, j++) {
            //1、处理左边界i
            if (i > 0 && nums[i - 1] == deque.peekFirst()) {
                deque.removeFirst();
            }
            //2、处理有边界j
            while (!deque.isEmpty() && nums[j] > deque.peekLast()) {
                deque.removeLast();
            }
            deque.addLast(nums[j]);
            //3、获取res窗口值
            if (i>= 0){
                res[i] = deque.peekFirst();
            }
        }
        return res;
    }
}
