package cn.buoy.leetcode.design;

import java.util.*;

public class Q355 {
    /**
     *https://www.youtube.com/watch?v=q6RILQAaFvc
     */

}

class Twitter {
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
        public Tweet tweet_head;

        public User(int id) {
            this.id = id;
            followed = new HashSet<>();
            follow(id); // first follow itself
            tweet_head = null;
        }

        public void follow(int id) {
            followed.add(id);
        }

        public void unfollow(int id) {
            followed.remove(id);
        }


        // everytime user post a new tweet, add it to the head of tweet list.
        //插到user自己的tweet头部
        public void post(int id) {
            Tweet t = new Tweet(id);
            t.next = tweet_head;
            tweet_head = t;
        }
    }


    /**
     * Initialize your data structure here.
     */
    public Twitter() {
        //存userId 和 user对象
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
    //关键代码
    public List<Integer> getNewsFeed(int userId) {
        List<Integer> res = new LinkedList<>();

        if (!userMap.containsKey(userId)) return res;
        //拿到某个user所有followed
        Set<Integer> users = userMap.get(userId).followed;
        //倒序queue
        PriorityQueue<Tweet> q = new PriorityQueue<Tweet>(users.size(), (a, b) -> (b.time - a.time));
        //所有的followed的tweet 都推入 q
        for (int user : users) {
            Tweet t = userMap.get(user).tweet_head;
            // very imporant! If we add null to the head we are screwed.
            if (t != null) {
                q.add(t);
            }
        }
        int n = 0;
        //放10条进去res就好
        while (!q.isEmpty() && n < 10) {
            Tweet t = q.poll();
            res.add(t.id);
            n++;
            if (t.next != null)
                q.add(t.next);
        }

        return res;

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

    /**
     * Initialize your data structure here.
     */
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