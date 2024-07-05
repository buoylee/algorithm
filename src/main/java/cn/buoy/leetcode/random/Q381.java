package cn.buoy.leetcode.random;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class Q381 {
    /**
     * 類似380題, 有重複元素.
     * https://www.youtube.com/watch?v=mRTgft9sBhA
     * 思路: 在 380基礎上, 需要在 map 的 value 改爲存放 list(index); 而在 list 中, 需要存放 int[2]{value, map.value.index}
     * 例如:
     * map: {1: [1,3], 2: [2], 3: [0,4]}
     * list: [｛3,0｝, ｛1,0｝, ｛2,0｝, ｛1,1｝ ,｛3,1｝]
     * 刪除: 看註釋
     */
    class RandomizedCollection {
        //有重复元素, index 用 list 存
        //value: list<index>
        Map<Integer, List<Integer>> valToListIdxsMap;
        //对比 无重复元素, list中元素, 除了存value, 还要存 该value对应 map[value]的arr 的index(index[map[value]] 就是 list 元素的 index)
        //多存map 中的 list 的 index, 是为了 快速找到 map的 list 对应元素 ,使删除复杂度为O(1)
        List<int[]> valToIdxList;

        /**
         * Initialize your data structure here.
         */
        public RandomizedCollection() {
            valToListIdxsMap = new HashMap<>();
            valToIdxList = new ArrayList<>();
        }

        /**
         * Inserts a value to the collection. Returns true if the collection did not already contain the specified element.
         */
        public boolean insert(int val) {
            List<Integer> idxList = valToListIdxsMap.get(val);
            if (idxList == null) {
                idxList = new ArrayList<>();
                valToListIdxsMap.put(val, idxList);
            }
            //新加入 map list 的 index
            int subIdx = idxList.size();
            int idx = valToIdxList.size();
            idxList.add(idx);
            //list 需要多存 上边的index
            valToIdxList.add(new int[]{val, subIdx});
            return subIdx == 0;
        }

        /**
         * Removes a value from the collection. Returns true if the collection contained the specified element.
         */
        public boolean remove(int val) {
            List<Integer> idxList = valToListIdxsMap.get(val);
            if (idxList == null || idxList.isEmpty())
                return false;

            //删除目標 value 的 maplist 的最后一个value(list的 index)
            int delSubIdx = idxList.size() - 1;
            int delIdx = idxList.remove(delSubIdx);

            //list 先remove(取出) 最后一个元素 last, 例: {value, map list index }
            //last 这个元素是可能要保留的, 放到 map list 删掉 的那个 value(list 的 index)中. 或者她就是要刪除的那個.
            int lastIdx = valToIdxList.size() - 1;
            int[] last = valToIdxList.remove(lastIdx);
            if (delIdx == lastIdx)
                return true;

            // 替代元素(last)的maplist內的index 更新成 被删元素的 list index
            List<Integer> lastIdxList = valToListIdxsMap.get(last[0]);
            lastIdxList.set(last[1], delIdx);
            //再把list last元素 放到 list删除的元素 的位置.
            valToIdxList.set(delIdx, last);
            return true;
        }

        /**
         * Get a random element from the collection.
         * 从list 随机拿一个
         */
        public int getRandom() {
            int randomIdx = ThreadLocalRandom.current().nextInt(valToIdxList.size());
            int[] randomEntry = valToIdxList.get(randomIdx);
            return randomEntry[0];
        }
    }
}

