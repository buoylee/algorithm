package cn.buoy.leetcode.matrix;

public class Q302 {
    /**
     * 理解题目就好做了, 限制了只有1个黑区域, 只要找到 `1`能到达的 `最上下左右`
     *
     * 这是第2种方法: 遍历所有点, 记录 x的最大/小, y的最大/小.
     * https://www.youtube.com/watch?v=cXBP5EQXaKc
     *
     *
     *
     *
     */

    /**
     * 第一种解法:
     * 从参考点开始4向找最远能到哪.
     *
     * @param image
     * @param x
     * @param y
     * @return
     */
    public int minArea(char[][] image, int x, int y) {
        //只看参考点的左边区域, 下边同理
        int left = leftmost(image, 0, y, true);
        int right = rightmost(image, y, image[0].length - 1, true);
        int top = leftmost(image, 0, x, false);
        int bottom = rightmost(image, x, image.length - 1, false);
        return (right - left + 1) * (bottom - top + 1);
    }

    //找 0~参考点 可能存在的列
    int leftmost(char[][] image, int min, int max, boolean horizontal) {
        int l = min, r = max;
        //这种 2分写法, 最后l r会重叠, 在不满足的情况, 才mid++/--
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (!hasBlack(image, mid, horizontal)) {
                l = mid + 1;
            } else {
                //找这里列中是否有1, 有则继续往左找
                r = mid;
            }
        }
        return l;
    }

    int rightmost(char[][] image, int min, int max, boolean horizontal) {
        int l = min, r = max;
        while (l < r) {
            int mid = l + (r - l + 1) / 2;
            if (!hasBlack(image, mid, horizontal)) {
                r = mid - 1;
            } else {
                l = mid;
            }
        }
        return r;
    }

    //水平/垂直方向是否有1
    //这里的`是否水平`是不是写反了?
    boolean hasBlack(char[][] image, int mid, boolean horizontal) {
        if (horizontal) {
            for (int i = 0; i < image.length; i++) {
                if (image[i][mid] == '1') {
                    return true;
                }
            }
        } else {
            for (int j = 0; j < image[0].length; j++) {
                if (image[mid][j] == '1') {
                    return true;
                }
            }
        }
        return false;
    }
}
