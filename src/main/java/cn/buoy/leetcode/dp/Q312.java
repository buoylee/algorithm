package cn.buoy.leetcode.dp;

public class Q312 {
    /**
     * https://www.youtube.com/watch?v=Ci39lcoLbyw
     * 视频简述: 第一层for 控制距离, 第2层控制left, 第3层遍历left+1, right-1 之间的所有可能数
     * 视频用的循環.
     * 代碼用的遞歸, 比較簡單直觀.
     * dp[i][j], 表示的是, 戳破 i+1 到 j-1 範圍內的氣球, 最大的結果.
     * dp[i] = for 任意一个点 作为 最后戳破的 num (会得到戳破的值 left * num * right), 然后在 这个点切分的 2个区域里 递归.
     */
    public int maxCoins(int[] nums) {
        // 爲了方便計算, 首尾各加入一個 1.
        int[] newNums = new int[nums.length + 2];
        // 从1开始填入数字
        // 細節: 如果不排除掉0, 如果先刺到0的鄰居, 結果只會更小(乘積等於0).
        int index = 1;
        for (int ele : nums)
            if (ele > 0)
                newNums[index++] = ele;
        newNums[0] = newNums[index++] = 1;
        // 關鍵: dp[i][j], 表示的是, 戳破 i+1 到 j-1 範圍內的氣球, 最大的結果.
        // 因为 需要 0~index, 所以这里 index 最后又++一次, 刚好可以作为 len.
        int[][] dp = new int[index][index];
        return burst(dp, newNums, 0, index - 1); // len - 1
    }

    /**
     * 求 dp[left][right]
     *
     * @param dp
     * @param nums
     * @param left  不需要戳破的左邊界
     * @param right 不需要戳破的右邊界
     * @return
     */
    public int burst(int[][] dp, int[] nums, int left, int right) {
        // 中间的球都破了.
        if (left + 1 == right) return 0;
        // 有就返回.
        if (dp[left][right] > 0) return dp[left][right];
        int ans = 0;
        // 關鍵: 得到 dp[left][right], 必須戳破所有 i+1 到 j-1 範圍內的氣球.
        // 所以 恰好上一步 就是 戳破 之間 某一球(lastBurst), 使得 之間 沒球,
        // 我們 遍歷 dp[left][lastBurst] + dp[lastBurst][right] + nums[lastBurst] * nums[lastBurst] * nums[lastBurst], 找到max那個就是答案.
        for (int i = left + 1; i < right; ++i)
            ans = Math.max(ans,
                    nums[left] * nums[i] * nums[right] //最後一個戳破的分數
                            + burst(dp, nums, left, i) // 左邊
                            + burst(dp, nums, i, right)); // 右邊
        dp[left][right] = ans;
        return ans;
    }

    //todo leetcode 不算靠前, 有時間再說

    public static void main(String[] args) {
        Q312 q312 = new Q312();
        int i = q312.maxCoins(new int[]{3, 1, 5, 8});
    }
}
