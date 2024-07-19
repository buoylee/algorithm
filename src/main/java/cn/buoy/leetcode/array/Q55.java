package cn.buoy.leetcode.array;

public class Q55 {
    /**
     * 簡單, 視頻解釋的很具體, 但是下邊代碼更簡潔.
     * https://www.youtube.com/watch?v=yHU3nrww_ZA
     * 思路: 從後檢查, "前一個" 能否到達 "後一個",
     * 如果 可以, "前一個" 將作爲 下一次檢查的 "後一個",
     * 如果 不可以, 原先的 "後一個" 將保留, 我們繼續向前找, 能到達原來的 "後一個" 的元素, 直到頭.
     */
    public boolean canJump(int[] nums) {
        int n = nums.length;
        // 末位 默認能走到.
        int lastReach = n - 1;
        // 倒数第二位开始检查能否到达 lastReach
        for (int i = n - 2; i >= 0; i--) {
            // 如果当前index + 当前index最远可跳步数, 可到达 lastReach, 则将检查点设置为当前 index.
            if (i + nums[i] >= lastReach)
                lastReach = i;
        }
        //如果能到达index = 0, 说明有解.
        return lastReach <= 0;
    }
}
