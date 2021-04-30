package cn.buoy.leetcode.binarysearch;

public class Q278 {
    /**
     * 二分法还是二分法,
     * 关键在于 如何 处理边界问题,
     */
    public int firstBadVersion(int n) {
        int start = 1;
        int end = n;
        //如果 位置重叠,
        //都是bad, 则 high++ 不影响
        //都是good, 需要low再--一次
        while (start <= end) {
            //防overflow
            int mid = end + (start - end) / 2;
            if (isBadVersion(mid))
                end = mid - 1;
            else
                start = mid + 1;
        }
        return start;
    }


    boolean isBadVersion(int version) {
        return false;
    }

}
