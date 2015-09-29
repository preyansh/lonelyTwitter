package ca.ualberta.cs.lonelytwitter;

import android.test.ActivityInstrumentationTestCase2;

import junit.framework.TestCase;

/**
 * Created by preyansh on 9/28/15.
 */
public class TweetListTest extends ActivityInstrumentationTestCase2 {
    public TweetListTest() {
        super(ca.ualberta.cs.lonelytwitter.LonelyTwitterActivity.class);
    }
    public void testAddTweet(){
        TweetList list=new TweetList();
        list.add(new NormalTweet("test"));
    }

    public void testDuplicateTweet(){
        TweetList list = new TweetList();
        Tweet my_tweet=new NormalTweet("test");
        list.add(my_tweet);
        try {
            list.addTweet(my_tweet);
            assertFalse(true);
        } catch (IllegalArgumentException exception){
            assertTrue(true);
        }
    }
    public void testHasTweet(){
        TweetList list = new TweetList();
        Tweet my_tweet=new NormalTweet("test");
        list.add(my_tweet);
        assertTrue(list.hasTweet(my_tweet));
        Tweet my_other_tweet= new NormalTweet("Test2");
        assertFalse(list.hasTweet(my_other_tweet));
    }

    public void testDelete(){
        TweetList list= new TweetList();
        Tweet tweet = new NormalTweet ("test");
        list.add(tweet);
        list.delete(tweet);
        assertFalse(list.contains(tweet));
    }
    public void testContains(){
        TweetList list = new TweetList();
        Tweet tweet = new NormalTweet("test");
        list.add(tweet);
        assertTrue(list.contains(tweet));
    }
}