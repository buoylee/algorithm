package cn.buoy.leetcode.stackandpq;

import java.util.*;

public class Q347 {
    /**
     * 这个比较清楚
     *
     * @param nums
     * @param k
     * @return
     */
    public int[] topKFrequent(int[] nums, int k) {

        List<Integer>[] bucket = new List[nums.length + 1];
        Map<Integer, Integer> frequencyMap = new HashMap<Integer, Integer>();

        //都丢进去map 统计, 数字n : 有几个
        for (int n : nums) {
            frequencyMap.put(n, frequencyMap.getOrDefault(n, 0) + 1);
        }

        for (int key : frequencyMap.keySet()) {
            int frequency = frequencyMap.get(key);
            //按 频率 作为 index 插入 bucket, 值为 那个数字
            if (bucket[frequency] == null) {
                bucket[frequency] = new ArrayList<>();
            }
            bucket[frequency].add(key);
        }

        List<Integer> res = new ArrayList<>();
        //从后到头 找k个
        for (int pos = bucket.length - 1; pos >= 0 && res.size() < k; pos--) {
            if (bucket[pos] != null) {
                res.addAll(bucket[pos]);
            }
        }
//        return res;
        int[] rr = new int[res.size()];
        for (int i = 0; i < res.size(); i++) {
            rr[i] = res.get(i);
        }
        return rr;
    }

    public int[] topKFrequent2(int[] nums, int k) {
        Map<Integer, Integer> valueToFreq = new HashMap<>();
        for (int num : nums) {
            valueToFreq.put(num, valueToFreq.getOrDefault(num, 0) + 1);
        }

        Map<Integer, List<Integer>> freqToValues = new HashMap<>();

        for (int value : valueToFreq.keySet()) {
            Integer freq = valueToFreq.get(value);
            if (!freqToValues.containsKey(freq)) {
                freqToValues.put(freq, new ArrayList<>());
            }
            freqToValues.get(freq).add(value);
        }

        int[] result = new int[k];
        for (int freq = nums.length, index = 0; freq >= 0 && index < k; freq--) {
            if (freqToValues.containsKey(freq)) {
                List<Integer> values = freqToValues.get(freq);
                for (Integer value : values) {
                    result[index++] = value;
                    if (index == k) {
                        break;
                    }
                }
            }
        }
        return result;
    }

    public List<Integer> topKFrequent3(int[] nums, int k) {

        List<Integer>[] bucket = new List[nums.length + 1];
        Map<Integer, Integer> frequencyMap = new HashMap<Integer, Integer>();

        //都丢进去map 统计, 数字n : 有几个
        for (int n : nums) {
            frequencyMap.put(n, frequencyMap.getOrDefault(n, 0) + 1);
        }

        for (int key : frequencyMap.keySet()) {
            int frequency = frequencyMap.get(key);
            //按 频率 作为 index 插入 bucket, 值为 那个数字
            if (bucket[frequency] == null) {
                bucket[frequency] = new ArrayList<>();
            }
            bucket[frequency].add(key);
        }

        List<Integer> res = new ArrayList<>();
        //从后到头 找k个
        for (int pos = bucket.length - 1; pos >= 0 && res.size() < k; pos--) {
            if (bucket[pos] != null) {
                res.addAll(bucket[pos]);
            }
        }
        return res;
    }


}
