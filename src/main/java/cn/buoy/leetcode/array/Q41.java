package cn.buoy.leetcode.array;

public class Q41 {
    public int firstMissingPositive(int[] nums) {
        int len = nums.length;
        for (int i = 0; i < len; i++)
            //index sorting
            //排除掉小于等于0, 且大于len的数,然后使用index sorting
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
