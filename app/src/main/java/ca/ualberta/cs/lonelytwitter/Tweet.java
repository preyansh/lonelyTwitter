package ca.ualberta.cs.lonelytwitter;

import java.io.IOException;
import java.util.Date;

/**
 * Created by preyanshu on 9/14/15.
 */

//STYLE IN JAVA: UPPERCASE FOR CLASSES, ATTRIBUTES AND METHODS are lowercase for the first bit and uppercase for the other parts
//Inheritence: allows you to create another thing that inherits all the methods and attributes of another class


// we dont want random people making tweets without permission
public abstract class Tweet {
    protected String text;
    private Date date;


    public Tweet(String tweet, Date date){
        text=tweet;
        this.date = date;
    }

    public Tweet(String text) {
        this.text=text;
        date=new Date();
    }

    //writes the tweet
    public String getText() {
        return text;
    }
    //x.y in java is basically x->t in C******************
    public void setText(String text) throws IOException{
        if (text.length()<=140) {
            this.text = text;
        } else{
            throw new IOException("Tweet was too long!!!");
            //exceptions are just objects that point to illegal arguments (othre types include runtime exceptions)
        }
    }
    //gets and sets the dates of the tweet
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public abstract Boolean isImportant();
}
