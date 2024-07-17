package cn.buoy.leetcode.tree;

import java.util.Stack;

public class Q255 {
    /**
     * 看懂就簡單, 一定要画图!
     * https://www.youtube.com/watch?v=Psce8aMuX8s
     * https://www.youtube.com/watch?v=p4dUsMe70Lk 思路比較好, 但還是不夠清晰, 然後看註釋來理解.
     * 原理: 如果遇到递增数时, 說明 遍歷到了 某個 root 的 "右 node", 所以, "後續遇到的 node" 都不應該 < "這個 root",
     * 怎麼得到 "這個 root" 呢? 一直 pop, 直到 stack.top >= curr, 最後 pop 出來的就是 "這個 root", 如此循環到結束.
     */
    public boolean verifyPreorder(int[] preorder) {
        int lowerBound = Integer.MIN_VALUE;
        Stack<Integer> stack = new Stack();
        for (int curr : preorder) {
            // 当前 node 小于 lowerBound, 返回false.
            if (curr < lowerBound)
                return false;
            // 關鍵: 一旦出現 遞增元素"curr", 說明 遍歷到了 某個 root 的 "右 node", 所以, "後續遇到的 node" 都不應該 < "這個 root",
            // 怎麼得到 "這個 root" 呢? 一直 pop, 直到 stack.top >= curr, 最後 pop 出來的就是 "這個 root", 如此循環到結束.
            while (!stack.empty() && curr > stack.peek())
                lowerBound = stack.pop();
            stack.push(curr);
        }
        return true;
    }
}
