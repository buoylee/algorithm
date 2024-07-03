package cn.buoy.leetcode.math;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Q15 {
    /**
     * 簡單, 視頻
     * https://www.youtube.com/watch?v=zMfD98y7Pec
     * https://www.youtube.com/watch?v=2tbi1W7ce1c 更短
     * 思路: 先排序,
     * 利用sorted arr 越右越大的特點, 我們只需要從i右鄰開始找 low/high, 不但可以避免相同 value 的不同組合,
     * 還可以 在遍歷時, 當 low 增大時, high 只需要向左遞減尋找合適的組合.
     */
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new LinkedList<>();
        for (int i = 0; i < nums.length - 2; i++) {
            // 關鍵: 去掉 相同value, 不同 index 的組合.
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            int low = i + 1, high = nums.length - 1, sum = 0 - nums[i];
            while (low < high) {
                if (nums[low] + nums[high] == sum) {
                    // 即使找到, 也要繼續, 應該有其他組合的可能出現, 例如:  1 + 6 == 7; 2 + 5 == 7;
                    result.add(Arrays.asList(nums[i], nums[low], nums[high]));
                    //low high 去重
                    while (low < high && nums[low] == nums[low + 1]) low++;
                    while (low < high && nums[high] == nums[high - 1]) high--;
                    low++;
                    high--;
                } else if (nums[low] + nums[high] < sum) // 其實這裏也可以加 while (low < high && nums[low] == nums[low + 1]) 相同 value 的去重, 爲了代碼簡單先不加.
                    low++;
                else // 其實這裏也可以加 while (low < high && nums[high] == nums[high - 1]) 相同 value 的去重, 爲了代碼簡單先不加.
                    high--;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Q15 q15 = new Q15();
        List<List<Integer>> lists = q15.threeSum(new int[]{-1, 0, 1, 2, -1, -4});
    }
}
