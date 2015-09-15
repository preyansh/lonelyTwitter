package ca.ualberta.cs.lonelytwitter;

import java.util.Date;

/**
 * Created by preyanshu on 9/14/15.
 */
public abstract class TweetMood {
    private String mood;
    private String date;

    public Time() {
        date="Sept.14. 2015" ;
    }

    public String getMood() {
        return mood;
    }

    public void setMood(String mood) {
        this.mood = mood;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
/*
-Add three new model classes to LonelyTwitter:
        -the first should be an abstract base class which represents the current mood.
        -The second and third should be non-abstract classes which represent different moods and inherit from the ABC.

mood:
>Each mood should have a date and getters and setters to access the date.
>A constructor which sets the date to a default
>a constructor which takes a date as an argument should be provided.

Encapuslation should be followed. Each mood should also have a mood-dependent format method which returns a string representing the mood. Finally, add a way for each tweet to have a list of moods
*/