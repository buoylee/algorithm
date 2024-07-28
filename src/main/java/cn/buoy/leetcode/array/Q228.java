package cn.buoy.leetcode.array;

import java.util.ArrayList;
import java.util.List;

public class Q228 {
    /**
     * 思路超简单, 注意写法
     * https://www.youtube.com/watch?v=CQC8rmyjAkg
     * 思路: 初始/出现不连续后, 先设置当前 num[i] 为 区间的 head, 然后不断比较 num[i] 和 num[i + 1], 如果 num[i + 1] - num[i] == 1, 说明连续, i++, 直到发现不连续;
     * 当发现不再连续时, 有2种情况: head ==/!= num[下一个 index 不再连续的 index],
     * 相等, 说明, 只是一个单独的数;
     * 不等, 说明, 有2个或以上元素的连续数列, 需要 打印为 "a->b"
     */
    public List<String> summaryRanges(int[] nums) {
        List<String> list = new ArrayList();
        if (nums.length == 1) {
            list.add(nums[0] + "");
            return list;
        }
        for (int i = 0; i < nums.length; i++) {
            // 取 区间头部 num
            int head = nums[i];
            // 看下一个 num 是否是 num + 1, 是就放一个区间(检查下下一个).
            while (i + 1 < nums.length && (nums[i + 1] - nums[i]) == 1)
                i++;
            // head 不等于 "当前 num[i]", 说明连续的数列长度不是1. 需要 写成 a->b
            if (head != nums[i]) {
                list.add(head + "->" + nums[i]);
            } else // head 等于 "当前 num[i]", 说明只是一个单独的数.
                list.add(head + "");
        }
        return list;
    }
}
