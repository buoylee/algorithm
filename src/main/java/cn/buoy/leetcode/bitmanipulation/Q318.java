package cn.buoy.leetcode.bitmanipulation;

public class Q318 {
    /**
     * 看視頻快速理解思路.
     * https://www.youtube.com/watch?v=tNM44ZCknp8
     * 思路: 用 "不同位置的 bit" 來表示 不同的字母, 當 2word "相 &" 時, 爲0則不存在相同的字母,
     * 這個時候來選出 max product of the length of each string to every other string.
     *
     * @param words
     * @return The soultion is calcuated by doing a product of the length of
     * each string to every other string. Anyhow the constraint given is
     * that the two strings should not have any common character. This
     * is taken care by creating a unique number for every string. Image
     * a an 32 bit integer where 0 bit corresponds to 'a', 1st bit
     * corresponds to 'b' and so on.
     * <p>
     * Thus if two strings contain the same character when we do and
     * "AND" the result will not be zero and we can ignore that case.
     */
    public int maxProduct(String[] words) {
        // 保存 字母 转化为 bit 后的数据.
        int[] bitsCheckers = new int[words.length];
        int result = 0;
        // populating the checker array with their respective numbers
        // 构建 每个 word 的 bit表示字母 的形式数据
        for (int i = 0; i < bitsCheckers.length; i++) {
            int num = 0;
            for (int j = 0; j < words[i].length(); j++)
                // 關鍵: 用一位來表示一種字母, 一個單詞所有不同字母組成的2進制數.
                num |= 1 << (words[i].charAt(j) - 'a');
            bitsCheckers[i] = num;
        }
        // 暴力对比所有 2元素 是否存在相同字母, 没重复就判断 max
        for (int i = 0; i < words.length; i++) {
            for (int j = i + 1; j < words.length; j++)
                // 用 & 來判斷有没有存在相同的字母, 为0時, 一個都沒有.
                if ((bitsCheckers[i] & bitsCheckers[j]) == 0) //checking if the two strings have common character
                    result = Math.max(result, words[i].length() * words[j].length());
        }
        return result;
    }

}
