package cn.buoy.leetcode;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FindMiss {
    public static void main(String[] args) throws Exception {
        System.out.println("test");
        if (true) return ;

        FindMiss findMiss = new FindMiss("/Users/buoy/Documents/gitrepo/algorithm/src/main/java/cn/buoy/leetcode", "");
        List<String> list = findMiss.getList();
//        for (String f : list) {
//            System.out.println(f);
//        }
        Stream<String> fileList = list.stream().filter(s -> {
            return s.startsWith("Q");
        });
//        long q = fileList.count();

//        System.out.println(q);

        Set<String> set = new HashSet<String>(fileList.collect(Collectors.toList()));
        ArrayList<String> strings = new ArrayList<>();

        for (int i = 1; i <= 400; i++) {
            if (!set.contains("Q" + i + ".java"))
                strings.add(i + "");
        }

        for (String f : strings) {
            System.out.println(f);
        }

    }

    List res = new ArrayList<String>();

    private String dir_name = null;
    private String list_name = null;
    //    private BufferedWriter out = null;
    Vector<String> ver = null;

    public FindMiss(String dir_name, String list_name) throws IOException {
        this.dir_name = dir_name;    //文件夹地址
        this.list_name = list_name;    //保存文件列表的文件地址
        ver = new Vector<String>();    //用做堆栈
    }

    public List<String> getList() throws Exception {
//        out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(list_name, true)));    //以追加的方式写入到指定的文件
        ver.add(dir_name);
        while (ver.size() > 0) {
            File[] files = new File(ver.get(0).toString()).listFiles();    //获取该文件夹下所有的文件(夹)名
            ver.remove(0);

            int len = files.length;
            for (int i = 0; i < len; i++) {
//                String tmp = files[i].getAbsolutePath();
                String tmp = files[i].getAbsolutePath();

                if (files[i].isDirectory())    //如果是目录，则加入队列。以便进行后续处理
                    ver.add(tmp);
                else
//                    out.write(tmp + "\r\n");        //如果是文件，则直接输出文件名到指定的文件。
//                    res.add(tmp);
                    res.add(files[i].getName());

            }
        }
//        out.close();
        return res;
    }
}
