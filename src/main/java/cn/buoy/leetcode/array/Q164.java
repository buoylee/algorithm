package cn.buoy.leetcode.array;

import java.util.Arrays;

public class Q164 {
    public static void main(String[] args) {
        int[] nums = {3, 6, 9, 1};

        Q164 q164 = new Q164();
        q164.maximumGap(nums);

    }

    /*
    https://www.youtube.com/watch?v=qN0qvtFbCYw
     */
    public int maximumGap(int[] num) {
        if (num == null || num.length < 2)
            return 0;
        // get the max and min value of the array
        int min = num[0];
        int max = num[0];
        for (int i : num) {
            min = Math.min(min, i);
            max = Math.max(max, i);
        }
        // the minimum possible gap, ceiling of the integer division
        //因为排除了最大最小, 又是的总有一个空桶, 即剩下元素 + 1, n - 2 + 1 = n - 1. 得出的gap就是最理想的.
        //the maximum gap will be no smaller than ceiling[(max - min ) / (n - 1)]; 产生空的桶, 会让最大gap出现在桶间
        int gap = (int) Math.ceil((double) (max - min) / (num.length - 1));
        //不同桶的所有最大值
        int[] bucketsMIN = new int[num.length - 1]; // store the min value in that bucket
        //不同桶的所有最小值
        int[] bucketsMAX = new int[num.length - 1]; // store the max value in that bucket
        Arrays.fill(bucketsMIN, Integer.MAX_VALUE);
        Arrays.fill(bucketsMAX, Integer.MIN_VALUE);
        // put numbers into buckets
        for (int i : num) {
            if (i == min || i == max)
                continue;
            int idx = (i - min) / gap; // index of the right position in the buckets
            bucketsMIN[idx] = Math.min(i, bucketsMIN[idx]);
            bucketsMAX[idx] = Math.max(i, bucketsMAX[idx]);
        }
        // scan the buckets for the max gap
        int maxGap = Integer.MIN_VALUE;
        int previous = min;
        for (int i = 0; i < num.length - 1; i++) {
            //方便比较, 不需要将最大,最小放入下边的 后桶最小减前桶最大? 真正原因是, 桶的数量可能刚好整除, 使得需要 + 1, 排除最大小这种方式不用另外处理.?
            //因为最大最小值肯定在最前最后, 所以这里不再考虑他们的位置关系.
            if (bucketsMIN[i] == Integer.MAX_VALUE && bucketsMAX[i] == Integer.MIN_VALUE)
                // empty bucket
                continue;
            // min value minus the previous value is the current gap
            maxGap = Math.max(maxGap, bucketsMIN[i] - previous);
            // update previous bucket value
            previous = bucketsMAX[i];
        }
        maxGap = Math.max(maxGap, max - previous); // updata the final max value gap
        return maxGap;
    }

//    int maximumGap2(vector<int>& nums) {
//        const int n = nums.size();
//        if(n<=1) return 0;
//        int maxE = *max_element(nums.begin(),nums.end());
//        int minE = *min_element(nums.begin(),nums.end());
//        double len = double(maxE-minE)/double(n-1);
//        vector<int> maxA(n,INT_MIN);
//        vector<int> minA(n,INT_MAX);
//        for(int i=0; i<n; i++) {
//            int index = (nums[i]-minE)/len;
//            maxA[index] = max(maxA[index],nums[i]);
//            minA[index] = min(minA[index],nums[i]);
//        }
//        int gap = 0, prev = maxA[0];
//        for(int i=1; i<n; i++) {
//            if(minA[i]==INT_MAX) continue;
//            gap = max(gap,minA[i]-prev);
//            prev = maxA[i];
//        }
//        return gap;
//    }
}
