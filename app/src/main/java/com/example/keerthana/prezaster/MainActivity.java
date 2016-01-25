package com.example.keerthana.prezaster;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.parse.ParseObject;
import com.parse.ParseUser;

import junit.framework.Test;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //  Test Parse
        /*
        ParseObject testObject = new ParseObject("TestObject");
        testObject.put("foo", "bar");
        testObject.saveInBackground();
        */


        if (ParseUser.getCurrentUser() != null){
            //Navigating to login activity
            Log.i("Flow","User Registered");
        } else {
            //Show Welcome activity
            Log.i("Flow", "Intro intent");
            startActivity(new Intent(this, Intro.class));
        }

    }
}
