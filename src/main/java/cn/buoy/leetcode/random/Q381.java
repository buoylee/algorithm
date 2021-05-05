package cn.buoy.leetcode.random;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class Q381 {
    /**
     * https://www.youtube.com/watch?v=mRTgft9sBhA
     * 和 381 只差在 有重复元素.
     */
}

class RandomizedCollection {
    //有重复元素, index 用 list 存
    //value: list<index>
    Map<Integer, List<Integer>> valToIdxList;
    //对比 无重复元素, list中元素, 除了存value, 还要存 该value对应 map[value]的arr 的index(index[map[value]] 就是 list 元素的 index)
    //多存map 中的 list 的 index, 是为了 快速找到 map的 list 对应元素 ,使删除复杂度为O(1)
    List<int[]> valSubIdxList;

    /**
     * Initialize your data structure here.
     */
    public RandomizedCollection() {
        valToIdxList = new HashMap<>();
        valSubIdxList = new ArrayList<>();
    }

    /**
     * Inserts a value to the collection. Returns true if the collection did not already contain the specified element.
     */
    public boolean insert(int val) {
        List<Integer> idxList = valToIdxList.get(val);
        if (idxList == null) {
            idxList = new ArrayList<>();
            valToIdxList.put(val, idxList);
        }
        //新加入 map list 的 index
        int subIdx = idxList.size();
        int idx = valSubIdxList.size();
        idxList.add(idx);
        //list 需要多存 上边的index
        valSubIdxList.add(new int[]{val, subIdx});
        return subIdx == 0;
    }

    /**
     * Removes a value from the collection. Returns true if the collection contained the specified element.
     */
    public boolean remove(int val) {
        List<Integer> idxList = valToIdxList.get(val);
        if (idxList == null || idxList.isEmpty()) {
            return false;
        }
        //删除map list 最后一个value, 对应list的 index
        int delSubIdx = idxList.size() - 1;
        int delIdx = idxList.remove(delSubIdx);
        int lastIdx = valSubIdxList.size() - 1;
        //list 也remove 最后一个元素, 例: {value, map list index }
        //last这个元素是要保留的, 放到 map list 删掉 的那个 value(list 的 index)中.
        int[] last = valSubIdxList.remove(lastIdx);
        if (delIdx == lastIdx) {
            return true;
        }
        //与被删元素交换(原末尾元素) 对应 value 的map list
        //last[0] 是value
        List<Integer> lastIdxList = valToIdxList.get(last[0]);
        //last[1] 是map list index 更新到 被删元素的 list index
        lastIdxList.set(last[1], delIdx);
        //再把list last元素 放到 list删除的元素 的位置.
        valSubIdxList.set(delIdx, last);
        return true;
    }

    /**
     * Get a random element from the collection.
     * 从list 随机拿一个
     */
    public int getRandom() {
        int randomIdx = ThreadLocalRandom.current().nextInt(valSubIdxList.size());
        int[] randomEntry = valSubIdxList.get(randomIdx);
        return randomEntry[0];
    }
}

/**
 * Your RandomizedCollection object will be instantiated and called as such:
 * RandomizedCollection obj = new RandomizedCollection();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 * <p>
 * <p>
 * val, [sIdx: idx]
 * <p>
 * [idx:<val, sIdx>]
 * <p>
 * <p>
 * val, [sIdx: idx]
 * <p>
 * [idx:<val, sIdx>]
 * <p>
 * <p>
 * val, [sIdx: idx]
 * <p>
 * [idx:<val, sIdx>]
 * <p>
 * <p>
 * val, [sIdx: idx]
 * <p>
 * [idx:<val, sIdx>]
 * <p>
 * <p>
 * val, [sIdx: idx]
 * <p>
 * [idx:<val, sIdx>]
 * <p>
 * <p>
 * val, [sIdx: idx]
 * <p>
 * [idx:<val, sIdx>]
 * <p>
 * <p>
 * val, [sIdx: idx]
 * <p>
 * [idx:<val, sIdx>]
 * <p>
 * <p>
 * val, [sIdx: idx]
 * <p>
 * [idx:<val, sIdx>]
 * <p>
 * <p>
 * val, [sIdx: idx]
 * <p>
 * [idx:<val, sIdx>]
 * <p>
 * <p>
 * val, [sIdx: idx]
 * <p>
 * [idx:<val, sIdx>]
 * <p>
 * <p>
 * val, [sIdx: idx]
 * <p>
 * [idx:<val, sIdx>]
 * <p>
 * <p>
 * val, [sIdx: idx]
 * <p>
 * [idx:<val, sIdx>]
 * <p>
 * <p>
 * val, [sIdx: idx]
 * <p>
 * [idx:<val, sIdx>]
 * <p>
 * <p>
 * val, [sIdx: idx]
 * <p>
 * [idx:<val, sIdx>]
 */

/**

 val, [sIdx: idx]

 [idx:<val, sIdx>]

 */
