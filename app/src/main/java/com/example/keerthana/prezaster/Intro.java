package com.example.keerthana.prezaster;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class Intro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("Flow", "Reached Intro");
        setContentView(R.layout.activity_intro);
    }
}
