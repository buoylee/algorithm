package cn.buoy.leetcode.stackandpq;

import java.util.*;

public class Q373 {
    /**
     * 明白原理就簡單, 代碼
     * https://www.youtube.com/watch?v=APZbA_q1zAc
     * 原理: '次小值' 可能出現在 '最小node' 的 '右或下格'.
     * 思路: 轉化爲 矩陣(實際用), 值爲 2數sum. 求當前queue最小.
     */
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int total) {
        // 比較 2個 sum 哪個小.
        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> a[0] + a[1] - b[0] - b[1]);
        List<List<Integer>> res = new ArrayList<>();
        if (nums1.length == 0 || nums2.length == 0 || total == 0) return res;
        // 先放入一整個首行, 細節優化: nums1.index < total
        for (int i = 0; i < nums1.length && i < total; i++)
            // num1.value, num2.value, num2.index
            queue.offer(new int[]{nums1[i], nums2[0], 0});
        // 拿出最小格, 插入 最小格的 右鄰居.
        while (total-- > 0 && !queue.isEmpty()) {
            // 最小 node
            int[] node = queue.poll();
            // num1.value, num2.value
            res.add(Arrays.asList(node[0], node[1]));
            // node[2] 就是 num2.index
            // 只是加入 最小sum那格的 右鄰居. 這裏只是限制合法的 nums2[]的 index,
            if (node[2] == nums2.length - 1) continue;
            queue.offer(new int[]{node[0], nums2[node[2] + 1], node[2] + 1});
        }
        return res;
    }

    public static void main(String[] args) {

    }
}

