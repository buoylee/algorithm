package cn.buoy.leetcode.binarysearch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Q315 {

    /**
     * https://www.youtube.com/watch?v=wjO48chdyBk
     * 視頻一講就明.
     * 思路: 轉化爲, 將原數組, 元素從後到前, 加入到一個新數字, 使得新數組升序排序, 而插入的index, 就是原數組對應元素的結果.
     */
    public List<Integer> countSmaller(int[] nums) {
        Integer[] result = new Integer[nums.length];
        List<Integer> tempList = new ArrayList<>();
        // 原數組, 元素從後到前
        for (int i = nums.length - 1; i >= 0; i--) {
            // 加入到一個新數字, 使得新數組升序排序
            int index = findIndex(tempList, nums[i]);
            // 插入的index, 就是原數組對應元素的結果
            result[i] = index;
            tempList.add(index, nums[i]);
        }
        return Arrays.asList(result);
    }

    // 找到 該元素 在當前 tempList 中應該插入的index
    private int findIndex(List<Integer> tempList, int target) {
        if (tempList.size() == 0) return 0;
        int start = 0;
        int end = tempList.size() - 1;
        //最大最小, 直接插頭尾.
        if (tempList.get(end) < target) return end + 1;
        if (tempList.get(start) >= target) return 0;

        while (start + 1 < end) {
            int mid = (end - start) / 2 + start;
            if (tempList.get(mid) < target) {
                start = mid + 1;
            } else {
                end = mid;
            }
        }
        if (tempList.get(start) >= target) return start;
        return end;
    }


    /**
     * https://www.youtube.com/watch?v=AeyUmjk4HGQ
     * 用 merge sort 每一次的换位, 每一次子merge 出现换位时, (下层merge到上层, 下层index 到 上层的差别多少就++几次)换到高位的value 的index 都会++
     */
    public List<Integer> countSmaller2(int[] nums) {
        List<Integer> list = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return list;
        }

        int[] helper = new int[nums.length];
        //index 对应 index
        int[] index = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            index[i] = i;
        }

        int[] res = new int[nums.length];
        mergeSort(nums, res, index, helper, 0, nums.length - 1);
        for (int i = 0; i < res.length; i++) {
            list.add(res[i]);
        }
        return list;
    }

    private void mergeSort(int[] nums, int[] res, int[] index, int[] helper, int left, int right) {
        //分到剩1个, 返回
        if (left == right) {
            return;
        }
        //且2半, 定义好边界往下传.
        int mid = left + (right - left) / 2;
        //分别排序好前后2个arr
        mergeSort(nums, res, index, helper, left, mid);
        mergeSort(nums, res, index, helper, mid + 1, right);
        //将前后2半 最后 merge 排序 返回.
        merge(nums, res, index, helper, left, mid, right);
    }

    //多传一个mid, 方便划分.
    private void merge(int[] nums, int[] res, int[] index, int[] helper, int left, int mid, int right) {
        //现在付给helper[]的, 是 左右2部分 `分别` 排好序的arr, 现在正要merge2部分到一起.
        for (int i = left; i <= right; i++) {
            helper[i] = index[i];
        }

        //左右2部分 起始indx
        int i = left, j = mid + 1;
        while (i <= mid) {
            // when moves i
            if (j > right || nums[helper[i]] <= nums[helper[j]]) {
                //关键! 后半部的位置 都是 在前半部的 后边的! 所以 后半部 放入到 index(排序后的arr) 的时候 并不需要 加上 视频中的rightCount(即 j - mid - 1)!
                //注意这里的代码, helper[], index[] 都是存的 原nums的 index! 不是原nums的元素. 类似 之前 讲 arr算法 视频的 只存引用的 例子.
                res[helper[i]] += j - mid - 1;
                //将 前部分最小的 放入 排好序的index[] 中.
                index[left++] = helper[i++];
            } else {
                //将 后部分最小的 放入 排好序的index[] 中.
                index[left++] = helper[j++];
            }
        }
    }

}
