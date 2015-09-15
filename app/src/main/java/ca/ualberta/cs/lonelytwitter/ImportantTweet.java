package ca.ualberta.cs.lonelytwitter;

import java.io.IOException;
import java.util.Date;



import java.util.Date;

/**
 * Created by preyanshu on 9/14/15.
 */

//grabs everything from Tweet except for constructors
//OVERRIDING IN SUBCLASSES WILL OVERRIDE THE SUPER CLASS
public class ImportantTweet extends Tweet implements Tweetable{
    public ImportantTweet(String tweet, Date date) {
        super(tweet, date);
    }

    public ImportantTweet(String text) throws IOException {
        super(text);
        this.setText(text);
    }
    public Boolean isImportant() {
        return Boolean.TRUE;
    }

    /*@Override
    public void setText(String text){
        super.setText(text);
    }*/
}
