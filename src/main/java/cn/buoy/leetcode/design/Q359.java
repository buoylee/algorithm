package cn.buoy.leetcode.design;

import java.util.HashMap;
import java.util.Map;

public class Q359 {
    /**
     *
     */
    class Logger {
        private Map<String, Integer> map;

        public Logger() {
            map = new HashMap<>();
        }

        public boolean shouldPrintMessage(int timestamp, String message) {
            int newTimestamp = map.containsKey(message) ? map.get(message) : timestamp;
            if (newTimestamp > timestamp) {
                return false;
            }
            //存10s后的时间, 下轮用新时间比就好
            map.put(message, timestamp + 10);
            return true;
        }
    }
}
