package cn.buoy.leetcode.tree;

import cn.buoy.leetcode.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class Q272 {

    /**
     * https://www.youtube.com/watch?v=iYK8SJjJ3II
     * 可以分析下复杂度. 中序写起来稍简单.
     * 先中序遍历, 按顺序插入list, 当 list.size 来到 k, 进行 差值判断, 比的是 list的头 与 准备插入的node 2者与target距离 的比较. 一旦 新的node的距离 比 list头 还大, 说明, 不用插入新的node, 已找到前k距离最小的数.
     *
     * @param root
     * @param target
     * @param k
     * @return
     */
    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        LinkedList<Integer> list = new LinkedList<Integer>();
        closestKValuesHelper(list, root, target, k);
        return list;
    }

    /**
     * @return <code>true</code> if result is already found.
     */
    private boolean closestKValuesHelper(LinkedList<Integer> list, TreeNode root, double target, int k) {
        if (root == null) return false;
        //
        if (closestKValuesHelper(list, root.left, target, k)) return true;

        if (list.size() == k) {
            if (Math.abs(list.getFirst() - target) < Math.abs(root.val - target)) {
                return true;
            } else {
                list.removeFirst();
            }
        }

        list.addLast(root.val);
        return closestKValuesHelper(list, root.right, target, k);
    }


    /**
     * 双stack
     *
     * @param root
     * @param target
     * @param k
     * @return
     */
    public List<Integer> closestKValues2(TreeNode root, double target, int k) {
        List<Integer> res = new ArrayList<>();

        //分别存在 大于 或 小于 target 的 2个栈 中.

        Stack<Integer> s1 = new Stack<>(); // predecessors
        Stack<Integer> s2 = new Stack<>(); // successors
        //中序遍历, 小到大
        inorder(root, target, false, s1);
        //中序遍历, 大到小
        inorder(root, target, true, s2);

        //越接近 target 越在顶, 按 target差值大小 取出就好.
        while (k-- > 0) {
            if (s1.isEmpty())
                res.add(s2.pop());
            else if (s2.isEmpty())
                res.add(s1.pop());
            else if (Math.abs(s1.peek() - target) < Math.abs(s2.peek() - target))
                res.add(s1.pop());
            else
                res.add(s2.pop());
        }

        return res;
    }

    // inorder traversal
    void inorder(TreeNode root, double target, boolean reverse, Stack<Integer> stack) {
        if (root == null) return;

        inorder(reverse ? root.right : root.left, target, reverse, stack);
        // early terminate, no need to traverse the whole tree
        //关键在这, 其实 原理就是 2个 大到小 , 小到大 的stack,
        //如果大到小, 当当前node 小于等于target 的时候 停止遍历.; 如果小到大, 当当前node 大于目标 的时候 停止遍历.
        if ((reverse && root.val <= target) || (!reverse && root.val > target)) return;
        // track the value of current node
        stack.push(root.val);
        inorder(reverse ? root.left : root.right, target, reverse, stack);
    }
}
