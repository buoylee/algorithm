package cn.buoy.leetcode.array;

import java.util.ArrayList;
import java.util.List;

public class Q228 {
    /**
     * https://www.youtube.com/watch?v=CQC8rmyjAkg
     */
    public List<String> summaryRanges(int[] nums) {
        List<String> list = new ArrayList();
        if (nums.length == 1) {
            list.add(nums[0] + "");
            return list;
        }
        for (int i = 0; i < nums.length; i++) {
            //去头部value
            int a = nums[i];
            while (i + 1 < nums.length && (nums[i + 1] - nums[i]) == 1) {
                //如果和下一个value比, 多1, 这 i++也合法. 放一个区间.
                i++;
            }
            if (a != nums[i]) {
                list.add(a + "->" + nums[i]);
            } else {
                //如果还是区间头部, 加元素本身即可.
                //如果是重复元素, 换成保留index即可.
                list.add(a + "");
            }
        }
        return list;
    }
}
