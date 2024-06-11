package cn.buoy.leetcode.array;

import java.util.ArrayList;
import java.util.List;

public class Q228 {
    /**
     * 思路超简单, 注意写法
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
            int head = nums[i];
            while (i + 1 < nums.length && (nums[i + 1] - nums[i]) == 1) {
                //对比下一个value是否是value++, 是就放一个区间(检查下下一个).
                i++;
            }
            //一旦发现下一个 i++ !=  nums[i++], 检查当前nums[i]时候是和head同一个index, 不是就用a->b, 是就a
            if (head != nums[i]) {
                list.add(head + "->" + nums[i]);
            } else {
                //如果还是区间头部, 加元素本身即可.
                //如果是重复元素, 换成保留index即可.
                list.add(head + "");
            }
        }
        return list;
    }
}
