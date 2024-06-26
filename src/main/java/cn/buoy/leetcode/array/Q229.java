package cn.buoy.leetcode.array;

import java.util.ArrayList;
import java.util.List;


/*
https://www.bilibili.com/video/BV1z4411Q77E?from=search&seid=10207950481018677297


https://www.youtube.com/watch?v=lGYHf_yMq7w
快速讲解
 */
public class Q229 {
    public List<Integer> majorityElement(int[] nums) {
        Integer major1 = null, major2 = null, cnt1 = 0, cnt2 = 0;
        for (Integer num : nums) {
            if (num.equals(major1)) {
                cnt1++;
            } else if (num.equals(major2)) {
                cnt2++;
            } else if (cnt1 == 0) {
                major1 = num;
                cnt1 = 1;
            } else if (cnt2 == 0) {
                major2 = num;
                cnt2 = 1;
            } else {
                cnt1--;
                cnt2--;
            }
        }

        //不可能直接走这里, 不止3种值...
        cnt1 = cnt2 = 0;
        for (Integer num : nums) {
            if (num.equals(major1)) cnt1++;
            else if (num.equals(major2)) {
                //可能major1 == major2, 所以用else if
                cnt2++;
            }
        }

        List<Integer> result = new ArrayList<>();
        if (cnt1 > nums.length / 3) result.add(major1);
        if (cnt2 > nums.length / 3) result.add(major2);
        return result;
    }
}
