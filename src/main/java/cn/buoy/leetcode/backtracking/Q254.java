package cn.buoy.leetcode.backtracking;

import java.util.ArrayList;
import java.util.List;

public class Q254 {
    /**
     * 参考:
     * https://www.youtube.com/watch?v=d0QvoL58SVg
     * 这个比较好理解
     */
    private void helper(List<List<Integer>> res, List<Integer> temp, int remain, int start) {
        //剩1, 结束
        if (remain == 1) {
            //质数丢掉
            if (temp.size() > 1) {
                res.add(new ArrayList<>(temp));
                return;
            }
        }

        for (int divisor = start; divisor <= remain; divisor++) {
            if (remain % divisor == 0) {
                temp.add(divisor);
                helper(res, temp, remain / divisor, divisor);
                temp.remove(temp.size() - 1);
            }
        }
    }

    public List<List<Integer>> getFactors(int n) {
        List<List<Integer>> res = new ArrayList<>();
        helper(res, new ArrayList<>(), n, 2);
        return res;
    }


    /**
     * 这个不够直观, 巧妙把2步合并, 倒数第2步就判断出是否有2个数可整除.
     */
    List<List<Integer>> ans = new ArrayList<>();

    //s 是除数,
    public void getFactors(int remain, int start, List<Integer> tempFactorList) {
        //除数divisor传递到下层时, 只能'相同或递增'
        //除数divisor遍历时, 只能'递增', 避免重复组合.
        //当前divisor, 如果 (remain/当前divisor) < 当前divisor; 即 divisor * divisor <= remain(本身*本身(不递增的情况下)), 还是大于remain, 说明没有结果, 直接跳出.
        for (int divisor = start; divisor * divisor <= remain; ++divisor) {
            //能整除
            if (remain % divisor == 0) {
                //加入 当前除数
                tempFactorList.add(divisor);
                //往下除
                getFactors(remain / divisor, divisor, tempFactorList);
                //因为在for中判断了是否还有可除数(不一定整除), if中判断了整除, 所以, 来到这里这是第2大类情况, 就是不在往下除情况, 直接add进 result.
                //这2行放在这里, 不用多写1次回溯操作.
                // 2*2*3
                tempFactorList.add(remain / divisor);
                ans.add(new ArrayList<>(tempFactorList));
                //回溯
                tempFactorList.remove(tempFactorList.size() - 1);
                tempFactorList.remove(tempFactorList.size() - 1);
            }
        }
    }

    public List<List<Integer>> getFactors2(int n) {
        //从 2 开始 除, 除1没意义.
        getFactors(n, 2, new ArrayList<>());
        return ans;
    }
}
