package cn.buoy.leetcode.backtracking;

import java.util.ArrayList;
import java.util.List;

public class Q60 {
    /**
     * 看懂規律就簡單, 視頻, 代碼
     * https://www.youtube.com/watch?v=p82WpXi_l_s
     * 例子:
     * "1 23"
     * "1 32"
     * <p>
     * "2 13"
     * "2 31"
     * <p>
     * "3 12"
     * "3 21"
     * <p>
     * 思路: 我們可以觀察到 我們用 (k - 1)(0-based index) / !(n 數字的數量 - 1) 就是 得到 第一位的數字的 - 1, 因爲 是我們會把 nums[i] 存成 num[0] = 1, ...
     * 然後 (k - 1) % !(數字的數量 - 1) 就是 k -1 在下一層的 index.
     * 這樣就很簡單了, 不斷遞歸求出對應數字即可.
     * 關鍵: nums[i]我們用 list 來存, 爲了方便 我們在使用玩1個數字時, 刪掉他, 使得他的後邊位置的數字的剛好向前挪1爲. 剛好符合繼續取數字的 index.
     */
    public String getPermutation(int n, int k) {
        // 關鍵: 構建 [1-n] arr, 爲了後取取出 k 的首位數字後刪除, 可以 剛好在下一次取數字時, 取道對應的數字.
        List<Integer> numbers = new ArrayList<>();
        for (int i = 1; i <= n; i++)
            numbers.add(i);
        // 關鍵: k 是從1開始數的, 如果轉化爲 index 也就是 0開始數, 剛好 k / fact(n - 1) = 對應 numbers 的數字, fact(n - 1) 就是 除掉第一個數, 剩餘的數的 階乘.
        return getPermutation(numbers, k - 1); // k-1 to convert to 0-based index
    }

    private String getPermutation(List<Integer> numbers, int k) {
        if (numbers.size() == 0) return "";
        int n = numbers.size();
        // 關鍵: 減1的階乘就是, 用 (k - 1)(函數傳入時, 已經減1, index格式)/ fact(n-1), 就是 "相同的 第一位數" 會有幾個.
        int fact = factorial(n - 1);
        // 第一個位置的數是什麼, 要去 numbers 里找
        int index = k / fact;
        // 關鍵: 直接刪除中間的元素, 剛好符合 接下來取 對應數字的 index
        int num = numbers.remove(index);
        // 關鍵: 除去第一位數後, 在該子範圍內 的 index.
        k %= fact;
        // 構建 str = 首位數字 + 遞歸...
        return num + getPermutation(numbers, k);
    }

    private int factorial(int n) {
        if (n == 0) return 1;
        int result = 1;
        for (int i = 1; i <= n; i++) {
            result *= i;
        }
        return result;
    }

    /**
     * https://www.youtube.com/watch?v=xdvPD1IiyUM
     * 有够难
     * 求!maxNum的所有组合中, 第orderNum的排列顺序.
     * <p>
     * 1,              2,              3,               4
     * n/!(4-1) == 6, n-1/!(4-2) == 4, n-2/!(4-3) == 1,
     * <p>
     * 1234, 1243, 1324, 1342, 1423, 1432,
     * 2134, 2143, 2314, 2314, 2413, 2431,
     * ...
     * 一定要先观察规律, 被拿走中间的数后, 仍然按照原来的顺序由小到继续排列组合.
     */
    public String getPermutation2(int maxNum, int orderNum) {
        int pos = 0;

        // factorial[] = {1, 1, 2, 6, 24, ... n!}
        int[] factorial = new int[maxNum + 1];
        StringBuilder sb = new StringBuilder();

        // create an array of factorial lookup
        int sum = 1;
        factorial[0] = 1;
        for (int i = 1; i <= maxNum; i++) {
            sum *= i;
            factorial[i] = sum;
        }

        // create a list of numbers to get indices
        //从1开始到n
        // numbers = {1, 2, 3, 4}
        List<Integer> numbers = new ArrayList<>();
        for (int i = 1; i <= maxNum; i++) {
            numbers.add(i);
        }

        //为了和numbers数组的index对应.
        orderNum--;

        for (int i = 1; i <= maxNum; i++) {
            //k在此轮哪个区间里.
            //第1轮: n种可能; 第2轮: n-1种可能; 第3轮: n-2种可能;
            // 思路要转过来: 现在要找第一位数字的位置, 那就要从1~n内找出来,
            // 所以这里的 总数 与 需要的 被除数 分别是:
            // (1): !n, !(n-1);
            // (2): !(n-1), !(n-2);...

            int index = orderNum / factorial[maxNum - i]; // 4 - 1; 4 - 2; 4 - 3;
            sb.append(String.valueOf(numbers.get(index)));
            numbers.remove(index);
            //下边2行相等, 一个是 orderNum 减去 前边index个(如果k在 从0数 第 index == 1, 就要减去1个factorial[n - i])区间所有元素 得到 我们要的元素 在 第几位; 一个是 取余, 直接拿到该区间第几位.
//            orderNum -= index * factorial[n - i];
            orderNum %= factorial[maxNum - i];

        }

        return String.valueOf(sb);
    }
}
