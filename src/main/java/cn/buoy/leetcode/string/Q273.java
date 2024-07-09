package cn.buoy.leetcode.string;

public class Q273 {
    /**
     * 簡單, 視頻, 代碼.
     * https://www.youtube.com/watch?v=R6BiEJG4BZ0
     * 思路: 對 數 的 不同 部分, 拆分遞歸處理.
     */
    private final String[] LESS_THAN_20 = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
    private final String[] TENS = {"", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
    private final String[] THOUSANDS = {"", "Thousand", "Million", "Billion"};

    public String numberToWords(int num) {
        if (num == 0) return "Zero";
        int thousandsArrIdx = 0;
        String result = "";
        // 處理 數中 '超過 1000 的這部分數' 的處理
        while (num > 0) {
            if (num % 1000 != 0)
                // 遞歸: 所以, ... Billion, ... Million, ... Thousand, (< 1000)
                result = helper(num % 1000) + THOUSANDS[thousandsArrIdx] + " " + result;
            num /= 1000;
            thousandsArrIdx++;
        }
        return result.trim();
    }

    // 處理 1000 以內 的部分.
    private String helper(int num) {
        if (num == 0)
            return "";
        else if (num < 20)
            return LESS_THAN_20[num] + " ";
        else if (num < 100)
            return TENS[num / 10] + " " + helper(num % 10);
        else
            return LESS_THAN_20[num / 100] + " Hundred " + helper(num % 100);
    }


    private final String[] belowTen = new String[]{"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine"};
    private final String[] belowTwenty = new String[]{"Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
    private final String[] belowHundred = new String[]{"", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};

    public String numberToWords2(int num) {
        if (num == 0) return "Zero";
        return helper2(num);
    }

    private String helper2(int num) {
        String result = new String();
        if (num < 10) result = belowTen[num];
        else if (num < 20) result = belowTwenty[num - 10];
        else if (num < 100) result = belowHundred[num / 10] + " " + helper(num % 10);
        else if (num < 1000) result = helper(num / 100) + " Hundred " + helper(num % 100);
            //1, 000, 000, 处理的是 中间 3位
        else if (num < 1000000) result = helper(num / 1000) + " Thousand " + helper(num % 1000);
        else if (num < 1000000000) result = helper(num / 1000000) + " Million " + helper(num % 1000000);
        else result = helper(num / 1000000000) + " Billion " + helper(num % 1000000000);
        return result.trim();
    }

    public static void main(String[] args) {
        Q273 q273 = new Q273();
        String s = q273.numberToWords(22800);
        System.out.println(s);
    }
}
