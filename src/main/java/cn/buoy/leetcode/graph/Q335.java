package cn.buoy.leetcode.graph;

public class Q335 {

    /**
     *
     * 複習優先級 -1.
     *
     * https://www.youtube.com/watch?v=zo20CxMckjU&t=98s
     * 這個比較直觀, 但是寫法很糟.
     * @param lengths
     * @return
     */
    public boolean isSelfCrossing(int[] lengths) {
        for (int i = 3; i < lengths.length; i++) {
            //一直递减 相遇 (里边叉出去)
            if (i >= 3 && lengths[i] >= lengths[i - 2] && lengths[i - 1] <= lengths[i - 3]) return true;
            //相等 相遇
            if (i >= 4 && lengths[i - 1] == lengths[i - 3] && lengths[i] + lengths[i - 4] >= lengths[i - 2]) return true;
            //由大变小 相遇 (外边插进来)
            if (i >= 5 && lengths[i - 2] >= lengths[i - 4] && lengths[i - 5] + lengths[i - 1] >= lengths[i - 3] && lengths[i - 1] <= lengths[i - 3] && lengths[i - 4] + lengths[i] >= lengths[i - 2])
                return true;
        }

        return false;
    }

    /**
     * https://leetcode.com/problems/self-crossing/discuss/729133/How-to-explain-to-interviewer-335.-Self-Crossing
     * https://www.youtube.com/watch?v=W7MyjXDE5xg
     * 不好理解, 關鍵要找規律.
     */
    public boolean isSelfCrossing2(int[] lengths) {
        if (lengths.length <= 3) {
            return false;
        }
        int i = 2;
        // keep spiraling outward
        while (i < lengths.length && lengths[i] > lengths[i - 2]) {
            i++;
        }
        // 可以一直走到lengths尾, 說明沒有相交.
        if (i >= lengths.length) {
            return false;
        }

        // 這裏省略了 i == 2時, spiraling inward 的情況, 直接跳到了類似下邊 spiral 處理. 直接 ++, 直接到了 spiraling inward 這一步.

        // transition from spiraling outward to spiraling inward
        //这裏 其實是爲了處理 spiraling inward, 使得過往的 lengths[i - 1] 爲我們接下來的 判斷服務(因爲接下來, 對邊 只能不斷的減少, 也就是 對邊 和 對邊的對邊 都會限制length[i]的合法長度), 限制接下來一定要 lengths[i] < lengths[i - 2]
        //这个是等于或超过了 lengths[i - 2] - lengths[i - 4], i+1就要受到 lengths[i-1] - lengths[i-1] 的限制.
        if ((i >= 4 && lengths[i] >= lengths[i - 2] - lengths[i - 4]) ||
                // why? 这里 没有 i - 4, 如果不好想就写 >=
                (i == 3 && lengths[i] == lengths[i - 2])) {
            //update i-1 的限制
            // 這裏表達, 前3步 - 前1步, 就是 我們下1步[i+1]的上限.
            lengths[i - 1] -= lengths[i - 3];
        }

        i++;

        // keep spiraling inward
        while (i < lengths.length) {
            if (lengths[i] >= lengths[i - 2]) {
                return true;
            }
            i++;
        }
        return false;
    }


}
