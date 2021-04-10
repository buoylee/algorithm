package cn.buoy.leetcode.dfsandbfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Q51 {
    public List<List<String>> solveNQueens(int n) {
        //外层: 一共x种解法; 内层n 行, string 一行表示
        List<List<String>> res = new ArrayList<>();
        String[] queens = new String[n];
        char[] initial = new char[n];
        Arrays.fill(initial, '.');
        Arrays.fill(queens, String.valueOf(Arrays.copyOf(initial, n)));
        int[] flag = new int[5 * n - 2];
        Arrays.fill(flag, 1);
        slove(res, queens, flag, 0, n);
        return res;
    }

    //fixme
    //y = x, y = x - 1, /
    //y = -x, y = -x - 1, \
    private void slove(List<List<String>> res, String[] queens, int[] flag, int row, int n) {
        if (row == n) {
            res.add(new ArrayList<>(Arrays.asList(queens)));
            return;
        }
        for (int col = 0; col != n; col++) {
            if (flag[col] == 1 && flag[n + col + row] == 1 && flag[4 * n - 2 + col - row] == 1) {
                flag[col] = 0;
                flag[n + col + row] = 0;
                flag[4 * n - 2 + col - row] = 0;
                char[] chars = queens[row].toCharArray();

                chars[col] = 'Q';
                queens[row] = String.valueOf(chars);

                slove(res, queens, flag, row + 1, n);

                chars = queens[row].toCharArray();
                chars[col] = '.';
                queens[row] = String.valueOf(chars);

                flag[col] = 1;
                flag[n + col + row] = 1;
                flag[4 * n - 2 + col - row] = 1;
            }
        }
    }

    /*
    https://www.youtube.com/watch?v=-OVuYEtjVUE
     */
    //todo 回溯法
}
