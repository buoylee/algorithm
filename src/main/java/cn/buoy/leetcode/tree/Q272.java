package cn.buoy.leetcode.tree;

import cn.buoy.leetcode.TreeNode;

import java.util.*;

public class Q272 {

    /**
     * 超簡單, 就是 inorder 同時檢查 size而已. 視頻隨便聽下, 然後看代碼. 可以分析下复杂度. 中序写起来稍简单.
     * https://www.youtube.com/watch?v=iYK8SJjJ3II
     * 思路: inorder 遍歷 同時 檢查 "最小差值的 size".
     * 按中序遍历, 按顺序把 "差值" 插入list,
     * 当 list.size 来到 size, 进行 差值判断, 比的是 list "head 與 target 差值" 与 "curr 與 target 差值".
     * 一旦 "curr 與 target 差值" 更大, 说明 "當前 list" 已是 "前k距离最小的数".
     * 原因: inorder 從最小到最大 排列, 與 target 的差值肯定是"先越來越小, 然後越來越大"(就像2次方程一樣, 範圍會不同而已)
     */
    public List<Integer> closestKValues(TreeNode root, double target, int size) {
        Deque<Integer> result = new LinkedList<>();
        inorder(root, target, size, result);
        return new ArrayList<>(result);
    }

    private void inorder(TreeNode curr, double target, int size, Deque<Integer> result) {
        if (curr == null) return;
        inorder(curr.left, target, size, result);
        if (result.size() == size) {
            if (Math.abs(curr.val - target) < Math.abs(result.peekFirst() - target))
                result.pollFirst();
            else
                return;
        }
        result.addLast(curr.val);
        inorder(curr.right, target, size, result);
    }

    public List<Integer> closestKValues2(TreeNode root, double target, int k) {
        LinkedList<Integer> result = new LinkedList<Integer>();
        closestKValuesHelper(result, root, target, k);
        return result;
    }

    private boolean closestKValuesHelper(LinkedList<Integer> list, TreeNode root, double target, int size) {
        if (root == null) return false;
        boolean ifClosest = closestKValuesHelper(list, root.left, target, size);
        if (ifClosest) return true;
        if (list.size() == size) {
            if (Math.abs(list.getFirst() - target) < Math.abs(root.val - target)) {
                return true;
            } else
                list.removeFirst();
        }
        list.addLast(root.val);
        return closestKValuesHelper(list, root.right, target, size);
    }


    /**
     * 双stack
     *
     * @param root
     * @param target
     * @param k
     * @return
     */
    public List<Integer> closestKValues3(TreeNode root, double target, int k) {
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
