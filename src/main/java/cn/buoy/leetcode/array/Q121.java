package cn.buoy.leetcode.array;

public class Q121 {
    /**
     * 簡單, 視頻
     * https://www.youtube.com/watch?v=JD8QaYVq5lQ
     * https://www.youtube.com/watch?v=TtBmqil3EMM 短
     * https://www.youtube.com/watch?v=USEFjOtuyA4 stock buy/sell collection
     * 思路: 用 curr - "curr 之前出現過的最低點", 從中選出最大的就是答案.
     * 需要一個 minPrice 時刻更新 "當前 value" 是否最低點. 然後用 curr - minPrice(小心 curr/minPrice 同時出現時, 只需要更新 minPrice)即可.
     */
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) return 0;
        int minPrice = prices[0];
        int maxProfit = 0;
        for (int i = 1; i < prices.length; i++) {
            // 出現更低點, 所以也就不需要去檢查 "是否出現更大利潤"
            if (prices[i] < minPrice)
                minPrice = prices[i];
            else if (prices[i] - minPrice > maxProfit) // 是否出現更大利潤
                maxProfit = prices[i] - minPrice;
        }
        return maxProfit;
    }
}
