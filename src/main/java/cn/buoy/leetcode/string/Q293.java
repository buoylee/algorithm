package cn.buoy.leetcode.string;

import java.util.ArrayList;
import java.util.List;

public class Q293 {

    public static void main(String[] args) {
        String s = "++++";
        Q293 q293 = new Q293();
        List<String> strings = q293.generatePossibleNextMoves(s);
        System.out.println(strings);
    }

    public List<String> generatePossibleNextMoves(String s) {
        List list = new ArrayList();
        //注意indexOf 第2个参数, 从哪个index开始找的第一个满足的index
        for (int i = -1; (i = s.indexOf("++", i + 1)) >= 0; )
            //前部分是 切 包括头, 不包括尾, 后边是从index开始一直到尾.
            list.add(s.substring(0, i) + "--" + s.substring(i + 2));
        return list;
    }
}
