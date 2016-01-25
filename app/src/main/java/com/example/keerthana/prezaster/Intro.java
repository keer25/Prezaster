package com.example.keerthana.prezaster;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class Intro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("Flow", "Reached Intro");
        setContentView(R.layout.activity_intro);
    }

    public void signup(View view) {
        startActivity(new Intent(this,Signup.class));
    }
}
