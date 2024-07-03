package cn.buoy.leetcode.math;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Q368 {
    /**
     * 是簡單的, 視頻, 看註釋.
     * https://www.youtube.com/watch?v=YzCbp19FugM
     * https://www.youtube.com/watch?v=vn7eVpfW-mY 更好
     */
    // if we sort the array, every element in a divisibleSubset can be divisible by the element just before it.
    // for any element k, its largestDivisibleSubset that ends with k can be formed in the following way:
    // use element k after any one of the previous elements that is divisble
    public List<Integer> largestDivisibleSubset(int[] nums) {
        //dp[i] 表示, 由 i 作爲 最大的倍數的subset, 最長的 subset size.
        int[] dp = new int[nums.length]; // the length of largestDivisibleSubset that ends with element i
        //爲了得到 具體的 subset, 記錄着 能和 當前 index 組成最長 subset 的 上一個元素 的index. 当完成dp. 就可以先找到最大subset的那个元素, 然后一路往回找, 直到 某个元素可整除的元素的index 等于 -1 结束.
        int[] prevIndex = new int[nums.length]; // the previous index of element i in the largestDivisibleSubset ends with element i
        //排序, 爲了方便判斷 能否被之前的元素整除.
        Arrays.sort(nums);
        // 記錄 最大subset size, 爲了 當出現更大的 maxSubSetSize時, 更新 maxSizeIndex.
        int maxSubSetSize = 0;
        // 用於記錄 最大subset size 的index, 和 得到 他的pre node.
        int maxSizeIndex = -1;
        for (int i = 0; i < nums.length; i++) {
            // 默認
            dp[i] = 1;
            // 默認
            prevIndex[i] = -1;
            for (int j = i - 1; j >= 0; j--) {
                //得到 能整除的 最大的 dp
                if (nums[i] % nums[j] == 0 && dp[j] + 1 > dp[i]) {
                    dp[i] = dp[j] + 1;
                    prevIndex[i] = j;
                }
            }
            //update max subset 最大个数 及其的 index, 方便后边直接找回元素.
            if (dp[i] > maxSubSetSize) {
                maxSubSetSize = dp[i];
                maxSizeIndex = i;
            }
        }
        List<Integer> res = new ArrayList<Integer>();
        // 從最大的 maxSizeIndex 開始, 從 prevIndex[] 中找到 largest size subset 的所有元素.
        while (maxSizeIndex != -1) {
            res.add(nums[maxSizeIndex]);
            maxSizeIndex = prevIndex[maxSizeIndex];
        }
        return res;
    }
}
