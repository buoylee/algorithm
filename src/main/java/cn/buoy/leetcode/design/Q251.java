package cn.buoy.leetcode.design;

import java.util.Iterator;
import java.util.List;

public class Q251 {
    /**
     * https://www.youtube.com/watch?v=VQ0Wva3TaeI
     * 用 内, 外 2指针.
     */
    class Vector2D {

        int[][] v;
        int i = 0, j = 0;

        public Vector2D(int[][] v) {
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
