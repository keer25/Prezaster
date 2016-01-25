package com.example.keerthana.prezaster;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class Signup extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
    }
    //TODO Add string resources for the function and layout
    public void createUser(View view){
        Log.i("Flow", "Signup called");
        String username = ((EditText) findViewById(R.id.sname)).getText().toString();
        String password = ((EditText) findViewById(R.id.spass)).getText().toString();
        String passwordConf = ((EditText) findViewById(R.id.spassconf)).getText().toString();
        //Validating the user details
        boolean valError = false;
        StringBuilder errorMessage =  new StringBuilder("Invalid Details: ");
        if(username.length() == 0) {
            valError = true;
            errorMessage.append("Username is required ");
        }
        else if(password.length() == 0) {
            valError = true;
            errorMessage.append("Password is Required");
        }
        else if(!password.equals(passwordConf)) {
            valError = true;
            errorMessage.append("Passwords don't Match");
        }
        if (valError){
            Toast.makeText(this, errorMessage.toString(), Toast.LENGTH_LONG).show();
        }
        else {
            //Setting up a parse user
            ParseUser user = new ParseUser();
            user.setUsername(username);
            user.setPassword(password);
            //Signing up in the cloud
            user.signUpInBackground(new SignUpCallback() {
                @Override
                public void done(com.parse.ParseException e) {
                    if( e != null){
                        Toast.makeText(Signup.this,e.getMessage(),Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(Signup.this,"Account Created", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(getApplicationContext(),UserSpace.class));
                    }
                }
            });
        }
    }
}
