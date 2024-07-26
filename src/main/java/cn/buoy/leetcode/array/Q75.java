package cn.buoy.leetcode.array;

public class Q75 {
    /**
     * 思路简单, 視頻, 實際交換過程還是不那麼簡單
     * https://www.youtube.com/watch?v=aVOm2Kickys
     * https://www.youtube.com/watch?v=AFAwe8uBWv8 短
     * 思路: 3指针;
     * i 遍历 arr 當前 index;
     * low 表示 low 之前的元素都是0;
     * high 表示, high 之后的元素都是2.
     * 關鍵: 換位後, i 是否 ++ 是重點, 看註釋.
     */
    public void sortColors(int[] nums) {
        if (nums == null || nums.length < 2) return;
        int low = 0;
        int high = nums.length - 1;
        for (int i = low; i <= high; ) {
            if (nums[i] == 0) {
                swap(nums, i, low);
                // 關鍵: 因爲下邊 num == 2 時, swap 後, i 位置不變, 使得 i 必須優先換得 "非2的數", 所以走到這裏 "low 到 i-1" 之間一定只會是 1.
                i++;
                low++;
            } else if (nums[i] == 2) { // 關鍵: num == 2, i 不會變, 如果 [i] 換完還是 '2', 繼續與 [high] 交換, 直到 非2.
                swap(nums, i, high);
                high--;
            } else
                // 是 '1', 直接檢查 下一個 index
                i++;
        }
    }

    void swap(int[] nums, int low, int high) {
        int temp = nums[low];
        nums[low] = nums[high];
        nums[high] = temp;
    }

    /**
     * 直接統計 數字 個數
     */
    public void sortColors2(int[] nums) {
        int count0 = 0, count1 = 0, count2 = 0;
        for (int num : nums) {
            if (num == 0) count0++;
            else if (num == 1) count1++;
            else count2++;
        }

        for (int i = 0; i < nums.length; i++) {
            if (i < count0) nums[i] = 0;
            else if (i < count0 + count1) nums[i] = 1;
            else nums[i] = 2;
        }
    }
}
