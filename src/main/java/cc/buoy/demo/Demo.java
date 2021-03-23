package cc.buoy.demo;

import java.util.Arrays;

public class Demo {

    public static void main(String[] args) {
//        System.out.println("aha");
//
//        int[] arr = {1, 2, 5, 9, 2, 4, 3};
//
//        int[] sort = Sort(arr);
//
//        System.out.println(Arrays.toString(sort));

        notOR();

    }

    //选择排序
    public static int[] Sort(int[] arr) {

        int len = arr.length;
        int[] res = Arrays.copyOf(arr, len);

        if (len < 2) {
            return res;
        }

        for (int i = 0; i < len - 1; i++) {
            int minIndex = i;
            int min = res[i];

            for (int j = i + 1; j < len; j++) {
                if (min > res[j]) {
                    minIndex = j;
                    min = res[j];
                }
//                j++;
            }

            if (minIndex != i) {
                int tmp = res[i];
                res[i] = res[minIndex];
                res[minIndex] = tmp;
            }
//            i++;
        }

        return res;
    }

    //抑或
    public static void notOR() {
        int a = 2321;
        int b = 98073;
        a = a ^ b;
        b = a ^ b;
        a = a ^ b;
        System.out.println(a + "_" + b);

    }


}
