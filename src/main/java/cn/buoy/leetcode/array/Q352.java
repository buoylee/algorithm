package cn.buoy.leetcode.array;

import java.util.*;

public class Q352 {
    /**
     * 簡單, 視頻
     * 大致做法:
     * https://www.youtube.com/watch?v=WRE7innGPJ4
     * <p>
     * https://www.youtube.com/watch?v=PeAN0lWOzak
     * 思路: 關鍵: 使用 treeSet 存儲 num,
     * addNum(): 直插 num;
     * getIntervals(): 遍历 treeSet,
     * 如果 curr num == latest interval(用 int[2] 表示開始/結束的區間) end + 1,
     * 說明是連續, 更新 latest interval end == end + 1, 例如: [start, end + 1]
     * 不等, 說明補是連續, 加入 int[curNum, curNum] 即可.
     */
    public class SummaryRanges {
        private Set<Integer> numbers;

        public SummaryRanges() {
            // 關鍵: 用 tree set
            numbers = new TreeSet<>();
        }

        public void addNum(int value) {
            numbers.add(value);
        }

        public int[][] getIntervals() {
            // We are not sure of the size upfront
            List<int[]> disjointInterval = new ArrayList<>();
            // 因爲使用了 treeSet, 只需要不斷往後檢查即可.
            for (int n : numbers) {
                int size = disjointInterval.size();
                // 如果下一个value == 上一个 end + 1, 就是连续; 否则新开范围数组.
                if (size > 0 && disjointInterval.get(size - 1)[1] + 1 == n) {
                    disjointInterval.get(size - 1)[1] = n; // [Merge] Update the right end state
                } else
                    disjointInterval.add(new int[]{n, n}); // [New entry] Create a new interval
            }
            return disjointInterval.toArray(new int[0][]);
        }

        /**
         * https://www.youtube.com/watch?v=a7xGgDhRuA0
         * 思路一样, 检查前一个和后一个,
         * 分不同情况:
         * [value, value];
         * [start++, end];
         * [start, end++];
         * [start1, value--], value, [value++, end2]合并
         */
        private final TreeMap<Integer, Integer> orderedMap = new TreeMap<>();

//            public SummaryRanges() {
//                this.orderedMap = new TreeMap<>();
//            }

        public void addNum2(final int value) {
            if (!this.orderedMap.containsKey(value)) {
                final Map.Entry<Integer, Integer> low = this.orderedMap.lowerEntry(value);

                if (low == null) {
                    //无前, 有后, [start++, end]
                    if (this.orderedMap.containsKey(value + 1)) {
                        this.orderedMap.put(value, this.orderedMap.get(value + 1));
                        this.orderedMap.remove(value + 1);
                    } else {
                        //无前, 无后, [value, value]
                        this.orderedMap.put(value, value);
                    }
                } else {
                    if (value > low.getValue()) {
                        //有前, 无后(先做有前判断, 接着看有后再处理), [start, end++]
                        if (low.getValue() == value - 1) {
                            int right = value;

                            //有前, 有后, [start1, value--], value, [value++, end2]合并
                            if (this.orderedMap.containsKey(value + 1)) {
                                right = this.orderedMap.get(value + 1);
                                this.orderedMap.remove(value + 1);
                            }

                            this.orderedMap.put(low.getKey(), right);
                        } else {
                            //无前, 有后(先做有前判断, 接着看有后再处理), [start++, end]
                            if (this.orderedMap.containsKey(value + 1)) {
                                this.orderedMap.put(value, this.orderedMap.get(value + 1));
                                this.orderedMap.remove(value + 1);
                            } else {
                                //无前, 无后(先做有前判断, 接着看有后再处理), [value, value]
                                this.orderedMap.put(value, value);
                            }
                        }
                    }
                }
            }
        }

        public int[][] getIntervals2() {
            final int[][] intervals = new int[this.orderedMap.size()][2];

            int i = 0;

            for (final Map.Entry<Integer, Integer> interval : this.orderedMap.entrySet()) {
                intervals[i][0] = interval.getKey();
                intervals[i++][1] = interval.getValue();
            }

            return intervals;
        }


        /**
         * 先不用看
         * treeMap:
         * https://www.youtube.com/watch?v=mfBPUTW0cZY (失效)
         * get会比较快
         */
        TreeMap<Integer, int[]> map = new TreeMap<>();

//        public SummaryRanges2() {
//            map = new TreeMap<>();
//        }

        public void addNum3(int val) {
            //重复
            if (map.containsKey(val)) return;
            //返回小于val的最大key
            Integer lowerKey = map.lowerKey(val);
            //返回大于val的最小key
            Integer higherKey = map.higherKey(val);
            if (lowerKey != null && higherKey != null && val == map.get(lowerKey)[1] + 1
                    && val == map.get(higherKey)[0] - 1) {
                //如果 low 的 右边界 +1 == value == high的 左边界 -1, 就可以连起来
                //这里是合并到low, remove掉high
                map.get(lowerKey)[1] = map.get(higherKey)[1];
                map.remove(higherKey);
            } else if (lowerKey != null && val <= map.get(lowerKey)[1] + 1) {
                //只有1边, 优先, 是low的话, 合到low的右边界.
                map.get(lowerKey)[1] = Math.max(val, map.get(lowerKey)[1]);
            } else if (higherKey != null && val == map.get(higherKey)[0] - 1) {
                //反则, 合到左边界
                map.put(val, new int[]{val, map.get(higherKey)[1]});
                map.remove(higherKey);
            } else {
                //什么都没有就 自己单独一个区间
                map.put(val, new int[]{val, val});
            }
        }

        public int[][] getIntervals3() {
            int[][] res = new int[map.size()][2];
            int i = 0;
            for (int[] a : map.values()) {
                res[i++] = a;
            }
            return res;
        }
    }
}
