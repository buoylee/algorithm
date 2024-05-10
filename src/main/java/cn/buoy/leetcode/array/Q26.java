package cn.buoy.leetcode.array;

public class Q26 {

    /*
    定义一个指针id, 表示当前检查不重复元素个数, 也是当前指针是已完成指针的后一位, 也是,如果再有不同元素被发现时,被覆盖的指针位置;
    当找到当前遍历指针与前一位置,不同指时, nums[id] = nums[i], id++
     */
    public int removeDuplicates(int[] nums) {
        int n = nums.length;
        if (n < 2)
            return n;
        int id = 1;
        for (int i = 1; i < n; ++i)
            if (nums[i] != nums[i - 1])
                nums[id++] = nums[i];
        return id;
    }

    public int removeDuplicates2(int[] nums) {
        int n = nums.length;
        int count = 0;
        for (int i = 1; i < n; i++) {
            if (nums[i] == nums[i - 1])
                count++;
            else
                nums[i - count] = nums[i];
        }
        return n - count;
    }
}
