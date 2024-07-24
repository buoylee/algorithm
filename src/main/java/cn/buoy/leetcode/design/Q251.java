package cn.buoy.leetcode.design;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class Q251 {
    /**
     * 簡單, 視頻, 代碼
     * https://www.youtube.com/watch?v=VQ0Wva3TaeI
     * 思路: 外 arr index, 内 arr index, 2指针.
     */
    class Vector2D {
        private int[][] arr2D;
        private int outer, inner;

        public Vector2D(int[][] arr2D) {
            this.arr2D = arr2D;
            this.outer = 0;
            this.inner = 0;
        }

        // inner 超出 的情況, 在 hasNext() 裏處理, 因爲 hasNext() 還要處理 outer 跳到下一個.
        public int next() {
            if (!hasNext()) throw new NoSuchElementException();
            return arr2D[outer][inner++];
        }

        public boolean hasNext() {
            // 如果是 內層到達最後一個, 外層 ++ 且 inner 歸零
            while (outer < arr2D.length && inner == arr2D[outer].length) {
                outer++;
                inner = 0;
            }
            // 又不是 inner 最後一個, outer 又沒超過. 就還有 next.
            return outer < arr2D.length;
        }
    }

    class Vector2D3 {

        int[][] v;
        int i = 0, j = 0;

        public Vector2D3(int[][] v) {
            this.v = v;
        }

        public int next() {
            //j++ 出界后, hasNext()会移动到外层的下一个arr.
            if (hasNext()) return v[i][j++];
            else return -1;
        }

        public boolean hasNext() {
            //注意这里 i < v.length, 边界, 因为需要出界来达到没有next的目的.
            while (i < v.length && j == v[i].length) {  // Move to next available vector
                i++;
                j = 0;
            }
            return i < v.length;
        }
    }

    /**
     * iterator
     */
    class Vector2D2 {

        private Iterator<List<Integer>> i;
        private Iterator<Integer> j;

        public Vector2D2(List<List<Integer>> vec2d) {
            i = vec2d.iterator();
        }

        public int next() {
            hasNext();
            return j.next();
        }

        public boolean hasNext() {
            //当外层 还有, 内层没, 则尝试找到下一个.
            //用while 是因为内层 可能是0元素.
            while ((j == null || !j.hasNext()) && i.hasNext())
                j = i.next().iterator();
            return j != null && j.hasNext();
        }
    }
}
