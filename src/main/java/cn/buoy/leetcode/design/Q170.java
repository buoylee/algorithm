package cn.buoy.leetcode.design;

import java.util.*;

public class Q170 {
    /**
     * https://www.youtube.com/watch?v=BKL4fTaIbRw
     * 关键思路: 用map 的O(1) 用value反向查找index
     */
    class TwoSum {

        Map<Integer, Integer> map;
        List<Integer> list;
        int min;
        int max;

        public TwoSum() {
            map = new HashMap<>();
            list = new ArrayList<>();
            min = Integer.MAX_VALUE;
            max = Integer.MIN_VALUE;
        }

        /**
         * Add the number to an internal data structure..
         */
        public void add(int number) {
            //记录list index
            //这里比较巧妙, 重复会覆盖最后的index, 如果出现x + x的 情况, 第一个出现的x 就会匹配最后的, 不冲突, 可行.
//            map.put(number, list.size());
            //忘了 就用这个, 记得下边还有要替换的!
            //-------替换-------
            map.put(number, map.getOrDefault(number, 0) + 1);
            //---------------------

            list.add(number);
            //顺便求个min, max
            min = Math.min(min, number);
            max = Math.max(max, number);
        }

        /**
         * Find if there exists any pair of numbers which sum is equal to the value.
         */
        public boolean find(int value) {
            //corner case
            if (list.size() < 2) return false;
            if (value < min * 2 || value > max * 2) {
                return false;
            }

            for (int i = 0; i < list.size(); i++) {
                //遍历每个list value
                int n = list.get(i);
                //求 补数
                int complement = value - n;
                //用 补数 在 map 里找, 如果 map存在 且不是index 不是i自己, 则为true
//                if (map.containsKey(complement)) {
//                    if (i != map.get(complement)) {
//                    return true;
//                }
//                }
                //-------还要替换-------
                if (complement == n && map.get(complement) > 1)
                    return true;
                if (complement != n && map.containsKey(complement))
                    return true;
                //---------------------

            }
            return false;
        }
    }


    public class TwoSum3 {
        Map<Integer, Integer> hm;

        TwoSum3() {
            hm = new HashMap<Integer, Integer>();
        }

        // Add the number to an internal data structure.
        public void add(int number) {
            if (hm.containsKey(number)) {
                hm.put(number, 2);
            } else {
                hm.put(number, 1);
            }
        }

        // Find if there exists any pair of numbers which sum is equal to the value.
        public boolean find(int value) {
            Iterator<Integer> iter = hm.keySet().iterator();
            while (iter.hasNext()) {
                int num1 = iter.next();
                int num2 = value - num1;
                if (hm.containsKey(num2)) {
                    if (num1 != num2 || hm.get(num2) == 2) {
                        return true;
                    }
                }
            }
            return false;
        }
    }


    public class TwoSum2 {
        Set<Integer> sum;
        Set<Integer> num;

        TwoSum2() {
            sum = new HashSet<Integer>();
            num = new HashSet<Integer>();
        }

        // Add the number to an internal data structure.
        public void add(int number) {
            if (num.contains(number)) {
                sum.add(number * 2);
            } else {
                Iterator<Integer> iter = num.iterator();
                while (iter.hasNext()) {
                    sum.add(iter.next() + number);
                }
                num.add(number);
            }
        }

        // Find if there exists any pair of numbers which sum is equal to the value.
        public boolean find(int value) {
            return sum.contains(value);
        }
    }
}
