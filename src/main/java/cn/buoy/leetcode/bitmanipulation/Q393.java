package cn.buoy.leetcode.bitmanipulation;

public class Q393 {
    /**
     * https://www.youtube.com/watch?v=IKplJ57qNQg
     * 思路簡單, 看視頻理解思路, 代碼可能不太好寫.
     * 第一个 byte, 如果0个`1`开头, data.len == 1
     * 第一个 byte, `10`开头, 不合法.
     * 第一个 byte, 如果2个`1`开头, data.len == 2
     * 第一个 byte, 如果3个`1`开头, data.len == 3
     * 第一个 byte, 如果4个`1`开头, data.len == 4
     * 題目是可能存在多個utf-8字符的, 所以需要檢查完所有.
     */
    public boolean validUtf8(int[] data) {
        // 寫的有點繞, 多看.
        int idx = 0;
        while (idx < data.length) {
            //求出data除第一個 byte, 后续有几个 byte.
            //followers 只存在, -1, 1, 2, 3, 這4種情況.
            int followers = count(data[idx++]) - 1;
            if (followers == 0 || followers >= 4) return false;

            // followers其實是表示後續幾個元素要符合utf-8規則, 當滿足, 會繼續檢查下一個followers, 如果提前不符合, 則失敗; 當 followers爲0, 如果後續還有元素, 則需要繼續檢查. 直到idx == data.length.
            while (followers-- > 0)
                //index也不能超过len 或 头部不能只有1个`1`
                if (idx == data.length || count(data[idx++]) != 1) return false;
        }
        return true;
    }

    //计算這個 int 高位有幾個1, n个表示, arr.length 必须 >= n 才能滿足utf-8的要求.
    private int count(int num) {
        int bits = 0;
        for (int idx = 7; idx >= 0; idx--)
            if ((num >> idx & 1) == 0) break;
            else bits++;
        return bits;
    }

}
