package com.jiejiedai.api.leetcode.baseThoughtImprove;

import com.jiejiedai.api.leetcode.TreeNode;

import java.util.*;

/**
 * 树与递归
 * <p>
 * 树的操作：
 * 1、利用Queue队列
 * 2、递归
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
        TreeNode node = new TreeNode(1);
        TreeNode right = new TreeNode(2);
        node.left = null;
        node.right = right;
        System.out.println(node.val + " " + (node.left != null ? node.left.val : "null") + " " + node.right.val);

        mirrorTree(node);
        System.out.println(node.val + " " + (node.left != null ? node.left.val : "null") + " " + (node.right != null ? node.right.val : "null"));
    }
}
