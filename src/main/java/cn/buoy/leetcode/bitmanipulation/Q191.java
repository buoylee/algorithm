package cn.buoy.leetcode.bitmanipulation;

public class Q191 {
    /**
     * https://www.youtube.com/watch?v=0KAuHtVlN9Y
     */
    public static int hammingWeight(int n) {
        int count = 0;
        while (n != 0) {
            //100100
            //100011
            //减1后&, 刚好可以小区
            n &= (n - 1);
            count++;
        }

        return count;
    }


    //这个好像比较烂, 一位位找
    public static int hammingWeight2(int n) {
        int ones = 0;
        while (n != 0) {
            ones = ones + (n & 1);
            n = n >>> 1;
        }
        return ones;
    }


}
