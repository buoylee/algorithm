package cn.buoy.leetcode.bitmanipulation;

public class Q318 {
    /**
     * https://www.youtube.com/watch?v=tNM44ZCknp8
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
        int[] checker = new int[words.length];
        int max = 0;
        // populating the checker array with their respective numbers
        //每一位1代表一个字母
        for (int i = 0; i < checker.length; i++) {
            //拼凑每个word 有有什么字母组成 (26位, 分别对应每个字母, 有则置1).
            int num = 0;
            for (int j = 0; j < words[i].length(); j++) {
                num |= 1 << (words[i].charAt(j) - 'a');
            }
            checker[i] = num;
        }

        for (int i = 0; i < words.length; i++) {
            for (int j = i + 1; j < words.length; j++) {
                //只要没有公共的字母, 与 操作 后, 肯定为0.
                if ((checker[i] & checker[j]) == 0) //checking if the two strings have common character
                    max = Math.max(max, words[i].length() * words[j].length());
            }
        }
        return max;
    }

}
