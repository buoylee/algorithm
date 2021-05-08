package cn.buoy.leetcode.random;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class Q380 {
    /**
     * https://www.youtube.com/watch?v=4neZY8NIylk
     * list: I: 1; D: N; R: 1
     * map : I: 1; D: 1; R: N
     * <p>
     * map 保存 list 的 value:index, 用来 快速删除 list的 value, 为了random方便(直接取list size), 将要删除的元素, 与末尾元素交换 再删除.
     */
    class RandomizedSet {
        HashMap<Integer, Integer> valToInd;
        List<Integer> list;
        int ind = 0;

        /**
         * Initialize your data structure here.
         */
        public RandomizedSet() {
            valToInd = new HashMap<>();
            list = new ArrayList<>();
        }

        /**
         * Inserts a value to the set. Returns true if the set did not already contain the specified element.
         * 先在map 找是否存在, 不在再插入list, 并在map中保存 list中的 (value:index)关系.
         */
        public boolean insert(int val) {
            if (valToInd.containsKey(val)) return false;
            list.add(val);
            valToInd.put(val, list.size() - 1);
            return true;
        }

        /**
         * Removes a value from the set. Returns true if the set contained the specified element.
         * map先判断是否存在value, 存在则与末尾元素交换 再删除list末尾. map 删除对应value, 还要更新原末尾元素的index.
         */
        public boolean remove(int val) {
            //需要删除的index
            int ind = valToInd.getOrDefault(val, -1);
            if (ind == -1) return false;
            Collections.swap(list, ind, list.size() - 1);
            //原末尾元素.
            int swappedWith = list.get(ind);
            //更新原末尾元素的index
            valToInd.put(swappedWith, ind);
            list.remove(list.size() - 1);
            valToInd.remove(val);
            return true;
        }

        /**
         * Get a random element from the set.
         */
        public int getRandom() {
            int max = list.size();
            int min = 0;
            int ind = (int) (Math.random() * (max - min) + min);
            return list.get(ind);
        }
    }
}

