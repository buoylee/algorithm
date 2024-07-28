package cn.buoy.leetcode.array;

public class Q283 {
    /**
     * 简单
     * https://www.youtube.com/watch?v=3gPn77w8ezw
     * 思路: 双指针,
     * i 表示遍历到的 index,
     * handlingIdx 表示 当前需要填充非0 的位置(当前有可能是0/非0), 0 ~ j -1 是已经检查过的, 不是0的数.
     * 先把 不是 0 的 num, 一个个地 从 handlingIdx == 0 开始 插入 nums, 遍历完 nums, 如果 handlingIdx 没到 nums.length - 1, 则一直 置0 到末尾.
     */
    public void moveZeroes(int[] nums) {
        if (nums == null || nums.length == 0) return;
        // 需要插入 `不为0的 num` 的位置
        int handlingIdx = 0;
        // 不是0的数, 从 index0 开始插入
        for (int num : nums) {
            if (num != 0)
                nums[handlingIdx++] = num;
        }
        // 处理完 nums 所有 非0元素后, 如果 handlingIdx 没到 nums.length - 1, 则一直 置0 到末尾.
        while (handlingIdx < nums.length)
            nums[handlingIdx++] = 0;
    }
}
