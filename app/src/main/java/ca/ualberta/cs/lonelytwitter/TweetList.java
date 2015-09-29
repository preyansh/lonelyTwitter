package ca.ualberta.cs.lonelytwitter;

import java.util.ArrayList;

/**
 * Created by preyansh on 9/28/15.
 */
public class TweetList {
    private ArrayList<Tweet> tweets = new ArrayList<Tweet>();

    public void add(Tweet tweet){
        tweets.add(tweet);
    }
    public void delete(Tweet tweet){
        tweets.remove(tweet);
    }

    public Boolean contains(Tweet tweet){
        return tweets.contains(tweet);

    }
    public void addTweet(Tweet tweet){
        if (tweets.contains(tweet))
            throw new IllegalArgumentException("IllegalArgumentException");
        tweets.add(tweet);
    }
    public boolean hasTweet(Tweet tweet){
        if (tweets.contains(tweet)) {
            //System.out.println("Tweet in List");
            return true;
        }
        return false;
    }

}
