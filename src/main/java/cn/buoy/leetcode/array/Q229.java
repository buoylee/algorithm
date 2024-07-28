package cn.buoy.leetcode.array;

import java.util.ArrayList;
import java.util.List;

public class Q229 {
    /**
     * 和 超过 1/2 投票, 同样思路. 摩尔投票. 背.
     * https://www.bilibili.com/video/BV1z4411Q77E?from=search&seid=10207950481018677297
     * https://www.youtube.com/watch?v=lGYHf_yMq7w
     * 思路: count == 0, 就替换 当前 num, count++;
     * num 相等 就 count++; num 不等 就 count--;
     */
    public List<Integer> majorityElement(int[] nums) {
        Integer candidate1 = null, candidate2 = null, count1 = 0, count2 = 0;
        for (Integer num : nums) {
            if (num.equals(candidate1)) {
                count1++;
            } else if (num.equals(candidate2)) {
                count2++;
            } else if (count1 == 0) {
                candidate1 = num;
                count1 = 1;
            } else if (count2 == 0) {
                candidate2 = num;
                count2 = 1;
            } else {
                count1--;
                count2--;
            }
        }
        // 验证 candidate 是否真的 >= 1/3, 先重置 count1/count2
        count1 = count2 = 0;
        for (Integer num : nums) {
            if (num.equals(candidate1))
                count1++;
            else if (num.equals(candidate2))
                // 可能 candidate1 == candidate2, 所以用else if
                count2++;
        }

        List<Integer> result = new ArrayList<>();
        if (count1 > nums.length / 3) result.add(candidate1);
        if (count2 > nums.length / 3) result.add(candidate2);
        return result;
    }
}
