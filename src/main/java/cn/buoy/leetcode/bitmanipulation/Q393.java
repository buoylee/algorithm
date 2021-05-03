package cn.buoy.leetcode.bitmanipulation;

public class Q393 {
    /**
     * https://www.youtube.com/watch?v=IKplJ57qNQg
     * 第一个byte, 如果0个`1`开头, data.len == 1
     * 第一个byte 不会以 1个`1`开头, 只有后续byte有可能.
     * 第一个byte, 如果2个`1`开头, data.len == 2
     * 第一个byte, 如果3个`1`开头, data.len == 3
     * 第一个byte, 如果4个`1`开头, data.len == 4
     */
    public boolean validUtf8(int[] data) {
        int idx = 0;
        while (idx < data.length) {
            //求出data index0 后续有几个byte.
            int followers = count(data[idx++]) - 1;
            if (followers == 0 || followers >= 4) return false;

            //当 followers -1 时(arr.len == 1), 不会false.
            while (followers-- > 0)
                //index也不能超过len 或 头部不能只有1个`1`
                if (idx == data.length || count(data[idx++]) != 1) return false;
        }
        return true;
    }

    //第一次进来, 计算 高位开始有几个1, n个 表示 这个 arr.length 必须为n.
    //计算头部有几个1.
    private int count(int num) {
        int bits = 0;
        for (int idx = 7; idx >= 0; idx--)
            if ((num >> idx & 1) == 0) break;
            else bits++;
        return bits;
    }

}
