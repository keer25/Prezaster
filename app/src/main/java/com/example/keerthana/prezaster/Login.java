package com.example.keerthana.prezaster;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void authUser(View view) {
        String username = ((EditText) findViewById(R.id.lname)).getText().toString();
        String password = ((EditText) findViewById(R.id.lpassword)).getText().toString();
        boolean error = false;
        StringBuilder errorMessage = new StringBuilder("Invalid Creds: ");
        if (username.length()==0) {
            error = true;
            errorMessage.append("Username is required");
        }
        else if (password.length()==0) {
            error = true;
            errorMessage.append("Password is required");
        }

        if(error) {
            Toast.makeText(Login.this, errorMessage.toString(), Toast.LENGTH_LONG).show();
        } else {
            ParseUser.logInInBackground(username, password, new LogInCallback() {
                @Override
                public void done(ParseUser user, com.parse.ParseException e) {
                    if( e != null){
                        Toast.makeText(Login.this,e.getMessage(),Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(Login.this,"Logged in", Toast.LENGTH_SHORT).show();
                        //Redirect to user feed
                    }
                }
            });
        }
    }
}
