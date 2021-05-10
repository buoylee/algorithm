package cn.buoy.leetcode.array;

import java.util.TreeMap;

public class Q352 {
    /**
     * 大致做法:
     * https://www.youtube.com/watch?v=WRE7innGPJ4
     * <p>
     * treeMap:
     * https://www.youtube.com/watch?v=mfBPUTW0cZY
     */
    public class SummaryRanges {
        TreeMap<Integer, int[]> map;

        public SummaryRanges() {
            map = new TreeMap<>();
        }

        public void addNum(int val) {
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

        public int[][] getIntervals() {
            int[][] res = new int[map.size()][2];
            int i = 0;
            for (int[] a : map.values()) {
                res[i++] = a;
            }
            return res;
        }
    }
}
