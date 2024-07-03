package cn.buoy.leetcode.matrix;

public class Q302 {
    /**
     * 題目座標只是作爲一個起點方便遍歷, 只要找到 black 能到达的 `最上下左右`.
     * 這總方法超簡單, 超直觀.
     * 思路: dfs遍历所有 black, 记录 x的最大/小, y的最大/小. 相減, 再乘就是結果.
     * https://www.youtube.com/watch?v=cXBP5EQXaKc
     */
    // x的最大/小, y的最大/小
    int minX, maxX, minY, maxY;

    public int minArea(char[][] image, int x, int y) {
        // init 無所謂
        minX = x;
        maxX = x;
        minY = y;
        maxY = y;
        dfs(image, x, y);
        return (maxX - minX + 1) * (maxY - minY + 1);
    }

    private void dfs(char[][] image, int x, int y) {
        if (x < 0 || x >= image.length || y < 0 || y >= image[0].length // 是否在 矩陣邊界 外
                || image[x][y] == '0') // 是否 == 0
            return;
        image[x][y] = '0';
        minX = Math.min(minX, x);
        maxX = Math.max(maxX, x);
        minY = Math.min(minY, y);
        maxY = Math.max(maxY, y);
        dfs(image, x - 1, y); // 上
        dfs(image, x + 1, y); // 下
        dfs(image, x, y - 1); // 左
        dfs(image, x, y + 1); // 右
    }

    /**
     * 簡單, 看下視頻思路就好, 代碼其實沒那麼好寫, 看下邊, 2分邊界問題可能不好理解.
     * https://www.youtube.com/watch?v=J9oXzbBUbCs
     * 第一种解法: 2分
     * 先搞清楚前提! 超重要, 後邊簡單. i 和 j 在這裏不是座標!!! 笑死! 表示第 i 行, 第 j 列. 即 (j, i)才是座標表示法.
     * 思路: 通過 題目給的點, 用2分法, 只需要找出 '這一個點' 的4個方向 存在的最遠 black. 就能算出結果.
     * 舉例左: 2分 0~j, mid這一列如果不存在black則往回找(右), 否則往左, 直至找到. 其他方向同理, 寫法有點繞, 要時間.
     */
    public int minArea2(char[][] image, int i, int j) { // 先搞清楚前提! 超重要, 後邊簡單. i,j 在這裏不是座標!!! 笑死! 表示第 i 行, 第 j 列.
        int m = image.length, n = image[0].length;
        int left = searchColumns(image, 0, j, 0, m, true);
        int right = searchColumns(image, j + 1, n, 0, m, false);
        int top = searchRows(image, 0, i, left, right, true);
        int bottom = searchRows(image, i + 1, m, left, right, false);
        return (right - left) * (bottom - top);
    }

    private int searchColumns(char[][] image, int left, int right, int top, int bottom, boolean opt) { // opt 表示: level 是否找到 black. 根本目的其實是爲了少些 2個函數 right 和 bottom.
        // todo 這種2分寫法, 也是一種典型
        while (left != right) {
            int mid = (left + right) / 2;
            int level = top;
            // 同一列, 往下找, 直到邊界 或 black
            while (level < bottom && image[level][mid] == '0') level++;
            // left: 如果是 black(沒到 bottom 就停了), 要繼續往左找
            // right: 如果到 bottom 了(level >= bottom), 要繼續往左找
            if (level < bottom == opt)
                right = mid;
            else  // left: 沒有black(到 bottom 還沒找到 black), 要繼續往右找; // right: 如果是 black, 要繼續往右找.
                left = mid + 1;
        }
        return left;
    }

    private int searchRows(char[][] image, int top, int bottom, int left, int right, boolean opt) {
        while (top != bottom) {
            int mid = (top + bottom) / 2;
            int width = left;
            // 同一行, 往右找, 直到邊界 或 black
            while (width < right && image[mid][width] == '0') width++;
            // top: 如果是 black(沒到 right 就停了), 要繼續往上找
            if (width < right == opt)
                bottom = mid;
            else // top: 沒有black(到 right 還沒找到 black), 要繼續往下找;
                top = mid + 1;
        }
        return top;
    }
}
