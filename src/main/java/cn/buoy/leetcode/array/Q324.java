package cn.buoy.leetcode.array;

import java.util.Arrays;
import java.util.Collections;

public class Q324 {
    /**
     * 知道思路就簡單, 視頻, 註釋
     * https://www.youtube.com/watch?v=GeqJmahF5JA 失效
     * https://www.youtube.com/watch?v=M2pnGMBo9Vs
     * 第一种,很好理解, 先排序, 从中位数分开(左边多1), 轮流取 左区间最小 右区间最大 插入res
     * 思路: 分2半, 按顺序 接个插入;
     * 特例: [4,5,5,6], wrong, 不能使用上述方法.
     * 这时候发现如果后端差入的位置在前就不会有邻接相等的情况, 但是上升下降 顺序反了([5, 4, 6, 5]).
     * 最后发现反转即可([5, 6, 4, 5]).
     * 但是空间为O(n)
     * 最后优化为下边代码.
     */
    public void wiggleSort(int[] nums) {
        int[] val = Arrays.copyOf(nums, nums.length);
        Arrays.sort(val);
        int idx = 0;
        // 能发现奇偶情况是不同的. 从上邊 wrong answer 可以直到.
        // 偶数 间隔插 就可以了.
        if (nums.length % 2 == 1) {
            // 先把 較小的一半 插入 偶數位. 注意, 這裏最後一位是 len - 1 (中位數)
            for (int i = 0; i < nums.length; i += 2)
                nums[i] = val[idx++];
            // 再把 較大的一半 插入 奇數位
            for (int i = 1; i < nums.length; i += 2)
                nums[i] = val[idx++];
        } else {
            // 關鍵: 如果是 偶数, 因爲有可能出現重複元素的情況, 选`中间的数`安排在2邊的 插法 间隔相同的可能更低. 但是題目說明肯定有解, 所以使用以下插法.
            // 先把 較小的一半 插入 奇數位
            for (int i = 1; i < nums.length; i += 2)
                nums[i] = val[idx++];
            // 再把 較大的一半 插入 偶數位
            for (int i = 0; i < nums.length; i += 2)
                nums[i] = val[idx++];
            // 整體 reverse
            for (int i = 0, j = val.length - 1; i < j; i++, j--) {
                int tmp = nums[i];
                nums[i] = nums[j];
                nums[j] = tmp;
            }
        }
    }

    /**
     * 根据上边得出更简单的写法
     *
     * @param nums
     */
    public void wiggleSort3(int[] nums) {
        int[] val = Arrays.copyOf(nums, nums.length);
        Arrays.sort(val);
        int idx = val.length - 1;
        for (int i = 1; i < nums.length; i += 2) nums[i] = val[idx--];
        for (int i = 0; i < nums.length; i += 2) nums[i] = val[idx--];
    }


    // the idea is to sort the input array and divide into two equal parts; 0 to mid & mid+ 1 to end
    // and then arrange the elements (using auxillary array) using reverse order for both parts (mid to 0 and end to mid + 1).
    // an example : Input array [1, 5, 1, 1, 6, 4] , after sorting it becomes [1,1,1,4,5,6]
    // the two parts of the array are [1,1,1] & [4,5,6]
    // now take last values of these two arrays i.e. index 2(o-based) from both ; which gives us 1 & 6 respectively
    // now [1,6,...] becomes the first two values in the final/input array
    // now again take previous chosen index - 1, which are index 1 from both ; which gives us 1 & 5
    // now [1,6,1,5] becomes our array
    // now again take index - 1 , which is index 0 from both now; this gives us 1 & 4
    // now [1,6,1,5,1,4] becomes our answer
    public void wiggleSort2(int[] nums) {

        //sort the array
        Arrays.sort(nums);

        // find mid point of array
        //使 偶数时, mid 在中位数的前边那个.
        //奇数时, mid 还是 中位数
        int mid = (nums.length - 1) / 2;
        int right = nums.length - 1;

        //aux array to temp store the values
        int[] result = new int[right + 1];
        int counter = 0;

        // select values from two parts of the array and arrange them in aux array
        while (mid >= 0 || right > (nums.length - 1) / 2) {

            if (counter % 2 == 0) {
                result[counter] = nums[mid];
                mid--;
            } else {
                result[counter] = nums[right];
                right--;
            }

            counter++;
        }

        //now store back these values in input/original array
        for (int i = 0; i < nums.length; i++) {
            nums[i] = result[i];
        }
    }


    //todo 可能有其他算法 3色
}
