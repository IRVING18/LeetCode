package com.jiejiedai.api.leetcode.baseThoughtImprove;

import com.jiejiedai.api.leetcode.TreeNode;

import java.util.*;

/**
 * 树与递归
 * <p>
 * 树的操作：
 * 1、利用Queue队列
 * 2、递归
 * 3、中序遍历：二叉查找树的特性，可递归打印小->大，大-> 小
 *          二叉查找树特性：中序遍历
 *           小->大：
 *               recur(node.left) 左
 *               node.val 根
 *               recur(node.right) 右
 *           大->小：
 *               recur(node.right) 右
 *               node.val 根
 *               recur(node.left) 左
 * 3、广度优先算法
 * 4、深度优先算法 https://leetcode.cn/tag/depth-first-search/problemset/
 */
public class BaseTreeRecursion {

    /**
     * 剑指 Offer 32 - I. 从上到下打印二叉树
     * <p>
     * 从上到下打印出二叉树的每个节点，同一层的节点按照从左到右的顺序打印。
     * <p>
     * 例如:
     * 给定二叉树: [3,9,20,null,null,15,7],
     * <p>
     * 3
     * / \
     * 9  20
     * /  \
     * 15   7
     * 返回：
     * <p>
     * [3,9,20,15,7]
     * <p>
     * 链接：https://leetcode.cn/problems/cong-shang-dao-xia-da-yin-er-cha-shu-lcof
     * <p>
     * 思路：利用队列处理
     * 1、将root节点放入queue中
     * 2、queue.poll()出来的数据node存到res数组中。
     * 3、node的左子树不为空 就offer进queue，右子树相同
     * 4、跳出条件：当queue为空
     */
    public int[] levelOrder(TreeNode root) {
        if (root == null) {
            return new int[]{};
        }
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        List<Integer> res = new ArrayList<Integer>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            res.add(node.val);
            if (node.left != null) queue.offer(node.left);
            if (node.right != null) queue.offer(node.right);
        }
        int[] arr = new int[res.size()];
        for (int i = 0; i < res.size(); i++) {
            arr[i] = res.get(i);
        }
        return arr;
    }

    /**
     * 剑指 Offer 32 - II. 从上到下打印二叉树 II
     * <p>
     * 从上到下按层打印二叉树，同一层的节点按从左到右的顺序打印，每一层打印到一行。
     * <p>
     * 例如:
     * 给定二叉树: [3,9,20,null,null,15,7],
     * <p>
     * 3
     * / \
     * 9  20
     * /  \
     * 15   7
     * 返回其层次遍历结果：
     * <p>
     * [
     * [3],
     * [9,20],
     * [15,7]
     * ]
     * <p>
     * 链接：https://leetcode.cn/problems/cong-shang-dao-xia-da-yin-er-cha-shu-ii-lcof
     * <p>
     * 思路一：创建Pair，存储level和node
     * 思路二：遍历当前level，
     */
    public List<List<Integer>> levelOrder2(TreeNode root) {
//        if (root == null){
//            return new ArrayList();
//        }
//        Queue<Pair> queue = new LinkedList<Pair>();
//        List<List<Integer>> res = new ArrayList();
//        queue.offer(new Pair(root,0));
//        while(!queue.isEmpty()){
//            Pair pair = queue.poll();
//            if (res.size() <= pair.level){
//                res.add(new ArrayList<Integer>());
//            }
//            res.get(pair.level).add(pair.node.val);
//
//            if (pair.node.left != null) queue.offer(new Pair(pair.node.left,pair.level + 1));
//            if (pair.node.right != null) queue.offer(new Pair(pair.node.right,pair.level + 1));
//        }
//
//        return res;

        List<List<Integer>> ret = new ArrayList<List<Integer>>();
        if (root == null) {
            return ret;
        }

        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            List<Integer> level = new ArrayList<Integer>();
            int currentLevelSize = queue.size();
            for (int i = 1; i <= currentLevelSize; ++i) {
                TreeNode node = queue.poll();
                level.add(node.val);
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            ret.add(level);
        }

        return ret;

    }

    class Pair {
        TreeNode node;
        int level;

        Pair(TreeNode node, int level) {
            this.node = node;
            this.level = level;
        }
    }

    /**
     * 剑指 Offer 32 - III. 从上到下打印二叉树 III
     * <p>
     * 请实现一个函数按照之字形顺序打印二叉树，即第一行按照从左到右的顺序打印，第二层按照从右到左的顺序打印，第三行再按照从左到右的顺序打印，其他行以此类推。
     * <p>
     * 例如:
     * 给定二叉树: [3,9,20,null,null,15,7],
     * <p>
     * 3
     * / \
     * 9  20
     * /  \
     * 15   7
     * 返回其层次遍历结果：
     * <p>
     * [
     * [3],
     * [20,9],
     * [15,7]
     * ]
     * <p>
     * 链接：https://leetcode.cn/problems/cong-shang-dao-xia-da-yin-er-cha-shu-iii-lcof
     * <p>
     * 思路：
     * 沿用上边 遍历当前level，
     * 在每个level层级，处理时，偶数行，不用list，改用一个队列，offerLast()。
     */
    public List<List<Integer>> levelOrder3(TreeNode root) {
        if (root == null) {
            return null;
        }
        List<List<Integer>> res = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        boolean isLeftOrder = true;
        while (!queue.isEmpty()) {
            int currentLevelSize = queue.size();
            Deque<Integer> deque = new LinkedList<>();
            for (int i = 0; i < currentLevelSize; i++) {
                TreeNode node = queue.poll();
                if (isLeftOrder) {
                    deque.offer(node.val);
                } else {
                    deque.offerFirst(node.val);
                }

                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            res.add(new ArrayList<Integer>(deque));
            isLeftOrder = !isLeftOrder;
        }
        return res;
    }

    /**
     * 剑指 Offer 26. 树的子结构
     * <p>
     * 输入两棵二叉树A和B，判断B是不是A的子结构。(约定空树不是任意一个树的子结构)
     * <p>
     * B是A的子结构， 即 A中有出现和B相同的结构和节点值。
     * <p>
     * 例如:
     * 给定的树 A:
     * <p>
     *      3
     *     / \
     *    4   5
     *   / \
     *  1   2
     * 给定的树 B：
     * <p>
     *    4 
     *   /
     *  1
     * 返回 true，因为 B 与 A 的一个子树拥有相同的结构和节点值。
     * <p>
     * 示例 1：
     * <p>
     * 输入：A = [1,2,3], B = [3,1]
     * 输出：false
     * 示例 2：
     * <p>
     * 输入：A = [3,4,5,1,2], B = [4,1]
     * 输出：true
     * <p>
     * 链接：https://leetcode.cn/problems/shu-de-zi-jie-gou-lcof
     * <p>
     * 思路：递归
     * 1、第一层递归：判断A是否和B完全相交，iscontain()
     * 2、第二层递归：判断A是否和B完全相交，不完全相交-> left、right是否完全相交 isSubStructure()
     */
    public boolean isSubStructure(TreeNode A, TreeNode B) {
        if (A == null || B == null) {
            return false;
        }

        return isContain(A, B) || isSubStructure(A.left, B) || isSubStructure(A.right, B);
    }

    /**
     * 是否完全相交
     * <p>
     * 或者B完全在A中
     */
    private boolean isContain(TreeNode A, TreeNode B) {
        //B完全在A中
        if (B == null) {
            return true;
        }

        return A != null && A.val == B.val && isContain(A.left, B.left) && isContain(A.right, B.right);
    }

    /**
     * 剑指 Offer 27. 二叉树的镜像
     * 请完成一个函数，输入一个二叉树，该函数输出它的镜像。
     * <p>
     * 例如输入：
     * <p>
     *      4
     *    /   \
     *   2     7
     *  / \   / \
     * 1   3 6   9
     * 镜像输出：
     * <p>
     *      4
     *    /   \
     *   7     2
     *  / \   / \
     * 9   6 3   1
     * <p>
     *  
     * <p>
     * 示例 1：
     * <p>
     * 输入：root = [4,2,7,1,3,6,9]
     * 输出：[4,7,2,9,6,3,1]
     * <p>
     * 链接：https://leetcode.cn/problems/er-cha-shu-de-jing-xiang-lcof
     */
    public static TreeNode mirrorTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode left = null;
        if (root.left != null) {
            left = root.left;
        }
        TreeNode right = null;
        if (root.right != null) {
            right = root.right;
        }

        root.left = right;
        root.right = left;

        mirrorTree(root.left);
        mirrorTree(root.right);

        return root;
    }


    /**
     * 剑指 Offer 28. 对称的二叉树
     * <p>
     * 请实现一个函数，用来判断一棵二叉树是不是对称的。如果一棵二叉树和它的镜像一样，那么它是对称的。
     * <p>
     * 例如，二叉树 [1,2,2,3,4,4,3] 是对称的。
     * <p>
     *     1
     *    / \
     *   2   2
     *  / \ / \
     * 3  4 4  3
     * 但是下面这个 [1,2,2,null,3,null,3] 则不是镜像对称的:
     * <p>
     *     1
     *    / \
     *   2   2
     *    \   \
     *    3    3
     * <p>
     *  
     * <p>
     * 示例 1：
     * <p>
     * 输入：root = [1,2,2,3,4,4,3]
     * 输出：true
     * 示例 2：
     * <p>
     * 输入：root = [1,2,2,null,3,null,3]
     * 输出：false
     * <p>
     * 链接：https://leetcode.cn/problems/dui-cheng-de-er-cha-shu-lcof
     */
    public boolean isSymmetric(TreeNode root) {
        return isSymmertric(root, root);
    }

    public boolean isSymmertric(TreeNode nodeA, TreeNode nodeB) {
        if (nodeA == null && nodeB == null) {
            return true;
        }
        if (nodeA == null || nodeB == null) {
            return false;
        }
        return nodeA.val == nodeB.val && isSymmertric(nodeA.left, nodeB.right) && isSymmertric(nodeA.right, nodeB.left);
    }


    public static void main(String[] args) {
//        TreeNode node = new TreeNode(1);
//        TreeNode right = new TreeNode(2);
//        node.left = null;
//        node.right = right;
//        System.out.println(node.val + ' ' + (node.left != null ? node.left.val : 'null') + ' ' + node.right.val);
//
//        mirrorTree(node);
//        System.out.println(node.val + ' ' + (node.left != null ? node.left.val : 'null') + ' ' + (node.right != null ? node.right.val : 'null'));


//        char[][] arr = new char[][]{
//                {'A', 'B', 'C', 'C'},
//                {'S', 'F', 'C', 'S'},
//                {'A', 'D', 'E', 'E'}
//        };
//
//        System.out.println(exist1(arr, "ABCCED") ? "true" : "false");

        System.out.println(35  % 10 +"   "+35 / 10);
    }

    /**
     * 剑指 Offer 12. 矩阵中的路径
     * <p>
     * 给定一个 m x n 二维字符网格 board 和一个字符串单词 word 。如果 word 存在于网格中，返回 true ；否则，返回 false 。
     *  {'A', 'B', 'C', 'C'},
     *  {'S', 'F', 'C', 'S'},
     *  {'A', 'D', 'E', 'E'}
     *
     *  ABCCED
     *
     * <p>
     * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
     * <p>
     * 链接：https://leetcode.cn/problems/ju-zhen-zhong-de-lu-jing-lcof
     *
     *
     * 思路：递归
     */
    public static boolean exist1(char[][] board, String word) {
        int r = board.length;
        int l = board[0].length;

        boolean[][] visited = new boolean[r][l];
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < l; j++) {
                if (check1(board, visited, word, i, j, 0)) {
                    return true;
                }
            }
        }

        return false;
    }


    public static boolean check1(char[][] board, boolean[][] visited, String word, int r, int l, int k) {
        if (board[r][l] != word.charAt(k)) {
            return false;
        } else if (k == word.length() - 1) {
            return true;
        }

        int[][] range = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        visited[r][l] = true;
        for (int i = 0; i < range.length; i++) {
            int newr = r + range[i][0];
            int newl = l + range[i][1];
            if (newr >= 0 && newr < board.length && newl >= 0 && newl < board[0].length) {
                if (!visited[newr][newl]) {
                    if (check1(board, visited, word, newr, newl, k + 1)) {
                        return true;
                    }
                }
            }
        }
        visited[r][l] = false;
        return false;
    }


    /**
     * 面试题13. 机器人的运动范围
     *
     * 地上有一个m行n列的方格，从坐标 [0,0] 到坐标 [m-1,n-1] 。
     * 一个机器人从坐标 [0, 0] 的格子开始移动，它每次可以向左、右、上、下移动一格（不能移动到方格外），也不能进入行坐标和列坐标的数位之和大于k的格子。
     * 例如，当k为18时，机器人能够进入方格 [35, 37] ，因为3+5+3+7=18。但它不能进入方格 [35, 38]，因为3+5+3+8=19。请问该机器人能够到达多少个格子？
     *
     *
     * 示例 1：
     * 输入：m = 2, n = 3, k = 1
     * 输出：3
     *
     * 示例 2：
     * 输入：m = 3, n = 1, k = 0
     * 输出：1
     *
     * 提示：
     * 1 <= n,m <= 100
     * 0 <= k <= 20
     *
     * 链接：https://leetcode.cn/problems/ji-qi-ren-de-yun-dong-fan-wei-lcof
     */
    public int movingCount(int m, int n, int k) {
        boolean[][] visited = new boolean[m][n];
        return dfs(0, 0, m, n, k, visited);
    }


    public int dfs(int i, int j, int m, int n, int k, boolean[][] visited) {
        if (i >= m || j >= n || k < getSum(i) + getSum(j) || visited[i][j]) {
            return 0;
        }

        visited[i][j] = true;
        return 1 + dfs(i + 1, j, m, n, k, visited) + dfs(i, j + 1, m, n, k, visited);
    }

    public int getSum(int num) {
        return num % 10 + num / 10;
    }


    /**
     *
     * 剑指 Offer 34. 二叉树中和为某一值的路径
     *
     *
     * 思路一：递归
     * 链接：https://leetcode.cn/problems/er-cha-shu-zhong-he-wei-mou-yi-zhi-de-lu-jing-lcof/solution/mian-shi-ti-34-er-cha-shu-zhong-he-wei-mou-yi-zh-5/
     * 递推参数： 当前节点 root ，当前目标值 tar 。
     * 终止条件： 若节点 root 为空，则直接返回。
     * 递推工作：
     *      1、路径更新： 将当前节点值 root.val 加入路径 path ；
     *      2、目标值更新： tar = tar - root.val（即目标值 tar 从 sum 减至 00 ）；
     *      3、路径记录： 当 ① root 为叶节点 且 ② 路径和等于目标值 ，则将此路径 path 加入 res 。
     *      4、中序遍历： 递归左 / 右子节点。
     *      5、路径恢复： 向上回溯前，需要将当前节点从路径 path 中删除，即执行 path.pop() 。
     *
     * 思路二：深度优先算法
     *
     * todo https://leetcode.cn/tag/depth-first-search/problemset/
     */
    LinkedList<List<Integer>> res = new LinkedList<>();
    LinkedList<Integer> path = new LinkedList<>();
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        recur(root, sum);
        return res;
    }
    void recur(TreeNode root, int tar) {
        if(root == null) return;
        path.add(root.val);
        tar -= root.val;
        if (tar == 0 && root.left == null && root.right == null){
            //深复制一份存储到res中
            res.add(new LinkedList<Integer>(path));
        }
        recur(root.left,tar);
        recur(root.right,tar);
        //路径恢复
        path.removeLast();
    }


    /**
     *
     * 二叉查找树特性：中序遍历
     *  小->大：
     *      recur(node.left) 左
     *      node.val 根
     *      recur(node.right) 右
     *
     *  大->小：
     *      recur(node.right) 右
     *      node.val 根
     *      recur(node.left) 左
     */
    int kKth;
    int resKth;
    public int kthLargest(TreeNode root, int k) {
        this.kKth = k;
        recurKth(root);
        return resKth;
    }

    private void recurKth(TreeNode node){
        if (node == null) return;
        //大->小 中序遍历
        recurKth(node.right); //右
        if (--kKth == 0) {
            resKth = node.val; //根
            return;
        }
        recurKth(node.left); //左
    }


    /**
     * 剑指 Offer 36. 二叉搜索树与双向链表
     *
     * https://leetcode.cn/problems/er-cha-sou-suo-shu-yu-shuang-xiang-lian-biao-lcof/?envType=study-plan&id=lcof&plan=lcof&plan_progress=cafihze
     *
     * 思路：
     * （1）递归中序遍历
     * 1、终止条件：cur = null
     * 2、递归左子树：dfs(node.left)
     * 3、构建链表：
     *      1、当pre为null时，说明在head节点上，记下head
     *      2、当pre不为null，pre.right = cur; cur.left = pre;
     *      3、保存cur，pre = cur。即当前cur 是后续节点的 pre
     *
     * （2）treeToDoublyList
     * 1、特例处理：root == null 直接return
     * 2、pre置为null
     * 3、进行中序遍历
     * 4、头尾节点处理：中序遍历结束后，pre就是tail节点：pre.left = head；head.right = pre
     * 5、直接返回head
     */
    Node pre = null;
    Node head = null;
    public Node treeToDoublyList(Node root) {

        if (root == null)return null;
        pre = null;
        //中序遍历
        dfsNode(root);
        //头尾节点处理
        pre.right = head;
        head.left = pre;
        return head;
    }

    private void dfsNode(Node node) {
        if (node == null) return;
        //左
        dfsNode(node.left);
        //根
        //pre为null说明，当前节点为最左边的叶子节点，也就是最小值，head
        if (pre == null) {
            head = node;
        } else {
            node.left = pre;
            pre.right = node;
        }
        pre = node;
        //右
        dfsNode(node.right);
    }

    class Node {
        public int val;
        public Node left;
        public Node right;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val,Node _left,Node _right) {
            val = _val;
            left = _left;
            right = _right;
        }
    }

    /**
     * 一、深度优先搜索（后序遍历）：栈 或者 递归
     * 二、广度优先算法（层序遍历）：队列
     */
    public int maxDepth(TreeNode root) {
//        //深度优先
//        if (root == null){
//            return 0;
//        }
//        return Math.max(maxDepth(root.left),maxDepth(root.right)) + 1;

        //广度优先
        if (root == null){
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);
        int res = 0;

        while (!queue.isEmpty()){
            Queue<TreeNode> tmp = new LinkedList<TreeNode>();
            for(TreeNode node: queue){
                if (node.left != null) tmp.add(node.left);
                if (node.right != null) tmp.add(node.right);
            }
            queue = tmp;
            res++;
        }
        return res;
    }



}
