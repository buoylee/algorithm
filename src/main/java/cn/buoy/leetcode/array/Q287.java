package cn.buoy.leetcode.array;

import java.util.HashSet;

public class Q287 {
    //???
    public int findDuplicate(int[] nums) {
        HashSet<Integer> ints = new HashSet<Integer>();
        for (Integer n : nums) {
            boolean add = ints.add(n);
            if (!add)
                return n;
        }
        return -1;
    }

    /*
    https://www.youtube.com/watch?v=u_gg0uVZdsE
     */
    public int findDuplicate1(int[] nums) {
        //todo
        return 0;
    }

}
