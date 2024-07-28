package cn.buoy.leetcode.array;

public class Q321 {

    /**
     * 402, 删除k个数, 不改变 arr 顺序, 使数字最小
     * https://leetcode-cn.com/problems/remove-k-digits/solution/yi-diao-kwei-shu-zi-by-leetcode-solution/
     * <p>
     * 繁瑣, 稍微複雜, 拆分子問題後易理解, 視頻, 註釋
     * https://leetcode.com/problems/create-maximum-number/solutions/77285/share-my-greedy-solution/
     * https://web.archive.org/web/20160120093629/http://algobox.org/create-maximum-number/ 文字版
     * <p>
     * https://www.youtube.com/watch?v=YYduNJfzWaA&t=192s 好
     * <p>
     * 思路: 題目拆解爲 2個子問題:
     * 1. 刪除指定 x 個 arr 元素, 餘下的digit組成的最大數是多少
     * 2. 合併 2個 sub arr, 要求 sub arr 各自內部元素順序不變, "組成的最終 arr" 表示的數字最大是多少
     * 題目整體思路: 通過 遍歷所有 2個 num arr 生成的 不同長度的 sub arr(但是 2 sub arr 總長爲 width) 組成的 最大數字是多少(sub arr 各自內部元素順序不變 前提下). 更具體看子函數註釋.
     */
    public int[] maxNumber(int[] nums1, int[] nums2, int width) {
        int nums1Len = nums1.length;
        int nums2Len = nums2.length;
        int[] result = new int[width];
        // 遍历所有 nums1 和 nums2 分別取不同長度, 組合成 width 长度, 的各種可能.
        // Math.max(0, width - nums2Len) 意思是, 如果 nums2 太短的話, 至少需要在 nums1 取多長.
        for (int i = Math.max(0, width - nums2Len); i <= width && i <= nums1Len; ++i) {
            // 關鍵: 取總長爲 width == nums1 sub arr.len + nums2 sub arr.len(各自 sub arr 設定 len 下, 最大的可能) 組成最終 arr 表示的數字的 max.
            int[] candidate = merge(maxArray(nums1, i), maxArray(nums2, width - i), width);
            if (greater(candidate, 0, result, 0))
                result = candidate;
        }
        return result;
    }

    /**
     * 關鍵: 子問題, 在 width == nums1.len + nums2.len 的情况下, 不改变2个数组内各自元素相对位置情况下, 整合成一个最大的数字.
     * 注意: 這裏的 width == nums1.len + nums2.len, 才可以使用此合併算法.
     * (所以 傳入的 width 是多餘的?)
     */
    private int[] merge(int[] nums1, int[] nums2, int width) {
        int[] result = new int[width];
        for (int nums1StartIdx = 0, nums2StartIdx = 0, resultIdx = 0; resultIdx < width; ++resultIdx)
            // 每当比较完一次数字前缀大小, 取greater arr 的 head 從 index0 開始放入 result, 取完后指针++
            result[resultIdx] = greater(nums1, nums1StartIdx, nums2, nums2StartIdx) ? nums1[nums1StartIdx++] : nums2[nums2StartIdx++];
        return result;
    }

    /**
     * 比較 nums1 是否 greater than nums2
     * 这里是從左侧开始比较2个数组, 第一个出现比较大的数字(数字前缀) 或 前綴一樣的情況下 len 較長的数组 就是 greater.
     *
     * @param nums1
     * @param num1StartIdx 進行比較的 nums1 的起始 index
     * @param nums2
     * @param num2StartIdx 進行比較的 nums2 的起始 index
     * @return
     */
    public boolean greater(int[] nums1, int num1StartIdx, int[] nums2, int num2StartIdx) {
        while (num1StartIdx < nums1.length && num2StartIdx < nums2.length && nums1[num1StartIdx] == nums2[num2StartIdx]) {
            num1StartIdx++;
            num2StartIdx++;
        }
        // 如果前边遍历的都相等, num2StartIdx 又走到头了, 不管 nums1 是不是走到头, 都给 nums1 greater 好了(因爲有可能相等).
        // 如果 num2StartIdx 没有走到头, 且 nums1 当前元素又比 nums2 当前元素大, nums1 greater.
        return num2StartIdx == nums2.length || (num1StartIdx < nums1.length && nums1[num1StartIdx] > nums2[num2StartIdx]);
    }

    /**
     * 關鍵: 子問題, nums 元素間 顺序不变 情况下, 取 subArrLen 長度的 subarray 可组成的最大数.
     * 思路:
     *
     * @param nums
     * @param subArrLen 需要返回的 sub array 目標長度.
     * @return
     */
    public int[] maxArray(int[] nums, int subArrLen) {
        int len = nums.length;
        int[] stackAsResult = new int[subArrLen];
        for (int i = 0, stackSize = 0; i < len; ++i) {
            // len - i + stackSize > subArrLen, 用来判断剩下的元素(还在数组中还没遍历的元素+栈中保存的元素)是否还足够构成题目条件的 subArrLen 长度
            // stackSize > 0, 表示栈中还有元素可以 pop
            // stackAsResult[stackSize - 1] < nums[i], 表示 stack top < curr num, 可能可以 pop
            while (len - i + stackSize > subArrLen && stackSize > 0 && stackAsResult[stackSize - 1] < nums[i])
                stackSize--;
            // stackSize < subArrSize 长度, 就加入 curr num, 然後 stackSize++
            if (stackSize < subArrLen)
                stackAsResult[stackSize++] = nums[i];
        }
        return stackAsResult;
    }
}
