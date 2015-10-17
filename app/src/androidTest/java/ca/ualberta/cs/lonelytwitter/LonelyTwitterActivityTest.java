package ca.ualberta.cs.lonelytwitter;

import android.app.Activity;
import android.app.Instrumentation;
import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import junit.framework.TestCase;

/**
 * Created by wz on 14/09/15.
 */
public class LonelyTwitterActivityTest extends ActivityInstrumentationTestCase2 {

    private String tweetText;
    private EditText bodyText;
    private Button saveButton;

    public LonelyTwitterActivityTest() {
        super(ca.ualberta.cs.lonelytwitter.LonelyTwitterActivity.class);
    }

    public void testStart() throws Exception {
        Activity activity = getActivity();
    }

    public void testEditTweet(){
        LonelyTwitterActivity activity=(LonelyTwitterActivity) getActivity();

        activity.getTweets().clear;

        tweetText = "Hello!";
        bodyText = activity.getBodyText();
        activity.runOnUiThread(new Runnable() {
            public void run() {
                bodyText.setText(tweetText);
            }
        });
        bodyText.setText(tweetText);

        saveButton = activity.getSaveButton();
        activity.runOnUiThread(new Runnable() {
            public void run() {
                saveButton.performClick();
            }
        });
        getInstrumentation().waitForIdleSync();

        //gets list of tweets from view
        final ListView oldTweetsList=activity.getOldTweetsList();
        Tweet newestTweet=(Tweet)oldTweetsList.getItemAtPosition(0);
        assertEquals(tweetText,newestTweet.getText());

        //from https://developer.android.com/training/activity-testing/activity-functional-testing.html
        //from 2015-10-16
        // Set up an ActivityMonitor
        Instrumentation.ActivityMonitor receiverActivityMonitor =
                getInstrumentation().addMonitor(EditTweetActivity.class.getName(),
                        null, false);

        activity.runOnUiThread(new Runnable() {
            public void run() {
                View v = oldTweetsList.getChildAt(0);
                oldTweetsList.performItemClick(v, 0, v.getId());
            }
        });
        getInstrumentation().waitForIdleSync();

        // Validate that ReceiverActivity is started
        EditTweetActivity receiverActivity = (EditTweetActivity)
                receiverActivityMonitor.waitForActivityWithTimeout(1000);
        assertNotNull("ReceiverActivity is null", receiverActivity);
        assertEquals("Monitor for ReceiverActivity has not been called",
                1, receiverActivityMonitor.getHits());
        assertEquals("Activity is of wrong type",
                EditTweetActivity.class, receiverActivity.getClass());

        // Remove the ActivityMonitor
        getInstrumentation().removeMonitor(receiverActivityMonitor);

        //test that tweet editor starts up with correct tweet
        bodyText=activity.getBodyText();
        activity.runOnUiThread(new Runnable() {
            public void run() {

            }
        });

        //test that we can edit a tweet
        saveButton = activity.getSaveButton();
        activity.runOnUiThread(new Runnable() {
            public void run() {
                saveButton.checkInputConnectionProxy();
            }
        });

        //test that we can push a save button for the edited tweet

        //test that the modified tweet was saved

        //test that modified tweet is in the tweetlist

        assertTrue(activity.getTweets().getTweets().get(0).getText().equals(tweetText));
    }
}