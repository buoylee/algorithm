package cn.buoy.leetcode.design;

import java.util.*;

public class Q355 {
    /**
     * https://www.youtube.com/watch?v=q6RILQAaFvc
     * https://www.youtube.com/watch?v=vUGrlKvib1k 短
     */
    static class Twitter {
        private static int timeStamp = 0;

        // easy to find if user exist
        private Map<Integer, User> userMap;

        // Tweet link to next Tweet so that we can save a lot of time
        // when we execute getNewsFeed(userId)
        private class Tweet {
            public int id;
            public int time;
            public Tweet next;

            public Tweet(int id) {
                this.id = id;
                time = timeStamp++;
                next = null;
            }
        }


        // OO design so User can follow, unfollow and post itself
        public class User {
            public int id;
            //followed 用set完事, add remove O(1)
            public Set<Integer> followed;
            public Tweet userLatestTweetHead;

            public User(int id) {
                this.id = id;
                followed = new HashSet<>();
                follow(id); // first follow itself
                userLatestTweetHead = null;
            }

            public void follow(int id) {
                followed.add(id);
            }

            public void unfollow(int id) {
                followed.remove(id);
            }


            // everytime user post a new tweet, add it to the head of tweet list.
            // 關鍵: 插到 user 的 tweet 头部
            public void post(int id) {
                Tweet t = new Tweet(id);
                t.next = userLatestTweetHead;
                userLatestTweetHead = t;
            }
        }

        public Twitter() {
            //存 userId 和 userId 的 user 对象
            userMap = new HashMap<Integer, User>();
        }

        /**
         * Compose a new tweet.
         */
        public void postTweet(int userId, int tweetId) {
            if (!userMap.containsKey(userId)) {
                User u = new User(userId);
                userMap.put(userId, u);
            }
            userMap.get(userId).post(tweetId);
        }


        // Best part of this.
        // first get all tweets lists from one user including itself and all people it followed.
        // Second add all heads into a max heap. Every time we poll a tweet with
        // largest time stamp from the heap, then we add its next tweet into the heap.
        // So after adding all heads we only need to add 9 tweets at most into this
        // heap before we get the 10 most recent tweet.
        public List<Integer> getNewsFeed(int userId) {
            List<Integer> result = new LinkedList<>();
            if (!userMap.containsKey(userId)) return result;
            // 拿到某个 user 所有 followed
            Set<Integer> users = userMap.get(userId).followed;
            // 按時間倒序 queue, 這不是需要返回的結果
            PriorityQueue<Tweet> queue = new PriorityQueue<Tweet>(users.size(), (a, b) -> (b.time - a.time));
            // 所有的 followed 的 tweet head 都推入 queue
            for (int user : users) {
                Tweet userLatestTweetHead = userMap.get(user).userLatestTweetHead;
                // very imporant! If we add null to the head we are screwed.
                if (userLatestTweetHead != null)
                    queue.add(userLatestTweetHead);
            }
            // 返回的 tweet 數量
            int resultSize = 0;
            // 關鍵: 每從 queue 彈出一條, 記得把 "彈出的 node.next" push queue 來排序, 直至夠10條 resultSize.
            while (!queue.isEmpty() && resultSize < 10) {
                Tweet t = queue.poll();
                result.add(t.id);
                resultSize++;
                // 關鍵: "彈出的 node.next" push queue
                if (t.next != null)
                    queue.add(t.next);
            }
            return result;
        }

        /**
         * Follower follows a followee. If the operation is invalid, it should be a no-op.
         */
        public void follow(int followerId, int followeeId) {
            if (!userMap.containsKey(followerId)) {
                User u = new User(followerId);
                userMap.put(followerId, u);
            }
            if (!userMap.containsKey(followeeId)) {
                User u = new User(followeeId);
                userMap.put(followeeId, u);
            }
            userMap.get(followerId).follow(followeeId);
        }

        /**
         * Follower unfollows a followee. If the operation is invalid, it should be a no-op.
         */
        public void unfollow(int followerId, int followeeId) {
            // Follower unfollows a followee, 需要檢查 Follower != null 和 2者不能相同
            if (!userMap.containsKey(followerId) || followerId == followeeId)
                return;
            userMap.get(followerId).unfollow(followeeId);
        }
    }

    /**
     * Your Twitter object will be instantiated and called as such:
     * Twitter obj = new Twitter();
     * obj.postTweet(userId,tweetId);
     * List<Integer> param_2 = obj.getNewsFeed(userId);
     * obj.follow(followerId,followeeId);
     * obj.unfollow(followerId,followeeId);
     */
    class Twitter2 {
        ArrayList<Integer> tweetID;
        ArrayList<Integer> userID;
        Map<Integer, HashSet<Integer>> following;

        public Twitter2() {
            // Map<Integer, ArrayList<Integer>> tweet = new HashMap<>();
            following = new HashMap<>();
            tweetID = new ArrayList<>();
            userID = new ArrayList<>();
        }

        /**
         * Compose a new tweet.
         */
        public void postTweet(int userId, int tweetId) {
            tweetID.add(tweetId);
            userID.add(userId);
            // System.out.println(tweetID+"\n"+userID+"\n"+following);
        }

        /**
         * Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent.
         */
        public List<Integer> getNewsFeed(int userId) {
            List<Integer> newsFeed = new ArrayList<>();
            HashSet<Integer> set = following.get(userId);
            for (int i = userID.size() - 1; i >= 0 && newsFeed.size() < 10; i--) {
                int id = userID.get(i);
                if (userId == id || (set != null && set.contains(userID.get(i))))
                    newsFeed.add(tweetID.get(i));
            }
            return newsFeed;
        }

        /**
         * Follower follows a followee. If the operation is invalid, it should be a no-op.
         */
        public void follow(int followerId, int followeeId) {
            HashSet<Integer> set;
            set = following.get(followerId);
            if (set != null) {
                if (!set.contains(followeeId)) set.add(followeeId);
            } else {
                set = new HashSet<Integer>();
                set.add(followeeId);
            }
            following.put(followerId, set);
            // following.put(followerId,following.getOrDefault(followerId,new HashSet<Integer>()).add(followeeId));
        }

        /**
         * Follower unfollows a followee. If the operation is invalid, it should be a no-op.
         */
        public void unfollow(int followerId, int followeeId) {
            HashSet<Integer> set;
            set = following.get(followerId);
            if (set != null) {
                if (set.contains(followeeId)) set.remove(followeeId);
            } else {
                set = new HashSet<Integer>();
            }
            following.put(followerId, set);

            // System.out.println(tweetID);
        }
    }
}

