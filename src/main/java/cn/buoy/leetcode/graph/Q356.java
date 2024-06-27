package cn.buoy.leetcode.graph;

import java.util.HashSet;

public class Q356 {
    /**
     * https://www.youtube.com/watch?v=i2C2XfBBAWs
     * 簡單, 視頻.
     * 思路: 因爲是求一條 平行于 y軸 的 對稱線. 所以, 可以 用 最左 和 最右 必然是對稱線這個必然條件來解題.
     * 如何表達這個對稱線呢, 可以用 (x1 - x2)/2, 但是爲了代碼簡潔 使用 x1 - x2 或 x1 + x2, 也是可以表達對稱于某條y軸的條件.
     * 用最左 和 最右 找出, 對稱的條件後, 把 所有點都加入到set, 遍歷 set, 通過 前邊的條件 得到 某個點的對稱點, 一旦這個 對稱點 不存在于 set, 說明不存在這樣的線, 使 所有的點 都存在 都以某條y軸爲對稱線 的對稱點.
     */
    public boolean isReflected(int[][] points) {
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        HashSet<String> set = new HashSet<>();
        //有2个目的:
        //通過 max min 找到那个 可能的y軸, 不用除2.
        //将 points 转化为 set, 加速.
        for (int[] p : points) {
            max = Math.max(max, p[0]);
            min = Math.min(min, p[0]);
            String str = p[0] + "," + p[1];
            set.add(str);
        }
        // 用於判斷 對稱點 的條件. 用 (x1 - x2)/2 也可以(嗎?), 但是 這樣方便計算.
        int sum = max + min;
        for (int[] point : points) {
            // 检查 對稱點 是否存在于 set.
            String str = (sum - point[0]) + "," + point[1];
            if (!set.contains(str))
                return false;
        }
        return true;
    }
}
