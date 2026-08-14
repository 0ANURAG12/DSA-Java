/*
 * LeetCode 355. Design Twitter
 * https://leetcode.com/problems/design-twitter/
 *
 * Approach:
 * - Design
 * - Maintain a User object for every user.
 * - Each User stores:
 *      1. The users they follow.
 *      2. Their tweets in reverse chronological order.
 * - Every tweet is assigned an increasing timestamp.
 * - To generate the news feed:
 *      1. Collect up to the latest 10 tweets from the user.
 *      2. Collect up to the latest 10 tweets from every followed user.
 *      3. Insert all collected tweets into a max-heap ordered by timestamp.
 *      4. Extract the latest 10 tweets from the heap.
 *
 * Time Complexity:
 * - postTweet   : O(1)
 * - follow      : O(1)
 * - unfollow    : O(1)
 * - getNewsFeed : O((F × 10) log(F × 10))
 *   where F is the number of followed users.
 *
 * Space Complexity:
 * - O(U + T)
 *   where U is the number of users and T is the total number of tweets.
 */

class Tweet implements Comparable<Tweet> {

    int time;
    int tweetId;

    Tweet(int time, int tweetId) {
        this.time = time;
        this.tweetId = tweetId;
    }

    @Override
    public int compareTo(Tweet other) {
        // Max Heap based on timestamp
        return other.time - this.time;
    }
}

class User {

    int userId;
    HashSet<Integer> followers;
    List<Tweet> tweets;

    User(int userId) {
        this.userId = userId;
        followers = new HashSet<>();
        tweets = new LinkedList<>();
    }

    public void addTweet(Tweet tweet) {
        // Store newest tweet at the front
        tweets.add(0, tweet);
    }

    public void addFollower(int followeeId) {
        followers.add(followeeId);
    }

    public void removeFollower(int followeeId) {
        followers.remove(followeeId);
    }
}

class Twitter {

    HashMap<Integer, User> userMap;
    int timeCounter;

    public Twitter() {
        userMap = new HashMap<>();
        timeCounter = 0;
    }

    public void postTweet(int userId, int tweetId) {

        timeCounter++;

        if (!userMap.containsKey(userId)) {
            userMap.put(userId, new User(userId));
        }

        userMap.get(userId).addTweet(new Tweet(timeCounter, tweetId));
    }

    public List<Integer> getNewsFeed(int userId) {

        if (!userMap.containsKey(userId)) {
            return new ArrayList<>();
        }

        PriorityQueue<Tweet> maxHeap = new PriorityQueue<>();

        User user = userMap.get(userId);

        // Add tweets of followed users
        for (int followeeId : user.followers) {

            int count = 0;

            for (Tweet tweet : userMap.get(followeeId).tweets) {
                maxHeap.offer(tweet);

                if (++count == 10) {
                    break;
                }
            }
        }

        // Add user's own tweets
        int count = 0;
        for (Tweet tweet : user.tweets) {
            maxHeap.offer(tweet);

            if (++count == 10) {
                break;
            }
        }

        List<Integer> newsFeed = new ArrayList<>();

        while (!maxHeap.isEmpty() && newsFeed.size() < 10) {
            newsFeed.add(maxHeap.poll().tweetId);
        }

        return newsFeed;
    }

    public void follow(int followerId, int followeeId) {

        if (!userMap.containsKey(followerId)) {
            userMap.put(followerId, new User(followerId));
        }

        if (!userMap.containsKey(followeeId)) {
            userMap.put(followeeId, new User(followeeId));
        }

        userMap.get(followerId).addFollower(followeeId);
    }

    public void unfollow(int followerId, int followeeId) {

        if (!userMap.containsKey(followerId) || !userMap.containsKey(followeeId)) {
            return;
        }

        userMap.get(followerId).removeFollower(followeeId);
    }
}

/**
 * Your Twitter object will be instantiated and called as such:
 * Twitter obj = new Twitter();
 * obj.postTweet(userId, tweetId);
 * List<Integer> param_2 = obj.getNewsFeed(userId);
 * obj.follow(followerId, followeeId);
 * obj.unfollow(followerId, followeeId);
 */
