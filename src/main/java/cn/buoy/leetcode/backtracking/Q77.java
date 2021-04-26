package cn.buoy.leetcode.backtracking;

import java.util.ArrayList;
import java.util.List;

public class Q77 {
    public static void main(String[] args) {
        Q77 q77 = new Q77();
        List<List<Integer>> lists = q77.combine2(4, 2);
    }

    /**
     * https://www.youtube.com/watch?v=2AzrCkRkRzc
     */
    public static List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> combs = new ArrayList<List<Integer>>();
        combine(combs, new ArrayList<Integer>(), 1, n, k);
        return combs;
    }

    //当k == 0, 或者 i > n 就会结束.
    public static void combine(List<List<Integer>> combs, List<Integer> comb, int start, int n, int k) {
        if (k == 0) {
            //拷贝到 新的list 才放入result中.
            combs.add(new ArrayList<Integer>(comb));
            return;
        }
        //并不会出现 大的数在前 的这种排列组合, 这里限制了 只能取 start及其之后的.
        for (int i = start; i <= n; i++) {
            comb.add(i);
            combine(combs, comb, i + 1, n, k - 1);
            comb.remove(comb.size() - 1);
        }
    }


    public List<List<Integer>> combine2(int n, int k) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if (k > n || k < 0) {
            return result;
        }
        if (k == 0) {
            result.add(new ArrayList<Integer>());
            return result;
        }
        //这里是加入当前n的情况.
        //思路是, 先处理后边的所有情况, 然后最后 都加上n 给所有的后续list.
        result = combine2(n - 1, k - 1);
        for (List<Integer> list : result) {
            list.add(n);
        }
        //这里是没加入当前n的情况.
        result.addAll(combine2(n - 1, k));
        //n只会递减.
        //返回这2种情况的所有组合.
        return result;
    }
}
