package cn.buoy.leetcode.bitmanipulation;

public class Q318 {
    /**
     * https://www.youtube.com/watch?v=tNM44ZCknp8
     * 看視頻快速理解思路.
     * 思路: 用一位來表示一個字母, 當2word相 & 時, 爲0則不存在相同的字母, 這個時候來選出max product of the length of each string to every other string.
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
        int[] bitsCheckers = new int[words.length];
        int max = 0;
        // populating the checker array with their respective numbers
        for (int i = 0; i < bitsCheckers.length; i++) {
            int num = 0;
            for (int j = 0; j < words[i].length(); j++) {
                // 關鍵: 用一位來表示一種字母, 一個單詞所有不同字母組成的2進制數.
                num |= 1 << (words[i].charAt(j) - 'a');
            }
            bitsCheckers[i] = num;
        }

        for (int i = 0; i < words.length; i++) {
            for (int j = i + 1; j < words.length; j++) {
                //用 & 來判斷有没有存在相同的字母, 为0時, 一個都沒有.
                if ((bitsCheckers[i] & bitsCheckers[j]) == 0) //checking if the two strings have common character
                    max = Math.max(max, words[i].length() * words[j].length());
            }
        }
        return max;
    }

}
