package cn.buoy.leetcode.math;

public class Q390 {
    /**
     * https://www.youtube.com/watch?v=D7_ALByjHMw
     * <p>
     * 这个不好理解:
     * https://www.youtube.com/watch?v=DfMn4U10hnI&t=224s
     */
    public int lastRemaining(int n) {
        boolean left = true;
        int remaining = n;
        int step = 1;
        int head = 1;
        //更新的是左边第一个剩下的元素
        while (remaining > 1) {
            if (left || remaining % 2 == 1) {
                head = head + step;
            }
            remaining = remaining / 2;
            //记录他为了更新head
            step = step * 2;
            left = !left;
        }
        return head;
    }
}
