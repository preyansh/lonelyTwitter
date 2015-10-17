package ca.ualberta.cs.lonelytwitter;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.Observer;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class LonelyTwitterActivity extends Activity implements MyObserver {

    private static final String FILENAME = "file.sav";

    private LonelyTwitterActivity activity = this;
    private EditText bodyText;
    private ListView oldTweetsList;
    private ArrayList<Tweet> tweets= new ArrayList<Tweet>(); //view
    private ArrayAdapter<Tweet> adapter;
    private Button saveButton;

    public ArrayList<Tweet> getTweets() {
        return tweets;
    }


    public EditText getBodyText() {
        return bodyText;
    }

    public Button getSaveButton() {
        return saveButton;
    }

    public ListView getOldTweetsList() {
        return oldTweetsList;
    }

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);//view
        setContentView(R.layout.main);//view

        bodyText = (EditText) findViewById(R.id.body);//view
        saveButton = (Button) findViewById(R.id.save);
        oldTweetsList = (ListView) findViewById(R.id.oldTweetsList);//view

        oldTweetsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent (activity , EditTweetActivity.class);
                startActivity(intent);
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                setResult(RESULT_OK);//move to controller
                String text = bodyText.getText().toString(); // move to controller
                tweets.add(new NormalTweet(text)); // move to controller
                saveInFile();  // move to model
                adapter.notifyDataSetChanged(); //model
            }
        });
    }

    @Override
    protected void onStart() {//view
        // TODO Auto-generated method stub
        super.onStart();//view
        loadFromFile();//model
        if (tweets == null) {
            throw new RuntimeException();//controller
        }
        adapter = new ArrayAdapter<Tweet>(this, R.layout.list_item, tweets);//view
        oldTweetsList.setAdapter(adapter);//view
    }

    private void loadFromFile() {//controller
        try {
            FileInputStream fis = openFileInput(FILENAME);//controller
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));//model
            Gson gson = new Gson();//model
            // Following line based on https://google-gson.googlecode.com/svn/trunk/gson/docs/javadocs/com/google/gson/Gson.html retrieved 2015-09-21
            Type listType = new TypeToken<ArrayList<NormalTweet>>() {
            }.getType();//model
            tweets = gson.fromJson(in, listType);//model

        } catch (FileNotFoundException e) {//controller
            tweets = new ArrayList<Tweet>();//model
        } catch (IOException e) {//controller
            throw new RuntimeException(e);//controller
        }
    }

    private void saveInFile() {//controller
        try {
            FileOutputStream fos = openFileOutput(FILENAME,
                    0);//controller
            OutputStreamWriter writer = new OutputStreamWriter(fos);//model
            Gson gson = new Gson();//model
            gson.toJson(tweets, writer);//controller
            writer.flush();//controller
            fos.close();//controller
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);//controller
        } catch (IOException e) {
            throw new RuntimeException(e);//controller
        }
    }

    public void myNotify(MyObservable observable) {
        adapter.notifyDataSetChanged();//model
    }
}