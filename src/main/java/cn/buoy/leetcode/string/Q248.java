package cn.buoy.leetcode.string;

public class Q248 {
    /**
     * 直接看题目, 看注释, 是好理解的. 可以不看视频. yt 目前找不到好的.
     * https://www.youtube.com/watch?v=-4q6HsR5zzg
     * 思路: 从外层到内(因为最外层不能是0开头, 这样思考比较直观), 构建 合法的 180旋转后 相同的数字.
     * 除了0开头, 还有考虑 中位数的 digit 的选择, (只能是 0, 1, 8)
     */
    private static final char[][] pairs = {{'0', '0'}, {'1', '1'}, {'6', '9'}, {'8', '8'}, {'9', '6'}};

    public int strobogrammaticInRange(String low, String high) {
        int[] count = {0};
        // 开始(low.length()) 和 结束(high.length()) 都是 具体数字的"位数"
        for (int numStrLen = low.length(); numStrLen <= high.length(); numStrLen++) {
            char[] c = new char[numStrLen];
            // 因为用的 array 来表示 数的str, 所以 末尾 numStrLen - 1 表示.
            dfs(low, high, c, 0, numStrLen - 1, count);
        }
        return count[0];
    }

    /**
     * @param lowNum  题目最小值
     * @param highNum 题目最大值
     * @param charArr "题目最小值 到 题目最大值" 范围内的数字的 "位数" 作为 "char[].length" 的 array
     * @param left    当前需要填充的 左index
     * @param right   当前需要填充的 右index
     * @param count
     */
    public void dfs(String lowNum, String highNum, char[] charArr, int left, int right, int[] count) {
        if (left > right) {
            String s = new String(charArr);
            // 意义: 当前数字 不能 超过 lowNum <= x <= highNum 题目的范围
            // 下边的写法是表示: string 表示的"相同位数的数字" 比较大小的方法
            if ((s.length() == lowNum.length() && s.compareTo(lowNum) < 0) ||
                    (s.length() == highNum.length() && s.compareTo(highNum) > 0)) {
                return;
            }
            count[0]++;
            return;
        }
        // "由外层到内" 填充合适的 pair, 到中位数为止.
        for (char[] p : pairs) {
            charArr[left] = p[0];
            charArr[right] = p[1];
            // 跳过不合法的组合, 这个组合的第一个digit为0, 且不是"只有个位数"的数字. 例如: "05", "073"; 因为, 只有个位数的 "0" 是合法的.
            if (charArr.length != 1 && charArr[0] == '0')
                continue;
            // 跳过不合法的组合, 有2种情况(同一个位置不可能出现2种digit(6, 9; 9, 6)), 1. 中位数; 2. 只有"个位"的数. 他们的 pair 必须是相同的数(0, 1, 8)中选一个.
            if (left == right && p[0] != p[1])
                continue;
            dfs(lowNum, highNum, charArr, left + 1, right - 1, count);
        }
    }


    //todo 这个时空更优
//    private static char[][] pairs = new char[][]{{'0', '0'}, {'1', '1'}, {'6', '9'}, {'8', '8'}, {'9', '6'}};
    public int strobogrammaticInRange2(String low, String high) {
        if (low.length() > high.length() || low.length() == high.length() && high.compareTo(low) < 0) return 0;
        return strobogrammaticInRangeFrom0(high, true) - strobogrammaticInRangeFrom0(low, false);
    }

    private int strobogrammaticInRangeFrom0(String num, boolean inclusive) {
        int len = num.length();
        if (len == 1) {
            if (num.charAt(0) == '0') return inclusive ? 1 : 0;       // 0?
            else if (num.charAt(0) == '1') return inclusive ? 2 : 1;       // 0,1?
            else if (num.charAt(0) < '8') return 2;                       // 0,1
            else if (num.charAt(0) == '8') return inclusive ? 3 : 2;       // 0,1,8?
            else return 3;                       // 0,1,8
        }
        int sum = 0;
        for (int i = 1; i < len; i++)
            sum += strobogrammaticDigit(i, true);
        sum += strobogrammaticInRangeSameDigits(new char[len], 0, len - 1, num.toCharArray(), inclusive);
        return sum;
    }

    private int strobogrammaticInRangeSameDigits(char[] chs, int i, int j, char[] upper, boolean inclusive) {
        int sum = 0;
        if (i > j) {
            if (inclusive && compareCharArray(upper, chs, chs.length - 1) >= 0 || !inclusive && compareCharArray(upper, chs, chs.length - 1) > 0)
                return 1;
            else return 0;
        }
        for (char[] pair : pairs) {
            if (i == 0 && pair[0] == '0' || i == j && (pair[0] == '6' || pair[0] == '9')) continue;
            chs[i] = pair[0];
            chs[j] = pair[1];
            if (compareCharArray(chs, upper, i) > 0) break;
            sum += strobogrammaticInRangeSameDigits(chs, i + 1, j - 1, upper, inclusive);
        }
        return sum;
    }

    private int strobogrammaticDigit(int digit, boolean outside) {
        if (digit == 0) return 1;
        if (digit == 1) return 3;
        return outside ? strobogrammaticDigit(digit - 2, false) * 4 : strobogrammaticDigit(digit - 2, false) * 5;
    }

    private int compareCharArray(char[] arr1, char[] arr2, int idx) {
        for (int i = 0; i <= idx; i++)
            if (arr1[i] == arr2[i]) continue;
            else if (arr1[i] > arr2[i]) return 1;
            else return -1;
        return 0;
    }
}
