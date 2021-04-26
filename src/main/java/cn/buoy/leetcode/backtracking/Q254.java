package cn.buoy.leetcode.backtracking;

import java.util.ArrayList;
import java.util.List;

public class Q254 {
    /**
     * 参考:
     * https://www.youtube.com/watch?v=d0QvoL58SVg
     */
    List<List<Integer>> ans = new ArrayList<>();

    //s 是除数,
    public void getFactors(int n, int s, List<Integer> path) {
        //只需要到 根号i, 因为只要不是根号i, 都是成对出现.
        //除数i 只能递增, 避免重复组合.
        for (int i = s; i * i <= n; ++i) {
            //能整除
            if (n % i == 0) {
                //假如 当前除数
                path.add(i);
                //往下除
                getFactors(n / i, i, path);
                //这是第2大类情况, 就是不在往下除情况, 直接add进 result.
                //这2行放在这里, 不用多写1次回溯操作.
                path.add(n / i);
                ans.add(new ArrayList<>(path));
                //回溯
                path.remove(path.size() - 1);
                path.remove(path.size() - 1);
            }
        }
    }

    public List<List<Integer>> getFactors(int n) {
        //从 2 开始 除, 除1没意义.
        getFactors(n, 2, new ArrayList<>());
        return ans;
    }
}
