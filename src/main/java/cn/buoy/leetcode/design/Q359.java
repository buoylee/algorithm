package cn.buoy.leetcode.design;

import java.util.HashMap;
import java.util.Map;

public class Q359 {
    /**
     * 超簡單, 視頻
     * https://www.youtube.com/watch?v=pSy2RSHYFLk
     * 思路: 存 <msg, timestamp + 10> 即可判斷 是否到達下次打印時間.
     */
    class Logger {
        // 存 <msg, timestamp + 10>
        private Map<String, Integer> map;

        public Logger() {
            map = new HashMap<>();
        }

        public boolean shouldPrintMessage(int timestamp, String message) {
            int newTimestamp = map.containsKey(message) ? map.get(message) : timestamp;
            if (newTimestamp > timestamp)
                return false;
            // 存10s后的时间, 超過這個時間即可打印
            map.put(message, timestamp + 10);
            return true;
        }
    }
}
