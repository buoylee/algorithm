package cn.buoy.leetcode.array;

public class Q41 {
    //fixme [1, 2, 3, 4, 5]
    //       0  1  2  3  4
    //index + 1 == nums[index]

    public int firstMissingPositive(int[] nums) {
        int len = nums.length;
        for (int i = 0; i < len; i++)
            //index sorting
            //排除掉小于等于0, 且大于len的数,然后使用index sorting; nums[i]如果不是对应位置的值, 那么应该要把它放在nums[i] - 1这个位置上.
            while (nums[i] > 0 && nums[i] <= len && nums[nums[i] - 1] != nums[i]) {
                swap(nums, i, nums[i] - 1);
            }

        //从头取出与值nums[index] 与 index + 1 不等的第一个数, 加一返回.
        for (int i = 0; i < len; i++)
            if (nums[i] != i + 1)
                return i + 1;

        return len + 1;
    }

    private void swap(int[] A, int i, int j) {
        int temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }
}
