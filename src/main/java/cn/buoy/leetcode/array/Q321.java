package cn.buoy.leetcode.array;

public class Q321 {

    /**
     * https://leetcode.com/problems/create-maximum-number/solutions/77285/share-my-greedy-solution/
     * 的comment里有一下连接
     * https://web.archive.org/web/20160120093629/http://algobox.org/create-maximum-number/
     * 一步步分解
     * <p>
     * https://www.youtube.com/watch?v=YYduNJfzWaA&t=192s
     * <p>
     * 参考:分2步走
     * 删除k个数, 不改变顺序, 使数字最小; leetcode 402
     * https://leetcode-cn.com/problems/remove-k-digits/solution/yi-diao-kwei-shu-zi-by-leetcode-solution/
     */
    public int[] maxNumber(int[] nums1, int[] nums2, int width) {
        int nums1Len = nums1.length;
        int nums2Len = nums2.length;
        int[] ans = new int[width];
        //遍历所有 nums1和nums2数组 各种长度组合可能.
        //Math.max(0, width - nums2Len)为了减少maxArray(nums1, i)的计算次数, 提早得到nums1至少加入n给数字.
        for (int i = Math.max(0, width - nums2Len); i <= width && i <= nums1Len; ++i) {
            int[] candidate = merge(maxArray(nums1, i), maxArray(nums2, width - i), width);
            if (greater(candidate, 0, ans, 0))
                ans = candidate;
        }
        return ans;
    }

    /**
     * 在限制合并后总长度为width的情况下, 不改变2个数组内各自元素相对位置情况下, 组合成一个最大的数字.
     *
     * @param nums1
     * @param nums2
     * @param width
     * @return
     */
    private int[] merge(int[] nums1, int[] nums2, int width) {
        int[] ans = new int[width];
        for (int i = 0, j = 0, r = 0; r < width; ++r)
            //每当比较完一次数字前缀大小, 记录后, 后移初始比较指针
            ans[r] = greater(nums1, i, nums2, j) ? nums1[i++] : nums2[j++];
        return ans;
    }

    /**
     * 这里是比较比较左侧开始比较2个数组, 第一个出现比较大的数字(数字前缀)的数组是哪一个.
     *
     * @param nums1
     * @param i     nums1初始比较的index
     * @param nums2
     * @param j     nums2初始比较的index
     * @return
     */
    public boolean greater(int[] nums1, int i, int[] nums2, int j) {
        while (i < nums1.length && j < nums2.length && nums1[i] == nums2[j]) {
            i++;
            j++;
        }
        //这里是列举nums1比nums2大的情况
        //如果前边遍历的都相等,j(nums2)又走到头了, 不管nums1是不是走到头, 都给nums1 great好了.
        //如果j(nums2)没有走到头, 且nums1当前元素又比nums2当前元素大, 那就是nums1 great.
        return j == nums2.length || (i < nums1.length && nums1[i] > nums2[j]);
    }

    /**
     * array元素顺序不变情况下, 特定subArrSize长度下, 子array可组成的最大数.
     *
     * @param nums
     * @param subArrSize
     * @return
     */
    public int[] maxArray(int[] nums, int subArrSize) {
        int len = nums.length;
        int[] ans = new int[subArrSize];
        for (int i = 0, stackSize = 0; i < len; ++i) {
            //len - i + stackSize > subArrSize, 用来判断剩下的元素(还在数组中还没遍历完的元素+栈中保存的元素)是否还足够构成题目条件的subArrSize长度
            //stackSize > 0, 表示栈中还有元素可以pop
            //ans[stackSize - 1] < nums[i], 表示底下一个元素还比当前遍历的arr元素小, 可能可以pop
            while (len - i + stackSize > subArrSize && stackSize > 0 && ans[stackSize - 1] < nums[i])
                stackSize--;
            //只要stackSize还没满于题目的subArrSize长度, 就可以往里加(前边已处理完是否需要pop的过程)
            if (stackSize < subArrSize)
                ans[stackSize++] = nums[i];
        }
        return ans;
    }
}
