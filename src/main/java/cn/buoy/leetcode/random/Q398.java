package cn.buoy.leetcode.random;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class Q398 {
    /**
     * 這個方法簡單. 不是最優. 看下邊
     * https://www.youtube.com/watch?v=sPqLySxrrYo
     * 思路: 轉化爲 map{value, indexList[]}, rdm 取 indexList[]
     */
    HashMap<Integer, List<Integer>> map;
    Random rand;

    public Q398(int[] nums) {
        rand = new Random();
        map = new HashMap<>();
        for (int i = 0; i < nums.length; ++i) {
            if (!map.containsKey(nums[i]))
                map.put(nums[i], new ArrayList<>());
            map.get(nums[i]).add(i);
        }
    }

    public int pick(int target) {
        int size = map.get(target).size();
        return map.get(target).get(rand.nextInt(size));
    }

    /**
     * 簡單, 同 398, 水庫抽样.
     * https://www.youtube.com/watch?v=SnlxL5_LF7g
     */
    private int[] nums;

//    public Q398(int[] nums) {
//        this.nums = nums;
//    }

    public int pick2(int target) {
        Random r = new Random();
        int n = 0;
        int index = 0;
        for (int i = 0; i < nums.length; i++)
            if (nums[i] == target) {
                //統計同一个target的个数
                n++;
                // 水庫抽样.
                if (r.nextInt(n) == 0)
                    index = i;
            }
        return index;
    }
}
