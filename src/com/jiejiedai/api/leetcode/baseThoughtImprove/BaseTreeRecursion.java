package com.jiejiedai.api.leetcode.baseThoughtImprove;

import com.jiejiedai.api.leetcode.TreeNode;

import java.util.*;

/**
 * 树与递归
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
     *
     * 从上到下按层打印二叉树，同一层的节点按从左到右的顺序打印，每一层打印到一行。
     *
     * 例如:
     * 给定二叉树: [3,9,20,null,null,15,7],
     *
     *     3
     *    / \
     *   9  20
     *     /  \
     *    15   7
     * 返回其层次遍历结果：
     *
     * [
     *   [3],
     *   [9,20],
     *   [15,7]
     * ]
     *
     * 链接：https://leetcode.cn/problems/cong-shang-dao-xia-da-yin-er-cha-shu-ii-lcof
     *
     * 思路一：创建Pair，存储level和node
     * 思路二：遍历当前level，
     *
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

    class Pair{
        TreeNode node;
        int level;
        Pair(TreeNode node,int level){
            this.node = node;
            this.level = level;
        }
    }

    /**
     * 剑指 Offer 32 - III. 从上到下打印二叉树 III
     *
     * 请实现一个函数按照之字形顺序打印二叉树，即第一行按照从左到右的顺序打印，第二层按照从右到左的顺序打印，第三行再按照从左到右的顺序打印，其他行以此类推。
     *
     * 例如:
     * 给定二叉树: [3,9,20,null,null,15,7],
     *
     *     3
     *    / \
     *   9  20
     *     /  \
     *    15   7
     * 返回其层次遍历结果：
     *
     * [
     *   [3],
     *   [20,9],
     *   [15,7]
     * ]
     *
     * 链接：https://leetcode.cn/problems/cong-shang-dao-xia-da-yin-er-cha-shu-iii-lcof
     *
     * 思路：
     * 沿用上边 遍历当前level，
     * 在每个level层级，处理时，偶数行，不用list，改用一个队列，offerLast()。
     */
    public List<List<Integer>> levelOrder3(TreeNode root) {
        if (root == null){
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

}
